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
 * @Description: SUMMIT_MM
 * @author zhangdaihao
 * @date 2016-05-17 15:21:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_mm", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtMmEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**交易编号*/
	private java.lang.String tradeid;
	/**交易状态*/
	private java.lang.String tradestatus;
	/**交易类型*/
	private java.lang.String tradetype;
	/**交易对手*/
	private java.lang.String cust;
	/**帐簿*/
	private java.lang.String book;
	/**帐簿类型*/
	private java.lang.String booktype;
	/**描述*/
	private java.lang.String descrip;
	/**到期日*/
	private java.util.Date matdate;
	/**到期日-营业日期相差天数*/
	private java.lang.Integer matimpd;
	/**到期日-营业日期相差月数*/
	private java.lang.Integer matimpm;
	/**生效日*/
	private java.util.Date effdate;
	/**到期日-生效日期相差天数*/
	private java.lang.Integer mateffd;
	/**到期日-生效日期相差月数*/
	private java.lang.Integer mateffm;
	/**结束日期*/
	private java.util.Date enddate;
	/**下一个定价日*/
	private java.util.Date nextfixingday;
	/**货币*/
	private java.lang.String ccy;
	/**货币市场类型*/
	private java.lang.String mmtype;
	/**标准面值*/
	private BigDecimal notional;
	/**标准面值折人民币*/
	private BigDecimal notionalcny;
	/**标准面值折美金*/
	private BigDecimal notionalusd;
	/**标准面值折台币*/
	private BigDecimal notionaltwd;
	/**买/卖类型*/
	private java.lang.String pors;
	/**利率*/
	private BigDecimal interestRate;
	/**资产类型*/
	private java.lang.String interestFixfloat;
	/**期初/期末支付*/
	private java.lang.String schedPayTime;
	/**交易输入日期*/
	private java.util.Date inputdate;
	/**机构*/
	private java.lang.String company;
	/**营业日期*/
	private java.util.Date importdate;
	/**parentbrca*/
	private java.lang.String parentbrca;
	/**brca*/
	private java.lang.String brca;
	/**备注（对台报表用）*/
	private java.lang.String mmNote;
	/**MM客户名称（对台报表用）*/
	private java.lang.String mmCustomer;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
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
	 *@return: java.lang.String  帐簿
	 */
	@Column(name ="BOOK",nullable=true,length=50)
	public java.lang.String getBook(){
		return this.book;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帐簿
	 */
	public void setBook(java.lang.String book){
		this.book = book;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  帐簿类型
	 */
	@Column(name ="BOOKTYPE",nullable=true,length=50)
	public java.lang.String getBooktype(){
		return this.booktype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  帐簿类型
	 */
	public void setBooktype(java.lang.String booktype){
		this.booktype = booktype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DESCRIP",nullable=true,length=50)
	public java.lang.String getDescrip(){
		return this.descrip;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDescrip(java.lang.String descrip){
		this.descrip = descrip;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-营业日期相差天数
	 */
	@Column(name ="MATIMPD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpd(){
		return this.matimpd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-营业日期相差天数
	 */
	public void setMatimpd(java.lang.Integer matimpd){
		this.matimpd = matimpd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-营业日期相差月数
	 */
	@Column(name ="MATIMPM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMatimpm(){
		return this.matimpm;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-营业日期相差月数
	 */
	public void setMatimpm(java.lang.Integer matimpm){
		this.matimpm = matimpm;
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-生效日期相差天数
	 */
	@Column(name ="MATEFFD",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMateffd(){
		return this.mateffd;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-生效日期相差天数
	 */
	public void setMateffd(java.lang.Integer mateffd){
		this.mateffd = mateffd;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  到期日-生效日期相差月数
	 */
	@Column(name ="MATEFFM",nullable=true,precision=10,scale=0)
	public java.lang.Integer getMateffm(){
		return this.mateffm;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  到期日-生效日期相差月数
	 */
	public void setMateffm(java.lang.Integer mateffm){
		this.mateffm = mateffm;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束日期
	 */
	@Column(name ="ENDDATE",nullable=true)
	public java.util.Date getEnddate(){
		return this.enddate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束日期
	 */
	public void setEnddate(java.util.Date enddate){
		this.enddate = enddate;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货币市场类型
	 */
	@Column(name ="MMTYPE",nullable=true,length=50)
	public java.lang.String getMmtype(){
		return this.mmtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货币市场类型
	 */
	public void setMmtype(java.lang.String mmtype){
		this.mmtype = mmtype;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  标准面值折台币
	 */
	@Column(name ="NOTIONALTWD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionaltwd() {
		return notionaltwd;
	}
	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  标准面值折台币
	 */
	public void setNotionaltwd(BigDecimal notionaltwd) {
		this.notionaltwd = notionaltwd;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买/卖类型
	 */
	@Column(name ="PORS",nullable=true,length=1)
	public java.lang.String getPors(){
		return this.pors;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买/卖类型
	 */
	public void setPors(java.lang.String pors){
		this.pors = pors;
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
	 *@return: java.lang.String  期初/期末支付
	 */
	@Column(name ="SCHED_PAY_TIME",nullable=true,length=50)
	public java.lang.String getSchedPayTime(){
		return this.schedPayTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  期初/期末支付
	 */
	public void setSchedPayTime(java.lang.String schedPayTime){
		this.schedPayTime = schedPayTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交易输入日期
	 */
	@Column(name ="INPUTDATE",nullable=true)
	public java.util.Date getInputdate(){
		return this.inputdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交易输入日期
	 */
	public void setInputdate(java.util.Date inputdate){
		this.inputdate = inputdate;
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
	 *@return: java.lang.String  parentbrca
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=4)
	public java.lang.String getParentbrca(){
		return this.parentbrca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  parentbrca
	 */
	public void setParentbrca(java.lang.String parentbrca){
		this.parentbrca = parentbrca;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  brca
	 */
	@Column(name ="BRCA",nullable=true,length=4)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  brca
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注（对台报表用）
	 */
	@Column(name ="MMNOTE",nullable=true,length=50)
	public java.lang.String getMmNote() {
		return mmNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注（对台报表用）
	 */
	public void setMmNote(java.lang.String mmNote) {
		this.mmNote = mmNote;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  MM客户名称（对台报表用）
	 */
	@Column(name ="MMCUSTOMER",nullable=true,length=50)
	public java.lang.String getMmCustomer() {
		return mmCustomer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  MM客户名称（对台报表用）
	 */
	public void setMmCustomer(java.lang.String mmCustomer) {
		this.mmCustomer = mmCustomer;
	}
	
	
}
