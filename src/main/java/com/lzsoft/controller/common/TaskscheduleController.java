package com.lzsoft.controller.common;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.service.common.TaskscheduleServiceI;



/**   
 * @Title: Controller
 * @Description: 提取任务状态控制表
 * @author onlineGenerator
 * @date 2016-01-04 16:00:41
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/taskscheduleController")
public class TaskscheduleController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TaskscheduleController.class);

	@Autowired
	private TaskscheduleServiceI taskscheduleService;
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
	 * 提取任务状态控制表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "taskschedule")
	public ModelAndView taskschedule(HttpServletRequest request) {
		return new ModelAndView("safe/taskschedule/taskscheduleList");
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
	public void datagrid(TaskscheduleEntity taskschedule,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, taskschedule, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_importdate_begin = request.getParameter("importdate_begin");
		String query_importdate_end = request.getParameter("importdate_end");
		
		if(StringUtil.isNotEmpty(query_importdate_begin)){
			cq.ge("importdate", new SimpleDateFormat("yyyy-MM-dd").parse(query_importdate_begin));
		}
		if(StringUtil.isNotEmpty(query_importdate_end)){
			cq.le("importdate", new SimpleDateFormat("yyyy-MM-dd").parse(query_importdate_end));
		}
		if(StringUtil.isEmpty(query_importdate_begin)&&StringUtil.isEmpty(query_importdate_end)){
			cq.eq("importdate", systemService.getMaxFieldValueByWhere(TaskscheduleEntity.class, "importdate", "", new Object[]{}));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.taskscheduleService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除提取任务状态控制表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TaskscheduleEntity taskschedule, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		taskschedule = systemService.getEntity(TaskscheduleEntity.class, taskschedule.getId());
		message = "提取任务状态控制表删除成功";
		try{
			taskscheduleService.delete(taskschedule);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "提取任务状态控制表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除提取任务状态控制表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "提取任务状态控制表删除成功";
		try{
			for(String id:ids.split(",")){
				TaskscheduleEntity taskschedule = systemService.getEntity(TaskscheduleEntity.class, 
				id
				);
				taskscheduleService.delete(taskschedule);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "提取任务状态控制表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加提取任务状态控制表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TaskscheduleEntity taskschedule, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "提取任务状态控制表添加成功";
		try{
			taskscheduleService.save(taskschedule);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "提取任务状态控制表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新提取任务状态控制表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TaskscheduleEntity taskschedule, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "提取任务状态控制表更新成功";
		TaskscheduleEntity t = taskscheduleService.get(TaskscheduleEntity.class, taskschedule.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(taskschedule, t);
			taskscheduleService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "提取任务状态控制表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 提取任务状态控制表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TaskscheduleEntity taskschedule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(taskschedule.getId())) {
			taskschedule = taskscheduleService.getEntity(TaskscheduleEntity.class, taskschedule.getId());
			req.setAttribute("taskschedulePage", taskschedule);
		}
		return new ModelAndView("safe/taskschedule/taskschedule-add");
	}
	/**
	 * 提取任务状态控制表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TaskscheduleEntity taskschedule, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(taskschedule.getId())) {
			taskschedule = taskscheduleService.getEntity(TaskscheduleEntity.class, taskschedule.getId());
			req.setAttribute("taskschedulePage", taskschedule);
		}
		return new ModelAndView("safe/taskschedule/taskschedule-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("safe/taskschedule/taskscheduleUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TaskscheduleEntity taskschedule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TaskscheduleEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, taskschedule, request.getParameterMap());
		List<TaskscheduleEntity> taskschedules = this.taskscheduleService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"提取任务状态控制表");
		modelMap.put(NormalExcelConstants.CLASS,TaskscheduleEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("提取任务状态控制表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,taskschedules);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TaskscheduleEntity taskschedule,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "提取任务状态控制表");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,TaskscheduleEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TaskscheduleEntity> listTaskscheduleEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TaskscheduleEntity.class,params);
				for (TaskscheduleEntity taskschedule : listTaskscheduleEntitys) {
					taskscheduleService.save(taskschedule);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
}
