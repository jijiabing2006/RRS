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

import com.lzsoft.entity.common.EncodeMapUsccEntity;
import com.lzsoft.service.common.EncodeMapUsccServiceI;

/**   
 * @Title: Controller
 * @Description: 组织机构代码mapping社会统一信用代码
 * @author zhangdaihao
 * @date 2017-09-19 14:23:24
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/encodeMapUsccController")
public class EncodeMapUsccController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EncodeMapUsccController.class);

	@Autowired
	private EncodeMapUsccServiceI encodeMapUsccService;
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
	 * 组织机构代码mapping社会统一信用代码列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "encodeMapUscc")
	public ModelAndView encodeMapUscc(HttpServletRequest request) {
		return new ModelAndView("com/lzsoft/common/encodeMapUsccList");
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
	public void datagrid(EncodeMapUsccEntity encodeMapUscc,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EncodeMapUsccEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, encodeMapUscc, request.getParameterMap());
		this.encodeMapUsccService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除组织机构代码mapping社会统一信用代码
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EncodeMapUsccEntity encodeMapUscc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		encodeMapUscc = systemService.getEntity(EncodeMapUsccEntity.class, encodeMapUscc.getId());
		message = "组织机构代码mapping社会统一信用代码删除成功";
		encodeMapUsccService.delete(encodeMapUscc);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加组织机构代码mapping社会统一信用代码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EncodeMapUsccEntity encodeMapUscc, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(encodeMapUscc.getId())) {
			message = "组织机构代码mapping社会统一信用代码更新成功";
			EncodeMapUsccEntity t = encodeMapUsccService.get(EncodeMapUsccEntity.class, encodeMapUscc.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(encodeMapUscc, t);
				encodeMapUsccService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "组织机构代码mapping社会统一信用代码更新失败";
			}
		} else {
			message = "组织机构代码mapping社会统一信用代码添加成功";
			encodeMapUsccService.save(encodeMapUscc);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 组织机构代码mapping社会统一信用代码列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EncodeMapUsccEntity encodeMapUscc, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(encodeMapUscc.getId())) {
			encodeMapUscc = encodeMapUsccService.getEntity(EncodeMapUsccEntity.class, encodeMapUscc.getId());
			req.setAttribute("encodeMapUsccPage", encodeMapUscc);
		}
		return new ModelAndView("com/lzsoft/common/encodeMapUscc");
	}
}
