package com.lzsoft.service.safe.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.sms.entity.TSSmsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.MailReceipterEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.entity.safe.AccCBEntity;
import com.lzsoft.entity.safe.BopAEntity;
import com.lzsoft.entity.safe.BopBEntity;
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
import com.lzsoft.entity.safe.JshDEntity;
import com.lzsoft.entity.safe.JshEEntity;
import com.lzsoft.entity.safe.JshFEntity;
import com.lzsoft.entity.safe.JshGEntity;
import com.lzsoft.service.safe.SentMTSServiceI;
import com.lzsoft.service.safe.feedback.impl.FeedbackServiceImpl;
@Service("sentMTSService")
@Transactional
public class SentMTSServiceImpl extends FeedbackServiceImpl implements
		SentMTSServiceI {

	/**
	 * 进行业务发送
	 */
	public void execute() {
		//查询未上报数据，进行提醒
		queryUnsendToMTSDatas();
		try {
			//判断服务器中是否还有文件需要发送MTS，给所有用户发送邮件
			judgeWhetherSent();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}

	}

	/**
	 * 判断是否还有文件需要发送MTS，给所有用户发送邮件，进行发送
	 * @return
	 * @throws Exception
	 */
	public boolean judgeWhetherSent() throws Exception {

		String errBuffer = "";
		//检查是否还有文件未发送MTS
		errBuffer = readSendFloders();

		if (errBuffer.toString().length() > 1) {
			sendMailForMTS("MTS", errBuffer, true);
			return true;
		} else {
			errBuffer = "已经没有文件需要发送。。";
			sendMailForMTS("MTS", errBuffer, false);
			return false;
		}

	}

	@SuppressWarnings("deprecation")
	private void sendMailForMTS(String type, String errBuffer, boolean f) {
		TSSmsEntity tsmse = new TSSmsEntity();
		if (f) {
			List emails = getFieldValueByWhere(MailReceipterEntity.class,
					"email", "state='Y' or msend='Y'", new Object[] {});
			if (emails.size() > 0) {
				tsmse.setEsType("2");
				tsmse.setEsTitle("MTS服务器内有上报文件还未发送");
				tsmse.setEsContent("<p>Hi ,</p><p>" + errBuffer
						+ "请查看MTS服务器是否运行正常。</p><p>" + DateUtils.getCurrDate24()
						+ "</p>");
				tsmse.setEsReceiver(StringUtils.join(emails, ";"));
				tsmse.setCreateBy("admin");
				tsmse.setCreateDate(DateUtils.getDate());
				tsmse.setCreateName("系统自动");
				tsmse.setEsStatus("1");
			} else {
				tsmse.setEsTitle("MTS服务器内有上报文件还未发送 ,未找到 相关邮件收取人信息");
				tsmse.setEsContent("<p>Hi ,</p><p>" + errBuffer
						+ "请查看MTS服务器是否运行正常。</p><p>" + DateUtils.getCurrDate24()
						+ "</p>");
				tsmse.setEsReceiver("");
				tsmse.setCreateBy("admin");
				tsmse.setCreateDate(DateUtils.getDate());
				tsmse.setCreateName("系统自动");
				tsmse.setEsStatus("1");
				// addLog("MTS发送异常", Globals.Log_Type_ALERT,
				// Globals.Log_Leavel_WARRING);
			}
		} else {
			tsmse.setEsTitle("MTS 无上报文件等待发送 ");
			tsmse.setEsContent("<p>Hi ,</p><p>MTS 无上报文件等待发送 。</p><p>"
					+ DateUtils.getCurrDate24() + "</p>");
			tsmse.setCreateBy("admin");
			tsmse.setCreateDate(DateUtils.getDate());
			tsmse.setCreateName("系统自动");
			tsmse.setEsStatus("2");// 不需要发邮件
		}
		save(tsmse);
	}

	/**
	 * 查询未上传MTS的数据，进行邮件提醒
	 */
	private void queryUnsendToMTSDatas() {
		Date maxed = getMaxDate();
		TSSmsEntity tsmse = new TSSmsEntity();

		//查询当前日期下，各个分支行还未发送MTS的数据
		Map<String, StringBuffer> msmap = new HashMap<String, StringBuffer>();
		getBrcasByClazz(AccCAEntity.class, maxed, msmap, "<p>开关户数据；</p>");
		getBrcasByClazz(AccCBEntity.class, maxed, msmap, "<p>收支余数据；</p>");
		getBrcasByClazz(BopAEntity.class, maxed, msmap, "<p>涉外收入数据-基础信息;</p>");
		getBrcasByClazz(BopBEntity.class, maxed, msmap, "<p>境外汇款数据-基础信息;</p>");
		getBrcasByClazz(BopCEntity.class, maxed, msmap, "<p>对外付款承兑数据-基础信息;</p>");
		getBrcasByClazz(BopDEntity.class, maxed, msmap, "<p>境内收入数据-基础信息;</p>");
		getBrcasByClazz(BopEEntity.class, maxed, msmap, "<p>境内汇款数据-基础信息;</p>");
		getBrcasByClazz(BopFEntity.class, maxed, msmap, "<p>境内付款承兑数据-基础信息;</p>");

		getBrcasByClazz(BopGEntity.class, maxed, msmap, "<p>涉外收入数据-申报信息;</p>");
		getBrcasByClazz(BopHEntity.class, maxed, msmap, "<p>境外汇款数据-申报信息;</p>");
		getBrcasByClazz(BopKEntity.class, maxed, msmap, "<p>对外付款承兑数据-申报信息;</p>");

		getBrcasByClazz(BopNEntity.class, maxed, msmap, "<p>境外汇款数据-管理信息;</p>");
		getBrcasByClazz(BopPEntity.class, maxed, msmap, "<p>对外付款承兑数据-管理信息;</p>");
		getBrcasByClazz(BopREntity.class, maxed, msmap, "<p>境内收入数据-管理信息;</p>");
		getBrcasByClazz(BopQEntity.class, maxed, msmap, "<p>境内汇款数据-管理信息;</p>");
		getBrcasByClazz(BopSEntity.class, maxed, msmap, "<p>境内付款承兑数据-管理信息;</p>");

		getBrcasByClazz(JshDEntity.class, maxed, msmap,
				"<p>外汇账户内结汇数据-基础信息;</p>");
		getBrcasByClazz(JshEEntity.class, maxed, msmap,
				"<p>外汇账户内售汇数据-基础信息;</p>");
		getBrcasByClazz(JshFEntity.class, maxed, msmap,
				"<p>外汇账户内结汇数据-管理信息;</p>");
		getBrcasByClazz(JshGEntity.class, maxed, msmap,
				"<p>外汇账户内售汇数据-管理信息;</p>");

		//利用邮箱，进行信息推送
		if (msmap.size() > 0) {
			sendMessageToRelatedPerson(maxed, tsmse, msmap);
		}

	}

	/***
	 * 将邮件发送给各个分行的邮箱
	 * @param maxed
	 * @param tsmse
	 * @param msmap
	 */
	private void sendMessageToRelatedPerson(Date maxed, TSSmsEntity tsmse,
			Map<String, StringBuffer> msmap) {

		Set keys = msmap.keySet();
		int c = 0;
		for (Object key : keys) {
			List emails = getFieldValueByWhere(MailReceipterEntity.class,
					"email", "state='Y' and (brca like ? or msend='Y')",
					new Object[] { "%" + (String) key + "%" });
			if (emails.size() > 0) {
				tsmse.setEsType("2");
				tsmse.setEsTitle("上报提醒:" + key);
				tsmse.setEsContent("<p>Hi ,</p><p>" + "交易日期:"
						+ DateUtils.dateToStr(maxed, "yyyy-MM-dd")
						+ "有以下数据还没上传MTS服务器，请确认后及时处理。</p>" + msmap.get(key)
						+ "<p>" + DateUtils.getCurrDate24() + "</p>");
				tsmse.setEsReceiver(StringUtils.join(emails, ";"));
				tsmse.setCreateBy("admin");
				tsmse.setCreateDate(DateUtils.getDate());
				tsmse.setCreateName("系统自动");
				tsmse.setEsStatus("1");
				c++;
			} else {
				tsmse.setEsTitle("上报提醒:" + key + ",未找到 相关邮件收取人信息");
				tsmse.setEsContent("<p>Hi ,</p><p>" + "交易日期为:"
						+ DateUtils.dateToStr(maxed, "yyyy-MM-dd")
						+ "有以下数据还没上传MTS服务器，请经办人员确认后及时处理。</p>" + msmap.get(key)
						+ "<p>" + DateUtils.getCurrDate24() + "</p>");
				tsmse.setEsReceiver(" ");
				tsmse.setCreateBy("admin");
				tsmse.setCreateDate(DateUtils.getDate());
				tsmse.setCreateName("系统自动");
				tsmse.setEsStatus("2");
				// addLog("MTS发送异常", Globals.Log_Type_ALERT,
				// Globals.Log_Leavel_WARRING);
			}
			save(tsmse);
		}
	}

	/**
	 * 获取最大营业日
	 * @return
	 */
	private Date getMaxDate() {
		Object maxed = getMaxFieldValueByWhere(TaskscheduleEntity.class,
				"importdate", " taskname like ? ",
				new Object[] { "autoExtract%" });
		return (Date) maxed;
	}

	/**
	 * 查询当前日期下，各个分支行还未发送MTS的数据
	 * @param clazz
	 * @param maxed
	 * @param msmap
	 * @param tsmse
	 */
	private <T> void getBrcasByClazz(Class<T> clazz, Date maxed,
			Map<String, StringBuffer> msmap, String tsmse) {
		List<T> brcas = getFieldValueByWhere(clazz, "brca",
				"importdate=? and isexport='0' ", new Object[] { maxed });

		if (!brcas.isEmpty()) {
			for (Object brca : brcas) {
				if (null == msmap.get(brca)) {
					msmap.put((String) brca, new StringBuffer(tsmse));
				} else {
					msmap.get(brca).append(tsmse);
				}
			}

		}
	}

}
