package com.lzsoft.controller.safe;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lzsoft.entity.safe.BopGEntity;
import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.entity.safe.ReportDicEntity;
import com.lzsoft.service.safe.ReportDicServiceI;
import com.lzsoft.util.EntityToString;

/**   
 * @Title: Controller
 * @Description: 报表字典
 * @author zhangdaihao
 * @date 2017-07-19 16:18:04
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/reportDicController")
public class ReportDicController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReportDicController.class);

	@Autowired
	private ReportDicServiceI reportDicService;
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
	 * 报表字典列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "reportDic")
	public ModelAndView reportDic(HttpServletRequest request) {
		return new ModelAndView("safe/rpm/reportDicList");
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
	public void datagrid(ReportDicEntity reportDic,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, reportDic, request.getParameterMap());
		this.reportDicService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除报表字典
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ReportDicEntity reportDic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		reportDic = systemService.getEntity(ReportDicEntity.class, reportDic.getId());
		message = "报表字典删除成功";
		reportDicService.delete(reportDic);
		systemService.addLog(message+"["+EntityToString.entityToString(reportDic)+"]", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加报表字典
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ReportDicEntity reportDic, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(reportDic.getId())) {
			message = "报表字典更新成功";
			ReportDicEntity t = reportDicService.get(ReportDicEntity.class, reportDic.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reportDic, t);
				reportDicService.saveOrUpdate(t);
				systemService.addLog(message+"["+EntityToString.entityToString(t)+"]", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "报表字典更新失败";
			}
		} else {
			message = "报表字典添加成功";
			reportDicService.save(reportDic);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 报表字典列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ReportDicEntity reportDic, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(reportDic.getId())) {
			reportDic = reportDicService.getEntity(ReportDicEntity.class, reportDic.getId());
			req.setAttribute("reportDicPage", reportDic);
		}
		return new ModelAndView("safe/rpm/reportDic");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("safe/rpm/reportDicUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ReportDicEntity reportDic,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ReportDicEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, reportDic, request.getParameterMap());
		List<ReportDicEntity> reportDics = this.reportDicService.getListByCriteriaQuery(cq,false);
		System.out.println("export records [" + reportDics.size() + "]条");
		modelMap.put(NormalExcelConstants.FILE_NAME,"t_report_dic");
		modelMap.put(NormalExcelConstants.CLASS,ReportDicEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("reportDic列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,reportDics);
		System.out.println("modelMap.size = [" + modelMap.size() + "]");
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ReportDicEntity reportDic,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "t_report_dic");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,ReportDicEntity.class);
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
				List<ReportDicEntity> listReportDicEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ReportDicEntity.class,params);
				for (ReportDicEntity reportDic : listReportDicEntitys) {
					reportDicService.save(reportDic);
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
