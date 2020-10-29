package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_p
 * @author onlineGenerator
 * @date 2015-08-13 09:21:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_p", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopPEntity extends BopControlEntity implements java.io.Serializable {
	/**提运单号*/
	@Excel(name="提运单号")
	private java.lang.String billno;
	/**合同金额*/
	@Excel(name="合同金额")
	private java.math.BigDecimal contamt;
	
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
	
}
