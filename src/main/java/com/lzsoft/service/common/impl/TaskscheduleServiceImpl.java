package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.service.common.TaskscheduleServiceI;

@Service("taskscheduleService")
@Transactional
public class TaskscheduleServiceImpl extends CommonServiceImpl implements TaskscheduleServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TaskscheduleEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((TaskscheduleEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TaskscheduleEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TaskscheduleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TaskscheduleEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TaskscheduleEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TaskscheduleEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{executable}",String.valueOf(t.isExecutable()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{taskdesc}",String.valueOf(t.getTaskdesc()));
 		sql  = sql.replace("#{taskname}",String.valueOf(t.getTaskname()));
 		sql  = sql.replace("#{counts}",String.valueOf(t.getCounts()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}