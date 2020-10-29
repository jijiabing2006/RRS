package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.GuaranteeEntity;
import com.lzsoft.service.common.GuaranteeServiceI;

@Service("guaranteeService")
@Transactional
public class GuaranteeServiceImpl extends CommonServiceImpl implements GuaranteeServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((GuaranteeEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((GuaranteeEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((GuaranteeEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(GuaranteeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(GuaranteeEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(GuaranteeEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,GuaranteeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{ctnm}",String.valueOf(t.getCtnm()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{cnlobs}",String.valueOf(t.getCnlobs()));
 		sql  = sql.replace("#{grtc}",String.valueOf(t.getGrtc()));
 		sql  = sql.replace("#{wart}",String.valueOf(t.getWart()));
 		sql  = sql.replace("#{mrt_name}",String.valueOf(t.getMrtName()));
 		sql  = sql.replace("#{mrt_cd}",String.valueOf(t.getMrtCd()));
 		sql  = sql.replace("#{nam_owner}",String.valueOf(t.getNamOwner()));
 		sql  = sql.replace("#{idtp_owner}",String.valueOf(t.getIdtpOwner()));
 		sql  = sql.replace("#{id_owner}",String.valueOf(t.getIdOwner()));
 		sql  = sql.replace("#{ace_freq}",String.valueOf(t.getAceFreq()));
 		sql  = sql.replace("#{est_val}",String.valueOf(t.getEstVal()));
 		sql  = sql.replace("#{upd_valdate}",String.valueOf(t.getUpdValdate()));
 		sql  = sql.replace("#{matdat_val}",String.valueOf(t.getMatdatVal()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}