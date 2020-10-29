package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: bop_e
 * @author onlineGenerator
 * @date 2015-06-08 15:06:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bop_e", schema = "")
@Inheritance(strategy = InheritanceType.JOINED)
public class BopEEntity extends BopBaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**收款人帐号*/
	@Excel(name="收款人帐号")
	private java.lang.String oppacc;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款人帐号
	 */
	@Column(name ="OPPACC",nullable=false,length=32)
	public java.lang.String getOppacc(){
		return this.oppacc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款人帐号
	 */
	public void setOppacc(java.lang.String oppacc){
		this.oppacc = oppacc;
	}
}
