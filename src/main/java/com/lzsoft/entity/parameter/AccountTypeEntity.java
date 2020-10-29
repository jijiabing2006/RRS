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
 * @Description: 账户性质
 * @author zhangdaihao
 * @date 2015-05-04 10:49:38
 * @version V1.0   
 *
 */
@Entity
@Table(name = "s_p_accounttype", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
@ExcelTarget("currencyEntity")
public class AccountTypeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**账户性质代码*/
	@Excel(name="账户性质代码",type=1)
	private java.lang.String code;
	/**账户性质名称*/
	@Excel(name="账户性质名称")
	private java.lang.String name;
	
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
	 *@return: java.lang.String  账户性质代码
	 */
	@Column(name ="CODE",nullable=false,length=3)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户性质代码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户性质名称
	 */
	@Column(name ="NAME",nullable=true,length=128)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户性质名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
}
