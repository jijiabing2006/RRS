package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

@MappedSuperclass
public abstract class SafeBaseEntity extends IdEntity {
	/** 操作类型 */
	@Excel(name = "操作类型")
	private java.lang.String actiontype;
	/** 修改删除原因 */
	@Excel(name = "修改删除原因")
	private java.lang.String actiondesc;

	/** 申报号码 */
	@Excel(name = "申报号码")
	private java.lang.String rptno;
	/** 分行代码 */
	private java.lang.String brca;
	/** 营业日期 */
	@Excel(name = "营业日期")
	private java.util.Date importdate;
	/** isdel */
	private java.lang.String isdel;
	/** isedit */
	private java.lang.String isedit;
	/** isexport */
	private java.lang.String isexport;
	/** ishandadd */
	private java.lang.String ishandadd;
	/** isvalidation */
	private java.lang.String isvalidation;
	/** filename */
	private java.lang.String filename;
	/** isinsafe */
	private java.lang.String isinsafe;
	/** tfilename */
	private java.lang.String tfilename;
	/** remark */
	private java.lang.String remark;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 操作类型
	 */
	@Column(name = "ACTIONTYPE", nullable = false, length = 1)
	public java.lang.String getActiontype() {
		return this.actiontype;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 操作类型
	 */
	public void setActiontype(java.lang.String actiontype) {
		this.actiontype = actiontype;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 修改删除原因
	 */
	@Column(name = "ACTIONDESC", nullable = true, length = 128)
	public java.lang.String getActiondesc() {
		return this.actiondesc;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 修改删除原因
	 */
	public void setActiondesc(java.lang.String actiondesc) {
		this.actiondesc = actiondesc;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 分行代码
	 */
	@Column(name = "BRCA", nullable = false, length = 20)
	public java.lang.String getBrca() {
		return this.brca;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 分行代码
	 */
	public void setBrca(java.lang.String brca) {
		this.brca = brca;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 营业日期
	 */
	@Column(name = "IMPORTDATE", nullable = false)
	public java.util.Date getImportdate() {
		return this.importdate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 营业日期
	 */
	public void setImportdate(java.util.Date importdate) {
		this.importdate = importdate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String isdel
	 */
	@Column(name = "ISDEL", nullable = true, length = 1)
	public java.lang.String getIsdel() {
		return this.isdel;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String isdel
	 */
	public void setIsdel(java.lang.String isdel) {
		this.isdel = isdel;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String isedit
	 */
	@Column(name = "ISEDIT", nullable = true, length = 1)
	public java.lang.String getIsedit() {
		return this.isedit;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String isedit
	 */
	public void setIsedit(java.lang.String isedit) {
		this.isedit = isedit;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String isexport
	 */
	@Column(name = "ISEXPORT", nullable = true, length = 1)
	public java.lang.String getIsexport() {
		return this.isexport;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String isexport
	 */
	public void setIsexport(java.lang.String isexport) {
		this.isexport = isexport;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String ishandadd
	 */
	@Column(name = "ISHANDADD", nullable = true, length = 1)
	public java.lang.String getIshandadd() {
		return this.ishandadd;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String ishandadd
	 */
	public void setIshandadd(java.lang.String ishandadd) {
		this.ishandadd = ishandadd;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String isvalidation
	 */
	@Column(name = "ISVALIDATION", nullable = true, length = 1)
	public java.lang.String getIsvalidation() {
		return this.isvalidation;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String isvalidation
	 */
	public void setIsvalidation(java.lang.String isvalidation) {
		this.isvalidation = isvalidation;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String filename
	 */
	@Column(name = "FILENAME", nullable = true, length = 255)
	public java.lang.String getFilename() {
		return this.filename;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String filename
	 */
	public void setFilename(java.lang.String filename) {
		this.filename = filename;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String isinsafe
	 */
	@Column(name = "ISINSAFE", nullable = true, length = 1)
	public java.lang.String getIsinsafe() {
		return this.isinsafe;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String isinsafe
	 */
	public void setIsinsafe(java.lang.String isinsafe) {
		this.isinsafe = isinsafe;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String tfilename
	 */
	@Column(name = "TFILENAME", nullable = true, length = 255)
	public java.lang.String getTfilename() {
		return this.tfilename;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String tfilename
	 */
	public void setTfilename(java.lang.String tfilename) {
		this.tfilename = tfilename;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String remark
	 */
	@Column(name = "REMARK", nullable = true, length = 255)
	public java.lang.String getRemark() {
		return this.remark;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String remark
	 */
	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	@Column(name ="RPTNO",nullable=true,length=255)
	public java.lang.String getRptno() {
		return rptno;
	}

	public void setRptno(java.lang.String rptno) {
		this.rptno = rptno;
	}
}