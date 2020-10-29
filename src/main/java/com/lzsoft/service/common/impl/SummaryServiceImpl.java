package com.lzsoft.service.common.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.autoimport.service.impl.AutoImportDataMainService;
import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.AccCustEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.SafeBaseEntity;
import com.lzsoft.entity.summary.OutlineImport;
import com.lzsoft.entity.summary.OutlineSAFE;
import com.lzsoft.service.common.SummaryServiceI;
import com.lzsoft.service.safe.impl.SafeBaseService;

@Service("summaryService")
@Transactional
public class SummaryServiceImpl extends SafeBaseService implements
		SummaryServiceI {
	@Autowired
	private AutoImportDataMainService importData;
	
	private Date getMaxImportdate(Date maxImportdate) {
		if (null == maxImportdate) {
			Object imprtdate = getMaxFieldValueByWhere(AccCustEntity.class,
					"importdate", "", new Object[] {});
			maxImportdate = (Date) imprtdate;
		}
		return maxImportdate;
	}


	@Override
	public Map<String, OutlineSAFE> getSummary(String accType, String[] allTypeList,Date maxImportdate) {

		maxImportdate = getMaxImportdate(maxImportdate);
		Map<String,OutlineSAFE> outlinem=new HashMap<String, OutlineSAFE>();
		String brca = ResourceUtil.getSessionUserName().getCurrentDepart()
				.getBrca();
		String sql = "";
		Object[] parma = null;
		if (!Constants.HQBANKCODE.equals(brca)) {
			sql = "importdate=? and brca=? ";
			parma = new Object[] { maxImportdate, brca };
		} else {
			sql = "importdate=?";
			parma = new Object[] { maxImportdate };
		}
		List dataList = null;
	init();
		for (String type : allTypeList) {
		dataList = findHql(beanMap.get(accType + type), sql, parma);
		outlinem.put(accType + type, 	getOutline(dataList,maxImportdate));
		}
		return outlinem;
	}

	private OutlineSAFE getOutline(List list,Date importdate) {
		OutlineSAFE outlineSafe=new OutlineSAFE();
		int cNum = 0;
		int editNum = 0;
		int vNum = 0;
		int unvNum = 0;
		int waitNum = 0;
		int inNum = 0;
		int failedNum = 0;
		cNum = list.size();
		SafeBaseEntity safe;
		for (Object obj : list) {
			safe = (SafeBaseEntity) obj;
			if ("1".equals(safe.getIsedit())) {
				editNum++;
			}
			if ("1".equals(safe.getIsvalidation())) {
				vNum++;
			}
			if ("0".equals(safe.getIsvalidation())
					&& "1".equals(safe.getIsedit())) {
				unvNum++;
			}
			if ("2".equals(safe.getIsinsafe())) {
				waitNum++;
			}
			if ("1".equals(safe.getIsinsafe())) {
				inNum++;
			}
			if ("3".equals(safe.getIsinsafe())) {
				failedNum++;
			}
			
		}
		outlineSafe.setImportdate(DateUtils.dateToStr(importdate,
				Constants.DATEFORMAT));
		outlineSafe.setCounts(String.valueOf(cNum));
		outlineSafe.setEditnum(editNum > 0 ? String.valueOf(editNum)
				: "");
		outlineSafe.setUneditnum(cNum - editNum > 0 ? String
				.valueOf(cNum - editNum) : "");
		outlineSafe.setVnum(vNum > 0 ? String.valueOf(vNum) : "");
		outlineSafe.setUnvnum(unvNum > 0 ? String.valueOf(unvNum) : "");
		outlineSafe.setWaitnum(waitNum > 0 ? String.valueOf(waitNum)
				: "");
		outlineSafe.setVinnum(inNum > 0 ? String.valueOf(inNum) : "");
		outlineSafe.setFailnum(failedNum > 0 ? String
				.valueOf(failedNum) : "");
		return outlineSafe;
		
	}
	
	@Override
	public Object getImportSummary(Class clazz, Date maxImportdate) throws Exception {

			OutlineImport outlineImport=importData.getOutlineImport();
			maxImportdate=getMaxImportdate(maxImportdate);

			TaskscheduleEntity task = queryTaskSchedule(maxImportdate,
					"autoExtractAcc");
			if (null != task) {
				if (task.isExecutable()) {
					outlineImport.setAccextractstate("营业日期为:<Strong Style='color:red;'>" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "</Strong>的ACC数据还没有提取过，请等待系统自动提取或者选择手工提取后，再继续后续工作。");
				} else {
					outlineImport.setAccextractstate("营业日期为:" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "的ACC数据已经提取过。");
				}
			}
			task = queryTaskSchedule(maxImportdate, "autoExtractBop");
			if (null != task) {
				if (task.isExecutable()) {
					outlineImport.setBopextractstate("营业日期为:<Strong Style='color:red;'>" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "</Strong>的BOP数据还没有提取过，请等待系统自动提取或者选择手工提取后，再继续后续工作。");
				} else {
					outlineImport.setBopextractstate("营业日期为:" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "的BOP数据已经提取过。");
				}
			}
			task = queryTaskSchedule(maxImportdate, "autoExtractJsh");
			if (null != task) {
				if (task.isExecutable()) {
					outlineImport.setJshextractstate("营业日期为:<Strong Style='color:red;'>" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "</Strong>的JSH数据还没有提取过，请等待系统自动提取或者选择手工提取后，再继续后续工作。");
				} else {
					outlineImport.setJshextractstate("营业日期为:" + DateUtils.dateToStr(maxImportdate,
							Constants.DATEFORMAT)
							+ "的JSH数据已经提取过。");
				}
			}
			return outlineImport;


	}

}