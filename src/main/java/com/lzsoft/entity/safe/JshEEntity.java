package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 购汇基础信息
 * @author onlineGenerator
 * @date 2015-07-20 09:47:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jsh_e", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class JshEEntity extends JshBaseEntity implements java.io.Serializable {
	
	/**购汇金额*/
	@Excel(name="购汇金额")
	private java.math.BigDecimal lcyamt;
	/**购汇币别*/
	@Excel(name="购汇币别")
	private java.lang.String lcyccy;

	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  购汇金额
	 */
	@Column(name ="LCYAMT",nullable=false,length=22)
	public java.math.BigDecimal getLcyamt(){
		return this.lcyamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  购汇金额
	 */
	public void setLcyamt(java.math.BigDecimal lcyamt){
		this.lcyamt = lcyamt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  购汇币别
	 */
	@Column(name ="LCYCCY",nullable=false,length=3)
	public java.lang.String getLcyccy(){
		return this.lcyccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购汇币别
	 */
	public void setLcyccy(java.lang.String lcyccy){
		this.lcyccy = lcyccy;
	}
	
}
