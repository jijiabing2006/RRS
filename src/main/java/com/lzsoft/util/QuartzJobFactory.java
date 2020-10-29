package com.lzsoft.util;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.TaskUtils;
import org.jeecgframework.web.system.pojo.base.TSScheduleJobEntity;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * 
 * @Description: 计划任务执行处 无状态
 * @author chenjianlin
 * @date 2014年4月24日 下午5:05:47
 */
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TSScheduleJobEntity scheduleJob = (TSScheduleJobEntity) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}