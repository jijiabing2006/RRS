package com.lzsoft.service.safe.feedback.reportmanager.impl;

import java.text.ParseException;

import org.dom4j.Element;

import com.lzsoft.service.safe.feedback.reportmanager.IParseSupport;


public class FeedbackControlFilename implements IParseSupport {

	private String filename;//

	public void parse(Element e) throws ParseException {
		setFilename(e.getText());
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
