package com.lzsoft.service.safe.impl;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.ReportindexEntity;
import com.lzsoft.entity.common.ReportsenttypeEntity;
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
import com.lzsoft.service.prevalidate.AccValidateMainImpl;
import com.lzsoft.service.prevalidate.BopValidateMainImpl;
import com.lzsoft.service.prevalidate.JshValidateMainImpl;
import com.lzsoft.service.report.util.AccXmlUtil;
import com.lzsoft.service.report.util.BopXmlUtil;
import com.lzsoft.service.report.util.JshXmlUtil;
import com.lzsoft.service.report.util.SafeXmlUtil;
import com.lzsoft.util.FTPClientUtil;
import com.lzsoft.util.Rootpath;
import com.lzsoft.util.ZipUtil;

public class SafeBaseService extends BaseService {

	@Autowired
	protected AccValidateMainImpl accValidateMainImpl;
	@Autowired
	protected BopValidateMainImpl bopValidateMainImpl;
	@Autowired
	protected JshValidateMainImpl jshValidateMainImpl;

	/**
	 * FTP工具类
	 */
	@Autowired
	protected FTPClientUtil ftpUtilManager;

	protected String accXmlPath = Rootpath.getAppPath()
			+ Constants.ACCXMLPATHNAME;
	protected String accZipPath = Rootpath.getAppPath()
			+ Constants.ACCZIPPATHNAME;
	protected String jshXmlPath = Rootpath.getAppPath()
			+ Constants.JSHXMLPATHNAME;
	protected String jshZipPath = Rootpath.getAppPath()
			+ Constants.JSHZIPPATHNAME;
	protected String bopXmlPath = Rootpath.getAppPath()
			+ Constants.BOPXMLPATHNAME;
	protected String bopZipPath = Rootpath.getAppPath()
			+ Constants.BOPZIPPATHNAME;

	public SafeBaseService() {
		super();
	}

	protected String getZipXmlPath(String accType, String type) {
		String path = "";
		if ("ACC".equalsIgnoreCase(accType)) {
			path = "Z".equalsIgnoreCase(type) ? accZipPath : accXmlPath;
		} else if ("BOP".equalsIgnoreCase(accType)) {
			return "Z".equalsIgnoreCase(type) ? bopZipPath : bopXmlPath;
		} else if ("JSH".equalsIgnoreCase(accType)) {
			path = "Z".equalsIgnoreCase(type) ? jshZipPath : jshXmlPath;
		}
		FileUtils.newFolder(path);
		return path;
	}

	/**
	 * 根据appType返回对应的反馈文件存放路径
	 * 
	 * @param appType
	 * @return
	 */

	protected String getFeedbackPath(String appType) {
		String feedbackpath = "";
		if ("BOP".equalsIgnoreCase(appType)) {
			feedbackpath = Rootpath.getAppPath() + Constants.BOPFEEDBACKPATH;
		} else if ("JSH".equalsIgnoreCase(appType)) {
			feedbackpath = Rootpath.getAppPath() + Constants.JSHFEEDBACKPATH;
		} else if ("ACC".equalsIgnoreCase(appType)) {
			feedbackpath = Rootpath.getAppPath() + Constants.ACCFEEDBACKPATH;
		} else if ("MTS".equalsIgnoreCase(appType)) {
			feedbackpath = Rootpath.getAppPath() + Constants.MTSLOGPATH;
		}
		FileUtils.newFolder(feedbackpath);
		return feedbackpath;
	}

	protected ReportsenttypeEntity getReportSentType(String accType) {

		CriteriaQuery cq = new CriteriaQuery(ReportsenttypeEntity.class);
		cq.eq("type", accType);
		cq.eq("state", "1");
		cq.add();
		ReportsenttypeEntity reportsenttype = getUniqueObjectByCriteriaQuery(cq);

		if (null == reportsenttype) {
			return null;
		}
		if ("ACC".equalsIgnoreCase(accType)) {
			reportsenttype.setUploaddirpath(accXmlPath);
		} else if ("BOP".equalsIgnoreCase(accType)) {
			reportsenttype.setUploaddirpath(bopXmlPath);
		} else if ("JSH".equalsIgnoreCase(accType)) {
			reportsenttype.setUploaddirpath(jshXmlPath);
		}
		return reportsenttype;
	}

	/**
	 * 新建一个ReportIndex
	 * 
	 * @param currentDepart
	 * @param accType
	 * @return
	 */
	protected ReportindexEntity getNewReportIndex(BankinfoEntity bkinfo,
			String accType) {
		ReportindexEntity reportindex = new ReportindexEntity();
		reportindex.setBrca(bkinfo.getBrca());
		reportindex.setParentbrca(bkinfo.getParentbrca());
		reportindex.setImportdate(DateUtils
				.getCurrentDate(Constants.DATEFORMAT));
		reportindex.setApptype(accType);
		return reportindex;
	}

	/**
	 * 
	 * @param class 实体类
	 * @param querybean
	 *            查询类
	 * @param reportindex
	 *            报表序列类
	 */
	protected String getFileindex(Class<ReportindexEntity> clazz,
			ReportindexEntity reportindex) {

		String wheresql = "";
		Object[] paramter = null;
		if (reportindex.getReporttype().indexOf("T") >= 0) {
			wheresql = " parentbrca=? and importdate=? and reporttype=? and apptype=? ";// 为了防止分支行12位金融机构代码相同，BOPT序列号重复，T文件的序列号只判断到分行
			paramter = new Object[] { reportindex.getParentbrca(),
					reportindex.getImportdate(), reportindex.getReporttype(),
					reportindex.getApptype() };
		} else {
			wheresql = " parentbrca=? and importdate=? and reporttype=? and apptype=? ";
			paramter = new Object[] {
					reportindex.getParentbrca(), // 上报文件名也要不相同（因为支行使用了分行的金融机构代码）
					reportindex.getImportdate(), reportindex.getReporttype(),
					reportindex.getApptype() };
		}
		List<ReportindexEntity> list = findHql(ReportindexEntity.class,
				wheresql, paramter);

		if (null != list && !list.isEmpty())
			return getNextIndex(String.valueOf(list.size()));
		return "00";
	}

	private String getNextIndex(String reportIndex) {
		if (null != reportIndex && !"".equals(reportIndex)) {
			int index = Integer.parseInt(reportIndex);
			int nextIndex = index + 100;
			return String.valueOf(nextIndex).substring(1);
		}
		return "00";
	}

	protected void addTashSchdule(Date exportdate, String brca, String type) {

		TaskscheduleEntity ts = new TaskscheduleEntity();
		ts.setImportdate(exportdate);
		ts.setBrca(brca);
		ts.setExecutable(true);
		ts.setTaskdesc(type + "Waitting Feedback");
		ts.setTaskname(type.toLowerCase() + "WF");
		deleteAllEntitie(findHql(TaskscheduleEntity.class,
				"importdate=? and brca=? and taskname=?", new Object[] {
						exportdate, brca, type.toLowerCase() + "WF" }));

		save(ts);
	}

	public FTPClientUtil getFtpUtilManager() {
		return ftpUtilManager;
	}

	public void setFtpUtilManager(FTPClientUtil ftpUtilManager) {
		this.ftpUtilManager = ftpUtilManager;
	}

	/**
	 * 根据传入日期，返回taskSchedule
	 * 
	 * @param maxImportdate
	 * @return
	 */
	protected TaskscheduleEntity queryTaskSchedule(Date maxImportdate,
			String taskname) {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class);
		cq.eq("importdate", maxImportdate);
		cq.eq("taskname", taskname);
		cq.add();
		return getUniqueObjectByCriteriaQuery(cq);

	}

	protected void clearFolder(String accType) throws Exception {
		if ("ACC".equalsIgnoreCase(accType)) {
			FileUtils.delDir(accXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(accXmlPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(accXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(accZipPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(accZipPath.replaceAll("/", "\\\\"));
		} else if ("BOP".equalsIgnoreCase(accType)) {
			FileUtils.delDir(bopXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(bopXmlPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(bopXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(bopZipPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(bopZipPath.replaceAll("/", "\\\\"));
		} else if ("JSH".equalsIgnoreCase(accType)) {
			FileUtils.delDir(jshXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(jshXmlPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(jshXmlPath.replaceAll("/", "\\\\"));
			FileUtils.newFolder(jshZipPath.replaceAll("/", "\\\\"));
			FileUtils.cleanDirectory(jshZipPath.replaceAll("/", "\\\\"));
		}

	}

	protected SafeXmlUtil getXmlUtil(String accType) {
		SafeXmlUtil xmlUtil = null;
		if ("ACC".equalsIgnoreCase(accType)) {
			return new AccXmlUtil(accType);
		} else if ("BOP".equalsIgnoreCase(accType)) {
			return new BopXmlUtil(accType);
		} else if ("JSH".equalsIgnoreCase(accType)) {
			return new JshXmlUtil(accType);
		}
		return xmlUtil;
	}
	protected String getTfilechar(String accType) {
		if ("ACC".equalsIgnoreCase(accType)) {
			return "TT";
		} else if ("BOP".equalsIgnoreCase(accType)) {
			return "T";
		} else if ("JSH".equalsIgnoreCase(accType)) {
			return "T";
		}
		return "TT";
	}

	/**
	 * 
	 * @param download
	 * @param accType
	 * @param zipPathAndName
	 * @return 2 预校验完成，上报内容有错误 1 预校验完成，上报内容正确 3 预校验发生异常
	 * @throws Exception
	 */
	protected int preValidation(String accType, String zipPathAndName)
			throws Exception {
		if ("1".equals(getReportsentByType(accType, "1").getPrevalidate())) {// prevalidate==1说明需要执行SAFE预校验程序，根据校验结果返回状态值
			if ("ACC".equals(accType)) {
				return accPreValidation(zipPathAndName);
			} else if ("BOP".equals(accType)) {
				return bopPreValidation(zipPathAndName);
			} else if ("JSH".equals(accType)) {
				return jshPreValidation(zipPathAndName);
			}
		} else {// 没有执行预校验程序时，默认为通过预校验
			return 1;
		}
		return 0;
	}

	private int accPreValidation(String zipPathAndName) throws Exception {
		return 1;/*
		String controlName = StringUtils.substringBefore(
				StringUtils.substringAfterLast(zipPathAndName, "\\"), ".");
		FileUtils.delDir((accValidateMainImpl.getSendPath() + controlName)
				.replaceAll("/", "\\\\"));
		FileUtils.delDir((accValidateMainImpl.getFeedbackPath()).replaceAll(
				"/", "\\\\"));

		ZipUtil.extZipByFolderNoRootFolder(zipPathAndName,
				accValidateMainImpl.getSendPath() + controlName, "");
		int result = accValidateMainImpl.autovalidate();
		if (1 == result) {
			String feedbackpath = accValidateMainImpl
					.readValidateResult(controlName);
			if (!"".equals(feedbackpath)) {
				// 错误反馈打包下载，清除反馈目录，清除原上报XML目录，备份原上报ZIP目录
				// System.out.println("打包");
				FileUtils.moveDirectory(
						feedbackpath,
						getFeedbackPath("ACC")
								+ StringUtils.substringAfterLast(feedbackpath,
										File.separator));
				return 2;
			}
			return 1;
		}
		return 3;
	*/}

	/**
	 * 
	 * @param bopZipPathAndName
	 * @return 2 预校验完成，上报内容有错误 1 预校验完成，上报内容正确 3 预校验发生异常
	 * @throws Exception
	 */
	private int bopPreValidation(String bopZipPathAndName) throws Exception {return 1;/*
		String controlName = StringUtils.substringBefore(
				StringUtils.substringAfterLast(bopZipPathAndName, "\\"), ".");
		FileUtils.delDir((bopValidateMainImpl.getSendPath() + controlName)
				.replaceAll("/", "\\\\"));
		FileUtils.delDir((bopValidateMainImpl.getFeedbackPath()).replaceAll(
				"/", "\\\\"));

		ZipUtil.extZipByFolderNoRootFolder(bopZipPathAndName,
				bopValidateMainImpl.getSendPath() + controlName, "");
		int result = bopValidateMainImpl.autovalidate();
		if (1 == result) {
			String feedbackpath = bopValidateMainImpl
					.readValidateResult(controlName);
			if (!"".equals(feedbackpath)) {
				// 错误反馈打包下载，清除反馈目录，清除原上报XML目录，备份原上报ZIP目录
				// System.out.println("打包");
				FileUtils.moveDirectory(
						feedbackpath,
						getFeedbackPath("BOP")
								+ StringUtils.substringAfterLast(feedbackpath,
										File.separator));
				return 2;
			}
			return 1;
		}
		return 3;
	*/}

	/**
	 * 
	 * @param download
	 * @param jshZipPathAndName
	 * @return 2 预校验完成，上报内容有错误 1 预校验完成，上报内容正确 3 预校验发生异常
	 * @throws Exception
	 */
	private int jshPreValidation(String jshZipPathAndName) throws Exception {return 1;/*
		String controlName = StringUtils.substringBefore(
				StringUtils.substringAfterLast(jshZipPathAndName, "\\"), ".");
		FileUtils.delDir((jshValidateMainImpl.getSendPath() + controlName)
				.replaceAll("/", "\\\\"));
		FileUtils.delDir((jshValidateMainImpl.getFeedbackPath()).replaceAll(
				"/", "\\\\"));

		ZipUtil.extZipByFolderNoRootFolder(jshZipPathAndName,
				jshValidateMainImpl.getSendPath() + controlName, "");
		int result = jshValidateMainImpl.autovalidate();
		if (1 == result) {
			String feedbackpath = jshValidateMainImpl
					.readValidateResult(controlName);
			if (!"".equals(feedbackpath)) {
				// 错误反馈打包下载，清除反馈目录，清除原上报XML目录，备份原上报ZIP目录
				// System.out.println("打包");
				FileUtils.moveDirectory(
						feedbackpath,
						getFeedbackPath("JSH")
								+ StringUtils.substringAfterLast(feedbackpath,
										File.separator));
				return 2;
			}
			return 1;
		}
		return 3;
	*/}

	protected void init() {
		initBeanMap();
	}

	@SuppressWarnings("rawtypes")
	protected Map<String, Class> beanMap;

	@SuppressWarnings("rawtypes")
	private void initBeanMap() {
		if ( null == beanMap || beanMap.keySet().size() !=20) {
			beanMap = Collections.synchronizedMap(new HashMap<String, Class>());
			beanMap.put("ACCCA", AccCAEntity.class);
			beanMap.put("ACCCB", AccCBEntity.class);
			beanMap.put("BOPA", BopAEntity.class);
			beanMap.put("BOPB", BopBEntity.class);
			beanMap.put("BOPC", BopCEntity.class);
			beanMap.put("BOPD", BopDEntity.class);
			beanMap.put("BOPE", BopEEntity.class);
			beanMap.put("BOPF", BopFEntity.class);

			beanMap.put("BOPG", BopGEntity.class);
			beanMap.put("BOPH", BopHEntity.class);
			beanMap.put("BOPK", BopKEntity.class);

			beanMap.put("BOPN", BopNEntity.class);
			beanMap.put("BOPP", BopPEntity.class);
			beanMap.put("BOPQ", BopQEntity.class);
			beanMap.put("BOPR", BopREntity.class);
			beanMap.put("BOPS", BopSEntity.class);
			
			beanMap.put("JSHD", JshDEntity.class);
			beanMap.put("JSHE", JshEEntity.class);
			beanMap.put("JSHF", JshFEntity.class);
			beanMap.put("JSHG", JshGEntity.class);
		}
	}
}