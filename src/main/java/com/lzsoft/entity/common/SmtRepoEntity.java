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
 * @Description: SUMMIT-REPO回购信息表
 * @author zhangdaihao
 * @date 2016-03-15 14:43:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_repo", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtRepoEntity implements java.io.Serializable {
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
	/**交易日*/
	private java.util.Date tradedate;
	/**生效日*/
	private java.util.Date effdate;
	/**到期日*/
	private java.util.Date matdate;
	/**币种*/
	private java.lang.String ccy;
	/**资产类型*/
	private java.lang.String interestFixfloat;
	/**利率*/
	private BigDecimal interestRate;
	/**面值*/
	private BigDecimal notional;
	/**面值折人民币*/
	private BigDecimal notionalcny;
	/**面值折美金*/
	private BigDecimal notionalusd;
	/**收付类型*/
	private java.lang.String pors;
	/**回购类型*/
	private java.lang.String reptype;
	/**计息基准*/
	private java.lang.String interestBasis;
	/**Index*/
	private java.lang.String interestDmindex;
	/**账簿*/
	private java.lang.String book;
	/**账簿种类*/
	private java.lang.String bookType;
	/**下一定价日*/
	private java.util.Date nextfixingday;
	/**机构*/
	private java.lang.String company;
	/**系统日期*/
	private java.util.Date sysDate;
	/**营业日期*/
	private java.util.Date importdate;
	/**到期天数*/
	private java.lang.Integer matimpd;
	/**到期月数*/
	private java.lang.Integer matimpm;
	/**分支行号*/
	private java.lang.String brca;
	/**母行号*/
	private java.lang.String parentbrca;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	@Column(name ="CCY",nullable=true,length=3)
	public java.lang.String getCcy(){
		return this.ccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setCcy(java.lang.String ccy){
		this.ccy = ccy;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值
	 */
	@Column(name ="NOTIONAL",nullable=true,precision=20,scale=4)
	public BigDecimal getNotional(){
		return this.notional;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值
	 */
	public void setNotional(BigDecimal notional){
		this.notional = notional;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值折人民币
	 */
	@Column(name ="NOTIONALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionalcny(){
		return this.notionalcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值折人民币
	 */
	public void setNotionalcny(BigDecimal notionalcny){
		this.notionalcny = notionalcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值折美金
	 */
	@Column(name ="NOTIONALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionalusd(){
		return this.notionalusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值折美金
	 */
	public void setNotionalusd(BigDecimal notionalusd){
		this.notionalusd = notionalusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收付类型
	 */
	@Column(name ="PORS",nullable=true,length=1)
	public java.lang.String getPors(){
		return this.pors;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收付类型
	 */
	public void setPors(java.lang.String pors){
		this.pors = pors;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  回购类型
	 */
	@Column(name ="REPTYPE",nullable=true,length=50)
	public java.lang.String getReptype(){
		return this.reptype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  回购类型
	 */
	public void setReptype(java.lang.String reptype){
		this.reptype = reptype;
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
	 *@return: java.lang.String  账簿种类
	 */
	@Column(name ="BOOK_TYPE",nullable=true,length=50)
	public java.lang.String getBookType(){
		return this.bookType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账簿种类
	 */
	public void setBookType(java.lang.String bookType){
		this.bookType = bookType;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下一定价日
	 */
	@Column(name ="NEXTFIXINGDAY",nullable=true)
	public java.util.Date getNextfixingday(){
		return this.nextfixingday;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下一定价日
	 */
	public void setNextfixingday(java.util.Date nextfixingday){
		this.nextfixingday = nextfixingday;
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
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期天数
	 */
	@Column(name ="MATIMPD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpd(){
		return this.matimpd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期天数
	 */
	public void setMatimpd(java.lang.Integer matimpd){
		this.matimpd = matimpd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期月数
	 */
	@Column(name ="MATIMPM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpm(){
		return this.matimpm;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期月数
	 */
	public void setMatimpm(java.lang.Integer matimpm){
		this.matimpm = matimpm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支行号
	 */
	@Column(name ="BRCA",nullable=true,length=4)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支行号
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  母行号
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=4)
	public java.lang.String getParentbrca(){
		return this.parentbrca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  母行号
	 */
	public void setParentbrca(java.lang.String parentbrca){
		this.parentbrca = parentbrca;
	}
}
