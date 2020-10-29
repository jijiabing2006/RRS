package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity 营业日信息表
 * @Description: 
 * @author onlineGenerator
 * @date 2015-09-25 15:52:33
 * @version V1.0   
 *
 */
@Entity
@SuppressWarnings("serial")
public class WorkingDayEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/**上一营业日*/
	private java.lang.String lastWorkingDay;
	/**今日*/
	private java.lang.String today;
	/**下一营业日*/
	private java.lang.String nextWorkingDay;
	/**下下一营业日*/
	private java.lang.String nextNextWorkingDay;
	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getLastWorkingDay() {
		return lastWorkingDay;
	}
	public void setLastWorkingDay(java.lang.String lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}
	public java.lang.String getToday() {
		return today;
	}
	public void setToday(java.lang.String today) {
		this.today = today;
	}
	public java.lang.String getNextWorkingDay() {
		return nextWorkingDay;
	}
	public void setNextWorkingDay(java.lang.String nextWorkingDay) {
		this.nextWorkingDay = nextWorkingDay;
	}
	public java.lang.String getNextNextWorkingDay() {
		return nextNextWorkingDay;
	}
	public void setNextNextWorkingDay(java.lang.String nextNextWorkingDay) {
		this.nextNextWorkingDay = nextNextWorkingDay;
	}
	
}
