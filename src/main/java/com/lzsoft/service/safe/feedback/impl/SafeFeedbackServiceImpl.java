package com.lzsoft.service.safe.feedback.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.entity.common.FeedbackheadinfoEntity;
import com.lzsoft.entity.common.FeedbackloginfoEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.AccCBEntity;

@Service
@Transactional 
public class SafeFeedbackServiceImpl extends FeedbackServiceImpl {
 
	@Override
	public void execute() throws Exception {
		try {
			LogUtil.info("开始读取反馈信息");
			LogUtil.info("反馈信息：" + DateUtils.getCurrDate24() + "。");
			 
			 			 System.out.println("");
			List<FeedbackloginfoEntity> flogs = getSentFilenamesWF();
			for (FeedbackloginfoEntity flog : flogs) {
				 readFeedback(flog.getImportdate(), "",flog.getApptype(),false); 
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("读取反馈信息时发生异常！", e);
		}
	}

	/**
	 * 读取反馈文件，根据反馈更新业务反馈的状态
	 */
	@Override
	public String readFeedback(Date importdate, String brca, String appType, boolean local) {
		String errBuffer = "";
		try {

			errBuffer = readFeedback(importdate, appType, local);
			if (errBuffer.indexOf("成功") >= 0 && errBuffer.indexOf("没有") < 0) {
				String m = updateDataStatus(importdate, appType);
				if (m.length() > 1) {
					errBuffer = "以下报文需要处理" + m + "请点击'查询'查看详情。";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "读取日期[" + DateUtils.dateToStr(importdate, Constants.DATEFORMAT) + "]的反馈文件失败.";
		}

		return errBuffer;
	}

	/**
	 * 根据传入日期，返回taskSchedule
	 * 
	 * @return
	 */
	private List<TaskscheduleEntity> queryTaskSchedule(String type) {

		return findHql(TaskscheduleEntity.class, " executable=? and taskname=?", new Object[] { true, type });// "accWF"

	}

	/**
	 * 对读取日期date下所有记录状态进行更新 进入SAFE记录标识为1，等待的标识为2，有错误待解决的标识为3, 预校验有错误的标识为4
	 * 
	 * @param appType
	 * @param date
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String updateDataStatus(Date importdate, String appType)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List clazzlist = new ArrayList();
		init();
		Set keysets = beanMap.keySet();
		for (Object beankey : keysets) {
			if (StringUtils.contains((String) beankey, appType)) {
				clazzlist.add(beanMap.get(beankey));
			}
		}
		StringBuffer errBuffer = new StringBuffer();
		List<FeedbackheadinfoEntity> fdhinfos = findHql(FeedbackheadinfoEntity.class, "importdate=? and apptype=?  ",
				new Object[] { importdate, appType });

		for (FeedbackheadinfoEntity feedbackHeadinfo : fdhinfos) {
			String filename = StringUtils.substringBefore(feedbackHeadinfo.getFilename(), "ERR");
			String tfilename = StringUtils.substringBefore(feedbackHeadinfo.getTfilename(), "ERR");
			String headid = feedbackHeadinfo.getId();
			if ("1".equals(feedbackHeadinfo.getState()) && feedbackHeadinfo.getCurrentfile().indexOf("T") >= 0) {// 没有错误进行SAFE的
				updateSuccessStat2DB(tfilename, importdate, clazzlist);

			} else if ("0".equals(feedbackHeadinfo.getState()) && feedbackHeadinfo.getCurrentfile().indexOf("T") < 0) {// 有错误，有待处理记录的
				updateFailstat2DB(filename, tfilename, headid, clazzlist);
				errBuffer.append("[" + filename + "],");
			}

		}
		return errBuffer.toString();

	}

	/**
	 * 将业务数据中的isinsafe更新 设置记录中Isinsafe为1，说明正常入库 如果是ACCCB进行特殊处理（标识islastexport)
	 * 
	 * @param tfilename
	 * @param importdate
	 * @param clazzlist
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateSuccessStat2DB(String tfilename, Date importdate, List clazzlist)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List uobjs = new ArrayList();
		List<AccCBEntity> lastAccMovements = new ArrayList<AccCBEntity>();
		for (Object clazz : clazzlist) {
			List objects = findHql((Class) clazz, "tfilename=?", new Object[] { tfilename });
			for (Object object : objects) {
				PropertyUtils.setProperty(object, "isinsafe", "1");

				if (object.getClass().equals(AccCBEntity.class)) {// 如果是ACCCB需要特殊处理

					PropertyUtils.setProperty(object, "islastexport", "1");
					getLastAccCB(importdate, lastAccMovements, (AccCBEntity) object);
				}
				uobjs.add(object);
			}
		}
		if (uobjs.size() > 0) {
			batchSave(uobjs);
		}
		if (!lastAccMovements.isEmpty()) {
			batchSave(lastAccMovements);// 更新 最近一次上报
		}

	}

	/**
	 * 
	 * @param importdate
	 *            营业日期
	 * @param lastAccMovements
	 *            最近一天的所有记录集合
	 * @param accCB
	 *            当前交易记录
	 */
	private void getLastAccCB(Date importdate, List<AccCBEntity> lastAccMovements, AccCBEntity accCB) {
		CriteriaQuery cq = new CriteriaQuery(AccCBEntity.class);
		cq.eq("accountno", accCB.getAccountno());
		cq.eq("currencycode", accCB.getCurrencycode());
		cq.lt("dealdate", accCB.getImportdate());
		cq.max("importdate");
		cq.add();

		Date lastdealdate = getUniqueResultByCriteriaQuery(cq);
		if (lastdealdate != null && importdate.compareTo(lastdealdate) != 0) {
			CriteriaQuery cq2 = new CriteriaQuery(AccCBEntity.class);
			cq2.eq("accountno", accCB.getAccountno());
			cq2.eq("currencycode", accCB.getCurrencycode());
			cq2.eq("dealdate", lastdealdate);
			cq2.add();
			AccCBEntity lastAcccb = getUniqueResultByCriteriaQuery(cq2);
			if (null != lastAcccb) {
				lastAcccb.setIslastexport("0");
				lastAccMovements.add(lastAcccb);
			}
		}
	}

	/**
	 * 对没有正常进入SAFE的记录，标识其状态为3，说明失败（需要重新进行上报）
	 * 
	 * @param clazzlist
	 * @param feedbackHeadinfo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateFailstat2DB(String filename, String tfilename, String id, List clazzlist)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List rptnos = getFieldValueByWhere(FeedbackerrorinfoEntity.class, "rptno", "feedbackheadid=?",
				new Object[] { id });

		List uobjs = new ArrayList();
		for (Object clazz : clazzlist) {
			List objects = findHql((Class) clazz, "filename=? and tfilename=? ", new Object[] { filename, tfilename });
			for (Object object : objects) {
				if (rptnos.contains(PropertyUtils.getProperty(object, "rptno"))) {
					PropertyUtils.setProperty(object, "isinsafe", "3");
					PropertyUtils.setProperty(object, "isedit", "0");
					PropertyUtils.setProperty(object, "isvalidation", "0");
					PropertyUtils.setProperty(object, "isexport", "1");
				} else {
					PropertyUtils.setProperty(object, "isinsafe", "1");
				}

				uobjs.add(object);
			}
		}
		if (uobjs.size() > 0) {
			batchSave(uobjs);
		}
	}
}
