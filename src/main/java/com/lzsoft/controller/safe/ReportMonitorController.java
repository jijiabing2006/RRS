package com.lzsoft.controller.safe;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.entity.safe.ReportMonitorEntity;
import com.lzsoft.service.safe.ReportMonitorServiceI;

/**   
 * @Title: Controller
 * @Description: 报表监控
 * @author zhangdaihao
 * @date 2017-07-21 11:32:45
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/reportMonitorController")
public class ReportMonitorController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReportMonitorController.class);

	@Autowired
	private ReportMonitorServiceI reportMonitorService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	/**
	 * 报表监控列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportMonitor")
	public ModelAndView reportMonitor(ModelMap modelMap,HttpServletRequest request) {
		return new ModelAndView("safe/rpm/reportMonitorList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ReportMonitorEntity reportMonitor,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		//模糊查询
		if(reportMonitor !=null && reportMonitor.getUserId()!=null && !"".equals(reportMonitor.getUserId())){
			reportMonitor.setUserId("*"+ reportMonitor.getUserId()+"*");
		}
		if(reportMonitor != null && reportMonitor.getReportName()!=null &&  !"".equals(reportMonitor.getReportName())){
			reportMonitor.setReportName("*"+ reportMonitor.getReportName()+"*");
		}
		if(reportMonitor != null && reportMonitor.getUserName()!=null &&  !"".equals(reportMonitor.getUserName())){
			reportMonitor.setUserName("*"+ reportMonitor.getUserName()+"*");
		}
		
		CriteriaQuery cq = new CriteriaQuery(ReportMonitorEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, reportMonitor, request.getParameterMap());
		try{
			//自定义追加查询条件
				TSUser user=ResourceUtil.getSessionUserName();
				String brca=	user.getCurrentDepart().getBrca();
				if(!brca.equals(Constants.HQBANKCODE)){
					cq.eq("brca",brca);
				}else{
					cq.isNotNull("brca");
				}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.reportMonitorService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除报表监控
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ReportMonitorEntity reportMonitor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		reportMonitor = systemService.getEntity(ReportMonitorEntity.class, reportMonitor.getId());
		message = "报表监控删除成功";
		reportMonitorService.delete(reportMonitor);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 添加报表监控
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ReportMonitorEntity reportMonitor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//modify  by  sunxiaobei  2017-11-13  添加机构字段
		String  brca=ResourceUtil.getSessionUserName().getCurrentDepart().getBrca();
		reportMonitor.setBrca(brca);
		if (StringUtil.isNotEmpty(reportMonitor.getId())) {
			/*只能修改本人记录*/
			//if(!ResourceUtil.getSessionUserName().getUserName().equals(reportMonitor.getUserId())) {
			//	message = "不能修改非本人的记录";
			//} else {
			message = "报表监控更新成功";
			ReportMonitorEntity t = reportMonitorService.get(ReportMonitorEntity.class, reportMonitor.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reportMonitor, t);
				reportMonitorService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "报表监控更新失败";
			}
			//}
		} else {
			message = "报表监控添加成功";
			reportMonitorService.save(reportMonitor);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 完成报表监控
	 * @return
	 */
	@RequestMapping(params = "complete")
	@ResponseBody
	public AjaxJson complete(ReportMonitorEntity reportMonitor, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		reportMonitor = systemService.getEntity(ReportMonitorEntity.class, reportMonitor.getId());
		message = "操作完成";
		reportMonitorService.complete(reportMonitor);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}

	/**
	 * 报表监控列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ReportMonitorEntity reportMonitor, HttpServletRequest req) {
		System.out.println("req---[" + req.toString() + "]");
		System.out.println("req---[" + req.getMethod() + "]");
		System.out.println("req---[" + req.getSession().getId() + "]");
		System.out.println("req---[" + req.getSession().getAttributeNames() + "]");
		System.out.println("req---[" + ResourceUtil.getSessionUserName().getRealName() + "]");
		System.out.println("req---[" + ResourceUtil.getSessionUserName().getUserName() + "]");
		if (StringUtil.isNotEmpty(reportMonitor.getId())) {
			reportMonitor = reportMonitorService.getEntity(ReportMonitorEntity.class, reportMonitor.getId());
			req.setAttribute("reportMonitorPage", reportMonitor);
		}
		return new ModelAndView("safe/rpm/reportMonitor");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ReportMonitorEntity reportMonitor,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ReportMonitorEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, reportMonitor, request.getParameterMap());
		try{
			//自定义追加查询条件
				TSUser user=ResourceUtil.getSessionUserName();
				String brca=	user.getCurrentDepart().getBrca();
				if(!brca.equals(Constants.HQBANKCODE)){
					cq.eq("brca",brca);
				}else{
					cq.isNotNull("brca");
				}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		List<ReportDicEntity> reportMonitors = this.reportMonitorService.getListByCriteriaQuery(cq,false);
		System.out.println("export records [" + reportMonitors.size() + "]条");
		modelMap.put(NormalExcelConstants.FILE_NAME,"t_report_dic");
		modelMap.put(NormalExcelConstants.CLASS,ReportMonitorEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("reportMonitor列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,reportMonitors);
		System.out.println("modelMap.size = [" + modelMap.size() + "]");
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
