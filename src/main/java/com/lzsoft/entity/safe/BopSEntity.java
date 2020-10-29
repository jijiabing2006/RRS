package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_s
 * @author onlineGenerator
 * @date 2015-08-13 09:20:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_s", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopSEntity  extends BopDeclareEntity implements java.io.Serializable {
	
	/**境内收入类型*/
	@Excel(name="境内收入类型")
	private java.lang.String payattr;

	/**合同号*/
	@Excel(name="合同号")
	private java.lang.String contrno;
	/**发票号*/
	@Excel(name="发票号")
	private java.lang.String invoino;
	/**提运单号*/
	@Excel(name="提运单号")
	private java.lang.String billno;
	/**合同金额*/
	@Excel(name="合同金额")
	private java.math.BigDecimal contamt;
	
	/**外汇局批件号*/
	@Excel(name="外汇局批件号")
	private java.lang.String regno;
	
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
	 *@return: java.lang.String  提运单号
	 */
	@Column(name ="BILLNO",nullable=true,length=20)
	public java.lang.String getBillno(){
		return this.billno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提运单号
	 */
	public void setBillno(java.lang.String billno){
		this.billno = billno;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  合同金额
	 */
	@Column(name ="CONTAMT",nullable=true,length=22)
	public java.math.BigDecimal getContamt(){
		return this.contamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  合同金额
	 */
	public void setContamt(java.math.BigDecimal contamt){
		this.contamt = contamt;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收入类型
	 */
	@Column(name ="PAYATTR",nullable=true,length=1)
	public java.lang.String getPayattr(){
		return this.payattr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收入类型
	 */
	public void setPayattr(java.lang.String payattr){
		this.payattr = payattr;
	}/**
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
}
