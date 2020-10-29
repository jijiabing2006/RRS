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
 * @Description: acc_accountopenclose
 * @author onlineGenerator
 * @date 2015-06-30 14:22:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "acc_accountopenclose", schema = "")
@SuppressWarnings("serial")
@ExcelTarget("accCAEntity")
@Inheritance(strategy = InheritanceType.JOINED)
public class AccCAEntity extends SafeBaseEntity implements java.io.Serializable {
	/**金融机构标识码*/
	@Excel(name="金融机构标识码")
	private java.lang.String branchcode;
	/**金融机构名称*/
	@Excel(name="金融机构名称")
	private java.lang.String branchname;
	/**账号*/
	@Excel(name="账号")
	private java.lang.String accountno;
	/**账户状态*/
	@Excel(name="账户状态")
	private java.lang.String accountstat;
	/**开户主体类型*/
	@Excel(name="开户主体类型")
	private java.lang.String amtype;
	/**开户主体代码*/
	@Excel(name="开户主体代码")
	private java.lang.String encode;
	/**开户主体名称*/
	@Excel(name="开户主体名称")
	private java.lang.String enname;
	/**账户性质代码*/
	@Excel(name="账户性质代码")
	private java.lang.String accounttype;
	/**账户类别*/
	@Excel(name="账户类别")
	private java.lang.String accountcata;
	/**币种*/
	@Excel(name="币种")
	private java.lang.String currencycode;
	/**业务发生日期*/
	@Excel(name="业务发生日期")
	private java.util.Date businessdate;
	/**外汇局批件号*/
	@Excel(name="外汇局批件号")
	private java.lang.String filenumber;
	/**限额类型*/
	@Excel(name="限额类型")
	private java.lang.String limittype;
	/**账户限额*/
	@Excel(name="账户限额")
	private java.math.BigDecimal accountlimit;
	
	
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
	 *@return: java.lang.String  金融机构名称
	 */
	@Column(name ="BRANCHNAME",nullable=false,length=128)
	public java.lang.String getBranchname(){
		return this.branchname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金融机构名称
	 */
	public void setBranchname(java.lang.String branchname){
		this.branchname = branchname;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户状态
	 */
	@Column(name ="ACCOUNTSTAT",nullable=false,length=2)
	public java.lang.String getAccountstat(){
		return this.accountstat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户状态
	 */
	public void setAccountstat(java.lang.String accountstat){
		this.accountstat = accountstat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户主体类型
	 */
	@Column(name ="AMTYPE",nullable=false,length=2)
	public java.lang.String getAmtype(){
		return this.amtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户主体类型
	 */
	public void setAmtype(java.lang.String amtype){
		this.amtype = amtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户主体代码
	 */
	@Column(name ="ENCODE",nullable=false,length=18)
	public java.lang.String getEncode(){
		return this.encode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户主体代码
	 */
	public void setEncode(java.lang.String encode){
		this.encode = encode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户主体名称
	 */
	@Column(name ="ENNAME",nullable=false,length=128)
	public java.lang.String getEnname(){
		return this.enname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户主体名称
	 */
	public void setEnname(java.lang.String enname){
		this.enname = enname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户性质代码
	 */
	@Column(name ="ACCOUNTTYPE",nullable=false,length=4)
	public java.lang.String getAccounttype(){
		return this.accounttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户性质代码
	 */
	public void setAccounttype(java.lang.String accounttype){
		this.accounttype = accounttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户类别
	 */
	@Column(name ="ACCOUNTCATA",nullable=false,length=2)
	public java.lang.String getAccountcata(){
		return this.accountcata;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类别
	 */
	public void setAccountcata(java.lang.String accountcata){
		this.accountcata = accountcata;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  业务发生日期
	 */
	@Column(name ="BUSINESSDATE",nullable=false)
	public java.util.Date getBusinessdate(){
		return this.businessdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  业务发生日期
	 */
	public void setBusinessdate(java.util.Date businessdate){
		this.businessdate = businessdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外汇局批件号
	 */
	@Column(name ="FILENUMBER",nullable=true,length=28)
	public java.lang.String getFilenumber(){
		return this.filenumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外汇局批件号
	 */
	public void setFilenumber(java.lang.String filenumber){
		this.filenumber = filenumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  限额类型
	 */
	@Column(name ="LIMITTYPE",nullable=false,length=2)
	public java.lang.String getLimittype(){
		return this.limittype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  限额类型
	 */
	public void setLimittype(java.lang.String limittype){
		this.limittype = limittype;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  账户限额
	 */
	@Column(name ="ACCOUNTLIMIT",nullable=true,scale=2,length=22)
	public java.math.BigDecimal getAccountlimit(){
		return this.accountlimit;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  账户限额
	 */
	public void setAccountlimit(java.math.BigDecimal accountlimit){
		this.accountlimit = accountlimit;
	}
	@Column(name ="ifxacod",length=20)
	public java.lang.String getIfxacod() {
		return ifxacod;
	}

	public void setIfxacod(java.lang.String ifxacod) {
		this.ifxacod = ifxacod;
	}

}
