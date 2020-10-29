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
 * @Description: 结售汇业务
 * @author onlineGenerator
 * @date 2015-08-26 15:24:08
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_jsh", schema = "")
@SuppressWarnings("serial")
public class JshEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**交易序号*/
	@Excel(name="交易序号")
	private java.lang.String dlno;
	/**客户编号*/
	@Excel(name="客户编号")
	private java.lang.String csnm;
	/**购汇的账户编号*/
	@Excel(name="购汇的账户编号")
	private java.lang.String forgExAcc;
	/**结汇的账户编号*/
	@Excel(name="结汇的账户编号")
	private java.lang.String setExAcc;
	/**交易类别*/
	@Excel(name="交易类别")
	private java.lang.String dtyp;
	/**汇款类型*/
	@Excel(name="汇款类型")
	private java.lang.String ortp;
	/**签约日*/
	@Excel(name="签约日")
	private java.util.Date ddat;
	/**交割日*/
	@Excel(name="交割日")
	private java.util.Date vdat;
	/**买入币种*/
	@Excel(name="买入币种")
	private java.lang.String pucy;
	/**买入金额*/
	@Excel(name="买入金额")
	private java.math.BigDecimal puam;
	/**买入金额折美金*/
	@Excel(name="买入金额折美金")
	private java.math.BigDecimal puamusd;
	/**买入金额折人民币*/
	@Excel(name="买入金额折人民币")
	private java.math.BigDecimal puamcny;
	/**卖出币种*/
	@Excel(name="卖出币种")
	private java.lang.String slcy;
	/**卖出金额*/
	@Excel(name="卖出金额")
	private java.math.BigDecimal slam;
	/**卖出金额折美金*/
	@Excel(name="卖出金额折美金")
	private java.math.BigDecimal slamusd;
	/**卖出金额折人民币*/
	@Excel(name="卖出金额折人民币")
	private java.math.BigDecimal slamcny;
	/**客户买入汇率*/
	@Excel(name="客户买入汇率")
	private java.math.BigDecimal cusBuy;
	/**银行买入汇率*/
	@Excel(name="银行买入汇率")
	private java.math.BigDecimal bkBuy;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String note;
	/**结售汇代码*/
	@Excel(name="结售汇代码")
	private java.lang.String exSetcd;
	/**国际收支申报代码*/
	@Excel(name="国际收支申报代码")
	private java.lang.String declUbop;
	/**结售汇用途*/
	@Excel(name="结售汇用途")
	private java.lang.String exSetapp;
	/**分支机构代码*/
	@Excel(name="分支机构代码")
	private java.lang.String brca;
	/**母行代码*/
	@Excel(name="母行代码")
	private java.lang.String parentbrca;
	/**经办行代码*/
	@Excel(name="经办行代码")
	private java.lang.String brcaCo;
	/**系统日期*/
	@Excel(name="系统日期")
	private java.util.Date sysDate;
	/**营业日期*/
	@Excel(name="营业日期")
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
	 *@return: java.lang.String  交易序号
	 */
	@Column(name ="DLNO",nullable=true,length=50)
	public java.lang.String getDlno(){
		return this.dlno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易序号
	 */
	public void setDlno(java.lang.String dlno){
		this.dlno = dlno;
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
	 *@return: java.lang.String  购汇的账户编号
	 */
	@Column(name ="FORG_EX_ACC",nullable=true,length=50)
	public java.lang.String getForgExAcc(){
		return this.forgExAcc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  购汇的账户编号
	 */
	public void setForgExAcc(java.lang.String forgExAcc){
		this.forgExAcc = forgExAcc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结汇的账户编号
	 */
	@Column(name ="SET_EX_ACC",nullable=true,length=50)
	public java.lang.String getSetExAcc(){
		return this.setExAcc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结汇的账户编号
	 */
	public void setSetExAcc(java.lang.String setExAcc){
		this.setExAcc = setExAcc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易类别
	 */
	@Column(name ="DTYP",nullable=true,length=20)
	public java.lang.String getDtyp(){
		return this.dtyp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易类别
	 */
	public void setDtyp(java.lang.String dtyp){
		this.dtyp = dtyp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  汇款类型
	 */
	@Column(name ="ORTP",nullable=true,length=20)
	public java.lang.String getOrtp(){
		return this.ortp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  汇款类型
	 */
	public void setOrtp(java.lang.String ortp){
		this.ortp = ortp;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签约日
	 */
	@Column(name ="DDAT",nullable=true)
	public java.util.Date getDdat(){
		return this.ddat;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签约日
	 */
	public void setDdat(java.util.Date ddat){
		this.ddat = ddat;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交割日
	 */
	@Column(name ="VDAT",nullable=true)
	public java.util.Date getVdat(){
		return this.vdat;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交割日
	 */
	public void setVdat(java.util.Date vdat){
		this.vdat = vdat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买入币种
	 */
	@Column(name ="PUCY",nullable=true,length=10)
	public java.lang.String getPucy(){
		return this.pucy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买入币种
	 */
	public void setPucy(java.lang.String pucy){
		this.pucy = pucy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  买入金额
	 */
	@Column(name ="PUAM",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getPuam(){
		return this.puam;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  买入金额
	 */
	public void setPuam(java.math.BigDecimal puam){
		this.puam = puam;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  买入金额折美金
	 */
	@Column(name ="PUAMUSD",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getPuamusd() {
		return puamusd;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  买入金额折美金
	 */
	public void setPuamusd(java.math.BigDecimal puamusd) {
		this.puamusd = puamusd;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  买入金额折人民币
	 */
	@Column(name ="PUAMCNY",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getPuamcny() {
		return puamcny;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  买入金额折人民币
	 */
	public void setPuamcny(java.math.BigDecimal puamcny) {
		this.puamcny = puamcny;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖出币种
	 */
	@Column(name ="SLCY",nullable=true,length=10)
	public java.lang.String getSlcy(){
		return this.slcy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖出币种
	 */
	public void setSlcy(java.lang.String slcy){
		this.slcy = slcy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  卖出金额
	 */
	@Column(name ="SLAM",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getSlam(){
		return this.slam;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  卖出金额
	 */
	public void setSlam(java.math.BigDecimal slam){
		this.slam = slam;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  卖出金额折美金
	 */
	@Column(name ="SLAMUSD",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getSlamusd() {
		return slamusd;
	}
	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  卖出金额折美金
	 */
	public void setSlamusd(java.math.BigDecimal slamusd) {
		this.slamusd = slamusd;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  卖出金额折人民币
	 */
	@Column(name ="SLAMCNY",nullable=true,scale=4,length=20)
	public java.math.BigDecimal getSlamcny() {
		return slamcny;
	}
	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  卖出金额折人民币
	 */
	public void setSlamcny(java.math.BigDecimal slamcny) {
		this.slamcny = slamcny;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  客户买入汇率
	 */
	@Column(name ="CUS_BUY",nullable=true,scale=8,length=20)
	public java.math.BigDecimal getCusBuy(){
		return this.cusBuy;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  客户买入汇率
	 */
	public void setCusBuy(java.math.BigDecimal cusBuy){
		this.cusBuy = cusBuy;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  银行买入汇率
	 */
	@Column(name ="BK_BUY",nullable=true,scale=8,length=20)
	public java.math.BigDecimal getBkBuy(){
		return this.bkBuy;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  银行买入汇率
	 */
	public void setBkBuy(java.math.BigDecimal bkBuy){
		this.bkBuy = bkBuy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="NOTE",nullable=true,length=255)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结售汇代码
	 */
	@Column(name ="EX_SETCD",nullable=true,length=10)
	public java.lang.String getExSetcd(){
		return this.exSetcd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结售汇代码
	 */
	public void setExSetcd(java.lang.String exSetcd){
		this.exSetcd = exSetcd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国际收支申报代码
	 */
	@Column(name ="DECL_UBOP",nullable=true,length=10)
	public java.lang.String getDeclUbop(){
		return this.declUbop;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国际收支申报代码
	 */
	public void setDeclUbop(java.lang.String declUbop){
		this.declUbop = declUbop;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结售汇用途
	 */
	@Column(name ="EX_SETAPP",nullable=true,length=10)
	public java.lang.String getExSetapp(){
		return this.exSetapp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结售汇用途
	 */
	public void setExSetapp(java.lang.String exSetapp){
		this.exSetapp = exSetapp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=10)
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
	 *@return: java.lang.String  母行代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=10)
	public java.lang.String getParentbrca(){
		return this.parentbrca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  母行代码
	 */
	public void setParentbrca(java.lang.String parentbrca){
		this.parentbrca = parentbrca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经办行代码
	 */
	@Column(name ="BRCA_CO",nullable=true,length=10)
	public java.lang.String getBrcaCo(){
		return this.brcaCo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经办行代码
	 */
	public void setBrcaCo(java.lang.String brcaCo){
		this.brcaCo = brcaCo;
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
}
