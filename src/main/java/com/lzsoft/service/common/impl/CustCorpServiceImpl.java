package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.CustCorpEntity;
import com.lzsoft.service.common.CustCorpServiceI;

@Service("custCorpService")
@Transactional
public class CustCorpServiceImpl extends CommonServiceImpl implements CustCorpServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((CustCorpEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((CustCorpEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((CustCorpEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(CustCorpEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(CustCorpEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(CustCorpEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,CustCorpEntity t){
 		sql  = sql.replace("#{mlpr}",String.valueOf(t.getMlpr()));
 		sql  = sql.replace("#{pctnm}",String.valueOf(t.getPctnm()));
 		sql  = sql.replace("#{pctnt}",String.valueOf(t.getPctnt()));
 		sql  = sql.replace("#{regaddress}",String.valueOf(t.getRegaddress()));
 		sql  = sql.replace("#{attcode}",String.valueOf(t.getAttcode()));
 		sql  = sql.replace("#{custsize}",String.valueOf(t.getCustsize()));
 		sql  = sql.replace("#{relatetype}",String.valueOf(t.getRelatetype()));
 		sql  = sql.replace("#{relatename}",String.valueOf(t.getRelatename()));
 		sql  = sql.replace("#{crnm}",String.valueOf(t.getCrnm()));
 		sql  = sql.replace("#{crit}",String.valueOf(t.getCrit()));
 		sql  = sql.replace("#{crid}",String.valueOf(t.getCrid()));
 		sql  = sql.replace("#{enupdate}",String.valueOf(t.getEnupdate()));
 		sql  = sql.replace("#{entyp}",String.valueOf(t.getEntyp()));
 		sql  = sql.replace("#{buslicense}",String.valueOf(t.getBuslicense()));
 		sql  = sql.replace("#{orgexpdate}",String.valueOf(t.getOrgexpdate()));
 		sql  = sql.replace("#{busscope}",String.valueOf(t.getBusscope()));
 		sql  = sql.replace("#{regdate}",String.valueOf(t.getRegdate()));
 		sql  = sql.replace("#{industrycode}",String.valueOf(t.getIndustrycode()));
 		sql  = sql.replace("#{regtype}",String.valueOf(t.getRegtype()));
 		sql  = sql.replace("#{lncardid}",String.valueOf(t.getLncardid()));
 		sql  = sql.replace("#{listcompanyflag}",String.valueOf(t.getListcompanyflag()));
 		sql  = sql.replace("#{isbanksh}",String.valueOf(t.getIsbanksh()));
 		sql  = sql.replace("#{pcutp}",String.valueOf(t.getPcutp()));
 		sql  = sql.replace("#{parn}",String.valueOf(t.getParn()));
 		sql  = sql.replace("#{excreditass}",String.valueOf(t.getExcreditass()));
 		sql  = sql.replace("#{rishrank}",String.valueOf(t.getRishrank()));
 		sql  = sql.replace("#{nationaltaxlic}",String.valueOf(t.getNationaltaxlic()));
 		sql  = sql.replace("#{localtaxlic}",String.valueOf(t.getLocaltaxlic()));
 		sql  = sql.replace("#{contactor}",String.valueOf(t.getContactor()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{fax}",String.valueOf(t.getFax()));
 		sql  = sql.replace("#{sms}",String.valueOf(t.getSms()));
 		sql  = sql.replace("#{email}",String.valueOf(t.getEmail()));
 		sql  = sql.replace("#{caddress}",String.valueOf(t.getCaddress()));
 		sql  = sql.replace("#{istaxfree}",String.valueOf(t.getIstaxfree()));
 		sql  = sql.replace("#{taxfreecode}",String.valueOf(t.getTaxfreecode()));
 		sql  = sql.replace("#{zipcode}",String.valueOf(t.getZipcode()));
 		sql  = sql.replace("#{blcl}",String.valueOf(t.getBlcl()));
 		sql  = sql.replace("#{cklr}",String.valueOf(t.getCklr()));
 		sql  = sql.replace("#{recpccy}",String.valueOf(t.getRecpccy()));
 		sql  = sql.replace("#{rgcp}",String.valueOf(t.getRgcp()));
 		sql  = sql.replace("#{actcp}",String.valueOf(t.getActcp()));
 		sql  = sql.replace("#{totalassets}",String.valueOf(t.getTotalassets()));
 		sql  = sql.replace("#{annrevenue}",String.valueOf(t.getAnnrevenue()));
 		sql  = sql.replace("#{empnumber}",String.valueOf(t.getEmpnumber()));
 		sql  = sql.replace("#{regc}",String.valueOf(t.getRegc()));
 		sql  = sql.replace("#{opdt}",String.valueOf(t.getOpdt()));
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}