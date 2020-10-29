package com.lzsoft.service.common.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.LoanEntity;
import com.lzsoft.service.common.LoanServiceI;

@Service("loanService")
@Transactional
public class LoanServiceImpl extends CommonServiceImpl implements LoanServiceI {

	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((LoanEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((LoanEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((LoanEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(LoanEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(LoanEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(LoanEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,LoanEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{csnm}",String.valueOf(t.getCsnm()));
 		sql  = sql.replace("#{ctnm}",String.valueOf(t.getCtnm()));
 		sql  = sql.replace("#{trade_id}",String.valueOf(t.getTradeId()));
 		sql  = sql.replace("#{parentbrca}",String.valueOf(t.getParentbrca()));
 		sql  = sql.replace("#{brca}",String.valueOf(t.getBrca()));
 		sql  = sql.replace("#{dtyp}",String.valueOf(t.getDtyp()));
 		sql  = sql.replace("#{lnrf}",String.valueOf(t.getLnrf()));
 		sql  = sql.replace("#{cidt}",String.valueOf(t.getCidt()));
 		sql  = sql.replace("#{cidn}",String.valueOf(t.getCidn()));
 		sql  = sql.replace("#{cuin}",String.valueOf(t.getCuin()));
 		sql  = sql.replace("#{city}",String.valueOf(t.getCity()));
 		sql  = sql.replace("#{custsize}",String.valueOf(t.getCustsize()));
 		sql  = sql.replace("#{lano}",String.valueOf(t.getLano()));
 		sql  = sql.replace("#{al_tp}",String.valueOf(t.getAlTp()));
 		sql  = sql.replace("#{loanbustype}",String.valueOf(t.getLoanbustype()));
 		sql  = sql.replace("#{use_loan}",String.valueOf(t.getUseLoan()));
 		sql  = sql.replace("#{loan_prd}",String.valueOf(t.getLoanPrd()));
 		sql  = sql.replace("#{use_oprloan}",String.valueOf(t.getUseOprloan()));
 		sql  = sql.replace("#{line_code}",String.valueOf(t.getLineCode()));
 		sql  = sql.replace("#{fact}",String.valueOf(t.getFact()));
 		sql  = sql.replace("#{fccy}",String.valueOf(t.getFccy()));
 		sql  = sql.replace("#{famt}",String.valueOf(t.getFamt()));
 		sql  = sql.replace("#{dealdate}",String.valueOf(t.getDealdate()));
 		sql  = sql.replace("#{matdate}",String.valueOf(t.getMatdate()));
 		sql  = sql.replace("#{act_end_loan}",String.valueOf(t.getActEndLoan()));
 		sql  = sql.replace("#{loan_ext}",String.valueOf(t.getLoanExt()));
 		sql  = sql.replace("#{stdt_loanext}",String.valueOf(t.getStdtLoanext()));
 		sql  = sql.replace("#{endt_loanext}",String.valueOf(t.getEndtLoanext()));
 		sql  = sql.replace("#{ccy}",String.valueOf(t.getCcy()));
 		sql  = sql.replace("#{notesell_am}",String.valueOf(t.getNotesellAm()));
 		sql  = sql.replace("#{notesell_bl}",String.valueOf(t.getNotesellBl()));
 		sql  = sql.replace("#{rftp}",String.valueOf(t.getRftp()));
 		sql  = sql.replace("#{tm_insrate}",String.valueOf(t.getTmInsrate()));
 		sql  = sql.replace("#{lvl_insrate}",String.valueOf(t.getLvlInsrate()));
 		sql  = sql.replace("#{repr_loan_insrate}",String.valueOf(t.getReprLoanInsrate()));
 		sql  = sql.replace("#{date_ins_repr}",String.valueOf(t.getDateInsRepr()));
 		sql  = sql.replace("#{loan_gar_mtd}",String.valueOf(t.getLoanGarMtd()));
 		sql  = sql.replace("#{ovdue_loan}",String.valueOf(t.getOvdueLoan()));
 		sql  = sql.replace("#{ovdue_loan_prd}",String.valueOf(t.getOvdueLoanPrd()));
 		sql  = sql.replace("#{loan_risk}",String.valueOf(t.getLoanRisk()));
 		sql  = sql.replace("#{loangrade}",String.valueOf(t.getLoangrade()));
 		sql  = sql.replace("#{loan_stat}",String.valueOf(t.getLoanStat()));
 		sql  = sql.replace("#{restr_date}",String.valueOf(t.getRestrDate()));
 		sql  = sql.replace("#{restr_loan}",String.valueOf(t.getRestrLoan()));
 		sql  = sql.replace("#{tp_restr_loan}",String.valueOf(t.getTpRestrLoan()));
 		sql  = sql.replace("#{gov_pltf_loan}",String.valueOf(t.getGovPltfLoan()));
 		sql  = sql.replace("#{pay_term}",String.valueOf(t.getPayTerm()));
 		sql  = sql.replace("#{nx_pay}",String.valueOf(t.getNxPay()));
 		sql  = sql.replace("#{cur_ins_recv}",String.valueOf(t.getCurInsRecv()));
 		sql  = sql.replace("#{basic_rate}",String.valueOf(t.getBasicRate()));
 		sql  = sql.replace("#{real_intrate}",String.valueOf(t.getRealIntrate()));
 		sql  = sql.replace("#{rev_cdt_com}",String.valueOf(t.getRevCdtCom()));
 		sql  = sql.replace("#{refn}",String.valueOf(t.getRefn()));
 		sql  = sql.replace("#{ovdue_day}",String.valueOf(t.getOvdueDay()));
 		sql  = sql.replace("#{bling_date}",String.valueOf(t.getBlingDate()));
 		sql  = sql.replace("#{ac_repay_prin}",String.valueOf(t.getAcRepayPrin()));
 		sql  = sql.replace("#{ac_repay_ins}",String.valueOf(t.getAcRepayIns()));
 		sql  = sql.replace("#{loan_pat}",String.valueOf(t.getLoanPat()));
 		sql  = sql.replace("#{importdate}",String.valueOf(t.getImportdate()));
 		sql  = sql.replace("#{ove_defi_ins}",String.valueOf(t.getOveDefiIns()));
 		sql  = sql.replace("#{orloan_id}",String.valueOf(t.getOrloanId()));
 		sql  = sql.replace("#{issue_price}",String.valueOf(t.getIssuePrice()));
 		sql  = sql.replace("#{drawdown_account}",String.valueOf(t.getDrawdownAccount()));
 		sql  = sql.replace("#{ove_defp}",String.valueOf(t.getOveDefp()));
 		sql  = sql.replace("#{ove_defi}",String.valueOf(t.getOveDefi()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 		

 	}
}