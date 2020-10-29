package com.lzsoft.common;

import java.io.File;

public class Constants {

	public static final String HQBANKCODE = "000000";
	public static final int DEFAULT_CCY_LENGTH = 3;
	public static final String ROOT_PATH = null;
	public static final int NUM_ZERO = 0;
	public static final int SUBSTRING_FIRST_INDEX = 0;
	public static final int NUM_ONE = 1;
	public static final String DATEFORMAT = "yyyy-MM-dd";
	public static final String BOP_LAST_FOUR = "****";
	public static final String JSH_LAST_FOUR = "****";
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
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

	public static final String DATA_FILES_PATH = "config" + File.separator
			+ "importdata" + File.separator + "filepath.properties";

	public static final String AUDITIMPORT_PATH = "config" + File.separator
			+ "importdata" + File.separator + "etlmapping.properties";

	/**
	 * 
	 */

	public static final String ACCOUNTTYPEPATH = "config" + File.separator
			+ "accounttype" + File.separator + "accounttype.properties";

	public static final String BOP_RPTNO_DATEFORMAT = "yyMMdd";
	public static final String JSH_RPTNO_DATEFORMAT = "yyMMdd";

	/**
	 * ACC报表路径
	 */
	public static final String ACCXMLPATHNAME = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "xml"
			+ File.separator;
	/**
	 * BOP报表路径
	 */
	public static final String BOPXMLPATHNAME = "report" + File.separator
			+ "bop" + File.separator + "output" + File.separator + "xml"
			+ File.separator;
	/**
	 * JSH报表路径
	 */
	public static final String JSHXMLPATHNAME = "report" + File.separator
			+ "jsh" + File.separator + "output" + File.separator + "xml"
			+ File.separator;
	/**
	 * ACC报表路径
	 */
	public static final String ACCZIPPATHNAME = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "zip"
			+ File.separator;
	/**
	 * BOP报表路径
	 */
	public static final String BOPZIPPATHNAME = "report" + File.separator
			+ "bop" + File.separator + "output" + File.separator + "zip"
			+ File.separator;
	/**
	 * JSH报表路径
	 */
	public static final String JSHZIPPATHNAME = "report" + File.separator
			+ "jsh" + File.separator + "output" + File.separator + "zip"
			+ File.separator;
	/**
	 * ACC令牌文件目录
	 */
	public static final String ACCTOKENFILEPATH = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "token"
			+ File.separator;
	/**
	 * BOP令牌文件目录
	 */
	public static final String BOPTOKENFILEPATH = "report" + File.separator
			+ "bop" + File.separator + "output" + File.separator + "token"
			+ File.separator;
	/**
	 * JSH令牌文件目录
	 */
	public static final String JSHTOKENFILEPATH = "report" + File.separator
			+ "jsh" + File.separator + "output" + File.separator + "token"
			+ File.separator;

	/**
	 * ACC报送文件目录
	 */
	public static final String ACCRENDERPATH = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "render"
			+ File.separator;
	/**
	 * BOP报送文件目录
	 */
	public static final String BOPRENDERPATH = "report" + File.separator
			+ "bop" + File.separator + "output" + File.separator + "render"
			+ File.separator;
	/**
	 * JSH报送文件目录
	 */
	public static final String JSHRENDERPATH = "report" + File.separator
			+ "jsh" + File.separator + "output" + File.separator + "render"
			+ File.separator;
	/**
	 * BOP反馈文件目录
	 */
	public static final String BOPFEEDBACKPATH = "report" + File.separator
			+ "bop" + File.separator + "output" + File.separator + "feedback"
			+ File.separator;
	/**
	 * ACC反馈文件目录
	 */
	public static final String ACCFEEDBACKPATH = "report" + File.separator
			+ "acc" + File.separator + "output" + File.separator + "feedback"
			+ File.separator;
	/**
	 * JSH反馈文件目录
	 */
	public static final String JSHFEEDBACKPATH = "report" + File.separator
			+ "jsh" + File.separator + "output" + File.separator + "feedback"
			+ File.separator;
	/**
	 * MTS log目录
	 */
	public static final String MTSLOGPATH = "report" + File.separator + "mts"
			+ File.separator + "log" + File.separator;
	public static final int EXCEL_EXPORT_LINES = 10000;

	/**
	 * 基础信息
	 */
	public static final String[] BOP_BASE_TYPE = new String[] { "A", "B", "C","D", "E", "F" };
	public static final String[] JSH_BASE_TYPE = new String[] { "D", "E" };
	public static final String[] ACC_BASE_TYPE = new String[] { "CA", "CB" };

	/**
	 * 申报信息
	 */
	public static final String[] BOP_DECLARE_TYPE = new String[] { "G", "H","K" };

	/**
	 * 管理信息
	 */
	public static final String[] BOP_CONTROL_TYPE = new String[] { "N", "P","Q", "R", "S" };
	public static final String[] JSH_CONTROL_TYPE = new String[] { "F", "G" };

}
