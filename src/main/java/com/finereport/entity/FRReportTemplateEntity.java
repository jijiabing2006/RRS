package com.finereport.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: FR报表模板信息
 * @author onlineGenerator
 * @date 2015-10-16 09:51:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "f_r_report_template", schema = "")
@SuppressWarnings("serial")
public class FRReportTemplateEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**报表表*/
	@Excel(name="模板名称")
	private java.lang.String cptname;
	/**全称*/
	@Excel(name="模板")
	private byte[]  cpt;//数据库要使用LongBlob
	
	private String extend;// 扩展名
	private String showDetails;// 是否显示明细
	private String hyperlink;// 超链接关联的cptname
	private String regorg;// 超链接关联的cptname
	
	
	/**创建时间*/
	private java.util.Date createDate;
	/**创建人ID*/
	private java.lang.String createBy;
	/**创建人名称*/
	private java.lang.String createName;
	/**修改时间*/
	private java.util.Date updateDate;
	/**修改人ID*/
	private java.lang.String updateBy;
	/**修改人名称*/
	private java.lang.String updateName;
	
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
	@Column(name ="cptname",nullable=false,length=50)
	public java.lang.String getCptname(){
		return this.cptname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报表表
	 */
	public void setCptname(java.lang.String cptname){
		this.cptname = cptname;
	}
	@Column(name = "cpt" )
	@Lob @Basic(fetch=FetchType.LAZY)
	public byte[] getCpt() {
		return cpt;
	}

	public void setCpt(byte[] cpt) {
		this.cpt = cpt;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="create_date",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人ID
	 */
	@Column(name ="create_by",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人ID
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="create_name",nullable=true,length=32)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="update_date",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人ID
	 */
	@Column(name ="update_by",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人ID
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="update_name",nullable=true,length=32)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	@Column(name ="extend",nullable=true,length=10)
	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}
	@Column(name ="show_details",nullable=true,length=2)
	public String getShowDetails() {
		return showDetails;
	}

	public void setShowDetails(String showDetails) {
		this.showDetails = showDetails;
	}
	@Column(name ="hyperlink_cptname",nullable=true,length=50)
	public String getHyperlink() {
		return hyperlink;
	}

	public String getRegorg() {
		return regorg;
	}
	@Column(name ="regorg",nullable=true,length=10)
	public void setRegorg(String regorg) {
		this.regorg = regorg;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}
	
}
