package com.lzsoft.autoimport.service.base.etl.parse;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import com.lzsoft.autoimport.service.base.IParseSupport;
import com.lzsoft.util.XmlHelper;

public class Record implements IParseSupport {

	private String name;
	private String type;
	private String fieldDelimiter;
	private List<Field> fieldList;

	public void parse(Element e) throws ParseException {
		try {
			setName(e.attributeValue("name"));
			setType(e.attributeValue("type"));
			setFieldDelimiter(e.attributeValue("fieldDelimiter"));
			setFieldList(e);
		} catch (Exception ex) {
			throw new ParseException(ex.getMessage(), ex.hashCode());
		}
	}

	public Record(String eltConfigFilePath) throws Exception {
		try {
			InputStream in = null;
			Document document = null;
			Element root = null;
			in = this.getClass().getResourceAsStream(eltConfigFilePath);
			document = XmlHelper.getDocument(in);
			root = document.getRootElement();
			this.parse(root);
		} catch (Exception e) {
			throw e;
		}
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

	public String getFieldDelimiter() {
		return fieldDelimiter;
	}

	public void setFieldDelimiter(String fieldDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	@SuppressWarnings("unchecked")
	public void setFieldList(Element root) throws Exception {
		try {
			List<Element> eleList = XmlHelper.getElementsByName(root, "Field");
			if (null == eleList || eleList.isEmpty())
				throw new Exception("���������ļ��Ƿ���ȷ.");
			this.fieldList = new ArrayList<Field>();
			Field field = null;
			for (Element element : eleList) {
				field = new Field();
				field.parse(element);
				this.fieldList.add(field);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
