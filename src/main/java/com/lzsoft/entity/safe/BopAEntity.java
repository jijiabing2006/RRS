package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_a
 * @author onlineGenerator
 * @date 2015-06-08 15:05:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_a", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopAEntity extends BopBaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**国内银行扣费币种*/
	@Excel(name="国内银行扣费币种")
	private java.lang.String inchargeccy;
	/**国内银行扣费金额*/
	@Excel(name="国内银行扣费金额")
	private java.math.BigDecimal inchargeamt;
	/**国外银行扣费币种*/
	@Excel(name="国外银行扣费币种")
	private java.lang.String outchargeccy;
	/**国外银行扣费金额*/
	@Excel(name="国外银行扣费金额")
	private java.math.BigDecimal outchargeamt;

	

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国内银行扣费币种
	 */
	@Column(name ="INCHARGECCY",nullable=true,length=3)
	public java.lang.String getInchargeccy(){
		return this.inchargeccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国内银行扣费币种
	 */
	public void setInchargeccy(java.lang.String inchargeccy){
		this.inchargeccy = inchargeccy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  国内银行扣费金额
	 */
	@Column(name ="INCHARGEAMT",nullable=true,length=22)
	public java.math.BigDecimal getInchargeamt(){
		return this.inchargeamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  国内银行扣费金额
	 */
	public void setInchargeamt(java.math.BigDecimal inchargeamt){
		this.inchargeamt = inchargeamt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国外银行扣费币种
	 */
	@Column(name ="OUTCHARGECCY",nullable=true,length=3)
	public java.lang.String getOutchargeccy(){
		return this.outchargeccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国外银行扣费币种
	 */
	public void setOutchargeccy(java.lang.String outchargeccy){
		this.outchargeccy = outchargeccy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  国外银行扣费金额
	 */
	@Column(name ="OUTCHARGEAMT",nullable=true,length=22)
	public java.math.BigDecimal getOutchargeamt(){
		return this.outchargeamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  国外银行扣费金额
	 */
	public void setOutchargeamt(java.math.BigDecimal outchargeamt){
		this.outchargeamt = outchargeamt;
	}

}
