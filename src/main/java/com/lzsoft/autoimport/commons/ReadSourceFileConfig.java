package com.lzsoft.autoimport.commons;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.DateUtils;

public class ReadSourceFileConfig {

	/**
	 * 
	 * 
	 * @param tableName
	 * @return
	 */
	public static Map<String, String> getSourcefileConfig(Date transDate,
			String sourcePath) throws Exception {
		Configuration pc = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			File sourcePathDir = new File(sourcePath);
			File[] sourceFileList = sourcePathDir.listFiles();
			pc = new PropertiesConfiguration(
					"config/importdata/sourcemapping.properties");
			Object key = null;
			String sourcefile = null;
			String[] resourceMappingList = null;
			for (Iterator iterator = pc.getKeys(); iterator.hasNext();) {
				key = (Object) iterator.next();
				sourcefile = pc.getString(key.toString());
				resourceMappingList = sourcefile.split("\\|");
				map.put(key.toString(), getMatchSourceFileList(transDate,
						sourceFileList, resourceMappingList));
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return map;
	}

	private static String getMatchSourceFileList(Date transDate,
			File[] resourceFileList, String[] resourceMappingList)
			throws Exception {
		try {
			String dateInFilename = DateUtils.dateToStr(transDate, "yyyyMMdd");
			for (String resourceMapping : resourceMappingList) {
				for (File resourceFile : resourceFileList) {
					if(isMatch(resourceMapping, resourceFile.getName()))
						return resourceFile.getPath();
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private static boolean isMatch(String resourceMapping,String resourceFile) throws Exception {
		try {
			String suffix=StringUtils.substringBeforeLast(resourceFile, ".");
//			if(suffix.length()==6){
//				return resourceMapping.equalsIgnoreCase(resourceFile.substring(0,19));
//			}else{
				return resourceMapping.equalsIgnoreCase(suffix);
//			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

//	public static String getFileName(String filename) {
//		int count = 0;
//		for (int i = filename.length(); i > 0; i--) {
//			if (filename.substring(i - 1, i).equalsIgnoreCase("_")) {
//				count++;
//				if (count == 1) {
//					filename = filename.substring(0, i - 1);
//					break;
//				}
//			}
//		}
//		int count1 = 0;
//		for (int i = filename.length(); i > 0; i--) {
//			if (filename.substring(i - 1, i).equalsIgnoreCase("_")) {
//				count1++;
//				if (count1 == 1) {
//					filename = filename.substring(0, i - 1);
//					break;
//				}
//			}
//		}
//		
//		for (int i = filename.length(); i > 0; i--) {
//			if (filename.substring(i - 1, i).equalsIgnoreCase("\\")) {
//				filename = filename.substring(i, filename.length());
//				break;
//			}
//		}
//		return filename;
//	}
	
	public static String getFileName(String filename) {
		int count = 0;
		for (int i = filename.length(); i > 0; i--) {
			if (filename.substring(i - 1, i).equalsIgnoreCase(".")) {
				count++;
				if (count == 1) {
					filename = filename.substring(0, i - 1);
					break;
				}
			}
		}
		
		for (int i = filename.length(); i > 0; i--) {
			if (filename.substring(i - 1, i).equalsIgnoreCase("\\")) {
				filename = filename.substring(i, filename.length());
				break;
			}
		}
		return filename;
	}
}