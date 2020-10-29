package com.lzsoft.service.safe;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.safe.ReportMonitorEntity;

public interface ReportMonitorServiceI extends CommonService{
	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ReportMonitorEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ReportMonitorEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ReportMonitorEntity t);
 	
 	
 	
 	public <T> void  complete(ReportMonitorEntity t);
}
