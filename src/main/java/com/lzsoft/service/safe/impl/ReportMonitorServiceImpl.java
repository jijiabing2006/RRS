package com.lzsoft.service.safe.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.entity.safe.ReportMonitorEntity;
import com.lzsoft.service.safe.ReportMonitorServiceI;

@Service("reportMonitorService")
@Transactional
public class ReportMonitorServiceImpl extends CommonServiceImpl implements ReportMonitorServiceI {
	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ReportMonitorEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((ReportMonitorEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((ReportMonitorEntity)entity);
 	} 
 	
	@Override
	public <T> void complete(ReportMonitorEntity t) {
 		//执行更新操作配置的sql增强
 		this.complete2(t);
		super.saveOrUpdate(t);
	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ReportMonitorEntity t){
 		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class);
 		cq.eq("reportName", t.getReportName());
 		cq.add();
 		ReportDicEntity o = getUniqueObjectByCriteriaQuery(cq);
 		if (null != o) {
 			t.setReportType(o.getReportType());
 		}
 		
 		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		String dateNowStr = sdf.format(d);  

		try  {
			Date warnDay = t.getWarnTime();
			Date sysTime = sdf.parse(dateNowStr);
		
		GregorianCalendar gc=new GregorianCalendar(); 
		
 		if("month".equals(t.getReportType())) {			
 			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(2, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(2, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("season".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(2, 3);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(2, 3);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 3);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("year".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(1, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(1, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(1, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("day".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(5, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(5, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(5, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("ten".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
				gc.setTime(warnDay);
				gc.add(5, 10);
				t.setNextWarnTime(gc.getTime());
			} else {
				gc.setTime(warnDay);
				gc.add(5, 10);
				t.setCurrentWarnTime(gc.getTime());
				gc.add(5, 10);
 				t.setNextWarnTime(gc.getTime());
			}
			
		} else if("halfy".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(2, 6);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(2, 6);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 6);
 				t.setNextWarnTime(gc.getTime());
 			}
		}else if("week".equals(t.getReportType())) {
			if (sysTime.before(warnDay)) {
 				t.setCurrentWarnTime(warnDay);
 				gc.setTime(warnDay);
 				gc.add(4, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDay);
 				gc.add(4, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(4, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		}  
 		
 		
		} catch (Exception e)  {  
			   System.out.println(e.getMessage());  
		} 
	 	return true;
 	}
 	
 	/**
 	 * 完成后进行提交，将提醒日和上次提醒日更新
 	 * @author  sunxiaobei
 	 * @date 2017年12月4日 下午2:35:21
 	 * @param t
 	 * @return
 	 * @TODO
 	 */
 	public boolean  complete2(ReportMonitorEntity t){
 		
 		try{
	 		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class);
	 		cq.eq("reportName", t.getReportName());
	 		cq.add();
	 		ReportDicEntity o = getUniqueObjectByCriteriaQuery(cq);
	 		if (null != o) {
	 			t.setReportType(o.getReportType());
	 		}
	 		//如果当前日期与上一提醒日相同，相当于提醒完以后当天完成,这种情况不用更新
	 		if(DateUtils.date_sdf.parse(DateUtils.date_sdf.format(new Date())).equals(t.getLastWarnTime())){
	 			return  true;
	 		}
	 		//如果本次操作完成，将当前提醒日更新为上一次，将提醒日按照类型进行更新
			GregorianCalendar gc=new GregorianCalendar(); 
			Date currDate= t.getWarnTime();
				t.setLastWarnTime(currDate);
	 			if("month".equals(t.getReportType())) {
	 				gc.setTime(currDate);
	 				gc.add(2,1);
	 			    t.setWarnTime(gc.getTime());
	 			} else if("season".equals(t.getReportType())) {
	 				gc.setTime(currDate);
	 				gc.add(2,3);
	 				 t.setWarnTime(gc.getTime());
				} else if("year".equals(t.getReportType())) {		
					gc.setTime(currDate);
	 				gc.add(1,1);
	 				 t.setWarnTime(gc.getTime());
				} else if("day".equals(t.getReportType())) {
					gc.setTime(currDate);
					gc.add(5,1);
					 t.setWarnTime(gc.getTime());
				} else if("ten".equals(t.getReportType())) {
					gc.setTime(currDate);
					gc.add(5,10);
					 t.setWarnTime(gc.getTime());
				} else if("halfy".equals(t.getReportType())) {
					gc.setTime(currDate);
	 				gc.add(2,6);
	 				 t.setWarnTime(gc.getTime());
				}else if("week".equals(t.getReportType())){
					gc.setTime(currDate);
					gc.add(4,1);
					t.setWarnTime(gc.getTime());
				}

	 	 }catch(Exception  e){
	 		 e.printStackTrace();
	 		 return  false;
	 	 }
 		
		 return  true;
 	}
 	
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ReportMonitorEntity t){
 		
 		try {
// 		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class);
// 		cq.eq("reportName", t.getReportName());
// 		cq.add();
// 		ReportDicEntity o = getUniqueObjectByCriteriaQuery(cq);
// 		if (null != o) {
// 			t.setReportType(o.getReportType());
// 		}
		
// 		//如果本次操作完成，将当前提醒日更新为上一次，将提醒日按照类型进行更新
//		GregorianCalendar gc=new GregorianCalendar(); 
// 		if("Y".equals(t.getIsDone())) {	
// 			
//			Date currDate= t.getWarnTime();
//			t.setLastWarnTime(currDate);
// 			if("month".equals(t.getReportType())) {
// 				gc.setTime(currDate);
// 				gc.add(2,1);
// 			    t.setWarnTime(gc.getTime());
// 			} else if("season".equals(t.getReportType())) {
// 				gc.setTime(currDate);
// 				gc.add(2,3);
// 				 t.setWarnTime(gc.getTime());
//			} else if("year".equals(t.getReportType())) {		
//				gc.setTime(currDate);
// 				gc.add(1,1);
// 				 t.setWarnTime(gc.getTime());
//			} else if("day".equals(t.getReportType())) {
//				gc.setTime(currDate);
//				gc.add(5,1);
//				 t.setWarnTime(gc.getTime());
//			} else if("ten".equals(t.getReportType())) {
//				gc.setTime(currDate);
//				gc.add(3,1);
//				 t.setWarnTime(gc.getTime());
//			} else if("halfy".equals(t.getReportType())) {
//				gc.setTime(currDate);
// 				gc.add(2,6);
// 				 t.setWarnTime(gc.getTime());
//			}
// 		
// 			/*更新最新的报表完成状态*/
// 			t.setIsDone("N");
// 		}
 		/**
 		else {
 		
 		//如果用户更新告警时间，需同步更新currentWarnTime, nextWarnTime
 		Date warnDayT = t.getWarnTime();
		Date sysTime = sdf.parse(dateNowStr);
 		if("month".equals(t.getReportType())) {			
 			if (sysTime.before(warnDayT)) {
 				t.setCurrentWarnTime(warnDayT);
 				gc.setTime(warnDayT);
 				gc.add(2, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDayT);
 				gc.add(2, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("season".equals(t.getReportType())) {
			if (sysTime.before(warnDayT)) {
 				t.setCurrentWarnTime(warnDayT);
 				gc.setTime(warnDayT);
 				gc.add(2, 3);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDayT);
 				gc.add(2, 3);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 3);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("year".equals(t.getReportType())) {
			if (sysTime.before(warnDayT)) {
 				t.setCurrentWarnTime(warnDayT);
 				gc.setTime(warnDayT);
 				gc.add(1, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDayT);
 				gc.add(1, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(1, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("day".equals(t.getReportType())) {
			if (sysTime.before(warnDayT)) {
 				t.setCurrentWarnTime(warnDayT);
 				gc.setTime(warnDayT);
 				gc.add(5, 1);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDayT);
 				gc.add(5, 1);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(5, 1);
 				t.setNextWarnTime(gc.getTime());
 			}
		} else if("ten".equals(t.getReportType())) {
			if (sysTime.before(warnDayT)) {
				gc.setTime(warnDayT);
				gc.add(3, 1);
				t.setNextWarnTime(gc.getTime());
			} else {
				gc.setTime(warnDayT);
				gc.add(3, 1);
				t.setCurrentWarnTime(gc.getTime());
 				gc.add(3, 1);
 				t.setNextWarnTime(gc.getTime());
			}
			
		} else if("halfy".equals(t.getReportType())) {
			if (sysTime.before(warnDayT)) {
 				t.setCurrentWarnTime(warnDayT);
 				gc.setTime(warnDayT);
 				gc.add(2, 6);
 				t.setNextWarnTime(gc.getTime());
 			} else {
 				gc.setTime(warnDayT);
 				gc.add(2, 6);
 				t.setCurrentWarnTime(gc.getTime());
 				gc.add(2, 6);
 				t.setNextWarnTime(gc.getTime());
 			}
		}
 		}*/
 		} catch (Exception e)  {  
			   System.out.println(e.getMessage());  
		} 
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ReportMonitorEntity t){
	 	return true;
 	}


}

