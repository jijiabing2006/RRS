package org.jeecgframework.core.timer;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.LogUtil;
import org.jeecgframework.web.system.pojo.base.TSScheduleJobEntity;
import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.JobTaskServiceI;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.lzsoft.common.Constants;
import com.lzsoft.util.QuartzJobFactory;
import com.lzsoft.util.QuartzJobFactoryDisallowConcurrentExecution;

/**
 * 读取数据库 然后判断是否启动任务
 * 
 * @author JueYue
 * @date 2013-9-22
 * @version 1.0
 */
public class DataBaseSchedulerFactoryBean extends SchedulerFactoryBean {

	// @Autowired
	// private TimeTaskServiceI timeTaskService;

	@Autowired
	private JobTaskServiceI jobTaskService;

	/**
	 * 读取数据库判断是否开始定时任务
	 */
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		// String[] trigerrNames =
		// this.getScheduler().getTriggerNames(Scheduler.DEFAULT_GROUP);
		// Set<TriggerKey> triggers =
		// this.getScheduler().getTriggerKeys(GroupMatcher.anyTriggerGroup());
		// TSTimeTaskEntity task;
		// task =
		// timeTaskService.findUniqueByProperty(TSTimeTaskEntity.class,"taskId","accTrigger");
		// List<TSTimeTaskEntity> jList =
		// timeTaskService.getList(TSTimeTaskEntity.class);
		List<TSScheduleJobEntity> jobList = jobTaskService
				.getList(TSScheduleJobEntity.class);
		for (TSScheduleJobEntity job : jobList) {
			if (job == null
					|| Constants.STATUS_NOT_RUNNING.equals(job.getJobStatus())) {
				continue;
			} else {
				Scheduler scheduler = this.getScheduler();
				LogUtil.info("读取数据库并判断任务"+job.getJobGroup()+"."+job.getJobName()+"为随机启动任务，启动时间为："+DateUtils.date2Str(new Date(), DateUtils.date_sdf) );
				TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
						job.getJobGroup());

				CronTrigger trigger = (CronTrigger) scheduler
						.getTrigger(triggerKey);

				// 不存在，创建一个
				if (null == trigger) {
					Class clazz = Constants.CONCURRENT_IS.equals(job
							.getIsConcurrent()) ? QuartzJobFactory.class
							: QuartzJobFactoryDisallowConcurrentExecution.class;

					JobDetail jobDetail = JobBuilder.newJob(clazz)
							.withIdentity(job.getJobName(), job.getJobGroup())
							.build();

					jobDetail.getJobDataMap().put("scheduleJob", job);

					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
							.cronSchedule(job.getCronExpression());

					trigger = TriggerBuilder.newTrigger()
							.withIdentity(job.getJobName(), job.getJobGroup())
							.withSchedule(scheduleBuilder).build();

					scheduler.scheduleJob(jobDetail, trigger);
				} else {
					// Trigger已存在，那么更新相应的定时设置
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
							.cronSchedule(job.getCronExpression());

					// 按新的cronExpression表达式重新构建trigger
					trigger = trigger.getTriggerBuilder()
							.withIdentity(triggerKey)
							.withSchedule(scheduleBuilder).build();

					// 按新的trigger重新设置job执行
					scheduler.rescheduleJob(triggerKey, trigger);
				}
			}
		}
	}

}
