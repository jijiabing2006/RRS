package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**   
 * @Title: Entity
 * @Description: 账户收支余信息
 * @author onlineGenerator
 * @date 2015-05-05 17:05:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "acc_movementbalance", schema = "")
@SuppressWarnings("serial")
@ExcelTarget("accCBEntity")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccCBEntity extends SafeBaseEntity implements java.io.Serializable {
	/**金融机构标识码*/
	private java.lang.String branchcode;
	/**账号*/
	@Excel(name="账号")
	private java.lang.String accountno;
	/**发生日期*/
	@Excel(name="发生日期")
	private java.util.Date dealdate;
	/**币种*/
	@Excel(name="币种")
	private java.lang.String currencycode;
	/**当日贷方发生额*/
	@Excel(name="当日贷方发生额")
	private java.math.BigDecimal credit;
	/**当日借方发生额*/
	@Excel(name="当日借方发生额")
	private java.math.BigDecimal debit;
	/**账户余额*/
	@Excel(name="账户余额")
	private java.math.BigDecimal balance;
	/**上一日余额*/
	@Excel(name="上一日余额")
	private java.math.BigDecimal lastbalance;
	/**已上报余额*/
	@Excel(name="已上报余额")
	private java.math.BigDecimal safebalance;
	/**state*/
	private java.lang.String state;
	/**islastexport*/
	private java.lang.String islastexport;
	
	/**账号*/
	@Excel(name="IFX账号")
	private java.lang.String ifxacod;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金融机构标识码
	 */
	@Column(name ="BRANCHCODE",nullable=false,length=12)
	public java.lang.String getBranchcode(){
		return this.branchcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金融机构标识码
	 */
	public void setBranchcode(java.lang.String branchcode){
		this.branchcode = branchcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */
	@Column(name ="ACCOUNTNO",nullable=false,length=64)
	public java.lang.String getAccountno(){
		return this.accountno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setAccountno(java.lang.String accountno){
		this.accountno = accountno;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发生日期
	 */
	@Column(name ="DEALDATE",nullable=false)
	public java.util.Date getDealdate(){
		return this.dealdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发生日期
	 */
	public void setDealdate(java.util.Date dealdate){
		this.dealdate = dealdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	@Column(name ="CURRENCYCODE",nullable=false,length=3)
	public java.lang.String getCurrencycode(){
		return this.currencycode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setCurrencycode(java.lang.String currencycode){
		this.currencycode = currencycode;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当日贷方发生额
	 */
	@Column(name ="CREDIT",nullable=false,scale=2,length=22)
	public java.math.BigDecimal getCredit(){
		return this.credit;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当日贷方发生额
	 */
	public void setCredit(java.math.BigDecimal credit){
		this.credit = credit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  当日借方发生额
	 */
	@Column(name ="DEBIT",nullable=false,scale=2,length=22)
	public java.math.BigDecimal getDebit(){
		return this.debit;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  当日借方发生额
	 */
	public void setDebit(java.math.BigDecimal debit){
		this.debit = debit;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  账户余额
	 */
	@Column(name ="BALANCE",nullable=false,scale=2,length=22)
	public java.math.BigDecimal getBalance(){
		return this.balance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  账户余额
	 */
	public void setBalance(java.math.BigDecimal balance){
		this.balance = balance;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  上一日余额
	 */
	@Column(name ="LASTBALANCE",nullable=true,scale=2,length=22)
	public java.math.BigDecimal getLastbalance(){
		return this.lastbalance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  上一日余额
	 */
	public void setLastbalance(java.math.BigDecimal lastbalance){
		this.lastbalance = lastbalance;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  已上报余额
	 */
	@Column(name ="SAFEBALANCE",nullable=true,scale=2,length=22)
	public java.math.BigDecimal getSafebalance(){
		return this.safebalance;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  已上报余额
	 */
	public void setSafebalance(java.math.BigDecimal safebalance){
		this.safebalance = safebalance;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */
	@Column(name ="STATE",nullable=true,length=255)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  state
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  islastexport
	 */
	@Column(name ="ISLASTEXPORT",nullable=true,length=255)
	public java.lang.String getIslastexport(){
		return this.islastexport;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  islastexport
	 */
	public void setIslastexport(java.lang.String islastexport){
		this.islastexport = islastexport;
	}
	@Column(name ="ifxacod",length=20)
	public java.lang.String getIfxacod() {
		return ifxacod;
	}

	public void setIfxacod(java.lang.String ifxacod) {
		this.ifxacod = ifxacod;
	}

}
