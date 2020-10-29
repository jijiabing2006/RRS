package org.jeecgframework.web.system.controller.core;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSScheduleJobEntity;
import org.jeecgframework.web.system.service.JobTaskServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzsoft.common.Constants;
import com.lzsoft.entity.safe.AccCAEntity;

@Scope("prototype")
@Controller
@RequestMapping("/jobTaskController")
public class JobTaskController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(JobTaskController.class);
	@Autowired
	private JobTaskServiceI jobTaskService;

	@Autowired
	private DynamicTask dynamicTask;

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
	 * 定时任务管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jobTask")
	public ModelAndView timeTask(HttpServletRequest request) {
		return new ModelAndView("system/timetask/jobTaskList");
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
	public void datagrid(TSScheduleJobEntity scheduleJob,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSScheduleJobEntity.class,
				dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				scheduleJob, request.getParameterMap());
		this.jobTaskService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除定时任务管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSScheduleJobEntity scheduleJob,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		scheduleJob = systemService.getEntity(TSScheduleJobEntity.class,
				scheduleJob.getId());

		if ("1".equals(scheduleJob.getJobStatus())) {
			message = "定时任务：" + scheduleJob.getJobName() + "处于运行状态，停止后才能删除";
		} else {
			message = "定时任务管理删除成功";
			jobTaskService.delete(scheduleJob);
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加定时任务管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSScheduleJobEntity scheduleJob,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		try {
			scheduleJob.setBeanClass(scheduleJob.getBeanClass().trim());
			if (StringUtil.isNotEmpty(scheduleJob.getId())) {
				scheduleJob.setUpdateTime(new Date());
				jobTaskService.saveOrUpdate(scheduleJob);
			} else {
				scheduleJob.setCreateTime(new Date());
				jobTaskService.save(scheduleJob);
			}

		} catch (Exception e) {
			message = "保存失败，检查 name group 组合是否有重复！";
			j.setMsg(message);
			return j;
		}

		return j;
	}

	/**
	 * 定时任务管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSScheduleJobEntity scheduleJob,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(scheduleJob.getId())) {
			scheduleJob = jobTaskService.getEntity(TSScheduleJobEntity.class,
					scheduleJob.getId());
			req.setAttribute("jobTaskPage", scheduleJob);
		}
		return new ModelAndView("system/timetask/jobTask");
	}

	/**
	 * 启动或者停止任务
	 */
	@RequestMapping(params = "startOrStopTask")
	@ResponseBody
	public AjaxJson startOrStopTask(TSScheduleJobEntity scheduleJob,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "定时任务管理更新失败";
		try {
			boolean isStart = scheduleJob.getJobStatus().equals("1");

			TSScheduleJobEntity t = jobTaskService.get(
					TSScheduleJobEntity.class, scheduleJob.getId());

			if (isStart) {
				t.setJobStatus(Constants.STATUS_RUNNING);
				dynamicTask.addJob(t);
			} else {
				dynamicTask.deleteJob(t);
				t.setJobStatus(Constants.STATUS_NOT_RUNNING);
			}
			jobTaskService.saveOrUpdate(t);

			systemService.addLog((isStart ? "开启任务" : "停止任务") + t.getJobName(),
					Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			message = "定时任务管理添加成功";
		} catch (SchedulerException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新任务时间使之生效
	 */
	@RequestMapping(params = "updateTime")
	@ResponseBody
	public AjaxJson updateTime(TSScheduleJobEntity scheduleJob,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			message = "cron表达式有误，不能被解析！";
			j.setMsg(message);
			j.setSuccess(false);
			j.setObj("cronExpression");
			return j;
		}
		try {
			Object obj = null;
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				obj = SpringUtils.getBean(scheduleJob.getSpringId());
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass().trim());
				obj = clazz.newInstance();
			}
			if (obj == null) {
				message = "未找到目标类或者SpringID！";
				j.setMsg(message);
				j.setSuccess(false);
				j.setObj("beanClass");
				return j;
			} else {
				Class clazz = obj.getClass();
				Method method = null;

				method = clazz.getMethod(scheduleJob.getMethodName(), null);

				if (method == null) {
					message = "未找到目标方法！";
					j.setMsg(message);
					j.setSuccess(false);
					j.setObj("methodName");
					return j;
				}
			}
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			// TODO Auto-generated catch block
			message = "未找到目标类或者方法！" + e.getMessage();
			j.setMsg(message);
			j.setSuccess(false);
			j.setObj("cronExpression");
			return j;
		}
		return j;
	}

}
