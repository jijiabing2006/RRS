package com.lzsoft.entity.common;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: r_c_feedbackformatinfo
 * @author onlineGenerator
 * @date 2015-06-23 09:23:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_feedbackformatinfo", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class FeedbackformatinfoEntity extends IdEntity   implements java.io.Serializable {
	/**format*/
	@Excel(name="format")
	private java.lang.String format;
	private java.lang.String processed;
	
	private FeedbackheadinfoEntity fbh;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  format
	 */
	@Column(name ="FORMAT",nullable=true,length=255)
	public java.lang.String getFormat(){
		return this.format;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  format
	 */
	public void setFormat(java.lang.String format){
		this.format = format;
	}
	@Column(name ="PROCESSED",nullable=true,length=10)
	public java.lang.String getProcessed() {
		return processed;
	}

	public void setProcessed(java.lang.String processed) {
		this.processed = processed;
	}

	@ManyToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "FEEDBACKHEAD_ID")
	public FeedbackheadinfoEntity getFbh() {
		return fbh;
	}

	public void setFbh(FeedbackheadinfoEntity fbh) {
		this.fbh = fbh;
	}
}
