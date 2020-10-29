package com.lzsoft.service.safe.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.alibaba.druid.util.StringUtils;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.CustCorpEntity;
import com.lzsoft.entity.common.CustIndvEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;

public class ExtractServiceImpl extends CommonServiceImpl {

	protected static String logPath = null;

	/**
	 * result 1:正常返回 2:没有符合账户性质的外汇账号 3:没有交易数据,也没有开关户数据 4:有交易数据但是没有账户信息
	 * 5:没有与外汇账号关联的主体信息
	 * */
	public void execute() throws Exception {
	}

	protected BankinfoEntity getBankInfoByBrca(List<BankinfoEntity> bankinfos,
			String brca) {
		for (BankinfoEntity bankinfo : bankinfos) {
			if (bankinfo.getBrca().equals(brca)) {
				return bankinfo;
			}

		}
		return null;
	}

	protected List<BankinfoEntity> getAllBankinfos() {
		return this.getList(BankinfoEntity.class);
	}

	/**
	 * 提取maximportdate日期下，客户编号在csnms范围内的客户
	 * 
	 * @param maxImportdate
	 * @param csnms
	 * @return
	 */
	protected List<CustBaseEntity> getCustinfos(Date maxImportdate,
			List<String> csnms) {
		List<CustBaseEntity> custs = new ArrayList<CustBaseEntity>();
		CriteriaQuery cqc = new CriteriaQuery(CustCorpEntity.class);
		cqc.eq("importdate", maxImportdate);
		cqc.in("csnm", csnms.toArray());
		cqc.add();
		List<CustBaseEntity> corps = getListByCriteriaQuery(cqc, false);
		CriteriaQuery cqi = new CriteriaQuery(CustIndvEntity.class);
		cqi.eq("importdate", maxImportdate);
		cqi.in("csnm", csnms.toArray());
		cqi.add();
		List<CustBaseEntity> indvs = getListByCriteriaQuery(cqi, false);
		custs.addAll(corps);
		custs.addAll(indvs);
		return custs;
	}

	/**
	 * 根据传入日期，返回taskSchedule
	 * 
	 * @param maxImportdate
	 * @return
	 */
	protected TaskscheduleEntity queryTaskSchedule(Date maxImportdate,
			String taskname) {
		List<TaskscheduleEntity> t = findHql(
				" from  TaskscheduleEntity where  importdate=? and taskname=?",
				new Object[] { maxImportdate, taskname });
		if (!t.isEmpty()) {
			return t.get(0);
		}
		return null;

	}

	/**
	 * 通过Source区分是对私还是对公 通过islocal判断是境内还是境外
	 * 
	 * @param obj
	 * @param cust
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void setCustypeAndIdnumber(Object obj, CustBaseEntity cust) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (StringUtils.equals("A1", cust.getSource())) {
			if (StringUtils.equals("1", cust.getIslocal())) {
				PropertyUtils.setProperty(obj, "custype", "D");
				PropertyUtils.setProperty(obj, "idcode", cust.getEncode());
			} else {
				PropertyUtils.setProperty(obj, "custype", "F");
				PropertyUtils.setProperty(obj, "idcode", cust.getEncode());
			}
		} else {
			PropertyUtils.setProperty(obj, "custype", "C");
			PropertyUtils.setProperty(obj, "custcod", cust.getEncode());
		}
	}

	protected Date getMaxImportDate(String taskname) {

		List<TaskscheduleEntity> t = findByPropertyisOrder(
				TaskscheduleEntity.class, "taskname", taskname, false);
		if (!t.isEmpty()) {
			return t.get(0).getImportdate();
		}
		return null;
	}
}
