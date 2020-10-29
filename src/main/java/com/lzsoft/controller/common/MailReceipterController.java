package com.lzsoft.controller.common;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSRole;
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

import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.MailReceipterEntity;
import com.lzsoft.service.common.MailReceipterServiceI;

/**
 * @Title: Controller
 * @Description: r_c_mail_receipter
 * @author onlineGenerator
 * @date 2015-09-07 15:56:57
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/mailReceipterController")
public class MailReceipterController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MailReceipterController.class);

	@Autowired
	private MailReceipterServiceI mailReceipterService;
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
	 * r_c_mail_receipter列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "mailReceipter")
	public ModelAndView mailReceipter(HttpServletRequest request) {
		return new ModelAndView("basedata/mailReceipterList");
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
	public void datagrid(MailReceipterEntity mailReceipter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MailReceipterEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				mailReceipter, request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.mailReceipterService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除r_c_mail_receipter
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MailReceipterEntity mailReceipter,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		mailReceipter = systemService.getEntity(MailReceipterEntity.class,
				mailReceipter.getId());
		message = "r_c_mail_receipter删除成功";
		try {
			mailReceipterService.delete(mailReceipter);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_mail_receipter删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除r_c_mail_receipter
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_mail_receipter删除成功";
		try {
			for (String id : ids.split(",")) {
				MailReceipterEntity mailReceipter = systemService.getEntity(
						MailReceipterEntity.class, id);
				mailReceipterService.delete(mailReceipter);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_mail_receipter删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加r_c_mail_receipter
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MailReceipterEntity mailReceipter,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_mail_receipter添加成功";
		try {
			TSBaseUser tsbuser = systemService.getEntity(TSUser.class,
					mailReceipter.getUserid());
			mailReceipter.setUsername(tsbuser.getRealName());
			mailReceipter.setEmail(((TSUser) tsbuser).getEmail());
			String brcaid = oConvertUtils.getString(request
					.getParameter("brcaid"));
			mailReceipter.setBrca(getBrcacodes(brcaid));
			mailReceipterService.save(mailReceipter);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_mail_receipter添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新r_c_mail_receipter
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(MailReceipterEntity mailReceipter,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "r_c_mail_receipter更新成功";
		MailReceipterEntity t = mailReceipterService.get(
				MailReceipterEntity.class, mailReceipter.getId());
		try {
			TSBaseUser tsbuser = systemService.getEntity(TSUser.class,
					mailReceipter.getUserid());
			mailReceipter.setUsername(tsbuser.getRealName());
			mailReceipter.setEmail(((TSUser) tsbuser).getEmail());
			String brcaid = oConvertUtils.getString(request
					.getParameter("brcaid"));
			mailReceipter.setBrca(getBrcacodes(brcaid));
			MyBeanUtils.copyBeanNotNull2Bean(mailReceipter, t);

			mailReceipterService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "r_c_mail_receipter更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	private String getBrcacodes(String brcaid) {
		String brca = "";
		if (null != brcaid) {
			String[] brcaids = StringUtils.split(brcaid, ",");

			for (String id : brcaids) {
				BankinfoEntity bk = systemService.getEntity(
						BankinfoEntity.class, id);
				brca += bk.getBrca() + ",";
			}
		}
		return brca;
	}

	/**
	 * r_c_mail_receipter新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MailReceipterEntity mailReceipter,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mailReceipter.getId())) {
			mailReceipter = mailReceipterService.getEntity(
					MailReceipterEntity.class, mailReceipter.getId());
			req.setAttribute("mailReceipterPage", mailReceipter);
			String[] brcas = StringUtils.split(mailReceipter.getBrca(), ",");
			String brcaName = "";
			for (String brca : brcas) {
				brcaName += systemService.findShortcnnameBybrca(brca) + ",";
			}
			req.setAttribute("brcaName", brcaName);
		}
		return new ModelAndView("basedata/mailReceipter-add");
	}

	/**
	 * r_c_mail_receipter编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MailReceipterEntity mailReceipter, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mailReceipter.getId())) {
			mailReceipter = mailReceipterService.getEntity(MailReceipterEntity.class, mailReceipter.getId());
			req.setAttribute("mailReceipterPage", mailReceipter);
			String[] brcas=StringUtils.split(mailReceipter.getBrca(),",");
			String brcaid="";
			String brcaName="";
			if (null!=brcas) {
			for (String brca : brcas) {
				if(!"".equals(brca)){
				brcaid+=systemService.findUniqueByProperty(BankinfoEntity.class, "brca", brca).getId()+",";
				brcaName+=systemService.findShortcnnameBybrca(brca)+",";
				}
			}
			}
			req.setAttribute("id",brcaid);
			req.setAttribute("brcaName", brcaName);
			
		}
		return new ModelAndView("basedata/mailReceipter-update");
	}

	/**
	 * r_c_mail_receipter编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "brcas")
	public ModelAndView brcas(MailReceipterEntity mailReceipter,
			HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("basedata/brcas");
		String ids = oConvertUtils.getString(req.getParameter("ids"));

		@SuppressWarnings("unused")
		String type = oConvertUtils.getString(req.getAttribute("id"));
		mv.addObject("ids", ids);
		return mv;
	}

	/**
	 * 显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridBrca")
	public void datagridBrca(BankinfoEntity bankinfoEntity,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BankinfoEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				bankinfoEntity);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("basedata/mailReceipterUpload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MailReceipterEntity mailReceipter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MailReceipterEntity.class,
				dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				mailReceipter, request.getParameterMap());
		List<MailReceipterEntity> mailReceipters = this.mailReceipterService
				.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "r_c_mail_receipter");
		modelMap.put(NormalExcelConstants.CLASS, MailReceipterEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams(
				"r_c_mail_receipter列表", "导出人:"
						+ ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, mailReceipters);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MailReceipterEntity mailReceipter,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(TemplateExcelConstants.FILE_NAME, "r_c_mail_receipter");
		modelMap.put(TemplateExcelConstants.PARAMS, new TemplateExportParams(
				"Excel模板地址"));
		modelMap.put(TemplateExcelConstants.MAP_DATA, null);
		modelMap.put(TemplateExcelConstants.CLASS, MailReceipterEntity.class);
		modelMap.put(TemplateExcelConstants.LIST_DATA, null);
		return TemplateExcelConstants.JEECG_TEMPLATE_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<MailReceipterEntity> listMailReceipterEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(),
								MailReceipterEntity.class, params);
				for (MailReceipterEntity mailReceipter : listMailReceipterEntitys) {
					mailReceipterService.save(mailReceipter);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
