package com.lzsoft.service.safe.feedback.reportmanager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.lzsoft.service.safe.feedback.reportmanager.IParseSupport;
import com.lzsoft.util.XmlHelper;

public class FeedbackDataMsg implements IParseSupport {

	private static FeedbackDataMsg feedbackDataMsg;

	private FeedbackDataMsg(String filePath) throws ParseException,
			FileNotFoundException {

		init(filePath);

	}

	private void init(String filePath) throws ParseException,
			FileNotFoundException {

		InputStream in = null;

		Document document = null;

		Element root = null;

		try {

			in = new FileInputStream(new File(filePath));

			document = XmlHelper.getDocument(in);

			root = document.getRootElement();

			this.parse(root);

		} catch (DocumentException e) {

			e.printStackTrace();

		}

	}

	public static FeedbackDataMsg getInstance(String filePath)
			throws ParseException, FileNotFoundException {

		feedbackDataMsg = new FeedbackDataMsg(filePath);

		return feedbackDataMsg;

	}

	private String apptype;// 应用类型

	private String currentfile;// 当前文件类型

	private String inout;// 输入/输出

	private String formaterrs;// 文件格式错误数

	private List<FeedbackDataFormat> formats;

	private String totalrecords;// 总记录数

	private String sucrecords;// 成功的记录数

	private String falrecords;// 失败的记录数

	private List<FeedbackDataRec> errrecords;

	public void parse(Element root) throws ParseException {
		setApptype(XmlHelper.getElementValueByName(root, "APPTYPE"));
		setCurrentfile(XmlHelper.getElementValueByName(root, "CURRENTFILE"));
		setInout(XmlHelper.getElementValueByName(root, "INOUT"));
		setFormaterrs(XmlHelper.getElementValueByName(root, "FORMATERRS"));
		setFormats(root);
		setTotalrecords(XmlHelper.getElementValueByName(root, "TOTALRECORDS"));
		setSucrecords(XmlHelper.getElementValueByName(root, "SUCRECORDS"));
		setFalrecords(XmlHelper.getElementValueByName(root, "FALRECORDS"));
		setErrrecords(root);
	}

	/**
	 * 应用类型
	 * 
	 * @return
	 */
	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	/**
	 * 当前文件类型
	 * 
	 * @return
	 */
	public String getCurrentfile() {
		return currentfile;
	}

	public void setCurrentfile(String currentfile) {
		this.currentfile = currentfile;
	}

	/**
	 * 输入/输出
	 * 
	 * @return
	 */
	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	/**
	 * 文件格式错误数
	 * 
	 * @return
	 */
	public String getFormaterrs() {
		return formaterrs;
	}

	public void setFormaterrs(String formaterrs) {
		this.formaterrs = formaterrs;
	}

	public List<FeedbackDataFormat> getFormats() {
		return formats;
	}

	@SuppressWarnings("unchecked")
	public void setFormats(Element root) throws ParseException {
		Element eleFormats = XmlHelper.getElementByName(root, "FORMATS");
		if (null != eleFormats) {
			formats = new ArrayList<FeedbackDataFormat>();
			FeedbackDataFormat format = null;
			List<Element> formatList = XmlHelper.getElementsByName(eleFormats,
					"FORMAT");
			for (Element e : formatList) {
				format = new FeedbackDataFormat();
				format.parse(e);
				formats.add(format);
			}
		}
	}

	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public String getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(String totalrecords) {
		this.totalrecords = totalrecords;
	}

	/**
	 * 成功的记录数
	 * 
	 * @return
	 */
	public String getSucrecords() {
		return sucrecords;
	}

	public void setSucrecords(String sucrecords) {
		this.sucrecords = sucrecords;
	}

	/**
	 * 失败的记录数
	 * 
	 * @return
	 */
	public String getFalrecords() {
		return falrecords;
	}

	public void setFalrecords(String falrecords) {
		this.falrecords = falrecords;
	}

	public List<FeedbackDataRec> getErrrecords() {
		return errrecords;
	}

	@SuppressWarnings("unchecked")
	public void setErrrecords(Element root) throws ParseException {
		Element eleErrrecords = XmlHelper.getElementByName(root, "ERRRECORDS");
		if (null != eleErrrecords) {
			errrecords = new ArrayList<FeedbackDataRec>();
			FeedbackDataRec rec = null;
			List<Element> errrecordList = XmlHelper.getElementsByName(
					eleErrrecords, "REC");
			for (Element e : errrecordList) {
				rec = new FeedbackDataRec();
				rec.parse(e);
				errrecords.add(rec);
			}
		}
	}

}
