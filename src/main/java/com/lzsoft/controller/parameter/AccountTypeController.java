package com.lzsoft.controller.parameter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
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

import com.lzsoft.entity.parameter.AccountTypeEntity;
import com.lzsoft.service.parameter.AccountTypeServiceI;

/**   
 * @Title: Controller
 * @Description: 账户性质
 * @author zhangdaihao
 * @date 2015-05-04 10:49:38
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/accountTypeController")
public class AccountTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AccountTypeController.class);
	private static final String ACCOUNTTYPE_UPLOAD_PAGE = "parameter/accounttype/accountTypeUpload";

	@Autowired
	private AccountTypeServiceI accountTypeService;
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
	 * 账户性质列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "accountType")
	public ModelAndView accountType(HttpServletRequest request) {
		return new ModelAndView("parameter/accounttype/accountTypeList");
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
	public void datagrid(AccountTypeEntity accountType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AccountTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, accountType, request.getParameterMap());
		this.accountTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除账户性质
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AccountTypeEntity accountType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		accountType = systemService.getEntity(AccountTypeEntity.class, accountType.getId());
		message = "账户性质删除成功";
		accountTypeService.delete(accountType);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加账户性质
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AccountTypeEntity accountType, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(accountType.getId())) {
			message = "账户性质更新成功";
			AccountTypeEntity t = accountTypeService.get(AccountTypeEntity.class, accountType.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(accountType, t);
				accountTypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "账户性质更新失败";
			}
		} else {
			message = "账户性质添加成功";
			accountTypeService.save(accountType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 账户性质列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AccountTypeEntity accountType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(accountType.getId())) {
			accountType = accountTypeService.getEntity(AccountTypeEntity.class, accountType.getId());
			req.setAttribute("accountTypePage", accountType);
		}
		return new ModelAndView("parameter/accounttype/accountType");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AccountTypeEntity accounttype,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(AccountTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				accounttype, request.getParameterMap());
		List<AccountTypeEntity> acctypes = this.accountTypeService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "账户性质信息");
		map.put(NormalExcelConstants.CLASS, AccountTypeEntity.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("账户性质列表",
				"导出人:Jeecg", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, acctypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;

	}

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
			// params.setNeedSave(true);
			try {
				List<AccountTypeEntity> listAccType = ExcelImportUtil
						.importExcel(file.getInputStream(),
								AccountTypeEntity.class, params);
				for (AccountTypeEntity accounttype : listAccType) {
					if (accounttype.getName() != null) {
						accountTypeService.save(accounttype);
					}
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

	/**
	 * 上传账户列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public String upload(HttpServletRequest req) {
		return ACCOUNTTYPE_UPLOAD_PAGE;
	}

	/**
	 * 获取账户性质列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getAccountTypeBox")
	@ResponseBody
	public List<ComboBox> getAccountTypeBox(HttpServletRequest request, String id,
			String text) {
		List<AccountTypeEntity> acctypesList = systemService
				.findByQueryString("from AccountTypeEntity where 1=1");
		ComboBox com = new ComboBox();
		com.setId(id);
		com.setText(text);
		AjaxJson j = new AjaxJson();
		return TagUtil.getComboBox(acctypesList, com);
	}
}
