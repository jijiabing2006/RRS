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
 * @Description: SUMMIT_BOOK
 * @author zhangdaihao
 * @date 2016-03-15 14:34:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_book", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtBookEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**账簿*/
	private java.lang.String book;
	/**描述*/
	private java.lang.String descrip;
	/**是否损益账簿*/
	private java.lang.String pandl;
	/**账簿种类*/
	private java.lang.String type;
	/**所属Desk*/
	private java.lang.String parentdesk;
	/**默认的记账类型*/
	private java.lang.String accttype;
	
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
	 *@return: java.lang.String  账簿
	 */
	@Column(name ="BOOK",nullable=true,length=50)
	public java.lang.String getBook(){
		return this.book;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账簿
	 */
	public void setBook(java.lang.String book){
		this.book = book;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIP",nullable=true,length=50)
	public java.lang.String getDescrip(){
		return this.descrip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescrip(java.lang.String descrip){
		this.descrip = descrip;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否损益账簿
	 */
	@Column(name ="PANDL",nullable=true,length=50)
	public java.lang.String getPandl(){
		return this.pandl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否损益账簿
	 */
	public void setPandl(java.lang.String pandl){
		this.pandl = pandl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账簿种类
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账簿种类
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属Desk
	 */
	@Column(name ="PARENTDESK",nullable=true,length=50)
	public java.lang.String getParentdesk(){
		return this.parentdesk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属Desk
	 */
	public void setParentdesk(java.lang.String parentdesk){
		this.parentdesk = parentdesk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  默认的记账类型
	 */
	@Column(name ="ACCTTYPE",nullable=true,length=50)
	public java.lang.String getAccttype(){
		return this.accttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  默认的记账类型
	 */
	public void setAccttype(java.lang.String accttype){
		this.accttype = accttype;
	}
}
