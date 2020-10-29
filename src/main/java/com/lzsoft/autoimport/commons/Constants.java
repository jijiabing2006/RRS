package com.lzsoft.autoimport.commons;

import java.io.File;

/**
 * 
 * @function
 * @author
 * @time
 * @last modification by
 * @version 1.0
 */
public class Constants {

	/**
	 * 
	 */
	public static final String CHARSETCONFIGFILEPATH = "config\\charsetConfig.properties";

	/**
	 * 
	 */
	public static final String DBCHARSET = "databaseCharset";

	/**
	 * 
	 */
	public static final String PAGECHARSET = "pageCharset";

	/**
	 * 
	 */
	public static final String UNZIPPATH = "upload/importdata/";

	public static final String AUTOIMPORT_ERRORINFO_PATH = "logs/amoutimportlog/";

	public static final String DATEFORMAT = "yyyy-MM-dd";

	public static final String HQBANKCODE = "000000";

	public static final String DEFAULT_CNUM = "000000";
	/**
	 * 
	 */
	public static final int DEFAULT_CCY_LENGTH = 3;

	/**
	 * 
	 */
	public static final String INDUSTYBOPCODEPATH = "config/industrycodebopmapping.properties";

	/**
	 *
	 */
	public static final String INDUSTYPBOCCODEPATH = "config/industrycodepbocmapping.properties";

	/**
	 * 
	 */
	public static final String INDUSTYLOANCODEPATH = "config/industrycodeloanmapping.properties";

	/**
	 *
	 */
	public static final String PAYMETHODONEPATH = "config/paymethodonemapping.properties";

	/**
	 * 
	 */
	public static final String PAYMETHODTWOPATH = "config/paymethodtwomapping.properties";

	/**
	 * �Ƿ�Ϊnostr�˺ŵ������ļ�·��
	 */
	public static final String NOSTRACCOUNTPATH = "config/nostraccount.properties";
	/**
	 * suspense
	 */
	public static final String SUSPENSEACCOUNTPATH = "config/suspenseaccount.properties";

	/**
	 * DRCR
	 */
	public static final String PRODUCTCODETODRCRPATH = "config/productcodetodrcr.properties";
	/**
	 * Fee Mapping
	 */
	public static final String FEEPATH = "config/fee.properties";

	/**
	 * CITY Mapping
	 */
	public static final String CITYPATH = "config/city.properties";

	public static final String DATEFORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String[] DATEFORMATS = { DATEFORMAT,
			DATEFORMAT_YYYYMMDD };

	public static final String ROOT_PATH = null;

	public static final int NUM_ZERO = 0;

	public static final int SUBSTRING_FIRST_INDEX = 0;

	public static final int NUM_ONE = 1;

	public static final int DEFAULT_SCALE = 8;
	/**
	 * 是否可以导入的控制文件
	 */
	public static final String CNSH_FILE = "CNSH.flg";
	/**
	 * 可疑交易的日志文件
	 */
	public static final String SUSPICIOUS_FILE = "CHKLDWS.txt";

	/**
	 * 导入文件的后缀名
	 */
	public static final String FILE_TYPE = ".zip";
	/**
	 * 默认日期
	 */
	public static final String DATE_DEFAULT = null;
	/**
	 * 解压zip格式时的编码
	 */
	public static final String ZIP_GB2312_CODE = "gb2312";

	/**
	 * 文件编码
	 */
	public static final String FILE_UTF8_CODE = "UTF-8";
	/**
	 * 日期类型
	 */
	public static final String JAVA_DATE_TYPE = "java.util.Date";
	/**
	 * 数字类型
	 */
	public static final String JAVA_DOUBLE_TYPE = "java.lang.Double";
	/**
	 * /** 数字类型
	 */
	public static final String JAVA_BIGDECIMAL_TYPE = "java.math.BigDecimal";
	/**
	 * STRINGBUILDER的删除首字母的索引
	 */
	public static final int STRINGBUILDER_DELETE_FIRST_INDEX = 0;

	/**
	 * 数据文件的目录（包括原始的ZIP，解压后存放TXT的目录，部分LOGs的上当等等）
	 */
	public static final String DATA_FILES_PATH = "config" + File.separator
			+ "importdata" + File.separator + "filepath.properties";
	public static final String AUDITIMPORT_PATH = "config" + File.separator
			+ "importdata" + File.separator + "etlmapping.properties";

	/**
	 * 
	 */
	public static final String ACCOUNTTYPEPATH = "config/accounttypemapping.properties";

	public static final String ACCOUNTTYPE_CNY_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "accounttypecny.properties";

	public static final String ACCOUNTTYPE_NOT_CNY_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "accounttypenotcny.properties";
	
	public static final String INCOMTYPE_IN_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "incomtypein.properties";
	
	public static final String INCOMTYPE_OUT_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "incomtypeout.properties";
	
	public static final String PAYTYPE_IN_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "paytypein.properties";
	
	public static final String PAYTYPE_OUT_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "paytypeout.properties";

	public static final String CONVERTCOUNTRYCODEPATH = "config"
			+ File.separator + "importdata" + File.separator + "transformdata"
			+ File.separator + "country.properties";

	public static final String INSTITUTIONCODEPATH = "config" + File.separator
			+ "importdata" + File.separator + "transformdata" + File.separator
			+ "institutioncode.properties";

	public static final String PISAINDUSTRYCODE_PATH_MAPPING = "config"
			+ File.separator + "importdata" + File.separator + "transformdata"
			+ File.separator + "pisaindustrycode.properties";

	public static final String DEPOSIT_ACCOUNTTYPE_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "depositproductcode.properties";

	public static final String SUMMITMMTYPE_PATH_MAPPING = "config"
			+ File.separator + "importdata"
			+ File.separator + "summitmmtype.properties";
}
