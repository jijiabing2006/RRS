package com.lzsoft.service.common;

import org.jeecgframework.core.common.service.CommonService;

import com.lzsoft.entity.common.EncodeMapUsccEntity;

public interface EncodeMapUsccServiceI extends CommonService{
	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(EncodeMapUsccEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(EncodeMapUsccEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(EncodeMapUsccEntity t);
}
