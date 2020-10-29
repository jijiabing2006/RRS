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

public class FeedbackControlMsg implements IParseSupport {

	private static FeedbackControlMsg feedbackControlMsg;

	private FeedbackControlMsg(String filePath) throws ParseException,
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

	public static FeedbackControlMsg getInstance(String filePath)
			throws ParseException, FileNotFoundException {
		feedbackControlMsg = new FeedbackControlMsg(filePath);
		return feedbackControlMsg;

	}

	private String apptype;// 应用类型

	private String currentfile;// 当前文件类型

	private String inout;// 输入/输出

	private String totalfiles;// 总文件数

	private String filename;// 文件名称

	private List<FeedbackControlFilename> filenameList;

	public void parse(Element root) throws ParseException {
		setApptype(XmlHelper.getElementValueByName(root, "APPTYPE"));
		setCurrentfile(XmlHelper.getElementValueByName(root, "CURRENTFILE"));
		setInout(XmlHelper.getElementValueByName(root, "INOUT"));
		setTotalfiles(XmlHelper.getElementValueByName(root, "TOTALFILES"));
		setFilenameList(root);
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getCurrentfile() {
		return currentfile;
	}

	public void setCurrentfile(String currentfile) {
		this.currentfile = currentfile;
	}

	public String getInout() {
		return inout;
	}

	public void setInout(String inout) {
		this.inout = inout;
	}

	public String getTotalfiles() {
		return totalfiles;
	}

	public void setTotalfiles(String totalfiles) {
		this.totalfiles = totalfiles;
	}

	public List<FeedbackControlFilename> getFilenameList() {
		return filenameList;
	}

	@SuppressWarnings("unchecked")
	public void setFilenameList(Element root) throws ParseException {
		Element files = XmlHelper.getElementByName(root, "FILES");
		this.filenameList = new ArrayList<FeedbackControlFilename>();
		if (null != files) {
			FeedbackControlFilename filename = null;
			List<Element> filenames = XmlHelper.getElementsByName(files,
					"FILENAME");
			for (Element e : filenames) {
				filename = new FeedbackControlFilename();
				filename.parse(e);
				filenameList.add(filename);
			}
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
