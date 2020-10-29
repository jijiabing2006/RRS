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
 * @Description: 担保交易
 * @author zhangdaihao
 * @date 2016-08-23 15:06:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_guarantee", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class GuaranteeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**客户名称*/
	private java.lang.String ctnm;
	/**客户代码*/
	private java.lang.String csnm;
	/**贷款或表外业务合同号*/
	private java.lang.String cnlobs;
	/**担保合同号*/
	private java.lang.String grtc;
	/**担保合同类型*/
	private java.lang.String wart;
	/**押品大类代码*/
	private java.lang.String mrtcode;
	/**押品小类代码*/
	private java.lang.String mrttype;
	/**押品名称*/
	private java.lang.String mrtName;
	/**押品代码*/
	private java.lang.String mrtCd;
	/**押品权属人或保证人名称*/
	private java.lang.String namOwner;
	/**押品权属人或保证人证件类型*/
	private java.lang.String idtpOwner;
	/**押品权属人或保证人证件代码*/
	private java.lang.String idOwner;
	/**押品评估频率*/
	private java.lang.String aceFreq;
	/**押品评估价值或保证金额*/
	private BigDecimal estVal;
	/**押品评估价值或保证金额折人民币*/
	private BigDecimal estValcny;
	/**押品评估价值或保证金额折美金*/
	private BigDecimal estValusd;
	/**首次估值日期*/
	private java.util.Date fstValdate;
	/**最新估值日期*/
	private java.util.Date updValdate;
	/**估值到期日期*/
	private java.util.Date matdatVal;
	/**分支机构代码*/
	private java.lang.String brca;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**交易分支机构代码*/
	private java.lang.String brcaCo;
	/**担保合同号*/
	private java.lang.String grtcNo;
	/**抵押物币种*/
	private java.lang.String ccy;
	/**本行担保凭证号*/
	private java.lang.String applicationId;
	/**额度编号*/
	private java.lang.String lineRef;
	/**押品权属人（或保证人）*/
	private java.lang.String colCust;
	/**合同签订日期*/
	private java.util.Date valDate;
	/**合同终止日期*/
	private java.util.Date expDate;
	/**抵押币种*/
	private java.lang.String insCcy;
	/**抵押金额*/
	private java.lang.String insAmt;
	/**登记机关*/
	private java.lang.String issueInstit;
	/**登记日期*/
	private java.util.Date regnDate;
	/**抵押物说明*/
	private java.lang.String colAdd;
	/**担保品终止日期*/
	private java.util.Date colExpDate;
	/**最后修改时间*/
	private java.lang.String lastTime;
	/**市场价值*/
	private BigDecimal nominalVal;
	/**担保品设定币种*/
	private java.lang.String ledgCcy;
	/**担保品设定价值*/
	private BigDecimal ledgVal;
	/**系统日期*/
	private java.util.Date sysDate;
	/**营业日期*/
	private java.util.Date importdate;
	/**RISK_LEV*/
	private java.lang.String riskLev;
	
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
	public void setRiskLev(java.lang.String riskLev){
		this.riskLev = riskLev;
	}
	
	
	@Column(name ="RISK_LEV",nullable=true,length=100)
	public java.lang.String getRiskLev() {
		return riskLev;
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
	 *@return: java.lang.String  客户代码
	 */
	@Column(name ="CSNM",nullable=false,length=20)
	public java.lang.String getCsnm(){
		return this.csnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户代码
	 */
	public void setCsnm(java.lang.String csnm){
		this.csnm = csnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贷款或表外业务合同号
	 */
	@Column(name ="CNLOBS",nullable=true,length=50)
	public java.lang.String getCnlobs(){
		return this.cnlobs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贷款或表外业务合同号
	 */
	public void setCnlobs(java.lang.String cnlobs){
		this.cnlobs = cnlobs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  担保合同号
	 */
	@Column(name ="GRTC",nullable=true,length=50)
	public java.lang.String getGrtc(){
		return this.grtc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  担保合同号
	 */
	public void setGrtc(java.lang.String grtc){
		this.grtc = grtc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  担保合同类型
	 */
	@Column(name ="WART",nullable=true,length=20)
	public java.lang.String getWart(){
		return this.wart;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  担保合同类型
	 */
	public void setWart(java.lang.String wart){
		this.wart = wart;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品大类代码
	 */
	@Column(name ="MRTCODE",nullable=true,length=20)
	public java.lang.String getMrtcode(){
		return this.mrtcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品大类代码
	 */
	public void setMrtcode(java.lang.String mrtcode){
		this.mrtcode = mrtcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品小类代码
	 */
	@Column(name ="MRTTYPE",nullable=true,length=20)
	public java.lang.String getMrttype(){
		return this.mrttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品小类代码
	 */
	public void setMrttype(java.lang.String mrttype){
		this.mrttype = mrttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品名称
	 */
	@Column(name ="MRT_NAME",nullable=true,length=255)
	public java.lang.String getMrtName(){
		return this.mrtName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品名称
	 */
	public void setMrtName(java.lang.String mrtName){
		this.mrtName = mrtName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品代码
	 */
	@Column(name ="MRT_CD",nullable=true,length=50)
	public java.lang.String getMrtCd(){
		return this.mrtCd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品代码
	 */
	public void setMrtCd(java.lang.String mrtCd){
		this.mrtCd = mrtCd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品权属人或保证人名称
	 */
	@Column(name ="NAM_OWNER",nullable=true,length=255)
	public java.lang.String getNamOwner(){
		return this.namOwner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品权属人或保证人名称
	 */
	public void setNamOwner(java.lang.String namOwner){
		this.namOwner = namOwner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品权属人或保证人证件类型
	 */
	@Column(name ="IDTP_OWNER",nullable=true,length=50)
	public java.lang.String getIdtpOwner(){
		return this.idtpOwner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品权属人或保证人证件类型
	 */
	public void setIdtpOwner(java.lang.String idtpOwner){
		this.idtpOwner = idtpOwner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品权属人或保证人证件代码
	 */
	@Column(name ="ID_OWNER",nullable=true,length=50)
	public java.lang.String getIdOwner(){
		return this.idOwner;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品权属人或保证人证件代码
	 */
	public void setIdOwner(java.lang.String idOwner){
		this.idOwner = idOwner;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品评估频率
	 */
	@Column(name ="ACE_FREQ",nullable=true,length=50)
	public java.lang.String getAceFreq(){
		return this.aceFreq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品评估频率
	 */
	public void setAceFreq(java.lang.String aceFreq){
		this.aceFreq = aceFreq;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  押品评估价值或保证金额
	 */
	@Column(name ="EST_VAL",nullable=true,precision=20,scale=4)
	public BigDecimal getEstVal(){
		return this.estVal;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  押品评估价值或保证金额
	 */
	public void setEstVal(BigDecimal estVal){
		this.estVal = estVal;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  押品评估价值或保证金额折人民币
	 */
	@Column(name ="EST_VALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEstValcny(){
		return this.estValcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  押品评估价值或保证金额折人民币
	 */
	public void setEstValcny(BigDecimal estValcny){
		this.estValcny = estValcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  押品评估价值或保证金额折美金
	 */
	@Column(name ="EST_VALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEstValusd(){
		return this.estValusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  押品评估价值或保证金额折美金
	 */
	public void setEstValusd(BigDecimal estValusd){
		this.estValusd = estValusd;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  首次估值日期
	 */
	@Column(name ="FST_VALDATE",nullable=true)
	public java.util.Date getFstValdate(){
		return this.fstValdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  首次估值日期
	 */
	public void setFstValdate(java.util.Date fstValdate){
		this.fstValdate = fstValdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  最新估值日期
	 */
	@Column(name ="UPD_VALDATE",nullable=true)
	public java.util.Date getUpdValdate(){
		return this.updValdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  最新估值日期
	 */
	public void setUpdValdate(java.util.Date updValdate){
		this.updValdate = updValdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  估值到期日期
	 */
	@Column(name ="MATDAT_VAL",nullable=true)
	public java.util.Date getMatdatVal(){
		return this.matdatVal;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  估值到期日期
	 */
	public void setMatdatVal(java.util.Date matdatVal){
		this.matdatVal = matdatVal;
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
	 *@return: java.lang.String  担保合同号
	 */
	@Column(name ="GRTC_NO",nullable=true,length=50)
	public java.lang.String getGrtcNo(){
		return this.grtcNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  担保合同号
	 */
	public void setGrtcNo(java.lang.String grtcNo){
		this.grtcNo = grtcNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押物币种
	 */
	@Column(name ="CCY",nullable=true,length=3)
	public java.lang.String getCcy(){
		return this.ccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押物币种
	 */
	public void setCcy(java.lang.String ccy){
		this.ccy = ccy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本行担保凭证号
	 */
	@Column(name ="APPLICATION_ID",nullable=true,length=100)
	public java.lang.String getApplicationId(){
		return this.applicationId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本行担保凭证号
	 */
	public void setApplicationId(java.lang.String applicationId){
		this.applicationId = applicationId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  额度编号
	 */
	@Column(name ="LINE_REF",nullable=true,length=50)
	public java.lang.String getLineRef(){
		return this.lineRef;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  额度编号
	 */
	public void setLineRef(java.lang.String lineRef){
		this.lineRef = lineRef;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押品权属人（或保证人）
	 */
	@Column(name ="COL_CUST",nullable=true,length=50)
	public java.lang.String getColCust(){
		return this.colCust;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押品权属人（或保证人）
	 */
	public void setColCust(java.lang.String colCust){
		this.colCust = colCust;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同签订日期
	 */
	@Column(name ="VAL_DATE",nullable=true)
	public java.util.Date getValDate(){
		return this.valDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同签订日期
	 */
	public void setValDate(java.util.Date valDate){
		this.valDate = valDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同终止日期
	 */
	@Column(name ="EXP_DATE",nullable=true)
	public java.util.Date getExpDate(){
		return this.expDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同终止日期
	 */
	public void setExpDate(java.util.Date expDate){
		this.expDate = expDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押币种
	 */
	@Column(name ="INS_CCY",nullable=true,length=100)
	public java.lang.String getInsCcy(){
		return this.insCcy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押币种
	 */
	public void setInsCcy(java.lang.String insCcy){
		this.insCcy = insCcy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押金额
	 */
	@Column(name ="INS_AMT",nullable=true,length=300)
	public java.lang.String getInsAmt(){
		return this.insAmt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押金额
	 */
	public void setInsAmt(java.lang.String insAmt){
		this.insAmt = insAmt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登记机关
	 */
	@Column(name ="ISSUE_INSTIT",nullable=true,length=200)
	public java.lang.String getIssueInstit(){
		return this.issueInstit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登记机关
	 */
	public void setIssueInstit(java.lang.String issueInstit){
		this.issueInstit = issueInstit;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  登记日期
	 */
	@Column(name ="REGN_DATE",nullable=true)
	public java.util.Date getRegnDate(){
		return this.regnDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  登记日期
	 */
	public void setRegnDate(java.util.Date regnDate){
		this.regnDate = regnDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押物说明
	 */
	@Column(name ="COL_ADD",nullable=true,length=200)
	public java.lang.String getColAdd(){
		return this.colAdd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押物说明
	 */
	public void setColAdd(java.lang.String colAdd){
		this.colAdd = colAdd;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date 担保品终止日期 
	 */
	@Column(name ="COL_EXP_DATE",nullable=true)
	public java.util.Date getColExpDate() {
		return colExpDate;
	}
	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date 担保品终止日期
	 */
	public void setColExpDate(java.util.Date colExpDate) {
		this.colExpDate = colExpDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最后修改时间
	 */
	@Column(name ="LAST_TIME",nullable=true,length=50)
	public java.lang.String getLastTime() {
		return lastTime;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最后修改时间
	 */
	public void setLastTime(java.lang.String lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  押品评估价值或保证金额
	 */
	@Column(name ="NOMINAL_VAL",nullable=true,precision=20,scale=4)
	public BigDecimal getNominalVal() {
		return nominalVal;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  押品评估价值或保证金额
	 */
	public void setNominalVal(BigDecimal nominalVal) {
		this.nominalVal = nominalVal;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  担保品设定币种
	 */
	@Column(name ="LEDG_CCY",nullable=true,length=3)
	public java.lang.String getLedgCcy() {
		return ledgCcy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  担保品设定币种
	 */
	public void setLedgCcy(java.lang.String ledgCcy) {
		this.ledgCcy = ledgCcy;
	}

	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  担保品设定价值
	 */
	@Column(name ="LEDG_VAL",nullable=true,precision=20,scale=4)
	public BigDecimal getLedgVal() {
		return ledgVal;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  担保品设定价值
	 */
	public void setLedgVal(BigDecimal ledgVal) {
		this.ledgVal = ledgVal;
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
