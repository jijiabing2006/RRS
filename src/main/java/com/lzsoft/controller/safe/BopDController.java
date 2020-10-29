package com.lzsoft.controller.safe;
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
import org.jeecgframework.core.util.DateUtils;
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
import com.lzsoft.entity.safe.BopDEntity;
import com.lzsoft.service.safe.BopDServiceI;
import com.lzsoft.util.EntityToString;



/**   
 * @Title: Controller
 * @Description: bop_d
 * @author onlineGenerator
 * @date 2015-06-08 15:06:31
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/bopDController")
public class BopDController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BopDController.class);

	@Autowired
	private BopDServiceI bopDService;
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
	 * bop_d列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "bopD")
	public ModelAndView bopD(HttpServletRequest request) {
		return new ModelAndView("safe/bop/bopDList");
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
	public void datagrid(BopDEntity bopD,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BopDEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bopD, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user=ResourceUtil.getSessionUserName();
			String brca=	user.getCurrentDepart().getBrca();
			if(!brca.equals(Constants.HQBANKCODE)){
				cq.eq("brca",brca);
			}else{
				cq.isNotNull("brca");
			}
			
			if(!cq.isMenualsearch()){//说明是自动加载
				//自动加载没有进入SAFE的数据
				cq.notEq("isinsafe", "1");
				cq.isNull("isinsafe");
				cq.notEq("isvalidation", "1");
				cq.or(cq,1, 2);//1，2的或条件拼装后放在下标4了
				cq.or(cq,4, 3);//将3，4再拼装为或条件，放入下标5
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bopDService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除bop_d
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BopDEntity bopD, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bopD = systemService.getEntity(BopDEntity.class, bopD.getId());
		message = "境内收入删除成功";
		try{
			bopDService.delete(bopD);
			systemService.addLog(message+"["+EntityToString.entityToString(bopD)+"]", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "境内收入删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除bop_d
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "境内收入删除成功";
		try{
			for(String id:ids.split(",")){
				BopDEntity bopD = systemService.getEntity(BopDEntity.class, 
				id
				);
				bopDService.delete(bopD);
				systemService.addLog(message+"["+EntityToString.entityToString(bopD)+"]", Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "境内收入删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 添加bop_d
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BopDEntity bopD, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "境内收入添加成功";
		try{
			TSUser user=ResourceUtil.getSessionUserName();
			   String brca=	user.getCurrentDepart().getBrca();
				CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class);
				cq.eq("brca", brca);
				cq.add();
				BankinfoEntity bank=systemService.getUniqueObjectByCriteriaQuery(cq);
				bopD.setBrca(brca);
				bopD.setRptno(bank.getBranchcode()+DateUtils.dateToStr (bopD.getImportdate(),"yyMMdd")+"****");
			bopDService.save(bopD);
			systemService.addLog(message+"["+EntityToString.entityToString(bopD)+"]", Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "境内收入添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新bop_d
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BopDEntity bopD, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "境内收入更新成功";
		BopDEntity t = bopDService.get(BopDEntity.class, bopD.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bopD, t);
			bopDService.saveOrUpdate(t);
			systemService.addLog(message+"["+EntityToString.entityToString(bopD)+"]", Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "境内收入更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * bop_d新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BopDEntity bopD, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bopD.getId())) {
			bopD = bopDService.getEntity(BopDEntity.class, bopD.getId());
			req.setAttribute("bopDPage", bopD);
		}
		return new ModelAndView("safe/bop/bopD-add");
	}
	/**
	 * bop_d编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BopDEntity bopD, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bopD.getId())) {
			bopD = bopDService.getEntity(BopDEntity.class, bopD.getId());
			req.setAttribute("bopDPage", bopD);
		}
		return new ModelAndView("safe/bop/bopD-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("safe/bop/bopDUpload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "exportXls")
	public String exportXls(BopDEntity bopD,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BopDEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bopD, request.getParameterMap());
		List<BopDEntity> bopDs = this.bopDService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"境内收入");
		modelMap.put(NormalExcelConstants.CLASS,BopDEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("境内收入列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,bopDs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BopDEntity bopD,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "境内收入");
		modelMap.put(TemplateExcelConstants.PARAMS,new TemplateExportParams("Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA,null);
		modelMap.put(TemplateExcelConstants.CLASS,BopDEntity.class);
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
				List<BopDEntity> listBopDEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BopDEntity.class,params);
				for (BopDEntity bopD : listBopDEntitys) {
					bopDService.save(bopD);
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
