package com.lzsoft.controller.safe;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.safe.AccCAEntity;
import com.lzsoft.service.safe.AccCAServiceI;
import com.lzsoft.util.EntityToString;



/**   
 * @Title: Controller
 * @Description: 账户开关户信息
 * @author onlineGenerator
 * @date 2015-06-30 14:22:42
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/accCAController")
public class AccCAController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AccCAController.class);

	@Autowired
	private AccCAServiceI accCAService;
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
	 * 账户开关户信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "accCA")
	public ModelAndView accCA(HttpServletRequest request) {
		return new ModelAndView("safe/acc/accCAList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "datagrid")
	public void datagrid(AccCAEntity accCA,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AccCAEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, accCA, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user=ResourceUtil.getSessionUserName();
			String brca=	user.getCurrentDepart().getBrca();
			if(!brca.equals(Constants.HQBANKCODE)){
				cq.eq("brca",brca);
			}else{
				cq.isNotNull("brca");
			}
			
			if(!cq.isMenualsearch()){//
				//自动加载没有进入SAFE的数据
				cq.notEq("isinsafe", "1");
				cq.isNull("isinsafe");
				cq.or(cq, 1, 2);
			}
		String query_businessdate_begin = request.getParameter("businessdate_begin");
		String query_businessdate_end = request.getParameter("businessdate_end");
		if(StringUtil.isNotEmpty(query_businessdate_begin)){
			cq.ge("businessdate", new SimpleDateFormat("yyyy-MM-dd").parse(query_businessdate_begin));
		}
		if(StringUtil.isNotEmpty(query_businessdate_end)){
			cq.le("businessdate", new SimpleDateFormat("yyyy-MM-dd").parse(query_businessdate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.accCAService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除账户开关户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AccCAEntity accCA, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		accCA = systemService.getEntity(AccCAEntity.class, accCA.getId());
		message = "账户开关户信息删除成功:["+EntityToString.entityToString(accCA)+"]";
		accCAService.delete(accCA);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除账户开关户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			for (String id : ids.split(",")) {
				AccCAEntity accCA = systemService.getEntity(AccCAEntity.class,
						id);
				message = "账户开关户信息删除成功:["+EntityToString.entityToString(accCA)+"]";
				accCAService.delete(accCA);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "账户开关户信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加账户开关户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AccCAEntity accCA, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		try{
			TSUser user=ResourceUtil.getSessionUserName();
		   String brca=	user.getCurrentDepart().getBrca();
			CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class);
			cq.eq("brca", brca);
			cq.add();
			BankinfoEntity bank=systemService.getUniqueObjectByCriteriaQuery(cq);
			accCA.setBrca(brca);
			accCA.setBranchcode(bank.getBranchcode());
			accCA.setBranchname(bank.getFullcnname());
			accCA.setRptno(bank.getBranchcode()+accCA.getAccountno()+accCA.getCurrencycode());
			accCAService.save(accCA);
			message = "账户开关户信息添加成功:["+EntityToString.entityToString(accCA)+"]";
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "账户["+accCA.getAccountno()+"]开关户信息添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新账户开关户信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AccCAEntity accCA, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "更新成功";
		AccCAEntity t = accCAService.get(AccCAEntity.class, accCA.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(accCA, t);
			if("1".equals(t.getIsexport())){
				t.setIsexport("0");
			}
			accCAService.saveOrUpdate(t);
			message = "账户开关户信息更新成功:["+EntityToString.entityToString(accCA)+"]";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "账户["+t.getAccountno()+"]开关户信息更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 账户开关户信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AccCAEntity accCA, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(accCA.getId())) {
			accCA = accCAService.getEntity(AccCAEntity.class, accCA.getId());
			req.setAttribute("accCAPage", accCA);
		}
		return new ModelAndView("safe/acc/accCA-add");
	}
	/**
	 * 账户开关户信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AccCAEntity accCA, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(accCA.getId())) {
			accCA = accCAService.getEntity(AccCAEntity.class, accCA.getId());
			req.setAttribute("accCAPage", accCA);
		}
		return new ModelAndView("safe/acc/accCA-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("safe/acc/accCAUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportXls")
	public String exportXls(AccCAEntity accCA,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AccCAEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, accCA, request.getParameterMap());
		List<AccCAEntity> accCAs = this.accCAService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"账户开关户信息");
		modelMap.put(NormalExcelConstants.CLASS,AccCAEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("账户开关户信息列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,accCAs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AccCAEntity accCA,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "账户开关户信息");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,AccCAEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA,null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}
	
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
				List<AccCAEntity> listAccCAEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AccCAEntity.class,params);
				for (AccCAEntity accCA : listAccCAEntitys) {
					accCAService.save(accCA);
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
