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
 * @Description: SUMMIT-客户信息表
 * @author zhangdaihao
 * @date 2016-03-15 14:37:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_customer", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtCustomerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**分支机构代码*/
	private java.lang.String brca;
	/**客户代码*/
	private java.lang.String custid;
	/**客户类型*/
	private java.lang.String custtype;
	/**客户全名*/
	private java.lang.String fullname;
	/**注册名称*/
	private java.lang.String legalname;
	/**信用限额是否累加到母公司*/
	private java.lang.String creditroll;
	/**逻辑国家*/
	private java.lang.String logicalcountry;
	/**是否逻辑外国*/
	private java.lang.String logicalforeign;
	/**母公司*/
	private java.lang.String parent;
	/**地理国家*/
	private java.lang.String physicalcountry;
	/**是否地理外国*/
	private java.lang.String physicalforeign;
	/**是否银行机构*/
	private java.lang.String bankindicator;
	/**是否附属机构*/
	private java.lang.String companyaffil;
	/**是否内部交易对手*/
	private java.lang.String mirrorback;
	/**工业类型*/
	private java.lang.String finIns;
	/**PBOC分类*/
	private java.lang.String pboc;
	/**金融机构代码*/
	private java.lang.String finorgCode;
	/**国家评级*/
	private java.lang.String savereignRating;
	/**外部评级*/
	private java.lang.String exRating;
	/**金融机构代码*/
	private java.lang.String legPerCode;
	/**法人名称*/
	private java.lang.String orgCode;
	/**group类型*/
	private java.lang.String groupCust;
	/**所有制形式*/
	private java.lang.String ownership;
	/**机构类型*/
	private java.lang.String finInsBank;
	/**系统日期*/
	private java.util.Date sysDate;
	/**营业日期*/
	private java.util.Date importdate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=4)
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
	 *@return: java.lang.String  分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=4)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支机构代码
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户代码
	 */
	@Column(name ="CUSTID",nullable=true,length=50)
	public java.lang.String getCustid(){
		return this.custid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户代码
	 */
	public void setCustid(java.lang.String custid){
		this.custid = custid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户类型
	 */
	@Column(name ="CUSTTYPE",nullable=true,length=50)
	public java.lang.String getCusttype(){
		return this.custtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户类型
	 */
	public void setCusttype(java.lang.String custtype){
		this.custtype = custtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户全名
	 */
	@Column(name ="FULLNAME",nullable=true,length=255)
	public java.lang.String getFullname(){
		return this.fullname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户全名
	 */
	public void setFullname(java.lang.String fullname){
		this.fullname = fullname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  注册名称
	 */
	@Column(name ="LEGALNAME",nullable=true,length=255)
	public java.lang.String getLegalname(){
		return this.legalname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  注册名称
	 */
	public void setLegalname(java.lang.String legalname){
		this.legalname = legalname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用限额是否累加到母公司
	 */
	@Column(name ="CREDITROLL",nullable=true,length=50)
	public java.lang.String getCreditroll(){
		return this.creditroll;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用限额是否累加到母公司
	 */
	public void setCreditroll(java.lang.String creditroll){
		this.creditroll = creditroll;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逻辑国家
	 */
	@Column(name ="LOGICALCOUNTRY",nullable=true,length=50)
	public java.lang.String getLogicalcountry(){
		return this.logicalcountry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逻辑国家
	 */
	public void setLogicalcountry(java.lang.String logicalcountry){
		this.logicalcountry = logicalcountry;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否逻辑外国
	 */
	@Column(name ="LOGICALFOREIGN",nullable=true,length=50)
	public java.lang.String getLogicalforeign(){
		return this.logicalforeign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否逻辑外国
	 */
	public void setLogicalforeign(java.lang.String logicalforeign){
		this.logicalforeign = logicalforeign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  母公司
	 */
	@Column(name ="PARENT",nullable=true,length=50)
	public java.lang.String getParent(){
		return this.parent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  母公司
	 */
	public void setParent(java.lang.String parent){
		this.parent = parent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地理国家
	 */
	@Column(name ="PHYSICALCOUNTRY",nullable=true,length=50)
	public java.lang.String getPhysicalcountry(){
		return this.physicalcountry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地理国家
	 */
	public void setPhysicalcountry(java.lang.String physicalcountry){
		this.physicalcountry = physicalcountry;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否地理外国
	 */
	@Column(name ="PHYSICALFOREIGN",nullable=true,length=50)
	public java.lang.String getPhysicalforeign(){
		return this.physicalforeign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否地理外国
	 */
	public void setPhysicalforeign(java.lang.String physicalforeign){
		this.physicalforeign = physicalforeign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否银行机构
	 */
	@Column(name ="BANKINDICATOR",nullable=true,length=50)
	public java.lang.String getBankindicator(){
		return this.bankindicator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否银行机构
	 */
	public void setBankindicator(java.lang.String bankindicator){
		this.bankindicator = bankindicator;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否附属机构
	 */
	@Column(name ="COMPANYAFFIL",nullable=true,length=50)
	public java.lang.String getCompanyaffil(){
		return this.companyaffil;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否附属机构
	 */
	public void setCompanyaffil(java.lang.String companyaffil){
		this.companyaffil = companyaffil;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否内部交易对手
	 */
	@Column(name ="MIRRORBACK",nullable=true,length=50)
	public java.lang.String getMirrorback(){
		return this.mirrorback;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否内部交易对手
	 */
	public void setMirrorback(java.lang.String mirrorback){
		this.mirrorback = mirrorback;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工业类型
	 */
	@Column(name ="FIN_INS",nullable=true,length=50)
	public java.lang.String getFinIns(){
		return this.finIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工业类型
	 */
	public void setFinIns(java.lang.String finIns){
		this.finIns = finIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  PBOC分类
	 */
	@Column(name ="PBOC",nullable=true,length=50)
	public java.lang.String getPboc(){
		return this.pboc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  PBOC分类
	 */
	public void setPboc(java.lang.String pboc){
		this.pboc = pboc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金融机构代码
	 */
	@Column(name ="FINORG_CODE",nullable=true,length=50)
	public java.lang.String getFinorgCode(){
		return this.finorgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金融机构代码
	 */
	public void setFinorgCode(java.lang.String finorgCode){
		this.finorgCode = finorgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家评级
	 */
	@Column(name ="SAVEREIGN_RATING",nullable=true,length=50)
	public java.lang.String getSavereignRating(){
		return this.savereignRating;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家评级
	 */
	public void setSavereignRating(java.lang.String savereignRating){
		this.savereignRating = savereignRating;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外部评级
	 */
	@Column(name ="EX_RATING",nullable=true,length=50)
	public java.lang.String getExRating(){
		return this.exRating;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外部评级
	 */
	public void setExRating(java.lang.String exRating){
		this.exRating = exRating;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金融机构代码
	 */
	@Column(name ="LEG_PER_CODE",nullable=true,length=50)
	public java.lang.String getLegPerCode(){
		return this.legPerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金融机构代码
	 */
	public void setLegPerCode(java.lang.String legPerCode){
		this.legPerCode = legPerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人名称
	 */
	@Column(name ="ORG_CODE",nullable=true,length=50)
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人名称
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  group类型
	 */
	@Column(name ="GROUP_CUST",nullable=true,length=50)
	public java.lang.String getGroupCust(){
		return this.groupCust;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  group类型
	 */
	public void setGroupCust(java.lang.String groupCust){
		this.groupCust = groupCust;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所有制形式
	 */
	@Column(name ="OWNERSHIP",nullable=true,length=50)
	public java.lang.String getOwnership(){
		return this.ownership;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所有制形式
	 */
	public void setOwnership(java.lang.String ownership){
		this.ownership = ownership;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机构类型
	 */
	@Column(name ="FIN_INS_BANK",nullable=true,length=50)
	public java.lang.String getFinInsBank(){
		return this.finInsBank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构类型
	 */
	public void setFinInsBank(java.lang.String finInsBank){
		this.finInsBank = finInsBank;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  系统日期
	 */
	@Column(name ="SYS_DATE",nullable=true)
	public java.util.Date getSysDate(){
		return this.sysDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  系统日期
	 */
	public void setSysDate(java.util.Date sysDate){
		this.sysDate = sysDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  营业日期
	 */
	@Column(name ="IMPORTDATE",nullable=true)
	public java.util.Date getImportdate(){
		return this.importdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  营业日期
	 */
	public void setImportdate(java.util.Date importdate){
		this.importdate = importdate;
	}
}
