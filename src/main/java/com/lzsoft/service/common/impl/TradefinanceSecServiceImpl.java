package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.TradefinanceSecEntity;
import com.lzsoft.service.common.TradefinanceSecServiceI;

@Service("tradefinanceSecService")
@Transactional
public class TradefinanceSecServiceImpl extends CommonServiceImpl implements TradefinanceSecServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TradefinanceSecEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((TradefinanceSecEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TradefinanceSecEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TradefinanceSecEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TradefinanceSecEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TradefinanceSecEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TradefinanceSecEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{lcno}",String.valueOf(t.getLcno()));
 		sql  = sql.replace("#{ctnm}",String.valueOf(t.getCtnm()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{resc}",String.valueOf(t.getResc()));
 		sql  = sql.replace("#{isdt}",String.valueOf(t.getIsdt()));
 		sql  = sql.replace("#{befc}",String.valueOf(t.getBefc()));
 		sql  = sql.replace("#{malc}",String.valueOf(t.getMalc()));
 		sql  = sql.replace("#{lccr}",String.valueOf(t.getLccr()));
 		sql  = sql.replace("#{lcam}",String.valueOf(t.getLcam()));
 		sql  = sql.replace("#{lcte}",String.valueOf(t.getLcte()));
 		sql  = sql.replace("#{coms}",String.valueOf(t.getComs()));
 		sql  = sql.replace("#{ocms}",String.valueOf(t.getOcms()));
 		sql  = sql.replace("#{cnlobs}",String.valueOf(t.getCnlobs()));
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