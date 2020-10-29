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
 * @Description: SUMMIT-FXSwap外汇掉期交易明细信息表
 * @author zhangdaihao
 * @date 2016-03-15 14:40:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_fxswap", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtFxswapEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**分支机构代码*/
	private java.lang.String brca;
	/**交易编号*/
	private java.lang.String tradeid;
	/**交易状态*/
	private java.lang.String tradestatus;
	/**交易对手*/
	private java.lang.String cust;
	/**账簿*/
	private java.lang.String book;
	/**账户类型*/
	private java.lang.String type;
	/**描述*/
	private java.lang.String descrip;
	/**交易日*/
	private java.util.Date tradedate;
	/**起息日*/
	private java.util.Date valdate;
	/**到期日*/
	private java.util.Date maturitydate;
	/**买/卖类型*/
	private java.lang.String pors;
	/**买入金额*/
	private BigDecimal boughtamt;
	/**买入金额交易日折美金*/
	private BigDecimal boughtamtusdtrade;
	/**买入金额交易日折人民币*/
	private BigDecimal boughtamtcnytrade;
	/**买入金额起息日折美金*/
	private BigDecimal boughtamtusdvalue;
	/**买入金额起息日折人民币*/
	private BigDecimal boughtamtcnyvalue;
	/**买入币种*/
	private java.lang.String boughtccy;
	/**卖出金额*/
	private BigDecimal soldamt;
	/**卖出金额交易日折美金*/
	private BigDecimal soldamtusdtrade;
	/**卖出金额交易日折人民币*/
	private BigDecimal soldamtcnytrade;
	/**卖出金额起息日折美金*/
	private BigDecimal soldamtusdvalue;
	/**卖出金额起息日折人民币*/
	private BigDecimal soldamtcnyvalue;
	/**卖出币种*/
	private java.lang.String soldccy;
	/**买入货币利率*/
	private BigDecimal boughtrate;
	/**卖出货币利率*/
	private BigDecimal soldrate;
	/**即期汇率*/
	private BigDecimal spotrate;
	/**近端起息日*/
	private java.util.Date shortvaldate;
	/**远端买入金额*/
	private BigDecimal fwdboughtamt;
	/**远端买入金额折美金*/
	private BigDecimal fwdboughtamtusd;
	/**远端买入金额折人民币*/
	private BigDecimal fwdboughtamtcny;
	/**远端卖出金额*/
	private BigDecimal fwdsoldamt;
	/**远端卖出金额折美金*/
	private BigDecimal fwdsoldamtusd;
	/**远端卖出金额折人民币*/
	private BigDecimal fwdsoldamtcny;
	/**系统日期*/
	private java.util.Date sysDate;
	/**机构*/
	private java.lang.String company;
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
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=4)
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
	 *@return: java.lang.String  分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=4)
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
	 *@return: java.lang.String  账户类型
	 */
	@Column(name ="TYPE",nullable=true,length=50)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账户类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
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
	 *@return: java.util.Date  起息日
	 */
	@Column(name ="VALDATE",nullable=true)
	public java.util.Date getValdate(){
		return this.valdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  起息日
	 */
	public void setValdate(java.util.Date valdate){
		this.valdate = valdate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	@Column(name ="MATURITYDATE",nullable=true)
	public java.util.Date getMaturitydate(){
		return this.maturitydate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setMaturitydate(java.util.Date maturitydate){
		this.maturitydate = maturitydate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买/卖类型
	 */
	@Column(name ="PORS",nullable=true,length=50)
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
	 *@return: BigDecimal  买入金额
	 */
	@Column(name ="BOUGHTAMT",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtamt(){
		return this.boughtamt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入金额
	 */
	public void setBoughtamt(BigDecimal boughtamt){
		this.boughtamt = boughtamt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  买入金额交易日折美金
	 */
	@Column(name ="BOUGHTAMTUSDTRADE",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtamtusdtrade(){
		return this.boughtamtusdtrade;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入金额交易日折美金
	 */
	public void setBoughtamtusdtrade(BigDecimal boughtamtusdtrade){
		this.boughtamtusdtrade = boughtamtusdtrade;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  买入金额交易日折人民币
	 */
	@Column(name ="BOUGHTAMTCNYTRADE",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtamtcnytrade(){
		return this.boughtamtcnytrade;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入金额交易日折人民币
	 */
	public void setBoughtamtcnytrade(BigDecimal boughtamtcnytrade){
		this.boughtamtcnytrade = boughtamtcnytrade;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  买入金额起息日折美金
	 */
	@Column(name ="BOUGHTAMTUSDVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtamtusdvalue(){
		return this.boughtamtusdvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入金额起息日折美金
	 */
	public void setBoughtamtusdvalue(BigDecimal boughtamtusdvalue){
		this.boughtamtusdvalue = boughtamtusdvalue;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  买入金额起息日折人民币
	 */
	@Column(name ="BOUGHTAMTCNYVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtamtcnyvalue(){
		return this.boughtamtcnyvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入金额起息日折人民币
	 */
	public void setBoughtamtcnyvalue(BigDecimal boughtamtcnyvalue){
		this.boughtamtcnyvalue = boughtamtcnyvalue;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买入币种
	 */
	@Column(name ="BOUGHTCCY",nullable=true,length=3)
	public java.lang.String getBoughtccy(){
		return this.boughtccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  买入币种
	 */
	public void setBoughtccy(java.lang.String boughtccy){
		this.boughtccy = boughtccy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出金额
	 */
	@Column(name ="SOLDAMT",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldamt(){
		return this.soldamt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出金额
	 */
	public void setSoldamt(BigDecimal soldamt){
		this.soldamt = soldamt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出金额交易日折美金
	 */
	@Column(name ="SOLDAMTUSDTRADE",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldamtusdtrade(){
		return this.soldamtusdtrade;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出金额交易日折美金
	 */
	public void setSoldamtusdtrade(BigDecimal soldamtusdtrade){
		this.soldamtusdtrade = soldamtusdtrade;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出金额交易日折人民币
	 */
	@Column(name ="SOLDAMTCNYTRADE",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldamtcnytrade(){
		return this.soldamtcnytrade;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出金额交易日折人民币
	 */
	public void setSoldamtcnytrade(BigDecimal soldamtcnytrade){
		this.soldamtcnytrade = soldamtcnytrade;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出金额起息日折美金
	 */
	@Column(name ="SOLDAMTUSDVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldamtusdvalue(){
		return this.soldamtusdvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出金额起息日折美金
	 */
	public void setSoldamtusdvalue(BigDecimal soldamtusdvalue){
		this.soldamtusdvalue = soldamtusdvalue;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出金额起息日折人民币
	 */
	@Column(name ="SOLDAMTCNYVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldamtcnyvalue(){
		return this.soldamtcnyvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出金额起息日折人民币
	 */
	public void setSoldamtcnyvalue(BigDecimal soldamtcnyvalue){
		this.soldamtcnyvalue = soldamtcnyvalue;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  卖出币种
	 */
	@Column(name ="SOLDCCY",nullable=true,length=3)
	public java.lang.String getSoldccy(){
		return this.soldccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  卖出币种
	 */
	public void setSoldccy(java.lang.String soldccy){
		this.soldccy = soldccy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  买入货币利率
	 */
	@Column(name ="BOUGHTRATE",nullable=true,precision=20,scale=4)
	public BigDecimal getBoughtrate(){
		return this.boughtrate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  买入货币利率
	 */
	public void setBoughtrate(BigDecimal boughtrate){
		this.boughtrate = boughtrate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  卖出货币利率
	 */
	@Column(name ="SOLDRATE",nullable=true,precision=20,scale=4)
	public BigDecimal getSoldrate(){
		return this.soldrate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  卖出货币利率
	 */
	public void setSoldrate(BigDecimal soldrate){
		this.soldrate = soldrate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  即期汇率
	 */
	@Column(name ="SPOTRATE",nullable=true,precision=20,scale=4)
	public BigDecimal getSpotrate(){
		return this.spotrate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  即期汇率
	 */
	public void setSpotrate(BigDecimal spotrate){
		this.spotrate = spotrate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  近端起息日
	 */
	@Column(name ="SHORTVALDATE",nullable=true)
	public java.util.Date getShortvaldate(){
		return this.shortvaldate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  近端起息日
	 */
	public void setShortvaldate(java.util.Date shortvaldate){
		this.shortvaldate = shortvaldate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端买入金额
	 */
	@Column(name ="FWDBOUGHTAMT",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdboughtamt(){
		return this.fwdboughtamt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端买入金额
	 */
	public void setFwdboughtamt(BigDecimal fwdboughtamt){
		this.fwdboughtamt = fwdboughtamt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端买入金额折美金
	 */
	@Column(name ="FWDBOUGHTAMTUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdboughtamtusd(){
		return this.fwdboughtamtusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端买入金额折美金
	 */
	public void setFwdboughtamtusd(BigDecimal fwdboughtamtusd){
		this.fwdboughtamtusd = fwdboughtamtusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端买入金额折人民币
	 */
	@Column(name ="FWDBOUGHTAMTCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdboughtamtcny(){
		return this.fwdboughtamtcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端买入金额折人民币
	 */
	public void setFwdboughtamtcny(BigDecimal fwdboughtamtcny){
		this.fwdboughtamtcny = fwdboughtamtcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端卖出金额
	 */
	@Column(name ="FWDSOLDAMT",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdsoldamt(){
		return this.fwdsoldamt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端卖出金额
	 */
	public void setFwdsoldamt(BigDecimal fwdsoldamt){
		this.fwdsoldamt = fwdsoldamt;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端卖出金额折美金
	 */
	@Column(name ="FWDSOLDAMTUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdsoldamtusd(){
		return this.fwdsoldamtusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端卖出金额折美金
	 */
	public void setFwdsoldamtusd(BigDecimal fwdsoldamtusd){
		this.fwdsoldamtusd = fwdsoldamtusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  远端卖出金额折人民币
	 */
	@Column(name ="FWDSOLDAMTCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFwdsoldamtcny(){
		return this.fwdsoldamtcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  远端卖出金额折人民币
	 */
	public void setFwdsoldamtcny(BigDecimal fwdsoldamtcny){
		this.fwdsoldamtcny = fwdsoldamtcny;
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
}
