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
 * @Description: 保理业务
 * @author zhangdaihao
 * @date 2017-07-06 11:52:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_factoring", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FactoringEntity implements java.io.Serializable {
	/**编号*/
	private java.lang.String id;
	/**交易序号*/
	private java.lang.String tradeId;
	/**交易批号*/
	private java.lang.String tracNo;
	/**开户分支机构代码*/
	private java.lang.String brca;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**交易分支机构代码*/
	private java.lang.String brcaCo;
	/**发票号码*/
	private java.lang.String biln;
	/**发票期限*/
	private java.lang.String billPrd;
	/**发票起始日*/
	private java.lang.String dais;
	/**发票到期日*/
	private java.lang.String matd;
	/**发票受让日*/
	private java.lang.String trad;
	/**发票币种*/
	private java.lang.String billCcy;
	/**发票金额*/
	private java.math.BigDecimal billAm;
	/**billAmCny*/
	private java.math.BigDecimal billAmCny;
	/**billAmUsd*/
	private java.math.BigDecimal billAmUsd;
	/**发票余额*/
	private java.math.BigDecimal billBl;
	/**billBlCny*/
	private java.math.BigDecimal billBlCny;
	/**billBlUsd*/
	private java.math.BigDecimal billBlUsd;
	/**卖方客户代码*/
	private java.lang.String csnmSell;
	/**卖方客户名称*/
	private java.lang.String ctnmSell;
	/**买方客户代码*/
	private java.lang.String csnmBuy;
	/**买方客户名称*/
	private java.lang.String ctnmBuy;
	/**保理商代号*/
	private java.lang.String factorNo;
	/**保理商名称*/
	private java.lang.String factorNa;
	/**保理商国别*/
	private java.lang.String factorCty;
	/**纸质保理合同编号*/
	private java.lang.String lnrf;
	/**纸质保理合同生效日期*/
	private java.lang.String startDate;
	/**纸质保理合同终止日期*/
	private java.lang.String expiryDate;
	/**纸质保理合同有效状态*/
	private java.lang.String availalbe;
	/**资产负债类型*/
	private java.lang.String alTp;
	/**是否有追索权*/
	private java.lang.String recourseFlg;
	/**产品类别*/
	private java.lang.String dtyp;
	/**承保比例*/
	private java.lang.String qtaCov;
	/**承保额度*/
	private java.math.BigDecimal amtCov;
	/**amtCovCny*/
	private java.math.BigDecimal amtCovCny;
	/**amtCovUsd*/
	private java.math.BigDecimal amtCovUsd;
	/**承保币种*/
	private java.lang.String ccyCov;
	/**管理费*/
	private java.lang.String manFee;
	/**处理费*/
	private java.lang.String proFee;
	/**手续费*/
	private java.lang.String comCharge;
	/**扣账方式*/
	private java.lang.String payMethod;
	/**融资比例*/
	private java.lang.String loanPro;
	/**融资业务类别*/
	private java.lang.String loantype;
	/**融资经营类型*/
	private java.lang.String loanbustype;
	/**融资实际境内外投向*/
	private java.lang.String useLoan;
	/**授信编号*/
	private java.lang.String lineCode;
	/**融资贷款发放日期*/
	private java.lang.String dealdate;
	/**融资贷款指定到期日*/
	private java.lang.String matdate;
	/**融资贷款实际终止日期*/
	private java.lang.String actEndLoan;
	/**融资贷款币种*/
	private java.lang.String notesellCcy;
	/**融资贷款金额*/
	private java.math.BigDecimal notesellAm;
	/**notesellAmCny*/
	private java.math.BigDecimal notesellAmCny;
	/**notesellAmUsd*/
	private java.math.BigDecimal notesellAmUsd;
	/**融资贷款余额*/
	private java.math.BigDecimal notesellBl;
	/**notesellBlCny*/
	private java.math.BigDecimal notesellBlCny;
	/**notesellBlUsd*/
	private java.math.BigDecimal notesellBlUsd;
	/**融资贷款余额变化日期*/
	private java.lang.String blChgDate;
	/**融资担保方式*/
	private java.lang.String loanGarMtd;
	/**是否地方政府融资平台贷款*/
	private java.lang.String govPltfLoan;
	/**融资贷款用途*/
	private java.lang.String loanpurpose;
	/**融资贷款的初始放款金额*/
	private java.lang.String issuePrice;
	/**融资放款账号*/
	private java.lang.String drawdownAccount;
	/**垫款标志*/
	private java.lang.String bankPayFlg;
	/**支付方*/
	private java.lang.String payer;
	/**支付方帐户帐号*/
	private java.lang.String acRepay;
	/**融资贷款还本方式*/
	private java.lang.String loanPatPri;
	/**融资贷款还息方式*/
	private java.lang.String loanPatIns;
	/**基准利率*/
	private java.lang.String basicRate;
	/**实际利率*/
	private java.lang.String realIntrate;
	/**利率区间*/
	private java.lang.String realIntrateCode;
	/**利率是否固定*/
	private java.lang.String rftp;
	/**利率期限单位*/
	private java.lang.String tmInsrate;
	/**利率水平*/
	private java.lang.String lvlInsrate;
	/**融资贷款利率重新定价日*/
	private java.lang.String dateInsRepr;
	/**融资贷款利率重新定价期限*/
	private java.lang.String reprLoanInsrate;
	/**下次付息日*/
	private java.lang.String nxPay;
	/**上次付息日到当前应收利息*/
	private java.math.BigDecimal curInsRecv;
	/**上次付息日到当前应收利息*/
	private java.math.BigDecimal curInsRecvCny;
	/**上次付息日到当前应收利息*/
	private java.math.BigDecimal curInsRecvUsd;
	/**预收利息余额*/
	private java.lang.String unearnIns;
	/**融资贷款逾期天数*/
	private java.lang.String ovdueLoan;
	/**融资贷款逾期期限*/
	private java.lang.String ovdueLoanPrd;
	/**逾期本金*/
	private java.math.BigDecimal oveDefp;
	/**逾期本金*/
	private java.math.BigDecimal oveDefpCny;
	/**逾期本金*/
	private java.math.BigDecimal oveDefpUsd;
	/**逾期利息*/
	private java.lang.String oveDefi;
	/**逾期本金罚息*/
	private java.lang.String oveDefpIns;
	/**逾期利息罚息*/
	private java.lang.String oveDefiIns;
	/**融资贷款风险分类*/
	private java.lang.String loanRisk;
	/**融资贷款质量*/
	private java.lang.String loangrade;
	/**融资贷款状态*/
	private java.lang.String loanStat;
	/**年初融资贷款质量状态*/
	private java.lang.String begLoanQul;
	/**融资贷款减值准备_一般准备金额*/
	private java.math.BigDecimal generalPro;
	/**融资贷款减值准备_一般准备金额*/
	private java.math.BigDecimal generalProCny;
	/**融资贷款减值准备_一般准备金额*/
	private java.math.BigDecimal generalProUsd;
	/**融资贷款减值准备_专项准备金额*/
	private java.math.BigDecimal specialPro;
	/**融资贷款减值准备_专项准备金额*/
	private java.math.BigDecimal specialProCny;
	/**融资贷款减值准备_专项准备金额*/
	private java.math.BigDecimal specialProUsd;
	/**融资贷款减值准备_特种准备金额*/
	private java.math.BigDecimal specificPro;
	/**融资贷款减值准备_特种准备金额*/
	private java.math.BigDecimal specificProCny;
	/**融资贷款减值准备_特种准备金额*/
	private java.math.BigDecimal specificProUsd;
	/**系统日期*/
	private java.util.Date sysDate;
	/**导入日期*/
	private java.util.Date importdate;
	/**到期日-报表日（相差天数）*/
	private java.lang.Integer matreportday;
	/**到期日-报表日（相差月数）*/
	private java.lang.Integer matreportmonth;
	/**下次付息日-报表日（相差天数）*/
	private java.lang.Integer nxreportday;
	/**下次付息日-报表日（相差月数）*/
	private java.lang.Integer nxreportmonth;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=200)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易序号
	 */
	@Column(name ="TRADE_ID",nullable=true,length=200)
	public java.lang.String getTradeId(){
		return this.tradeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易序号
	 */
	public void setTradeId(java.lang.String tradeId){
		this.tradeId = tradeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易批号
	 */
	@Column(name ="TRAC_NO",nullable=true,length=200)
	public java.lang.String getTracNo(){
		return this.tracNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易批号
	 */
	public void setTracNo(java.lang.String tracNo){
		this.tracNo = tracNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=200)
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
	@Column(name ="PARENTBRCA",nullable=true,length=200)
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
	@Column(name ="BRCA_CO",nullable=true,length=200)
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
	 *@return: java.lang.String  发票号码
	 */
	@Column(name ="BILN",nullable=true,length=200)
	public java.lang.String getBiln(){
		return this.biln;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票号码
	 */
	public void setBiln(java.lang.String biln){
		this.biln = biln;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票期限
	 */
	@Column(name ="BILL_PRD",nullable=true,length=200)
	public java.lang.String getBillPrd(){
		return this.billPrd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票期限
	 */
	public void setBillPrd(java.lang.String billPrd){
		this.billPrd = billPrd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票起始日
	 */
	@Column(name ="DAIS",nullable=true,length=200)
	public java.lang.String getDais(){
		return this.dais;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票起始日
	 */
	public void setDais(java.lang.String dais){
		this.dais = dais;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票到期日
	 */
	@Column(name ="MATD",nullable=true,length=200)
	public java.lang.String getMatd(){
		return this.matd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票到期日
	 */
	public void setMatd(java.lang.String matd){
		this.matd = matd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票受让日
	 */
	@Column(name ="TRAD",nullable=true,length=200)
	public java.lang.String getTrad(){
		return this.trad;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票受让日
	 */
	public void setTrad(java.lang.String trad){
		this.trad = trad;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票币种
	 */
	@Column(name ="BILL_CCY",nullable=true,length=200)
	public java.lang.String getBillCcy(){
		return this.billCcy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票币种
	 */
	public void setBillCcy(java.lang.String billCcy){
		this.billCcy = billCcy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票金额
	 */
	@Column(name ="BILL_AM",nullable=true,length=200)
	public java.math.BigDecimal getBillAm(){
		return this.billAm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票金额
	 */
	public void setBillAm(java.math.BigDecimal billAm){
		this.billAm = billAm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  billAmCny
	 */
	@Column(name ="BILL_AM_CNY",nullable=true,length=200)
	public java.math.BigDecimal getBillAmCny(){
		return this.billAmCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  billAmCny
	 */
	public void setBillAmCny(java.math.BigDecimal billAmCny){
		this.billAmCny = billAmCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  billAmUsd
	 */
	@Column(name ="BILL_AM_USD",nullable=true,length=200)
	public java.math.BigDecimal getBillAmUsd(){
		return this.billAmUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  billAmUsd
	 */
	public void setBillAmUsd(java.math.BigDecimal billAmUsd){
		this.billAmUsd = billAmUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票余额
	 */
	@Column(name ="BILL_BL",nullable=true,length=200)
	public java.math.BigDecimal getBillBl(){
		return this.billBl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票余额
	 */
	public void setBillBl(java.math.BigDecimal billBl){
		this.billBl = billBl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  billBlCny
	 */
	@Column(name ="BILL_BL_CNY",nullable=true,length=200)
	public java.math.BigDecimal getBillBlCny(){
		return this.billBlCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  billBlCny
	 */
	public void setBillBlCny(java.math.BigDecimal billBlCny){
		this.billBlCny = billBlCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  billBlUsd
	 */
	@Column(name ="BILL_BL_USD",nullable=true,length=200)
	public java.math.BigDecimal getBillBlUsd(){
		return this.billBlUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  billBlUsd
	 */
	public void setBillBlUsd(java.math.BigDecimal billBlUsd){
		this.billBlUsd = billBlUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖方客户代码
	 */
	@Column(name ="CSNM_SELL",nullable=true,length=200)
	public java.lang.String getCsnmSell(){
		return this.csnmSell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖方客户代码
	 */
	public void setCsnmSell(java.lang.String csnmSell){
		this.csnmSell = csnmSell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖方客户名称
	 */
	@Column(name ="CTNM_SELL",nullable=true,length=200)
	public java.lang.String getCtnmSell(){
		return this.ctnmSell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖方客户名称
	 */
	public void setCtnmSell(java.lang.String ctnmSell){
		this.ctnmSell = ctnmSell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买方客户代码
	 */
	@Column(name ="CSNM_BUY",nullable=true,length=200)
	public java.lang.String getCsnmBuy(){
		return this.csnmBuy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买方客户代码
	 */
	public void setCsnmBuy(java.lang.String csnmBuy){
		this.csnmBuy = csnmBuy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买方客户名称
	 */
	@Column(name ="CTNM_BUY",nullable=true,length=200)
	public java.lang.String getCtnmBuy(){
		return this.ctnmBuy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买方客户名称
	 */
	public void setCtnmBuy(java.lang.String ctnmBuy){
		this.ctnmBuy = ctnmBuy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保理商代号
	 */
	@Column(name ="FACTOR_NO",nullable=true,length=200)
	public java.lang.String getFactorNo(){
		return this.factorNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保理商代号
	 */
	public void setFactorNo(java.lang.String factorNo){
		this.factorNo = factorNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保理商名称
	 */
	@Column(name ="FACTOR_NA",nullable=true,length=200)
	public java.lang.String getFactorNa(){
		return this.factorNa;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保理商名称
	 */
	public void setFactorNa(java.lang.String factorNa){
		this.factorNa = factorNa;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保理商国别
	 */
	@Column(name ="FACTOR_CTY",nullable=true,length=200)
	public java.lang.String getFactorCty(){
		return this.factorCty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保理商国别
	 */
	public void setFactorCty(java.lang.String factorCty){
		this.factorCty = factorCty;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸质保理合同编号
	 */
	@Column(name ="LNRF",nullable=true,length=200)
	public java.lang.String getLnrf(){
		return this.lnrf;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸质保理合同编号
	 */
	public void setLnrf(java.lang.String lnrf){
		this.lnrf = lnrf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸质保理合同生效日期
	 */
	@Column(name ="START_DATE",nullable=true,length=200)
	public java.lang.String getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸质保理合同生效日期
	 */
	public void setStartDate(java.lang.String startDate){
		this.startDate = startDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸质保理合同终止日期
	 */
	@Column(name ="EXPIRY_DATE",nullable=true,length=200)
	public java.lang.String getExpiryDate(){
		return this.expiryDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸质保理合同终止日期
	 */
	public void setExpiryDate(java.lang.String expiryDate){
		this.expiryDate = expiryDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸质保理合同有效状态
	 */
	@Column(name ="AVAILALBE",nullable=true,length=200)
	public java.lang.String getAvailalbe(){
		return this.availalbe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸质保理合同有效状态
	 */
	public void setAvailalbe(java.lang.String availalbe){
		this.availalbe = availalbe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产负债类型
	 */
	@Column(name ="AL_TP",nullable=true,length=200)
	public java.lang.String getAlTp(){
		return this.alTp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产负债类型
	 */
	public void setAlTp(java.lang.String alTp){
		this.alTp = alTp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否有追索权
	 */
	@Column(name ="RECOURSE_FLG",nullable=true,length=200)
	public java.lang.String getRecourseFlg(){
		return this.recourseFlg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否有追索权
	 */
	public void setRecourseFlg(java.lang.String recourseFlg){
		this.recourseFlg = recourseFlg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品类别
	 */
	@Column(name ="DTYP",nullable=true,length=200)
	public java.lang.String getDtyp(){
		return this.dtyp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品类别
	 */
	public void setDtyp(java.lang.String dtyp){
		this.dtyp = dtyp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承保比例
	 */
	@Column(name ="QTA_COV",nullable=true,length=200)
	public java.lang.String getQtaCov(){
		return this.qtaCov;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承保比例
	 */
	public void setQtaCov(java.lang.String qtaCov){
		this.qtaCov = qtaCov;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承保额度
	 */
	@Column(name ="AMT_COV",nullable=true,length=200)
	public java.math.BigDecimal getAmtCov(){
		return this.amtCov;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承保额度
	 */
	public void setAmtCov(java.math.BigDecimal amtCov){
		this.amtCov = amtCov;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  amtCovCny
	 */
	@Column(name ="AMT_COV_CNY",nullable=true,length=200)
	public java.math.BigDecimal getAmtCovCny(){
		return this.amtCovCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  amtCovCny
	 */
	public void setAmtCovCny(java.math.BigDecimal amtCovCny){
		this.amtCovCny = amtCovCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  amtCovUsd
	 */
	@Column(name ="AMT_COV_USD",nullable=true,length=200)
	public java.math.BigDecimal getAmtCovUsd(){
		return this.amtCovUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  amtCovUsd
	 */
	public void setAmtCovUsd(java.math.BigDecimal amtCovUsd){
		this.amtCovUsd = amtCovUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  承保币种
	 */
	@Column(name ="CCY_COV",nullable=true,length=200)
	public java.lang.String getCcyCov(){
		return this.ccyCov;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  承保币种
	 */
	public void setCcyCov(java.lang.String ccyCov){
		this.ccyCov = ccyCov;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  管理费
	 */
	@Column(name ="MAN_FEE",nullable=true,length=200)
	public java.lang.String getManFee(){
		return this.manFee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  管理费
	 */
	public void setManFee(java.lang.String manFee){
		this.manFee = manFee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  处理费
	 */
	@Column(name ="PRO_FEE",nullable=true,length=200)
	public java.lang.String getProFee(){
		return this.proFee;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  处理费
	 */
	public void setProFee(java.lang.String proFee){
		this.proFee = proFee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手续费
	 */
	@Column(name ="COM_CHARGE",nullable=true,length=200)
	public java.lang.String getComCharge(){
		return this.comCharge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手续费
	 */
	public void setComCharge(java.lang.String comCharge){
		this.comCharge = comCharge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扣账方式
	 */
	@Column(name ="PAY_METHOD",nullable=true,length=200)
	public java.lang.String getPayMethod(){
		return this.payMethod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扣账方式
	 */
	public void setPayMethod(java.lang.String payMethod){
		this.payMethod = payMethod;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资比例
	 */
	@Column(name ="LOAN_PRO",nullable=true,length=200)
	public java.lang.String getLoanPro(){
		return this.loanPro;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资比例
	 */
	public void setLoanPro(java.lang.String loanPro){
		this.loanPro = loanPro;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资业务类别
	 */
	@Column(name ="LOANTYPE",nullable=true,length=200)
	public java.lang.String getLoantype(){
		return this.loantype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资业务类别
	 */
	public void setLoantype(java.lang.String loantype){
		this.loantype = loantype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资经营类型
	 */
	@Column(name ="LOANBUSTYPE",nullable=true,length=200)
	public java.lang.String getLoanbustype(){
		return this.loanbustype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资经营类型
	 */
	public void setLoanbustype(java.lang.String loanbustype){
		this.loanbustype = loanbustype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资实际境内外投向
	 */
	@Column(name ="USE_LOAN",nullable=true,length=200)
	public java.lang.String getUseLoan(){
		return this.useLoan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资实际境内外投向
	 */
	public void setUseLoan(java.lang.String useLoan){
		this.useLoan = useLoan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信编号
	 */
	@Column(name ="LINE_CODE",nullable=true,length=200)
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
	 *@return: java.lang.String  融资贷款发放日期
	 */
	@Column(name ="DEALDATE",nullable=true,length=200)
	public java.lang.String getDealdate(){
		return this.dealdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款发放日期
	 */
	public void setDealdate(java.lang.String dealdate){
		this.dealdate = dealdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款指定到期日
	 */
	@Column(name ="MATDATE",nullable=true,length=200)
	public java.lang.String getMatdate(){
		return this.matdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款指定到期日
	 */
	public void setMatdate(java.lang.String matdate){
		this.matdate = matdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款实际终止日期
	 */
	@Column(name ="ACT_END_LOAN",nullable=true,length=200)
	public java.lang.String getActEndLoan(){
		return this.actEndLoan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款实际终止日期
	 */
	public void setActEndLoan(java.lang.String actEndLoan){
		this.actEndLoan = actEndLoan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款币种
	 */
	@Column(name ="NOTESELL_CCY",nullable=true,length=200)
	public java.lang.String getNotesellCcy(){
		return this.notesellCcy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款币种
	 */
	public void setNotesellCcy(java.lang.String notesellCcy){
		this.notesellCcy = notesellCcy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款金额
	 */
	@Column(name ="NOTESELL_AM",nullable=true,length=200)
	public java.math.BigDecimal getNotesellAm(){
		return this.notesellAm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款金额
	 */
	public void setNotesellAm(java.math.BigDecimal notesellAm){
		this.notesellAm = notesellAm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  notesellAmCny
	 */
	@Column(name ="NOTESELL_AM_CNY",nullable=true,length=200)
	public java.math.BigDecimal getNotesellAmCny(){
		return this.notesellAmCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  notesellAmCny
	 */
	public void setNotesellAmCny(java.math.BigDecimal notesellAmCny){
		this.notesellAmCny = notesellAmCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  notesellAmUsd
	 */
	@Column(name ="NOTESELL_AM_USD",nullable=true,length=200)
	public java.math.BigDecimal getNotesellAmUsd(){
		return this.notesellAmUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  notesellAmUsd
	 */
	public void setNotesellAmUsd(java.math.BigDecimal notesellAmUsd){
		this.notesellAmUsd = notesellAmUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款余额
	 */
	@Column(name ="NOTESELL_BL",nullable=true,length=200)
	public java.math.BigDecimal getNotesellBl(){
		return this.notesellBl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款余额
	 */
	public void setNotesellBl(java.math.BigDecimal notesellBl){
		this.notesellBl = notesellBl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  notesellBlCny
	 */
	@Column(name ="NOTESELL_BL_CNY",nullable=true,length=200)
	public java.math.BigDecimal getNotesellBlCny(){
		return this.notesellBlCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  notesellBlCny
	 */
	public void setNotesellBlCny(java.math.BigDecimal notesellBlCny){
		this.notesellBlCny = notesellBlCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  notesellBlUsd
	 */
	@Column(name ="NOTESELL_BL_USD",nullable=true,length=200)
	public java.math.BigDecimal getNotesellBlUsd(){
		return this.notesellBlUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  notesellBlUsd
	 */
	public void setNotesellBlUsd(java.math.BigDecimal notesellBlUsd){
		this.notesellBlUsd = notesellBlUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款余额变化日期
	 */
	@Column(name ="BL_CHG_DATE",nullable=true,length=200)
	public java.lang.String getBlChgDate(){
		return this.blChgDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款余额变化日期
	 */
	public void setBlChgDate(java.lang.String blChgDate){
		this.blChgDate = blChgDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资担保方式
	 */
	@Column(name ="LOAN_GAR_MTD",nullable=true,length=200)
	public java.lang.String getLoanGarMtd(){
		return this.loanGarMtd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资担保方式
	 */
	public void setLoanGarMtd(java.lang.String loanGarMtd){
		this.loanGarMtd = loanGarMtd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否地方政府融资平台贷款
	 */
	@Column(name ="GOV_PLTF_LOAN",nullable=true,length=200)
	public java.lang.String getGovPltfLoan(){
		return this.govPltfLoan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否地方政府融资平台贷款
	 */
	public void setGovPltfLoan(java.lang.String govPltfLoan){
		this.govPltfLoan = govPltfLoan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款用途
	 */
	@Column(name ="LOANPURPOSE",nullable=true,length=200)
	public java.lang.String getLoanpurpose(){
		return this.loanpurpose;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款用途
	 */
	public void setLoanpurpose(java.lang.String loanpurpose){
		this.loanpurpose = loanpurpose;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款的初始放款金额
	 */
	@Column(name ="ISSUE_PRICE",nullable=true,length=200)
	public java.lang.String getIssuePrice(){
		return this.issuePrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款的初始放款金额
	 */
	public void setIssuePrice(java.lang.String issuePrice){
		this.issuePrice = issuePrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资放款账号
	 */
	@Column(name ="DRAWDOWN_ACCOUNT",nullable=true,length=200)
	public java.lang.String getDrawdownAccount(){
		return this.drawdownAccount;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资放款账号
	 */
	public void setDrawdownAccount(java.lang.String drawdownAccount){
		this.drawdownAccount = drawdownAccount;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  垫款标志
	 */
	@Column(name ="BANK_PAY_FLG",nullable=true,length=200)
	public java.lang.String getBankPayFlg(){
		return this.bankPayFlg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  垫款标志
	 */
	public void setBankPayFlg(java.lang.String bankPayFlg){
		this.bankPayFlg = bankPayFlg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方
	 */
	@Column(name ="PAYER",nullable=true,length=200)
	public java.lang.String getPayer(){
		return this.payer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方
	 */
	public void setPayer(java.lang.String payer){
		this.payer = payer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方帐户帐号
	 */
	@Column(name ="AC_REPAY",nullable=true,length=200)
	public java.lang.String getAcRepay(){
		return this.acRepay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方帐户帐号
	 */
	public void setAcRepay(java.lang.String acRepay){
		this.acRepay = acRepay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款还本方式
	 */
	@Column(name ="LOAN_PAT_PRI",nullable=true,length=200)
	public java.lang.String getLoanPatPri(){
		return this.loanPatPri;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款还本方式
	 */
	public void setLoanPatPri(java.lang.String loanPatPri){
		this.loanPatPri = loanPatPri;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款还息方式
	 */
	@Column(name ="LOAN_PAT_INS",nullable=true,length=200)
	public java.lang.String getLoanPatIns(){
		return this.loanPatIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款还息方式
	 */
	public void setLoanPatIns(java.lang.String loanPatIns){
		this.loanPatIns = loanPatIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  基准利率
	 */
	@Column(name ="BASIC_RATE",nullable=true,length=200)
	public java.lang.String getBasicRate(){
		return this.basicRate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  基准利率
	 */
	public void setBasicRate(java.lang.String basicRate){
		this.basicRate = basicRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实际利率
	 */
	@Column(name ="REAL_INTRATE",nullable=true,length=200)
	public java.lang.String getRealIntrate(){
		return this.realIntrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实际利率
	 */
	public void setRealIntrate(java.lang.String realIntrate){
		this.realIntrate = realIntrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率区间
	 */
	@Column(name ="REAL_INTRATE_CODE",nullable=true,length=200)
	public java.lang.String getRealIntrateCode(){
		return this.realIntrateCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率区间
	 */
	public void setRealIntrateCode(java.lang.String realIntrateCode){
		this.realIntrateCode = realIntrateCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率是否固定
	 */
	@Column(name ="RFTP",nullable=true,length=200)
	public java.lang.String getRftp(){
		return this.rftp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率是否固定
	 */
	public void setRftp(java.lang.String rftp){
		this.rftp = rftp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率期限单位
	 */
	@Column(name ="TM_INSRATE",nullable=true,length=200)
	public java.lang.String getTmInsrate(){
		return this.tmInsrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率期限单位
	 */
	public void setTmInsrate(java.lang.String tmInsrate){
		this.tmInsrate = tmInsrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率水平
	 */
	@Column(name ="LVL_INSRATE",nullable=true,length=200)
	public java.lang.String getLvlInsrate(){
		return this.lvlInsrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率水平
	 */
	public void setLvlInsrate(java.lang.String lvlInsrate){
		this.lvlInsrate = lvlInsrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款利率重新定价日
	 */
	@Column(name ="DATE_INS_REPR",nullable=true,length=200)
	public java.lang.String getDateInsRepr(){
		return this.dateInsRepr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款利率重新定价日
	 */
	public void setDateInsRepr(java.lang.String dateInsRepr){
		this.dateInsRepr = dateInsRepr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款利率重新定价期限
	 */
	@Column(name ="REPR_LOAN_INSRATE",nullable=true,length=200)
	public java.lang.String getReprLoanInsrate(){
		return this.reprLoanInsrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款利率重新定价期限
	 */
	public void setReprLoanInsrate(java.lang.String reprLoanInsrate){
		this.reprLoanInsrate = reprLoanInsrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下次付息日
	 */
	@Column(name ="NX_PAY",nullable=true,length=200)
	public java.lang.String getNxPay(){
		return this.nxPay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下次付息日
	 */
	public void setNxPay(java.lang.String nxPay){
		this.nxPay = nxPay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: BigDecimal  上次付息日到当前应收利息
	 */
	@Column(name ="CUR_INS_RECV_CNY",nullable=true,length=200)
	public BigDecimal getCurInsRecvCny(){
		return this.curInsRecvCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: BigDecimal  上次付息日到当前应收利息
	 */
	public void setCurInsRecvCny(BigDecimal curInsRecvCny){
		this.curInsRecvCny = curInsRecvCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上次付息日到当前应收利息
	 */
	@Column(name ="CUR_INS_RECV_USD",nullable=true,length=200)
	public BigDecimal getCurInsRecvUsd(){
		return this.curInsRecvUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上次付息日到当前应收利息
	 */
	public void setCurInsRecvUsd(BigDecimal curInsRecvUsd){
		this.curInsRecvUsd = curInsRecvUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上次付息日到当前应收利息
	 */
	@Column(name ="CUR_INS_RECV",nullable=true,length=200)
	public BigDecimal getCurInsRecv(){
		return this.curInsRecv;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上次付息日到当前应收利息
	 */
	public void setCurInsRecv(BigDecimal curInsRecv){
		this.curInsRecv = curInsRecv;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预收利息余额
	 */
	@Column(name ="UNEARN_INS",nullable=true,length=200)
	public java.lang.String getUnearnIns(){
		return this.unearnIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预收利息余额
	 */
	public void setUnearnIns(java.lang.String unearnIns){
		this.unearnIns = unearnIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款逾期天数
	 */
	@Column(name ="OVDUE_LOAN",nullable=true,length=200)
	public java.lang.String getOvdueLoan(){
		return this.ovdueLoan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款逾期天数
	 */
	public void setOvdueLoan(java.lang.String ovdueLoan){
		this.ovdueLoan = ovdueLoan;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款逾期期限
	 */
	@Column(name ="OVDUE_LOAN_PRD",nullable=true,length=200)
	public java.lang.String getOvdueLoanPrd(){
		return this.ovdueLoanPrd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款逾期期限
	 */
	public void setOvdueLoanPrd(java.lang.String ovdueLoanPrd){
		this.ovdueLoanPrd = ovdueLoanPrd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期本金
	 */
	@Column(name ="OVE_DEFP",nullable=true,length=200)
	public BigDecimal getOveDefp(){
		return this.oveDefp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期本金
	 */
	public void setOveDefp(BigDecimal oveDefp){
		this.oveDefp = oveDefp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期本金
	 */
	@Column(name ="OVE_DEFP_CNY",nullable=true,length=200)
	public BigDecimal getOveDefpCny(){
		return this.oveDefpCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期本金
	 */
	public void setOveDefpCny(BigDecimal oveDefpCny){
		this.oveDefpCny = oveDefpCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期本金
	 */
	@Column(name ="OVE_DEFP_USD",nullable=true,length=200)
	public BigDecimal getOveDefpUsd(){
		return this.oveDefpUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期本金
	 */
	public void setOveDefpUsd(BigDecimal oveDefpUsd){
		this.oveDefpUsd = oveDefpUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期利息
	 */
	@Column(name ="OVE_DEFI",nullable=true,length=200)
	public java.lang.String getOveDefi(){
		return this.oveDefi;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期利息
	 */
	public void setOveDefi(java.lang.String oveDefi){
		this.oveDefi = oveDefi;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期本金罚息
	 */
	@Column(name ="OVE_DEFP_INS",nullable=true,length=200)
	public java.lang.String getOveDefpIns(){
		return this.oveDefpIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期本金罚息
	 */
	public void setOveDefpIns(java.lang.String oveDefpIns){
		this.oveDefpIns = oveDefpIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逾期利息罚息
	 */
	@Column(name ="OVE_DEFI_INS",nullable=true,length=200)
	public java.lang.String getOveDefiIns(){
		return this.oveDefiIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逾期利息罚息
	 */
	public void setOveDefiIns(java.lang.String oveDefiIns){
		this.oveDefiIns = oveDefiIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款风险分类
	 */
	@Column(name ="LOAN_RISK",nullable=true,length=200)
	public java.lang.String getLoanRisk(){
		return this.loanRisk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款风险分类
	 */
	public void setLoanRisk(java.lang.String loanRisk){
		this.loanRisk = loanRisk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款质量
	 */
	@Column(name ="LOANGRADE",nullable=true,length=200)
	public java.lang.String getLoangrade(){
		return this.loangrade;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款质量
	 */
	public void setLoangrade(java.lang.String loangrade){
		this.loangrade = loangrade;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款状态
	 */
	@Column(name ="LOAN_STAT",nullable=true,length=200)
	public java.lang.String getLoanStat(){
		return this.loanStat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款状态
	 */
	public void setLoanStat(java.lang.String loanStat){
		this.loanStat = loanStat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年初融资贷款质量状态
	 */
	@Column(name ="BEG_LOAN_QUL",nullable=true,length=200)
	public java.lang.String getBegLoanQul(){
		return this.begLoanQul;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年初融资贷款质量状态
	 */
	public void setBegLoanQul(java.lang.String begLoanQul){
		this.begLoanQul = begLoanQul;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	@Column(name ="GENERAL_PRO",nullable=true,length=200)
	public java.math.BigDecimal getGeneralPro(){
		return this.generalPro;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	public void setGeneralPro(java.math.BigDecimal generalPro){
		this.generalPro = generalPro;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	@Column(name ="GENERAL_PRO_CNY",nullable=true,length=200)
	public java.math.BigDecimal getGeneralProCny(){
		return this.generalProCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	public void setGeneralProCny(java.math.BigDecimal generalProCny){
		this.generalProCny = generalProCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	@Column(name ="GENERAL_PRO_USD",nullable=true,length=200)
	public java.math.BigDecimal getGeneralProUsd(){
		return this.generalProUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_一般准备金额
	 */
	public void setGeneralProUsd(java.math.BigDecimal generalProUsd){
		this.generalProUsd = generalProUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	@Column(name ="SPECIAL_PRO",nullable=true,length=200)
	public java.math.BigDecimal getSpecialPro(){
		return this.specialPro;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	public void setSpecialPro(java.math.BigDecimal specialPro){
		this.specialPro = specialPro;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	@Column(name ="SPECIAL_PRO_CNY",nullable=true,length=200)
	public java.math.BigDecimal getSpecialProCny(){
		return this.specialProCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	public void setSpecialProCny(java.math.BigDecimal specialProCny){
		this.specialProCny = specialProCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	@Column(name ="SPECIAL_PRO_USD",nullable=true,length=200)
	public java.math.BigDecimal getSpecialProUsd(){
		return this.specialProUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_专项准备金额
	 */
	public void setSpecialProUsd(java.math.BigDecimal specialProUsd){
		this.specialProUsd = specialProUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	@Column(name ="SPECIFIC_PRO",nullable=true,length=200)
	public java.math.BigDecimal getSpecificPro(){
		return this.specificPro;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	public void setSpecificPro(java.math.BigDecimal specificPro){
		this.specificPro = specificPro;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	@Column(name ="SPECIFIC_PRO_CNY",nullable=true,length=200)
	public java.math.BigDecimal getSpecificProCny(){
		return this.specificProCny;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	public void setSpecificProCny(java.math.BigDecimal specificProCny){
		this.specificProCny = specificProCny;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	@Column(name ="SPECIFIC_PRO_USD",nullable=true,length=200)
	public java.math.BigDecimal getSpecificProUsd(){
		return this.specificProUsd;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资贷款减值准备_特种准备金额
	 */
	public void setSpecificProUsd(java.math.BigDecimal specificProUsd){
		this.specificProUsd = specificProUsd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  系统日期
	 */
	@Column(name ="SYS_DATE",nullable=true,length=200)
	public java.util.Date getSysDate(){
		return this.sysDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  系统日期
	 */
	public void setSysDate(java.util.Date sysDate){
		this.sysDate = sysDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  导入日期
	 */
	@Column(name ="IMPORTDATE",nullable=true)
	public java.util.Date getImportdate(){
		return this.importdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  导入日期
	 */
	public void setImportdate(java.util.Date importdate){
		this.importdate = importdate;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-报表日（相差天数）
	 */
	@Column(name ="MATREPORTDAY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatreportday(){
		return this.matreportday;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-报表日（相差天数）
	 */
	public void setMatreportday(java.lang.Integer matreportday){
		this.matreportday = matreportday;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-报表日（相差月数）
	 */
	@Column(name ="MATREPORTMONTH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatreportmonth(){
		return this.matreportmonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-报表日（相差月数）
	 */
	public void setMatreportmonth(java.lang.Integer matreportmonth){
		this.matreportmonth = matreportmonth;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  下次付息日-报表日（相差天数）
	 */
	@Column(name ="NXREPORTDAY",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNxreportday(){
		return this.nxreportday;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  下次付息日-报表日（相差天数）
	 */
	public void setNxreportday(java.lang.Integer nxreportday){
		this.nxreportday = nxreportday;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  下次付息日-报表日（相差月数）
	 */
	@Column(name ="NXREPORTMONTH",nullable=true,precision=10,scale=0)
	public java.lang.Integer getNxreportmonth(){
		return this.nxreportmonth;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  下次付息日-报表日（相差月数）
	 */
	public void setNxreportmonth(java.lang.Integer nxreportmonth){
		this.nxreportmonth = nxreportmonth;
	}
}
