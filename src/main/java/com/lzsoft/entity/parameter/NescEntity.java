package com.lzsoft.entity.parameter;

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
 * @Description: 国民经济代码Mapping
 * @author zhangdaihao
 * @date 2015-11-23 13:36:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "s_p_nesc", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class NescEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**T24Sector*/
	private java.lang.String sector;
	/**Sector英文描述*/
	private java.lang.String sectordesceng;
	/**Sector中文描述*/
	private java.lang.String sectordescchn;
	/**国民经济代码大类ID*/
	private java.lang.String nescidlv1;
	/**国民经济代码大类描述*/
	private java.lang.String nesclv1desc;
	/**国民经济代码小类ID*/
	private java.lang.String nescidlv2;
	/**国民经济代码小类描述*/
	private java.lang.String nesclv2desc;
	/**自贸区国民经济部门ID*/
	private java.lang.String ftnescid;
	/**自贸区国民经济部门描述*/
	private java.lang.String ftnescdesc;
	/**客户属性中资，外资等*/
	private java.lang.String attribute;
	/**客户属性中资，外资等*/
	private java.lang.String attributedesc;
	
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
	 *@return: java.lang.String  T24Sector
	 */
	@Column(name ="SECTOR",nullable=true,length=4)
	public java.lang.String getSector(){
		return this.sector;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  T24Sector
	 */
	public void setSector(java.lang.String sector){
		this.sector = sector;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Sector英文描述
	 */
	@Column(name ="SECTORDESCENG",nullable=true,length=255)
	public java.lang.String getSectordesceng(){
		return this.sectordesceng;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Sector英文描述
	 */
	public void setSectordesceng(java.lang.String sectordesceng){
		this.sectordesceng = sectordesceng;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  Sector中文描述
	 */
	@Column(name ="SECTORDESCCHN",nullable=true,length=255)
	public java.lang.String getSectordescchn(){
		return this.sectordescchn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  Sector中文描述
	 */
	public void setSectordescchn(java.lang.String sectordescchn){
		this.sectordescchn = sectordescchn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国民经济代码大类ID
	 */
	@Column(name ="NESCIDLV1",nullable=true,length=10)
	public java.lang.String getNescidlv1(){
		return this.nescidlv1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国民经济代码大类ID
	 */
	public void setNescidlv1(java.lang.String nescidlv1){
		this.nescidlv1 = nescidlv1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国民经济代码大类描述
	 */
	@Column(name ="NESCLV1DESC",nullable=true,length=255)
	public java.lang.String getNesclv1desc(){
		return this.nesclv1desc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国民经济代码大类描述
	 */
	public void setNesclv1desc(java.lang.String nesclv1desc){
		this.nesclv1desc = nesclv1desc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国民经济代码小类ID
	 */
	@Column(name ="NESCIDLV2",nullable=true,length=10)
	public java.lang.String getNescidlv2(){
		return this.nescidlv2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国民经济代码小类ID
	 */
	public void setNescidlv2(java.lang.String nescidlv2){
		this.nescidlv2 = nescidlv2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国民经济代码小类描述
	 */
	@Column(name ="NESCLV2DESC",nullable=true,length=255)
	public java.lang.String getNesclv2desc(){
		return this.nesclv2desc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国民经济代码小类描述
	 */
	public void setNesclv2desc(java.lang.String nesclv2desc){
		this.nesclv2desc = nesclv2desc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自贸区国民经济部门ID
	 */
	@Column(name ="FTNESCID",nullable=true,length=10)
	public java.lang.String getFtnescid(){
		return this.ftnescid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自贸区国民经济部门ID
	 */
	public void setFtnescid(java.lang.String ftnescid){
		this.ftnescid = ftnescid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自贸区国民经济部门描述
	 */
	@Column(name ="FTNESCDESC",nullable=true,length=255)
	public java.lang.String getFtnescdesc(){
		return this.ftnescdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自贸区国民经济部门描述
	 */
	public void setFtnescdesc(java.lang.String ftnescdesc){
		this.ftnescdesc = ftnescdesc;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户属性
	 */
	@Column(name ="ATTRIBUTE",nullable=true,length=10)
	public java.lang.String getAttribute(){
		return this.attribute;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户属性
	 */
	public void setAttribute(java.lang.String attribute){
		this.attribute = attribute;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户属性描述
	 */
	@Column(name ="ATTRIBUTEDESC",nullable=true,length=255)
	public java.lang.String getAttributedesc(){
		return this.attributedesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户属性描述
	 */
	public void setAttributedesc(java.lang.String attributedesc){
		this.attributedesc = attributedesc;
	}
}
