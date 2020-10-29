package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.CustBankEntity;
import com.lzsoft.service.common.CustBankServiceI;

@Service("custBankService")
@Transactional
public class CustBankServiceImpl extends CommonServiceImpl implements CustBankServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CustBankEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CustBankEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CustBankEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CustBankEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CustBankEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CustBankEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CustBankEntity t){
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{fax}",String.valueOf(t.getFax()));
 		sql  = sql.replace("#{sms}",String.valueOf(t.getSms()));
 		sql  = sql.replace("#{email}",String.valueOf(t.getEmail()));
 		sql  = sql.replace("#{caddress}",String.valueOf(t.getCaddress()));
 		sql  = sql.replace("#{ecas}",String.valueOf(t.getEcas()));
 		sql  = sql.replace("#{crating}",String.valueOf(t.getCrating()));
 		sql  = sql.replace("#{opdt}",String.valueOf(t.getOpdt()));
 		sql  = sql.replace("#{cptc}",String.valueOf(t.getCptc()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}