package com.lzsoft.service.safe.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.entity.safe.ReportMonitorEntity;
import com.lzsoft.service.safe.ReportDicServiceI;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;

@Service("reportDicService")
@Transactional
public class ReportDicServiceImpl extends CommonServiceImpl implements ReportDicServiceI {

	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ReportDicEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ReportDicEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ReportDicEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ReportDicEntity t){
 		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class);
 		cq.eq("reportName", t.getReportName());
 		cq.add();
 		List<ReportDicEntity> os = getListByCriteriaQuery(cq,false);
 		if (os.size() > 1 ) {
 			delete(t);
 		}
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ReportDicEntity t){
 		CriteriaQuery cq = new CriteriaQuery(ReportMonitorEntity.class);
 		cq.eq("reportName", t.getReportName());
 		cq.add();
 		List<ReportMonitorEntity> os = getListByCriteriaQuery(cq,false);
 		if (null != os && !os.isEmpty()) {
 			for (ReportMonitorEntity o : os) {
 				o.setReportType(t.getReportType());
 			}
 		}
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ReportDicEntity t){
	 	return true;
 	}
	
}