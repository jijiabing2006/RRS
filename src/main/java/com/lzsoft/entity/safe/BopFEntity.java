package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_f
 * @author onlineGenerator
 * @date 2015-06-08 15:06:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_f", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopFEntity extends BopBaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**实际付款币种*/
	@Excel(name="实际付款币种")
	private java.lang.String actuccy;
	/**实际付款金额*/
	@Excel(name="实际付款金额")
	private java.math.BigDecimal actuamt;
	/**扣费币种*/
	@Excel(name="扣费币种")
	private java.lang.String outchargeccy;
	/**扣费金额*/
	@Excel(name="扣费金额")
	private java.math.BigDecimal outchargeamt;
	/**信用证保函编号*/
	@Excel(name="信用证保函编号")
	private java.lang.String lcbgno;
	/**开证日期*/
	@Excel(name="开证日期")
	private java.lang.String issdate;
	/**期限*/
	@Excel(name="期限")
	private java.lang.String tenor;

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际付款币种
	 */
	@Column(name ="ACTUCCY",nullable=false,length=22)
	public java.lang.String getActuccy(){
		return this.actuccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际付款币种
	 */
	public void setActuccy(java.lang.String actuccy){
		this.actuccy = actuccy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  实际付款金额
	 */
	@Column(name ="ACTUAMT",nullable=false,length=22)
	public java.math.BigDecimal getActuamt(){
		return this.actuamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  实际付款金额
	 */
	public void setActuamt(java.math.BigDecimal actuamt){
		this.actuamt = actuamt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扣费币种
	 */
	@Column(name ="OUTCHARGECCY",nullable=true,length=3)
	public java.lang.String getOutchargeccy(){
		return this.outchargeccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扣费币种
	 */
	public void setOutchargeccy(java.lang.String outchargeccy){
		this.outchargeccy = outchargeccy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  扣费金额
	 */
	@Column(name ="OUTCHARGEAMT",nullable=true,length=22)
	public java.math.BigDecimal getOutchargeamt(){
		return this.outchargeamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  扣费金额
	 */
	public void setOutchargeamt(java.math.BigDecimal outchargeamt){
		this.outchargeamt = outchargeamt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用证保函编号
	 */
	@Column(name ="LCBGNO",nullable=true,length=20)
	public java.lang.String getLcbgno(){
		return this.lcbgno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证保函编号
	 */
	public void setLcbgno(java.lang.String lcbgno){
		this.lcbgno = lcbgno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开证日期
	 */
	@Column(name ="ISSDATE",nullable=true,length=8)
	public java.lang.String getIssdate(){
		return this.issdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开证日期
	 */
	public void setIssdate(java.lang.String issdate){
		this.issdate = issdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  期限
	 */
	@Column(name ="TENOR",nullable=true,length=10)
	public java.lang.String getTenor(){
		return this.tenor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期限
	 */
	public void setTenor(java.lang.String tenor){
		this.tenor = tenor;
	}
}
