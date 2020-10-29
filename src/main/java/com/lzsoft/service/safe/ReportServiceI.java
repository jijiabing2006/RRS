package com.lzsoft.service.safe;

import java.util.Date;

import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;

public interface ReportServiceI {
/**
 * 导入上报文件
 * @param allTypeList 导出文件类型  ACCCA/ACCCB....JSHD/JSHE.../JSHG
 * @param dealDate  营业日期
 * @param currentDepart 当前选择的组织机构
 * @param accType  
 * @return
 * @throws Exception
 */
	int exportReport(String[] allTypeList, Date dealDate,
			TSDepart currentDepart,String accType) throws Exception;

	String exportReportAndSend(String date, TSUser user,String accType);


	void addTashSchdule(Date date, TSUser user,String accType) throws Exception;

	<T > int validation(T entity) throws Exception;

	int generateEmptyBOPReport(String[] allTypeList,Date reportdate, TSDepart currentDepart,
			String accType) throws Exception;


}
