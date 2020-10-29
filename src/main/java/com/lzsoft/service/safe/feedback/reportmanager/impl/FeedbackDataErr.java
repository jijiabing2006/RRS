package com.lzsoft.service.safe.feedback.reportmanager.impl;

import java.text.ParseException;

import org.dom4j.Element;

import com.lzsoft.service.safe.feedback.reportmanager.IParseSupport;
import com.lzsoft.util.XmlHelper;

public class FeedbackDataErr implements IParseSupport {

	private String errfield;// 出错字段英文标识

	private String errfieldcn;// 出错字段中文标识

	private String errdesc;// 出错原因

	public void parse(Element e) throws ParseException {
		setErrfield(XmlHelper.getElementValueByName(e, "ERRFIELD"));
		setErrfieldcn(XmlHelper.getElementValueByName(e, "ERRFIELDCN"));
		setErrdesc(XmlHelper.getElementValueByName(e, "ERRDESC"));
	}

	/**
	 * 出错字段英文标识
	 * 
	 * @return
	 */
	public String getErrfield() {
		return errfield;
	}

	public void setErrfield(String errfield) {
		this.errfield = errfield;
	}

	/**
	 * 出错字段中文标识
	 * 
	 * @return
	 */
	public String getErrfieldcn() {
		return errfieldcn;
	}

	public void setErrfieldcn(String errfieldcn) {
		this.errfieldcn = errfieldcn;
	}

	/**
	 * 出错原因
	 * 
	 * @return
	 */
	public String getErrdesc() {
		return errdesc;
	}

	public void setErrdesc(String errdesc) {
		this.errdesc = errdesc;
	}

}
