package com.lzsoft.service.safe;
import java.util.Date;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.common.TaskscheduleEntity;

public interface JshServiceI extends CommonService{
	
 	public String extracteJshReport(Date maxImportdate, TaskscheduleEntity taskscheduleEntity);
 	public String extracteJshReport();
	@SuppressWarnings("rawtypes")
	public Class getClazzByType(String type);
	public String getManagerType(String type);
	public <T> T findManageData(T jshobj);
	public <T> T  findBaseData(T jshobj);
 
}
