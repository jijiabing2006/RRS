package com.lzsoft.service.common;

import java.util.Date;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.summary.OutlineSAFE;

public interface SummaryServiceI extends CommonService {
	/**
	 * 根据类及日期进行查询
	 * 
	 * @param clazz
	 * @param importdate
	 * @return 不同模块的概要信息（ACC，BOP...）
	 */
	Map<String, OutlineSAFE> getSummary(String clazz, String[] allTypeList,
			Date importdate);
	Object getImportSummary(Class clazz, Date importdate) throws Exception;



}
