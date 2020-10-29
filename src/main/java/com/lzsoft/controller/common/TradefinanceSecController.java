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

import com.lzsoft.entity.common.TradefinanceSecEntity;
import com.lzsoft.service.common.TradefinanceSecServiceI;



/**   
 * @Title: Controller
 * @Description: 贸易融资二表
 * @author onlineGenerator
 * @date 2015-08-26 15:26:03
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/tradefinanceSecController")
public class TradefinanceSecController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TradefinanceSecController.class);

	@Autowired
	private TradefinanceSecServiceI tradefinanceSecService;
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
	 * 贸易融资二表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tradefinanceSec")
	public ModelAndView tradefinanceSec(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/com.lzsoft/tradefinanceSecList");
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
	public void datagrid(TradefinanceSecEntity tradefinanceSec,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TradefinanceSecEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tradefinanceSec, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tradefinanceSecService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除贸易融资二表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TradefinanceSecEntity tradefinanceSec, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tradefinanceSec = systemService.getEntity(TradefinanceSecEntity.class, tradefinanceSec.getId());
		message = "贸易融资二表删除成功";
		try{
			tradefinanceSecService.delete(tradefinanceSec);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "贸易融资二表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除贸易融资二表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "贸易融资二表删除成功";
		try{
			for(String id:ids.split(",")){
				TradefinanceSecEntity tradefinanceSec = systemService.getEntity(TradefinanceSecEntity.class, 
				id
				);
				tradefinanceSecService.delete(tradefinanceSec);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "贸易融资二表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加贸易融资二表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TradefinanceSecEntity tradefinanceSec, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "贸易融资二表添加成功";
		try{
			tradefinanceSecService.save(tradefinanceSec);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "贸易融资二表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新贸易融资二表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TradefinanceSecEntity tradefinanceSec, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "贸易融资二表更新成功";
		TradefinanceSecEntity t = tradefinanceSecService.get(TradefinanceSecEntity.class, tradefinanceSec.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tradefinanceSec, t);
			tradefinanceSecService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "贸易融资二表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 贸易融资二表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TradefinanceSecEntity tradefinanceSec, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tradefinanceSec.getId())) {
			tradefinanceSec = tradefinanceSecService.getEntity(TradefinanceSecEntity.class, tradefinanceSec.getId());
			req.setAttribute("tradefinanceSecPage", tradefinanceSec);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/tradefinanceSec-add");
	}
	/**
	 * 贸易融资二表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TradefinanceSecEntity tradefinanceSec, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tradefinanceSec.getId())) {
			tradefinanceSec = tradefinanceSecService.getEntity(TradefinanceSecEntity.class, tradefinanceSec.getId());
			req.setAttribute("tradefinanceSecPage", tradefinanceSec);
		}
		return new ModelAndView("com/lzsoft/com.lzsoft/tradefinanceSec-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/lzsoft/com.lzsoft/tradefinanceSecUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TradefinanceSecEntity tradefinanceSec,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TradefinanceSecEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tradefinanceSec, request.getParameterMap());
		List<TradefinanceSecEntity> tradefinanceSecs = this.tradefinanceSecService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"贸易融资二表");
		modelMap.put(NormalExcelConstants.CLASS,TradefinanceSecEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("贸易融资二表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tradefinanceSecs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TradefinanceSecEntity tradefinanceSec,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "贸易融资二表");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,TradefinanceSecEntity.class);
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
				List<TradefinanceSecEntity> listTradefinanceSecEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TradefinanceSecEntity.class,params);
				for (TradefinanceSecEntity tradefinanceSec : listTradefinanceSecEntitys) {
					tradefinanceSecService.save(tradefinanceSec);
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
