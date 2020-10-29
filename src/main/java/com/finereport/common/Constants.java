package com.finereport.common;

import java.io.File;

public class Constants {

	/**
	 * 日期类型
	 */
	public static final String JAVA_DATE_TYPE = "java.util.Date";
	/**
	 * 数字类型
	 */
	public static final String JAVA_DOUBLE_TYPE = "java.lang.Double";
	/**
	 * 数字类型
	 */
	public static final String JAVA_BIGDECIMAL_TYPE = "java.math.BigDecimal";


	/**
	 * ACC报表路径
	 */
	public static final String ACCXMLPATHNAME = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "xml"
			+ File.separator;
	/**
	 * ACC报表路径
	 */
	public static final String ACCZIPPATHNAME = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "zip"
			+ File.separator;

	/**
	 * ACC报送文件目录
	 */
	public static final String ACCRENDERPATH = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "render"
			+ File.separator;

}
