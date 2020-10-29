package com.lzsoft.entity.common;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 授信额度表
 * @author zhangdaihao
 * @date 2016-09-06 10:48:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_limit", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class LimitEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**客户编号*/
	private java.lang.String csnm;
	/**客户名称*/
	private java.lang.String ctnm;
	/**开户分支机构代码*/
	private java.lang.String brca;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**交易分支机构代码*/
	private java.lang.String brcaCo;
	/**授信编号*/
	private java.lang.String lineCode;
	/**授信类型编号*/
	private java.lang.String ftypCode;
	/**授信类型*/
	private java.lang.String ftyp;
	/**授信币种*/
	private java.lang.String fccy;
	/**授信金额*/
	private BigDecimal famt;
	/**授信金额CNY*/
	private BigDecimal famtcny;
	/**授信金额USD*/
	private BigDecimal famtusd;
	/**授信余额*/
	private BigDecimal fuam;
	/**授信余额CNY*/
	private BigDecimal fuamcny;
	/**授信余额USD*/
	private BigDecimal fuamusd;
	/**贷款承诺是否可撤销*/
	private java.lang.String revoLoancmtm;
	/**贷款合同编号*/
	private java.lang.String loanCttNum;
	/**集团客户编号*/
	private java.lang.String liabilityNum;
	/**最高层额度记录*/
	private java.lang.String creditLine;
	/**最后提款日期*/
	private java.util.Date drawDate;
	/**到期日*/
	private java.util.Date expiryDate;
	/**系统日期*/
	private java.util.Date sysDate;
	/**营业日期*/
	private java.util.Date importdate;
	/**LOAN_GAR_MTD*/
	private java.lang.String loanGarMtd;
	
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
	 *@return: java.lang.String  客户编号
	 */
	@Column(name ="CSNM",nullable=false,length=20)
	public java.lang.String getCsnm(){
		return this.csnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户编号
	 */
	public void setCsnm(java.lang.String csnm){
		this.csnm = csnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */
	@Column(name ="CTNM",nullable=true,length=255)
	public java.lang.String getCtnm(){
		return this.ctnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCtnm(java.lang.String ctnm){
		this.ctnm = ctnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=10)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户分支机构代码
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=false,length=10)
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
	 *@return: java.lang.String  交易分支机构代码
	 */
	@Column(name ="BRCA_CO",nullable=false,length=10)
	public java.lang.String getBrcaCo(){
		return this.brcaCo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易分支机构代码
	 */
	public void setBrcaCo(java.lang.String brcaCo){
		this.brcaCo = brcaCo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信编号
	 */
	@Column(name ="LINE_CODE",nullable=true,length=50)
	public java.lang.String getLineCode(){
		return this.lineCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信编号
	 */
	public void setLineCode(java.lang.String lineCode){
		this.lineCode = lineCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信类型
	 */
	@Column(name ="FTYP",nullable=true,length=50)
	public java.lang.String getFtyp(){
		return this.ftyp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信类型
	 */
	public void setFtyp(java.lang.String ftyp){
		this.ftyp = ftyp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信类型编号
	 */
	@Column(name ="FTYP_CODE",nullable=true,length=50)
	public java.lang.String getFtypCode() {
		return ftypCode;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信类型编号
	 */
	public void setFtypCode(java.lang.String ftypCode) {
		this.ftypCode = ftypCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信币种
	 */
	@Column(name ="FCCY",nullable=true,length=10)
	public java.lang.String getFccy(){
		return this.fccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信币种
	 */
	public void setFccy(java.lang.String fccy){
		this.fccy = fccy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额
	 */
	@Column(name ="FAMT",nullable=true,precision=20,scale=4)
	public BigDecimal getFamt(){
		return this.famt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额
	 */
	public void setFamt(BigDecimal famt){
		this.famt = famt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额CNY
	 */
	@Column(name ="FAMTCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFamtcny(){
		return this.famtcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额CNY
	 */
	public void setFamtcny(BigDecimal famtcny){
		this.famtcny = famtcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额USD
	 */
	@Column(name ="FAMTUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFamtusd(){
		return this.famtusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额USD
	 */
	public void setFamtusd(BigDecimal famtusd){
		this.famtusd = famtusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信余额
	 */
	@Column(name ="FUAM",nullable=true,precision=20,scale=4)
	public BigDecimal getFuam(){
		return this.fuam;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信余额
	 */
	public void setFuam(BigDecimal fuam){
		this.fuam = fuam;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信余额CNY
	 */
	@Column(name ="FUAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFuamcny(){
		return this.fuamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信余额CNY
	 */
	public void setFuamcny(BigDecimal fuamcny){
		this.fuamcny = fuamcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信余额USD
	 */
	@Column(name ="FUAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFuamusd(){
		return this.fuamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信余额USD
	 */
	public void setFuamusd(BigDecimal fuamusd){
		this.fuamusd = fuamusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贷款承诺是否可撤销
	 */
	@Column(name ="REVO_LOANCMTM",nullable=true,length=100)
	public java.lang.String getRevoLoancmtm(){
		return this.revoLoancmtm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贷款承诺是否可撤销
	 */
	public void setRevoLoancmtm(java.lang.String revoLoancmtm){
		this.revoLoancmtm = revoLoancmtm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贷款合同编号
	 */
	@Column(name ="LOAN_CTT_NUM",nullable=true,length=200)
	public java.lang.String getLoanCttNum(){
		return this.loanCttNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贷款合同编号
	 */
	public void setLoanCttNum(java.lang.String loanCttNum){
		this.loanCttNum = loanCttNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集团客户编号
	 */
	@Column(name ="LIABILITY_NUM",nullable=true,length=100)
	public java.lang.String getLiabilityNum(){
		return this.liabilityNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集团客户编号
	 */
	public void setLiabilityNum(java.lang.String liabilityNum){
		this.liabilityNum = liabilityNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最高层额度记录
	 */
	@Column(name ="CREDIT_LINE",nullable=true,length=100)
	public java.lang.String getCreditLine(){
		return this.creditLine;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最高层额度记录
	 */
	public void setCreditLine(java.lang.String creditLine){
		this.creditLine = creditLine;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最后提款日期
	 */
	@Column(name ="DRAW_DATE",nullable=true)
	public java.util.Date getDrawDate(){
		return this.drawDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最后提款日期
	 */
	public void setDrawDate(java.util.Date drawDate){
		this.drawDate = drawDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	@Column(name ="EXPIRY_DATE",nullable=true)
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}
	
	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  系统日期
	 */
	@Column(name ="SYS_DATE",nullable=false)
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
	@Column(name ="IMPORTDATE",nullable=false)
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
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  LOAN_GAR_MTD
	 */
	@Column(name ="LOAN_GAR_MTD",nullable=true,length=100)
	public java.lang.String getloanGarMtd(){
		return this.loanGarMtd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  LOAN_GAR_MTD
	 */
	public void setloanGarMtd(java.lang.String loanGarMtd){
		this.loanGarMtd = loanGarMtd;
	}
	
	
	
	
}
