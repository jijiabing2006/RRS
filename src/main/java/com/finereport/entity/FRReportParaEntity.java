package com.finereport.entity;

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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: FR报表参数表
 * @author onlineGenerator
 * @date 2015-10-16 09:51:59
 * @version V1.0   
 *
 */
@Entity
@Table(name = "f_r_report_para", schema = "")
@SuppressWarnings("serial")
public class FRReportParaEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**f_r_report.id*/
	@Excel(name="f_r_report.id")
	private java.lang.String reportid;
	/**参数名*/
	@Excel(name="参数名")
	private java.lang.String paraname;
	/**参数值*/
	@Excel(name="参数值")
	private java.lang.String paravalue;
	/**参数说明*/
	@Excel(name="参数说明")
	private java.lang.String parades;
	/**参数值说明*/
	@Excel(name="参数值说明")
	private java.lang.String valuedes;
	
	private FRReportEntity fRReportEntity;
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
	 *@return: java.lang.String  f_r_report.id
	 */
	@Column(name ="REPORTID",nullable=false,length=32)
	public java.lang.String getReportid(){
		return this.reportid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  f_r_report.id
	 */
	public void setReportid(java.lang.String reportid){
		this.reportid = reportid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数名
	 */
	@Column(name ="PARANAME",nullable=false,length=50)
	public java.lang.String getParaname(){
		return this.paraname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数名
	 */
	public void setParaname(java.lang.String paraname){
		this.paraname = paraname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数值
	 */
	@Column(name ="PARAVALUE",nullable=false,length=100)
	public java.lang.String getParavalue(){
		return this.paravalue;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数值
	 */
	public void setParavalue(java.lang.String paravalue){
		this.paravalue = paravalue;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数说明
	 */
	@Column(name ="PARADES",nullable=true,length=50)
	public java.lang.String getParades(){
		return this.parades;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数说明
	 */
	public void setParades(java.lang.String parades){
		this.parades = parades;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  参数值说明
	 */
	@Column(name ="VALUEDES",nullable=true,length=50)
	public java.lang.String getValuedes(){
		return this.valuedes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  参数值说明
	 */
	public void setValuedes(java.lang.String valuedes){
		this.valuedes = valuedes;
	}
	 @JsonIgnore
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "reportid")
	public FRReportEntity getfRReportEntity() {
		return fRReportEntity;
	}

	public void setfRReportEntity(FRReportEntity fRReportEntity) {
		this.fRReportEntity = fRReportEntity;
	}
}
