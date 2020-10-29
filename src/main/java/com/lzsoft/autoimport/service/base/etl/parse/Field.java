package com.lzsoft.autoimport.service.base.etl.parse;

import java.text.ParseException;

import org.dom4j.Element;

import com.lzsoft.autoimport.service.base.IParseSupport;

public class Field implements IParseSupport {

	private String name;
	private String type;
	private String format;
	private String start;
	private String end;
	private String removespace;

	public void parse(Element e) throws ParseException {
		this.setName(e.attributeValue("name"));
		this.setType(e.attributeValue("type"));
		this.setFormat(e.attributeValue("format"));
		this.setStart(e.attributeValue("start"));
		this.setEnd(e.attributeValue("end"));
		this.setRemovespace(e.attributeValue("removespace"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getRemovespace() {
		return removespace;
	}

	public void setRemovespace(String removespace) {
		this.removespace = removespace;
		if (null == this.removespace || "".equals(this.removespace))
			this.removespace = "true";
	}
}
