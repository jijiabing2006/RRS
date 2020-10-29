package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_k
 * @author onlineGenerator
 * @date 2015-08-13 09:20:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_k", schema = "")
@SuppressWarnings("serial")
public class BopKEntity extends BopDeclareEntity implements java.io.Serializable {
	/** 交易附言1 */
	@Excel(name = "交易附言1")
	private java.lang.String txrem;
	/** 交易附言2 */
	@Excel(name = "交易附言2")
	private java.lang.String tx2rem;
	/**外汇局批件号*/
	@Excel(name="外汇局批件号")
	private java.lang.String regno;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易附言1
	 */
	@Column(name ="TXREM",nullable=false,length=50)
	public java.lang.String getTxrem(){
		return this.txrem;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易附言1
	 */
	public void setTxrem(java.lang.String txrem){
		this.txrem = txrem;
	}	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易附言2
	 */
	@Column(name ="TX2REM",nullable=true,length=50)
	public java.lang.String getTx2rem(){
		return this.tx2rem;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易附言2
	 */
	public void setTx2rem(java.lang.String tx2rem){
		this.tx2rem = tx2rem;
	}
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
}
