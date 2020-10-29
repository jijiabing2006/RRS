package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.JshEntity;
import com.lzsoft.service.common.JshEntityServiceI;

@Service("jshEntityService")
@Transactional
public class JshEntityServiceImpl extends CommonServiceImpl implements JshEntityServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((JshEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((JshEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((JshEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(JshEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(JshEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(JshEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,JshEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{dlno}",String.valueOf(t.getDlno()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{forg_ex_acc}",String.valueOf(t.getForgExAcc()));
 		sql  = sql.replace("#{set_ex_acc}",String.valueOf(t.getSetExAcc()));
 		sql  = sql.replace("#{dtyp}",String.valueOf(t.getDtyp()));
 		sql  = sql.replace("#{ortp}",String.valueOf(t.getOrtp()));
 		sql  = sql.replace("#{ddat}",String.valueOf(t.getDdat()));
 		sql  = sql.replace("#{vdat}",String.valueOf(t.getVdat()));
 		sql  = sql.replace("#{pucy}",String.valueOf(t.getPucy()));
 		sql  = sql.replace("#{puam}",String.valueOf(t.getPuam()));
 		sql  = sql.replace("#{slcy}",String.valueOf(t.getSlcy()));
 		sql  = sql.replace("#{slam}",String.valueOf(t.getSlam()));
 		sql  = sql.replace("#{cus_buy}",String.valueOf(t.getCusBuy()));
 		sql  = sql.replace("#{bk_buy}",String.valueOf(t.getBkBuy()));
 		sql  = sql.replace("#{note}",String.valueOf(t.getNote()));
 		sql  = sql.replace("#{ex_setcd}",String.valueOf(t.getExSetcd()));
 		sql  = sql.replace("#{decl_ubop}",String.valueOf(t.getDeclUbop()));
 		sql  = sql.replace("#{ex_setapp}",String.valueOf(t.getExSetapp()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}