package org.jeecgframework.web.system.pojo.base;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;

import javax.xml.soap.Text;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.SequenceGenerator;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 计划任务表
 * @author onlineGenerator
 * @date 2015-09-25 14:34:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_schedulejob", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TSScheduleJobEntity   implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建时间*/
	@Excel(name="创建时间")
	private java.util.Date createTime;
	/**更新时间*/
	@Excel(name="更新时间")
	private java.util.Date updateTime;
	/**任务名称*/
	@Excel(name="任务名称")
	private java.lang.String jobName;
	/**任务组*/
	@Excel(name="任务组")
	private java.lang.String jobGroup;
	/**开关状态*/
	@Excel(name="开关状态")
	private java.lang.String jobStatus;
	/**cron表达式*/
	@Excel(name="cron表达式")
	private java.lang.String cronExpression;
	/**任务描述*/
	@Excel(name="任务描述")
	private java.lang.String description;
	/**targetclass*/
	@Excel(name="targetclass")
	private java.lang.String beanClass;
	/**是否立即运行*/
	@Excel(name="是否立即运行")
	private java.lang.String isConcurrent;
	/**beanid*/
	@Excel(name="beanid")
	private java.lang.String springId;
	/**方法名*/
	@Excel(name="方法名")
	private java.lang.String methodName;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_TIME",nullable=true)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_TIME",nullable=true)
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务名称
	 */
	@Column(name ="JOB_NAME",nullable=true,length=255)
	public java.lang.String getJobName(){
		return this.jobName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务名称
	 */
	public void setJobName(java.lang.String jobName){
		this.jobName = jobName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务组
	 */
	@Column(name ="JOB_GROUP",nullable=true,length=255)
	public java.lang.String getJobGroup(){
		return this.jobGroup;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务组
	 */
	public void setJobGroup(java.lang.String jobGroup){
		this.jobGroup = jobGroup;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开关状态
	 */
	@Column(name ="JOB_STATUS",nullable=true,length=255)
	public java.lang.String getJobStatus(){
		return this.jobStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开关状态
	 */
	public void setJobStatus(java.lang.String jobStatus){
		this.jobStatus = jobStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  cron表达式
	 */
	@Column(name ="CRON_EXPRESSION",nullable=false,length=255)
	public java.lang.String getCronExpression(){
		return this.cronExpression;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  cron表达式
	 */
	public void setCronExpression(java.lang.String cronExpression){
		this.cronExpression = cronExpression;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务描述
	 */
	@Column(name ="DESCRIPTION",nullable=true,length=255)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务描述
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  targetclass
	 */
	@Column(name ="BEAN_CLASS",nullable=true,length=255)
	public java.lang.String getBeanClass(){
		return this.beanClass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  targetclass
	 */
	public void setBeanClass(java.lang.String beanClass){
		this.beanClass = beanClass;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否立即运行
	 */
	@Column(name ="IS_CONCURRENT",nullable=true,length=255)
	public java.lang.String getIsConcurrent(){
		return this.isConcurrent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否立即运行
	 */
	public void setIsConcurrent(java.lang.String isConcurrent){
		this.isConcurrent = isConcurrent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  beanid
	 */
	@Column(name ="SPRING_ID",nullable=true,length=255)
	public java.lang.String getSpringId(){
		return this.springId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  beanid
	 */
	public void setSpringId(java.lang.String springId){
		this.springId = springId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  方法名
	 */
	@Column(name ="METHOD_NAME",nullable=false,length=255)
	public java.lang.String getMethodName(){
		return this.methodName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  方法名
	 */
	public void setMethodName(java.lang.String methodName){
		this.methodName = methodName;
	}
}
