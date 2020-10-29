package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 十大户
 * @author onlineGenerator
 * @date 2015-09-25 15:52:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_top10", schema = "")
@SuppressWarnings("serial")
public class Top10Entity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	private java.lang.String seqno;
	private java.lang.String ctnm;
	private java.lang.String encode;
	private java.lang.String parentbrca;
	private java.math.BigDecimal d;
	private java.math.BigDecimal e;
	private java.math.BigDecimal f;
	private java.lang.String g;
	private java.math.BigDecimal h;
	private java.math.BigDecimal i;
	private java.math.BigDecimal j;
	private java.math.BigDecimal k;
	private java.math.BigDecimal l;
	private java.math.BigDecimal m;
	private java.util.Date importdate;
	private java.lang.String rptno;
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
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一级机构代码
	 */
	@Column(name ="PARENTBRCA",nullable=true,length=50)
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
	@Column(name ="seqno",nullable=true,length=2)
	public java.lang.String getSeqno() {
		return seqno;
	}

	public void setSeqno(java.lang.String seqno) {
		this.seqno = seqno;
	}
	@Column(name ="ctnm",nullable=true,length=150)
	public java.lang.String getCtnm() {
		return ctnm;
	}

	public void setCtnm(java.lang.String ctnm) {
		this.ctnm = ctnm;
	}
	@Column(name ="encode",nullable=true,length=50)
	public java.lang.String getEncode() {
		return encode;
	}
	
	public void setEncode(java.lang.String encode) {
		this.encode = encode;
	}
	@Column(name ="d",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getD() {
		return d;
	}

	public void setD(java.math.BigDecimal d) {
		this.d = d;
	}
	@Column(name ="e",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getE() {
		return e;
	}

	public void setE(java.math.BigDecimal e) {
		this.e = e;
	}
	@Column(name ="f",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getF() {
		return f;
	}

	public void setF(java.math.BigDecimal f) {
		this.f = f;
	}
	@Column(name ="g",nullable=true,length=50)
	public java.lang.String getG() {
		return g;
	}

	public void setG(java.lang.String g) {
		this.g = g;
	}
	@Column(name ="h",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getH() {
		return h;
	}

	public void setH(java.math.BigDecimal h) {
		this.h = h;
	}
	@Column(name ="i",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getI() {
		return i;
	}

	public void setI(java.math.BigDecimal i) {
		this.i = i;
	}
	@Column(name ="j",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getJ() {
		return j;
	}

	public void setJ(java.math.BigDecimal j) {
		this.j = j;
	}
	@Column(name ="k",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getK() {
		return k;
	}

	public void setK(java.math.BigDecimal k) {
		this.k = k;
	}
	@Column(name ="l",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getL() {
		return l;
	}
	@Column(name ="rptno",nullable=true,length=20)
	public java.lang.String getRptno() {
		return rptno;
	}

	public void setRptno(java.lang.String rptno) {
		this.rptno = rptno;
	}

	public void setL(java.math.BigDecimal l) {
		this.l = l;
	}
	@Column(name ="m",nullable=true,precision=19,scale=2)
	public java.math.BigDecimal getM() {
		return m;
	}
	public void setM(java.math.BigDecimal m) {
		this.m = m;
	}
}
