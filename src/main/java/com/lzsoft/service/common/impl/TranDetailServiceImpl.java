package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.TranDetailEntity;
import com.lzsoft.service.common.TranDetailServiceI;

@Service("tranDetailService")
@Transactional
public class TranDetailServiceImpl extends CommonServiceImpl implements TranDetailServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TranDetailEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((TranDetailEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TranDetailEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TranDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TranDetailEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TranDetailEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TranDetailEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{tid}",String.valueOf(t.getTid()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{ccy}",String.valueOf(t.getCcy()));
 		sql  = sql.replace("#{acod}",String.valueOf(t.getAcod()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{brca_co}",String.valueOf(t.getBrcaCo()));
 		sql  = sql.replace("#{pstd}",String.valueOf(t.getPstd()));
 		sql  = sql.replace("#{vald}",String.valueOf(t.getVald()));
 		sql  = sql.replace("#{psta}",String.valueOf(t.getPsta()));
 		sql  = sql.replace("#{drcr}",String.valueOf(t.getDrcr()));
 		sql  = sql.replace("#{dlref}",String.valueOf(t.getDlref()));
 		sql  = sql.replace("#{trat}",String.valueOf(t.getTrat()));
 		sql  = sql.replace("#{strcode}",String.valueOf(t.getStrcode()));
 		sql  = sql.replace("#{ptrcode}",String.valueOf(t.getPtrcode()));
 		sql  = sql.replace("#{inbop}",String.valueOf(t.getInbop()));
 		sql  = sql.replace("#{opname}",String.valueOf(t.getOpname()));
 		sql  = sql.replace("#{opaccount}",String.valueOf(t.getOpaccount()));
 		sql  = sql.replace("#{fcyamt}",String.valueOf(t.getFcyamt()));
 		sql  = sql.replace("#{exrate}",String.valueOf(t.getExrate()));
 		sql  = sql.replace("#{examt}",String.valueOf(t.getExamt()));
 		sql  = sql.replace("#{rmbaccount}",String.valueOf(t.getRmbaccount()));
 		sql  = sql.replace("#{othamt}",String.valueOf(t.getOthamt()));
 		sql  = sql.replace("#{othaccount}",String.valueOf(t.getOthaccount()));
 		sql  = sql.replace("#{paymethodone}",String.valueOf(t.getPaymethodone()));
 		sql  = sql.replace("#{paymethodtwo}",String.valueOf(t.getPaymethodtwo()));
 		sql  = sql.replace("#{authority_code}",String.valueOf(t.getAuthorityCode()));
 		sql  = sql.replace("#{tdlc}",String.valueOf(t.getTdlc()));
 		sql  = sql.replace("#{pnar}",String.valueOf(t.getPnar()));
 		sql  = sql.replace("#{incomtype}",String.valueOf(t.getIncomtype()));
 		sql  = sql.replace("#{paytype}",String.valueOf(t.getPaytype()));
 		sql  = sql.replace("#{incomtaxfree}",String.valueOf(t.getIncomtaxfree()));
 		sql  = sql.replace("#{appl}",String.valueOf(t.getAppl()));
 		sql  = sql.replace("#{applphone}",String.valueOf(t.getApplphone()));
 		sql  = sql.replace("#{local_bank_fee}",String.valueOf(t.getLocalBankFee()));
 		sql  = sql.replace("#{local_bank_fee_ccy}",String.valueOf(t.getLocalBankFeeCcy()));
 		sql  = sql.replace("#{forebchar}",String.valueOf(t.getForebchar()));
 		sql  = sql.replace("#{ccyforebchar}",String.valueOf(t.getCcyforebchar()));
 		sql  = sql.replace("#{lclgno}",String.valueOf(t.getLclgno()));
 		sql  = sql.replace("#{lclgopendate}",String.valueOf(t.getLclgopendate()));
 		sql  = sql.replace("#{lclgtenor}",String.valueOf(t.getLclgtenor()));
 		sql  = sql.replace("#{bopccy}",String.valueOf(t.getBopccy()));
 		sql  = sql.replace("#{bopamt}",String.valueOf(t.getBopamt()));
 		sql  = sql.replace("#{fx_de_pur}",String.valueOf(t.getFxDePur()));
 		sql  = sql.replace("#{psmk}",String.valueOf(t.getPsmk()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{transactionremark}",String.valueOf(t.getTransactionremark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}