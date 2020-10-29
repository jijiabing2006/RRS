package com.lzsoft.autoimport.service.base;

import java.text.ParseException;

import org.dom4j.Element;

public interface IParseSupport {
	
	/**
	 * ����һ���ڵ�
	 * 
	 * @param e
	 * @throws ParseException
	 */
	public void parse(Element e) throws ParseException;
	
}
