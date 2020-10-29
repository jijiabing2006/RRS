package com.lzsoft.autoimport.service.impl;

import java.util.Date;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.autoimport.commons.Constants;
import com.lzsoft.entity.common.TaskscheduleEntity;

/**
 * @todo  导入summit数据
 *
 */
@Service(value = "summitImportData")
@Transactional
public class SummitImportDataImpl extends CommonServiceImpl {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(SummitImportDataImpl.class);

	private static PropertiesConfiguration pc = null;
	private static String kettelfilepath = null;

	public int autoImportDataToDB() {
		try {
			//查询未执行的最大营业日期
			Date tranDate = getMaxImportdate(TaskscheduleEntity.class,
					"importdate",
					"taskname='autoImportSmtData' and Executable='1'",
					new Object[] {});
			if (null != tranDate) {
				String tranDateStr = DateUtils.dateToStr(tranDate, "yyyy-MM-dd");
				// 开始导入数据
//				System.out.println("Start import Summit data.....Date:"+tranDateStr);
				logger.info("Start import Summit data.....Date:"+tranDateStr);
				pc = new PropertiesConfiguration(Constants.DATA_FILES_PATH);
				kettelfilepath = pc.getString("kettelfilepath");
				// 调用Kettle,导入Summit的数据
				importfromSummit(tranDate);
//				System.out.println("import Summit data Completed........");
				logger.info("import Summit data Completed........");
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(""+e.getMessage());
			// feedback(tranDate);
		}
		return 1;
	}

	private Date getMaxImportdate(Class clazz, String fieldname, String hql,
			Object[] params) {
		Object importdate = getMaxFieldValueByWhere(clazz, fieldname, hql, params);
		return (Date) importdate;
	}

	/**
	 * 调用Kettle,导入Summit的数据
	 * 
	 * @param tranDate
	 * @throws Exception
	 */
	private void importfromSummit(Date tranDate) throws Exception {
		String date = DateUtils.dateToStr(tranDate, "yyyyMMdd");
		String[] params = { date }; // 传递参数
		String path = kettelfilepath + "SummitToRRS.kjb";
		runJob(params, path);
	}

	/**
	 * 运行转换文件方法
	 * 
	 * @param params
	 *            多个参数变量值
	 * @param ktrPath
	 *            转换文件的路径，后缀ktr
	 */
	public static void runTransfer(String[] params, String ktrPath)
			throws Exception {
		Trans trans = null;
		// 初始化
		// 转换元对象
		KettleEnvironment.init();// 初始化
		EnvUtil.environmentInit();
		TransMeta transMeta = new TransMeta(ktrPath);
		// 转换
		trans = new Trans(transMeta);
		// 执行转换
		trans.execute(params);
		// 等待转换执行结束
		trans.waitUntilFinished();
		// 抛出异常
		if (trans.getErrors() > 0) {
			throw new Exception(
					"There are errors during transformation exception!(传输过程中发生异常)");
		}
	}

	/**
	 * java 调用 kettle 的job
	 * 
	 * @param jobname
	 *            如： String fName= "D:\\kettle\\informix_to_am_4.ktr";
	 */
	public static void runJob(String[] params, String jobPath) throws Exception {
		KettleEnvironment.init();
		// jobname 是Job脚本的路径及名称
		JobMeta jobMeta = new JobMeta(jobPath, null);
		Job job = new Job(null, jobMeta);
		// 向Job 脚本传递参数，脚本中获取参数值：${参数名}
		// job.setVariable(paraname, paravalue);
		job.setVariable("inputdate", params[0]);
		// job.setVariable("dt", params[1]);
		job.start();
		job.waitUntilFinished();
		if (job.getErrors() > 0) {
			throw new Exception(
					"There are errors during job exception!(执行job发生异常)");
		}
	}
}