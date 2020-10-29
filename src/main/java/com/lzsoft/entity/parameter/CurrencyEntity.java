package com.lzsoft.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**   
 * @Title: Entity
 * @Description: 币种代码表
 * @author zhangdaihao
 * @date 2015-04-27 11:07:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "s_p_currency", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("currencyEntity")
public class CurrencyEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**币种代码*/
	@Excel(name="币种代码")
	private java.lang.String currency;
	/**币种名称*/
	@Excel(name="币种名称")
	private java.lang.String name;
	/**数字代码*/
	@Excel(name="数字代码")
	private java.lang.String number;
	@Excel(name="是否常用")
	private java.lang.String status;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种代码
	 */
	@Column(name ="CURRENCY",nullable=false,length=3)
	public java.lang.String getCurrency(){
		return this.currency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种代码
	 */
	public void setCurrency(java.lang.String currency){
		this.currency = currency;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种名称
	 */
	@Column(name ="NAME",nullable=true,length=128)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数字代码
	 */
	@Column(name ="NUMBER",nullable=false,length=3)
	public java.lang.String getNumber(){
		return this.number;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数字代码
	 */
	public void setNumber(java.lang.String number){
		this.number = number;
	}
	@Column(name = "status")
	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	

	
}
