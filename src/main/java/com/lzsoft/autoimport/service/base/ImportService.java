package com.lzsoft.autoimport.service.base;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

import java.io.File;

public class ImportService {
	protected Configuration pc = null;
	String sourcePath="";
	String rootPath ="";
	String postDate="";
	public Configuration getPc() {
		return pc;
	}


	public String getSourcePath() {
		return sourcePath;
	}


	public ImportService() throws ConfigurationException {
		super();
		try {
			pc = new PropertiesConfiguration(
					"config/importdata/importdata.properties");
//			 sourcePath = GetSystemConfig.getInstance().getStringFromSourceFile("SourcPath");
//			 postDate = GetSystemConfig.getInstance().getStringFromSourceFile("PostDateFile");
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	private String getRootPath() {
		String rootPath = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getFile();
		rootPath = rootPath.substring(1);
		if (rootPath.indexOf("WEB-INF") != -1) {
			rootPath = rootPath.substring(0, rootPath.indexOf("WEB-INF"));
		} else if (rootPath.indexOf("bin") != -1) {
			rootPath = rootPath.substring(0, rootPath.indexOf("bin"))
					+ "src/";
		} else if (rootPath.indexOf("classes") != -1) {
			rootPath = rootPath.substring(0, rootPath.indexOf("classes"));
		} else if (rootPath.toLowerCase().endsWith(".exe")
				|| rootPath.toLowerCase().endsWith(".jar")) {
			rootPath = StringUtils.substringBeforeLast(rootPath, "/");
			rootPath = StringUtils.substringBeforeLast(rootPath, "\\")
					+ File.separator ;

		}
		/*
		 * if (rootPath.indexOf("exe") != -1) { rootPath = rootPath
		 * .substring(0, rootPath.indexOf("autoimport.exe")) + "config/"; }
		 */
		rootPath = rootPath.replaceAll("/", "\\\\");

		return rootPath.replaceAll("%20", " ");
}

	public String getPostDate() {
		File ilDir = new File(postDate);
//		return ilDir.list()[0].substring((ilDir.list()[0].lastIndexOf(".")-8),ilDir.list()[0].lastIndexOf("."));
		String datee  = ilDir.list()[0].substring((ilDir.list()[0].lastIndexOf(".")-8),ilDir.list()[0].lastIndexOf("."));
		return datee;
	}
	
}
