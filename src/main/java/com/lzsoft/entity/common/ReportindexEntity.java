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
 * @Description: r_c_reportindex
 * @author onlineGenerator
 * @date 2015-06-16 17:09:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_reportindex", schema = "")
@SuppressWarnings("serial")
public class ReportindexEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**apptype*/
	@Excel(name="apptype")
	private java.lang.String apptype;
	/**brca*/
	@Excel(name="brca")
	private java.lang.String brca;
	
	private java.lang.String parentbrca;
	/**fileindex*/
	@Excel(name="fileindex")
	private java.lang.String fileindex;
	/**importdate*/
	@Excel(name="importdate")
	private java.util.Date importdate;
	/**reporttype*/
	@Excel(name="reporttype")
	private java.lang.String reporttype;
	/**subjection*/
	@Excel(name="subjection")
	private java.lang.String subjection;
	
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
	 *@return: java.lang.String  brca
	 */
	@Column(name ="BRCA",nullable=true,length=255)
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

	@Column(name ="PARENTBRCA",nullable=true,length=20)
	public java.lang.String getParentbrca() {
		return parentbrca;
	}

	public void setParentbrca(java.lang.String parentbrca) {
		this.parentbrca = parentbrca;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  fileindex
	 */
	@Column(name ="FILEINDEX",nullable=true,length=255)
	public java.lang.String getFileindex(){
		return this.fileindex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  fileindex
	 */
	public void setFileindex(java.lang.String fileindex){
		this.fileindex = fileindex;
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
	 *@return: java.lang.String  reporttype
	 */
	@Column(name ="REPORTTYPE",nullable=true,length=255)
	public java.lang.String getReporttype(){
		return this.reporttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  reporttype
	 */
	public void setReporttype(java.lang.String reporttype){
		this.reporttype = reporttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  subjection
	 */
	@Column(name ="SUBJECTION",nullable=true,length=255)
	public java.lang.String getSubjection(){
		return this.subjection;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  subjection
	 */
	public void setSubjection(java.lang.String subjection){
		this.subjection = subjection;
	}
}
