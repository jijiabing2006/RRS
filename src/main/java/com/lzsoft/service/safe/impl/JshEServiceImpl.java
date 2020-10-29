package com.lzsoft.service.safe.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.JshEEntity;
import com.lzsoft.service.safe.JshEServiceI;

@Service("jshEService")
@Transactional
public class JshEServiceImpl extends CommonServiceImpl implements JshEServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((JshEEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((JshEEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((JshEEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(JshEEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(JshEEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(JshEEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,JshEEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{actiontype}",String.valueOf(t.getActiontype()));
 		sql  = sql.replace("#{actiondesc}",String.valueOf(t.getActiondesc()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{buscode}",String.valueOf(t.getBuscode()));
 		sql  = sql.replace("#{custype}",String.valueOf(t.getCustype()));
 		sql  = sql.replace("#{custcod}",String.valueOf(t.getCustcod()));
 		sql  = sql.replace("#{idcode}",String.valueOf(t.getIdcode()));
 		sql  = sql.replace("#{custnm}",String.valueOf(t.getCustnm()));
 		sql  = sql.replace("#{lcyacc}",String.valueOf(t.getLcyacc()));
 		sql  = sql.replace("#{fcyacc}",String.valueOf(t.getFcyacc()));
 		sql  = sql.replace("#{oppuser}",String.valueOf(t.getOppuser()));
 		sql  = sql.replace("#{oppbank}",String.valueOf(t.getOppbank()));
 		sql  = sql.replace("#{lcyamt}",String.valueOf(t.getLcyamt()));
 		sql  = sql.replace("#{lcyccy}",String.valueOf(t.getLcyccy()));
 		sql  = sql.replace("#{exrate}",String.valueOf(t.getExrate()));
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