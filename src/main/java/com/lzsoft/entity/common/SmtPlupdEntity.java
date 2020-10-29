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
 * @Description: SUMMIT-PLUPD估值信息表
 * @author zhangdaihao
 * @date 2016-07-08 16:13:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_smt_plupd", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SmtPlupdEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**交易编号*/
	private java.lang.String tradeid;
	/**交易类型*/
	private java.lang.String tradetype;
	/**交易对手*/
	private java.lang.String cust;
	/**起息日*/
	private java.util.Date effdate;
	/**到期日*/
	private java.util.Date matdate;
	/**币种*/
	private java.lang.String ccy;
	/**面值*/
	private BigDecimal notional;
	/**面值折人民币*/
	private BigDecimal notionalcny;
	/**面值折美元*/
	private BigDecimal notionalusd;
	/**期末利息*/
	private BigDecimal endaccrual;
	/**期末利息折人民币*/
	private BigDecimal endaccrualcny;
	/**期末利息折美元*/
	private BigDecimal endaccrualusd;
	/**期末价值*/
	private BigDecimal endvalue;
	/**期末价值折人民币*/
	private BigDecimal endvaluecny;
	/**期末价值折美元*/
	private BigDecimal endvalueusd;
	/**期末价格*/
	private BigDecimal endprice;
	/**交易利率*/
	private BigDecimal rate;
	/**买卖类型*/
	private java.lang.String pors;
	/**交易价的描述*/
	private java.lang.String tradeprice;
	/**Eccy*/
	private BigDecimal eccy;
	/**子类型*/
	private java.lang.String subtype;
	/**期间损益*/
	private BigDecimal profitloss;
	/**NP*/
	private BigDecimal endvalueNp;
	/**NP折人民币*/
	private BigDecimal endvalueNpcny;
	/**NP折美元*/
	private BigDecimal endvalueNpusd;
	/**NS*/
	private BigDecimal endvalueNs;
	/**NS折人民币*/
	private BigDecimal endvalueNscny;
	/**NS折美元*/
	private BigDecimal endvalueNsusd;
	/**FP*/
	private BigDecimal endvalueFp;
	/**FP折人民币*/
	private BigDecimal endvalueFpcny;
	/**FP折美元*/
	private BigDecimal endvalueFpusd;
	/**FS*/
	private BigDecimal endvalueFs;
	/**FS折人民币*/
	private BigDecimal endvalueFscny;
	/**FS折美元*/
	private BigDecimal endvalueFsusd;
	/**机构*/
	private java.lang.String company;
	/**营业日期*/
	private java.util.Date importdate;
	/**系统日期*/
	private java.util.Date sysDate;
	/**分支机构代码*/
	private java.lang.String brca;
	/**上级机构代码*/
	private java.lang.String parentbrca;
	
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
	 *@return: java.util.Date  起息日
	 */
	@Column(name ="EFFDATE",nullable=true)
	public java.util.Date getEffdate(){
		return this.effdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  起息日
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
	 *@return: BigDecimal  面值折美元
	 */
	@Column(name ="NOTIONALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getNotionalusd(){
		return this.notionalusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  面值折美元
	 */
	public void setNotionalusd(BigDecimal notionalusd){
		this.notionalusd = notionalusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末利息
	 */
	@Column(name ="ENDACCRUAL",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrual(){
		return this.endaccrual;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末利息
	 */
	public void setEndaccrual(BigDecimal endaccrual){
		this.endaccrual = endaccrual;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末利息折人民币
	 */
	@Column(name ="ENDACCRUALCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrualcny(){
		return this.endaccrualcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末利息折人民币
	 */
	public void setEndaccrualcny(BigDecimal endaccrualcny){
		this.endaccrualcny = endaccrualcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末利息折美元
	 */
	@Column(name ="ENDACCRUALUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndaccrualusd(){
		return this.endaccrualusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末利息折美元
	 */
	public void setEndaccrualusd(BigDecimal endaccrualusd){
		this.endaccrualusd = endaccrualusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末价值
	 */
	@Column(name ="ENDVALUE",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalue(){
		return this.endvalue;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末价值
	 */
	public void setEndvalue(BigDecimal endvalue){
		this.endvalue = endvalue;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末价值折人民币
	 */
	@Column(name ="ENDVALUECNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvaluecny(){
		return this.endvaluecny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末价值折人民币
	 */
	public void setEndvaluecny(BigDecimal endvaluecny){
		this.endvaluecny = endvaluecny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末价值折美元
	 */
	@Column(name ="ENDVALUEUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueusd(){
		return this.endvalueusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末价值折美元
	 */
	public void setEndvalueusd(BigDecimal endvalueusd){
		this.endvalueusd = endvalueusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期末价格
	 */
	@Column(name ="ENDPRICE",nullable=true,precision=20,scale=4)
	public BigDecimal getEndprice(){
		return this.endprice;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期末价格
	 */
	public void setEndprice(BigDecimal endprice){
		this.endprice = endprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  交易利率
	 */
	@Column(name ="RATE",nullable=true,precision=20,scale=4)
	public BigDecimal getRate(){
		return this.rate;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  交易利率
	 */
	public void setRate(BigDecimal rate){
		this.rate = rate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  买卖类型
	 */
	@Column(name ="PORS",nullable=true,length=50)
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
	 *@return: java.lang.String  交易价的描述
	 */
	@Column(name ="TRADEPRICE",nullable=true,length=50)
	public java.lang.String getTradeprice(){
		return this.tradeprice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  交易价的描述
	 */
	public void setTradeprice(java.lang.String tradeprice){
		this.tradeprice = tradeprice;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal Eccy
	 */
	@Column(name ="ECCY",nullable=true,precision=20,scale=4)
	public BigDecimal getEccy(){
		return this.eccy;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  Eccy
	 */
	public void setEccy(BigDecimal eccy){
		this.eccy = eccy;
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
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  期间损益
	 */
	@Column(name ="PROFITLOSS",nullable=true,precision=20,scale=4)
	public BigDecimal getProfitloss(){
		return this.profitloss;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  期间损益
	 */
	public void setProfitloss(BigDecimal profitloss){
		this.profitloss = profitloss;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NP
	 */
	@Column(name ="ENDVALUE_NP",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNp(){
		return this.endvalueNp;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NP
	 */
	public void setEndvalueNp(BigDecimal endvalueNp){
		this.endvalueNp = endvalueNp;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NP折人民币
	 */
	@Column(name ="ENDVALUE_NPCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNpcny(){
		return this.endvalueNpcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NP折人民币
	 */
	public void setEndvalueNpcny(BigDecimal endvalueNpcny){
		this.endvalueNpcny = endvalueNpcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NP折美元
	 */
	@Column(name ="ENDVALUE_NPUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNpusd(){
		return this.endvalueNpusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NP折美元
	 */
	public void setEndvalueNpusd(BigDecimal endvalueNpusd){
		this.endvalueNpusd = endvalueNpusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NS
	 */
	@Column(name ="ENDVALUE_NS",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNs(){
		return this.endvalueNs;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NS
	 */
	public void setEndvalueNs(BigDecimal endvalueNs){
		this.endvalueNs = endvalueNs;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NS折人民币
	 */
	@Column(name ="ENDVALUE_NSCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNscny(){
		return this.endvalueNscny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NS折人民币
	 */
	public void setEndvalueNscny(BigDecimal endvalueNscny){
		this.endvalueNscny = endvalueNscny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  NS折美元
	 */
	@Column(name ="ENDVALUE_NSUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueNsusd(){
		return this.endvalueNsusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  NS折美元
	 */
	public void setEndvalueNsusd(BigDecimal endvalueNsusd){
		this.endvalueNsusd = endvalueNsusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FP
	 */
	@Column(name ="ENDVALUE_FP",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFp(){
		return this.endvalueFp;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FP
	 */
	public void setEndvalueFp(BigDecimal endvalueFp){
		this.endvalueFp = endvalueFp;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FP折人民币
	 */
	@Column(name ="ENDVALUE_FPCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFpcny(){
		return this.endvalueFpcny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FP折人民币
	 */
	public void setEndvalueFpcny(BigDecimal endvalueFpcny){
		this.endvalueFpcny = endvalueFpcny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FP折美元
	 */
	@Column(name ="ENDVALUE_FPUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFpusd(){
		return this.endvalueFpusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FP折美元
	 */
	public void setEndvalueFpusd(BigDecimal endvalueFpusd){
		this.endvalueFpusd = endvalueFpusd;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FS
	 */
	@Column(name ="ENDVALUE_FS",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFs(){
		return this.endvalueFs;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FS
	 */
	public void setEndvalueFs(BigDecimal endvalueFs){
		this.endvalueFs = endvalueFs;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FS折人民币
	 */
	@Column(name ="ENDVALUE_FSCNY",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFscny(){
		return this.endvalueFscny;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FS折人民币
	 */
	public void setEndvalueFscny(BigDecimal endvalueFscny){
		this.endvalueFscny = endvalueFscny;
	}
	/**
	 *方法: 取得BigDecimal
	 *@return: BigDecimal  FS折美元
	 */
	@Column(name ="ENDVALUE_FSUSD",nullable=true,precision=20,scale=4)
	public BigDecimal getEndvalueFsusd(){
		return this.endvalueFsusd;
	}

	/**
	 *方法: 设置BigDecimal
	 *@param: BigDecimal  FS折美元
	 */
	public void setEndvalueFsusd(BigDecimal endvalueFsusd){
		this.endvalueFsusd = endvalueFsusd;
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
}
