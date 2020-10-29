package com.lzsoft.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

/**   
 * @Title: Entity
 * @Description: r_c_reportsenttype
 * @author onlineGenerator
 * @date 2015-06-16 16:42:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "r_c_reportsenttype", schema = "")
@SuppressWarnings("serial")
public class ReportsenttypeEntity  extends IdEntity implements java.io.Serializable {
	/**contorldirpath*/
	private java.lang.String contorldirpath;
	/**downloaddirpath*/
	private java.lang.String downloaddirpath;
	/**errorfiledirpath*/
	private java.lang.String errorfiledirpath;
	/**feedbackdirpath*/
	private java.lang.String feedbackdirpath;
	/**historydirpath*/
	private java.lang.String historydirpath;
	/**logdirpath*/
	private java.lang.String logdirpath;
	/**password*/
	private java.lang.String password;
	/**port*/
	private java.lang.Integer port;
	/**readytosenddirpath*/
	private java.lang.String readytosenddirpath;
	/**senttype*/
	private java.lang.String senttype;
	/**serveraddress*/
	private java.lang.String serveraddress;
	/**state*/
	private java.lang.String state;
	/**type*/
	private java.lang.String type;
	/**uploaddirpath*/
	private java.lang.String uploaddirpath;
	/**username*/
	private java.lang.String username;
	private java.lang.String prevalidate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  contorldirpath
	 */
	@Column(name ="CONTORLDIRPATH",nullable=true,length=255)
	public java.lang.String getContorldirpath(){
		return this.contorldirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  contorldirpath
	 */
	public void setContorldirpath(java.lang.String contorldirpath){
		this.contorldirpath = contorldirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  downloaddirpath
	 */
	@Column(name ="DOWNLOADDIRPATH",nullable=true,length=255)
	public java.lang.String getDownloaddirpath(){
		return this.downloaddirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  downloaddirpath
	 */
	public void setDownloaddirpath(java.lang.String downloaddirpath){
		this.downloaddirpath = downloaddirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  errorfiledirpath
	 */
	@Column(name ="ERRORFILEDIRPATH",nullable=true,length=255)
	public java.lang.String getErrorfiledirpath(){
		return this.errorfiledirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  errorfiledirpath
	 */
	public void setErrorfiledirpath(java.lang.String errorfiledirpath){
		this.errorfiledirpath = errorfiledirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  feedbackdirpath
	 */
	@Column(name ="FEEDBACKDIRPATH",nullable=true,length=255)
	public java.lang.String getFeedbackdirpath(){
		return this.feedbackdirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  feedbackdirpath
	 */
	public void setFeedbackdirpath(java.lang.String feedbackdirpath){
		this.feedbackdirpath = feedbackdirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  historydirpath
	 */
	@Column(name ="HISTORYDIRPATH",nullable=true,length=255)
	public java.lang.String getHistorydirpath(){
		return this.historydirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  historydirpath
	 */
	public void setHistorydirpath(java.lang.String historydirpath){
		this.historydirpath = historydirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  logdirpath
	 */
	@Column(name ="LOGDIRPATH",nullable=true,length=255)
	public java.lang.String getLogdirpath(){
		return this.logdirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  logdirpath
	 */
	public void setLogdirpath(java.lang.String logdirpath){
		this.logdirpath = logdirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  password
	 */
	@Column(name ="PASSWORD",nullable=true,length=255)
	public java.lang.String getPassword(){
		return this.password;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  password
	 */
	public void setPassword(java.lang.String password){
		this.password = password;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  port
	 */
	@Column(name ="PORT",nullable=true,length=10)
	public java.lang.Integer getPort(){
		return this.port;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  port
	 */
	public void setPort(java.lang.Integer port){
		this.port = port;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  readytosenddirpath
	 */
	@Column(name ="READYTOSENDDIRPATH",nullable=true,length=255)
	public java.lang.String getReadytosenddirpath(){
		return this.readytosenddirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  readytosenddirpath
	 */
	public void setReadytosenddirpath(java.lang.String readytosenddirpath){
		this.readytosenddirpath = readytosenddirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  senttype
	 */
	@Column(name ="SENTTYPE",nullable=true,length=255)
	public java.lang.String getSenttype(){
		return this.senttype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  senttype
	 */
	public void setSenttype(java.lang.String senttype){
		this.senttype = senttype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  serveraddress
	 */
	@Column(name ="SERVERADDRESS",nullable=true,length=255)
	public java.lang.String getServeraddress(){
		return this.serveraddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  serveraddress
	 */
	public void setServeraddress(java.lang.String serveraddress){
		this.serveraddress = serveraddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  state
	 */
	@Column(name ="STATE",nullable=true,length=255)
	public java.lang.String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  state
	 */
	public void setState(java.lang.String state){
		this.state = state;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  type
	 */
	@Column(name ="TYPE",nullable=true,length=255)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  type
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  uploaddirpath
	 */
	@Column(name ="UPLOADDIRPATH",nullable=true,length=255)
	public java.lang.String getUploaddirpath(){
		return this.uploaddirpath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  uploaddirpath
	 */
	public void setUploaddirpath(java.lang.String uploaddirpath){
		this.uploaddirpath = uploaddirpath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  username
	 */
	@Column(name ="USERNAME",nullable=true,length=255)
	public java.lang.String getUsername(){
		return this.username;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  username
	 */
	public void setUsername(java.lang.String username){
		this.username = username;
	}
	@Column(name ="PREVALIDATE",nullable=true,length=2)
	public java.lang.String getPrevalidate() {
		return prevalidate;
	}

	public void setPrevalidate(java.lang.String prevalidate) {
		this.prevalidate = prevalidate;
	}
}
