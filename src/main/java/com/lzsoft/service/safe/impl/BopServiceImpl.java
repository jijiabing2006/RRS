package com.lzsoft.service.safe.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.lzsoft.entity.safe.BopAEntity;
import com.lzsoft.entity.safe.BopBEntity;
import com.lzsoft.entity.safe.BopBaseEntity;
import com.lzsoft.entity.safe.BopCEntity;
import com.lzsoft.entity.safe.BopDEntity;
import com.lzsoft.entity.safe.BopEEntity;
import com.lzsoft.entity.safe.BopFEntity;
import com.lzsoft.entity.safe.BopGEntity;
import com.lzsoft.entity.safe.BopHEntity;
import com.lzsoft.entity.safe.BopKEntity;
import com.lzsoft.entity.safe.BopNEntity;
import com.lzsoft.entity.safe.BopPEntity;
import com.lzsoft.entity.safe.BopQEntity;
import com.lzsoft.entity.safe.BopREntity;
import com.lzsoft.entity.safe.BopSEntity;
import com.lzsoft.service.safe.BopServiceI;
import com.lzsoft.util.EntityToString;

@Service("bopService")
@Transactional
public class BopServiceImpl extends ExtractServiceImpl implements BopServiceI {

	@Override
	public String extracteBopReport() {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class);
		cq.eq("taskname", "autoExtractBop");
		cq.max("importdate");
		cq.add();

		Date maxImportdate = getUniqueResultByCriteriaQuery(cq);

		CriteriaQuery cq2 = new CriteriaQuery(TaskscheduleEntity.class);
		cq2.eq("importdate", maxImportdate);
		cq2.eq("taskname", "autoExtractBop");
		cq2.eq("executable", true);
		cq2.add();
		TaskscheduleEntity o = getUniqueObjectByCriteriaQuery(cq2);
		String mes="";
		if (null != o) {
			LogUtil.info("Start extracte"+DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)+"BopReport.........." + (new Date()));
			mes=extracteBopReport(maxImportdate, o);
			LogUtil.info(mes+".........." + (new Date()));
			o.setExecutable(false);
			saveOrUpdate(o);
		
		} else {
			LogUtil.info(DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)+"Bop 已经提取过.........." + (new Date()));
		}
		return mes;
	}

	/**
	 * 提取外汇账户在（maxImportdate)日期下的上报数据
	 * 
	 * @param maxImportdate
	 * @param task
	 * @return 1:正常返回 2:没有符合账户性质的外汇账号 3:没有交易数据,也没有开关户数据 4:有交易数据但是没有账户信息
	 *         5:没有与外汇账号关联的主体信息 6:有开户数据，但是交易数据无关联账户信息
	 */
	@Override
	public String extracteBopReport(Date maxImportdate, TaskscheduleEntity task) {

		List<TranDetailEntity> trans = getTransByImportdate(maxImportdate);
		/*modify by tf 20170802*/
		List<TranDetailEntity> trans1 = getTransByImportdate1(maxImportdate);
		System.out.println("BOP");
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
						//t.setCsnm(t.getPnar().substring(1, 8));modify by fenny 20171206
						transtmp.add(t);
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
		/*end 20170802*/
		
		int[] count = null;
		if (null != trans && !trans.isEmpty()) {
			// 需要报BOP的交易列表,得到对应的客户号列表
			List<String> csnms = getCnums1(trans);
			List<CustBaseEntity> custs = getCustinfos(maxImportdate, csnms);
			Map<String, CustBaseEntity> custMaps = new HashMap<String, CustBaseEntity>();
			for (CustBaseEntity cust : custs) {
				custMaps.put(cust.getCsnm(), cust);
			}
			List<BopBaseEntity> bopList = new LinkedList<BopBaseEntity>();
			try {
				count = setBopDatas(trans, custMaps, bopList);
				
				if (0 != count[1] && !bopList.isEmpty()) {
					batchSave(bopList);
					
				}
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				LogUtil.error("提取BOP发生异常", e);
			}
		}else {
			count = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		}
		return getExtractMessages4Bop(count);

	}

	protected String getExtractMessages4Bop(int[] count) {
		String message = "";
		if (0 == count[1]) {
			message = "提取完成,无符合BOP条件的交易";
		} else {
			StringBuilder builder = new StringBuilder();
			builder.append("提取完成.基础信息共[").append(count[1]).append("]条.\n");
			if (count[2] > 0)
				builder.append("涉外收入申报单[").append(count[2]).append("]条.\n");
			if (count[3] > 0)
				builder.append("境外汇款申请书[").append(count[3]).append("]条.\n");
			if (count[4] > 0)
				builder.append("对外付款/承兑通知书[").append(count[4]).append("]条.\n");
			if (count[5] > 0)
				builder.append("境内收入申报单[").append(count[5]).append("]条.\n");
			if (count[6] > 0)
				builder.append("境内汇款申请书[").append(count[6]).append("]条.\n");
			if (count[7] > 0)
				builder.append("境内付款/承兑通知书[").append(count[7]).append("]条.\n");
			message = builder.toString();
		}
		return message;
	}
	public int[] setBopDatas(List<TranDetailEntity> trans,
			Map<String, CustBaseEntity> custMaps, List<BopBaseEntity> bopList)
			throws Exception {
		int aNum = 0;
		int bNum = 0;
		int cNum = 0;
		int dNum = 0;
		int eNum = 0;
		int fNum = 0;
		String csnm="";
		for (TranDetailEntity tran : trans) {
			Object bop = null;
			if("0".equals(tran.getCsnm())) {
				csnm = tran.getPnar().substring(1, 8);
			} else {
				csnm = tran.getCsnm();
			}
			if (StringUtils.equals(tran.getInbop(), "2")
					&& StringUtils.equals("1", tran.getDrcr())) {
				bop = new BopAEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "A");
						//custMaps.get(tran.getCsnm()), "A");modify by fenny 20171206
				aNum++;
			} else if (StringUtils.equals(tran.getInbop(), "3")
					&& StringUtils.equals("0", tran.getDrcr())) {
				bop = new BopBEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "B");
						//custMaps.get(tran.getCsnm()), "B");modify by fenny 20171206
				bNum++;
			} else if (StringUtils.equals(tran.getInbop(), "4")
					&& StringUtils.equals("0", tran.getDrcr())) {
				bop = new BopCEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "C");
						//custMaps.get(tran.getCsnm()), "C");modify by fenny 20171206
				cNum++;
			} else if ((StringUtils.equals(tran.getInbop(), "5") )
					&& StringUtils.equals("1", tran.getDrcr())) {
				bop = new BopDEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "D");
						//custMaps.get(tran.getCsnm()), "D");modify by fenny 20171206
				dNum++;
			} else if ((StringUtils.equals(tran.getInbop(), "6"))
					&& StringUtils.equals("0", tran.getDrcr())) {
				bop = new BopEEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "E");
						//custMaps.get(tran.getCsnm()), "E");modify by fenny 20171206
				eNum++;
			} else if ((StringUtils.equals(tran.getInbop(), "7") )
					&& StringUtils.equals("0", tran.getDrcr())) {
				bop = new BopFEntity();
				convertTransTOBop(bop, tran,
						custMaps.get(csnm), "F");
						//custMaps.get(tran.getCsnm()), "F");modify by fenny 20171206
				fNum++;
			}
			if (null != bop) {
				bopList.add((BopBaseEntity) bop);
			}
		}

		return new int[] { 0, aNum + bNum + cNum + dNum + eNum + fNum, aNum,
				bNum, cNum, dNum, eNum, fNum };
	}

	/**
	 * 根据BOPINAPOSTPD为涉外收入对象BopA赋值
	 * 
	 * @param tran
	 * @param bop
	 */
	public void convertTransTOBop(Object bop, TranDetailEntity tran,
			CustBaseEntity cust, String boptype) throws Exception {
		PropertyUtils.setProperty(bop, "actiontype", "A");// 操作类型
		PropertyUtils.setProperty(bop, "actiondesc", null);// 修改/删除原因
		PropertyUtils.setProperty(bop, "rptno", getBaseRptnoByBrca(tran));// 申报号码
		setCustypeAndIdnumber(bop, cust);
		PropertyUtils.setProperty(bop, "custnm", cust.getCtnm());// 收款人名称
		setOppuserByTranAndCust(bop, tran, cust, boptype);
		PropertyUtils.setProperty(bop, "txccy", tran.getBopccy());// 收入/汇出款币种
		setBopIncomeAndExpenses(bop, tran, boptype);
		setBopAmounts(bop, tran);
		setChargeAmount(bop, tran, boptype);
		setActuAmountAndLCinfo(bop, tran, boptype);
		PropertyUtils.setProperty(bop, "method", getPaymethod(tran));// 结算方式
		PropertyUtils.setProperty(bop, "buscode", tran.getDlref());// 银行业务编号
		PropertyUtils.setProperty(bop, "brca", tran.getBrca());
		PropertyUtils.setProperty(bop, "isdel", "0");
		PropertyUtils.setProperty(bop, "isedit", "0");
		PropertyUtils.setProperty(bop, "isexport", "0");
		PropertyUtils.setProperty(bop, "ishandadd", "0");
		PropertyUtils.setProperty(bop, "isvalidation", "0");
		PropertyUtils.setProperty(bop, "isinsafe", "0");
		PropertyUtils.setProperty(bop, "importdate", tran.getImportdate());
		setTransactionRemark(tran, cust);
		if (null != tran.getTransactionremark()) {
			PropertyUtils.setProperty(bop, "remark",
					tran.getTransactionremark());
		}
	}

	/**
	 * " 1 L－信用证, 6 G－保函 ,2 C－托收 ,3 T－电汇, 5 D－票汇, 4 M－信汇, 7 O－其他
	 * 
	 * @param tran
	 * @return
	 */
	private String getPaymethod(TranDetailEntity tran) {
		if (null != tran.getPaymethodone()) {
			return tran.getPaymethodone();
//			if (StringUtils.equals("1", tran.getPaymethodone())) {
//				return "L";
//			} else if (StringUtils.equals("2", tran.getPaymethodone())) {
//				return "C";
//			} else if (StringUtils.equals("3", tran.getPaymethodone())) {
//				return "T";
//			} else if (StringUtils.equals("4", tran.getPaymethodone())) {
//				return "M";
//			} else if (StringUtils.equals("5", tran.getPaymethodone())) {
//				return "D";
//			} else if (StringUtils.equals("6", tran.getPaymethodone())) {
//				return "G";
//			} else if (StringUtils.equals("7", tran.getPaymethodone())) {
//				return "O";
//			} else {
//				return null;
//			}
		}
		return "";
	}

	/**
	 * 对外付款承兑/境内付款承兑实信用证编号等信息不为空时也进行赋值
	 * 
	 * @param bop
	 * @param tran
	 * @param boptype
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected void setActuAmountAndLCinfo(Object bop, TranDetailEntity tran,
			String boptype) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if ("C".equalsIgnoreCase(boptype) || "F".equalsIgnoreCase(boptype)) {
			if (null != tran.getLclgtenor()
					&& !tran.getLclgtenor().equals(new BigDecimal(0))) {
				PropertyUtils.setProperty(bop, "tenor", tran.getLclgtenor());// 信用证/保函期限（天）
			}
			if (null != tran.getLclgno()) {
				PropertyUtils.setProperty(bop, "lcbgno", tran.getLclgno());// 信用证/保函编号
			}
			if (null != tran.getLclgopendate()) {
				PropertyUtils.setProperty(bop, "issdate",
						tran.getLclgopendate());// 开证日期
			}

		}
	}

	/**
	 * 对扣费币种，扣费金额赋值
	 * 
	 * @param bop
	 * @param tran
	 * @param boptype
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private void setChargeAmount(Object bop, TranDetailEntity tran,
			String boptype) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if ("A".equalsIgnoreCase(boptype) || "C".equalsIgnoreCase(boptype)
				|| "D".equalsIgnoreCase(boptype)
				|| "F".equalsIgnoreCase(boptype)) {
			// 只有A有国外和国内银行扣费， CDF都是国内银行扣费（是CF是放在OUTCHARGECCY,OUTCHARGEAMT里）
			if (null != tran.getLocalBankFee()
					&& tran.getLocalBankFee().doubleValue() != 0) {
				if ("A".equalsIgnoreCase(boptype)
						|| "D".equalsIgnoreCase(boptype)) {
					PropertyUtils.setProperty(bop, "inchargeccy",
							tran.getBopccy());// 国内银行扣费币种
					PropertyUtils.setProperty(bop, "inchargeamt",
							tran.getLocalBankFee());// 国内银行扣费金额
				} else if ("C".equalsIgnoreCase(boptype)
						|| "F".equalsIgnoreCase(boptype)) {
					PropertyUtils.setProperty(bop, "outchargeccy",
							tran.getBopccy());// 银行扣费币种
					PropertyUtils.setProperty(bop, "outchargeamt",
							tran.getLocalBankFee());// 银行扣费金额
				}

			}
			if ("A".equalsIgnoreCase(boptype)) {
				if (null != tran.getForebchar()
						&& tran.getForebchar().doubleValue() != 0) {
					PropertyUtils.setProperty(bop, "outchargeccy",
							tran.getBopccy());// 国外银行扣费币种
					PropertyUtils.setProperty(bop, "outchargeamt",
							tran.getForebchar());// 国外银行扣费金额
				}
			}
		}
	}

	/**
	 * 对结/购汇 ，现汇，其它金额，币种赋值
	 * 
	 * @param bop
	 * @param tran
	 * @throws Exception
	 */

	private void setBopAmounts(Object bop, TranDetailEntity tran)
			throws Exception {
		if (!"CNY".equals(tran.getBopccy())) {
			PropertyUtils.setProperty(bop, "fcyamt", tran.getFcyamt().compareTo(new BigDecimal(0))==0?null:tran.getFcyamt());
			PropertyUtils.setProperty(bop, "fcyacc", tran.getMainacod());
			if(tran.getFcyamt().compareTo(new BigDecimal(0))==0){//现汇金额大于0时，不显示结购汇金额
				PropertyUtils.setProperty(bop, "lcyamt", tran.getExamt().compareTo(new BigDecimal(0))==0?null: tran.getExamt());
				PropertyUtils.setProperty(bop, "lcyacc", tran.getRmbaccount());
				PropertyUtils.setProperty(bop, "exrate", tran.getExrate().compareTo(new BigDecimal(0))==0?null:tran.getExrate() );
				PropertyUtils.setProperty(bop, "othamt", null);
				PropertyUtils.setProperty(bop, "othacc", null);
			}
			
		} else {
			// 如果bopccy为人民币，（说明是跨境人民币业务），对应其它金额字段
			PropertyUtils.setProperty(bop, "fcyacc", null);
			PropertyUtils.setProperty(bop, "fcyamt", null);
			PropertyUtils.setProperty(bop, "lcyamt", null);
			PropertyUtils.setProperty(bop, "lcyacc", null);
			PropertyUtils.setProperty(bop, "exrate", null);
			PropertyUtils.setProperty(bop, "othamt", tran.getOthamt().compareTo(new BigDecimal(0))==0?null:tran.getOthamt());
			PropertyUtils.setProperty(bop, "othacc", tran.getOthaccount());
		}
	}

	/**
	 * 根据BOPtype 设置txamt,bopamt是指银行实际收入或者汇出的金额，psta是客户账户收入或者支出的金额
	 * 当BOPA,BOPD时,收入金额就是bopamt,实际入客户账金额psta小于等于bopamt(可能有扣费)
	 * bopamt>=psta+localbankfee+forebchar
	 * BOPB,BOPE时,汇款金额就是bopamt,与客户账户支出金额也相等(汇款没有扣费) bopamt=psta
	 * BOPC,BOPF时,实际汇出的金额是bopamt,客户要求汇出的金额与账户中支出的金额相等是psta
	 * bopamt=psta-localbankfee
	 * 
	 * @param bop
	 * @param tran
	 * @param boptype
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected void setBopIncomeAndExpenses(Object bop, TranDetailEntity tran,
			String boptype) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		if (null != tran.getBopamt() && 0 != tran.getBopamt().doubleValue()) {

			if ("A".equals(boptype) || "D".equals(boptype)
					|| "B".equals(boptype) || "E".equals(boptype)) {// A,B,D,E的收入/汇出金额与实际收入/支出金额(bopamt)一致，（等于现汇金额与费用之和)
				PropertyUtils.setProperty(bop, "txamt", tran.getBopamt());
			} else if ("C".equals(boptype) || "F".equals(boptype)) {// C,F的支出金额（psta等于实际汇出金额与费用之和)
				PropertyUtils.setProperty(bop, "txamt", tran.getPsta());
				PropertyUtils.setProperty(bop, "txccy", tran.getCcy());
				PropertyUtils.setProperty(bop, "actuccy", tran.getBopccy());
				PropertyUtils.setProperty(bop, "actuamt", tran.getBopamt());
			}
		}
	}

	/**
	 * 设置交易对手名称（BOPA,BOPB,BOPC）添加 (JW)和(JN)标识
	 * 
	 * @param bop
	 * @param tran
	 * @param cust
	 * @param boptype
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	protected void setOppuserByTranAndCust(Object bop,
			TranDetailEntity tran, CustBaseEntity cust, String boptype)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if ("A".equals(boptype) || "B".equals(boptype) || "C".equals(boptype)) {
			String opname = tran.getOpname();
			if (!StringUtils.startsWith(tran.getCounpartyAeracode(), "CHN")) {
				opname = "(JW)" + opname;
			} else if (StringUtils.startsWith(tran.getCounpartyAeracode(),
					"CHN")
					&& cust.getCtnt().equals("CHN")
					&& !tran.getCounpartyCountry().equals("CHN")) {
				opname = "(JN)" + opname;
			}
			// Smith add 2017新需求，如果是对私业务，付款人名称后增加付款人账号信息，以名称后加“[acc:账号]”表示。
			// 对于速汇业务和托收等没有对应账号的业务，账号部分填写“000000”（六个零）。
			if ("A".equals(boptype) || "B".equals(boptype)) {
				if (StringUtils.equals("A1", cust.getSource())) {
					if(StringUtils.isBlank(tran.getOpaccount())) {
						opname=opname+"[acc:000000]";
					} else {
						opname=opname+"[acc:"+tran.getOpaccount()+"]";
					}
				}
			}
			PropertyUtils.setProperty(bop, "oppuser", opname);// 付款人名称
		} else {
			PropertyUtils.setProperty(bop, "oppuser", tran.getOpname()!=null?tran.getOpname():"此项不能为空");// 付款人名称
		}
		if (null == PropertyUtils.getProperty(bop, "oppuser")
				|| "".equals(PropertyUtils.getProperty(bop, "oppuser"))) {
			PropertyUtils.setProperty(bop, "oppuser", tran.getOpname()!=null?tran.getOpname():"此项不能为空");// 付款人名称
		}
		if ("E".equals(boptype)) {
			PropertyUtils.setProperty(bop, "oppacc", tran.getOpaccount()!=null?tran.getOpaccount():"此项不能为空");// 收款人账号
		}
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
						Constants.BOP_RPTNO_DATEFORMAT));
			}
			rptno.append("****");
		}
		return rptno.length() == 22 ? rptno.toString() : "********";
	}

	/**
	 * 得到所有BOP交易的客户号
	 * 
	 * @param trans
	 */
	private List<String> getCnums(List<TranDetailEntity> trans) {
		List<String> csnmList = new ArrayList<String>();
		for (TranDetailEntity tran : trans) {
			if (StringUtils.isNotEmpty(tran.getCsnm())) {
					if(!csnmList.contains(tran.getCsnm())){
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
	/*end 20170802*/

	/**
	 * 通过importdate提取BOP交易记录，已经复核的交易数据不作重复提取 同时对已经提取的BOP数据进行清除(未复核的数据)
	 * 
	 * @param importdate
	 * @return
	 */
	protected List<TranDetailEntity> getTransByImportdate(Date importdate) {
		List<Object> dlrefs = delectUnValidationDatas(importdate);
//所有用客户账号做的交易数据
		CriteriaQuery cq = new CriteriaQuery(TranDetailEntity.class);
		cq.eq("importdate", importdate);
		cq.notEq("inbop", "");
		cq.notEq("inbop", "1");
		cq.notEq("csnm", "0");
		cq.isNull("psmk");
		cq.isNotNull("inbop");
		cq.isNotNull("bopccy");
		cq.isNotNull("bopamt");
		cq.notlike("csnm", "9%");
		cq.notlike("trat", "%Y");
		cq.notEq("bopamt", new BigDecimal(0));
		if (!dlrefs.isEmpty()) {
			cq.notIn("dlref", dlrefs.toArray());
		}
		
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}
	
	/**
	 * 通过importdate提取BOP交易记录，已经复核的交易数据不作重复提取 同时对已经提取的BOP数据进行清除(未复核的数据)
	 * 
	 * @param importdate
	 * @return
	 */
	protected List<TranDetailEntity> getTransByImportdate1(Date importdate) {
		List<Object> dlrefs = delectUnValidationDatas(importdate);
		
		/*CSNM為0且PNAR中兩個#之間部分为客户号，且ACOD第一位為字母，第四位到第八位为14211/14217/14218*/
		CriteriaQuery cq = new CriteriaQuery(TranDetailEntity.class);
		cq.eq("importdate", importdate);
		cq.notEq("inbop", "");
		cq.notEq("inbop", "1");
		cq.eq("csnm", "0");
		cq.like("pnar", "#_______#");
		//cq.like("acod", "^[A-Z][A-Z][A-Z]1421[178]");
		cq.isNotNull("acod");
		cq.isNull("psmk");
		cq.isNotNull("inbop");
		cq.isNotNull("bopccy");
		cq.isNotNull("bopamt");
		cq.notlike("trat", "%Y");
		cq.notEq("bopamt", new BigDecimal(0));
		if (!dlrefs.isEmpty()) {
			cq.notIn("dlref", dlrefs.toArray());
		}
		
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	/**
	 * 删除未复核的数据（已经复核的记录不可以通过提取按键删除)
	 * 
	 * @param importdate
	 * @return
	 */
	protected List<Object> delectUnValidationDatas(Date importdate) {
		deleteAllEntitie(findHql(BopAEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(BopBEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(BopCEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(BopDEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(BopEEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));
		deleteAllEntitie(findHql(BopFEntity.class,
				" isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", importdate }));

		List<Object> dlrefs = new ArrayList();
		// dlrefs.addAll(queryFieldValues(BopAEntity.class,
		// new String[]{"buscode"} ,
		// "importdate=? and isvalidation='1'",
		// new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopAEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopBEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopCEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopDEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopEEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		dlrefs.addAll(getFieldValueByWhere(BopFEntity.class, "buscode",
				"importdate=? and isvalidation='1'",
				new Object[] { importdate }));
		// dlrefs.addAll(getFieldValueByWhere(TranDetailEntity.class,
		// "dlref" ,
		// "importdate=? and acod='111'",
		// new Object[] { importdate }));

		return dlrefs;
	}
	/**
	 * 49号文增加的校验
	 * 
	 * @选 A/B/C者，且交易对手为居民，要SHOW出”请确认，涉外收支，但交易对手为居民，是否要申报“
	 * @选 D/E/F/G/H/I者，但为跨境，要SHOW出”请确认，境内收支，但交易去向为境外，是否输错字段“
	 * @选 D/E/F者，且交易对手不为“保税区(1402/1432)，要SHOW出”请确认，货物贸易，交易对手不为保税区加工区等特殊，是否输错字段“
	 * @选 D/E/F/G/H/I者，且本行客户为非居民，要SHOW出”请确认，境内收支，但交易对手为非居民，是否要申报“
	 * @param tran
	 * @param cust
	 */
	protected void setTransactionRemark(TranDetailEntity tran, CustBaseEntity cust) {
		if ((StringUtils.equals(tran.getInbop(), "2")
				|| StringUtils.equals(tran.getInbop(), "3") || StringUtils
					.equals(tran.getInbop(), "4"))
				&& StringUtils.equals(tran.getCounpartyCountry(), "CHN")) {
			tran.setTransactionremark("请确认，涉外收支，但交易对手为居民，是否要申报");
		} else if ((StringUtils.equals(tran.getInbop(), "5")
				|| StringUtils.equals(tran.getInbop(), "6")
				|| StringUtils.equals(tran.getInbop(), "7"))
				&& (!StringUtils.startsWith(tran.getCounpartyAeracode(), "CHN"))) {
			tran.setTransactionremark("请确认，境内收支，但交易去向为境外或者为空，是否输错字段");
		} else if ((StringUtils.equals(tran.getInbop(), "5")
				|| StringUtils.equals(tran.getInbop(), "6") || StringUtils
					.equals(tran.getInbop(), "7"))
				&& (!StringUtils.startsWith(tran.getCounpartyAeracode(), "Z0"))) {
			tran.setTransactionremark("请确认，货物贸易，交易对手不为保税区加工区等特殊，是否输错字段");
		} else if ((StringUtils.equals(tran.getInbop(), "5")
				|| StringUtils.equals(tran.getInbop(), "6")
				|| StringUtils.equals(tran.getInbop(), "7"))
				&& !StringUtils.equals(cust.getCtnt(), "CHN")) {
			tran.setTransactionremark("请确认，境内收支，但交易对手为非居民，是否要申报");
		} else if (StringUtils.equals(tran.getInbop(), "2")
				&& StringUtils.equals(tran.getDrcr(), "5")) {
			tran.setTransactionremark("请确认，涉外收入，但收支为DR，是否要申报");
		} else if (StringUtils.equals(tran.getInbop(), "5")
				&& StringUtils.equals(tran.getDrcr(), "0")) {
			tran.setTransactionremark("请确认，境内收入，但收支为DR，是否要申报");
		} else if ((StringUtils.equals(tran.getInbop(), "3"))
				&& StringUtils.equals(tran.getDrcr(), "1")) {
			tran.setTransactionremark("请确认，境外汇款，但收支为CR，是否要申报");
		} else if ((StringUtils.equals(tran.getInbop(), "4"))
				&& StringUtils.equals(tran.getDrcr(), "1")) {
			tran.setTransactionremark("请确认，对外付款承兑，但收支为CR，是否要申报");
		} else if ((StringUtils.equals(tran.getInbop(), "6"))
				&& StringUtils.equals(tran.getDrcr(), "1")) {
			tran.setTransactionremark("请确认，境内汇款，但收支为CR，是否要申报");
		} else if ((StringUtils.equals(tran.getInbop(), "7"))
				&& StringUtils.equals(tran.getDrcr(), "1")) {
			tran.setTransactionremark("请确认，境内付款承兑汇款，但收支为CR，是否要申报");
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findManageData(T bopobj) {
		String reportType = bopobj.getClass().getSimpleName();
		char rpttype = reportType.charAt(3);
		// 根据基础信息类型 得到 管理信息类型
		T t = null;
		String curfile  = getManagerType(String.valueOf(rpttype));
		if (null != curfile && !"".equals(curfile)) {
			CriteriaQuery cq = new CriteriaQuery(getClazzByType(curfile));

			try {
				cq.eq("importdate",
						PropertyUtils.getProperty(bopobj, "importdate"));
				cq.eq("buscode", PropertyUtils.getProperty(bopobj, "buscode"));
				cq.eq("brca", PropertyUtils.getProperty(bopobj, "brca"));
				cq.add();
				t = getUniqueObjectByCriteriaQuery(cq);
				if (null == t) {
					t = (T) getClazzByType(curfile).newInstance();
					newInstance(bopobj, t);
				}
				PropertyUtils.setProperty(t, "rptno",
						PropertyUtils.getProperty(bopobj, "rptno"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (InstantiationException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			}
		}
		return t;

	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findDeclareData(T bopobj) {
		String reportType = bopobj.getClass().getSimpleName();
		char rpttype = reportType.charAt(3);
		// 根据基础信息类型 得到 申报信息类型
		T t = null;
		String curfile  = getDeclareType(String.valueOf(rpttype));
		if (null != curfile && !"".equals(curfile)) {
			CriteriaQuery cq = new CriteriaQuery(getClazzByType(curfile));
			
			try {
				cq.eq("importdate",
						PropertyUtils.getProperty(bopobj, "importdate"));
				cq.eq("buscode", PropertyUtils.getProperty(bopobj, "buscode"));
				cq.eq("brca", PropertyUtils.getProperty(bopobj, "brca"));
				cq.add();
				t = getUniqueObjectByCriteriaQuery(cq);
				if (null == t) {
					t = (T) getClazzByType(curfile).newInstance();
					newInstance(bopobj, t);
				}
				PropertyUtils.setProperty(t, "rptno",
						PropertyUtils.getProperty(bopobj, "rptno"));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			} catch (InstantiationException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找管理信息时异常", e);
			}
		}
		return t;
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> T findBaseData(T bopobj) {
		String reportType = bopobj.getClass().getSimpleName();
		char rpttype = reportType.charAt(3);
		// 根据管理信息类型 得到基础信息类型
		T t = null;
		String curfile  = getBasedataType(String.valueOf(rpttype));
		if (null != curfile && !"".equals(curfile)) {
			CriteriaQuery cq = new CriteriaQuery(getClazzByType(curfile));
			
			try {
				cq.eq("importdate",
						PropertyUtils.getProperty(bopobj, "importdate"));
				cq.eq("buscode", PropertyUtils.getProperty(bopobj, "buscode"));
				cq.eq("brca", PropertyUtils.getProperty(bopobj, "brca"));
				cq.add();
				t = getUniqueObjectByCriteriaQuery(cq);

			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找基础信息时异常", e);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找基础信息时异常", e);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				LogUtil.error("BOP 查找基础信息时异常", e);
			} 
		}
		return t;
		
	}
	public String getManagerType(String rpttype) {
		String curfile="";
		if ("B".equals(rpttype)||"H".equals( rpttype)) {
			curfile = "N";
		} else if ("C".equals( rpttype)||"K".equals( rpttype)) {
			curfile = "P";
		} else if ("D".equals( rpttype)) {
			curfile = "R";
		} else if ("E".equals( rpttype)) {
			curfile = "Q";
		} else if ("F".equals( rpttype)) {
			curfile = "S";
		}
		return curfile;
	}
	public String getDeclareType(String rpttype) {
		String curfile="";
		if ("A".equals(rpttype)) {
			curfile = "G";
		} else if ("B".equals( rpttype)||"N".equals( rpttype)) {
			curfile = "H";
		} else if ("C".equals( rpttype)||"P".equals( rpttype)) {
			curfile = "K";
		}
		return curfile;
	}
	public String getBasedataType(String rpttype) {
		String curfile="";
		if ("G".equals(rpttype)) {
 			curfile = "A";
		} else if ("H".equals( rpttype)||"N".equals( rpttype)) {
			curfile = "B";
		} else if ("K".equals( rpttype)||"P".equals( rpttype)) {
			curfile = "C";
		} else if ("R".equals( rpttype)) {
			curfile = "D";
		} else if ("Q".equals( rpttype)) {
			curfile = "E";
		} else if ("S".equals( rpttype)) {
			curfile = "F";
		}
		return curfile;
	}
	public Class getClazzByType(String curfile) {
		Class clazz = null;
		if (curfile.equalsIgnoreCase("A")) {
			clazz = BopAEntity.class;
		} else if (curfile.equalsIgnoreCase("B")) {
			clazz = BopBEntity.class;
		} else if (curfile.equalsIgnoreCase("C")) {
			clazz = BopCEntity.class;
		} else if (curfile.equalsIgnoreCase("D")) {
			clazz = BopDEntity.class;
		} else if (curfile.equalsIgnoreCase("E")) {
			clazz = BopEEntity.class;
		} else if (curfile.equalsIgnoreCase("F")) {
			clazz = BopFEntity.class;
		} else if (curfile.equalsIgnoreCase("G")) {
			clazz = BopGEntity.class;
		} else if (curfile.equalsIgnoreCase("H")) {
			clazz = BopHEntity.class;
		} else if (curfile.equalsIgnoreCase("K")) {
			clazz = BopKEntity.class;
		} else if (curfile.equalsIgnoreCase("N")) {
			clazz = BopNEntity.class;
		} else if (curfile.equalsIgnoreCase("P")) {
			clazz = BopPEntity.class;
		} else if (curfile.equalsIgnoreCase("Q")) {
			clazz = BopQEntity.class;
		} else if (curfile.equalsIgnoreCase("R")) {
			clazz = BopREntity.class;
		} else if (curfile.equalsIgnoreCase("S")) {
			clazz = BopSEntity.class;
		}
		return clazz;
	}
	/**
	 * 找不到申报管理信息时，新创建一个实例
	 * 
	 * @param bopobj
	 * @param t
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private <T> void newInstance(T bopobj, T t) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		PropertyUtils.setProperty(t, "importdate",
				PropertyUtils.getProperty(bopobj, "importdate"));
		PropertyUtils.setProperty(t, "buscode",
				PropertyUtils.getProperty(bopobj, "buscode"));
		PropertyUtils.setProperty(t, "brca",
				PropertyUtils.getProperty(bopobj, "brca"));
		PropertyUtils.setProperty(t, "actiontype", "A");
		PropertyUtils.setProperty(t, "isedit", "0");
		PropertyUtils.setProperty(t, "isvalidation", "0");
		PropertyUtils.setProperty(t, "isexport", "0");
		PropertyUtils.setProperty(t, "isinsafe", "0");
	}
}