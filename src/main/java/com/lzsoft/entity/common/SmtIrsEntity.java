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
 * @Description: SUMMIT_IRS
 * @author zhangdaihao
 * @date 2016-03-15 14:35:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_irs", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtIrsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**交易编号*/
	private java.lang.String tradeid;
	/**交易状态*/
	private java.lang.String tradestatus;
	/**交易类型*/
	private java.lang.String tradetype;
	/**交易对手*/
	private java.lang.String cust;
	/**账簿*/
	private java.lang.String book;
	/**帐簿类型*/
	private java.lang.String bookType;
	/**子类型*/
	private java.lang.String subtype;
	/**交易日*/
	private java.util.Date tradedate;
	/**生效日*/
	private java.util.Date effdate;
	/**到期日*/
	private java.util.Date matdate;
	/**下次到期日*/
	private java.util.Date nextfixing;
	/**币种1*/
	private java.lang.String ccy1;
	/**币种2*/
	private java.lang.String ccy2;
	/**收付类型1*/
	private java.lang.String pors1;
	/**收付类型2*/
	private java.lang.String pors2;
	/**面值1*/
	private BigDecimal notional1;
	/**面值1折美金*/
	private BigDecimal notional1usd;
	/**面值1折人民币*/
	private BigDecimal notional1cny;
	/**面值2*/
	private BigDecimal notional2;
	/**面值2折美金*/
	private BigDecimal notional2usd;
	/**面值2折人民币*/
	private BigDecimal notional2cny;
	/**利率1*/
	private BigDecimal interestRate1;
	/**利率2*/
	private BigDecimal interestRate2;
	/**计息基准1*/
	private java.lang.String interestBasis1;
	/**计息基准2*/
	private java.lang.String interestBasis2;
	/**Index1*/
	private java.lang.String interestDmindex1;
	/**Index2*/
	private java.lang.String interestDmindex2;
	/**机构*/
	private java.lang.String company;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  交易状态
	 */
	@Column(name ="TRADESTATUS",nullable=true,length=50)
	public java.lang.String getTradestatus(){
		return this.tradestatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易状态
	 */
	public void setTradestatus(java.lang.String tradestatus){
		this.tradestatus = tradestatus;
	}
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
	 *@return: java.lang.String  帐簿类型
	 */
	@Column(name ="BOOK_TYPE",nullable=true,length=50)
	public java.lang.String getBookType(){
		return this.bookType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帐簿类型
	 */
	public void setBookType(java.lang.String bookType){
		this.bookType = bookType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  子类型
	 */
	@Column(name ="SUBTYPE",nullable=true,length=50)
	public java.lang.String getSubtype(){
		return this.subtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  子类型
	 */
	public void setSubtype(java.lang.String subtype){
		this.subtype = subtype;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日
	 */
	@Column(name ="EFFDATE",nullable=true)
	public java.util.Date getEffdate(){
		return this.effdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日
	 */
	public void setEffdate(java.util.Date effdate){
		this.effdate = effdate;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下次到期日
	 */
	@Column(name ="NEXTFIXING",nullable=true)
	public java.util.Date getNextfixing(){
		return this.nextfixing;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下次到期日
	 */
	public void setNextfixing(java.util.Date nextfixing){
		this.nextfixing = nextfixing;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种1
	 */
	@Column(name ="CCY1",nullable=true,length=3)
	public java.lang.String getCcy1(){
		return this.ccy1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种1
	 */
	public void setCcy1(java.lang.String ccy1){
		this.ccy1 = ccy1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种2
	 */
	@Column(name ="CCY2",nullable=true,length=3)
	public java.lang.String getCcy2(){
		return this.ccy2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种2
	 */
	public void setCcy2(java.lang.String ccy2){
		this.ccy2 = ccy2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收付类型1
	 */
	@Column(name ="PORS1",nullable=true,length=1)
	public java.lang.String getPors1(){
		return this.pors1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收付类型1
	 */
	public void setPors1(java.lang.String pors1){
		this.pors1 = pors1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收付类型2
	 */
	@Column(name ="PORS2",nullable=true,length=1)
	public java.lang.String getPors2(){
		return this.pors2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收付类型2
	 */
	public void setPors2(java.lang.String pors2){
		this.pors2 = pors2;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值1
	 */
	@Column(name ="NOTIONAL1",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional1(){
		return this.notional1;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值1
	 */
	public void setNotional1(BigDecimal notional1){
		this.notional1 = notional1;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值1折美金
	 */
	@Column(name ="NOTIONAL1USD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional1usd(){
		return this.notional1usd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值1折美金
	 */
	public void setNotional1usd(BigDecimal notional1usd){
		this.notional1usd = notional1usd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值1折人民币
	 */
	@Column(name ="NOTIONAL1CNY",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional1cny(){
		return this.notional1cny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值1折人民币
	 */
	public void setNotional1cny(BigDecimal notional1cny){
		this.notional1cny = notional1cny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值2
	 */
	@Column(name ="NOTIONAL2",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional2(){
		return this.notional2;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值2
	 */
	public void setNotional2(BigDecimal notional2){
		this.notional2 = notional2;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值2折美金
	 */
	@Column(name ="NOTIONAL2USD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional2usd(){
		return this.notional2usd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值2折美金
	 */
	public void setNotional2usd(BigDecimal notional2usd){
		this.notional2usd = notional2usd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值2折人民币
	 */
	@Column(name ="NOTIONAL2CNY",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional2cny(){
		return this.notional2cny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值2折人民币
	 */
	public void setNotional2cny(BigDecimal notional2cny){
		this.notional2cny = notional2cny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  利率1
	 */
	@Column(name ="INTEREST_RATE1",nullable=true,precision=20,scale=4)
	public BigDecimal getInterestRate1(){
		return this.interestRate1;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  利率1
	 */
	public void setInterestRate1(BigDecimal interestRate1){
		this.interestRate1 = interestRate1;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  利率2
	 */
	@Column(name ="INTEREST_RATE2",nullable=true,precision=20,scale=4)
	public BigDecimal getInterestRate2(){
		return this.interestRate2;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  利率2
	 */
	public void setInterestRate2(BigDecimal interestRate2){
		this.interestRate2 = interestRate2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息基准1
	 */
	@Column(name ="INTEREST_BASIS1",nullable=true,length=50)
	public java.lang.String getInterestBasis1(){
		return this.interestBasis1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息基准1
	 */
	public void setInterestBasis1(java.lang.String interestBasis1){
		this.interestBasis1 = interestBasis1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计息基准2
	 */
	@Column(name ="INTEREST_BASIS2",nullable=true,length=50)
	public java.lang.String getInterestBasis2(){
		return this.interestBasis2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计息基准2
	 */
	public void setInterestBasis2(java.lang.String interestBasis2){
		this.interestBasis2 = interestBasis2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Index1
	 */
	@Column(name ="INTEREST_DMINDEX1",nullable=true,length=50)
	public java.lang.String getInterestDmindex1(){
		return this.interestDmindex1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Index1
	 */
	public void setInterestDmindex1(java.lang.String interestDmindex1){
		this.interestDmindex1 = interestDmindex1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Index2
	 */
	@Column(name ="INTEREST_DMINDEX2",nullable=true,length=50)
	public java.lang.String getInterestDmindex2(){
		return this.interestDmindex2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Index2
	 */
	public void setInterestDmindex2(java.lang.String interestDmindex2){
		this.interestDmindex2 = interestDmindex2;
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
