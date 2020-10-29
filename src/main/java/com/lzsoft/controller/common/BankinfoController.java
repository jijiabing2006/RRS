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

import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.service.common.BankinfoServiceI;



/**   
 * @Title: Controller
 * @Description: r_c_bankinfo
 * @author onlineGenerator
 * @date 2015-06-01 16:22:02
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/bankinfoController")
public class BankinfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BankinfoController.class);

	@Autowired
	private BankinfoServiceI bankinfoService;
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
	 * r_c_bankinfo列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "bankinfo")
	public ModelAndView bankinfo(HttpServletRequest request) {
		return new ModelAndView("system/bankinfo/bankinfoList");
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
	public void datagrid(BankinfoEntity bankinfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankinfo, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_brca_begin = request.getParameter("brca_begin");
		String query_brca_end = request.getParameter("brca_end");
		if(StringUtil.isNotEmpty(query_brca_begin)){
			cq.ge("brca", Integer.parseInt(query_brca_begin));
		}
		if(StringUtil.isNotEmpty(query_brca_end)){
			cq.le("brca", Integer.parseInt(query_brca_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bankinfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除r_c_bankinfo
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BankinfoEntity bankinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bankinfo = systemService.getEntity(BankinfoEntity.class, bankinfo.getId());
		message = "r_c_bankinfo删除成功";
		try{
			bankinfoService.delete(bankinfo);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_bankinfo删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除r_c_bankinfo
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "r_c_bankinfo删除成功";
		try{
			for(String id:ids.split(",")){
				BankinfoEntity bankinfo = systemService.getEntity(BankinfoEntity.class, 
				id
				);
				bankinfoService.delete(bankinfo);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_bankinfo删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加r_c_bankinfo
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BankinfoEntity bankinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_bankinfo添加成功";
		try{
			bankinfoService.save(bankinfo);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "r_c_bankinfo添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新r_c_bankinfo
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BankinfoEntity bankinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_bankinfo更新成功";
		BankinfoEntity t = bankinfoService.get(BankinfoEntity.class, bankinfo.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bankinfo, t);
			bankinfoService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_bankinfo更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * r_c_bankinfo新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BankinfoEntity bankinfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankinfo.getId())) {
			bankinfo = bankinfoService.getEntity(BankinfoEntity.class, bankinfo.getId());
			req.setAttribute("bankinfoPage", bankinfo);
		}
		return new ModelAndView("system/bankinfo/bankinfo-add");
	}
	/**
	 * r_c_bankinfo编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BankinfoEntity bankinfo, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bankinfo.getId())) {
			bankinfo = bankinfoService.getEntity(BankinfoEntity.class, bankinfo.getId());
			req.setAttribute("bankinfoPage", bankinfo);
		}
		return new ModelAndView("system/bankinfo/bankinfo-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("system/bankinfo/bankinfoUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BankinfoEntity bankinfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bankinfo, request.getParameterMap());
		List<BankinfoEntity> bankinfos = this.bankinfoService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"r_c_bankinfo");
		modelMap.put(NormalExcelConstants.CLASS,BankinfoEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("r_c_bankinfo列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,bankinfos);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BankinfoEntity bankinfo,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "r_c_bankinfo");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,BankinfoEntity.class);
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
				List<BankinfoEntity> listBankinfoEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BankinfoEntity.class,params);
				for (BankinfoEntity bankinfo : listBankinfoEntitys) {
					bankinfoService.save(bankinfo);
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
