package com.lzsoft.service.safe.feedback.reportmanager.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

import com.lzsoft.service.safe.feedback.reportmanager.IParseSupport;
import com.lzsoft.util.XmlHelper;

public class FeedbackDataRec implements IParseSupport {

	private String rptno;// 申报号/核销收汇专用号码/组织机构代码
//	private String bussno;// 申报号/核销收汇专用号码/组织机构代码

	private List<FeedbackDataErr> errfields;

	public void parse(Element e) throws ParseException {
		String rptno =XmlHelper.getElementValueByName(e, "RPTNO");
		if("".equals(rptno)){
			rptno=XmlHelper.getElementValueByName(e, "BUSSNO");
			rptno=rptno.replaceAll(",", "");
		}
		setRptno(rptno);
//		setBussno(XmlHelper.getElementValueByName(e, "BUSSNO"));
		setErrfields(e);
	}

	public String getRptno() {
		return rptno;
	}

	public void setRptno(String rptno) {
		this.rptno = rptno;
	}

//	public String getBussno() {
//		return bussno;
//	}
//
//	public void setBussno(String bussno) {
//		this.bussno = bussno;
//	}

	public List<FeedbackDataErr> getErrfields() {
		return errfields;
	}

	@SuppressWarnings("unchecked")
	public void setErrfields(Element e) throws ParseException {
		Element errfield = XmlHelper.getElementByName(e, "ERRFIELDS");
		if (null != errfield) {
			errfields = new ArrayList<FeedbackDataErr>();
			FeedbackDataErr err = null;
			List<Element> errList = XmlHelper
					.getElementsByName(errfield, "ERR");
			for (Element ele : errList) {
				err = new FeedbackDataErr();
				err.parse(ele);
				errfields.add(err);
			}
		}
	}

}
