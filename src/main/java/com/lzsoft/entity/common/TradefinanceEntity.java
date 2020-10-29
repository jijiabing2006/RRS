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
 * @Description: 贸易融资业务
 * @author zhangdaihao
 * @date 2016-08-23 14:39:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_tradefinance", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TradefinanceEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**信用证编号*/
	private java.lang.String lcno;
	/**信用证类型*/
	private java.lang.String lctype;
	/**客户名称*/
	private java.lang.String ctnm;
	/**客户代码*/
	private java.lang.String csnm;
	/**开证银行信用证承兑行*/
	private java.lang.String isbk;
	/**开证日期*/
	private java.util.Date isdt;
	/**交单有效期/保兑到期日*/
	private java.util.Date adexrydate;
	/**[ADEXRYDATE]到期日-[ISDT]开证日期到期天数（RRS自加）*/
	private java.lang.Integer isdtadexryd;
	/**[ADEXRYDATE]到期日-[ISDT]开证日期到期月数（RRS自加）*/
	private java.lang.Integer isdtadexrym;
	/**受益人名称*/
	private java.lang.String befc;
	/**受益人代码*/
	private java.lang.String befcno;
	/**保证金比例*/
	private java.lang.String malc;
	/**借据号*/
	private java.lang.String ioun;
	/**信贷合同号*/
	private java.lang.String cdcn;
	/**押汇银行*/
	private java.lang.String ngtb;
	/**押汇银行的SWIFT代码*/
	private java.lang.String swiftcode;
	/**信用证币种*/
	private java.lang.String lccr;
	/**信用证金额*/
	private BigDecimal lcam;
	/**信用证金额折人民币（RRS自加）*/
	private BigDecimal lcamcny;
	/**信用证金额折折美金（RRS自加）*/
	private BigDecimal lcamusd;
	/**信用证余额*/
	private BigDecimal lcbl;
	/**信用证余额折人民币（RRS自加）*/
	private BigDecimal lcblcny;
	/**信用证余额折美金（RRS自加）*/
	private BigDecimal lcblusd;
	/**信用证期限类型*/
	private java.lang.String lcte;
	/**手续费*/
	private java.lang.String coms;
	/**手续费类型*/
	private java.lang.String chtp;
	/**担保合同号*/
	private java.lang.String grtc;
	/**授信编号*/
	private java.lang.String lineCode;
	/**授信类型*/
	private java.lang.String ftyp;
	/**授信币种*/
	private java.lang.String fccy;
	/**授信金额*/
	private BigDecimal fcam;
	/**授信金额折人民币（RRS自加）*/
	private BigDecimal fcamcny;
	/**授信金额折美金（RRS自加）*/
	private BigDecimal fcamusd;
	/**开户分支机构代码*/
	private java.lang.String brca;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**交易分支机构代码*/
	private java.lang.String brcaCo;
	/**信用证付款期限*/
	private java.lang.String tenorDays;
	/**垫款标志*/
	private java.lang.String bankPayFlg;
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
	 *@return: java.lang.String  信用证编号
	 */
	@Column(name ="LCNO",nullable=true,length=100)
	public java.lang.String getLcno(){
		return this.lcno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证编号
	 */
	public void setLcno(java.lang.String lcno){
		this.lcno = lcno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用证类型
	 */
	@Column(name ="LCTYPE",nullable=true,length=100)
	public java.lang.String getLctype(){
		return this.lctype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证类型
	 */
	public void setLctype(java.lang.String lctype){
		this.lctype = lctype;
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
	@Column(name ="CSNM",nullable=true,length=20)
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
	 *@return: java.lang.String  开证银行信用证承兑行
	 */
	@Column(name ="ISBK",nullable=true,length=50)
	public java.lang.String getIsbk(){
		return this.isbk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开证银行信用证承兑行
	 */
	public void setIsbk(java.lang.String isbk){
		this.isbk = isbk;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开证日期
	 */
	@Column(name ="ISDT",nullable=true)
	public java.util.Date getIsdt(){
		return this.isdt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开证日期
	 */
	public void setIsdt(java.util.Date isdt){
		this.isdt = isdt;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交单有效期/保兑到期日
	 */
	@Column(name ="ADEXRYDATE",nullable=true)
	public java.util.Date getAdexrydate(){
		return this.adexrydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交单有效期/保兑到期日
	 */
	public void setAdexrydate(java.util.Date adexrydate){
		this.adexrydate = adexrydate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  [ADEXRYDATE]到期日-[ISDT]开证日期到期天数（RRS自加）
	 */
	@Column(name ="ISDTADEXRYD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsdtadexryd(){
		return this.isdtadexryd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  [ADEXRYDATE]到期日-[ISDT]开证日期到期天数（RRS自加）
	 */
	public void setIsdtadexryd(java.lang.Integer isdtadexryd){
		this.isdtadexryd = isdtadexryd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  [ADEXRYDATE]到期日-[ISDT]开证日期到期月数（RRS自加）
	 */
	@Column(name ="ISDTADEXRYM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsdtadexrym(){
		return this.isdtadexrym;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  [ADEXRYDATE]到期日-[ISDT]开证日期到期月数（RRS自加）
	 */
	public void setIsdtadexrym(java.lang.Integer isdtadexrym){
		this.isdtadexrym = isdtadexrym;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  受益人名称
	 */
	@Column(name ="BEFC",nullable=true,length=100)
	public java.lang.String getBefc(){
		return this.befc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  受益人名称
	 */
	public void setBefc(java.lang.String befc){
		this.befc = befc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  受益人代码
	 */
	@Column(name ="BEFCNO",nullable=true,length=100)
	public java.lang.String getBefcno(){
		return this.befcno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  受益人代码
	 */
	public void setBefcno(java.lang.String befcno){
		this.befcno = befcno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保证金比例
	 */
	@Column(name ="MALC",nullable=true,length=50)
	public java.lang.String getMalc(){
		return this.malc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保证金比例
	 */
	public void setMalc(java.lang.String malc){
		this.malc = malc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借据号
	 */
	@Column(name ="IOUN",nullable=true,length=255)
	public java.lang.String getIoun(){
		return this.ioun;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借据号
	 */
	public void setIoun(java.lang.String ioun){
		this.ioun = ioun;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信贷合同号
	 */
	@Column(name ="CDCN",nullable=true,length=50)
	public java.lang.String getCdcn(){
		return this.cdcn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信贷合同号
	 */
	public void setCdcn(java.lang.String cdcn){
		this.cdcn = cdcn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押汇银行
	 */
	@Column(name ="NGTB",nullable=true,length=50)
	public java.lang.String getNgtb(){
		return this.ngtb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押汇银行
	 */
	public void setNgtb(java.lang.String ngtb){
		this.ngtb = ngtb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  押汇银行的SWIFT代码
	 */
	@Column(name ="SWIFTCODE",nullable=true,length=50)
	public java.lang.String getSwiftcode(){
		return this.swiftcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  押汇银行的SWIFT代码
	 */
	public void setSwiftcode(java.lang.String swiftcode){
		this.swiftcode = swiftcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用证币种
	 */
	@Column(name ="LCCR",nullable=true,length=10)
	public java.lang.String getLccr(){
		return this.lccr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证币种
	 */
	public void setLccr(java.lang.String lccr){
		this.lccr = lccr;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证金额
	 */
	@Column(name ="LCAM",nullable=true,precision=20,scale=4)
	public BigDecimal getLcam(){
		return this.lcam;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证金额
	 */
	public void setLcam(BigDecimal lcam){
		this.lcam = lcam;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证金额折人民币（RRS自加）
	 */
	@Column(name ="LCAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getLcamcny(){
		return this.lcamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证金额折人民币（RRS自加）
	 */
	public void setLcamcny(BigDecimal lcamcny){
		this.lcamcny = lcamcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证金额折折美金（RRS自加）
	 */
	@Column(name ="LCAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getLcamusd(){
		return this.lcamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证金额折折美金（RRS自加）
	 */
	public void setLcamusd(BigDecimal lcamusd){
		this.lcamusd = lcamusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证余额
	 */
	@Column(name ="LCBL",nullable=true,precision=20,scale=4)
	public BigDecimal getLcbl(){
		return this.lcbl;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证余额
	 */
	public void setLcbl(BigDecimal lcbl){
		this.lcbl = lcbl;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证余额折人民币（RRS自加）
	 */
	@Column(name ="LCBLCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getLcblcny(){
		return this.lcblcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证余额折人民币（RRS自加）
	 */
	public void setLcblcny(BigDecimal lcblcny){
		this.lcblcny = lcblcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  信用证余额折美金（RRS自加）
	 */
	@Column(name ="LCBLUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getLcblusd(){
		return this.lcblusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  信用证余额折美金（RRS自加）
	 */
	public void setLcblusd(BigDecimal lcblusd){
		this.lcblusd = lcblusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用证期限类型
	 */
	@Column(name ="LCTE",nullable=true,length=50)
	public java.lang.String getLcte(){
		return this.lcte;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证期限类型
	 */
	public void setLcte(java.lang.String lcte){
		this.lcte = lcte;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手续费
	 */
	@Column(name ="COMS",nullable=true,length=255)
	public java.lang.String getComs(){
		return this.coms;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手续费
	 */
	public void setComs(java.lang.String coms){
		this.coms = coms;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手续费类型
	 */
	@Column(name ="CHTP",nullable=true,length=50)
	public java.lang.String getChtp(){
		return this.chtp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手续费类型
	 */
	public void setChtp(java.lang.String chtp){
		this.chtp = chtp;
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
	@Column(name ="FTYP",nullable=true,length=10)
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
	@Column(name ="FCAM",nullable=true,precision=20,scale=4)
	public BigDecimal getFcam(){
		return this.fcam;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额
	 */
	public void setFcam(BigDecimal fcam){
		this.fcam = fcam;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额折人民币（RRS自加）
	 */
	@Column(name ="FCAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFcamcny(){
		return this.fcamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额折人民币（RRS自加）
	 */
	public void setFcamcny(BigDecimal fcamcny){
		this.fcamcny = fcamcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额折美金（RRS自加）
	 */
	@Column(name ="FCAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFcamusd(){
		return this.fcamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额折美金（RRS自加）
	 */
	public void setFcamusd(BigDecimal fcamusd){
		this.fcamusd = fcamusd;
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
	 *@return: java.lang.String  信用证付款期限
	 */
	@Column(name ="TENOR_DAYS",nullable=false,length=10)
	public java.lang.String getTenorDays(){
		return this.tenorDays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证付款期限
	 */
	public void setTenorDays(java.lang.String tenorDays){
		this.tenorDays = tenorDays;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信用证付款期限
	 */
	@Column(name ="BANK_PAY_FLG",nullable=false,length=10)
	public java.lang.String getBankPayFlg() {
		return bankPayFlg;
	}
	
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信用证付款期限
	 */
	public void setBankPayFlg(java.lang.String bankPayFlg) {
		this.bankPayFlg = bankPayFlg;
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
