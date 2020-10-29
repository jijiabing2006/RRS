package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_feedbackloginfo
 * @author onlineGenerator
 * @date 2015-06-24 15:10:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_feedbackloginfo", schema = "")
@SuppressWarnings("serial")
public class FeedbackloginfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**apptype*/
	@Excel(name="apptype")
	private java.lang.String apptype;
	/**filename*/
	@Excel(name="filename")
	private java.lang.String filename;
	/**importdate*/
	@Excel(name="importdate")
	private java.util.Date importdate;
	/**state*/
	@Excel(name="state")
	private java.lang.String state;
	
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
	 *@return: java.lang.String  apptype
	 */
	@Column(name ="APPTYPE",nullable=true,length=255)
	public java.lang.String getApptype(){
		return this.apptype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apptype
	 */
	public void setApptype(java.lang.String apptype){
		this.apptype = apptype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  filename
	 */
	@Column(name ="FILENAME",nullable=true,length=255)
	public java.lang.String getFilename(){
		return this.filename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  filename
	 */
	public void setFilename(java.lang.String filename){
		this.filename = filename;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  importdate
	 */
	@Column(name ="IMPORTDATE",nullable=true)
	public java.util.Date getImportdate(){
		return this.importdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  importdate
	 */
	public void setImportdate(java.util.Date importdate){
		this.importdate = importdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */
	@Column(name ="STATE",nullable=true,length=255)
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
}
