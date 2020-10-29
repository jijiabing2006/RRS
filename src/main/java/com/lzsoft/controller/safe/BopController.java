package com.lzsoft.controller.safe;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import com.lzsoft.entity.common.BankinfoEntity;
import com.lzsoft.entity.common.FeedbackerrorinfoEntity;
import com.lzsoft.entity.common.TaskscheduleEntity;
import com.lzsoft.entity.safe.SafeBaseEntity;
import com.lzsoft.service.safe.BopServiceI;
import com.lzsoft.service.safe.ReportServiceI;
import com.lzsoft.service.safe.feedback.IFeedbackService;

/**
 * @ClassName: bopController
 * @Description: TODO
 * @author
 */
@Scope("prototype")
@Controller
@RequestMapping("/bopController")
public class BopController extends BaseController {
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BopController.class);

	@Autowired
	private BopServiceI bopService;
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
		return new ModelAndView("safe/bop/control/" + turn + "");
	}

	/**
	 * 上报管理跳转
	 */
	@RequestMapping(params = "bopControl")
	public ModelAndView bopControl(HttpServletRequest request) {
		return new ModelAndView("safe/bop/control/bopControl");
	}

	/**
	 * 提取数据
	 */
	@RequestMapping(params = "bopExtract")
	@ResponseBody
	public AjaxJson bopExtract(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "提取数据完成";
		String date = oConvertUtils.getString(request.getParameter("date"));
		if (!StringUtil.isEmpty(date)) {
			CriteriaQuery cq2 = new CriteriaQuery(TaskscheduleEntity.class);
			cq2.eq("importdate", DateUtils.strToDate(date, "yyyy-MM-dd"));
			cq2.eq("taskname", "autoExtractBop");
			cq2.add();
			TaskscheduleEntity taskscheduleEntity= bopService.getUniqueObjectByCriteriaQuery(cq2);
			if (null!=taskscheduleEntity&&taskscheduleEntity.isExecutable()) {
				message = bopService.extracteBopReport(
						DateUtils.strToDate(date, Constants.DATEFORMAT),
						taskscheduleEntity);
				taskscheduleEntity.setExecutable(false);
				bopService.saveOrUpdate(taskscheduleEntity);//修改BOP可提取状态
			} else {
				message = "没有[" + date + "]日期下的提取任务，请确认提取日期是否正确，或增加计划任务。";
			}

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
		String accType = "BOP";

		String[] allTypeList = ArrayUtils.addAll(ArrayUtils.addAll(Constants.BOP_BASE_TYPE,Constants.BOP_DECLARE_TYPE),Constants.BOP_CONTROL_TYPE);
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
			LogUtil.error("BOP导出上报文件异常：", e);
		}

		j.setMsg(message);
		return j;

	}
	
	@RequestMapping(params = "exportBlankReport")
	@ResponseBody
	public AjaxJson exportBlankReport(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String date = oConvertUtils.getString(request.getParameter("date"));
		message = "提取数据完成";
		

		Date reportdate = DateUtils.strToDate(date, Constants.DATEFORMAT);
		Object mdate=	systemService.getMaxFieldValueByWhere(AccCustEntity.class, "importdate", "importdate=?", new Object[]{reportdate});
		if(null==mdate){
			message="没有"+date+"日期项下的T24数据，请确认日期选择是否正确。";
			j.setMsg(message);
			return j;
		}
		TSUser user = ResourceUtil.getSessionUserName();
		String accType = "BOP";
		
		String[] allTypeList = ArrayUtils.addAll(ArrayUtils.addAll(Constants.BOP_BASE_TYPE,Constants.BOP_DECLARE_TYPE),Constants.BOP_CONTROL_TYPE);
		// 2 预校验完成，上报内容有错误 1 预校验完成，上报内容正确 3 预校验发生异常 0有数据无法生成空报文
		try {
			int result = reportService.generateEmptyBOPReport(allTypeList, reportdate,
					user.getCurrentDepart(), accType);
			if (1 == result) {
				message = "通过预校验,";
				message = reportService
						.exportReportAndSend(date, user, accType);
				reportService.addTashSchdule(reportdate, user, accType);
			} else if (0 == result) {
			String brcode=	systemService.findUniqueByProperty(BankinfoEntity.class, "brca", user.getCurrentDepart().getBrca()).getBranchcode();
				message = "[" + date + "]下"+brcode+"有数据存在，不可以上报空报文。";
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
			LogUtil.error("生成BOP空报文件失败：", e);
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
		String accType = "BOP";
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
		cq.eq("fbh.apptype", "BOP");
		cq.eq("fbh.importdate", reportdate);

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 页面跳转
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(params = "gobopupdate")
	public ModelAndView gobopupdate(
			FeedbackerrorinfoEntity feedbackerrorinfoEntity,
			HttpServletRequest req) {
		feedbackerrorinfoEntity = systemService.getEntity(
				FeedbackerrorinfoEntity.class, feedbackerrorinfoEntity.getId());
		String curfile = feedbackerrorinfoEntity.getFbh().getCurrentfile();
		String url = "safe/bop/bop" + curfile + "-update";
		String reqkey = "bop" + curfile + "Page";
		Class clazz = bopService.getClazzByType(curfile);

		CriteriaQuery cq = new CriteriaQuery(clazz);
		cq.eq("filename", feedbackerrorinfoEntity.getFbh().getFilename());
		cq.eq("tfilename", feedbackerrorinfoEntity.getFbh().getTfilename());
		cq.eq("rptno", feedbackerrorinfoEntity.getRptno());
		cq.add();
		SafeBaseEntity bop = systemService.getUniqueResultByCriteriaQuery(cq);

		req.setAttribute(reqkey, bop);
		return new ModelAndView(url);

	}

	/**
	 * 批量复核BOP信息
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

				Object bopobj = systemService.getEntity(
						bopService.getClazzByType(type), id);
				if (!"1".equals(PropertyUtils.getProperty(bopobj, "isedit"))) {
					fmess.append(PropertyUtils.getProperty(bopobj, "buscode"))
							.append(",");
					count = 1;
				} else {
					if (Arrays.asList(Constants.BOP_BASE_TYPE).contains(type)) {// 基础信息要生成申报号码
						reportService.validation(bopobj);
						// 更新申报信息和管理信息申报号码
						Object dobj = bopService.findDeclareData(bopobj);
						if (dobj != null)
							bopService.saveOrUpdate(dobj);
						Object cobj = bopService.findManageData(bopobj);
						if (cobj != null)
							bopService.saveOrUpdate(cobj);
					} else {

						// 判断管理信息与基础信息的申报号码是否一致
						Object basebop = bopService.findBaseData(bopobj);
						if (null != basebop) {
							String rptno = (String) PropertyUtils.getProperty(
									basebop, "rptno");
							if (!"1".equals(PropertyUtils.getProperty(basebop,
									"isvalidation"))) {
								message = "业务编号："
										+ PropertyUtils.getProperty(bopobj,
												"buscode")
										+ "的基础信息还未审核，申报和管理信息不能审核";
								j.setMsg(message);
								return j;
							} else {
								if (Arrays.asList(Constants.BOP_CONTROL_TYPE)
										.contains(type)) {// 管理信息审核前要确认申报信息已经审核过
									Object declareObj = bopService
											.findDeclareData(bopobj);
									if (null!=declareObj&&!"1".equals(PropertyUtils.getProperty(
											declareObj, "isvalidation"))) { // 申报信息未审核的管理信息不可以审核
										message = "业务编号："
												+ PropertyUtils.getProperty(
														bopobj, "buscode")
												+ "的申报信息还未审核，管理信息不能审核";
										j.setMsg(message);
										return j;
									}
								}
								PropertyUtils.setProperty(bopobj, "rptno",
										rptno);
							}
						} else {
							message = "业务编号："
									+ PropertyUtils.getProperty(bopobj,
											"buscode") + "没有相应的基础信息，无法完成审核";
							j.setMsg(message);
							return j;
						}

					}

					PropertyUtils.setProperty(bopobj, "isvalidation", "1");
					PropertyUtils.setProperty(bopobj, "isinsafe", "0");
					PropertyUtils.setProperty(bopobj, "isexport", "0");
					bopService.saveOrUpdate(bopobj);
					safeFeedbackServiceImpl.deleteFeedbackError(bopobj);
				}
			}
			if (count > 0) {
				message = "业务编号：" + fmess.toString() + "的编辑状态不符合审核要求";

			}
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "BOP" + type + "审核失败";
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
					bopService.getClazzByType(type), id);
			request.setAttribute("cancelPojo", cancelPojo);
			request.setAttribute("type", type);
			request.setAttribute("actionname", "bopController");
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
	public AjaxJson cancelAuthor(Object bop, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			String id = oConvertUtils.getString(request.getParameter("id"));
			String type = oConvertUtils.getString(request.getParameter("type"));
			String remark = oConvertUtils.getString(request
					.getParameter("remark"));
			// String id = (String) PropertyUtils.getProperty(bop, "id");
			if (StringUtil.isNotEmpty(id)) {
				message = "审核状态重置成功，";
				Object t = systemService.get(bopService.getClazzByType(type),
						id);
				MyBeanUtils.copyBeanNotNull2Bean(bop, t);
				cancelCurrentObj(remark, t, type);
				bopService.saveOrUpdate(t);
				if (Arrays.asList(Constants.BOP_BASE_TYPE).contains(type)) {// 基础信息回滚要重置申报管理信息的申报号码和审核状态
					Object dobj = bopService.findDeclareData(t);
					if (dobj != null) {
						cancelCurrentObj(remark, dobj, type);
						bopService.saveOrUpdate(dobj);
						message += "同时申报信息被重置，";
					}
					Object cobj = bopService.findManageData(t);
					if (cobj != null) {
						cancelCurrentObj(remark, cobj, type);
						bopService.saveOrUpdate(cobj);
						message += "同时管理信息被重置，";
					}
				} else if (Arrays.asList(Constants.BOP_BASE_TYPE)
						.contains(type)) {// 申报信息回滚要重置管理信息的审核状态
					Object dobj = bopService.findDeclareData(t);
					if (dobj != null)
						cancelCurrentObj(remark, dobj, type);
					bopService.saveOrUpdate(dobj);
					message += "同时申报信息被重置，";
				}

				j.setMsg(message);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("Bop审核回滚异常", e);
			message = "Bop审核回滚异常";
		}
		return j;
	}

	private void cancelCurrentObj(String remark, Object t, String type)
			throws Exception, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		if ("4".equals(PropertyUtils.getProperty(t, "isinsafe"))) {// 预校验有误时，回滚
			PropertyUtils.setProperty(t, "isvalidation", "0");
		} else {
			PropertyUtils.setProperty(t, "isvalidation", "3");

			if (Arrays.asList(Constants.BOP_BASE_TYPE).contains(type)) {// 基础信息要生成申报号码
				PropertyUtils.setProperty(
						t,
						"rptno",
						StringUtils.substring(
								(String) PropertyUtils.getProperty(t, "rptno"),
								0, 18) + Constants.BOP_LAST_FOUR);
			}
		}
		PropertyUtils.setProperty(t, "isedit", "0");
		PropertyUtils.setProperty(t, "remark", remark);
	}

	/**
	 * BOP申报信息编辑页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goDeclaredata")
	public ModelAndView goDeclaredata(HttpServletRequest request) {
		String id = oConvertUtils.getString(request.getParameter("id"));
		String type = oConvertUtils.getString(request.getParameter("type"));
		request.setAttribute("id", id);
		String dtype = "";
		if (StringUtil.isNotEmpty(id)) {
			SafeBaseEntity basePojo = systemService.getEntity(
					bopService.getClazzByType(type), id);

			Object dobj = bopService.findDeclareData(basePojo);
			dtype = bopService.getDeclareType(type);
			request.setAttribute("bop" + dtype + "Page", dobj);
		}
		return new ModelAndView("safe/bop/bop" + dtype + "-update");
	}

	/**
	 * BOP管理信息编辑页面跳转
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goControldata")
	public ModelAndView goControldata(HttpServletRequest request) {
		String id = oConvertUtils.getString(request.getParameter("id"));
		String type = oConvertUtils.getString(request.getParameter("type"));
		request.setAttribute("id", id);
		String mtype = "";
		if (StringUtil.isNotEmpty(id)) {
			SafeBaseEntity basePojo = systemService.getEntity(
					bopService.getClazzByType(type), id);

			Object dobj = bopService.findManageData(basePojo);
			mtype = bopService.getManagerType(type);
			request.setAttribute("bop" + mtype + "Page", dobj);
		}
		return new ModelAndView("safe/bop/bop" + mtype + "-update");
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
