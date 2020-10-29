package com.lzsoft.entity.common;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_mail_receipter
 * @author onlineGenerator
 * @date 2015-09-07 15:56:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_mail_receipter", schema = "")
@SuppressWarnings("serial")
public class MailReceipterEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**username*/
	@Excel(name="username")
	private java.lang.String username;
	/**type*/
	@Excel(name="type")
	private java.lang.String type;
	/**state*/
	@Excel(name="state")
	private java.lang.String state;
	/**brca*/
	@Excel(name="brca")
	private java.lang.String brca;
	/**msend*/
	@Excel(name="msend")
	private java.lang.String msend;
	/**userid*/
	@Excel(name="userid")
	private java.lang.String userid;
	private java.lang.String email;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  username
	 */
	@Column(name ="USERNAME",nullable=true,length=50)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  username
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  type
	 */
	@Column(name ="TYPE",nullable=true,length=20)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  type
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */
	@Column(name ="STATE",nullable=true,length=3)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  state
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  brca
	 */
	@Column(name ="BRCA",nullable=true,length=20)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  brca
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  msend
	 */
	@Column(name ="MSEND",nullable=true,length=1)
	public java.lang.String getMsend(){
		return this.msend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  msend
	 */
	public void setMsend(java.lang.String msend){
		this.msend = msend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  userid
	 */
	@Column(name ="USERID",nullable=true,length=32)
	public java.lang.String getUserid(){
		return this.userid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  userid
	 */
	public void setUserid(java.lang.String userid){
		this.userid = userid;
	}
	
	@Column(name ="EMAIL",nullable=true,length=100)
	public java.lang.String getEmail(){
		return this.email;
	}

	public void setEmail(java.lang.String email){
		this.email = email;
	}
}
