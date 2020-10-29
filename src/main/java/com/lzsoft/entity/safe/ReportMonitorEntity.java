package com.lzsoft.entity.safe;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 报表监控
 * @author zhangdaihao
 * @date 2017-07-21 11:32:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_report_monitor", schema = "")
@SuppressWarnings("serial")
public class ReportMonitorEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**报表名称*/
	@Excel(name = "报表名称")
	private java.lang.String reportName;
	/**报表类型*/
	@Excel(name = "报表类型")
	private java.lang.String reportType;
	/**负责人*/
	@Excel(name = "负责人")
	private java.lang.String userId;
	/**姓名*/
	@Excel(name = "姓名")
	private java.lang.String userName;
	/**告警时间*/
	@Excel(name = "告警时间")
	private java.util.Date warnTime;
	/**是否完成*/
	@Excel(name = "是否完成")
	private java.lang.String isDone;
	/**上一次告警时间*/
	@Excel(name = "上一次告警时间")
	private java.util.Date lastWarnTime;
	/**本次告警时间*/
	@Excel(name = "本次告警时间")
	private java.util.Date currentWarnTime;
	/**下一次告警时间*/
	@Excel(name = "下一次告警时间")
	private java.util.Date nextWarnTime;
	/**分行号*/
	@Excel(name = "分行号")
	private java.lang.String brca;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
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
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报表名称
	 */
	@Column(name ="REPORT_NAME",nullable=false,length=500)
	public java.lang.String getReportName(){
		return this.reportName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报表名称
	 */
	public void setReportName(java.lang.String reportName){
		this.reportName = reportName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报表类型
	 */
	@Column(name ="REPORT_TYPE",nullable=false,length=32)
	public java.lang.String getReportType(){
		return this.reportType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报表类型
	 */
	public void setReportType(java.lang.String reportType){
		this.reportType = reportType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */
	@Column(name ="USER_ID",nullable=false,length=32)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="USER_NAME",nullable=false,length=32)
	public java.lang.String getUserName(){
		return this.userName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  告警时间
	 */
	@Column(name ="WARN_TIME",nullable=false,length=32)
	public  java.util.Date getWarnTime(){
		return this.warnTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  告警时间
	 */
	public void setWarnTime(java.util.Date warnTime){
		this.warnTime = warnTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否完成
	 */
	@Column(name ="IS_DONE",nullable=true,length=32)
	public java.lang.String getIsDone(){
		return this.isDone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否完成
	 */
	public void setIsDone(java.lang.String isDone){
		this.isDone = isDone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上一次告警时间
	 */
	@Column(name ="LAST_WARN_TIME",nullable=true,length=32)
	public  java.util.Date getLastWarnTime(){
		return this.lastWarnTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上一次告警时间
	 */
	public void setLastWarnTime(java.util.Date lastWarnTime){
		this.lastWarnTime = lastWarnTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本次告警时间
	 */
	@Column(name ="CURRENT_WARN_TIME",nullable=true,length=32)
	public java.util.Date getCurrentWarnTime(){
		return this.currentWarnTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本次告警时间
	 */
	public void setCurrentWarnTime(java.util.Date currentWarnTime){
		this.currentWarnTime = currentWarnTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下次告警时间
	 */
	@Column(name ="NEXT_WARN_TIME",nullable=true,length=32)
	public java.util.Date getNextWarnTime(){
		return this.nextWarnTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下次告警时间
	 */
	public void setNextWarnTime(java.util.Date nextWarnTime){
		this.nextWarnTime = nextWarnTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分行号
	 */
	@Column(name ="BRCA",nullable=true,length=50)
	public java.lang.String getBrca(){
		return this.brca;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分行号
	 */
	public void setBrca(java.lang.String brca){
		this.brca = brca;
	}
}
