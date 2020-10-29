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
 * @Description: 存放同业定期存款信息MM
 * @author zhangdaihao
 * @date 2016-03-11 11:30:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_fixed_mm", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FixedMmEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**利息计提方式*/
	private java.lang.String accpar;
	/**报文编号*/
	private java.lang.String advsent;
	/**续存方式*/
	private java.lang.String aucap;
	/**转存标识*/
	private java.lang.String aurol;
	/**续存期限*/
	private java.lang.String aurolte;
	/**交易对手账户*/
	private java.lang.String benacc;
	/**交易对手银行*/
	private java.lang.String benbk;
	/**分支机构代码*/
	private java.lang.String brcaCo;
	/**账户类型*/
	private java.lang.String category;
	/**交易币种*/
	private java.lang.String ccy;
	/**交易对手*/
	private java.lang.String csnm;
	/**客户编号*/
	private java.lang.String cusref;
	/**交易日期*/
	private java.util.Date deld;
	/**交易序号（核心账号）*/
	private java.lang.String dlno;
	/**提款账户*/
	private java.lang.String drawacc;
	/**续存到期日*/
	private java.util.Date finmat;
	/**营业日期*/
	private java.util.Date importdate;
	/**利率重新定价期限*/
	private java.lang.String inddued;
	/**计息方式*/
	private java.lang.String intbas;
	/**利息支付币种*/
	private java.lang.String intccy;
	/**利率基准*/
	private java.lang.String intkey;
	/**利息清算账户*/
	private java.lang.String intliqacct;
	/**利率*/
	private java.lang.String intrate;
	/**利率类型*/
	private java.lang.String intratp;
	/**利率调整*/
	private java.lang.String intspr;
	/**授信编号*/
	private java.lang.String limitref;
	/**到期日*/
	private java.util.Date matd;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**本金*/
	private BigDecimal pcpl;
	/**上一到期日*/
	private java.util.Date predate;
	/**本金清算账户*/
	private java.lang.String priliqacct;
	/**备注*/
	private java.lang.String remarks;
	/**续存利率*/
	private BigDecimal rolrate;
	/**生效日期*/
	private java.util.Date stad;
	/**SWIFT CODE*/
	private java.lang.String swiftco;
	/**系统日期*/
	private java.util.Date sysDate;
	/**应计利息*/
	private BigDecimal totintamt;
	/**应计利息折人民币*/
	private BigDecimal totintamtcny;
	/**应计利息折美金*/
	private BigDecimal totintamtusd;
	/**本金折人民币*/
	private BigDecimal pcplcny;
	/**本金折美元*/
	private BigDecimal pcplusd;
	/**到期天数*/
	private java.lang.Integer matdimpd;
	/**到期月数*/
	private java.lang.Integer matdimpm;
	
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
	 *@return: java.lang.String  利息计提方式
	 */
	@Column(name ="ACCPAR",nullable=true,length=32)
	public java.lang.String getAccpar(){
		return this.accpar;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利息计提方式
	 */
	public void setAccpar(java.lang.String accpar){
		this.accpar = accpar;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报文编号
	 */
	@Column(name ="ADVSENT",nullable=true,length=50)
	public java.lang.String getAdvsent(){
		return this.advsent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报文编号
	 */
	public void setAdvsent(java.lang.String advsent){
		this.advsent = advsent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  续存方式
	 */
	@Column(name ="AUCAP",nullable=true,length=1)
	public java.lang.String getAucap(){
		return this.aucap;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  续存方式
	 */
	public void setAucap(java.lang.String aucap){
		this.aucap = aucap;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转存标识
	 */
	@Column(name ="AUROL",nullable=true,length=50)
	public java.lang.String getAurol(){
		return this.aurol;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转存标识
	 */
	public void setAurol(java.lang.String aurol){
		this.aurol = aurol;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  续存期限
	 */
	@Column(name ="AUROLTE",nullable=true,length=50)
	public java.lang.String getAurolte(){
		return this.aurolte;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  续存期限
	 */
	public void setAurolte(java.lang.String aurolte){
		this.aurolte = aurolte;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易对手账户
	 */
	@Column(name ="BENACC",nullable=true,length=50)
	public java.lang.String getBenacc(){
		return this.benacc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易对手账户
	 */
	public void setBenacc(java.lang.String benacc){
		this.benacc = benacc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易对手银行
	 */
	@Column(name ="BENBK",nullable=true,length=50)
	public java.lang.String getBenbk(){
		return this.benbk;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易对手银行
	 */
	public void setBenbk(java.lang.String benbk){
		this.benbk = benbk;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支机构代码
	 */
	@Column(name ="BRCA_CO",nullable=true,length=32)
	public java.lang.String getBrcaCo(){
		return this.brcaCo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支机构代码
	 */
	public void setBrcaCo(java.lang.String brcaCo){
		this.brcaCo = brcaCo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账户类型
	 */
	@Column(name ="CATEGORY",nullable=true,length=32)
	public java.lang.String getCategory(){
		return this.category;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类型
	 */
	public void setCategory(java.lang.String category){
		this.category = category;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易币种
	 */
	@Column(name ="CCY",nullable=true,length=3)
	public java.lang.String getCcy(){
		return this.ccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易币种
	 */
	public void setCcy(java.lang.String ccy){
		this.ccy = ccy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易对手
	 */
	@Column(name ="CSNM",nullable=true,length=32)
	public java.lang.String getCsnm(){
		return this.csnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易对手
	 */
	public void setCsnm(java.lang.String csnm){
		this.csnm = csnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户编号
	 */
	@Column(name ="CUSREF",nullable=true,length=50)
	public java.lang.String getCusref(){
		return this.cusref;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户编号
	 */
	public void setCusref(java.lang.String cusref){
		this.cusref = cusref;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交易日期
	 */
	@Column(name ="DELD",nullable=true)
	public java.util.Date getDeld(){
		return this.deld;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交易日期
	 */
	public void setDeld(java.util.Date deld){
		this.deld = deld;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易序号（核心账号）
	 */
	@Column(name ="DLNO",nullable=true,length=32)
	public java.lang.String getDlno(){
		return this.dlno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易序号（核心账号）
	 */
	public void setDlno(java.lang.String dlno){
		this.dlno = dlno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提款账户
	 */
	@Column(name ="DRAWACC",nullable=true,length=50)
	public java.lang.String getDrawacc(){
		return this.drawacc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提款账户
	 */
	public void setDrawacc(java.lang.String drawacc){
		this.drawacc = drawacc;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  续存到期日
	 */
	@Column(name ="FINMAT",nullable=true)
	public java.util.Date getFinmat(){
		return this.finmat;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  续存到期日
	 */
	public void setFinmat(java.util.Date finmat){
		this.finmat = finmat;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率重新定价期限
	 */
	@Column(name ="INDDUED",nullable=true,length=50)
	public java.lang.String getInddued(){
		return this.inddued;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率重新定价期限
	 */
	public void setInddued(java.lang.String inddued){
		this.inddued = inddued;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息方式
	 */
	@Column(name ="INTBAS",nullable=true,length=32)
	public java.lang.String getIntbas(){
		return this.intbas;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息方式
	 */
	public void setIntbas(java.lang.String intbas){
		this.intbas = intbas;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利息支付币种
	 */
	@Column(name ="INTCCY",nullable=true,length=3)
	public java.lang.String getIntccy(){
		return this.intccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利息支付币种
	 */
	public void setIntccy(java.lang.String intccy){
		this.intccy = intccy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率基准
	 */
	@Column(name ="INTKEY",nullable=true,length=32)
	public java.lang.String getIntkey(){
		return this.intkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率基准
	 */
	public void setIntkey(java.lang.String intkey){
		this.intkey = intkey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利息清算账户
	 */
	@Column(name ="INTLIQACCT",nullable=true,length=50)
	public java.lang.String getIntliqacct(){
		return this.intliqacct;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利息清算账户
	 */
	public void setIntliqacct(java.lang.String intliqacct){
		this.intliqacct = intliqacct;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率
	 */
	@Column(name ="INTRATE",nullable=true,length=32)
	public java.lang.String getIntrate(){
		return this.intrate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率
	 */
	public void setIntrate(java.lang.String intrate){
		this.intrate = intrate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率类型
	 */
	@Column(name ="INTRATP",nullable=true,length=32)
	public java.lang.String getIntratp(){
		return this.intratp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率类型
	 */
	public void setIntratp(java.lang.String intratp){
		this.intratp = intratp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  利率调整
	 */
	@Column(name ="INTSPR",nullable=true,length=32)
	public java.lang.String getIntspr(){
		return this.intspr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  利率调整
	 */
	public void setIntspr(java.lang.String intspr){
		this.intspr = intspr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  授信编号
	 */
	@Column(name ="LIMITREF",nullable=true,length=50)
	public java.lang.String getLimitref(){
		return this.limitref;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  授信编号
	 */
	public void setLimitref(java.lang.String limitref){
		this.limitref = limitref;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	@Column(name ="MATD",nullable=true)
	public java.util.Date getMatd(){
		return this.matd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setMatd(java.util.Date matd){
		this.matd = matd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=32)
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  本金
	 */
	@Column(name ="PCPL",nullable=true,precision=19,scale=2)
	public BigDecimal getPcpl(){
		return this.pcpl;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  本金
	 */
	public void setPcpl(BigDecimal pcpl){
		this.pcpl = pcpl;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上一到期日
	 */
	@Column(name ="PREDATE",nullable=true)
	public java.util.Date getPredate(){
		return this.predate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上一到期日
	 */
	public void setPredate(java.util.Date predate){
		this.predate = predate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本金清算账户
	 */
	@Column(name ="PRILIQACCT",nullable=true,length=50)
	public java.lang.String getPriliqacct(){
		return this.priliqacct;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本金清算账户
	 */
	public void setPriliqacct(java.lang.String priliqacct){
		this.priliqacct = priliqacct;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=50)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  续存利率
	 */
	@Column(name ="ROLRATE",nullable=true,precision=19,scale=2)
	public BigDecimal getRolrate(){
		return this.rolrate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  续存利率
	 */
	public void setRolrate(BigDecimal rolrate){
		this.rolrate = rolrate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日期
	 */
	@Column(name ="STAD",nullable=true)
	public java.util.Date getStad(){
		return this.stad;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日期
	 */
	public void setStad(java.util.Date stad){
		this.stad = stad;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  SWIFT CODE
	 */
	@Column(name ="SWIFTCO",nullable=true,length=50)
	public java.lang.String getSwiftco(){
		return this.swiftco;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  SWIFT CODE
	 */
	public void setSwiftco(java.lang.String swiftco){
		this.swiftco = swiftco;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  应计利息
	 */
	@Column(name ="TOTINTAMT",nullable=true,precision=19,scale=2)
	public BigDecimal getTotintamt(){
		return this.totintamt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  应计利息
	 */
	public void setTotintamt(BigDecimal totintamt){
		this.totintamt = totintamt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  应计利息折人民币
	 */
	@Column(name ="TOTINTAMTCNY",nullable=true,precision=19,scale=2)
	public BigDecimal getTotintamtcny(){
		return this.totintamtcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  应计利息折人民币
	 */
	public void setTotintamtcny(BigDecimal totintamtcny){
		this.totintamtcny = totintamtcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  应计利息折美金
	 */
	@Column(name ="TOTINTAMTUSD",nullable=true,precision=19,scale=2)
	public BigDecimal getTotintamtusd(){
		return this.totintamtusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  应计利息折美金
	 */
	public void setTotintamtusd(BigDecimal totintamtusd){
		this.totintamtusd = totintamtusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  本金折人民币
	 */
	@Column(name ="PCPLCNY",nullable=true,precision=19,scale=2)
	public BigDecimal getPcplcny(){
		return this.pcplcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  本金折人民币
	 */
	public void setPcplcny(BigDecimal pcplcny){
		this.pcplcny = pcplcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  本金折美元
	 */
	@Column(name ="PCPLUSD",nullable=true,precision=19,scale=2)
	public BigDecimal getPcplusd(){
		return this.pcplusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  本金折美元
	 */
	public void setPcplusd(BigDecimal pcplusd){
		this.pcplusd = pcplusd;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期天数（RRS自加）
	 */
	@Column(name ="MATDIMPD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatdimpd(){
		return this.matdimpd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期天数（RRS自加）
	 */
	public void setMatdimpd(java.lang.Integer matdimpd){
		this.matdimpd = matdimpd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期月数（RRS自加）
	 */
	@Column(name ="matdimpm",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatdimpm(){
		return this.matdimpm;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期月数（RRS自加）
	 */
	public void setMatdimpm(java.lang.Integer matdimpm){
		this.matdimpm = matdimpm;
	}
}
