package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.service.common.CustBaseServiceI;

@Service("custBaseService")
@Transactional
public class CustBaseServiceImpl extends CommonServiceImpl implements CustBaseServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CustBaseEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CustBaseEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CustBaseEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CustBaseEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CustBaseEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CustBaseEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CustBaseEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{ctnm}",String.valueOf(t.getCtnm()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{ctnt}",String.valueOf(t.getCtnt()));
 		sql  = sql.replace("#{cs}",String.valueOf(t.getCs()));
 		sql  = sql.replace("#{ctnmen}",String.valueOf(t.getCtnmen()));
 		sql  = sql.replace("#{encode}",String.valueOf(t.getEncode()));
 		sql  = sql.replace("#{islocal}",String.valueOf(t.getIslocal()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}