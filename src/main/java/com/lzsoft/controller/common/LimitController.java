package com.lzsoft.controller.common;
import java.io.IOException;
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

import com.lzsoft.entity.common.LimitEntity;
import com.lzsoft.service.common.LimitServiceI;



/**   
 * @Title: Controller
 * @Description: 授信额度表
 * @author onlineGenerator
 * @date 2015-08-26 15:24:27
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/limitController")
public class LimitController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LimitController.class);

	@Autowired
	private LimitServiceI limitService;
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
	 * 授信额度表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "limit")
	public ModelAndView limit(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/com.lzsoft/limitList");
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
	public void datagrid(LimitEntity limit,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LimitEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, limit, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.limitService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除授信额度表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LimitEntity limit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		limit = systemService.getEntity(LimitEntity.class, limit.getId());
		message = "授信额度表删除成功";
		try{
			limitService.delete(limit);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "授信额度表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除授信额度表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "授信额度表删除成功";
		try{
			for(String id:ids.split(",")){
				LimitEntity limit = systemService.getEntity(LimitEntity.class, 
				id
				);
				limitService.delete(limit);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "授信额度表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加授信额度表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LimitEntity limit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "授信额度表添加成功";
		try{
			limitService.save(limit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "授信额度表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新授信额度表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LimitEntity limit, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "授信额度表更新成功";
		LimitEntity t = limitService.get(LimitEntity.class, limit.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(limit, t);
			limitService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "授信额度表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 授信额度表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LimitEntity limit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(limit.getId())) {
			limit = limitService.getEntity(LimitEntity.class, limit.getId());
			req.setAttribute("limitPage", limit);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/limit-add");
	}
	/**
	 * 授信额度表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LimitEntity limit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(limit.getId())) {
			limit = limitService.getEntity(LimitEntity.class, limit.getId());
			req.setAttribute("limitPage", limit);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/limit-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/lzsoft/com.lzsoft/limitUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LimitEntity limit,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LimitEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, limit, request.getParameterMap());
		List<LimitEntity> limits = this.limitService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"授信额度表");
		modelMap.put(NormalExcelConstants.CLASS,LimitEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("授信额度表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,limits);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LimitEntity limit,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "授信额度表");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,LimitEntity.class);
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
				List<LimitEntity> listLimitEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LimitEntity.class,params);
				for (LimitEntity limit : listLimitEntitys) {
					limitService.save(limit);
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
