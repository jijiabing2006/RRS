package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 结汇基础信息
 * @author onlineGenerator
 * @date 2015-07-20 09:46:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jsh_d", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class JshDEntity  extends JshBaseEntity implements java.io.Serializable {

	/**结汇金额*/
	@Excel(name="结汇金额")
	private java.math.BigDecimal fcyamt;
	/**币别*/
	@Excel(name="币别")
	private java.lang.String fcyccy;
	
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  结汇金额
	 */
	@Column(name ="FCYAMT",nullable=false,length=22)
	public java.math.BigDecimal getFcyamt(){
		return this.fcyamt;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  结汇金额
	 */
	public void setFcyamt(java.math.BigDecimal fcyamt){
		this.fcyamt = fcyamt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币别
	 */
	@Column(name ="FCYCCY",nullable=false,length=3)
	public java.lang.String getFcyccy(){
		return this.fcyccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币别
	 */
	public void setFcyccy(java.lang.String fcyccy){
		this.fcyccy = fcyccy;
	}

}
