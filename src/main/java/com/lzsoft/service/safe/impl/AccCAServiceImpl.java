package com.lzsoft.service.safe.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.service.safe.AccCAServiceI;

@Service("accCAService")
@Transactional
public class AccCAServiceImpl extends CommonServiceImpl implements AccCAServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((AccCAEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((AccCAEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((AccCAEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(AccCAEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(AccCAEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(AccCAEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,AccCAEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{actiontype}",String.valueOf(t.getActiontype()));
 		sql  = sql.replace("#{actiondesc}",String.valueOf(t.getActiondesc()));
 		sql  = sql.replace("#{branchcode}",String.valueOf(t.getBranchcode()));
 		sql  = sql.replace("#{branchname}",String.valueOf(t.getBranchname()));
 		sql  = sql.replace("#{accountno}",String.valueOf(t.getAccountno()));
 		sql  = sql.replace("#{accountstat}",String.valueOf(t.getAccountstat()));
 		sql  = sql.replace("#{amtype}",String.valueOf(t.getAmtype()));
 		sql  = sql.replace("#{encode}",String.valueOf(t.getEncode()));
 		sql  = sql.replace("#{enname}",String.valueOf(t.getEnname()));
 		sql  = sql.replace("#{accounttype}",String.valueOf(t.getAccounttype()));
 		sql  = sql.replace("#{accountcata}",String.valueOf(t.getAccountcata()));
 		sql  = sql.replace("#{currencycode}",String.valueOf(t.getCurrencycode()));
 		sql  = sql.replace("#{businessdate}",String.valueOf(t.getBusinessdate()));
 		sql  = sql.replace("#{filenumber}",String.valueOf(t.getFilenumber()));
 		sql  = sql.replace("#{limittype}",String.valueOf(t.getLimittype()));
 		sql  = sql.replace("#{accountlimit}",String.valueOf(t.getAccountlimit()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{isdel}",String.valueOf(t.getIsdel()));
 		sql  = sql.replace("#{isedit}",String.valueOf(t.getIsedit()));
 		sql  = sql.replace("#{isexport}",String.valueOf(t.getIsexport()));
 		sql  = sql.replace("#{ishandadd}",String.valueOf(t.getIshandadd()));
 		sql  = sql.replace("#{isvalidation}",String.valueOf(t.getIsvalidation()));
 		sql  = sql.replace("#{filename}",String.valueOf(t.getFilename()));
 		sql  = sql.replace("#{isinsafe}",String.valueOf(t.getIsinsafe()));
 		sql  = sql.replace("#{tfilename}",String.valueOf(t.getTfilename()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}