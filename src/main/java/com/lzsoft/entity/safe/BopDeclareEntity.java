package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.jeecgframework.poi.excel.annotation.Excel;

@MappedSuperclass
public abstract class BopDeclareEntity extends SafeBaseEntity {

	/** 付款人常驻国家地区代码 */
	@Excel(name = "付款人常驻国家地区代码")
	private java.lang.String country;
	/** 收款性质 */
	@Excel(name = "收款性质")
	private java.lang.String paytype;
	/** 交易编码1 */
	@Excel(name = "交易编码1")
	private java.lang.String txcode;
	/** 相应金额1 */
	@Excel(name = "相应金额1")
	private java.math.BigDecimal tc1amt;

	/** 是否保税货物项下收汇 */
	@Excel(name = "是否保税货物项下收汇")
	private java.lang.String isref;
	/** 交易编码2 */
	@Excel(name = "交易编码2")
	private java.lang.String txcode2;
	/** 相应金额2 */
	@Excel(name = "相应金额2")
	private java.math.BigDecimal tc2amt;


	/** 填报人 */
	@Excel(name = "填报人")
	private java.lang.String crtuser;
	/** 填报人电话 */
	@Excel(name = "填报人电话")
	private java.lang.String inptelc;
	/** 申报日期 */
	@Excel(name = "申报日期")
	private java.util.Date rptdate;

	/** buscode */
	private java.lang.String buscode;
	/**cap*/
	private  boolean cap=false;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款人常驻国家地区代码
	 */
	@Column(name ="COUNTRY",nullable=false,length=3)
	public java.lang.String getCountry(){
		return this.country;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款人常驻国家地区代码
	 */
	public void setCountry(java.lang.String country){
		this.country = country;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款性质
	 */
	@Column(name ="PAYTYPE",nullable=false,length=1)
	public java.lang.String getPaytype(){
		return this.paytype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款性质
	 */
	public void setPaytype(java.lang.String paytype){
		this.paytype = paytype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易编码1
	 */
	@Column(name ="TXCODE",nullable=false,length=6)
	public java.lang.String getTxcode(){
		return this.txcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易编码1
	 */
	public void setTxcode(java.lang.String txcode){
		this.txcode = txcode;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  相应金额1
	 */
	@Column(name ="TC1AMT",nullable=false,length=22)
	public java.math.BigDecimal getTc1amt(){
		return this.tc1amt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  相应金额1
	 */
	public void setTc1amt(java.math.BigDecimal tc1amt){
		this.tc1amt = tc1amt;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易编码2
	 */
	@Column(name ="TXCODE2",nullable=true,length=6)
	public java.lang.String getTxcode2(){
		return this.txcode2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易编码2
	 */
	public void setTxcode2(java.lang.String txcode2){
		this.txcode2 = txcode2;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  相应金额2
	 */
	@Column(name ="TC2AMT",nullable=true,length=22)
	public java.math.BigDecimal getTc2amt(){
		return this.tc2amt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  相应金额2
	 */
	public void setTc2amt(java.math.BigDecimal tc2amt){
		this.tc2amt = tc2amt;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否保税货物项下收汇
	 */
	@Column(name ="ISREF",nullable=false,length=1)
	public java.lang.String getIsref(){
		return this.isref;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否保税货物项下收汇
	 */
	public void setIsref(java.lang.String isref){
		this.isref = isref;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  填报人
	 */
	@Column(name ="CRTUSER",nullable=false,length=20)
	public java.lang.String getCrtuser(){
		return this.crtuser;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  填报人
	 */
	public void setCrtuser(java.lang.String crtuser){
		this.crtuser = crtuser;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  填报人电话
	 */
	@Column(name ="INPTELC",nullable=false,length=20)
	public java.lang.String getInptelc(){
		return this.inptelc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  填报人电话
	 */
	public void setInptelc(java.lang.String inptelc){
		this.inptelc = inptelc;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申报日期
	 */
	@Column(name ="RPTDATE",nullable=false)
	public java.util.Date getRptdate(){
		return this.rptdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申报日期
	 */
	public void setRptdate(java.util.Date rptdate){
		this.rptdate = rptdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  buscode
	 */
	@Column(name ="BUSCODE",nullable=true,length=50)
	public java.lang.String getBuscode(){
		return this.buscode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  buscode
	 */
	public void setBuscode(java.lang.String buscode){
		this.buscode = buscode;
	}	
	@Column(name ="CAP",nullable=false,length=3)
	public boolean isCap() {
		return cap;
	}

	public void setCap(boolean cap) {
		this.cap = cap;
	}
}