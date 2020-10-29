package com.lzsoft.service.prevalidate;

import java.io.File;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

 
import com.lzsoft.common.Constants;
import com.lzsoft.util.LogUtil;
@Service(value = "accValidateMainImpl")
public class AccValidateMainImpl   {/*
	

	private static PropertiesConfiguration pc = null;
	private static String logPath = null;
	public int autovalidate() throws Exception{
		
		try {
			pc = new PropertiesConfiguration(Constants.DATA_FILES_PATH);
			logPath = pc.getString("logpath");
			DataFileReadFacade read = new DataFileReadFacade();
			read.readDataFile();
			LogUtil.generateLog(logPath, "校验完成，查看feedback文件夹");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.generateLog(logPath, "校验发生异常，查看feedback文件夹");
			return 0;
		}
		return 1;
	}
	public  String getSendPath() {
		return GlobalConfig.getSendPath();
	}
	public  String getFeedbackPath() {
		return GlobalConfig.getFeedbackPath();
	}
	public String readValidateResult(String filename) throws Exception{
		File file = new File(getFeedbackPath());
		int count=0;//反馈文件夹计数
		if (file.isDirectory()) {
			File[] files = file.listFiles();// 24位控制文件夹列表
			if (null == files || files.length == 0) {
				LogUtil.generateLog(logPath, DateUtil.getCurrentDate()+"预校验无反馈文件，请查看"+getSendPath()+"目录。");
				return getSendPath();
			}
			
			for (File f : files) {// 循环读取N个控制目录
				if (f.isDirectory()) {
					if(f.getName().indexOf(filename)>=0){
						File feedbackfile = new File(f.getPath());
						File[] feedbackfiles = feedbackfile.listFiles();
						if (null != feedbackfiles && feedbackfiles.length==1) {
							
							//判断csend目录下是否已经为空
							File[] cfiles =new File(getSendPath()).listFiles();
									if(null != cfiles && cfiles.length > 0){
										LogUtil.generateLog(logPath, filename+"预校验只有一个反馈文件，但"+getSendPath()+"目录不为空。"+DateUtil.getCurrentDate());
										return getSendPath();
									}
							if(StringUtils.substringAfterLast(feedbackfiles[0].getName(),File.separator).indexOf("T")>=0){
								count++;
								LogUtil.generateLog(logPath, filename+"预校验成功。"+DateUtil.getCurrentDate());
								return "";
							}
						}else{
							LogUtil.generateLog(logPath, filename+"预校验失败。"+DateUtil.getCurrentDate());
							return f.getPath();
						}
					}
				}
			}
		}
		if(count==0){
			return "";
		}else{
			LogUtil.generateLog(logPath, "没有与"+filename+"对应的校验结果。"+DateUtil.getCurrentDate());
			return getFeedbackPath();
		}
	}
*/}
