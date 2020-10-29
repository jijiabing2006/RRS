package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.jeecgframework.poi.excel.annotation.Excel;

@MappedSuperclass
public abstract class JshBaseEntity extends SafeBaseEntity {
	/** 收款人类型 */
	@Excel(name = "收款人类型")
	private java.lang.String custype;
	/** 个人身份证件号码 */
	@Excel(name = "个人身份证件号码")
	private java.lang.String idcode;
	/** 组织机构代码 */
	@Excel(name = "组织机构代码")
	private java.lang.String custcod;
	/** 收款人名称 */
	@Excel(name = "收款人名称")
	private java.lang.String custnm;
	/** 付款人名称 */
	@Excel(name = "付款人名称")
	private java.lang.String oppuser;

	/**外汇账户开户行*/
	@Excel(name="外汇(人民币)账户开户行")
	private java.lang.String oppbank;
	
	/** 人民币帐号银行卡号 */
	@Excel(name = "人民币帐号银行卡号")
	private java.lang.String lcyacc;
	/** 外汇帐号银行卡号 */
	@Excel(name = "外汇帐号银行卡号")
	private java.lang.String fcyacc;
	/** 结汇汇率 */
	@Excel(name = "结汇汇率")
	private java.math.BigDecimal exrate;

	/** 银行业务编号 */
	@Excel(name = "银行业务编号")
	private java.lang.String buscode;



	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 收款人类型
	 */
	@Column(name = "CUSTYPE", nullable = false, length = 1)
	public java.lang.String getCustype() {
		return this.custype;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 收款人类型
	 */
	public void setCustype(java.lang.String custype) {
		this.custype = custype;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 个人身份证件号码
	 */
	@Column(name = "IDCODE", nullable = true, length = 32)
	public java.lang.String getIdcode() {
		return this.idcode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 个人身份证件号码
	 */
	public void setIdcode(java.lang.String idcode) {
		this.idcode = idcode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 组织机构代码
	 */
	@Column(name = "CUSTCOD", nullable = true, length = 9)
	public java.lang.String getCustcod() {
		return this.custcod;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 组织机构代码
	 */
	public void setCustcod(java.lang.String custcod) {
		this.custcod = custcod;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 收款人名称
	 */
	@Column(name = "CUSTNM", nullable = false, length = 128)
	public java.lang.String getCustnm() {
		return this.custnm;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 收款人名称
	 */
	public void setCustnm(java.lang.String custnm) {
		this.custnm = custnm;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 付款人名称
	 */
	@Column(name = "OPPUSER", nullable = false, length = 128)
	public java.lang.String getOppuser() {
		return this.oppuser;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 付款人名称
	 */
	public void setOppuser(java.lang.String oppuser) {
		this.oppuser = oppuser;
	}

	
	@Column(name = "OPPBANK", nullable = true, length = 255)
	public java.lang.String getOppbank() {
		return oppbank;
	}

	public void setOppbank(java.lang.String oppbank) {
		this.oppbank = oppbank;
	}

	/**
	 * 方法: 取得java.math.BigDecimal
	 * 
	 * @return: java.math.BigDecimal 结汇汇率
	 */
	@Column(name = "EXRATE", nullable = true, scale = 8, length = 13)
	public java.math.BigDecimal getExrate() {
		return this.exrate;
	}

	/**
	 * 方法: 设置java.math.BigDecimal
	 * 
	 * @param: java.math.BigDecimal 结汇汇率
	 */
	public void setExrate(java.math.BigDecimal exrate) {
		this.exrate = exrate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 人民币帐号银行卡号
	 */
	@Column(name = "LCYACC", nullable = true, length = 32)
	public java.lang.String getLcyacc() {
		return this.lcyacc;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 人民币帐号银行卡号
	 */
	public void setLcyacc(java.lang.String lcyacc) {
		this.lcyacc = lcyacc;
	}
	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 外汇帐号银行卡号
	 */
	@Column(name = "FCYACC", nullable = true, length = 32)
	public java.lang.String getFcyacc() {
		return this.fcyacc;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 外汇帐号银行卡号
	 */
	public void setFcyacc(java.lang.String fcyacc) {
		this.fcyacc = fcyacc;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 银行业务编号
	 */
	@Column(name = "BUSCODE", nullable = false, length = 22)
	public java.lang.String getBuscode() {
		return this.buscode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 银行业务编号
	 */
	public void setBuscode(java.lang.String buscode) {
		this.buscode = buscode;
	}

	
}