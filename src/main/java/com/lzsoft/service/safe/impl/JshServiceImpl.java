package com.lzsoft.service.safe.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.common.TranDetailEntity;
import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.entity.safe.JshDEntity;
import com.lzsoft.entity.safe.JshEEntity;
import com.lzsoft.entity.safe.JshFEntity;
import com.lzsoft.entity.safe.JshGEntity;
import com.lzsoft.entity.safe.SafeBaseEntity;
import com.lzsoft.service.safe.JshServiceI;

@Service("jshService")
@Transactional
public class JshServiceImpl extends ExtractServiceImpl implements JshServiceI {

	/**
	 * 从trans数据中提取最新交易日下的jsh数据
	 */
	@Override
	public String extracteJshReport() {
		
		//查询最新交易日期
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class);
		cq.eq("taskname", "autoExtractJsh");
		cq.max("importdate");
		cq.add();
		Date maxImportdate = getUniqueResultByCriteriaQuery(cq);

		//查询当天的提取jsh任务信息
		CriteriaQuery cq2 = new CriteriaQuery(TaskscheduleEntity.class);
		cq2.eq("importdate", maxImportdate);
		cq2.eq("taskname", "autoExtractJsh");
		cq2.eq("executable", true);
		cq2.add();
		TaskscheduleEntity o = getUniqueObjectByCriteriaQuery(cq2);
		
		//如果未执行，进行执行
		String mes = "";
		if (null != o) {
			LogUtil.info("Start extracte"
					+ DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "JshReport.........." + (new Date()));
			//从trans中提取jsh数据
			mes = extracteJshReport(maxImportdate, o);
			LogUtil.info(mes + ".........." + (new Date()));
			//更新任务信息
			o.setExecutable(false);
			saveOrUpdate(o);
		} else {
			LogUtil.info(DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "Jsh 已经提取过.........." + (new Date()));
		}
		return mes;
	}

	/**
	 * 提取外汇账户在（maxImportdate)日期下的上报数据
	 * @param maxImportdate
	 * @param task
	 * @return 1:正常返回 2:没有符合账户性质的外汇账号 3:没有交易数据,也没有开关户数据 4:有交易数据但是没有账户信息
	 *         5:没有与外汇账号关联的主体信息 6:有开户数据，但是交易数据无关联账户信息
	 */
	@Override
	public String extracteJshReport(Date maxImportdate, TaskscheduleEntity task) {

		//提取结售汇的业务记录，并且删除原有提取的
		List<TranDetailEntity> trans = getTransByImportdate(maxImportdate);
		/*add by tf 20170803*/
		List<TranDetailEntity> trans1 = getTransByImportdate1(maxImportdate);
		System.out.println("JSH");
		System.out.println("[csn<>=0 && csnm not like '9%']的size=["+trans.size() + "]笔");
		System.out.println("[csnm=0]的size=["+trans1.size() + "]笔");
		List<TranDetailEntity> transtmp = new ArrayList();
		/*trans1中的##之间的如果是客户号就保留*/
		if (null != trans1 && !trans1.isEmpty()) {
			//int i = 0;
			for (TranDetailEntity t : trans1) {			
				String sAcod = t.getAcod();
				if ( sAcod == "") {
					System.out.println("sAcod 为空");
					continue;
				}
				char sAcodH = t.getAcod().charAt(0);
				if ((sAcodH >= 'A' && sAcodH <= 'Z') && ("14211".equals(sAcod.substring(3, 8))
						|| "14217".equals(sAcod.substring(3, 8))
						|| "14218".equals(sAcod.substring(3, 8)))) {
					CriteriaQuery cqt = new CriteriaQuery(CustBaseEntity.class);
					cqt.eq("csnm", t.getPnar().substring(1, 8));
					cqt.eq("importdate", maxImportdate);
					cqt.add();
					CustBaseEntity custbase_t = getUniqueObjectByCriteriaQuery(cqt);
					if(null == custbase_t) {
						//trans1.remove(i);modify by fenny 20171206
						System.out.println("remove tid :" + t.getTid() + ", csnm:" + t.getPnar().substring(1, 8));
					} else {
						//i=i+1;
						System.out.println("add tid :" + t.getTid() + ", csnm:" + t.getPnar().substring(1, 8));
						transtmp.add(t);//modify by fenny 20171206
					}
				} else {
					//trans1.remove(i);modify by fenny 20171206
					System.out.println("remove tid :" + t.getTid() + ", acod:" + t.getAcod());
				}
			}
		}
		if (transtmp.size() > 0) {//modify by fenny 20171206
			trans.addAll(transtmp);
		}
		System.out.println("[total]size=["+trans.size() + "]笔");
		/*end 20170803*/
		
		//去除使用中间账号作结售汇的交易
		filterTrans(trans);

		int[] count = null;
		if (null != trans && !trans.isEmpty()) {
			//得到所有JSH交易的客户信息
			List<String> csnms = getCnums1(trans);
			List<CustBaseEntity> custs = getCustinfos(maxImportdate, csnms);

			// 去除对私客户
			Map<String, CustBaseEntity> custMaps = new HashMap<String, CustBaseEntity>();
			for (CustBaseEntity cust : custs) {
				if(!StringUtils.equals("A1", cust.getSource())) {
					custMaps.put(cust.getCsnm(), cust);
				}
			}
			
			List<SafeBaseEntity> jshList = new LinkedList<SafeBaseEntity>();
			try {
				//将trans信息转化为jsh信息
				count = setJshDatas(trans, custMaps, jshList);
				if (0 != count[1] && !jshList.isEmpty()) {
					//将业务信息保存在结售汇相应的信息表中
					batchSave(jshList);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.error("提取JSH发生异常", e);
			}
		} else {
			count = new int[] { 0, 0, 0, 0 };
		}
		return getExtractMessages4Jsh(count);
	}
	
	/**
	 * 去除使用中间账号作结售汇的交易
	 * @param trans
	 */
	private void filterTrans(List<TranDetailEntity> trans) {
		String inbopn = "234567";
		List<TranDetailEntity> temp = new ArrayList<>();
		for (TranDetailEntity tran : trans) {
			if (null != tran.getInbop() && inbopn.indexOf(tran.getInbop()) > 0
					&& tran.getFcyamt().compareTo(new BigDecimal(0)) == 0) {// fcyamt==0说明已经在BOP报过结、购汇金额
				temp.add(tran);
			}
		}
		if (temp.size() > 0) {
			trans.removeAll(temp);
		}
	}

	/**
	 * 将trans信息转换为JSH信息，返回数组{0,结售汇总数量，结汇数量，售汇数量}
	 * @param trans
	 * @param custMaps
	 * @param jshList
	 * @return
	 * @throws Exception
	 */
	public int[] setJshDatas(List<TranDetailEntity> trans,
			Map<String, CustBaseEntity> custMaps, List<SafeBaseEntity> jshList)
			throws Exception {
		int dNum = 0;
		int eNum = 0;
		String csnm="";
		for (TranDetailEntity tran : trans) {
			Object jsh = null;
			Object jshM = null;
			if("0".equals(tran.getCsnm())) {
				csnm = tran.getPnar().substring(1, 8);
			} else {
				csnm = tran.getCsnm();
			}
			System.out.println("setJSHdata: " + csnm);
			//if(null != custMaps.get(tran.getCsnm())) {
			if ( null != custMaps.get(csnm) ) {
				if (StringUtils.equals("0", tran.getDrcr())) {
					jsh = new JshDEntity();
					convertIfxtransTOJsh(jsh, tran, custMaps.get(csnm),//custMaps.get(tran.getCsnm()),modify by fenny 20171206
							"D");
					jshM = new JshFEntity();
					BeanUtils.copyProperties(jshM, jsh);
					dNum++;
				} else if (StringUtils.equals("1", tran.getDrcr())) {
					jsh = new JshEEntity();
					convertIfxtransTOJsh(jsh, tran, custMaps.get(csnm),//custMaps.get(tran.getCsnm()),modify by fenny 20171206
							"E");
					jshM = new JshGEntity();
					BeanUtils.copyProperties(jshM, jsh);
	
					eNum++;
				}
			}
			if (null != jsh) {
				//检查账户信息
				checkAccountInfo(tran, jsh);
				if ("2C".equals(tran.getTransactionremark())) {
					PropertyUtils.setProperty(jshM, "cap", true);
				} else {
					PropertyUtils.setProperty(jshM, "cap", false);
				}
				jshList.add((SafeBaseEntity) jsh);
				jshList.add((SafeBaseEntity) jshM);
			}

		}

		return new int[] { 0, dNum + eNum, dNum, eNum };
	}

	/**
	 * 根据trans数据转化为jsh数据
	 * @param tran
	 * @param jsh
	 */
	public void convertIfxtransTOJsh(Object jsh, TranDetailEntity tran,
			CustBaseEntity cust, String jshType) throws Exception {
		PropertyUtils.setProperty(jsh, "actiontype", "A");// 操作类型
		PropertyUtils.setProperty(jsh, "actiondesc", null);// 修改/删除原因
		PropertyUtils.setProperty(jsh, "rptno", getBaseRptnoByBrca(tran));// 申报号码
		PropertyUtils.setProperty(jsh, "buscode", tran.getDlref());// 银行业务编号
		setCustypeAndIdnumber(jsh, cust);
		PropertyUtils.setProperty(jsh, "custnm", cust.getCtnm());// 收款人名称

		PropertyUtils.setProperty(jsh, "fcyacc", tran.getMainacod());
		PropertyUtils.setProperty(jsh, "lcyacc", tran.getOpaccount());

		PropertyUtils.setProperty(jsh, "oppuser", cust.getCtnm());// 收款人与付款人名称一致
		PropertyUtils.setProperty(jsh, "oppbank", tran.getCounpartyBankco());//
		if ("D".equals(jshType)) {
			PropertyUtils.setProperty(jsh, "fcyamt", tran.getExamt());
			PropertyUtils.setProperty(jsh, "fcyccy", tran.getCcy());

		} else if ("E".equals(jshType)) {
			PropertyUtils.setProperty(jsh, "lcyamt", tran.getExamt());
			PropertyUtils.setProperty(jsh, "lcyccy", tran.getCcy());
		}
		PropertyUtils.setProperty(jsh, "exrate", tran.getExrate());
		PropertyUtils.setProperty(jsh, "brca", tran.getBrca());
		PropertyUtils.setProperty(jsh, "isdel", "0");
		PropertyUtils.setProperty(jsh, "isedit", "0");
		PropertyUtils.setProperty(jsh, "isexport", "0");
		PropertyUtils.setProperty(jsh, "ishandadd", "0");
		PropertyUtils.setProperty(jsh, "isvalidation", "0");
		PropertyUtils.setProperty(jsh, "isinsafe", "0");
		PropertyUtils.setProperty(jsh, "importdate", tran.getImportdate());

	}

	/**
	 * 得到所有JSH交易的客户号
	 * @param trans
	 */
	private List<String> getCnums(List<TranDetailEntity> trans) {
		List<String> csnmList = new ArrayList<String>();
		for (TranDetailEntity tran : trans) {
			if (StringUtils.isNotEmpty(tran.getCsnm())) {
				if (!csnmList.contains(tran.getCsnm())) {
					csnmList.add(tran.getCsnm());
				}
			}
		}
		return csnmList;
	}

	/*add by tf 20170802*/
	private List<String> getCnums1(List<TranDetailEntity> trans) {
		List<String> csnmList = new ArrayList<String>();
		for (TranDetailEntity tran : trans) {
			if (StringUtils.isNotEmpty(tran.getCsnm())) {
				if ("0".equals(tran.getCsnm())) {
					if(!csnmList.contains(tran.getPnar().substring(1, 8))){
						csnmList.add(tran.getPnar().substring(1, 8));
					}
				} else {
					if(!csnmList.contains(tran.getCsnm())){
						csnmList.add(tran.getCsnm());
					}
				}
			}
		}
		return csnmList;
	}
	/*end 20170803*/
	/**
	 * 通过importdate提取JSH交易记录，已经复核的交易数据不作重复提取，同时对已经提取的JSH数据进行清除(未复核的数据)
	 * 
	 * @param importdate
	 * @return
	 */
	private List<TranDetailEntity> getTransByImportdate(Date importdate) {
		List<Object> dlrefs = delectUnValidationDatas(importdate);
		CriteriaQuery cq = new CriteriaQuery(TranDetailEntity.class);
		cq.eq("importdate", importdate);
		cq.notEq("csnm", "0");
		cq.notlike("csnm", "9%");
		cq.eq("trat", "ACTRN");
		cq.eq("trat", "FXN");
		cq.or(cq, 3, 4);
		cq.notEq("ccy", "CNY");
		cq.isNotNull("exrate");
		cq.isNotNull("examt");
		cq.notEq("examt", new BigDecimal(0));
		cq.notEq("exrate", new BigDecimal(0));
		if (!dlrefs.isEmpty()) {
			cq.notIn("dlref", dlrefs.toArray());
		}
		cq.isNull("psmk");
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}
	
	/**
	 * 通过importdate提取JSH交易记录，已经复核的交易数据不作重复提取 同时对已经提取的JSH数据进行清除(未复核的数据)
	 * add by tf 20170803
	 * @param importdate
	 * @return
	 */
	private List<TranDetailEntity> getTransByImportdate1(Date importdate) {
		List<Object> dlrefs = delectUnValidationDatas(importdate);
		CriteriaQuery cq = new CriteriaQuery(TranDetailEntity.class);
		cq.eq("importdate", importdate);
		cq.eq("csnm", "0");
		cq.eq("trat", "ACTRN");
		cq.eq("trat", "FXN");
		cq.or(cq, 2, 3);
		cq.notEq("ccy", "CNY");
		cq.isNotNull("exrate");
		cq.isNotNull("examt");
		cq.notEq("examt", new BigDecimal(0));
		cq.notEq("exrate", new BigDecimal(0));
		if (!dlrefs.isEmpty()) {
			cq.notIn("dlref", dlrefs.toArray());
		}
		cq.isNull("psmk");
		cq.like("pnar", "#_______#");
		cq.isNotNull("acod");
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}
	/*end 20170803*/
	
    /**
     * 删除该日期下所有结售汇未复核未发送的记录，并且获取已复核的记录业务号
     * @param importdate
     * @return
     */
	private List<Object> delectUnValidationDatas(Date importdate) {

		//删除结售汇最新营业日 未复核未发送的信息
		deleteAllEntitie(findHql(JshDEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(JshEEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(JshFEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(JshGEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));

		//获取结售汇最新营业日已复核的所有业务编号
		List<Object> dlrefs = new ArrayList();
		dlrefs.addAll(getFieldValueByWhere(JshDEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(JshEEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(JshFEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(JshGEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));

		return dlrefs;

	}

	//将结售汇提取完毕的信息，进行整合
	protected String getExtractMessages4Jsh(int[] count) {
		String message = "";
		if (0 == count[1]) {
			message = "提取完成,无符合JSH条件的交易";
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("提取完成.基础信息共[").append(count[1]).append("]条.\n");
			if (count[2] > 0)
				builder.append("账户内结汇[").append(count[2]).append("]条.\n");
			if (count[3] > 0)
				builder.append("账户内购汇[").append(count[3]).append("]条.\n");

			message = builder.toString();
		}
		return message;
	}

	/**
	 * 根据分行号和交易日期设置基础的申报号码
	 * 
	 * @param bean
	 * @return
	 */
	private String getBaseRptnoByBrca(TranDetailEntity tran) {
		StringBuffer rptno = new StringBuffer();
		String brca = tran.getBrca();
		if (null != brca && StringUtils.isNotEmpty(StringUtils.trim(brca))) {
			BankinfoEntity bankinfo = findUniqueByProperty(
					BankinfoEntity.class, "brca", brca);
			if (null != bankinfo) {
				rptno.append(null == bankinfo.getBranchcode() ? "************"
						: bankinfo.getBranchcode());
			}
			if (null != tran.getImportdate()) {
				rptno.append(DateUtils.dateToStr(tran.getImportdate(),
						Constants.JSH_RPTNO_DATEFORMAT));
			}
			rptno.append("****");
		}
		return rptno.length() == 22 ? rptno.toString() : "********";
	}

	private void checkAccountInfo(TranDetailEntity tran, Object jsh)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 310000154101 606520001129 USD 20121231
		List<AccCAEntity> caList = findHql(AccCAEntity.class,
				"accountno=? and currencycode=?",
				new Object[] { tran.getMainacod(), tran.getCcy() });
		if (null == caList || caList.isEmpty()) {
			PropertyUtils.setProperty(jsh, "remark",
					"没有查询到" + tran.getMainacod() + "的开户信息，请确认后再进行上报");
		} else if (caList.size() == 1) {
			AccCAEntity ca = caList.get(0);
			if ("D".equals(ca.getActiontype())
					&& ca.getBusinessdate().compareTo(tran.getPstd()) > 0) {// 已经删除，并且Jsh业务日期大于accca业务日期时
				PropertyUtils.setProperty(
						jsh,
						"remark",
						"账户"
								+ tran.getMainacod()
								+ "已经在"
								+ DateUtils.dateToStr(ca.getBusinessdate(),
										"yyyy-MM-dd") + "时删除，请确认后再进行上报");
			}
			if (ca.getAccounttype().startsWith("2")) {// 资本项目
				tran.setTransactionremark("2C");
			}
		} else {
			for (AccCAEntity ca : caList) {
				if ("13".equals(ca.getAccountstat())
						&& ca.getBusinessdate().compareTo(tran.getPstd()) > 0) {// 已经关户，并且Jsh业务日期大于accca业务日期时{
					PropertyUtils.setProperty(
							jsh,
							"remark",
							"账户"
									+ tran.getMainacod()
									+ "已经在"
									+ DateUtils.dateToStr(ca.getBusinessdate(),
											"yyyy-MM-dd") + "时关户，请确认后再进行上报");
				}
				if (ca.getAccounttype().startsWith("2")) {// 资本项目
					tran.setTransactionremark("2C");
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T findManageData(T jshobj) {
		String reportType = jshobj.getClass().getSimpleName();
		char rpttype = reportType.charAt(3);
		T t = null;
		//根据基础信息类型 得到 管理信息类型
		String curfile = getManagerType(String.valueOf(rpttype));
		if (null != curfile && !"".equals(curfile)) {
			CriteriaQuery cq = new CriteriaQuery(getClazzByType(curfile));

			try {
				cq.eq("importdate",PropertyUtils.getProperty(jshobj, "importdate"));
				cq.eq("buscode", PropertyUtils.getProperty(jshobj, "buscode"));
				cq.eq("brca", PropertyUtils.getProperty(jshobj, "brca"));
				cq.add();
				t = getUniqueObjectByCriteriaQuery(cq);
				if (null == t) {
					t = (T) getClazzByType(curfile).newInstance();
					newInstance(jshobj, t);
				}
				PropertyUtils.setProperty(t, "rptno",PropertyUtils.getProperty(jshobj, "rptno"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找管理信息时异常", e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找管理信息时异常", e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找管理信息时异常", e);
			} catch (InstantiationException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找管理信息时异常", e);
			}
		}
		return t;
	}

	/**
	 * 根据管理信息得到基础信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findBaseData(T jshobj) {
		String reportType = jshobj.getClass().getSimpleName();
		char rpttype = reportType.charAt(3);
		// 根据管理信息类型 得到基础信息类型
		T t = null;
		String curfile = getBasedataType(String.valueOf(rpttype));
		if (null != curfile && !"".equals(curfile)) {
			CriteriaQuery cq = new CriteriaQuery(getClazzByType(curfile));

			try {
				cq.eq("importdate",
						PropertyUtils.getProperty(jshobj, "importdate"));
				cq.eq("buscode", PropertyUtils.getProperty(jshobj, "buscode"));
				cq.eq("brca", PropertyUtils.getProperty(jshobj, "brca"));
				cq.add();
				t = getUniqueObjectByCriteriaQuery(cq);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找基础信息时异常", e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找基础信息时异常", e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				LogUtil.error("JSH 查找基础信息时异常", e);
			}
		}
		return t;

	}

	public String getManagerType(String rpttype) {
		String curfile = "";
		if ("D".equals(rpttype)) {
			curfile = "F";
		} else if ("E".equals(rpttype)) {
			curfile = "G";
		}
		return curfile;
	}

	public String getBasedataType(String rpttype) {
		String curfile = "";
		if ("F".equals(rpttype)) {
			curfile = "D";
		} else if ("G".equals(rpttype)) {
			curfile = "E";
		}
		return curfile;
	}

	/**
	 * 找不到管理信息时，新创建一个实例
	 * 
	 * @param jshobj
	 * @param t
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private <T> void newInstance(T jshobj, T t) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		PropertyUtils.setProperty(t, "importdate",PropertyUtils.getProperty(jshobj, "importdate"));
		PropertyUtils.setProperty(t, "buscode",PropertyUtils.getProperty(jshobj, "buscode"));
		PropertyUtils.setProperty(t, "brca",PropertyUtils.getProperty(jshobj, "brca"));
		PropertyUtils.setProperty(t, "actiontype", "A");
		PropertyUtils.setProperty(t, "isedit", "0");
		PropertyUtils.setProperty(t, "isvalidation", "0");
		PropertyUtils.setProperty(t, "isexport", "0");
		PropertyUtils.setProperty(t, "isinsafe", "0");
	}

	@Override
	public Class getClazzByType(String curfile) {
		Class clazz = null;
		if (curfile.equalsIgnoreCase("D")) {
			clazz = JshDEntity.class;
		} else if (curfile.equalsIgnoreCase("E")) {
			clazz = JshEEntity.class;
		} else if (curfile.equalsIgnoreCase("F")) {
			clazz = JshFEntity.class;
		} else if (curfile.equalsIgnoreCase("G")) {
			clazz = JshGEntity.class;
		}
		return clazz;
	}
}