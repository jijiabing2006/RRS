package com.lzsoft.service.safe;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.safe.ReportDicEntity;

public interface ReportDicServiceI extends CommonService{
public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ReportDicEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ReportDicEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ReportDicEntity t);
}
