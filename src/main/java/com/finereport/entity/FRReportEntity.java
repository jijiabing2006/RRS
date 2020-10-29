package com.finereport.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;

import javax.xml.soap.Text;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.junit.Ignore;

/**   
 * @Title: Entity
 * @Description: FR报表信息
 * @author onlineGenerator
 * @date 2015-10-16 09:51:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "f_r_report", schema = "")
@SuppressWarnings("serial")
public class FRReportEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**报表表*/
	@Excel(name="报表表")
	private java.lang.String reportname;
	/**全称*/
	@Excel(name="全称")
	private java.lang.String fullname;
	/**频度*/
	@Excel(name="频度")
	private java.lang.String statfreq;
	/**模板名称*/
	@Excel(name="模板名称")
	private java.lang.String templatename;
	/**监管机构*/
	@Excel(name="监管机构")
	private java.lang.String syskind;
	/** 批次*/
	@Excel(name=" 批次")
	private java.lang.String batch;
	
	private List<FRReportParaEntity> frreportparas=new ArrayList<FRReportParaEntity>();
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
	 *@return: java.lang.String  报表表
	 */
	@Column(name ="REPORTNAME",nullable=false,length=50)
	public java.lang.String getReportname(){
		return this.reportname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报表表
	 */
	public void setReportname(java.lang.String reportname){
		this.reportname = reportname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  全称
	 */
	@Column(name ="FULLNAME",nullable=false,length=100)
	public java.lang.String getFullname(){
		return this.fullname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  全称
	 */
	public void setFullname(java.lang.String fullname){
		this.fullname = fullname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  频度
	 */
	@Column(name ="STATFREQ",nullable=false,length=20)
	public java.lang.String getStatfreq(){
		return this.statfreq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  频度
	 */
	public void setStatfreq(java.lang.String statfreq){
		this.statfreq = statfreq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模板名称
	 */
	@Column(name ="TEMPLATENAME",nullable=false,length=20)
	public java.lang.String getTemplatename(){
		return this.templatename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  监管机构
	 */
	@Column(name ="SYSKIND",nullable=true,length=50)
	public java.lang.String getSyskind(){
		return this.syskind;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  监管机构
	 */
	public void setSyskind(java.lang.String syskind){
		this.syskind = syskind;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String   批次
	 */
	@Column(name ="BATCH",nullable=true,length=2)
	public java.lang.String getBatch(){
		return this.batch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String   批次
	 */
	public void setBatch(java.lang.String batch){
		this.batch = batch;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模板名称
	 */
	public void setTemplatename(java.lang.String templatename){
		this.templatename = templatename;
	}
	 @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fRReportEntity")
	public List<FRReportParaEntity> getFrreportparas() {
		return frreportparas;
	}

	public void setFrreportparas(List<FRReportParaEntity> frreportparas) {
		this.frreportparas = frreportparas;
	}
}
