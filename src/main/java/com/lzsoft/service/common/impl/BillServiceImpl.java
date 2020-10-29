package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.BillEntity;
import com.lzsoft.service.common.BillServiceI;

@Service("billService")
@Transactional
public class BillServiceImpl extends CommonServiceImpl implements BillServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BillEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BillEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BillEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BillEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BillEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BillEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,BillEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{biln}",String.valueOf(t.getBiln()));
 		sql  = sql.replace("#{payen}",String.valueOf(t.getPayen()));
 		sql  = sql.replace("#{drac}",String.valueOf(t.getDrac()));
 		sql  = sql.replace("#{payb}",String.valueOf(t.getPayb()));
 		sql  = sql.replace("#{dais}",String.valueOf(t.getDais()));
 		sql  = sql.replace("#{matd}",String.valueOf(t.getMatd()));
 		sql  = sql.replace("#{bptp}",String.valueOf(t.getBptp()));
 		sql  = sql.replace("#{fcvu}",String.valueOf(t.getFcvu()));
 		sql  = sql.replace("#{note}",String.valueOf(t.getNote()));
 		sql  = sql.replace("#{cmdt}",String.valueOf(t.getCmdt()));
 		sql  = sql.replace("#{acpn}",String.valueOf(t.getAcpn()));
 		sql  = sql.replace("#{acpt}",String.valueOf(t.getAcpt()));
 		sql  = sql.replace("#{pefn}",String.valueOf(t.getPefn()));
 		sql  = sql.replace("#{peac}",String.valueOf(t.getPeac()));
 		sql  = sql.replace("#{peob}",String.valueOf(t.getPeob()));
 		sql  = sql.replace("#{dicr}",String.valueOf(t.getDicr()));
 		sql  = sql.replace("#{ccy}",String.valueOf(t.getCcy()));
 		sql  = sql.replace("#{wart}",String.valueOf(t.getWart()));
 		sql  = sql.replace("#{cnlobs}",String.valueOf(t.getCnlobs()));
 		sql  = sql.replace("#{grtc}",String.valueOf(t.getGrtc()));
 		sql  = sql.replace("#{line_code}",String.valueOf(t.getLineCode()));
 		sql  = sql.replace("#{fact}",String.valueOf(t.getFact()));
 		sql  = sql.replace("#{fccy}",String.valueOf(t.getFccy()));
 		sql  = sql.replace("#{famt}",String.valueOf(t.getFamt()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}