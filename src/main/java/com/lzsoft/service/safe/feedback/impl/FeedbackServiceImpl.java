package com.lzsoft.service.safe.feedback.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.entity.common.FeedbackformatinfoEntity;
import com.lzsoft.entity.common.FeedbackheadinfoEntity;
import com.lzsoft.entity.common.FeedbackloginfoEntity;
import com.lzsoft.entity.common.ReportsenttypeEntity;
import com.lzsoft.service.safe.feedback.IFeedbackService;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackControlFilename;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackControlMsg;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackDataErr;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackDataFormat;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackDataMsg;
import com.lzsoft.service.safe.feedback.reportmanager.impl.FeedbackDataRec;
import com.lzsoft.service.safe.impl.SafeBaseService;
import com.lzsoft.util.FTPClientUtil;

public class FeedbackServiceImpl extends SafeBaseService implements
		IFeedbackService {

	/**
	 * 将反馈文件 从MTS服务器传到应用服务器
	 * @param importdate
	 * @param feedbackpath
	 * @param appType
	 * @return
	 * @throws Exception
	 */
	protected String readFeedbackFile(Date importdate, String feedbackpath,
			String appType) throws Exception {
		List<ReportsenttypeEntity> reportsenttypes = findHql(
				ReportsenttypeEntity.class, " type = ? and state=?",
				new Object[] { appType, "1" });// 得到AppType传输方式
		ReportsenttypeEntity reportsenttype = null;
		if (null == reportsenttypes) {
			return "请先在系统设置菜单的传输方式中设置" + appType + "报送方式的相关信息.";
		} else {
			reportsenttype = reportsenttypes.get(0);
		}
		if (StringUtils.equals("FTP", reportsenttype.getSenttype())) {
			String lp = readMtsLog(reportsenttype, importdate);
			if (!lp.equals("")) {
				//根据日志获取反馈文件名称
				saveMTSLog(importdate, appType, lp);
			} else {
				return "MTS服务器下没有["
						+ DateUtils.dateToStr(importdate, Constants.DATEFORMAT)
						+ "]发送成功的LOG记录，请确认上报日期";
			}

			return ftpUtilManager.readFeedbackFile(reportsenttype, importdate,
					feedbackpath, appType);
		}
		return "";
	}

	/**
	 * 检查是否有文件发送
	 * @return  未发送文件字符串
	 * @throws Exception
	 */
	protected String readSendFloders() throws Exception {
		List<ReportsenttypeEntity> reportsenttypes = findHql(
				ReportsenttypeEntity.class, " state=?", new Object[] { "1" });// 得到AppType传输方式
		StringBuffer errBuffer = new StringBuffer();
		if (null == reportsenttypes || reportsenttypes.isEmpty()) {
			return "请先在系统设置菜单的传输方式中设置SAFE报送方式的相关信息.";
		} else {
			for (ReportsenttypeEntity reportsenttype : reportsenttypes) {
				if (StringUtils.equals("FTP", reportsenttype.getSenttype())) {
					if (ftpUtilManager.getFilesInSendFloder(reportsenttype) > 0) {
						errBuffer.append("在MTS服务器还有以下文件未发送："
								+ reportsenttype.getReadytosenddirpath()
								+ ".  ");
					}
				}
			}
		}
		return errBuffer.toString();
	}

	/**
	 * 从FTP服务器读取反馈文件并解析反馈文件
	 * @param importdate  上报 日期
	 * @param appType 模块类型 ACC/BOP/JSH
	 * @param local 是从MTS服务器解析反馈文件还是从应用服务器解析反馈文件 local=true 在应用服务器直接解析
	 * @return
	 * @throws Exception
	 */
	protected String readFeedback(Date importdate, String appType, boolean local)
			throws Exception {
		StringBuffer errBuffer = new StringBuffer();
		String feedbackpath = getFeedbackPath(appType);
		if (!local) {
			String msg = readFeedbackFile(importdate, feedbackpath, appType);
			if ("".equals(msg)) {
				return "读取反馈可能出现异常，请联系IT人员处理。";
			}
			if (msg.indexOf("服务器下没有") >= 0) {
				return msg;
			}
			if (msg.indexOf("没有") >= 0 && msg.indexOf("LOG记录") >= 0) {
				return msg;
			}
		}
		return parseFeedbackFile(importdate, appType, errBuffer, feedbackpath);
	}

	/**
	 * 解析应用服务器下反馈文件，依据MTS发送文件LOG内容
	 * @param importdate 上报日期
	 * @param appType 模块名称 ACC/BOP/JSH
	 * @param errBuffer 提示消息
	 * @param feedbackpath  反馈文件路径
	 * @return 提示消息
	 * @throws FileNotFoundException
	 * @throws ParseException
	 * @throws IOException
	 */
	private String parseFeedbackFile(Date importdate, String appType,
			StringBuffer errBuffer, String feedbackpath)
			throws FileNotFoundException, ParseException, IOException {
		String date = DateUtils.dateToStr(importdate, Constants.DATEFORMAT);

		File file = new File(feedbackpath);
		if (file.isDirectory()) {
			File[] files = file.listFiles();// 24位控制文件夹列表
			if (null == files || files.length == 0) {
				errBuffer.append("日期[").append(date)
						.append("]下还没有任何反馈信息,请稍后重新读取.");
				return errBuffer.toString();
			}
			List<FeedbackloginfoEntity> flogs = getSentFilenames(importdate,
					appType);
			String[] filenames = getReadedFilenames(flogs);//已经读取过的反馈文件
			String savetfile = "";
			for (File f : files) {// 循环读取N个控制目录
				if (f.getName().indexOf(
						DateUtils.dateToStr(importdate, "yyMMdd")) != -1) {

					if (f.isDirectory()) {
						if (filenames.length > 0) {
							for (String fn : filenames) {
								if (f.getName().trim().indexOf(fn.trim()) >= 0) {
									errBuffer.append("批次[").append(fn.trim())
											.append("]已经读取过.");
									continue;
								} else {
									savetfile = saveFeedback(importdate,
											appType, savetfile, f);
								}
							}
						} else {
							savetfile = saveFeedback(importdate, appType,
									savetfile, f);
						}
					}
				}

			}
			if (savetfile.length() > 2) {
				//更新反馈文件记录中的反馈状态
				updateLogState(flogs, savetfile);
				//将读取完成的反馈文件保存
				errBuffer.append("[").append(savetfile.replaceAll(",", ""))
						.append("]成功读取.");
			}
		}
		FileUtils.cleanDirectory(feedbackpath);
		FileUtils.cleanDirectory(getFeedbackPath("MTS"));
		return errBuffer.toString();
	}

	/**
	 * 更新已发送上报文件是否已读取反馈的状态
	 * @param flogs
	 * @param savetfile
	 * 本次处理的控制文件名（集合）
	 */
	private void updateLogState(List<FeedbackloginfoEntity> flogs,
			String savetfile) {
		for (FeedbackloginfoEntity flog : flogs) {
			if (StringUtils.contains(savetfile, flog.getFilename().trim())) {
				flog.setState("1");
				saveOrUpdate(flog);
			}
		}
	}

	/**
	 * 读取文件，保存反馈的公共信息和错误信息
	 * @param importdate
	 * @param appType
	 * @param savetfile
	 * @param f
	 * @return
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	private String saveFeedback(Date importdate, String appType,
			String savetfile, File f) throws FileNotFoundException,
			ParseException {
        //读取反馈控制文件
		FeedbackControlMsg controlMsg = getControlMsg(f.getPath(), appType);// 读取反馈控制文件
		if (null != controlMsg) {
			@SuppressWarnings("unused")
			//保存反馈头信息
			int flag = saveSuccessInfo(controlMsg, importdate);
			// 得到反馈控制文件中的FILES列表
			List<FeedbackControlFilename> filenameList = controlMsg
					.getFilenameList();
			if (null != filenameList && !filenameList.isEmpty()) {
				FeedbackDataMsg dataMsg = null;
				//根据反馈信息保存公共信息以及错误信息
				for (FeedbackControlFilename filename : filenameList) {
					dataMsg = FeedbackDataMsg.getInstance(f.getPath()
							+ File.separator + filename.getFilename());
					saveFeedbackInfo(dataMsg, filename.getFilename(),
							f.getName(), importdate);
				}
			}
			savetfile += f.getName();
		}
		return savetfile;
	}

	private String[] getReadedFilenames(List<FeedbackloginfoEntity> flogs) {
		String s = "";
		for (FeedbackloginfoEntity feedbackloginfoEntity : flogs) {
			if ("1".equals(feedbackloginfoEntity.getState())) {
				s += feedbackloginfoEntity.getFilename() + ",";
			}
		}
		return StringUtils.split(s, ",");
	}

	/**
	 * 根据反馈日志信息，获取反馈文件名称，并且将其保存
	 * @param importdate
	 * @param appType
	 * @param f
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void saveMTSLog(Date importdate, String appType, String f)
			throws IOException {
		List<String> fcontents = org.apache.commons.io.FileUtils
				.readLines(new File(f));
		FeedbackloginfoEntity fle = null;
		List<FeedbackloginfoEntity> fles = new ArrayList<FeedbackloginfoEntity>();
		List filenames = getFieldValueByWhere(FeedbackloginfoEntity.class,
				"filename", " importdate = ?  and apptype=?", new Object[] {
						importdate, appType });
		for (String string : fcontents) {
			fle = new FeedbackloginfoEntity();
			String[] strs = StringUtils.split(string, "<");
			for (String s : strs) {
				if (s.indexOf(DateUtils.dateToStr(importdate, "yyMMdd")) != -1) {
					s = s.replaceAll("<", "").replaceAll(">", "").trim();
					if (!filenames.contains(s)) {
						fle.setApptype(appType);
						fle.setImportdate(importdate);
						fle.setFilename(s);
						fle.setState("0");
						fles.add(fle);
					}
				}

			}
		}

		batchSave(fles);
	}

	/**
	 * 将反馈日志文件 从MTS服务器传到应用服务器
	 * @param rs
	 * @param feedbackpath
	 * @param logpath
	 * @param sendpath
	 * @return
	 * @throws Exception
	 */
	protected String readMtsLog(ReportsenttypeEntity rs, Date maxd)
			throws Exception {
		return ftpUtilManager.downloadFilesByDate(rs, maxd,
				getFeedbackPath("MTS"));
	}

	/**
	 * 得到某一日期下所有正确的信息列表
	 * 
	 * @param reportdate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, FeedbackheadinfoEntity> getSuccessInfoMap(
			Date importdate, String appType) {
		List<FeedbackheadinfoEntity> headinfoList = findHql(
				FeedbackheadinfoEntity.class,
				" importdate = ? and (currentfile=? or currentfile=?) and apptype=?",
				new Object[] { importdate, "T", "TT", appType });
		if (null == headinfoList || headinfoList.isEmpty()) {
			return new HashMap<String, FeedbackheadinfoEntity>();
		}
		Map<String, FeedbackheadinfoEntity> successInfoMap = new HashMap<String, FeedbackheadinfoEntity>();
		for (FeedbackheadinfoEntity headinfo : headinfoList) {
			successInfoMap.put(headinfo.getFilename(), headinfo);
		}
		return successInfoMap;
	}

	protected FeedbackControlMsg getControlMsg(String feedbackpath,
			String appType) throws FileNotFoundException, ParseException {
		FeedbackControlMsg controlMsg = null;
		File feedbackfile = new File(feedbackpath);
		File[] feedbackfiles = feedbackfile.listFiles();
		for (File f : feedbackfiles) {
			if (f.getName().indexOf(appType + "T") != -1) {
				controlMsg = FeedbackControlMsg.getInstance(f.getPath());
				controlMsg.setFilename(f.getName());
				break;
			}
		}

		return controlMsg;
	}

	public List<String> getFeedbackFilenames(Date reportdate, String appType) {
		List<String> resultList = new ArrayList<String>();
		List<FeedbackheadinfoEntity> list = findHql(
				FeedbackheadinfoEntity.class, " importdate = ?  and apptype=?",
				new Object[] { reportdate, appType });

		if (null != list && !list.isEmpty()) {
			for (FeedbackheadinfoEntity headinfo : list) {
				resultList.add(headinfo.getFilename());
			}
		}
		return resultList;
	}

	public List<FeedbackloginfoEntity> getSentFilenames(Date reportdate,
			String appType) {
		return findHql(FeedbackloginfoEntity.class,
				" importdate = ?  and apptype=?", new Object[] { reportdate,
						appType });
	}
	/**
	 *    获取loginfo表是未读取过反馈的记录 WF(waiting feedback)
 
	 * @return
	 */
	public List<FeedbackloginfoEntity> getSentFilenamesWF() {
		return findHql(FeedbackloginfoEntity.class,
				" state<>?", new Object[] { "1"});
	}

	
	/**
	 * 根据反馈信息获取公共信息，格式错误信息，数据错误信息
	 * @param feedbackDataMsg
	 * @param fileName
	 * @param tfilename
	 * @param reportdate
	 * @return
	 */
	public int saveFeedbackInfo(FeedbackDataMsg feedbackDataMsg,
			String fileName, String tfilename, Date reportdate) {
		FeedbackheadinfoEntity headinfo = new FeedbackheadinfoEntity();// SAFE反馈文件公共信息
		List<FeedbackformatinfoEntity> formatinfoList = null;// SAFE反馈文件格式错误信息列表
		List<FeedbackerrorinfoEntity> errorinfoList = null;// SAFE反馈文件数据错误信息列表
		try {
			BeanUtils.copyProperties(headinfo, feedbackDataMsg);
			headinfo.setState("0");

			headinfo.setImportdate(reportdate);
			headinfo.setInouttype(feedbackDataMsg.getInout());
			headinfo.setFilename(StringUtils.substringBefore(fileName, "ERR"));
			headinfo.setTfilename(StringUtils.substringBefore(tfilename, "ERR"));
			if (headinfo.getCurrentfile().indexOf(feedbackDataMsg.getApptype()) != -1)
				headinfo.setCurrentfile(StringUtils.replace(headinfo.getCurrentfile(), headinfo.getApptype(), ""));
			formatinfoList = getFormatinfoList(feedbackDataMsg, headinfo);
			errorinfoList = getErrorinfoList(feedbackDataMsg, headinfo);
			return saveFeedbackInfoToDB(headinfo, formatinfoList, errorinfoList);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 从反馈中获取格式错误信息
	 * @param feedbackDataMsg
	 * @param fbh
	 * @return
	 */
	private List<FeedbackformatinfoEntity> getFormatinfoList(
			FeedbackDataMsg feedbackDataMsg, FeedbackheadinfoEntity fbh) {
		List<FeedbackformatinfoEntity> formatinfoList = new ArrayList<FeedbackformatinfoEntity>();
		FeedbackformatinfoEntity formatinfo = null;
		if (null != feedbackDataMsg.getFormats()
				&& !feedbackDataMsg.getFormats().isEmpty()) {
			for (FeedbackDataFormat format : feedbackDataMsg.getFormats()) {// 循环取得格式错误信息
				formatinfo = new FeedbackformatinfoEntity();
				formatinfo.setFormat(format.getFormat());
				formatinfo.setProcessed("0");
				formatinfoList.add(formatinfo);
			}
		}
		return formatinfoList;
	}

	/**
	 * 根据反馈信息获取数据错误信息
	 * @param feedbackDataMsg
	 * @param fbh
	 * @return
	 * @throws Exception
	 */
	private List<FeedbackerrorinfoEntity> getErrorinfoList(
			FeedbackDataMsg feedbackDataMsg, FeedbackheadinfoEntity fbh)
			throws Exception {
		List<FeedbackerrorinfoEntity> errorinfoList = new ArrayList<FeedbackerrorinfoEntity>();
		FeedbackerrorinfoEntity errorinfo = null;
		if (null != feedbackDataMsg.getErrrecords()
				&& !feedbackDataMsg.getErrrecords().isEmpty()) {
			for (FeedbackDataRec rec : feedbackDataMsg.getErrrecords()) {// 循环取得数据错误信息
				if (null != rec.getErrfields() && !rec.getErrfields().isEmpty()) {
					for (FeedbackDataErr err : rec.getErrfields()) {
						errorinfo = new FeedbackerrorinfoEntity();
						errorinfo.setRptno(rec.getRptno());
						errorinfo.setProcessed("0");
						errorinfo.setFbh(fbh);
						if ("".equals(rec.getRptno()))
							errorinfo.setRptno(StringUtils.replace(
									rec.getRptno(), ",", ""));
						BeanUtils.copyProperties(errorinfo, err);
						errorinfoList.add(errorinfo);
					}
				}
			}
		}
		return errorinfoList;
	}

	/**
	 * 保存公共信息，反馈错误信息
	 * @param headinfo
	 * @param formatinfoList
	 * @param errorinfoList
	 * @return
	 */
	private int saveFeedbackInfoToDB(FeedbackheadinfoEntity headinfo,
			List<FeedbackformatinfoEntity> formatinfoList,
			List<FeedbackerrorinfoEntity> errorinfoList) {
		try {
			save(headinfo);
			if (formatinfoList.size() > 0)
				batchSave(formatinfoList);
			if (errorinfoList.size() > 0) {
				batchSave(errorinfoList);
				// updateBOPBaseInfo(errorinfoList);
			}
		} catch (Exception e) {// 数据回滚
			e.printStackTrace();
			delete(headinfo);
			deleteAllEntitie(formatinfoList);
			deleteAllEntitie(errorinfoList);
			return -1;
		}
		return errorinfoList.size();
	}

	public int saveSuccessInfo(FeedbackControlMsg controlMsg, Date reportdate) {

		List<FeedbackheadinfoEntity> list = findHql(
				FeedbackheadinfoEntity.class, " importdate = ? and filename=?",
				new Object[] { reportdate, controlMsg.getFilename() });
		if (null != list && !list.isEmpty()) {
			return -1;
		} else {
			FeedbackheadinfoEntity headinfo = new FeedbackheadinfoEntity();
			headinfo.setApptype(controlMsg.getApptype());
			headinfo.setCurrentfile(StringUtils.replace(controlMsg.getCurrentfile(), controlMsg.getApptype(), ""));
			headinfo.setFalrecords(0);
			headinfo.setFilename(StringUtils.substringBefore(
					controlMsg.getFilename(), "ERR"));
			headinfo.setTfilename(StringUtils.substringBefore(
					controlMsg.getFilename(), "ERR"));
			headinfo.setFormaterrs(0);
			headinfo.setImportdate(reportdate);
			headinfo.setInouttype(controlMsg.getInout());
			headinfo.setSucrecords(0);
			headinfo.setTotalrecords(0);
			headinfo.setState((Integer.parseInt(controlMsg.getTotalfiles()) > 0) ? "0"
					: "1");
			save(headinfo);
			return 1;
		}
	}

	@Override
	public void deleteFeedbackError(Object obj) {

		try {
			CriteriaQuery cq = new CriteriaQuery(FeedbackerrorinfoEntity.class);

			cq.eq("rptno", (String) PropertyUtils.getProperty(obj, "rptno"));
			cq.createAlias("fbh", "fbh");
			cq.eq("fbh.filename",
					(String) PropertyUtils.getProperty(obj, "filename"));
			cq.eq("fbh.tfilename", (String) PropertyUtils.getProperty(obj, "tfilename"));

			cq.add();
			List<FeedbackerrorinfoEntity> fkes = getListByCriteriaQuery(cq,
					false);

			for (FeedbackerrorinfoEntity feedbackerrorinfoEntity : fkes) {
				feedbackerrorinfoEntity.setProcessed("1");
				feedbackerrorinfoEntity.getFbh().setState("1");
			}

			if (null != fkes) {
				batchSave(fkes);
			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

	}

	public FTPClientUtil getFtpUtilManager() {
		return ftpUtilManager;
	}

	public void setFtpUtilManager(FTPClientUtil ftpUtilManager) {
		this.ftpUtilManager = ftpUtilManager;
	}

	@Override
	public String readFeedback(Date importdate, String brca, String appType,
			boolean local) throws Exception {
		// TODO Auto-generated method stub
		return readFeedback(importdate, appType, local);
	}

}
