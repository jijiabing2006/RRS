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

import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.service.common.FeedbackerrorinfoServiceI;



/**   
 * @Title: Controller
 * @Description: r_c_feedbackerrorinfo
 * @author onlineGenerator
 * @date 2015-06-24 11:43:37
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/feedbackerrorinfoController")
public class FeedbackerrorinfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FeedbackerrorinfoController.class);

	@Autowired
	private FeedbackerrorinfoServiceI feedbackerrorinfoService;
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
	 * r_c_feedbackerrorinfo列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "feedbackerrorinfo")
	public ModelAndView feedbackerrorinfo(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/com.lzsoft/feedbackerrorinfoList");
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
	public void datagrid(FeedbackerrorinfoEntity feedbackerrorinfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FeedbackerrorinfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, feedbackerrorinfo, request.getParameterMap());
		try{
		//自定义追加查询条件
			
			if(!cq.isMenualsearch()){//说明是自动加载
				//自动加载没有进入SAFE的数据
				cq.notEq("processed", "1");
				cq.isNull("processed");
				cq.or(cq, 0, 1);
			}
			
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.feedbackerrorinfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除r_c_feedbackerrorinfo
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FeedbackerrorinfoEntity feedbackerrorinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		feedbackerrorinfo = systemService.getEntity(FeedbackerrorinfoEntity.class, feedbackerrorinfo.getId());
		message = "r_c_feedbackerrorinfo删除成功";
		try{
			feedbackerrorinfoService.delete(feedbackerrorinfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_feedbackerrorinfo删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除r_c_feedbackerrorinfo
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "r_c_feedbackerrorinfo删除成功";
		try{
			for(String id:ids.split(",")){
				FeedbackerrorinfoEntity feedbackerrorinfo = systemService.getEntity(FeedbackerrorinfoEntity.class, 
				id
				);
				feedbackerrorinfoService.delete(feedbackerrorinfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_feedbackerrorinfo删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加r_c_feedbackerrorinfo
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FeedbackerrorinfoEntity feedbackerrorinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_feedbackerrorinfo添加成功";
		try{
			feedbackerrorinfoService.save(feedbackerrorinfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_feedbackerrorinfo添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新r_c_feedbackerrorinfo
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FeedbackerrorinfoEntity feedbackerrorinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_feedbackerrorinfo更新成功";
		FeedbackerrorinfoEntity t = feedbackerrorinfoService.get(FeedbackerrorinfoEntity.class, feedbackerrorinfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(feedbackerrorinfo, t);
			feedbackerrorinfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_feedbackerrorinfo更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * r_c_feedbackerrorinfo新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FeedbackerrorinfoEntity feedbackerrorinfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(feedbackerrorinfo.getId())) {
			feedbackerrorinfo = feedbackerrorinfoService.getEntity(FeedbackerrorinfoEntity.class, feedbackerrorinfo.getId());
			req.setAttribute("feedbackerrorinfoPage", feedbackerrorinfo);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/feedbackerrorinfo-add");
	}
	/**
	 * r_c_feedbackerrorinfo编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FeedbackerrorinfoEntity feedbackerrorinfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(feedbackerrorinfo.getId())) {
			feedbackerrorinfo = feedbackerrorinfoService.getEntity(FeedbackerrorinfoEntity.class, feedbackerrorinfo.getId());
			req.setAttribute("feedbackerrorinfoPage", feedbackerrorinfo);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/feedbackerrorinfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/lzsoft/com.lzsoft/feedbackerrorinfoUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FeedbackerrorinfoEntity feedbackerrorinfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FeedbackerrorinfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, feedbackerrorinfo, request.getParameterMap());
		List<FeedbackerrorinfoEntity> feedbackerrorinfos = this.feedbackerrorinfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"r_c_feedbackerrorinfo");
		modelMap.put(NormalExcelConstants.CLASS,FeedbackerrorinfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("r_c_feedbackerrorinfo列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,feedbackerrorinfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FeedbackerrorinfoEntity feedbackerrorinfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "r_c_feedbackerrorinfo");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,FeedbackerrorinfoEntity.class);
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
				List<FeedbackerrorinfoEntity> listFeedbackerrorinfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FeedbackerrorinfoEntity.class,params);
				for (FeedbackerrorinfoEntity feedbackerrorinfo : listFeedbackerrorinfoEntitys) {
					feedbackerrorinfoService.save(feedbackerrorinfo);
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
