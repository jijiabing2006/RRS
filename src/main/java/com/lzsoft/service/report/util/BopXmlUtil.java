package com.lzsoft.service.report.util;

import java.io.FileOutputStream;
import java.util.Arrays;

import org.apache.commons.beanutils.BeanUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jeecgframework.core.util.DateUtils;

import com.lzsoft.common.Constants;
import com.lzsoft.util.Rootpath;

public class BopXmlUtil extends SafeXmlUtil {

	public BopXmlUtil(String type) {
		super(type);
	}

	// 根据传入的文件类型和数据动态得到REC节点
	@Override
	public Element getRec(String fileType, Object data) throws Exception {
		Element REC = new Element("REC");
		Element ACTIONTYPE = new Element("ACTIONTYPE");// 操作类型
		ACTIONTYPE.setText(BeanUtils.getProperty(data, "actiontype"));
		REC.addContent(ACTIONTYPE);
		Element ACTIONDESC = new Element("ACTIONDESC");// 修改 删除原因
		ACTIONDESC.setText(BeanUtils.getProperty(data, "actiondesc"));
		REC.addContent(ACTIONDESC);
		Element RPTNO = new Element("RPTNO");// 申报号码
		RPTNO.setText(BeanUtils.getProperty(data, "rptno"));
		REC.addContent(RPTNO);
		if (Arrays.asList(Constants.BOP_BASE_TYPE).contains(fileType)) {// 基础信息
			Element CUSTYPE = new Element("CUSTYPE");// 收款人类型
			CUSTYPE.setText(BeanUtils.getProperty(data, "custype"));
			REC.addContent(CUSTYPE);
			Element IDCODE = new Element("IDCODE");// 个人身份证件号码
			IDCODE.setText(BeanUtils.getProperty(data, "idcode"));
			REC.addContent(IDCODE);
			Element CUSTCOD = new Element("CUSTCOD");// 组织机构代码
			CUSTCOD.setText(BeanUtils.getProperty(data, "custcod"));
			REC.addContent(CUSTCOD);
			Element CUSTNM = new Element("CUSTNM");// 收款人名称
			CUSTNM.setText(BeanUtils.getProperty(data, "custnm"));
			REC.addContent(CUSTNM);
			Element OPPUSER = new Element("OPPUSER");// 付款人名称
			OPPUSER.setText(BeanUtils.getProperty(data, "oppuser"));
			REC.addContent(OPPUSER);
			if ("E".equalsIgnoreCase(fileType)) {
				Element OPPACC = new Element("OPPACC");// 付款人账号
				OPPACC.setText(BeanUtils.getProperty(data, "oppacc"));
				REC.addContent(OPPACC);
			}
			Element TXCCY = new Element("TXCCY");// 收入款币种
			TXCCY.setText(BeanUtils.getProperty(data, "txccy"));
			REC.addContent(TXCCY);
			Element TXAMT = new Element("TXAMT");// 收入款金额
			TXAMT.setText(BeanUtils.getProperty(data, "txamt"));
			REC.addContent(TXAMT);
			Element EXRATE = new Element("EXRATE");// 结汇汇率
			EXRATE.setText(BeanUtils.getProperty(data, "exrate"));
			REC.addContent(EXRATE);
			Element LCYAMT = new Element("LCYAMT");// 结汇金额
			LCYAMT.setText(BeanUtils.getProperty(data, "lcyamt"));
			REC.addContent(LCYAMT);
			Element LCYACC = new Element("LCYACC");// 人民币帐号/银行卡号
			LCYACC.setText(BeanUtils.getProperty(data, "lcyacc"));
			REC.addContent(LCYACC);
			Element FCYAMT = new Element("FCYAMT");// 现汇金额
			FCYAMT.setText(BeanUtils.getProperty(data, "fcyamt"));
			REC.addContent(FCYAMT);
			Element FCYACC = new Element("FCYACC");// 外汇帐号/银行卡号
			FCYACC.setText(BeanUtils.getProperty(data, "fcyacc"));
			REC.addContent(FCYACC);
			Element OTHAMT = new Element("OTHAMT");// 其它金额
			OTHAMT.setText(BeanUtils.getProperty(data, "othamt"));
			REC.addContent(OTHAMT);
			Element OTHACC = new Element("OTHACC");// 其它账号/银行卡号
			OTHACC.setText(BeanUtils.getProperty(data, "othacc"));
			REC.addContent(OTHACC);
			Element METHOD = new Element("METHOD");// 结算方式
			METHOD.setText(BeanUtils.getProperty(data, "method"));
			REC.addContent(METHOD);
			Element BUSCODE = new Element("BUSCODE");// 银行业务编号
			BUSCODE.setText(BeanUtils.getProperty(data, "buscode"));
			REC.addContent(BUSCODE);
			Element ACTUCCY = new Element("ACTUCCY");// 实际付款币种
			if ("C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				ACTUCCY.setText(BeanUtils.getProperty(data, "actuccy"));
				REC.addContent(ACTUCCY);
			}
			Element ACTUAMT = new Element("ACTUAMT");// 实际付款金额
			if ("C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				ACTUAMT.setText(BeanUtils.getProperty(data, "actuamt"));
				REC.addContent(ACTUAMT);
			}
			/** *************************************************** */
			Element INCHARGECCY = new Element("INCHARGECCY");// 国内银行扣费币种
			if ("A".equalsIgnoreCase(fileType)
					|| "D".equalsIgnoreCase(fileType)) {
				INCHARGECCY.setText(BeanUtils.getProperty(data, "inchargeccy"));
				REC.addContent(INCHARGECCY);
			}
			Element INCHARGEAMT = new Element("INCHARGEAMT");// 国内银行扣费金额
			if ("A".equalsIgnoreCase(fileType)
					|| "D".equalsIgnoreCase(fileType)) {
				INCHARGEAMT.setText(BeanUtils.getProperty(data, "inchargeamt"));
				REC.addContent(INCHARGEAMT);
			}
			Element OUTCHARGECCY = new Element("OUTCHARGECCY");// 国外银行扣费币种
			if ("A".equalsIgnoreCase(fileType)
					|| "C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				OUTCHARGECCY.setText(BeanUtils
						.getProperty(data, "outchargeccy"));
				REC.addContent(OUTCHARGECCY);
			}
			Element OUTCHARGEAMT = new Element("OUTCHARGEAMT");// 国外银行扣费金额
			if ("A".equalsIgnoreCase(fileType)
					|| "C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				OUTCHARGEAMT.setText(BeanUtils
						.getProperty(data, "outchargeamt"));
				REC.addContent(OUTCHARGEAMT);
			}
			Element LCBGNO = new Element("LCBGNO");// 信用证编号
			if ("C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				LCBGNO.setText(BeanUtils.getProperty(data, "lcbgno"));
				REC.addContent(LCBGNO);
			}
			Element ISSDATE = new Element("ISSDATE");// 开证日期
			if ("C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				ISSDATE.setText(BeanUtils.getProperty(data, "issdate"));
				REC.addContent(ISSDATE);
			}
			Element TENOR = new Element("TENOR");// 期限
			if ("C".equalsIgnoreCase(fileType)
					|| "F".equalsIgnoreCase(fileType)) {
				TENOR.setText(BeanUtils.getProperty(data, "tenor"));
				REC.addContent(TENOR);
			}
		}
		if (Arrays.asList(Constants.BOP_DECLARE_TYPE).contains(fileType)) {
			Element COUNTRY = new Element("COUNTRY");// 收付款人常驻国家代码
			COUNTRY.setText(BeanUtils.getProperty(data, "country"));
			REC.addContent(COUNTRY);
			Element PAYTYPE = new Element("PAYTYPE");// 收款性质、付款类型
			PAYTYPE.setText(BeanUtils.getProperty(data, "paytype"));
			REC.addContent(PAYTYPE);
			Element TXCODE = new Element("TXCODE");// 交易编码1
			TXCODE.setText(BeanUtils.getProperty(data, "txcode"));
			REC.addContent(TXCODE);
			Element TC1AMT = new Element("TC1AMT");// 相应金额1
			TC1AMT.setText(BeanUtils.getProperty(data, "tc1amt"));
			REC.addContent(TC1AMT);
			Element TXREM = new Element("TXREM");// 交易附言1
			TXREM.setText(BeanUtils.getProperty(data, "txrem"));
			REC.addContent(TXREM);
			Element TXCODE2 = new Element("TXCODE2");// 交易编码2
			TXCODE2.setText(BeanUtils.getProperty(data, "txcode2"));
			REC.addContent(TXCODE2);
			Element TC2AMT = new Element("TC2AMT");// 相应金额2
			TC2AMT.setText(BeanUtils.getProperty(data, "tc2amt"));
			REC.addContent(TC2AMT);
			Element TX2REM = new Element("TX2REM");// 交易附言2
			TX2REM.setText(BeanUtils.getProperty(data, "tx2rem"));
			REC.addContent(TX2REM);
			Element ISREF = new Element("ISREF");// 是否保税货物项下收汇
			ISREF.setText(BeanUtils.getProperty(data, "isref"));
			REC.addContent(ISREF);

			if ("G".equals(fileType)) {
				Element BILLNO = new Element("BILLNO");// 外汇局批件号/备案表号/业务编号
				BILLNO.setText(BeanUtils.getProperty(data, "billno"));
				REC.addContent(BILLNO);
				Element PAYATTR = new Element("PAYATTR");// 收入类型
				PAYATTR.setText(BeanUtils.getProperty(data, "payattr"));
				REC.addContent(PAYATTR);

			} else {
				Element REGNO = new Element("REGNO");// 外汇局批件号/备案表号/业务编号
				REGNO.setText(BeanUtils.getProperty(data, "regno"));
				REC.addContent(REGNO);
			}

		}
		if (Arrays.asList(Constants.BOP_CONTROL_TYPE).contains(fileType)) {

			if ("N".equals(fileType) || "P".equals(fileType)
					|| "Q".equals(fileType) || "S".equals(fileType)) {
				Element IMPDATE = new Element("IMPDATE");// 最迟装运日期
				IMPDATE.setText("");
				REC.addContent(IMPDATE);
				Element CONTRNO = new Element("CONTRNO");// 合同号
				CONTRNO.setText(BeanUtils.getProperty(data, "contrno"));
				REC.addContent(CONTRNO);
				Element INVOINO = new Element("INVOINO");// 发票号
				INVOINO.setText(BeanUtils.getProperty(data, "invoino"));
				REC.addContent(INVOINO);
			}

			if ("R".equals(fileType) || "Q".equals(fileType)
					|| "S".equals(fileType)) {
				Element ISREF = new Element("ISREF");// 是否保税货物项下收汇
				ISREF.setText(BeanUtils.getProperty(data, "isref"));
				REC.addContent(ISREF);
				Element TXCODE = new Element("TXCODE");// 交易编码1
				TXCODE.setText(BeanUtils.getProperty(data, "txcode"));
				REC.addContent(TXCODE);
				Element TC1AMT = new Element("TC1AMT");// 相应金额1
				TC1AMT.setText(BeanUtils.getProperty(data, "tc1amt"));
				REC.addContent(TC1AMT);
				Element TXCODE2 = new Element("TXCODE2");// 交易编码2
				TXCODE2.setText(BeanUtils.getProperty(data, "txcode2"));
				REC.addContent(TXCODE2);
				Element TC2AMT = new Element("TC2AMT");// 相应金额2
				TC2AMT.setText(BeanUtils.getProperty(data, "tc2amt"));
				REC.addContent(TC2AMT);
				Element PAYTYPE = new Element("PAYTYPE");// 收款性质、付款类型
				PAYTYPE.setText(BeanUtils.getProperty(data, "paytype"));
				REC.addContent(PAYTYPE);
			}
			if ("Q".equals(fileType) || "S".equals(fileType)) {
				Element COUNTRY = new Element("COUNTRY");// 收款人常驻国家
				COUNTRY.setText(BeanUtils.getProperty(data, "country"));
				REC.addContent(COUNTRY);
				Element PAYATTR = new Element("PAYATTR");// 付汇性质
				PAYATTR.setText(BeanUtils.getProperty(data, "payattr"));
				REC.addContent(PAYATTR);
			}
			if ("R".equals(fileType)) {
				Element PAYATTR = new Element("PAYATTR");// 境内收入类型
				PAYATTR.setText(BeanUtils.getProperty(data, "payattr"));
				REC.addContent(PAYATTR);
				Element TXREM = new Element("TXREM");// 交易附言1
				TXREM.setText(BeanUtils.getProperty(data, "txrem"));
				REC.addContent(TXREM);
				Element TX2REM = new Element("TX2REM");// 交易附言2
				TX2REM.setText(BeanUtils.getProperty(data, "tx2rem"));
				REC.addContent(TX2REM);

				Element REFNOS = new Element("REFNOS");
				Element REFNO = new Element("REFNO");// 出口收汇核销单号码
				REFNO.setText("");
				REFNOS.addContent(REFNO);
				REC.addContent(REFNOS);

				Element CHKAMT = new Element("CHKAMT");// 收汇总金额中用于出口核销的金额
				CHKAMT.setText("");
				REC.addContent(CHKAMT);
			}

			if ("N".equals(fileType) || "Q".equals(fileType)) {
				Element CUSMNO = new Element("CUSMNO");// 报关单经营单位代码
				CUSMNO.setText("");
				REC.addContent(CUSMNO);
				Element CUSTOMS = new Element("CUSTOMS");
				Element CUSTOM = new Element("CUSTOM");
				Element CUSTOMN = new Element("CUSTOMN");// 报关单号
				CUSTOMN.setText("");
				CUSTOM.addContent(CUSTOMN);
				Element CUSTCCY = new Element("CUSTCCY");// 报关单币种
				CUSTCCY.setText("");
				CUSTOM.addContent(CUSTCCY);
				Element CUSTAMT = new Element("CUSTAMT");// 报关金额
				CUSTAMT.setText("");
				CUSTOM.addContent(CUSTAMT);
				Element OFFAMT = new Element("OFFAMT");// 本次核注金额
				OFFAMT.setText("");
				CUSTOM.addContent(OFFAMT);
				CUSTOMS.addContent(CUSTOM);
				REC.addContent(CUSTOMS);

			}
			if ("P".equals(fileType) || "S".equals(fileType)) {
				Element BILLNO = new Element("BILLNO");// 提运单号
				BILLNO.setText("");
				REC.addContent(BILLNO);
				Element CONTAMT = new Element("CONTAMT");// 合同金额
				CONTAMT.setText("");
				REC.addContent(CONTAMT);
			}
			Element REGNO = new Element("REGNO");// 外汇局批件/备案表号
			REGNO.setText("");
			REC.addContent(REGNO);

		}
		if (Arrays.asList(Constants.BOP_DECLARE_TYPE).contains(fileType)
				|| Arrays.asList(Constants.BOP_CONTROL_TYPE).contains(fileType)) {
			Element CRTUSER = new Element("CRTUSER");// 填报人 申请人 联系人
			CRTUSER.setText(BeanUtils.getProperty(data, "crtuser"));
			REC.addContent(CRTUSER);
			Element INPTELC = new Element("INPTELC");// 填报人 申请人 联系人电话
			INPTELC.setText(BeanUtils.getProperty(data, "inptelc"));
			REC.addContent(INPTELC);
			Element RPTDATE = new Element("RPTDATE");// 填报人 申请人 联系人电话
			RPTDATE.setText(DateUtils.dateToStr(DateUtils.strToDate(
					BeanUtils.getProperty(data, "rptdate"), "yyyy-MM-dd"),
					"yyyyMMdd"));
			REC.addContent(RPTDATE);
		}

		return REC;
	}

	/**
	 * 生成空报文
	 * 
	 * @param pbocCode
	 * @param rptdate
	 * @param sequence
	 * @return
	 * @throws Exception
	 */
	@Override
	public String generateEmptyReport(String pbocBankCode, String reportDate,
			int parseInt) {

		try {

			String fileName = Rootpath.getAppPath()
					+ xmlpathname
					+ generateSafeReportFileName("T", pbocBankCode, reportDate,
							generateSequence(parseInt));

			Element root = new Element("MSG");
			Document document = new Document(root);
			Element APPTYPE = new Element("APPTYPE");
			APPTYPE.setText("BOP");
			root.addContent(APPTYPE);
			Element CURRENTFILE = new Element("CURRENTFILE");
			CURRENTFILE.setText("BOPT");
			root.addContent(CURRENTFILE);
			Element INOUT = new Element("INOUT");
			INOUT.setText("IN");
			root.addContent(INOUT);
			Element TOTALFILES = new Element("TOTALFILES");
			TOTALFILES.setText("0");
			root.addContent(TOTALFILES);
			Element FILES = new Element("FILES");
			FILES.setText(null);
			root.addContent(FILES);
			// 写xml文件头
			Format format = Format.getCompactFormat();
			format.setEncoding("gb18030");// 设置xml文件的字符为gb18030
			format.setIndent("    "); // 设置xml文件的缩进为4个空格
			XMLOutputter XMLOut = new XMLOutputter(format);// 元素后换行一层元素缩四格
			FileOutputStream fos = new FileOutputStream(fileName);
			XMLOut.output(document, fos);
			fos.close();
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 自动生成下一个序号
	 * 
	 * @param sequence
	 *            上一个序号
	 * @return
	 */
	private String generateSequence(int sequence) {
		if (String.valueOf(sequence).length() == 1) {
			return "0" + sequence;
		} else {
			return String.valueOf(sequence);
		}
	}

}
