package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 结汇管理信息
 * @author onlineGenerator
 * @date 2015-07-20 14:50:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jsh_g", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class JshGEntity extends SafeBaseEntity implements java.io.Serializable {
	/**外汇局批件号*/
	@Excel(name="外汇局批件号")
	private java.lang.String regno;
	/**交易编码*/
	@Excel(name="交易编码")
	private java.lang.String txcode;
	/**填报人*/
	@Excel(name="填报人")
	private java.lang.String crtuser;
	/**填报人电话*/
	@Excel(name="填报人电话")
	private java.lang.String inptelc;
	/**申报日期*/
	@Excel(name="申报日期")
	private java.util.Date rptdate;
	/**buscode*/
	private java.lang.String buscode;
	/**cap*/
	private  boolean cap=false;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外汇局批件号
	 */
	@Column(name ="REGNO",nullable=true,length=20)
	public java.lang.String getRegno(){
		return this.regno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外汇局批件号
	 */
	public void setRegno(java.lang.String regno){
		this.regno = regno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易编码
	 */
	@Column(name ="TXCODE",nullable=true,length=6)
	public java.lang.String getTxcode(){
		return this.txcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易编码
	 */
	public void setTxcode(java.lang.String txcode){
		this.txcode = txcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  填报人
	 */
	@Column(name ="CRTUSER",nullable=true,length=20)
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
	@Column(name ="INPTELC",nullable=true,length=20)
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
	@Column(name ="RPTDATE",nullable=true)
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
