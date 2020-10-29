package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.jeecgframework.poi.excel.annotation.Excel;

@MappedSuperclass
public abstract class BopControlEntity extends SafeBaseEntity {

	/**合同号*/
	@Excel(name="合同号")
	private java.lang.String contrno;
	/**发票号*/
	@Excel(name="发票号")
	private java.lang.String invoino;
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
	 *@return: java.lang.String  合同号
	 */
	@Column(name ="CONTRNO",nullable=false,length=20)
	public java.lang.String getContrno(){
		return this.contrno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同号
	 */
	public void setContrno(java.lang.String contrno){
		this.contrno = contrno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票号
	 */
	@Column(name ="INVOINO",nullable=false,length=35)
	public java.lang.String getInvoino(){
		return this.invoino;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票号
	 */
	public void setInvoino(java.lang.String invoino){
		this.invoino = invoino;
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