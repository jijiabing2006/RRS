package com.lzsoft.autoimport.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.autoimport.service.impl.AutoImportDataMainService;
import com.lzsoft.autoimport.service.impl.SummitImportDataImpl;
import com.lzsoft.autoimport.service.impl.SummitUpdateDataImpl;

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
@RequestMapping("/importDataController")
public class ImportDataController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ImportDataController.class);

	@Autowired
	private AutoImportDataMainService importData;

	@Autowired
	private SummitImportDataImpl summitImportDate;
	@Autowired
	private SummitUpdateDataImpl summitUpdateDate;
	
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
	 * @return
	 */
	@RequestMapping(params = "importdata")
	public ModelAndView bankinfo(HttpServletRequest request) {
		return new ModelAndView("basedata/importdata");
	}

	/**
	 * @return
	 */
	@RequestMapping(params = "doImportdata")
	@ResponseBody
	public AjaxJson doImportData() {
		AjaxJson j = new AjaxJson();
		message = "手工导数完成";
		try{
			importData.importData();
		}catch(Exception e){
			e.printStackTrace();
			message = "手工导数失败";
			LogUtil.error(message, e);
			systemService.addLog(message+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(params = "doImportSummitdata")
	@ResponseBody
	public AjaxJson doImportSummitdata() {
		AjaxJson j = new AjaxJson();
		message = "手工导入Summit数据完成";
		try{
			summitImportDate.autoImportDataToDB();
		}catch(Exception e){
			e.printStackTrace();
			message = "手工导入Summit数据失败";
			LogUtil.error(message, e);
			systemService.addLog(message+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(params = "doUpdateSummitdata")
	@ResponseBody
	public AjaxJson doUpdateSummitdata() {
		AjaxJson j = new AjaxJson();
		message = "手工更新Summit数据完成";
		try{
			summitUpdateDate.autoUpdateDataToDB();
		}catch(Exception e){
			e.printStackTrace();
			message = "手工更新Summit数据失败";
			LogUtil.error(message, e);
			systemService.addLog(message+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_ERROR);
		}
		j.setMsg(message);
		return j;
	}
}
