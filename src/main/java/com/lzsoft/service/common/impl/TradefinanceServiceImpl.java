package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.TradefinanceEntity;
import com.lzsoft.service.common.TradefinanceServiceI;

@Service("tradefinanceService")
@Transactional
public class TradefinanceServiceImpl extends CommonServiceImpl implements TradefinanceServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TradefinanceEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((TradefinanceEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TradefinanceEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TradefinanceEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TradefinanceEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TradefinanceEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TradefinanceEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{lcno}",String.valueOf(t.getLcno()));
 		sql  = sql.replace("#{ctnm}",String.valueOf(t.getCtnm()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{isbk}",String.valueOf(t.getIsbk()));
 		sql  = sql.replace("#{isdt}",String.valueOf(t.getIsdt()));
 		sql  = sql.replace("#{adexrydate}",String.valueOf(t.getAdexrydate()));
 		sql  = sql.replace("#{befc}",String.valueOf(t.getBefc()));
 		sql  = sql.replace("#{malc}",String.valueOf(t.getMalc()));
 		sql  = sql.replace("#{ioun}",String.valueOf(t.getIoun()));
 		sql  = sql.replace("#{cdcn}",String.valueOf(t.getCdcn()));
 		sql  = sql.replace("#{ngtb}",String.valueOf(t.getNgtb()));
 		sql  = sql.replace("#{swiftcode}",String.valueOf(t.getSwiftcode()));
 		sql  = sql.replace("#{lccr}",String.valueOf(t.getLccr()));
 		sql  = sql.replace("#{lcam}",String.valueOf(t.getLcam()));
 		sql  = sql.replace("#{lcte}",String.valueOf(t.getLcte()));
 		sql  = sql.replace("#{coms}",String.valueOf(t.getComs()));
 		sql  = sql.replace("#{chtp}",String.valueOf(t.getChtp()));
 		sql  = sql.replace("#{grtc}",String.valueOf(t.getGrtc()));
 		sql  = sql.replace("#{line_code}",String.valueOf(t.getLineCode()));
 		sql  = sql.replace("#{ftyp}",String.valueOf(t.getFtyp()));
 		sql  = sql.replace("#{fccy}",String.valueOf(t.getFccy()));
 		sql  = sql.replace("#{fcam}",String.valueOf(t.getFcam()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}