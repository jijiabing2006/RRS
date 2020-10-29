package com.lzsoft.controller.parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.ComboTree;
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
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
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

import com.lzsoft.entity.parameter.CurrencyEntity;
import com.lzsoft.service.parameter.CurrencyServiceI;

/**
 * @Title: Controller
 * @Description: 币种代码表
 * @author zhangdaihao
 * @date 2015-04-27 11:07:33
 * @version V1.0
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/currencyController")
public class CurrencyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CurrencyController.class);

	private static final String CURRENCY_UPLOAD_PAGE = "parameter/currency/currencyUpload";

	@Autowired
	private CurrencyServiceI currencyService;
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
	 * 币种代码表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "currency")
	public ModelAndView currency(HttpServletRequest request) {
		return new ModelAndView("parameter/currency/currencyList");
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
	public void datagrid(CurrencyEntity currency, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CurrencyEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				currency, request.getParameterMap());
		this.currencyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除币种代码表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CurrencyEntity currency, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		currency = systemService.getEntity(CurrencyEntity.class,
				currency.getId());
		message = "币种代码表删除成功";
		currencyService.delete(currency);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加币种代码表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CurrencyEntity currency, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(currency.getId())) {
			message = "币种代码表更新成功";
			CurrencyEntity t = currencyService.get(CurrencyEntity.class,
					currency.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(currency, t);
				currencyService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "币种代码表更新失败";
			}
		} else {
			message = "币种代码表添加成功";
			currencyService.save(currency);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 币种代码表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CurrencyEntity currency,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(currency.getId())) {
			currency = currencyService.getEntity(CurrencyEntity.class,
					currency.getId());
			req.setAttribute("currencyPage", currency);
		}
		return new ModelAndView("parameter/currency/currency");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CurrencyEntity currency,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap map) {

		CriteriaQuery cq = new CriteriaQuery(CurrencyEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				currency, request.getParameterMap());
		List<CurrencyEntity> currencys = this.currencyService
				.getListByCriteriaQuery(cq, false);

		map.put(NormalExcelConstants.FILE_NAME, "币种信息");
		map.put(NormalExcelConstants.CLASS, CurrencyEntity.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("币种列表",
				"导出人:Jeecg", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, currencys);
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
			// params.setTitleRows(2);
			// params.setHeadRows(2);
			// params.setNeedSave(true);
			try {
				List<CurrencyEntity> listCurrency = ExcelImportUtil
						.importExcel(file.getInputStream(),
								CurrencyEntity.class, params);
				for (CurrencyEntity currency : listCurrency) {
					if (currency.getName() != null) {
						currencyService.save(currency);
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
	 * 上传币种列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public String upload(HttpServletRequest req) {
		return CURRENCY_UPLOAD_PAGE;
	}

	/**
	 * 获取币种列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getCurrencyBox")
	@ResponseBody
	public List<ComboBox> getCurrencyBox(HttpServletRequest request, String id,
			String text) {
		List<CurrencyEntity> currencysList = systemService
				.findByQueryString("from CurrencyEntity where status=1");
		ComboBox com = new ComboBox();
		com.setId(id);
		com.setText(text);
		AjaxJson j = new AjaxJson();
		return TagUtil.getComboBox(currencysList, com);
	}

	@RequestMapping(params = "getCurrencyTree")
	@ResponseBody
	public List<ComboTree> getCurrencyTree(HttpServletRequest request) {
		// findHql不能处理is null条件
		List<CurrencyEntity> currencyList = systemService
				.findByQueryString("from CurrencyEntity where 1=1");
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "currency",
				null);
		comboTrees = systemService.ComboTree(currencyList, comboTreeModel,
				null, true);
		return comboTrees;
	}

}
