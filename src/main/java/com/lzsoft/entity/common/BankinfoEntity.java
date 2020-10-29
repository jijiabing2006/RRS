package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_bankinfo
 * @author onlineGenerator
 * @date 2015-06-01 16:22:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_bankinfo", schema = "")
@SuppressWarnings("serial")
public class BankinfoEntity extends IdEntity implements java.io.Serializable {
	/**金融机构代码*/
	@Excel(name="金融机构代码")
	private java.lang.String branchcode;
	/**14位金融机构代码*/
	@Excel(name="14位金融机构代码")
	private java.lang.String bankcodeC14;
	/**15位AML代码*/
	@Excel(name="15位AML代码")
	private java.lang.String amlbankcode;
	/**中文简称*/
	@Excel(name="中文简称")
	private java.lang.String shortcnname;
	/**英文简称*/
	@Excel(name="英文简称")
	private java.lang.String shortenname;
	/**中文全称*/
	@Excel(name="中文全称")
	private java.lang.String fullcnname;
	/**英文全称*/
	@Excel(name="英文全称")
	private java.lang.String fullenname;
	/**机构代码*/
	@Excel(name="机构代码")
	private java.lang.String brca;
	/**上一级机构代码*/
	@Excel(name="上一级机构代码")
	private java.lang.String parentbrca;
	/**行政区划代码*/
	@Excel(name="行政区划代码")
	private java.lang.String areacode;
	/**市级代码*/
	@Excel(name="市级代码")
	private java.lang.String citycode;
	/**市级名称*/
	@Excel(name="市级名称")
	private java.lang.String cityname;
	/**省级代码*/
	@Excel(name="省级代码")
	private java.lang.String provincecode;
	/**省级名称*/
	@Excel(name="省级名称")
	private java.lang.String provincename;
	/**地址*/
	@Excel(name="地址")
	private java.lang.String address;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金融机构代码
	 */
	@Column(name ="BRANCHCODE",nullable=false,length=255)
	public java.lang.String getBranchcode(){
		return this.branchcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金融机构代码
	 */
	public void setBranchcode(java.lang.String branchcode){
		this.branchcode = branchcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  14位金融机构代码
	 */
	@Column(name ="BANKCODE_C14",nullable=false,length=255)
	public java.lang.String getBankcodeC14(){
		return this.bankcodeC14;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  14位金融机构代码
	 */
	public void setBankcodeC14(java.lang.String bankcodeC14){
		this.bankcodeC14 = bankcodeC14;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  15位AML代码
	 */
	@Column(name ="AMLBANKCODE",nullable=true,length=255)
	public java.lang.String getAmlbankcode(){
		return this.amlbankcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  15位AML代码
	 */
	public void setAmlbankcode(java.lang.String amlbankcode){
		this.amlbankcode = amlbankcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中文简称
	 */
	@Column(name ="SHORTCNNAME",nullable=true,length=255)
	public java.lang.String getShortcnname(){
		return this.shortcnname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中文简称
	 */
	public void setShortcnname(java.lang.String shortcnname){
		this.shortcnname = shortcnname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文简称
	 */
	@Column(name ="SHORTENNAME",nullable=true,length=255)
	public java.lang.String getShortenname(){
		return this.shortenname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文简称
	 */
	public void setShortenname(java.lang.String shortenname){
		this.shortenname = shortenname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中文全称
	 */
	@Column(name ="FULLCNNAME",nullable=true,length=255)
	public java.lang.String getFullcnname(){
		return this.fullcnname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中文全称
	 */
	public void setFullcnname(java.lang.String fullcnname){
		this.fullcnname = fullcnname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文全称
	 */
	@Column(name ="FULLENNAME",nullable=true,length=255)
	public java.lang.String getFullenname(){
		return this.fullenname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文全称
	 */
	public void setFullenname(java.lang.String fullenname){
		this.fullenname = fullenname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机构代码
	 */
	@Column(name ="BRCA",nullable=false,length=255)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构代码
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=false,length=255)
	public java.lang.String getParentbrca(){
		return this.parentbrca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上一级机构代码
	 */
	public void setParentbrca(java.lang.String parentbrca){
		this.parentbrca = parentbrca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政区划代码
	 */
	@Column(name ="AREACODE",nullable=true,length=10)
	public java.lang.String getAreacode(){
		return this.areacode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政区划代码
	 */
	public void setAreacode(java.lang.String areacode){
		this.areacode = areacode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市级代码
	 */
	@Column(name ="CITYCODE",nullable=true,length=10)
	public java.lang.String getCitycode(){
		return this.citycode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市级代码
	 */
	public void setCitycode(java.lang.String citycode){
		this.citycode = citycode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  市级名称
	 */
	@Column(name ="CITYNAME",nullable=true,length=50)
	public java.lang.String getCityname(){
		return this.cityname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  市级名称
	 */
	public void setCityname(java.lang.String cityname){
		this.cityname = cityname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省级代码
	 */
	@Column(name ="PROVINCECODE",nullable=true,length=10)
	public java.lang.String getProvincecode(){
		return this.provincecode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省级代码
	 */
	public void setProvincecode(java.lang.String provincecode){
		this.provincecode = provincecode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省级名称
	 */
	@Column(name ="PROVINCENAME",nullable=true,length=50)
	public java.lang.String getProvincename(){
		return this.provincename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省级名称
	 */
	public void setProvincename(java.lang.String provincename){
		this.provincename = provincename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */
	@Column(name ="ADDRESS",nullable=true,length=255)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
}
