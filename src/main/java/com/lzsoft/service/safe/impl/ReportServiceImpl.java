package com.lzsoft.service.safe.impl;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.common.BopConstants;
import com.lzsoft.common.Constants;
import com.lzsoft.common.JshConstants;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.ReportindexEntity;
import com.lzsoft.entity.common.ReportsenttypeEntity;
import com.lzsoft.service.report.util.SafeXmlUtil;
import com.lzsoft.service.safe.ReportServiceI;
import com.lzsoft.util.ZipUtil;

@Service("reportService")
@Transactional
public class ReportServiceImpl extends SafeBaseService implements
		ReportServiceI {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int exportReport(String[] allTypeList, Date strToDate,
			TSDepart currentDepart, String accType) throws Exception {
		clearFolder(accType);
		BankinfoEntity bkinfo = getBankInfo(currentDepart.getBrca());
		String pbocBankCode = bkinfo.getBranchcode();
		init();
		// 查询ACC信息的条件
		Map<String, List> dataMapsByType = new HashMap<String, List>();

		String downloadFileName = "";
		int count = 0;
		String tfilechar=getTfilechar(accType);
		ReportindexEntity reportindex = getNewReportIndex(bkinfo, accType);
		SafeXmlUtil xmlUtil = getXmlUtil(accType);
		String reportDate = DateUtils.getCurrDate("yyMMdd");
		Map<String, String> fileNameMap = new HashMap<String, String>();
		List dataList = null;
		for (String type : allTypeList) {
			dataList = findHql(
					beanMap.get(accType + type),
					" importdate=? and isvalidation='1' and isexport='0' and brca=? ",
					new Object[] { strToDate, currentDepart.getBrca() });
			if (null != dataList && !dataList.isEmpty()) {
				reportindex.setReporttype(type);
				String fileindex = getFileindex(ReportindexEntity.class,
						reportindex);
				String fileName = xmlUtil.writeSafeXml(type, dataList,
						fileindex, pbocBankCode, reportDate);
				if (fileName.indexOf("\\") != -1)
					fileName = fileName
							.substring(fileName.lastIndexOf("\\") + 1);
				if (fileName.indexOf("/") != -1)
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);

				fileNameMap.put(type, fileName);
				fileName = StringUtils.substringBefore(fileName, ".");
				dataMapsByType.put(fileName, dataList);
				// this.savereportFilename(dataList, type, fileName);//
				// 更新数据状态（导出，导出文件名等）,---改为预校验成功后再更新
				reportindex.setFileindex(fileindex);
				count++;
				save(reportindex);
			}
		}
		if (0 == count)
			return 0;// 无数据上报
		// accreportindex.setId(0);
		reportindex.setReporttype(tfilechar);
		String fileindex = getFileindex(ReportindexEntity.class, reportindex);
		reportindex.setFileindex(fileindex);
		downloadFileName = xmlUtil.writeSafeXml(tfilechar, fileNameMap,
				pbocBankCode, reportDate, reportindex.getFileindex(), true);
		save(reportindex);
		String zipPathAndName = getZipXmlPath(accType, "Z")
				+ downloadFileName.substring(
						downloadFileName.lastIndexOf(File.separator) + 1,
						downloadFileName.lastIndexOf(".")) + ".zip";
		ZipUtil.toZipByFolderNoRootFolder(getZipXmlPath(accType, "X"),
				zipPathAndName, "");
	//	int result = preValidation(accType, zipPathAndName);
		int  result=1;//因为BOP无法通过字母开头的组机构代码，SAFE预校验程序无法正常工作 
		
		String tfilename = downloadFileName.substring(
				downloadFileName.lastIndexOf(File.separator) + 1,
				downloadFileName.lastIndexOf("."));
		if (result == 1) {
			// 更新上报数据状态
			savereportFilename(tfilename, dataMapsByType, 1);
		}
		if (result == 2) {
			// 预校验失败，更新数据状态
			savereportFilename(tfilename, dataMapsByType, 2);
		}
		return result;
	}

	/**
	 * 更新上报数据的状态
	 * 
	 * @param dataMapsByType
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("rawtypes")
	private void savereportFilename(String tfilename,
			Map<String, List> dataMapsByType, int type)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Set<String> fileNames = dataMapsByType.keySet();
		for (String fileName : fileNames) {
			this.savereportFilename(dataMapsByType.get(fileName), fileName,
					tfilename, type);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void savereportFilename(List<?> dataList, String filename,
			String tfilename, int type) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List oList = new ArrayList();
		for (int i = 0; i < dataList.size(); i++) {
			Object o = (Object) dataList.get(i);
			PropertyUtils.setProperty(o, "filename", filename);
			PropertyUtils.setProperty(o, "tfilename", tfilename);
			if (type == 1) { // 预校验正确时
				if ("0".equalsIgnoreCase((String) PropertyUtils.getProperty(o,
						"isexport"))) {
					PropertyUtils.setProperty(o, "isexport", "1");
				} else if ("2".equalsIgnoreCase((String) PropertyUtils
						.getProperty(o, "isexport"))) {
					PropertyUtils.setProperty(o, "isexport", "3");
				}
				PropertyUtils.setProperty(o, "isinsafe", "2");
			} else if (type == 2) {// 预校验不正确时
				PropertyUtils.setProperty(o, "isinsafe", "4");// 预校验不通过
			}

			oList.add(o);
		}
		if (oList != null && !oList.isEmpty()) {
			batchSave(oList);
		}
	}

	@Override
	public String exportReportAndSend(String date, TSUser user, String accType) {

		ReportsenttypeEntity reportsenttype = getReportsentByType(accType, "1");// 得到ACC传输方式
		if (null == reportsenttype) {
			return "没有与" + accType + "相关的FTP设置";
		}
		reportsenttype.setUploaddirpath(getZipXmlPath(accType, "X"));
		return ftpUtilManager.renderReportByAccType(reportsenttype, accType);

	}

	@Override
	public void addTashSchdule(Date date, TSUser user, String accType)
			throws Exception {
		addTashSchdule(date, user.getCurrentDepart().getBrca(), accType);
	}

	@Override
	public <T> int validation(T entity) throws Exception {

		String rptno = null != PropertyUtils.getProperty(entity, "rptno") ? (String) PropertyUtils
				.getProperty(entity, "rptno") : "";

		String rpdt = "";
		if (rptno.length() == 22) {
			rpdt = StringUtils.substring(rptno, 12, 18);
		}

		String rptdate = "".equals(rpdt) ? DateUtils.dateToStr(
				(Date) PropertyUtils.getProperty(entity, "importdate"),
				"yyMMdd") : rpdt;
		String brca = (String) PropertyUtils.getProperty(entity, "brca");
		String branchcode = getPbocBankCode(brca);

		if ("".equals(rptno) || rptno.length() != 22) {
			PropertyUtils.setProperty(entity, "rptno", branchcode + rptdate
					+ Constants.BOP_LAST_FOUR);
			rptno = null != PropertyUtils.getProperty(entity, "rptno") ? (String) PropertyUtils
					.getProperty(entity, "rptno") : "";
		}
		if (null != rptno
				&& rptno.length() == 22
				&& StringUtils.equals(Constants.BOP_LAST_FOUR,
						rptno.substring(18, rptno.length()))) {
			String custype = (String) PropertyUtils.getProperty(entity,
					"custype");
			String refnumber = generateRptNo(entity, rptdate, custype,
					branchcode);
			if (null != refnumber && refnumber.length() == 22
					&& !refnumber.contains("*")) {
				if (checkRptnoIsUniqu(entity, refnumber)) {
					return 4;
				}
				PropertyUtils.setProperty(entity, "rptno", refnumber);
				updateIfxtransByBuscode(entity);// 更新交易流水表中申报号码字段内容
				return 1;
			}
		} else if (null != rptno
				&& rptno.length() == 22
				&& !StringUtils.equals(Constants.BOP_LAST_FOUR,
						rptno.substring(18, rptno.length()))) {
			return 2;
		}
		return 0;

	}

	private <T> void updateIfxtransByBuscode(T entity)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// String dlref = StringUtils.substringBefore(
		// (String) PropertyUtils.getProperty(entity, "buscode"), "-");
		// List<TranDetailEntity> trans =
		// dao.queryByWhere(TranDetailEntity.class,
		// "importdate=? and dlref like ?", new Object[] {
		// (Date) PropertyUtils.getProperty(entity, "importdate"),
		// dlref + "%" });
		// if (null != trans) {
		// for (TranDetailEntity ifxtran : trans) {
		// ifxtran.setApnumber((String) PropertyUtils.getProperty(entity,
		// "rptno"));
		// }
		// dao.saveAll(trans);
		// }
	}

	private <T> String generateRptNo(T entity, String rptdate, String custype,
			String branchcode) throws Exception {
		String reportType = entity.getClass().getSimpleName();
		// char rpttype = reportType.charAt(reportType.length() - 1);
		char rpttype = reportType.charAt(3);
		String accType = StringUtils.substring(reportType, 0, 3).toUpperCase();

		Object lastRptNo = getLastRptNo(entity, branchcode, rptdate, custype,
				rpttype);
		if (null != lastRptNo) {
			if ("BOP".equals(accType)) {
				return generateBopRptNoByLastRptNo((String) lastRptNo, rpttype);
			}else 	if ("JSH".equals(accType)) {
				return generateJshRptNoByLastRptNo((String) lastRptNo, rpttype);
			}
		}
		return initRptNo(branchcode, rptdate, rpttype, custype.charAt(0),
				accType);

	}

	private <T> Object getLastRptNo(T entity, String branchcode,
			String rptdate, String custype, char rpttype) {
		// Table t=entity.getClass().getAnnotation(Table.class);
		// String tablename=t.name();

		String hql = "substring(rptno,13,6) =? and substring(rptno,1,12)=? and substring(rptno,19,4)!='****'";
		if (rpttype == 'A' || rpttype == 'B' || rpttype == 'C') {
			if ("C".equalsIgnoreCase(String.valueOf(custype))) {
				hql += "and custype = 'C'";
			} else {
				hql += "and custype != 'C'";
			}
		}
		return getMaxFieldValueByWhere(entity.getClass(), "rptno", hql,
				new Object[] { rptdate, branchcode });

	}

	private String generateBopRptNoByLastRptNo(String lastRptNo, char reportType) {
		if (null == lastRptNo || lastRptNo.trim().length() != 22) {
			return null;
		}
		char refnumberChar;// 申报号码所需要的字符标记
		int refnumberInt;
		String refnumber = lastRptNo.substring(0, 18);// 自动生成的申报号码
		String temp = "";
		if (reportType == 'A' || reportType == 'B' || reportType == 'C') {
			refnumberChar = lastRptNo.charAt(18);
			refnumberInt = Integer.parseInt(lastRptNo.substring(19,
					lastRptNo.length()));
			temp = getRefNumberStr(refnumberChar, refnumberInt, reportType,
					getAddpoint(reportType), "BOP");
			if (null != temp) {
				refnumber += temp;
			} else {
				refnumber = null;
			}
		}
		if (reportType == 'D') {
			refnumberInt = Integer.parseInt(lastRptNo.substring(18,
					lastRptNo.length()));
			if (refnumberInt == 9999) {
				refnumber = null;
			} else {
				refnumber += getFixedLengthStr(refnumberInt + 1, 4);
			}
		}
		if (reportType == 'E' || reportType == 'F') {
			refnumberChar = lastRptNo.charAt(21);
			refnumberInt = Integer.parseInt(lastRptNo.substring(18,
					lastRptNo.length() - 1));

			temp = getRefNumberStr(refnumberChar, refnumberInt, reportType,
					getAddpoint(reportType), "BOP");
			if (null != temp) {
				refnumber += temp;
			} else {
				refnumber = null;
			}
		}
		return refnumber;
	}
	private String generateJshRptNoByLastRptNo(String lastRptNo, char reportType) {
		if (null == lastRptNo
				|| StringUtils.length(StringUtils.trim(lastRptNo)) != 22) {
			return null;
		}
		char refnumberChar;// 申报号码所需要的字符标记
		int refnumberInt;
		String refnumber = lastRptNo.substring(0, 18);// 自动生成的申报号码
		String temp = "";
		refnumberChar = lastRptNo.charAt(21);
		refnumberInt = Integer.parseInt(lastRptNo.substring(18,
				lastRptNo.length() - 1));

		temp = getRefNumberStr(refnumberChar, refnumberInt, reportType,
				"E","JSH");
		if (null != temp) {
			refnumber += temp;
		} else {
			refnumber = null;
		}
		return refnumber;
	}

	/**
	 * 根据报表类型得到增加字符的位置
	 * 
	 * @param reportType
	 *            报表类型
	 * @return 字符出现的位置
	 */
	public String getAddpoint(char reportType) {
		if (reportType == 'A' || reportType == 'B' || reportType == 'C') {
			return "S";
		}
		if (reportType == 'E' || reportType == 'F') {
			return "E";
		}
		return null;
	}

	/**
	 * 得到信息的申报号码
	 * 
	 * @param refnumberChar
	 *            上一次出现的交易流水号的字符
	 * @param refnumberInt
	 *            交易流水号中的非字符部分
	 * @param reportType
	 *            报表类型
	 * @param addpoint
	 *            增加的位置
	 * @return 新的申报号码 @
	 */
	private String getRefNumberStr(char refnumberChar, int refnumberInt,
			char reportType, String addpoint, String accType) {
		if (refnumberInt < 999) {
			if ("S".equalsIgnoreCase(addpoint)) {
				return String.valueOf(refnumberChar)
						+ getFixedLengthStr((refnumberInt + 1),
								BopConstants.RPTNO_PART_LENGTH - 1);
			}
			if ("E".equalsIgnoreCase(addpoint)) {
				return getFixedLengthStr((refnumberInt + 1),
						BopConstants.RPTNO_PART_LENGTH - 1)
						+ String.valueOf(refnumberChar);
			}
			return null;
		} else {
			Character newRefnumberChar = getNextRefnumberChar(refnumberChar,
					reportType, accType);
			if (null == newRefnumberChar) {
				return null;
			}
			if ("S".equalsIgnoreCase(addpoint)) {
				return String.valueOf(newRefnumberChar) + "001";
			}
			if ("E".equalsIgnoreCase(addpoint)) {
				return "001" + String.valueOf(newRefnumberChar);
			}
			return null;
		}
	}

	/**
	 * 补齐不足指定长度的为指定长度的并返回字符串形式
	 * 
	 * @param i
	 *            待补齐的数字
	 * @param length
	 *            要补齐的长度
	 * @return 补足后的字符串
	 */
	private String getFixedLengthStr(int i, int length) {
		String result = String.valueOf(i);
		if (result.length() < length) {
			for (int m = 0; m < length; m++) {
				if (result.length() == length) {
					return result;
				}
				result = "0" + result;
			}
		}
		if (result.length() > length) {
			result = result
					.substring(result.length() - length, result.length());
		}
		return result;
	}

	/**
	 * 得到下一个交易流水号所需的字符
	 * 
	 * @param refnumberChar
	 *            上一个交易流水号的字符
	 * @param reportType
	 *            报告类型
	 * @return 下一个流水号需要的字符
	 */
	private Character getNextRefnumberChar(char refnumberChar, char reportType,
			String accType) {
		int index = -1;
		switch (reportType) {
		case 'A': {
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.A_RPTNO_RANG_FOR_C);
			if (-1 != index)
				return BopConstants.A_RPTNO_RANG_FOR_C[index + 1].charAt(0);
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.A_RPTNO_RANG_FOR_I);
			if (-1 != index)
				return BopConstants.A_RPTNO_RANG_FOR_I[index + 1].charAt(0);
		}
		case 'B':
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.B_RPTNO_RANG_FOR_C);
			if (-1 != index)
				return BopConstants.B_RPTNO_RANG_FOR_C[index + 1].charAt(0);
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.B_RPTNO_RANG_FOR_I);
			if (-1 != index)
				return BopConstants.B_RPTNO_RANG_FOR_I[index + 1].charAt(0);
		case 'C':
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.C_RPTNO_RANG_FOR_C);
			if (-1 != index)
				return BopConstants.C_RPTNO_RANG_FOR_C[index + 1].charAt(0);
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.C_RPTNO_RANG_FOR_I);
			if (-1 != index)
				return BopConstants.C_RPTNO_RANG_FOR_I[index + 1].charAt(0);
		case 'D':
			if ("JSH".equals(accType)) {
				index = getCharIndexInArray(String.valueOf(refnumberChar),
						JshConstants.E_RPTNO_RANG);
				if (-1 != index)
					return JshConstants.E_RPTNO_RANG[index + 1].charAt(0);
			}
		case 'E':
			if ("JSH".equals(accType)) {
				index = getCharIndexInArray(String.valueOf(refnumberChar),
						JshConstants.E_RPTNO_RANG);
				if (-1 != index)
					return JshConstants.E_RPTNO_RANG[index + 1].charAt(0);

			} else {
				index = getCharIndexInArray(String.valueOf(refnumberChar),
						BopConstants.E_RPTNO_RANG);
				if (-1 != index)
					return BopConstants.E_RPTNO_RANG[index + 1].charAt(0);
			}
		case 'F':
			index = getCharIndexInArray(String.valueOf(refnumberChar),
					BopConstants.F_RPTNO_RANG);
			if (-1 != index)
				return BopConstants.F_RPTNO_RANG[index + 1].charAt(0);
		}
		return null;
	}

	/**
	 * 得到字符在一个数组中出现的下标
	 * 
	 * @param charStr
	 *            要查找的字符
	 * @param parentStr
	 *            待查找的字符串
	 * @return 该字符在字符串中出现的下标
	 */
	private int getCharIndexInArray(String charStr, String[] parentStr) {
		for (int i = 1; i < parentStr.length; i++) {
			if (charStr.equals(parentStr[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 初始化某一天的申报号码
	 * 
	 * @param user
	 *            当前用户
	 * @param rpdt
	 *            报表日期
	 * @param reprotType
	 *            报告类型
	 * @param custype
	 *            客户类型
	 * @return
	 */
	private String initRptNo(String branchcode, String rpdt, char reportType,
			char custype, String accType) {
		StringBuffer rptNo = new StringBuffer();
		rptNo.append(branchcode);
		rptNo.append(rpdt);
		switch (reportType) {
		case 'A':
			if ('C' == custype) {
				rptNo.append(getinitRefNumber(BopConstants.A_RPTNO_RANG_FOR_C));
			} else {
				rptNo.append(getinitRefNumber(BopConstants.A_RPTNO_RANG_FOR_I));
			}
			break;
		case 'B':
			if ('C' == custype) {
				rptNo.append(getinitRefNumber(BopConstants.B_RPTNO_RANG_FOR_C));
			} else {
				rptNo.append(getinitRefNumber(BopConstants.B_RPTNO_RANG_FOR_I));
			}
			break;
		case 'C':
			if ('C' == custype) {
				rptNo.append(getinitRefNumber(BopConstants.C_RPTNO_RANG_FOR_C));
			} else {
				rptNo.append(getinitRefNumber(BopConstants.C_RPTNO_RANG_FOR_I));
			}
			break;
		case 'D':
			if ("JSH".equals(accType)) {
				rptNo.append(getinitRefNumber(JshConstants.D_RPTNO_RANG));
			} else {
				rptNo.append(getinitRefNumber(BopConstants.D_RPTNO_RANG));
			}

			break;
		case 'E':
			if ("JSH".equals(accType)) {
				rptNo.append(getinitRefNumber(JshConstants.E_RPTNO_RANG));
			} else {
				rptNo.append(getinitRefNumber(BopConstants.E_RPTNO_RANG));
			}
			break;
		case 'F':
			rptNo.append(getinitRefNumber(BopConstants.F_RPTNO_RANG));
			break;
		}
		return rptNo.length() == 0 ? null : rptNo.toString();
	}

	/**
	 * 初始化4位的交易流水号
	 * 
	 * @param rangArray
	 *            对应的取值范围数组
	 * @return 4位的交易流水号
	 */
	private String getinitRefNumber(String[] rangArray) {
		if (null == rangArray) {
			return "0001";
		}
		if ("S".equalsIgnoreCase(rangArray[0])) {
			return rangArray[1] + "001";
		}
		if ("E".equalsIgnoreCase(rangArray[0])) {
			return "001" + rangArray[1];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> boolean checkRptnoIsUniqu(T entity, String rptno)
			throws Exception {
		List<T> datas = (List<T>) findHql(
				entity.getClass(),
				"importdate=? and rptno=?",
				new Object[] {
						(Date) PropertyUtils.getProperty(entity, "importdate"),
						rptno });
		if (datas.size() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int generateEmptyBOPReport(String[] allTypeList,Date strToDate, TSDepart currentDepart,
			String accType) throws Exception {
		clearFolder(accType);
		BankinfoEntity bkinfo = getBankInfo(currentDepart.getBrca());
		String pbocBankCode = bkinfo.getBranchcode();
		init();
		Map<String, List> dataMapsByType = new HashMap<String, List>();

		String downloadFileName = "";
		int count = 0;
		String tfilechar=getTfilechar(accType);
		ReportindexEntity reportindex = getNewReportIndex(bkinfo, accType);
		SafeXmlUtil xmlUtil = getXmlUtil(accType);
		String reportDate = DateUtils.getCurrDate("yyMMdd");
		Map<String, String> fileNameMap = new HashMap<String, String>();
		List dataList = null;
		for (String type : allTypeList) {
			dataList = findHql(
					beanMap.get(accType + type),
					" importdate=?  and rptno like ? ",
					new Object[] { strToDate, pbocBankCode+"%"});
			if (null != dataList && !dataList.isEmpty()) {
				return 0;
			}
		}

		reportindex.setReporttype(tfilechar);
		String fileindex = getFileindex(ReportindexEntity.class, reportindex);
		reportindex.setFileindex(fileindex);
		downloadFileName = xmlUtil.generateEmptyReport(
				pbocBankCode, reportDate, Integer.parseInt(reportindex.getFileindex()));
		save(reportindex);
		String zipPathAndName = getZipXmlPath(accType, "Z")
				+ downloadFileName.substring(
						downloadFileName.lastIndexOf(File.separator) + 1,
						downloadFileName.lastIndexOf(".")) + ".zip";
		ZipUtil.toZipByFolderNoRootFolder(getZipXmlPath(accType, "X"),
				zipPathAndName, "");
		int result = preValidation(accType, zipPathAndName);
		String tfilename = downloadFileName.substring(
				downloadFileName.lastIndexOf(File.separator) + 1,
				downloadFileName.lastIndexOf("."));
		if (result == 1) {
			// 更新上报数据状态
			savereportFilename(tfilename, dataMapsByType, 1);
		}
		if (result == 2) {
			// 预校验失败，更新数据状态
			savereportFilename(tfilename, dataMapsByType, 2);
		}
		return result;
	}
}