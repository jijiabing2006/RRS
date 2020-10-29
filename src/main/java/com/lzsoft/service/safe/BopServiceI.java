package com.lzsoft.service.safe;
import java.util.Date;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.common.TaskscheduleEntity;

public interface BopServiceI extends CommonService{
	
 	public String extracteBopReport(Date maxImportdate, TaskscheduleEntity taskscheduleEntity);
 	public String extracteBopReport();
	public <T> T  findBaseData(T  bopobj);
	public Class getClazzByType(String type);
	public <T>T findManageData(T bopobj);
	public <T>T findDeclareData(T bopobj);
	public String getManagerType(String type);
	public String getDeclareType(String type);
 
}
