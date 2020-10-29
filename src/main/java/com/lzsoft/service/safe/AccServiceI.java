package com.lzsoft.service.safe;
import java.util.Date;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.common.TaskscheduleEntity;

public interface AccServiceI extends CommonService{
	
 	public String extracteAccReport(Date maxImportdate, TaskscheduleEntity taskscheduleEntity);
 	public String extracteAccReport();
	@SuppressWarnings("rawtypes")
	public Class getClazzByType(String curfile);
	public String transformeAcc(Date strToDate);
 
}
