package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_feedbackheadinfo
 * @author onlineGenerator
 * @date 2015-06-23 09:23:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_feedbackheadinfo", schema = "")
@SuppressWarnings("serial")
@Inheritance(strategy = InheritanceType.JOINED)
public class FeedbackheadinfoEntity extends IdEntity implements java.io.Serializable {
	@Excel(name="apptype")
	private java.lang.String apptype;
	/**currentfile*/
	@Excel(name="currentfile")
	private java.lang.String currentfile;
	/**falrecords*/
	@Excel(name="falrecords")
	private java.lang.Integer falrecords;
	/**filename*/
	@Excel(name="filename")
	private java.lang.String filename;
	/**filename*/
	@Excel(name="tfilename")
	private java.lang.String tfilename;
	/**formaterrs*/
	@Excel(name="formaterrs")
	private java.lang.Integer formaterrs;
	/**importdate*/
	@Excel(name="importdate")
	private java.util.Date importdate;
	/**inouttype*/
	@Excel(name="inouttype")
	private java.lang.String inouttype;
	/**state*/
	@Excel(name="state")
	private java.lang.String state;
	/**sucrecords*/
	@Excel(name="sucrecords")
	private java.lang.Integer sucrecords;
	/**totalrecords*/
	@Excel(name="totalrecords")
	private java.lang.Integer totalrecords;
	
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
	 *@return: java.lang.String  currentfile
	 */
	@Column(name ="CURRENTFILE",nullable=true,length=255)
	public java.lang.String getCurrentfile(){
		return this.currentfile;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  currentfile
	 */
	public void setCurrentfile(java.lang.String currentfile){
		this.currentfile = currentfile;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  falrecords
	 */
	@Column(name ="FALRECORDS",nullable=true,length=10)
	public java.lang.Integer getFalrecords(){
		return this.falrecords;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  falrecords
	 */
	public void setFalrecords(java.lang.Integer falrecords){
		this.falrecords = falrecords;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  tfilename
	 */
	@Column(name ="TFILENAME",nullable=true,length=255)
	public java.lang.String getTfilename(){
		return this.tfilename;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  filename
	 */
	public void setTfilename(java.lang.String tfilename){
		this.tfilename = tfilename;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  formaterrs
	 */
	@Column(name ="FORMATERRS",nullable=true,length=10)
	public java.lang.Integer getFormaterrs(){
		return this.formaterrs;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  formaterrs
	 */
	public void setFormaterrs(java.lang.Integer formaterrs){
		this.formaterrs = formaterrs;
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
	 *@return: java.lang.String  inouttype
	 */
	@Column(name ="INOUTTYPE",nullable=true,length=255)
	public java.lang.String getInouttype(){
		return this.inouttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  inouttype
	 */
	public void setInouttype(java.lang.String inouttype){
		this.inouttype = inouttype;
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  sucrecords
	 */
	@Column(name ="SUCRECORDS",nullable=true,length=10)
	public java.lang.Integer getSucrecords(){
		return this.sucrecords;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  sucrecords
	 */
	public void setSucrecords(java.lang.Integer sucrecords){
		this.sucrecords = sucrecords;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  totalrecords
	 */
	@Column(name ="TOTALRECORDS",nullable=true,length=10)
	public java.lang.Integer getTotalrecords(){
		return this.totalrecords;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  totalrecords
	 */
	public void setTotalrecords(java.lang.Integer totalrecords){
		this.totalrecords = totalrecords;
	}

	
	
	
}
