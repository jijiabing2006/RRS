package com.lzsoft.controller.safe;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.common.AccCustEntity;
import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.SafeBaseEntity;
import com.lzsoft.service.safe.AccServiceI;
import com.lzsoft.service.safe.ReportServiceI;
import com.lzsoft.service.safe.feedback.IFeedbackService;

/**
 * @ClassName: accController
 * @Description: TODO
 * @author
 */
@Scope("prototype")
@Controller
@RequestMapping("/accController")
public class AccController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(AccController.class);

	@Autowired
	private AccServiceI accService;
	@Autowired
	private ReportServiceI reportService;
	@Autowired
	private IFeedbackService safeFeedbackServiceImpl;
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
	 * 上报操作
	 */
	@RequestMapping(params = "execute")
	public ModelAndView execute(HttpServletRequest request) {
		String turn = oConvertUtils.getString(request.getParameter("turn"));
		return new ModelAndView("safe/acc/control/" + turn + "");
	}

	/**
	 * 上报管理跳转
	 */
	@RequestMapping(params = "accControl")
	public ModelAndView accControl(HttpServletRequest request) {
		return new ModelAndView("safe/acc/control/accControl");
	}

	/**
	 * 提取数据
	 */
	@RequestMapping(params = "accExtract")
	@ResponseBody
	public AjaxJson accExtract(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "提取数据完成";
		String date = oConvertUtils.getString(request.getParameter("date"));
		if (!StringUtil.isEmpty(date)) {
			
			
			CriteriaQuery cq2 = new CriteriaQuery(TaskscheduleEntity.class);
			cq2.eq("importdate", DateUtils.strToDate(date, "yyyy-MM-dd"));
			cq2.eq("taskname", "autoExtractAcc");
			cq2.add();
			TaskscheduleEntity taskscheduleEntity= accService.getUniqueObjectByCriteriaQuery(cq2);
			if (null!=taskscheduleEntity&&taskscheduleEntity.isExecutable()) {
				message = accService.extracteAccReport(
						DateUtils.strToDate(date, Constants.DATEFORMAT),
						taskscheduleEntity);
				taskscheduleEntity.setExecutable(false);
				accService.saveOrUpdate(taskscheduleEntity);//修改ACC可提取状态
			} else {
				message = "没有[" + date + "]日期下的提取任务，请确认提取日期是否正确，或增加计划任务。";
			}

		} else {

			message = "没有选择营业日期";

		}
		j.setMsg(message);
		return j;

	}

	/**
	 * 转换数据
	 */
	@RequestMapping(params = "accTransforme")
	@ResponseBody
	public AjaxJson accTransforme(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "转换数据完成";
		String date = oConvertUtils.getString(request.getParameter("date"));
		if (!StringUtil.isEmpty(date)) {
			message = accService.transformeAcc(DateUtils.strToDate(date,
					Constants.DATEFORMAT));
          systemService.addLog(message,  Globals.Log_Type_OTHER,
					Globals.Log_Leavel_INFO);
          message="转换结果到日志查询页面";
		} else {

			message = "没有选择营业日期";

		}
		j.setMsg(message);
		return j;

	}

	@RequestMapping(params = "exportReport")
	@ResponseBody
	public AjaxJson exportReport(HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		String date = oConvertUtils.getString(request.getParameter("date"));
		message = "提取数据完成";
		Date reportdate = DateUtils.strToDate(date, Constants.DATEFORMAT);
		TSUser user = ResourceUtil.getSessionUserName();
		String accType = "ACC";
		String[] allTypeList = Constants.ACC_BASE_TYPE;
		// 2 预校验完成，上报内容有错误 1 预校验完成，上报内容正确 3 预校验发生异常 0无数据
		try {
			int result = reportService.exportReport(allTypeList, reportdate,
					user.getCurrentDepart(), accType);
			if (1 == result) {
				message = "通过预校验,";
				message = reportService
						.exportReportAndSend(date, user, accType);
				reportService.addTashSchdule(reportdate, user, accType);
			} else if (0 == result) {
				message = "[" + date + "]下没有数据可以上报。";
			} else if (2 == result) {
				safeFeedbackServiceImpl.readFeedback(
						DateUtils.getCurrentDate(Constants.DATEFORMAT), "",
						accType, true);
				message = "预校验不通过，请查看反馈后重新导出上报。";
			} else if (3 == result) {
				message = "[" + date + "]下没有数据可以上报。";
				return j;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("ACC导出上报文件异常：", e);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 读取反馈
	 */
	@RequestMapping(params = "readFeedback")
	@ResponseBody
	public AjaxJson readFeedback(HttpServletRequest request,
			HttpServletResponse response) {

		AjaxJson j = new AjaxJson();
		String date = oConvertUtils.getString(request.getParameter("date"));
		String accType = "ACC";
		message = "";
		Date reportdate = DateUtils.strToDate(date, Constants.DATEFORMAT);
		if (null == reportdate || "".equalsIgnoreCase(reportdate.toString())) {
			message = "请选择要下载反馈信息的上报日期.";
		}
		try {
			message = safeFeedbackServiceImpl.readFeedback(reportdate, "",
					accType, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "findFeedback")
	public void findFeedback(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String date = oConvertUtils.getString(request
				.getParameter("fbh.importdate"));
		String processed = oConvertUtils.getString(request
				.getParameter("processed"));
		Date reportdate = !"".equals(date) ? DateUtils.strToDate(date,
				Constants.DATEFORMAT) : null;
		CriteriaQuery cq = new CriteriaQuery(FeedbackerrorinfoEntity.class,
				dataGrid);
		String menualsearch = request.getParameter("menualsearch");// 判断是自动加载还是手动加载（自动加载时只加载特殊条件的数据）
		if (null == menualsearch) {// 说明是自动加载
			// 自动加载没有进入SAFE的数据
			cq.notEq("processed", "1");
			cq.isNull("processed");
			cq.or(cq, 0, 1);
		}
		cq.eq("processed", processed);
		cq.createAlias("fbh", "fbh");
		cq.eq("fbh.apptype", "ACC");
		cq.eq("fbh.importdate", reportdate);

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 页面跳转
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(params = "goaccupdate")
	public ModelAndView goaccupdate(
			FeedbackerrorinfoEntity feedbackerrorinfoEntity,
			HttpServletRequest req) {
		feedbackerrorinfoEntity = systemService.getEntity(
				FeedbackerrorinfoEntity.class, feedbackerrorinfoEntity.getId());
		String curfile = feedbackerrorinfoEntity.getFbh().getCurrentfile();
		String url = "safe/acc/acc" + curfile + "-update";
		String reqkey = "acc" + curfile + "Page";
		Class clazz = accService.getClazzByType(curfile);

		CriteriaQuery cq = new CriteriaQuery(clazz);
		cq.eq("filename", feedbackerrorinfoEntity.getFbh().getFilename());
		cq.eq("tfilename", feedbackerrorinfoEntity.getFbh().getTfilename());
		cq.eq("apptype", feedbackerrorinfoEntity.getFbh().getApptype());
		cq.eq("rptno", feedbackerrorinfoEntity.getRptno());
		cq.add();
		SafeBaseEntity acc = systemService.getUniqueResultByCriteriaQuery(cq);

		req.setAttribute(reqkey, acc);
		return new ModelAndView(url);
	}

	/**
	 * 批量复核ACC信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchApprove")
	@ResponseBody
	public AjaxJson doBatchApprove(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "审核成功";
		String type = oConvertUtils.getString(request.getParameter("type"));
		try {
			StringBuffer fmess = new StringBuffer();
			int count = 0;
			for (String id : ids.split(",")) {

				Object accobj = systemService.getEntity(
						accService.getClazzByType(type), id);
				if (!"1".equals(PropertyUtils.getProperty(accobj, "isedit"))) {
					fmess.append(PropertyUtils.getProperty(accobj, "accountno"))
							.append(",");
					count = 1;
				} else {
					PropertyUtils.setProperty(accobj, "isvalidation", "1");
					PropertyUtils.setProperty(accobj, "isinsafe", "0");
					PropertyUtils.setProperty(accobj, "isexport", "0");
					accService.saveOrUpdate(accobj);
					safeFeedbackServiceImpl.deleteFeedbackError(accobj);

				}
			}
			if (count > 0) {
				message = "账号：" + fmess.toString() + "的编辑状态不符合审核要求";

			}
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "ACC" + type + "审核失败";
			LogUtil.error(message, e);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 跳转审核回滚页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doCancelAuthor")
	public ModelAndView doCheck(HttpServletRequest request) {
		String id = oConvertUtils.getString(request.getParameter("id"));
		String type = oConvertUtils.getString(request.getParameter("type"));
		request.setAttribute("id", id);
		if (StringUtil.isNotEmpty(id)) {
			SafeBaseEntity cancelPojo = systemService.getEntity(
					accService.getClazzByType(type), id);
			request.setAttribute("cancelPojo", cancelPojo);
			request.setAttribute("type", type);
			request.setAttribute("actionname", "accController");
		}
		return new ModelAndView("safe/cancelAuthor");
	}

	/**
	 * 审核回滚
	 * 
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "cancelAuthor")
	@ResponseBody
	public AjaxJson cancelAuthor(Object jsh, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String id = oConvertUtils.getString(request.getParameter("id"));
			String type = oConvertUtils.getString(request.getParameter("type"));
			String remark = oConvertUtils.getString(request
					.getParameter("remark"));
			// String id = (String) PropertyUtils.getProperty(jsh, "id");
			if (StringUtil.isNotEmpty(id)) {
				message = "审核状态重置成功";
				Object t = systemService.get(accService.getClazzByType(type),
						id);
				MyBeanUtils.copyBeanNotNull2Bean(jsh, t);
				cancelCurrentObj(remark, t);
				accService.saveOrUpdate(t);

				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("Jsh审核回滚异常", e);
			message = "Jsh审核回滚异常";
		}
		return j;
	}

	private void cancelCurrentObj(String remark, Object t) throws Exception,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if ("4".equals(PropertyUtils.getProperty(t, "isinsafe"))) {// 预校验有误时，回滚
			PropertyUtils.setProperty(t, "isvalidation", "0");
		} else {

			PropertyUtils.setProperty(t, "isvalidation", "3");
		}
		PropertyUtils.setProperty(t, "isedit", "0");
		PropertyUtils.setProperty(t, "remark", remark);
	}
	
	@RequestMapping(params = "dealDateCheck")
	@ResponseBody
	public AjaxJson dealDateCheck(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String date = oConvertUtils.getString(request.getParameter("date"));
		Date reportdate = DateUtils.strToDate(date, Constants.DATEFORMAT);
		System.out.println("LOG:dealDateCheck:" + date + "[" + reportdate.toString());
		
		CriteriaQuery cq = new CriteriaQuery(AccCustEntity.class);
		cq.max("importdate");
		cq.add();
		Date maxImportdate = systemService.getUniqueResultByCriteriaQuery(cq);
		System.out.println("LOG:maxImportdate:" + maxImportdate);
		
		if(reportdate.before(maxImportdate)) {
			message = "您所选日期小于最大记录日期，请确认是否删除或者修改报文需要上报！";
		} else {
			message = "请继续！";
		}
	
		j.setMsg(message);
		return j;
	}

}
