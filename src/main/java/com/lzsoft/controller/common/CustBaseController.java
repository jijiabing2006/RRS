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

import com.lzsoft.entity.common.CustBaseEntity;
import com.lzsoft.service.common.CustBaseServiceI;



/**   
 * @Title: Controller
 * @Description: 客户基础信息
 * @author onlineGenerator
 * @date 2015-08-26 15:22:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/custBaseController")
public class CustBaseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CustBaseController.class);

	@Autowired
	private CustBaseServiceI custBaseService;
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
	 * 客户基础信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "custBase")
	public ModelAndView custBase(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/com.lzsoft/custBaseList");
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
	public void datagrid(CustBaseEntity custBase,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CustBaseEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, custBase, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.custBaseService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除客户基础信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CustBaseEntity custBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		custBase = systemService.getEntity(CustBaseEntity.class, custBase.getId());
		message = "客户基础信息删除成功";
		try{
			custBaseService.delete(custBase);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户基础信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客户基础信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "客户基础信息删除成功";
		try{
			for(String id:ids.split(",")){
				CustBaseEntity custBase = systemService.getEntity(CustBaseEntity.class, 
				id
				);
				custBaseService.delete(custBase);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户基础信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户基础信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CustBaseEntity custBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "客户基础信息添加成功";
		try{
			custBaseService.save(custBase);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户基础信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客户基础信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CustBaseEntity custBase, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "客户基础信息更新成功";
		CustBaseEntity t = custBaseService.get(CustBaseEntity.class, custBase.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(custBase, t);
			custBaseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客户基础信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客户基础信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CustBaseEntity custBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(custBase.getId())) {
			custBase = custBaseService.getEntity(CustBaseEntity.class, custBase.getId());
			req.setAttribute("custBasePage", custBase);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/custBase-add");
	}
	/**
	 * 客户基础信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CustBaseEntity custBase, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(custBase.getId())) {
			custBase = custBaseService.getEntity(CustBaseEntity.class, custBase.getId());
			req.setAttribute("custBasePage", custBase);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/custBase-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/lzsoft/com.lzsoft/custBaseUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CustBaseEntity custBase,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CustBaseEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, custBase, request.getParameterMap());
		List<CustBaseEntity> custBases = this.custBaseService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客户基础信息");
		modelMap.put(NormalExcelConstants.CLASS,CustBaseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户基础信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,custBases);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CustBaseEntity custBase,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "客户基础信息");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,CustBaseEntity.class);
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
				List<CustBaseEntity> listCustBaseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CustBaseEntity.class,params);
				for (CustBaseEntity custBase : listCustBaseEntitys) {
					custBaseService.save(custBase);
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
