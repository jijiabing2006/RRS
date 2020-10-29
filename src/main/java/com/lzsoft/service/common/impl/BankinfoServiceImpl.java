package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.service.common.BankinfoServiceI;

@Service("bankinfoService")
@Transactional
public class BankinfoServiceImpl extends CommonServiceImpl implements BankinfoServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BankinfoEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BankinfoEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BankinfoEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BankinfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BankinfoEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BankinfoEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BankinfoEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{branchcode}",String.valueOf(t.getBranchcode()));
 		sql  = sql.replace("#{bankcode_c14}",String.valueOf(t.getBankcodeC14()));
 		sql  = sql.replace("#{amlbankcode}",String.valueOf(t.getAmlbankcode()));
 		sql  = sql.replace("#{shortcnname}",String.valueOf(t.getShortcnname()));
 		sql  = sql.replace("#{shortenname}",String.valueOf(t.getShortenname()));
 		sql  = sql.replace("#{fullcnname}",String.valueOf(t.getFullcnname()));
 		sql  = sql.replace("#{fullenname}",String.valueOf(t.getFullenname()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{areacode}",String.valueOf(t.getAreacode()));
 		sql  = sql.replace("#{citycode}",String.valueOf(t.getCitycode()));
 		sql  = sql.replace("#{cityname}",String.valueOf(t.getCityname()));
 		sql  = sql.replace("#{provincecode}",String.valueOf(t.getProvincecode()));
 		sql  = sql.replace("#{provincename}",String.valueOf(t.getProvincename()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}