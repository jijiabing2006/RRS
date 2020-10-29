package com.lzsoft.service.common.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.EncodeMapUsccEntity;
import com.lzsoft.service.common.EncodeMapUsccServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("encodeMapUsccService")
@Transactional
public class EncodeMapUsccServiceImpl extends CommonServiceImpl implements EncodeMapUsccServiceI {
	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((EncodeMapUsccEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((EncodeMapUsccEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((EncodeMapUsccEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(EncodeMapUsccEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(EncodeMapUsccEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(EncodeMapUsccEntity t){
	 	return true;
 	}
}