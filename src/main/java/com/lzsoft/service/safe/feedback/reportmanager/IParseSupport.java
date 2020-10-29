package com.lzsoft.service.safe.feedback.reportmanager;

import java.text.ParseException;

import org.dom4j.Element;

public interface IParseSupport {

	/**
	 * 
	 * @param e
	 * @throws ParseException
	 */
	public void parse(Element e) throws ParseException;
}
