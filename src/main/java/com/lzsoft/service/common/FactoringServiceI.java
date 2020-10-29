package com.lzsoft.service.common;

import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.common.FactoringEntity;

public interface FactoringServiceI extends CommonService{
public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(FactoringEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(FactoringEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(FactoringEntity t);
}
