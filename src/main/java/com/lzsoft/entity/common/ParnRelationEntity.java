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
 * @Description: 集团关系表
 * @author zhangdaihao
 * @date 2016-03-25 09:13:18
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_parn_relation", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ParnRelationEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**集团编号*/
	private java.lang.String parn;
	/**集团名称*/
	private java.lang.String parnm;
	/**集团下属客户号*/
	private java.lang.String csnm;
	/**集团授信币种*/
	private java.lang.String fccy;
	/**集团授信总限额*/
	private BigDecimal famt;
	/**备注*/
	private java.lang.String memo;
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
	 *@return: java.lang.String  集团编号
	 */
	@Column(name ="PARN",nullable=true,length=50)
	public java.lang.String getParn(){
		return this.parn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集团编号
	 */
	public void setParn(java.lang.String parn){
		this.parn = parn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集团名称
	 */
	@Column(name ="PARNM",nullable=true,length=200)
	public java.lang.String getParnm(){
		return this.parnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集团名称
	 */
	public void setParnm(java.lang.String parnm){
		this.parnm = parnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集团下属客户号
	 */
	@Column(name ="CSNM",nullable=true,length=100)
	public java.lang.String getCsnm(){
		return this.csnm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集团下属客户号
	 */
	public void setCsnm(java.lang.String csnm){
		this.csnm = csnm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集团授信币种
	 */
	@Column(name ="FCCY",nullable=true,length=50)
	public java.lang.String getFccy(){
		return this.fccy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集团授信币种
	 */
	public void setFccy(java.lang.String fccy){
		this.fccy = fccy;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  集团授信总限额
	 */
	@Column(name ="FAMT",nullable=true,precision=19,scale=2)
	public BigDecimal getFamt(){
		return this.famt;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  集团授信总限额
	 */
	public void setFamt(BigDecimal famt){
		this.famt = famt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="MEMO",nullable=true,length=200)
	public java.lang.String getMemo(){
		return this.memo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setMemo(java.lang.String memo){
		this.memo = memo;
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
