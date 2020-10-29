package com.lzsoft.service.safe.impl;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.ReportsenttypeEntity;


public class BaseService  extends CommonServiceImpl {
	
	protected String getPbocBankCode(String brca) {
		CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class);
		cq.eq("brca", brca);
		cq.add();
		BankinfoEntity bk = getUniqueObjectByCriteriaQuery(cq);
		if (null != bk) {
			return bk.getBranchcode();
		}
		return "";
	}
	protected BankinfoEntity getBankInfo(String brca) {
		CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class);
		cq.eq("brca", brca);
		cq.add();
		BankinfoEntity bk = getUniqueObjectByCriteriaQuery(cq);
		if (null != bk) {
			return bk;
		}
		return null;
	}
	
	protected ReportsenttypeEntity getReportsentByType(String type,String state) {
		
		CriteriaQuery cq = new CriteriaQuery(ReportsenttypeEntity.class);
		cq.eq("type", type);
		cq.eq("state", state);
		cq.add();
		ReportsenttypeEntity rstype = getUniqueObjectByCriteriaQuery(cq);
		if (null != rstype) {
			return rstype;
		}
		return null;
	}
	

	
	
}