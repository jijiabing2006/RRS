package com.lzsoft.service.safe.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.BopNEntity;
import com.lzsoft.service.safe.BopNServiceI;

@Service("bopNService")
@Transactional
public class BopNServiceImpl extends CommonServiceImpl implements BopNServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BopNEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BopNEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BopNEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BopNEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BopNEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BopNEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BopNEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{actiontype}",String.valueOf(t.getActiontype()));
 		sql  = sql.replace("#{actiondesc}",String.valueOf(t.getActiondesc()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{contrno}",String.valueOf(t.getContrno()));
 		sql  = sql.replace("#{invoino}",String.valueOf(t.getInvoino()));
 		sql  = sql.replace("#{crtuser}",String.valueOf(t.getCrtuser()));
 		sql  = sql.replace("#{inptelc}",String.valueOf(t.getInptelc()));
 		sql  = sql.replace("#{rptdate}",String.valueOf(t.getRptdate()));
 		sql  = sql.replace("#{buscode}",String.valueOf(t.getBuscode()));
 		sql  = sql.replace("#{cap}",String.valueOf(t.isCap()));
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
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}