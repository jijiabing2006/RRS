package com.lzsoft.service.safe.feedback.reportmanager.impl;

import java.text.ParseException;

import org.dom4j.Element;

import com.lzsoft.service.safe.feedback.reportmanager.IParseSupport;

public class FeedbackDataFormat implements IParseSupport {

	private String format;// 文件格式错误描述

	public void parse(Element e) throws ParseException {
		setFormat(e.getText());
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
