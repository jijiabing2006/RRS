package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.CustIndvEntity;
import com.lzsoft.service.common.CustIndvServiceI;

@Service("custIndvService")
@Transactional
public class CustIndvServiceImpl extends CommonServiceImpl implements CustIndvServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CustIndvEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CustIndvEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CustIndvEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CustIndvEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CustIndvEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CustIndvEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CustIndvEntity t){
 		sql  = sql.replace("#{piabi}",String.valueOf(t.getPiabi()));
 		sql  = sql.replace("#{resi}",String.valueOf(t.getResi()));
 		sql  = sql.replace("#{pro}",String.valueOf(t.getPro()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{industrycode}",String.valueOf(t.getIndustrycode()));
 		sql  = sql.replace("#{dob}",String.valueOf(t.getDob()));
 		sql  = sql.replace("#{isem}",String.valueOf(t.getIsem()));
 		sql  = sql.replace("#{shod}",String.valueOf(t.getShod()));
 		sql  = sql.replace("#{citp}",String.valueOf(t.getCitp()));
 		sql  = sql.replace("#{gend}",String.valueOf(t.getGend()));
 		sql  = sql.replace("#{mars}",String.valueOf(t.getMars()));
 		sql  = sql.replace("#{job}",String.valueOf(t.getJob()));
 		sql  = sql.replace("#{issa}",String.valueOf(t.getIssa()));
 		sql  = sql.replace("#{zipcode}",String.valueOf(t.getZipcode()));
 		sql  = sql.replace("#{income}",String.valueOf(t.getIncome()));
 		sql  = sql.replace("#{isbl}",String.valueOf(t.getIsbl()));
 		sql  = sql.replace("#{blre}",String.valueOf(t.getBlre()));
 		sql  = sql.replace("#{lvst}",String.valueOf(t.getLvst()));
 		sql  = sql.replace("#{regc}",String.valueOf(t.getRegc()));
 		sql  = sql.replace("#{opdt}",String.valueOf(t.getOpdt()));
 		sql  = sql.replace("#{nation}",String.valueOf(t.getNation()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}