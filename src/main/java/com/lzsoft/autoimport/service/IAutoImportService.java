package com.lzsoft.autoimport.service;

import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

public interface IAutoImportService extends CommonService{
	
	public List<?> getFinalList(String resourceFiles, Class<?> clazz, Date trandate)throws Exception;
}
