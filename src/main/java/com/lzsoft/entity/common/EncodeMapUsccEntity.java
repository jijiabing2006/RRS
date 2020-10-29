package com.lzsoft.entity.common;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: 组织机构代码mapping社会统一信用代码
 * @author zhangdaihao
 * @date 2017-09-19 14:23:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_encode_map_uscc", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class EncodeMapUsccEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**组织机构代码*/
	private java.lang.String encode;
	/**社会统一信用代码*/
	private java.lang.String citpUscc;
	/**是否生效*/
	private java.util.Date status;
	/**rsv1*/
	private java.lang.String rsv1;
	/**rsv2*/
	private java.lang.String rsv2;
	/**rsv3*/
	private java.lang.String rsv3;
	/**rsv4*/
	private java.lang.String rsv4;
	/**rsv5*/
	private java.lang.String rsv5;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织机构代码
	 */
	@Column(name ="ENCODE",nullable=true,length=9)
	public java.lang.String getEncode(){
		return this.encode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织机构代码
	 */
	public void setEncode(java.lang.String encode){
		this.encode = encode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  社会统一信用代码
	 */
	@Column(name ="CITP_USCC",nullable=true,length=18)
	public java.lang.String getCitpUscc(){
		return this.citpUscc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  社会统一信用代码
	 */
	public void setCitpUscc(java.lang.String citpUscc){
		this.citpUscc = citpUscc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否生效
	 */
	@Column(name ="STATUS",nullable=true,length=10)
	public java.util.Date getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否生效
	 */
	public void setStatus(java.util.Date status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rsv1
	 */
	@Column(name ="RSV1",nullable=true,length=32)
	public java.lang.String getRsv1(){
		return this.rsv1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rsv1
	 */
	public void setRsv1(java.lang.String rsv1){
		this.rsv1 = rsv1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rsv2
	 */
	@Column(name ="RSV2",nullable=true,length=32)
	public java.lang.String getRsv2(){
		return this.rsv2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rsv2
	 */
	public void setRsv2(java.lang.String rsv2){
		this.rsv2 = rsv2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rsv3
	 */
	@Column(name ="RSV3",nullable=true,length=32)
	public java.lang.String getRsv3(){
		return this.rsv3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rsv3
	 */
	public void setRsv3(java.lang.String rsv3){
		this.rsv3 = rsv3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rsv4
	 */
	@Column(name ="RSV4",nullable=true,length=32)
	public java.lang.String getRsv4(){
		return this.rsv4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rsv4
	 */
	public void setRsv4(java.lang.String rsv4){
		this.rsv4 = rsv4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rsv5
	 */
	@Column(name ="RSV5",nullable=true,length=32)
	public java.lang.String getRsv5(){
		return this.rsv5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rsv5
	 */
	public void setRsv5(java.lang.String rsv5){
		this.rsv5 = rsv5;
	}
}
