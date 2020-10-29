package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.AccCustEntity;
import com.lzsoft.service.common.AccCustServiceI;

@Service("accCustService")
@Transactional
public class AccCustServiceImpl extends CommonServiceImpl implements AccCustServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AccCustEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((AccCustEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((AccCustEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AccCustEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AccCustEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AccCustEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AccCustEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{acod}",String.valueOf(t.getAcod()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{accounttype}",String.valueOf(t.getAccounttype()));
 		sql  = sql.replace("#{ccy}",String.valueOf(t.getCcy()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{opendate}",String.valueOf(t.getOpendate()));
 		sql  = sql.replace("#{echam}",String.valueOf(t.getEcham()));
 		sql  = sql.replace("#{effdate}",String.valueOf(t.getEffdate()));
 		sql  = sql.replace("#{closedate}",String.valueOf(t.getClosedate()));
 		sql  = sql.replace("#{matdate}",String.valueOf(t.getMatdate()));
 		sql  = sql.replace("#{valdate}",String.valueOf(t.getValdate()));
 		sql  = sql.replace("#{balance}",String.valueOf(t.getBalance()));
 		sql  = sql.replace("#{cls_bal}",String.valueOf(t.getClsBal()));
 		sql  = sql.replace("#{int_prd}",String.valueOf(t.getIntPrd()));
 		sql  = sql.replace("#{int_stmt}",String.valueOf(t.getIntStmt()));
 		sql  = sql.replace("#{filenumber}",String.valueOf(t.getFileNumber()));
 		sql  = sql.replace("#{lmdt}",String.valueOf(t.getLmdt()));
 		sql  = sql.replace("#{accountstat}",String.valueOf(t.getAccountstat()));
 		sql  = sql.replace("#{limittype}",String.valueOf(t.getLimittype()));
 		sql  = sql.replace("#{accountlimit}",String.valueOf(t.getAccountlimit()));
 		sql  = sql.replace("#{ft_type}",String.valueOf(t.getFtType()));
 		sql  = sql.replace("#{mainacod}",String.valueOf(t.getMainacod()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}