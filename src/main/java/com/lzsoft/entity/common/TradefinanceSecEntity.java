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
 * @Description: 贸易融资二业务
 * @author zhangdaihao
 * @date 2016-08-23 14:45:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_tradefinance_sec", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TradefinanceSecEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**业务编号*/
	private java.lang.String lcno;
	/**业务种类*/
	private java.lang.String trat;
	/**融资性保函*/
	private java.lang.String gtypea;
	/**非融资性保函*/
	private java.lang.String gtypeb;
	/**客户名称*/
	private java.lang.String ctnm;
	/**客户代码*/
	private java.lang.String csnm;
	/**分类结果*/
	private java.lang.String resc;
	/**交易开始日期*/
	private java.util.Date isdt;
	/**受益人*/
	private java.lang.String befc;
	/**保证金比例*/
	private java.lang.String malc;
	/**币种*/
	private java.lang.String lccr;
	/**金额*/
	private BigDecimal lcam;
	/**金额折人民币（RRS自加）*/
	private BigDecimal lcamcny;
	/**金额折美金（RRS自加）*/
	private BigDecimal lcamusd;
	/**到期日*/
	private java.util.Date lcte;
	/**手续费币种+手续费*/
	private java.lang.String coms;
	/**手续费类型*/
	private java.lang.String ocms;
	/**贷款合同号*/
	private java.lang.String cnlobs;
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
	/**授信金额折人民币*/
	private BigDecimal fcamcny;
	/**授信金额折美金*/
	private BigDecimal fcamusd;
	/**预收费用余额*/
	private BigDecimal unearnFee;
	/**纸质合同编号*/
	private java.lang.String custAgrno;
	/**垫款标志*/
	private java.lang.String bankPayFlg;
	/**分支机构代码*/
	private java.lang.String brca;
	/**上一级机构代码*/
	private java.lang.String parentbrca;
	/**经办行代码*/
	private java.lang.String brcaCo;
	/**系统日期*/
	private java.util.Date sysDate;
	/**营业日期*/
	private java.util.Date importdate;
	/*初始金额*/
	private BigDecimal origLcam;
	/*初始金额折人民币*/
	private BigDecimal origLcamcny;
	/*初始金额折美元*/
	private BigDecimal origLcamusd;
	
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
	 *@return: java.lang.String  业务编号
	 */
	@Column(name ="LCNO",nullable=true,length=100)
	public java.lang.String getLcno(){
		return this.lcno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务编号
	 */
	public void setLcno(java.lang.String lcno){
		this.lcno = lcno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务种类
	 */
	@Column(name ="TRAT",nullable=true,length=100)
	public java.lang.String getTrat(){
		return this.trat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务种类
	 */
	public void setTrat(java.lang.String trat){
		this.trat = trat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  融资性保函
	 */
	@Column(name ="GTYPEA",nullable=true,length=50)
	public java.lang.String getGtypea(){
		return this.gtypea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  融资性保函
	 */
	public void setGtypea(java.lang.String gtypea){
		this.gtypea = gtypea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  非融资性保函
	 */
	@Column(name ="GTYPEB",nullable=true,length=50)
	public java.lang.String getGtypeb(){
		return this.gtypeb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  非融资性保函
	 */
	public void setGtypeb(java.lang.String gtypeb){
		this.gtypeb = gtypeb;
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
	 *@return: java.lang.String  分类结果
	 */
	@Column(name ="RESC",nullable=true,length=50)
	public java.lang.String getResc(){
		return this.resc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类结果
	 */
	public void setResc(java.lang.String resc){
		this.resc = resc;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  交易开始日期
	 */
	@Column(name ="ISDT",nullable=true)
	public java.util.Date getIsdt(){
		return this.isdt;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  交易开始日期
	 */
	public void setIsdt(java.util.Date isdt){
		this.isdt = isdt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  受益人
	 */
	@Column(name ="BEFC",nullable=true,length=100)
	public java.lang.String getBefc(){
		return this.befc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  受益人
	 */
	public void setBefc(java.lang.String befc){
		this.befc = befc;
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
	 *@return: java.lang.String  币种
	 */
	@Column(name ="LCCR",nullable=true,length=10)
	public java.lang.String getLccr(){
		return this.lccr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setLccr(java.lang.String lccr){
		this.lccr = lccr;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  金额
	 */
	@Column(name ="LCAM",nullable=true,precision=20,scale=4)
	public BigDecimal getLcam(){
		return this.lcam;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  金额
	 */
	public void setLcam(BigDecimal lcam){
		this.lcam = lcam;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  金额折人民币（RRS自加）
	 */
	@Column(name ="LCAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getLcamcny(){
		return this.lcamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  金额折人民币（RRS自加）
	 */
	public void setLcamcny(BigDecimal lcamcny){
		this.lcamcny = lcamcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  金额折美金（RRS自加）
	 */
	@Column(name ="LCAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getLcamusd(){
		return this.lcamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  金额折美金（RRS自加）
	 */
	public void setLcamusd(BigDecimal lcamusd){
		this.lcamusd = lcamusd;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到期日
	 */
	@Column(name ="LCTE",nullable=true)
	public java.util.Date getLcte(){
		return this.lcte;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到期日
	 */
	public void setLcte(java.util.Date lcte){
		this.lcte = lcte;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手续费币种+手续费
	 */
	@Column(name ="COMS",nullable=true,length=255)
	public java.lang.String getComs(){
		return this.coms;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手续费币种+手续费
	 */
	public void setComs(java.lang.String coms){
		this.coms = coms;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手续费类型
	 */
	@Column(name ="OCMS",nullable=true,length=200)
	public java.lang.String getOcms(){
		return this.ocms;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手续费类型
	 */
	public void setOcms(java.lang.String ocms){
		this.ocms = ocms;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贷款合同号
	 */
	@Column(name ="CNLOBS",nullable=true,length=50)
	public java.lang.String getCnlobs(){
		return this.cnlobs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贷款合同号
	 */
	public void setCnlobs(java.lang.String cnlobs){
		this.cnlobs = cnlobs;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  担保合同号
	 */
	@Column(name ="GRTC",nullable=true,length=255)
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
	 *@return: BigDecimal  授信金额折人民币
	 */
	@Column(name ="FCAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getFcamcny(){
		return this.fcamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额折人民币
	 */
	public void setFcamcny(BigDecimal fcamcny){
		this.fcamcny = fcamcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  授信金额折美金
	 */
	@Column(name ="FCAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getFcamusd(){
		return this.fcamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  授信金额折美金
	 */
	public void setFcamusd(BigDecimal fcamusd){
		this.fcamusd = fcamusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  预收费用余额
	 */
	@Column(name ="UNEARN_FEE",nullable=true,precision=20,scale=4)
	public BigDecimal getUnearnFee(){
		return this.unearnFee;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  预收费用余额
	 */
	public void setUnearnFee(BigDecimal unearnFee){
		this.unearnFee = unearnFee;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纸质合同编号
	 */
	@Column(name ="CUST_AGRNO",nullable=true,length=50)
	public java.lang.String getCustAgrno(){
		return this.custAgrno;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纸质合同编号
	 */
	public void setCustAgrno(java.lang.String custAgrno){
		this.custAgrno = custAgrno;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  垫款标志
	 */
	@Column(name ="BANK_PAY_FLG",nullable=true,length=50)
	public java.lang.String getBankPayFlg() {
		return bankPayFlg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  垫款标志
	 */
	public void setBankPayFlg(java.lang.String bankPayFlg) {
		this.bankPayFlg = bankPayFlg;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支机构代码
	 */
	@Column(name ="BRCA",nullable=true,length=10)
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
	 *@return: java.lang.String  经办行代码
	 */
	@Column(name ="BRCA_CO",nullable=false,length=10)
	public java.lang.String getBrcaCo(){
		return this.brcaCo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经办行代码
	 */
	public void setBrcaCo(java.lang.String brcaCo){
		this.brcaCo = brcaCo;
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
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  初始金额
	 */
	@Column(name ="ORIG_LCAM",nullable=true,precision=20,scale=4)
	public BigDecimal getOrigLcam(){
		return this.origLcam;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  初始金额
	 */
	public void setOrigLcam(BigDecimal origLcam){
		this.origLcam = origLcam;
	}
	
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  初始金额折人民币
	 */
	@Column(name ="ORIG_LCAMCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getOrigLcamcny(){
		return this.origLcamcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  初始金额折人民币
	 */
	public void setOrigLcamcny(BigDecimal origLcamcny){
		this.origLcamcny = origLcamcny;
	}
	
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  初始金额折美元
	 */
	@Column(name ="ORIG_LCAMUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getOrigLcamusd(){
		return this.origLcamusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  初始金额折美元
	 */
	public void setOrigLcamusd(BigDecimal origLcamusd){
		this.origLcamusd = origLcamusd;
	}
	
	
}
