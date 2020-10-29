package com.lzsoft.service.safe.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.BopEEntity;
import com.lzsoft.service.safe.BopEServiceI;

@Service("bopEService")
@Transactional
public class BopEServiceImpl extends CommonServiceImpl implements BopEServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BopEEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BopEEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BopEEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BopEEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BopEEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BopEEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BopEEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{actiontype}",String.valueOf(t.getActiontype()));
 		sql  = sql.replace("#{actiondesc}",String.valueOf(t.getActiondesc()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{custype}",String.valueOf(t.getCustype()));
 		sql  = sql.replace("#{idcode}",String.valueOf(t.getIdcode()));
 		sql  = sql.replace("#{custcod}",String.valueOf(t.getCustcod()));
 		sql  = sql.replace("#{custnm}",String.valueOf(t.getCustnm()));
 		sql  = sql.replace("#{oppuser}",String.valueOf(t.getOppuser()));
 		sql  = sql.replace("#{oppacc}",String.valueOf(t.getOppacc()));
 		sql  = sql.replace("#{txccy}",String.valueOf(t.getTxccy()));
 		sql  = sql.replace("#{txamt}",String.valueOf(t.getTxamt()));
 		sql  = sql.replace("#{exrate}",String.valueOf(t.getExrate()));
 		sql  = sql.replace("#{lcyamt}",String.valueOf(t.getLcyamt()));
 		sql  = sql.replace("#{lcyacc}",String.valueOf(t.getLcyacc()));
 		sql  = sql.replace("#{fcyamt}",String.valueOf(t.getFcyamt()));
 		sql  = sql.replace("#{fcyacc}",String.valueOf(t.getFcyacc()));
 		sql  = sql.replace("#{othamt}",String.valueOf(t.getOthamt()));
 		sql  = sql.replace("#{othacc}",String.valueOf(t.getOthacc()));
 		sql  = sql.replace("#{method}",String.valueOf(t.getMethod()));
 		sql  = sql.replace("#{buscode}",String.valueOf(t.getBuscode()));
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