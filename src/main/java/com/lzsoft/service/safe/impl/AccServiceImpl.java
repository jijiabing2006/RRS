package com.lzsoft.service.safe.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MapUtil;
import org.jeecgframework.core.util.PropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.AccCustEntity;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.common.TranDetailEntity;
import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.entity.safe.AccCBEntity;
import com.lzsoft.service.safe.AccServiceI;

@Service("accService")
@Transactional 
public class AccServiceImpl extends ExtractServiceImpl implements AccServiceI {

	@Override
	public String extracteAccReport() {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class);
		cq.eq("taskname", "autoExtractAcc");
		cq.max("importdate");
		cq.add();

		//获取营业日
		Date maxImportdate = getUniqueResultByCriteriaQuery(cq);

		CriteriaQuery cq2 = new CriteriaQuery(TaskscheduleEntity.class);
		cq2.eq("importdate", maxImportdate);
		cq2.eq("taskname", "autoExtractAcc");
		cq2.eq("executable", true);
		cq2.add();
		TaskscheduleEntity o = getUniqueObjectByCriteriaQuery(cq2);
		String mes = "";
		if (null != o) {
			LogUtil.info("Start extracte"
					+ DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "AccReport.........." + (new Date()));
			mes = extracteAccReport(maxImportdate, o);
			LogUtil.info(mes + ".........." + (new Date()));
			o.setExecutable(false);
			saveOrUpdate(o);

		} else {
			LogUtil.info(DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "Acc 已经提取过.........." + (new Date()));
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
	public String extracteAccReport(Date maxImportdate, TaskscheduleEntity task) {
		delectUnValidationDatas(maxImportdate);
		// 提取所有的银行信息
		List<BankinfoEntity> bankinfos = getAllBankinfos();

		// 提取maxImportdate下，符合acc账户性质范围的所有账户信息
		List<AccCustEntity> accts = getAcctsByMaximportDate(maxImportdate);
		if (null == accts || accts.isEmpty()) {
			return "提取" + DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "没有符合账户性质的外汇账号";
		}
		Map<String, AccCustEntity> acctMaps = new HashMap<String, AccCustEntity>();// accts转成Map(账号)
		List<String> csnms = new ArrayList<String>();// 有外汇账号的客户编号
		// 提取开关户（简单处理，只有新建开户（变更未处理），删除未处理）
		// 关户 （简单处理，有关户日期 （余额要为0））
		List<AccCustEntity> accOpens = null;
		try {
			accOpens = getAccOpens(maxImportdate, accts, acctMaps, csnms);
		} catch (Exception e) {
			// e.printStackTrace();
			return "提取 开关户发生错误 extracteAccReport-> getAccOpens()";
		}
		// 提取收支余变动(交易数据少，直接提取所有）。根据ABOQ以往经验，交易数据有重复需要过滤。
		List<TranDetailEntity> trans = getTrans(maxImportdate);
		if ((null == trans || trans.isEmpty())
				&& (null == accOpens || accOpens.isEmpty())) {
			return "提取" + DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "没有交易数据,也没有开关户数据";
		}
		List<AccCustEntity> accMovements = null;
		// 获取有收支变动账号的账户信息（目前是所有交易的）
		if (!trans.isEmpty()) {
			accMovements = getAccMovements(trans, acctMaps);
			if (null == accMovements || accMovements.isEmpty()) {
				if (accOpens.isEmpty()) {// 没有开户
					return "提取"
							+ DateUtils.date2Str(maxImportdate,
									DateUtils.date_sdf) + "有交易数据但是没有账户信息";
				} else {
					// return 6;
				}

			}
		}
		// 提取有外汇账户的客户信息
		List<CustBaseEntity> custs = getCustinfos(maxImportdate, csnms);
		if (null == custs || custs.isEmpty()) {
			return "提取" + DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
					+ "没有与外汇账号关联的主体信息";
		}

		Date lastImportDate = getLastImportDate(maxImportdate);
		List<AccCustEntity> lastAccBalances = null;
		List<CustBaseEntity> lastCustomers = null;
		if (null != lastImportDate) {
			// 提取上一营业日账户余额
			lastAccBalances = getLastAccBalances(lastImportDate, csnms);

			// 提取上一营业日的客户信息
			lastCustomers = getCustinfos(lastImportDate, csnms);
		}
//		if (null != lastAccBalances && null != lastCustomers) {
//			getAccChanges(accOpens, accts, acctMaps, custs, lastAccBalances,
//					lastCustomers);
//			// 将本日账户状态未做变更的，与上一营业日的账户状态进行同步(之前已经上报开，关户的账户)
//			synchronizationacct(acctMaps, accOpens, lastAccBalances);
//		}
		Map<String, CustBaseEntity> custMap = getcustsMap(custs);
		List<AccCAEntity> accCAs = null;
		List<AccCBEntity> accCBs = null;

		if (null != accOpens) {
			accCAs = getAccCAs(accOpens, custMap, bankinfos, accCAs);
		}
		if (null != accMovements && !accMovements.isEmpty()) {
			accCBs = getAccCBs(trans, lastAccBalances, accMovements, custMap,
					bankinfos, accCBs);
		}
		int opNum = 0;
		int mbNum = 0;

		if (null != accCAs && !accCAs.isEmpty()) {
			removeExistOpens(maxImportdate, accCAs);
			batchSave(accCAs);
			opNum = accCAs.size();
		}
		if (null != accCBs && !accCBs.isEmpty()) {
			removeExistAccountMovements(maxImportdate, accCBs);
			batchSave(accCBs);
			mbNum = accCBs.size();
		}
		if (null != task && opNum + mbNum > 0) {

			task.setCounts(opNum + mbNum);
			task.setExecutable(false);
			// updateEntitie(task);
		}
		return "提取" + DateUtils.date2Str(maxImportdate, DateUtils.date_sdf)
				+ "开户：" + opNum + "笔。\n收支交易：" + mbNum + "笔。";

	}

	/**
	 * 删除提取日期未复核的所有记录
	 * 
	 * @param maxImportdate
	 */
	private void delectUnValidationDatas(Date maxImportdate) {
		deleteAllEntitie(findHql(
				" from AccCAEntity where isinsafe=? and isvalidation=? and isexport=? and importdate=? ",
				new Object[] { "0", "0", "0", maxImportdate }));
		deleteAllEntitie(findHql(
				" from AccCBEntity where isinsafe=? and isvalidation=? and isexport=? and importdate=?",
				new Object[] { "0", "0", "0", maxImportdate }));

	}

	/**
	 * 将开，关户日期与导入日期相同的账户放到accOpens 将客户编号放到csnms中（用于查询客户）
	 * 以brca,acod,ccy为key,acctcust为value放到maps中（用于交易与账户关联）
	 * 
	 * @param maxImportdate
	 * @param acccusts
	 * @param accOpens
	 * @param maps
	 * @param csnms
	 * @return accOpens
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	private List<AccCustEntity> getAccOpens(Date maxImportdate,
			List<AccCustEntity> acccusts, Map<String, AccCustEntity> maps,
			List<String> csnms) throws Exception {
		List<AccCustEntity> accOpens = new ArrayList<AccCustEntity>();
		for (AccCustEntity acccust : acccusts) {
			if (null != acccust.getAccountstat()
					&& !"".equals(acccust.getAccountstat())) {// 账户状态不为空（开，变，关）
				if ("14".equals(acccust.getAccountstat())) {
					AccCustEntity temp = new AccCustEntity();
					PropertyUtils.copyProperties(temp, acccust);
					temp.setAccountstat("13");
					accOpens.add(temp);// 既有开，也有关时（虚拟一笔关）
					acccust.setAccountstat("11");
					accOpens.add(acccust);
				}
				if (("11".equals(acccust.getAccountstat()) && maxImportdate
						.compareTo(acccust.getOpendate()) == 0)
						|| ("13".equals(acccust.getAccountstat()) && maxImportdate
								.compareTo(acccust.getClosedate()) == 0)) {
					// LogUtil.generateLog(logPath,
					// "AccountStat=1" + ifxacct.getAccountstat() + ":"
					// + ifxacct.getMainacod() + ifxacct.getCcy());
					accOpens.add(acccust);

				}

			}
			if(!csnms.contains(acccust.getCsnm())){
				csnms.add(acccust.getCsnm());
			}
			maps.put(acccust.getBrca() + acccust.getMainacod() + acccust.getCcy(),
					acccust);

		}

		return accOpens;
	}

	/**
	 * 提取MaxImportdat下所有的交易数据， 将与交易有关的账户信息放到accMovements中
	 * 
	 * @param maxImportdate
	 * @param maps
	 * @return
	 */
	private List<AccCustEntity> getAccMovements(List<TranDetailEntity> trans,
			Map<String, AccCustEntity> maps) {
		List<AccCustEntity> accMovements = new ArrayList<AccCustEntity>();
		StringBuffer key = new StringBuffer();
		for (TranDetailEntity tran : trans) {
			if (!key.toString().contains(
					tran.getBrca() + tran.getMainacod() + tran.getCcy())) {
				AccCustEntity tacct = maps.get(tran.getBrca() + tran.getMainacod()
						+ tran.getCcy());
				if (null != tacct) {
					accMovements.add(tacct);
				}
				key.append(tran.getBrca() + tran.getMainacod() + tran.getCcy()
						+ ",");
			}

		}
		return accMovements;
	}

	/**
	 * 判断是否有外汇账号变更,
	 * 
	 * @param accOpens
	 * @param accts
	 * @param acctmaps
	 * @param custs
	 * @param lastAccBalances
	 * @param lastCustomers
	 */

	private void getAccChanges(List<AccCustEntity> accOpens,
			List<AccCustEntity> accts, Map<String, AccCustEntity> acctmaps,
			List<CustBaseEntity> custs, List<AccCustEntity> lastAccBalances,
			List<CustBaseEntity> lastCustomers) {
		List<AccCustEntity> accchanges = new ArrayList<AccCustEntity>();

		Map<String, CustBaseEntity> custmaps = new HashMap<String, CustBaseEntity>();
		for (CustBaseEntity cust : custs) {
			custmaps.put(cust.getBrca() + cust.getCsnm(), cust);
		}

		getAccountChangedList(acctmaps, lastAccBalances, accchanges);
		getCustomerChangedAccountList(accts, lastCustomers, accchanges,
				custmaps);

		setAccountStat(accOpens, accchanges);

	}

	/**
	 * 得到账户信息有变更的账户List
	 * 
	 * @param acctmaps
	 * @param lastAccBalances
	 * @param accchange
	 */
	private void getAccountChangedList(Map<String, AccCustEntity> acctmaps,
			List<AccCustEntity> lastAccBalances, List<AccCustEntity> accchange) {
		for (AccCustEntity lastacct : lastAccBalances) {
			AccCustEntity acct = acctmaps.get(lastacct.getBrca()
					+ lastacct.getMainacod() + lastacct.getCcy());
			if (null != acct) {

				BigDecimal acctAccountlimit = null != acct.getAccountlimit() ? acct
						.getAccountlimit() : new BigDecimal(0);
				BigDecimal lastAcctAccountlimit = null != lastacct
						.getAccountlimit() ? lastacct.getAccountlimit()
						: new BigDecimal(0);

				// 比较 账户性质代码 账户类别 外汇局批件号/备案表号/业务编号 限额类型 限额
				if (!StringUtils.equals(acct.getAccounttype(),
						lastacct.getAccounttype())
						|| !StringUtils.equals(acct.getAccountcata(),
								lastacct.getAccountcata())
						|| !StringUtils.equals(acct.getFileNumber(),
								lastacct.getFileNumber())
						|| !StringUtils.equals(acct.getLimittype(),
								lastacct.getLimittype())
						|| acctAccountlimit.compareTo(lastAcctAccountlimit) != 0) {
					acct.setAccountstat("12");
					accchange.add(acct);
				}
			}
		}
	}

	/**
	 * 得到账户主体信息有变更的账户List
	 * 
	 * @param accts
	 * @param lastCustomers
	 * @param accchange
	 * @param custmaps
	 * @return
	 */
	private void getCustomerChangedAccountList(List<AccCustEntity> accts,
			List<CustBaseEntity> lastCustomers, List<AccCustEntity> accchanges,
			Map<String, CustBaseEntity> custmaps) {
		for (CustBaseEntity lastcust : lastCustomers) {
			CustBaseEntity cust = custmaps.get(lastcust.getBrca()
					+ lastcust.getCsnm());
			// 开户主体代码 开户主体名称
			if (!StringUtils.equals(cust.getEncode(), lastcust.getEncode())
					|| !StringUtils.equals(cust.getCtnm(), lastcust.getCtnm())) {
				for (AccCustEntity acct : accts) {
					if (acct.getBrca().equals(cust.getBrca())
							&& acct.getCsnm().equals(cust.getCsnm())) {
						acct.setAccountstat("12");
						accchanges.add(acct);
					}

				}
			}
		}
	}

	/**
	 * 设置账户变更（12变更，15有开户及变更，16有变更及关户
	 * 
	 * @param accOpens
	 * @param accchanges
	 */
	private void setAccountStat(List<AccCustEntity> accOpens,
			List<AccCustEntity> accchanges) {
		if (!accchanges.isEmpty()) {
			for (AccCustEntity accChange : accchanges) {
				if ("11".equals(accChange.getAccountstat())) {
					// accChange.setAccountstat("15");//有开户，变更(暂时不会有此情况不处理)
				} else if ("13".equals(accChange.getAccountstat())) {
					// accChange.setAccountstat("16");//有变更，关户(暂时不会有此情况不处理)
				} else {
					accChange.setAccountstat("12");
				}
				accOpens.add(accChange);
			}

		}
	}

	/**
	 * 同步账户状态到账户 信息表
	 * 
	 * @param acctmaps
	 * @param accOpens
	 * @param lastAccBalances
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void synchronizationacct(Map<String, AccCustEntity> acctmaps,
			List<AccCustEntity> accOpens, List<AccCustEntity> lastAccBalances) {
		AccCustEntity acct = null;
		try {
			for (AccCustEntity lastacct : lastAccBalances) {
				acct = acctmaps.get(lastacct.getBrca() + lastacct.getMainacod()
						+ lastacct.getCcy());
				if (null != acct && null != lastacct.getAccountstat()
						&& null == acct.getAccountstat()) {

					BeanUtils.copyProperty(acct, "accountstat",
							lastacct.getAccountstat());

				}
			}
			for (AccCustEntity accOpen : accOpens) {
				acct = acctmaps.get(accOpen.getBrca() + accOpen.getMainacod()
						+ accOpen.getCcy());
				if (null != acct && null != accOpen.getAccountstat()) {
					BeanUtils.copyProperty(acct, "accountstat",
							accOpen.getAccountstat());
				}
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set<String> keys = acctmaps.keySet();
		for (String obj : keys) {
			updateEntitie(acctmaps.get(obj));

		}
	}

	/**
	 * 将List转成Map，csnm作为Key,
	 * 
	 * @param custs
	 * @return
	 */
	private Map<String, CustBaseEntity> getcustsMap(List<CustBaseEntity> custs) {
		Map<String, CustBaseEntity> custMap = new HashMap<String, CustBaseEntity>();
		for (CustBaseEntity cust : custs) {
			custMap.put(cust.getCsnm(), cust);
		}
		return custMap;
	}

	/**
	 * 
	 * @param maxImportdate
	 * @return
	 */
	private List<AccCustEntity> getAcctsByMaximportDate(Date maxImportdate) {
		Map<String, String> accMap = PropertiesUtil
				.getMapByPorpFile(Constants.ACCOUNTTYPEPATH);
		List<String> accs = MapUtil.getKeyList(accMap);

		CriteriaQuery cq = new CriteriaQuery(AccCustEntity.class);
		cq.eq("importdate", maxImportdate);
		cq.in("accounttype", accs.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	private List<TranDetailEntity> getTrans(Date maxImportdate) {
		// " importdate=? and ccy!=? and (apostpdid not like ? and  apostpdid not like ? and apostpdid not like ?)",
		// new Object[] { maxImportdate, "CNY", "EX%", "IM%",
		// "LN%" });
		CriteriaQuery cq = new CriteriaQuery(TranDetailEntity.class);
		cq.eq("importdate", maxImportdate);
		cq.notEq("csnm", "0");
		cq.notlike("csnm", "9%");
		cq.isNull("psmk");
		// hql((this_.0 like ? and this_.1 like ?) or this_.2 like ?)
		// 表示法cq.add(cq.or(cq.and(cq, 0, 1), cq, 2))-
		// cq.add(cq.and(cq.and(cq.or(cq.or(cq, 2, 3), cq, 4), cq, 0), cq, 1));
		cq.add();
		return getListByCriteriaQuery(cq, false);

	}

	/**
	 * 获取上一营业日期
	 * 
	 * @param maxImportdate
	 * @return
	 */
	private Date getLastImportDate(Date maxImportdate) {

		CriteriaQuery cq = new CriteriaQuery(AccCustEntity.class);
		cq.lt("importdate", maxImportdate);
		cq.isNotNull("accounttype");
		cq.max("importdate");
		cq.add();
		Date nearDate = getUniqueResultByCriteriaQuery(cq);
		return nearDate;
	}

	/**
	 * 提取上一营业日账户余额(客户范围在csnms中)
	 * 
	 * @param maxImportdate
	 * @param csnms
	 * @return
	 */
	private List<AccCustEntity> getLastAccBalances(Date lastImportDate,
			List<String> csnms) {
		CriteriaQuery cq = new CriteriaQuery(AccCustEntity.class);
		cq.eq("importdate", lastImportDate);
		cq.in("csnm", csnms.toArray());
		cq.add();
		return getListByCriteriaQuery(cq, false);
	}

	/**
	 * 开关户赋值
	 * 
	 * @param accOpens
	 * @param custMap
	 * @param bankinfos
	 * @param accCAs
	 * @return
	 */
	private List<AccCAEntity> getAccCAs(List<AccCustEntity> accOpens,
			Map<String, CustBaseEntity> custMap,
			List<BankinfoEntity> bankinfos, List<AccCAEntity> accCAs) {
		AccCAEntity accCA = null;
		BankinfoEntity bkinfo = null;
		accCAs = new ArrayList<AccCAEntity>();
		for (AccCustEntity acct : accOpens) {
			accCA = new AccCAEntity();
			accCA.setActiontype("A");
			accCA.setActiondesc("");
			bkinfo = getBankInfoByBrca(bankinfos, acct.getBrca());
			accCA.setBrca(acct.getBrca());
			accCA.setBranchcode(bkinfo.getBranchcode());
			accCA.setBranchname(bkinfo.getFullcnname());
			accCA.setAccountno(acct.getMainacod());//T24有主账号和核心账号的区别，申报时要求使用主账号
			accCA.setAccountstat(acct.getAccountstat());
			accCA.setAmtype(acct.getAmtype());
			accCA.setEncode(custMap.get(acct.getCsnm()).getEncode());
			accCA.setEnname(custMap.get(acct.getCsnm()).getCtnm());
			accCA.setAccounttype(acct.getAccounttype());
			accCA.setAccountcata(acct.getAccountcata());
			accCA.setCurrencycode(acct.getCcy());
			if ("11".equals(acct.getAccountstat())) {
				accCA.setBusinessdate(acct.getOpendate());
			} else if ("13".equals(acct.getAccountstat())) {
				accCA.setBusinessdate(acct.getClosedate());
			} else {
				accCA.setBusinessdate(acct.getImportdate());
			}

			if (null != acct.getFileNumber()
					&& !"".equals(acct.getFileNumber())
					&& acct.getFileNumber().length() > 1) {
				accCA.setFilenumber(acct.getFileNumber());
			} else {
				accCA.setFilenumber("N/A");// 不能为空，无filenumber要填写‘N/A’
			}
			accCA.setLimittype(acct.getLimittype());
			accCA.setAccountlimit(acct.getAccountlimit());
			accCA.setRemark("");
			accCA.setIsedit("0");
			accCA.setIshandadd("0");
			accCA.setIsdel("0");
			accCA.setIsexport("0");
			accCA.setIsvalidation("0");
			accCA.setIsinsafe("0");
			accCA.setImportdate(acct.getImportdate());
			accCA.setRptno((accCA.getBranchcode() + accCA.getAccountno() + accCA
					.getCurrencycode()));
			accCAs.add(accCA);
		}
		return accCAs;
	}

	/**
	 * 收支余赋值
	 * 
	 * @param trans
	 * @param accMovements
	 * @param accMovements
	 * @param custMap
	 * @param bankinfos
	 * @param accCBs
	 * @return
	 */
	private List<AccCBEntity> getAccCBs(List<TranDetailEntity> trans,
			List<AccCustEntity> lastAccBalances,
			List<AccCustEntity> accMovements,
			Map<String, CustBaseEntity> custMap,
			List<BankinfoEntity> bankinfos, List<AccCBEntity> accCBs) {
		AccCBEntity accCB = null;
		accCBs = new ArrayList<AccCBEntity>();
		// 提取所有已经上报账户收支余信息

		for (AccCustEntity acct : accMovements) {
			accCB = new AccCBEntity();

			accCB.setActiontype("A");
			accCB.setActiondesc("");
			accCB.setBranchcode(getBankInfoByBrca(bankinfos, acct.getBrca())
					.getBranchcode());
			accCB.setAccountno(acct.getMainacod());
			accCB.setDealdate(acct.getImportdate());
			accCB.setCurrencycode(acct.getCcy());
			accCB.setBalance(acct.getBalance());
			accCB.setBrca(acct.getBrca());
			setLastBalance(lastAccBalances, accCB, acct);

			setSafeBalance(accCB, acct);

			int result = setCreditAndDebit(accCB, trans);

			accCB.setRemark("");
			accCB.setIsedit("0");
			accCB.setIshandadd("0");
			accCB.setIsdel("0");
			accCB.setIsexport("0");
			accCB.setIsvalidation("0");
			accCB.setIslastexport("0");
			accCB.setIsinsafe("0");
			accCB.setImportdate(acct.getImportdate());
			accCB.setRptno(accCB.getBranchcode() + accCB.getAccountno()
					+ accCB.getCurrencycode()
					+ DateUtils.dateToStr(accCB.getDealdate(), "yyyyMMdd"));// 提取时赋值
			accCBs.add(accCB);
		}
		return accCBs;
	}

	/**
	 * 对AccCB的上一营业日的余额赋值
	 * 
	 * @param lastAccBalances
	 * @param accCB
	 * @param acct
	 */
	private void setLastBalance(List<AccCustEntity> lastAccBalances,
			AccCBEntity accCB, AccCustEntity acct) {
		AccCustEntity lastacct = null;
		if (null != lastAccBalances) {
			lastacct = getLastacct(acct, lastAccBalances);
		}
		if (null == lastacct) {
			accCB.setLastbalance(new BigDecimal(0));
		} else {
			accCB.setLastbalance(lastacct.getBalance());
		}
	}

	/**
	 * 通过账户，币种
	 * 
	 * @param acct
	 * @param lastAccMovements
	 * @return
	 */
	private AccCustEntity getLastacct(AccCustEntity acct,
			List<AccCustEntity> lastAccMovements) {
		for (AccCustEntity lastacct : lastAccMovements) {
			if (acct.getCsnm().equals(lastacct.getCsnm())
					&& acct.getMainacod().equals(lastacct.getMainacod())
					&& acct.getCcy().equals(lastacct.getCcy())
					&& acct.getBrca().equals(lastacct.getBrca())) {
				return lastacct;
			}
		}
		return null;
	}

	/**
	 * 对AccCB的已经报送SAFE的余额赋值
	 * 
	 * @param accCB
	 * @param acct
	 * @return
	 */

	private void setSafeBalance(AccCBEntity accCB, AccCustEntity acct) {
		AccCBEntity lastAccCB = (AccCBEntity) getLastSafeBalance(acct);
		if (null == lastAccCB) {
			accCB.setSafebalance(new BigDecimal(0));
		} else {
			accCB.setSafebalance(lastAccCB.getBalance());
		}
	}

	/**
	 * 得到最近一次上报到SAFE的收支余记录
	 * 
	 * @param ifxacct
	 * @return
	 */
	private AccCBEntity getLastSafeBalance(AccCustEntity ifxacct) {

		CriteriaQuery cq = new CriteriaQuery(AccCBEntity.class);
		cq.eq("accountno", ifxacct.getMainacod());
		cq.eq("currencycode", ifxacct.getCcy());
		cq.eq("brca", ifxacct.getBrca());
		cq.eq("islastexport", "1");
		cq.lt("importdate", ifxacct.getImportdate());
		cq.setOrder((Map<String, Object>)(new HashMap<String,Object>().put("importdate","desc")));
		cq.add();
		
		List<AccCBEntity> ListAC=getListByCriteriaQuery(cq,false);
		return ListAC.get(0);
	}

	/**
	 * 计算当前营业日期的收支总额，并通过上一日账户余额，本日账户余额，最近一次上报SAFE的账户余额，判断本次上报是否余额平衡
	 * 
	 * @param accCB
	 *            本日要上报收支余信息
	 * @param trans
	 *            本日所有交易信息
	 * @return 0 本日与上一营业日不平 ，1 平衡， 2 上一营业日与最近一次上报SAFE的余额不等
	 */
	private int setCreditAndDebit(AccCBEntity accCB,
			List<TranDetailEntity> trans) {
		BigDecimal credit = new BigDecimal(0);
		BigDecimal debit = new BigDecimal(0);
		for (TranDetailEntity tran : trans) {
			if (tran.getMainacod().equals(accCB.getAccountno())
					&& tran.getCcy().equals(accCB.getCurrencycode())
					&& tran.getBrca().equals(accCB.getBrca())) {
				if ("1".equalsIgnoreCase(tran.getDrcr())) {
					credit = credit.add(tran.getPsta());
				} else {
					debit = debit.add(tran.getPsta());
				}
				accCB.setBrca(tran.getBrca());
			}
		}
		accCB.setCredit(credit);
		accCB.setDebit(debit);

		if (accCB.getLastbalance().add(credit).subtract(debit)
				.compareTo(accCB.getBalance()) != 0) {
			accCB.setState("0");
			return 0;
		} else if (accCB.getSafebalance().compareTo(accCB.getLastbalance()) != 0) {
			accCB.setState("2");
			return 2;
		} else {
			accCB.setState("1");
		}
		return 1;
	}

	/**
	 * 移除maxImportdate下已经已经复核或者导出的开户记录
	 * 
	 * @param maxImportdate
	 * @param accCAs
	 */
	protected void removeExistOpens(Date maxImportdate, List<AccCAEntity> accCAs) {

		List<AccCAEntity> alreadyValidationOrExport = findHql(
				"from AccCAEntity where  importdate=? and (isvalidation='1' or isexport='1' or isinsafe='1')",
				maxImportdate);
		if (null != alreadyValidationOrExport
				&& !alreadyValidationOrExport.isEmpty()) {
			List<AccCAEntity> temps = new ArrayList<AccCAEntity>();
			for (AccCAEntity accCA : accCAs) {
				for (AccCAEntity accCA2 : alreadyValidationOrExport) {
					if (accCA.getBranchcode().equals(accCA2.getBranchcode())
							&& accCA.getAccountno().equals(
									accCA2.getAccountno())
							&& accCA.getCurrencycode().equals(
									accCA2.getCurrencycode())) {
						temps.add(accCA);
					}
				}
			}
			if (!temps.isEmpty()) {
				accCAs.removeAll(temps);
			}
		}
	}

	/**
	 * 移除maxImportdate下已经已经复核或者导出的收支记录
	 * 
	 * @param maxImportdate
	 * @param accCAs
	 */
	protected void removeExistAccountMovements(Date maxImportdate,
			List<AccCBEntity> accCBs) {
		List<AccCBEntity> alreadyValidationOrExport = findHql(
				"from AccCBEntity where  importdate=? and (isvalidation='1' or isexport='1' or isinsafe='1')",
				maxImportdate);
		if (null != alreadyValidationOrExport
				&& !alreadyValidationOrExport.isEmpty()) {
			List<AccCBEntity> temps = new ArrayList<AccCBEntity>();
			for (AccCBEntity accCB : accCBs) {
				for (AccCBEntity accCB2 : alreadyValidationOrExport) {
					if (accCB.getBranchcode().equals(accCB2.getBranchcode())
							&& accCB.getAccountno().equals(
									accCB2.getAccountno())
							&& accCB.getCurrencycode().equals(
									accCB2.getCurrencycode())
							&& accCB.getDealdate().equals(accCB2.getDealdate())) {
						temps.add(accCB);
					}
				}
			}
			if (!temps.isEmpty()) {
				accCBs.removeAll(temps);
			}
		}
	}

	@Override
	public Class getClazzByType(String curfile) {
		Class clazz = null;
		if (curfile.equalsIgnoreCase("CA")) {
			clazz = AccCAEntity.class;
		} else if (curfile.equalsIgnoreCase("CB")) {
			clazz = AccCBEntity.class;
		}
		return clazz;
	}

	@Override
	public String transformeAcc(Date date) {

		// 得到date下t_acc_cust的所有记录
		List<AccCustEntity> acccusts = findHql(
				"from AccCustEntity where  importdate=? and accounttype is not null and accounttype<>'' and accounttype<>'0'",
				date);

		// AccOpen表下所有数据
		List<AccCAEntity> acccas = findHql(
				"from AccCAEntity where   (isvalidation='1' and isexport='1' and isinsafe='1')",
				null);
		Map<String, AccCAEntity> mapa = new HashMap<>();
		Map<String, AccCAEntity> mapc = new HashMap<>();
		for (AccCAEntity accCAEntity : acccas) {
			
			accCAEntity.setIfxacod(accCAEntity.getAccountno());
			
			if(!"13".equals(accCAEntity.getAccountstat())){
				mapa.put(
						accCAEntity.getAccountno() + accCAEntity.getCurrencycode(),
						accCAEntity);
			}else{
				mapc.put(
						accCAEntity.getAccountno() + accCAEntity.getCurrencycode(),
						accCAEntity);
			}
			
			
		}
		// AccMovement表下所有islastExport='1'
		List<AccCBEntity> acccbs = findHql(
				"from AccCBEntity where  islastExport='1' and  (isvalidation='1' and isexport='1' and isinsafe='1')",
				null);

		Map<String, AccCBEntity> mapb = new HashMap<>();
		for (AccCBEntity accCBEntity : acccbs) {
			mapb.put(
					accCBEntity.getAccountno() + accCBEntity.getCurrencycode(),
					accCBEntity);
			accCBEntity.setIfxacod(accCBEntity.getAccountno());
		}

		StringBuffer sb=new StringBuffer();
		StringBuffer snb=new StringBuffer();
		StringBuffer ssb=new StringBuffer();
		StringBuffer ssbb=new StringBuffer();
		StringBuffer sbc=new StringBuffer();
		ssb.append("T24中accounttype is not null and accounttype <>'' and accounttype <>'0'的账号共有："+acccusts.size()+"\n");
		ssb.append("ACC 开户中isvalidation='1' and isexport='1' and isinsafe='1'的账号共有："+acccas.size()+"\n");
		ssb.append("Acc 收支中islastExport='1' and  (isvalidation='1' and isexport='1' and isinsafe='1'的账号共有："+acccbs.size()+"\n");
		ssb.append("T24中有以下BPACOD，但在ACC已上报记录中没有匹配：\n");
		for (AccCustEntity accCustEntity : acccusts) {

			String bpacod = accCustEntity.getBpacod();
			AccCAEntity accca = mapa.get(bpacod)!=null?mapa.get(bpacod):mapa.get("NRA"+bpacod);
			
			if(null!=accca){
				
				accca.setAccountno(accCustEntity.getMainacod());
				accca.setAccountstat("12");
				accca.setIshandadd("1");
				accca.setIsvalidation("0");
				accca.setIsexport("0");
				accca.setIsinsafe("0");
				accca.setActiontype("C");
				accca.setActiondesc("银行换核心系统，账号转换为新账号");
			}else{
				ssb.append("BPacod:"+bpacod+". 账户类型为:"+accCustEntity.getAccounttype()+" Category为："+accCustEntity.getCategory()+"没有上报过开户记录\n ");
				//continue;
			}
			
			AccCBEntity acccb = mapb.get(bpacod)!=null?mapb.get(bpacod):mapb.get("NRA"+bpacod);;
			if(null!=acccb){
				acccb.setAccountno(accCustEntity.getMainacod());
				acccb.setIshandadd("1");
			}else if(accCustEntity.getBalance().compareTo(new BigDecimal(0.0))!=0){
				ssbb.append("BPacod:"+bpacod+"余额："+accCustEntity.getBalance()+"不存在于交易记录\n " );
			}
			
		}

		List<AccCAEntity> as = new ArrayList<AccCAEntity>( mapa.values());
	
		batchSave(as);
		List<AccCBEntity> bs =new ArrayList<AccCBEntity>( mapb.values());
		batchSave(bs);
		
		sb.append("以下账号已经进行了转换:\n");
		snb.append("以下账号没有找到相对应的T24账号：\n");
		for (AccCAEntity a : as) {
			if("1".equals(a.getIshandadd())){//已经修改过账号
				sb.append("旧账号为："+a.getIfxacod()+"	 ");
				sb.append("新账号为："+a.getAccountno());
				sb.append("币种为："+a.getCurrencycode()+"\n");
				
			}else{//没有在T24找到相对应的账号
				AccCAEntity acccc=mapc.get(a.getAccountno()+a.getCurrencycode());
				if(null!=acccc){
					sbc.append("账号："+acccc.getAccountno()+acccc.getCurrencycode()+"已经关闭，不需要再进行转换\n");
				}else{
					snb.append("账号为："+a.getAccountno());
					snb.append("币种为："+a.getCurrencycode()+"\n");
				}
				
				
			}
		}

		return (ssb.append("\n").append(ssbb).append("\n").append(snb).append("\n").append(sb).append(sbc)).toString();
	}
}