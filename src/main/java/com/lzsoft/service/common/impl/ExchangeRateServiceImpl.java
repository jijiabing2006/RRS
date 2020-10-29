package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.ExchangeRateEntity;
import com.lzsoft.service.common.ExchangeRateServiceI;

@Service("exchangeRateService")
@Transactional
public class ExchangeRateServiceImpl extends CommonServiceImpl implements ExchangeRateServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ExchangeRateEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ExchangeRateEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ExchangeRateEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ExchangeRateEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ExchangeRateEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ExchangeRateEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ExchangeRateEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{ccy}",String.valueOf(t.getCcy()));
 		sql  = sql.replace("#{exchangemethod}",String.valueOf(t.getExchangemethod()));
 		sql  = sql.replace("#{exchangerateBuy}",String.valueOf(t.getExchangerateBuy()));
 		sql  = sql.replace("#{exchangerateMid}",String.valueOf(t.getExchangerateMid()));
 		sql  = sql.replace("#{exchangerateSell}",String.valueOf(t.getExchangerateSell()));
 		sql  = sql.replace("#{ratetype}",String.valueOf(t.getRatetype()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}