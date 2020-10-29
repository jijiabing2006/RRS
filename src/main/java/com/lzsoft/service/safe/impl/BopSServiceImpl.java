package com.lzsoft.service.safe.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.BopSEntity;
import com.lzsoft.service.safe.BopSServiceI;

@Service("bopSService")
@Transactional
public class BopSServiceImpl extends CommonServiceImpl implements BopSServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BopSEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BopSEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BopSEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BopSEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BopSEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BopSEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BopSEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{actiontype}",String.valueOf(t.getActiontype()));
 		sql  = sql.replace("#{actiondesc}",String.valueOf(t.getActiondesc()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{isref}",String.valueOf(t.getIsref()));
 		sql  = sql.replace("#{country}",String.valueOf(t.getCountry()));
 		sql  = sql.replace("#{payattr}",String.valueOf(t.getPayattr()));
 		sql  = sql.replace("#{paytype}",String.valueOf(t.getPaytype()));
 		sql  = sql.replace("#{txcode}",String.valueOf(t.getTxcode()));
 		sql  = sql.replace("#{tc1amt}",String.valueOf(t.getTc1amt()));
 		sql  = sql.replace("#{txcode2}",String.valueOf(t.getTxcode2()));
 		sql  = sql.replace("#{tc2amt}",String.valueOf(t.getTc2amt()));
 		sql  = sql.replace("#{contrno}",String.valueOf(t.getContrno()));
 		sql  = sql.replace("#{invoino}",String.valueOf(t.getInvoino()));
 		sql  = sql.replace("#{billno}",String.valueOf(t.getBillno()));
 		sql  = sql.replace("#{contamt}",String.valueOf(t.getContamt()));
 		sql  = sql.replace("#{regno}",String.valueOf(t.getRegno()));
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