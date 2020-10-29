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
 * @Description: SUMMIT_REPO抵质押品信息表
 * @author zhangdaihao
 * @date 2016-05-18 16:38:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_coll", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtCollEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**交易编号*/
	private java.lang.String tradeid;
	/**债券类型*/
	private java.lang.String type;
	/**债券编号*/
	private java.lang.String sec;
	/**币种*/
	private java.lang.String ccy;
	/**面值*/
	private BigDecimal quantity;
	/**面值折人民币*/
	private BigDecimal quantitycny;
	/**面值折美金*/
	private BigDecimal quantityusd;
	/**抵押开始时间*/
	private java.lang.String startdate;
	/**抵押品净价（开始）*/
	private BigDecimal startprice;
	/**抵押品净价（开始）折人民币*/
	private BigDecimal startpricecny;
	/**抵押品净价（开始）折美金*/
	private BigDecimal startpriceusd;
	/**抵押结束日期*/
	private java.lang.String enddate;
	/**抵押品净价（结束）*/
	private BigDecimal endprice;
	/**抵押品净价（结束）折人民币*/
	private BigDecimal endpricecny;
	/**抵押品净价（结束）折美金*/
	private BigDecimal endpriceusd;
	/**抵押折扣*/
	private BigDecimal margin;
	/**抵押折扣计算方法*/
	private java.lang.String marginexp;
	/**MBS Pool Factor*/
	private java.lang.String poolfactor;
	
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
	 *@return: java.lang.String  债券编号
	 */
	@Column(name ="SEC",nullable=true,length=50)
	public java.lang.String getSec(){
		return this.sec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  债券编号
	 */
	public void setSec(java.lang.String sec){
		this.sec = sec;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	@Column(name ="CCY",nullable=true,length=50)
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值
	 */
	@Column(name ="QUANTITY",nullable=true,precision=20,scale=4)
	public BigDecimal getQuantity(){
		return this.quantity;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值
	 */
	public void setQuantity(BigDecimal quantity){
		this.quantity = quantity;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值折人民币
	 */
	@Column(name ="QUANTITYCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getQuantitycny(){
		return this.quantitycny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值折人民币
	 */
	public void setQuantitycny(BigDecimal quantitycny){
		this.quantitycny = quantitycny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  面值折美金
	 */
	@Column(name ="QUANTITYUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getQuantityusd(){
		return this.quantityusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值折美金
	 */
	public void setQuantityusd(BigDecimal quantityusd){
		this.quantityusd = quantityusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押开始时间
	 */
	@Column(name ="STARTDATE",nullable=true,length=50)
	public java.lang.String getStartdate(){
		return this.startdate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押开始时间
	 */
	public void setStartdate(java.lang.String startdate){
		this.startdate = startdate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（开始）
	 */
	@Column(name ="STARTPRICE",nullable=true,precision=20,scale=4)
	public BigDecimal getStartprice(){
		return this.startprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（开始）
	 */
	public void setStartprice(BigDecimal startprice){
		this.startprice = startprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（开始）折人民币
	 */
	@Column(name ="STARTPRICECNY",nullable=true,precision=20,scale=4)
	public BigDecimal getStartpricecny(){
		return this.startpricecny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（开始）折人民币
	 */
	public void setStartpricecny(BigDecimal startpricecny){
		this.startpricecny = startpricecny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（开始）折美金
	 */
	@Column(name ="STARTPRICEUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getStartpriceusd(){
		return this.startpriceusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（开始）折美金
	 */
	public void setStartpriceusd(BigDecimal startpriceusd){
		this.startpriceusd = startpriceusd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押结束日期
	 */
	@Column(name ="ENDDATE",nullable=true,length=50)
	public java.lang.String getEnddate(){
		return this.enddate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押结束日期
	 */
	public void setEnddate(java.lang.String enddate){
		this.enddate = enddate;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（结束）
	 */
	@Column(name ="ENDPRICE",nullable=true,precision=20,scale=4)
	public BigDecimal getEndprice(){
		return this.endprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（结束）
	 */
	public void setEndprice(BigDecimal endprice){
		this.endprice = endprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（结束）折人民币
	 */
	@Column(name ="ENDPRICECNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndpricecny(){
		return this.endpricecny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（结束）折人民币
	 */
	public void setEndpricecny(BigDecimal endpricecny){
		this.endpricecny = endpricecny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押品净价（结束）折美金
	 */
	@Column(name ="ENDPRICEUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndpriceusd(){
		return this.endpriceusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押品净价（结束）折美金
	 */
	public void setEndpriceusd(BigDecimal endpriceusd){
		this.endpriceusd = endpriceusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  抵押折扣
	 */
	@Column(name ="MARGIN",nullable=true,precision=20,scale=4)
	public BigDecimal getMargin(){
		return this.margin;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  抵押折扣
	 */
	public void setMargin(BigDecimal margin){
		this.margin = margin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  抵押折扣计算方法
	 */
	@Column(name ="MARGINEXP",nullable=true,length=50)
	public java.lang.String getMarginexp(){
		return this.marginexp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  抵押折扣计算方法
	 */
	public void setMarginexp(java.lang.String marginexp){
		this.marginexp = marginexp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  MBS Pool Factor
	 */
	@Column(name ="POOLFACTOR",nullable=true,length=50)
	public java.lang.String getPoolfactor(){
		return this.poolfactor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  MBS Pool Factor
	 */
	public void setPoolfactor(java.lang.String poolfactor){
		this.poolfactor = poolfactor;
	}
}
