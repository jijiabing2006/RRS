package com.lzsoft.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jeecgframework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: 计划任务表
 * @author lawrence	
 * @date 2015-06-01 11:07:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_taskschedule", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TaskscheduleEntity extends IdEntity implements java.io.Serializable {
	/**分行代码*/
	private java.lang.String brca;
	/**上级分行代码*/
	private java.lang.String parentbrca;
	/**任务描述*/
	private java.lang.String taskdesc;
	/**任务名称**/
	private java.lang.String taskname;
	/**提取数量**/
	private int counts;
	
	private boolean executable;
	
	private Date importdate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Column(name ="brca",nullable=false,length=255)
	public java.lang.String getBrca() {
		return brca;
	}

	public void setBrca(java.lang.String brca) {
		this.brca = brca;
	}
	@Column(name ="parentbrca",nullable=false,length=255)
	public java.lang.String getParentbrca() {
		return parentbrca;
	}

	public void setParentbrca(java.lang.String parentbrca) {
		this.parentbrca = parentbrca;
	}
	@Column(name ="taskdesc",nullable=false,length=255)
	public java.lang.String getTaskdesc() {
		return taskdesc;
	}

	public void setTaskdesc(java.lang.String taskdesc) {
		this.taskdesc = taskdesc;
	}
	@Column(name ="taskname",nullable=false,length=255)
	public java.lang.String getTaskname() {
		return taskname;
	}

	public void setTaskname(java.lang.String taskname) {
		this.taskname = taskname;
	}
	@Column(name ="counts",nullable=false,length=255)
	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}
	@Column(name ="executable",nullable=false)
	public boolean isExecutable() {
		return executable;
	}

	public void setExecutable(boolean executable) {
		this.executable = executable;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种代码
	 */
	@Column(name ="importdate",nullable=false)
	public Date getImportdate() {
		return importdate;
	}

	public void setImportdate(Date importdate) {
		this.importdate = importdate;
	}

	


	
}
