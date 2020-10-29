package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.service.common.FeedbackerrorinfoServiceI;

@Service("feedbackerrorinfoService")
@Transactional
public class FeedbackerrorinfoServiceImpl extends CommonServiceImpl implements FeedbackerrorinfoServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((FeedbackerrorinfoEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((FeedbackerrorinfoEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((FeedbackerrorinfoEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(FeedbackerrorinfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(FeedbackerrorinfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(FeedbackerrorinfoEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,FeedbackerrorinfoEntity t){
 		sql  = sql.replace("#{errdesc}",String.valueOf(t.getErrdesc()));
 		sql  = sql.replace("#{errfield}",String.valueOf(t.getErrfield()));
 		sql  = sql.replace("#{errfieldcn}",String.valueOf(t.getErrfieldcn()));
 		sql  = sql.replace("#{rptno}",String.valueOf(t.getRptno()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}