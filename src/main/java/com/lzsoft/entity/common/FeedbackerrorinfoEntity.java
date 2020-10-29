package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_feedbackerrorinfo
 * @author onlineGenerator
 * @date 2015-06-19 16:59:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_feedbackerrorinfo", schema = "")
@SuppressWarnings("serial")
public class FeedbackerrorinfoEntity extends IdEntity implements java.io.Serializable {
	/**errdesc*/
	@Excel(name="errdesc")
	private java.lang.String errdesc;
	/**errfield*/
	@Excel(name="errfield")
	private java.lang.String errfield;
	/**errfieldcn*/
	@Excel(name="errfieldcn")
	private java.lang.String errfieldcn;
	/**rptno*/
	@Excel(name="rptno")
	private java.lang.String rptno;
	private java.lang.String processed;
	private FeedbackheadinfoEntity fbh;
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  errdesc
	 */
	@Column(name ="ERRDESC",nullable=true,length=255)
	public java.lang.String getErrdesc(){
		return this.errdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  errdesc
	 */
	public void setErrdesc(java.lang.String errdesc){
		this.errdesc = errdesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  errfield
	 */
	@Column(name ="ERRFIELD",nullable=true,length=255)
	public java.lang.String getErrfield(){
		return this.errfield;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  errfield
	 */
	public void setErrfield(java.lang.String errfield){
		this.errfield = errfield;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  errfieldcn
	 */
	@Column(name ="ERRFIELDCN",nullable=true,length=255)
	public java.lang.String getErrfieldcn(){
		return this.errfieldcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  errfieldcn
	 */
	public void setErrfieldcn(java.lang.String errfieldcn){
		this.errfieldcn = errfieldcn;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  rptno
	 */
	@Column(name ="RPTNO",nullable=true,length=255)
	public java.lang.String getRptno(){
		return this.rptno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  rptno
	 */
	public void setRptno(java.lang.String rptno){
		this.rptno = rptno;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FEEDBACKHEADID")
	public FeedbackheadinfoEntity getFbh() {
		return fbh;
	}

	public void setFbh(FeedbackheadinfoEntity fbh) {
		this.fbh = fbh;
	}

	@Column(name ="PROCESSED",nullable=true,length=10)
	public java.lang.String getProcessed() {
		return processed;
	}

	public void setProcessed(java.lang.String processed) {
		this.processed = processed;
	}
}
