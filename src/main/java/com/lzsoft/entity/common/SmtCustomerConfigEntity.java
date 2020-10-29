package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: Summit客户转换（对台报表用）
 * @author zhangdaihao
 * @date 2016-07-05 15:50:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "s_p_smtcustomer", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtCustomerConfigEntity implements java.io.Serializable {
	/**id*/
	private java.lang.Integer id;
	/**swiftcode*/
	private java.lang.String swiftcode;
	/**customername*/
	private java.lang.String customername;
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,precision=10,scale=0)
	public java.lang.Integer getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  id
	 */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  swiftcode
	 */
	@Column(name ="SWIFTCODE",nullable=false,length=4)
	public java.lang.String getSwiftcode(){
		return this.swiftcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  swiftcode
	 */
	public void setSwiftcode(java.lang.String swiftcode){
		this.swiftcode = swiftcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  customername
	 */
	@Column(name ="CUSTOMERNAME",nullable=true,length=100)
	public java.lang.String getCustomername(){
		return this.customername;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  customername
	 */
	public void setCustomername(java.lang.String customername){
		this.customername = customername;
	}
}
