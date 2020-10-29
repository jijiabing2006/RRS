package com.lzsoft.controller.common;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.lzsoft.entity.common.FactoringEntity;
import com.lzsoft.service.common.FactoringServiceI;

/**   
 * @Title: Controller
 * @Description: 保理业务
 * @author zhangdaihao
 * @date 2017-07-06 11:52:32
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/factoringController")
public class FactoringController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FactoringController.class);

	@Autowired
	private FactoringServiceI factoringService;
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
	 * 保理业务列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "factoring")
	public ModelAndView factoring(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/common/factoringList");
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
	public void datagrid(FactoringEntity factoring,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FactoringEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, factoring, request.getParameterMap());
		this.factoringService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除保理业务
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FactoringEntity factoring, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		factoring = systemService.getEntity(FactoringEntity.class, factoring.getId());
		message = "保理业务删除成功";
		factoringService.delete(factoring);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加保理业务
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FactoringEntity factoring, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(factoring.getId())) {
			message = "保理业务更新成功";
			FactoringEntity t = factoringService.get(FactoringEntity.class, factoring.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(factoring, t);
				factoringService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "保理业务更新失败";
			}
		} else {
			message = "保理业务添加成功";
			factoringService.save(factoring);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 保理业务列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FactoringEntity factoring, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(factoring.getId())) {
			factoring = factoringService.getEntity(FactoringEntity.class, factoring.getId());
			req.setAttribute("factoringPage", factoring);
		}
		return new ModelAndView("com/lzsoft/common/factoring");
	}
}
