package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.jeecgframework.poi.excel.annotation.Excel;

@MappedSuperclass
public abstract class BopBaseEntity extends SafeBaseEntity {
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
	/** 收入款币种 */
	@Excel(name = "收入款币种")
	private java.lang.String txccy;
	/** 收入款金额 */
	@Excel(name = "收入款金额")
	private java.math.BigDecimal txamt;
	/** 结汇汇率 */
	@Excel(name = "结汇汇率")
	private java.math.BigDecimal exrate;
	/** 结汇金额 */
	@Excel(name = "结汇金额")
	private java.math.BigDecimal lcyamt;
	/** 人民币帐号银行卡号 */
	@Excel(name = "人民币帐号银行卡号")
	private java.lang.String lcyacc;
	/** 现汇金额 */
	@Excel(name = "现汇金额")
	private java.math.BigDecimal fcyamt;
	/** 外汇帐号银行卡号 */
	@Excel(name = "外汇帐号银行卡号")
	private java.lang.String fcyacc;
	/** 其它金额 */
	@Excel(name = "其它金额")
	private java.math.BigDecimal othamt;
	/** 其它帐号银行卡号 */
	@Excel(name = "其它帐号银行卡号")
	private java.lang.String othacc;
	/** 结算方式 */
	@Excel(name = "结算方式")
	private java.lang.String method;
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

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 收入款币种
	 */
	@Column(name = "TXCCY", nullable = false, length = 3)
	public java.lang.String getTxccy() {
		return this.txccy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 收入款币种
	 */
	public void setTxccy(java.lang.String txccy) {
		this.txccy = txccy;
	}

	/**
	 * 方法: 取得java.math.BigDecimal
	 * 
	 * @return: java.math.BigDecimal 收入款金额
	 */
	@Column(name = "TXAMT", nullable = false, length = 22)
	public java.math.BigDecimal getTxamt() {
		return this.txamt;
	}

	/**
	 * 方法: 设置java.math.BigDecimal
	 * 
	 * @param: java.math.BigDecimal 收入款金额
	 */
	public void setTxamt(java.math.BigDecimal txamt) {
		this.txamt = txamt;
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
	 * 方法: 取得java.math.BigDecimal
	 * 
	 * @return: java.math.BigDecimal 结汇金额
	 */
	@Column(name = "LCYAMT", nullable = true, length = 22)
	public java.math.BigDecimal getLcyamt() {
		return this.lcyamt;
	}

	/**
	 * 方法: 设置java.math.BigDecimal
	 * 
	 * @param: java.math.BigDecimal 结汇金额
	 */
	public void setLcyamt(java.math.BigDecimal lcyamt) {
		this.lcyamt = lcyamt;
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
	 * 方法: 取得java.math.BigDecimal
	 * 
	 * @return: java.math.BigDecimal 现汇金额
	 */
	@Column(name = "FCYAMT", nullable = true, length = 22)
	public java.math.BigDecimal getFcyamt() {
		return this.fcyamt;
	}

	/**
	 * 方法: 设置java.math.BigDecimal
	 * 
	 * @param: java.math.BigDecimal 现汇金额
	 */
	public void setFcyamt(java.math.BigDecimal fcyamt) {
		this.fcyamt = fcyamt;
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
	 * 方法: 取得java.math.BigDecimal
	 * 
	 * @return: java.math.BigDecimal 其它金额
	 */
	@Column(name = "OTHAMT", nullable = true, length = 22)
	public java.math.BigDecimal getOthamt() {
		return this.othamt;
	}

	/**
	 * 方法: 设置java.math.BigDecimal
	 * 
	 * @param: java.math.BigDecimal 其它金额
	 */
	public void setOthamt(java.math.BigDecimal othamt) {
		this.othamt = othamt;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 其它帐号银行卡号
	 */
	@Column(name = "OTHACC", nullable = true, length = 32)
	public java.lang.String getOthacc() {
		return this.othacc;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 其它帐号银行卡号
	 */
	public void setOthacc(java.lang.String othacc) {
		this.othacc = othacc;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 结算方式
	 */
	@Column(name = "METHOD", nullable = true, length = 1)
	public java.lang.String getMethod() {
		return this.method;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 结算方式
	 */
	public void setMethod(java.lang.String method) {
		this.method = method;
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