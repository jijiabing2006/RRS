package com.lzsoft.service.safe.feedback.impl;

import org.springframework.stereotype.Service;


@Service
public class SentMtsServiceImpl extends FeedbackServiceImpl {/*

	@SuppressWarnings("unused")
	@Override
	public void execute() throws Exception {
		try {
			LogUtil.info("读取ACC BOP JSH数据，检测上传MTS状态："
					+ DateUtils.getCurrDate24() + "。");
			if (queryUnsendToMTSDatas() == 1) {
				logs.setEventdate(new Date());
				logs.setEvents("MTS警告");
				logs.setObjects("有数据未上传MTS，相关邮件已经发送。");
				LogUtil.generateLog(logPath,
						"有数据未上传，相关邮件已经发送。" + DateUtil.getCurrDate24() + "。");
			} else if (queryUnsendToMTSDatas() == 2) {
				logs.setEventdate(new Date());
				logs.setEvents("MTS普通信息");
				logs.setObjects("未检测到未上传MTS服务器的数据！");
				logs.setUserid("admin");
				LogUtil.generateLog(logPath,
						"未检测到未上传MTS服务的数据！" + DateUtil.getCurrDate24() + "。");
			} else if (queryUnsendToMTSDatas() == 0) {
				logs.setEventdate(new Date());
				logs.setEvents("MTS严重警告");
				logs.setObjects("有数据未上传MTS服务器,相关邮件未发送成功！");
				logs.setUserid("admin");
				LogUtil.generateLog(logPath, "有数据未上传MTS服务器,相关邮件未发送成功！"
						+ DateUtil.getCurrDate24() + "。");
			}
			LogUtil.generateLog(logPath,
					"读取MTS Send 发送目录信息" + DateUtil.getCurrDate24() + "。");
			if (judgeWhetherSent()) {
				logs.setEventdate(new Date());
				logs.setEvents("MTS严重警告");
				logs.setObjects("读取MTS Send目录,有文件没有上传MQ！邮件已经发送。");
				LogUtil.generateLog(logPath, "读取MTS Send目录,有文件没有上传MQ！邮件已经发送。"
						+ DateUtil.getCurrDate24() + "。");
			} else {
				logs.setEventdate(new Date());
				logs.setEvents("读取MTS Send");
				logs.setObjects("读取MTS Send目录,没有文件！");
				logs.setUserid("admin");
				LogUtil.generateLog(logPath,
						"读取MTS Send目录,没有文件！" + DateUtil.getCurrDate24() + "。");
			}
			dao.save(logs);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ddddd");
			LogUtil.handle(logPath, e);
			logs.setEventdate(new Date());
			logs.setEvents("读取MTS Send 异常");
			logs.setObjects("读取MTS Send 发送目录信息时发生异常！");
			logs.setUserid("admin");
			dao.save(logs);
		}
	}

	@SuppressWarnings("deprecation")
	private void sendMailForMTS(String type, String errBuffer, boolean f)
			throws Exception {
		Message mess = new Message();
		if (f) {
			String[] userid = new String[] { "userid" };
			List<Object[]> userids = dao.queryFieldValues(
					MessageReceipter.class, userid, "type=? and state=1",
					new Object[] { type });
if(null!=userids){
			String[] email = new String[] { "email" };
			List<Object[]> emails = dao.queryFieldValues(UserInfo.class, email,
					" userid in (?1) and email is not null and email<>''",
					new Object[] { userids });

			mess.setSubject("MTS服务器内有上报文件还未发送");
			mess.setMessage("<p>Hi ,</p><p>" + errBuffer
					+ "请查询相关系统是否正常。</p><p>" + DateUtil.getCurrDate24() + "</p>");

			mess.setAddress(emails.toString());
			mess.setSenddate(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
			String[] s = emails.toArray(new String[emails.size()]);
			System.out.println("");
			mailSender.send(s, mess.getSubject(), mess.getMessage());
}
			mess.setFlag("1");// 已经发邮件
		} else {
			mess.setSubject("MTS 无上报文件等待发送 ");
			mess.setMessage("<p>Hi ,</p><p>MTS 无上报文件等待发送 。</p><p>"
					+ DateUtil.getCurrDate24() + "</p>");
			mess.setFlag("2");// 不需要发邮件
		}

		dao.save(mess);

	}

	public boolean judgeWhetherSent() throws Exception {

		String errBuffer = "";
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

	public boolean haveNotsentDatas() {
		Date maxed = getMaxDate();

		return false;
	}

	public void readMtsLogs() throws Exception {
		Message message = null;
		Date maxed = getMaxDate();
		List<TaskSchedule> tl = queryExtractTaskSchedule(maxed);
		if (!tl.isEmpty()) {
			message = new Message();// 所有数据还没有提取完成,发邮件
		}
		// 提取正常,但是还没有发送到MTS
		// queryUnsendToMTSDatas();

		tl = querySentTaskSchedule(maxed);
		if (!tl.isEmpty()) {
			// 说明已经上传，等待读取反馈中
			int result = 0;
			for (TaskSchedule t : tl) {
				String type = StringUtils.substring(t.getTableName(), 0, 3);
				Reportsenttype rs = dao.findByWhere(Reportsenttype.class,
						"type = ?", new Object[] { type });
				result = readMtsLog(rs, maxed);
				if (result == 2) {// 有文件，说明还没发送，发mail

				}
				if (result == 3) {// 说明send文件夹下没有文件，但是也没有发送成功的LOG

				}
			}
		} else {

		}

	}

	*//**
	 * 根据传入日期，返回taskSchedule
	 * 
	 * @param maxed
	 * 
	 * @return
	 *//*
	private List<TaskSchedule> queryExtractTaskSchedule(Date maxed) {
		return dao
				.queryByWhere(
						TaskSchedule.class,
						" importdate=? and taskname like ? and executable=? and taskname not like ? ",
						new Object[] { maxed, "autoExtract%", true, "%AML" });

	}

	*//**
	 * 根据传入日期，返回taskSchedule
	 * 
	 * @param maxed2
	 * 
	 * @return
	 *//*
	private List<TaskSchedule> querySentTaskSchedule(Date maxed) {

		return dao.queryByWhere(TaskSchedule.class,
				" importdate=? and taskname like ? and executable=? ",
				new Object[] { maxed, "%WF%", true });

	}

	private Date getMaxDate() {
		Date maxed = (Date) dao.getMaxByWhere(TaskSchedule.class, "importdate",
				" taskname like ? ", new Object[] { "autoExtract%" });
		return maxed;
	}

	private int queryUnsendToMTSDatas() throws Exception {
		Date maxed = getMaxDate();
		Message mess = new Message();
		Map<String, StringBuffer> msmap = new HashMap<String, StringBuffer>();
		getBrcaByClazz(AccCAEntity.class, maxed, msmap, "<p>开关户数据；</p>");
		getBrcaByClazz(AccCBEntity.class, maxed, msmap, "<p>收支余数据；</p>");
		getBrcaByClazz(BopAEntity.class, maxed, msmap, "<p>涉外收入数据;</p>");
		getBrcaByClazz(BopBEntity.class, maxed, msmap, "<p>境外汇款数据;</p>");
		getBrcaByClazz(BopCEntity.class, maxed, msmap, "<p>对外付款承兑数据;</p>");
		getBrcaByClazz(BopDEntity.class, maxed, msmap, "<p>境内收入数据;</p>");
		getBrcaByClazz(BopEEntity.class, maxed, msmap, "<p>境内汇款数据;</p>");
		getBrcaByClazz(BopFEntity.class, maxed, msmap, "<p>境内付款承兑数据;</p>");
		getBrcaByClazz(JshDEntity.class, maxed, msmap, "<p>外汇账户内结汇基础数据;</p>");
		getBrcaByClazz(JshEEntity.class, maxed, msmap, "<p>外汇账户内售汇基础数据;</p>");
		getBrcaByClazz(JshFEntity.class, maxed, msmap, "<p>外汇账户内结汇管理数据;</p>");
		getBrcaByClazz(JshGEntity.class, maxed, msmap, "<p>外汇账户内售汇管理数据;</p>");
		if (msmap.size() > 0) {
			return sendMessageToRelatedPerson(maxed, mess, msmap);
		}
		return 2;
	}

	private <T extends BaseEO> void getBrcaByClazz(Class<T> clazz,
			Date maxed, Map<String, StringBuffer> msmap, String mess) {
		List<Object> subbkids = dao.getFieldValueByWhere(clazz, "brca",
				"importdate=? and isexport='0' ", new Object[] { maxed });

		if (!subbkids.isEmpty()) {
			for (Object subbkid : subbkids) {
				if (null == msmap.get(subbkid)) {
					msmap.put((String) subbkid, new StringBuffer(mess));
				} else {
					msmap.get(subbkid).append(mess);
				}
			}

		}
	}

	private int sendMessageToRelatedPerson(Date maxed, Message mess,
			Map<String, StringBuffer> msmap) throws IOException,
			MessagingException {

		Set keys = msmap.keySet();
		int c = 0;
		for (Object key : keys) {
			String[] userid = new String[] { "userid" };
			List<Object[]> userids = dao.queryFieldValues(
					MessageReceipter.class, userid,
					"state=1 and (subbkid=? or msend='1')",
					new Object[] { (String) key });
			if (userid.length > 0) {
				String[] email = new String[] { "email" };
				List<Object[]> emails = dao.queryFieldValues(UserInfo.class,
						email,
						" userid in (?1) and email is not null and email<>''",
						new Object[] { userids });
				mess.setSubject("上报提醒:" + key);
				mess.setMessage("<p>Hi ,</p><p>" + "交易日期:"
						+ DateUtil.dateToStr(maxed, "yyyy-MM-dd")
						+ "有以下数据还没上传MTS服务器，请确认后及时处理。</p>" + msmap.get(key)
						+ "<p>" + DateUtil.getCurrDate24() + "</p>");

				mess.setAddress(emails.toString());
				mess.setSenddate(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				String[] s = emails.toArray(new String[emails.size()]);
				mailSender.send(s, mess.getSubject(), mess.getMessage());
				mess.setFlag("1");// 已经发邮件
				c++;
			} else {
				mess.setSubject("上报提醒:" + key);
				mess.setMessage("<p>Hi ,</p><p>" + "交易日期为:"
						+ DateUtil.dateToStr(maxed, "yyyy-MM-dd")
						+ "有以下数据还没上传MTS服务器，请经办人员确认后及时处理。</p>" + msmap.get(key)
						+ "<p>" + DateUtil.getCurrDate24() + "</p>");

				mess.setAddress("未找到" + key + "相关邮件收取人信息");
				mess.setSenddate(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
				mess.setFlag("0");// 未发邮件
			}

			dao.save(mess);
		}
		if (c == keys.size()) {
			return 1;
		} else {
			return 0;
		}
	}

*/}
