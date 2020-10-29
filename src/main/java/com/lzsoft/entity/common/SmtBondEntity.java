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
 * @Description: SUMMIT-BOND债券交易信息表
 * @author zhangdaihao
 * @date 2016-03-15 14:33:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_bond", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtBondEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**上级机构代码*/
	private java.lang.String parentbrca;
	/**分支行行号*/
	private java.lang.String brca;
	/**交易编号*/
	private java.lang.String tradeid;
	/**交易状态*/
//	private java.lang.String tradestatus;
	/**交易类型*/
	private java.lang.String tradetype;
	/**交易对手*/
	private java.lang.String cust;
	/**交易日*/
	private java.util.Date tradedate;
	/**货币*/
	private java.lang.String ccy;
	/**标准面值*/
	private BigDecimal notional;
	/**标准面值折人民币*/
	private BigDecimal notionalcny;
	/**标准面值折美金*/
	private BigDecimal notionalusd;
	/**买卖类型*/
	private java.lang.String pors;
	/**会计类型*/
	private java.lang.String traccttype;
	/**到期日*/
	private java.util.Date matdate;
	/**账簿*/
	private java.lang.String book;
	/**账簿类型*/
	private java.lang.String booktype;
	/**发行人*/
	private java.lang.String issuer;
	/**发行人类别1（PBOC）*/
	private java.lang.String issuertype;
	/**发行人类别2（SAFE）*/
	private java.lang.String issuertype2;
	/**资产类型*/
	private java.lang.String interestFixfloat;
	/**Index*/
	private java.lang.String interestDmindex;
	/**券号*/
	private java.lang.String sec;
	/**债券类型*/
	private java.lang.String type;
	/**债券子类型*/
	private java.lang.String subtype;
	/**清算日期*/
	private java.util.Date valuedate;
	/**利率*/
	private BigDecimal interestRate;
	/**计息基准*/
	private java.lang.String interestBasis;
	/**债券分类*/
	private java.lang.String issuetypeNonfin;
	/**逻辑国家*/
	private java.lang.String issuerLogicCountry;
	/**是否逻辑外国*/
	private java.lang.String issuerLogicForeign;
	/**是否为金融机构*/
	private java.lang.String issuerFinIns;
	/**国家评级*/
	private java.lang.String issuerLogicCountryRating;
	/**是否地理外国*/
	private java.lang.String issuerPhysicalCountry;
	/**下一个定价日*/
	private java.util.Date nextfixingday;
	/**债券市场价格*/
	private BigDecimal bondmtmprice;
	/**期末金额*/
	private BigDecimal endvalue;
	/**期末金额折美金*/
	private BigDecimal endvalueusd;
	/**期末金额折人民币*/
	private BigDecimal endvaluecny;
	/**累计应计利息*/
	private BigDecimal endaccrual;
	/**累计应计利息折美金*/
	private BigDecimal endaccrualusd;
	/**累计应计利息折人民币*/
	private BigDecimal endaccrualcny;
	/**账簿金额*/
	private BigDecimal rembookval;
	/**账簿金额折人民币*/
	private BigDecimal rembookvalcny;
	/**账簿金额折美金*/
	private BigDecimal rembookvalusd;
	/**评级1*/
	private java.lang.String rating1;
	/**评级机构1*/
	private java.lang.String ratingagencyname1;
	/**评级2*/
	private java.lang.String rating2;
	/**评级机构2*/
	private java.lang.String ratingagencyname2;
	/**评级3*/
	private java.lang.String rating3;
	/**评级机构3*/
	private java.lang.String ratingagencyname3;
	/**机构*/
	private java.lang.String company;
	/**到期日-营业日，日*/
	private java.lang.Integer matimpd;
	/**到期日-营业日，月份*/
	private java.lang.Integer matimpm;
	/**营业日期*/
	private java.util.Date importdate;
	/**系统日期*/
	private java.util.Date sysDate;
	
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
	 *@return: java.lang.String  上级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=4)
	public java.lang.String getParentbrca(){
		return this.parentbrca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级机构代码
	 */
	public void setParentbrca(java.lang.String parentbrca){
		this.parentbrca = parentbrca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支行行号
	 */
	@Column(name ="BRCA",nullable=true,length=4)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支行行号
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易编号
	 */
	@Column(name ="TRADEID",nullable=true,length=50)
	public java.lang.String getTradeid(){
		return this.tradeid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易编号
	 */
	public void setTradeid(java.lang.String tradeid){
		this.tradeid = tradeid;
	}
//	/**
//	 *方法: 取得java.lang.String
//	 *@return: java.lang.String  交易状态
//	 */
//	@Column(name ="TRADESTATUS",nullable=true,length=50)
//	public java.lang.String getTradestatus(){
//		return this.tradestatus;
//	}
//
//	/**
//	 *方法: 设置java.lang.String
//	 *@param: java.lang.String  交易状态
//	 */
//	public void setTradestatus(java.lang.String tradestatus){
//		this.tradestatus = tradestatus;
//	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易类型
	 */
	@Column(name ="TRADETYPE",nullable=true,length=50)
	public java.lang.String getTradetype(){
		return this.tradetype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易类型
	 */
	public void setTradetype(java.lang.String tradetype){
		this.tradetype = tradetype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易对手
	 */
	@Column(name ="CUST",nullable=true,length=50)
	public java.lang.String getCust(){
		return this.cust;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易对手
	 */
	public void setCust(java.lang.String cust){
		this.cust = cust;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交易日
	 */
	@Column(name ="TRADEDATE",nullable=true)
	public java.util.Date getTradedate(){
		return this.tradedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交易日
	 */
	public void setTradedate(java.util.Date tradedate){
		this.tradedate = tradedate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货币
	 */
	@Column(name ="CCY",nullable=true,length=3)
	public java.lang.String getCcy(){
		return this.ccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货币
	 */
	public void setCcy(java.lang.String ccy){
		this.ccy = ccy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  标准面值
	 */
	@Column(name ="NOTIONAL",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional(){
		return this.notional;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  标准面值
	 */
	public void setNotional(BigDecimal notional){
		this.notional = notional;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  标准面值折人民币
	 */
	@Column(name ="NOTIONALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionalcny(){
		return this.notionalcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  标准面值折人民币
	 */
	public void setNotionalcny(BigDecimal notionalcny){
		this.notionalcny = notionalcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  标准面值折美金
	 */
	@Column(name ="NOTIONALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionalusd(){
		return this.notionalusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  标准面值折美金
	 */
	public void setNotionalusd(BigDecimal notionalusd){
		this.notionalusd = notionalusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买卖类型
	 */
	@Column(name ="PORS",nullable=true,length=1)
	public java.lang.String getPors(){
		return this.pors;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买卖类型
	 */
	public void setPors(java.lang.String pors){
		this.pors = pors;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会计类型
	 */
	@Column(name ="TRACCTTYPE",nullable=true,length=50)
	public java.lang.String getTraccttype(){
		return this.traccttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会计类型
	 */
	public void setTraccttype(java.lang.String traccttype){
		this.traccttype = traccttype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	@Column(name ="MATDATE",nullable=true)
	public java.util.Date getMatdate(){
		return this.matdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setMatdate(java.util.Date matdate){
		this.matdate = matdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账簿
	 */
	@Column(name ="BOOK",nullable=true,length=50)
	public java.lang.String getBook(){
		return this.book;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账簿
	 */
	public void setBook(java.lang.String book){
		this.book = book;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账簿类型
	 */
	@Column(name ="BOOKTYPE",nullable=true,length=50)
	public java.lang.String getBooktype(){
		return this.booktype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账簿类型
	 */
	public void setBooktype(java.lang.String booktype){
		this.booktype = booktype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发行人
	 */
	@Column(name ="ISSUER",nullable=true,length=50)
	public java.lang.String getIssuer(){
		return this.issuer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发行人
	 */
	public void setIssuer(java.lang.String issuer){
		this.issuer = issuer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发行人类别1（PBOC）
	 */
	@Column(name ="ISSUERTYPE",nullable=true,length=50)
	public java.lang.String getIssuertype(){
		return this.issuertype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发行人类别1（PBOC）
	 */
	public void setIssuertype(java.lang.String issuertype){
		this.issuertype = issuertype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发行人类别2（SAFE）
	 */
	@Column(name ="ISSUERTYPE2",nullable=true,length=50)
	public java.lang.String getIssuertype2(){
		return this.issuertype2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发行人类别2（SAFE）
	 */
	public void setIssuertype2(java.lang.String issuertype2){
		this.issuertype2 = issuertype2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产类型
	 */
	@Column(name ="INTEREST_FIXFLOAT",nullable=true,length=50)
	public java.lang.String getInterestFixfloat(){
		return this.interestFixfloat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产类型
	 */
	public void setInterestFixfloat(java.lang.String interestFixfloat){
		this.interestFixfloat = interestFixfloat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Index
	 */
	@Column(name ="INTEREST_DMINDEX",nullable=true,length=50)
	public java.lang.String getInterestDmindex(){
		return this.interestDmindex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Index
	 */
	public void setInterestDmindex(java.lang.String interestDmindex){
		this.interestDmindex = interestDmindex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  券号
	 */
	@Column(name ="SEC",nullable=true,length=50)
	public java.lang.String getSec(){
		return this.sec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  券号
	 */
	public void setSec(java.lang.String sec){
		this.sec = sec;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  债券类型
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债券类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  债券子类型
	 */
	@Column(name ="SUBTYPE",nullable=true,length=50)
	public java.lang.String getSubtype(){
		return this.subtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债券子类型
	 */
	public void setSubtype(java.lang.String subtype){
		this.subtype = subtype;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  清算日期
	 */
	@Column(name ="VALUEDATE",nullable=true)
	public java.util.Date getValuedate(){
		return this.valuedate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  清算日期
	 */
	public void setValuedate(java.util.Date valuedate){
		this.valuedate = valuedate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  利率
	 */
	@Column(name ="INTEREST_RATE",nullable=true,precision=20,scale=4)
	public BigDecimal getInterestRate(){
		return this.interestRate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  利率
	 */
	public void setInterestRate(BigDecimal interestRate){
		this.interestRate = interestRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息基准
	 */
	@Column(name ="INTEREST_BASIS",nullable=true,length=50)
	public java.lang.String getInterestBasis(){
		return this.interestBasis;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息基准
	 */
	public void setInterestBasis(java.lang.String interestBasis){
		this.interestBasis = interestBasis;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  债券分类
	 */
	@Column(name ="ISSUETYPE_NONFIN",nullable=true,length=50)
	public java.lang.String getIssuetypeNonfin(){
		return this.issuetypeNonfin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债券分类
	 */
	public void setIssuetypeNonfin(java.lang.String issuetypeNonfin){
		this.issuetypeNonfin = issuetypeNonfin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  逻辑国家
	 */
	@Column(name ="ISSUER_LOGIC_COUNTRY",nullable=true,length=50)
	public java.lang.String getIssuerLogicCountry(){
		return this.issuerLogicCountry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  逻辑国家
	 */
	public void setIssuerLogicCountry(java.lang.String issuerLogicCountry){
		this.issuerLogicCountry = issuerLogicCountry;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否逻辑外国
	 */
	@Column(name ="ISSUER_LOGIC_FOREIGN",nullable=true,length=50)
	public java.lang.String getIssuerLogicForeign(){
		return this.issuerLogicForeign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否逻辑外国
	 */
	public void setIssuerLogicForeign(java.lang.String issuerLogicForeign){
		this.issuerLogicForeign = issuerLogicForeign;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否为金融机构
	 */
	@Column(name ="ISSUER_FIN_INS",nullable=true,length=50)
	public java.lang.String getIssuerFinIns(){
		return this.issuerFinIns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否为金融机构
	 */
	public void setIssuerFinIns(java.lang.String issuerFinIns){
		this.issuerFinIns = issuerFinIns;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家评级
	 */
	@Column(name ="ISSUER_LOGIC_COUNTRY_RATING",nullable=true,length=50)
	public java.lang.String getIssuerLogicCountryRating(){
		return this.issuerLogicCountryRating;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家评级
	 */
	public void setIssuerLogicCountryRating(java.lang.String issuerLogicCountryRating){
		this.issuerLogicCountryRating = issuerLogicCountryRating;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否地理外国
	 */
	@Column(name ="ISSUER_PHYSICAL_COUNTRY",nullable=true,length=50)
	public java.lang.String getIssuerPhysicalCountry(){
		return this.issuerPhysicalCountry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否地理外国
	 */
	public void setIssuerPhysicalCountry(java.lang.String issuerPhysicalCountry){
		this.issuerPhysicalCountry = issuerPhysicalCountry;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下一个定价日
	 */
	@Column(name ="NEXTFIXINGDAY",nullable=true)
	public java.util.Date getNextfixingday(){
		return this.nextfixingday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下一个定价日
	 */
	public void setNextfixingday(java.util.Date nextfixingday){
		this.nextfixingday = nextfixingday;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  债券市场价格
	 */
	@Column(name ="BONDMTMPRICE",nullable=true,precision=20,scale=4)
	public BigDecimal getBondmtmprice(){
		return this.bondmtmprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  债券市场价格
	 */
	public void setBondmtmprice(BigDecimal bondmtmprice){
		this.bondmtmprice = bondmtmprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末金额
	 */
	@Column(name ="ENDVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalue(){
		return this.endvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末金额
	 */
	public void setEndvalue(BigDecimal endvalue){
		this.endvalue = endvalue;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末金额折人民币
	 */
	@Column(name ="ENDVALUECNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvaluecny(){
		return this.endvaluecny;
	}
	
	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末金额折人民币
	 */
	public void setEndvaluecny(BigDecimal endvaluecny){
		this.endvaluecny = endvaluecny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末金额折美金
	 */
	@Column(name ="ENDVALUEUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueusd(){
		return this.endvalueusd;
	}
	
	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末金额折美金
	 */
	public void setEndvalueusd(BigDecimal endvalueusd){
		this.endvalueusd = endvalueusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  累计应计利息
	 */
	@Column(name ="ENDACCRUAL",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrual(){
		return this.endaccrual;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  累计应计利息
	 */
	public void setEndaccrual(BigDecimal endaccrual){
		this.endaccrual = endaccrual;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  累计应计利息折美金
	 */
	@Column(name ="ENDACCRUALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrualusd(){
		return this.endaccrualusd;
	}
	
	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  累计应计利息折美金
	 */
	public void setEndaccrualusd(BigDecimal endaccrualusd){
		this.endaccrualusd = endaccrualusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  累计应计利息折人民币
	 */
	@Column(name ="ENDACCRUALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrualcny(){
		return this.endaccrualcny;
	}
	
	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  累计应计利息折人民币
	 */
	public void setEndaccrualcny(BigDecimal endaccrualcny){
		this.endaccrualcny = endaccrualcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  账簿金额
	 */
	@Column(name ="REMBOOKVAL",nullable=true,precision=20,scale=4)
	public BigDecimal getRembookval(){
		return this.rembookval;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  账簿金额
	 */
	public void setRembookval(BigDecimal rembookval){
		this.rembookval = rembookval;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  账簿金额折人民币
	 */
	@Column(name ="REMBOOKVALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getRembookvalcny(){
		return this.rembookvalcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  账簿金额折人民币
	 */
	public void setRembookvalcny(BigDecimal rembookvalcny){
		this.rembookvalcny = rembookvalcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  账簿金额折美金
	 */
	@Column(name ="REMBOOKVALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getRembookvalusd(){
		return this.rembookvalusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  账簿金额折美金
	 */
	public void setRembookvalusd(BigDecimal rembookvalusd){
		this.rembookvalusd = rembookvalusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级1
	 */
	@Column(name ="RATING1",nullable=true,length=50)
	public java.lang.String getRating1(){
		return this.rating1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级1
	 */
	public void setRating1(java.lang.String rating1){
		this.rating1 = rating1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级机构1
	 */
	@Column(name ="RATINGAGENCYNAME1",nullable=true,length=50)
	public java.lang.String getRatingagencyname1(){
		return this.ratingagencyname1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级机构1
	 */
	public void setRatingagencyname1(java.lang.String ratingagencyname1){
		this.ratingagencyname1 = ratingagencyname1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级2
	 */
	@Column(name ="RATING2",nullable=true,length=50)
	public java.lang.String getRating2(){
		return this.rating2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级2
	 */
	public void setRating2(java.lang.String rating2){
		this.rating2 = rating2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级机构2
	 */
	@Column(name ="RATINGAGENCYNAME2",nullable=true,length=50)
	public java.lang.String getRatingagencyname2(){
		return this.ratingagencyname2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级机构2
	 */
	public void setRatingagencyname2(java.lang.String ratingagencyname2){
		this.ratingagencyname2 = ratingagencyname2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级3
	 */
	@Column(name ="RATING3",nullable=true,length=50)
	public java.lang.String getRating3(){
		return this.rating3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级3
	 */
	public void setRating3(java.lang.String rating3){
		this.rating3 = rating3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  评级机构3
	 */
	@Column(name ="RATINGAGENCYNAME3",nullable=true,length=50)
	public java.lang.String getRatingagencyname3(){
		return this.ratingagencyname3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  评级机构3
	 */
	public void setRatingagencyname3(java.lang.String ratingagencyname3){
		this.ratingagencyname3 = ratingagencyname3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机构
	 */
	@Column(name ="COMPANY",nullable=true,length=50)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-营业日，日
	 */
	@Column(name ="MATIMPD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpd(){
		return this.matimpd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-营业日，日
	 */
	public void setMatimpd(java.lang.Integer matimpd){
		this.matimpd = matimpd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-营业日，月份
	 */
	@Column(name ="MATIMPM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpm(){
		return this.matimpm;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-营业日，月份
	 */
	public void setMatimpm(java.lang.Integer matimpm){
		this.matimpm = matimpm;
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
}
