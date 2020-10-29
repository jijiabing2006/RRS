package org.jeecgframework.core.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.SpringUtils;
import org.jeecgframework.web.system.pojo.base.TSScheduleJobEntity;
import org.jeecgframework.web.system.pojo.base.TSTimeTaskEntity;
import org.jeecgframework.web.system.service.TimeTaskServiceI;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingBean;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean.StatefulMethodInvokingJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.lzsoft.common.Constants;
import com.lzsoft.util.QuartzJobFactory;
import com.lzsoft.util.QuartzJobFactoryDisallowConcurrentExecution;


/**
 * 动态任务,用以动态调整Spring的任务
 * 
 * @author JueYue
 * @date 2013-9-20
 * @version 1.0
 */
@Service(value = "dynamicTask")
public class DynamicTask {

	private static Logger logger = Logger.getLogger(DynamicTask.class);

	/*@Resource
	private Scheduler schedulerFactory;
*/
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	


	/**
	 * 更改任务 cron表达式
	 * @throws SchedulerException 
	 */
	public void updateCron(TSScheduleJobEntity job) throws SchedulerException {
		if (Constants.STATUS_RUNNING.equals(job.getJobStatus())) {
			updateJobCron(job);
		}
	}

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void addJob(TSScheduleJobEntity job) throws SchedulerException {
		if (job == null || !Constants.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}

		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		logger.debug(scheduler + ".......................................................................................add");
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 不存在，创建一个
		if (null == trigger) {
			Class clazz = Constants.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;

			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}

	
	
	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TSScheduleJobEntity> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<TSScheduleJobEntity> jobList = new ArrayList<TSScheduleJobEntity>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				TSScheduleJobEntity job = new TSScheduleJobEntity();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TSScheduleJobEntity> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<TSScheduleJobEntity> jobList = new ArrayList<TSScheduleJobEntity>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			TSScheduleJobEntity job = new TSScheduleJobEntity();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(TSScheduleJobEntity scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(TSScheduleJobEntity scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(TSScheduleJobEntity scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(TSScheduleJobEntity scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(TSScheduleJobEntity scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 更新定时任务的触发表达式
	 * 
	 * @param triggerName
	 *            触发器名字
	 * @param start
	 *            触发表达式
	 * @return 成功则返回true，否则返回false
	 */
	/*public boolean startOrStop(String triggerName, boolean start) {
		try {
			// CronTrigger trigger = (CronTrigger) getTrigger(triggerName,
			// Scheduler.DEFAULT_GROUP);
			if (start) {
				schedulerFactory.resumeTrigger(new TriggerKey(triggerName));
				logger.info("trigger the start successfully!!");
			} else {
				schedulerFactory.pauseTrigger(new TriggerKey(triggerName));
				logger.info("trigger the pause successfully!!");
			}
			return true;
		} catch (SchedulerException e) {
			logger.error("Fail to reschedule. " + e);
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return false;
		}
	}*/

	/**
	 * 更新定时任务的触发表达式
	 * 
	 * @param triggerName
	 *            触发器名字
	 * @param cronExpression
	 *            触发表达式
	 * @return 成功则返回true，否则返回false
	 */
	/*public boolean updateCronExpression(TSTimeTaskEntity timeTask) {
		try {
			Set<TriggerKey>  triggers = this.schedulerFactory.getTriggerKeys(GroupMatcher.anyTriggerGroup());
			boolean existtrig=false;
			for (TriggerKey trigg : triggers) {
				if(trigg.getName().equals(timeTask.getTaskId())){
					existtrig=true;
				}
			}
			if(!existtrig){//没有trigger时，以taskDemoServiceTaskJob为模板新创建
				JobDetailImpl tjd = (JobDetailImpl) this.schedulerFactory
						.getJobDetail(new JobKey("taskDemoServiceTaskJob",
								"test"));
				JobDetailImpl jobDetail = (JobDetailImpl)	JobBuilder
			      .newJob(StatefulMethodInvokingJob.class)
			      .withIdentity(timeTask.getTaskId(),
			        timeTask.getTaskId()).build();
	
				System.out.println();
				if (null != tjd) {
					// 处理获取到的job
					JobDataMap jobDataMap=new JobDataMap();
					MethodInvokingBean md=new MethodInvokingBean();
//					md.setTargetObject(timeTask.getTargetObject());
					md.setTargetMethod(timeTask.getTargetMethod());
					
					Object o=SpringUtils.getBean(timeTask.getTargetObject() );
					System.out.println( o.getClass() );
					md.setTargetClass(o.getClass());
					//md.setConcurrent(true);
					jobDataMap.put("methodInvoker", md);
//					MethodInvokingJobDetailFactoryBean to=(MethodInvokingJobDetailFactoryBean) tjd.getJobDataMap().get("methodInvoker");
//					to.setTargetObject(ApplicationContextUtil.getContext().getBean(timeTask.getTargetObject()).getClass().getName());
//					to.setTargetMethod(timeTask.getTargetMethod());
//					to.setConcurrent(true);
					jobDetail.setJobDataMap(jobDataMap);
					
			//		handleJobDetail(jobDetail, timeTask);
				
					
					 CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						      .cronSchedule(timeTask.getCronExpression());
						    // 按新的表达式构建一个新的trigger
					 CronTriggerImpl   cronTrigger =(CronTriggerImpl) TriggerBuilder
						      .newTrigger()
						      .withIdentity(timeTask.getTaskId(),
						        timeTask.getTaskId())
						      .withSchedule(scheduleBuilder).build();
					
				//	cronTrigger.setJobName(jobDetail.getKey().getName());
//					 this.schedulerFactory.rescheduleJob(
//								new TriggerKey(timeTask.getTaskId()), cronTrigger);
				    this.schedulerFactory.scheduleJob(jobDetail,cronTrigger);
					System.out.println(schedulerFactory.getTriggerKeys(GroupMatcher.anyTriggerGroup()));
					creatSpringMvcTaskXML(schedulerFactory,jobDetail, cronTrigger);
				}
				return false;

			}else{//对已有Trigger更新
				for (TriggerKey trigg : triggers) {
					if(trigg.getName().equals(timeTask.getTaskId())){
						this.schedulerFactory.unscheduleJob(new TriggerKey(timeTask.getTaskId(),timeTask.getTaskId()));
					}
				}
				
				
			}
			
			CronTriggerImpl trigger = (CronTriggerImpl) getTrigger(
					timeTask.getTaskId(), Scheduler.DEFAULT_GROUP);
			if (trigger == null) {
				
				
				
				
				
				
				
		
				return false;
			}
			if (StringUtils.equals(trigger.getCronExpression(),
					timeTask.getCronExpression())) {
				logger.info("cronExpression is same with the running Schedule , no need to update.");
				return true;
			}
			trigger.setCronExpression(timeTask.getCronExpression());
			schedulerFactory.rescheduleJob(
					new TriggerKey(timeTask.getTaskId()), trigger);
			updateSpringMvcTaskXML(trigger, timeTask.getCronExpression());
			logger.info("Update the cronExpression successfully!!");
			return true;
		} catch (ParseException e) {
			logger.error("The new cronExpression - "
					+ timeTask.getCronExpression()
					+ " not conform to the standard. " + e);
			return false;
		} catch (SchedulerException e) {
			logger.error("Fail to reschedule. " + e);
			return false;
		} 
	}*/

	protected void handleJobDetail(JobDetailImpl jd, TSTimeTaskEntity task) {
		jd.setDescription(jd.getName());
		jd.setName(UUID.randomUUID().toString());
		// 获取实际调度对象
		Object targetObject = jd.getJobDataMap().get("targetObject");
		if(null!=targetObject){
		Object cloneTargetObject;
		try {
			cloneTargetObject = MethodUtils.invokeMethod(targetObject, "clone",
					null);
			PropertyUtils.setProperty(cloneTargetObject, "triggerName", task);
			jd.getJobDataMap().put("targetObject", cloneTargetObject);
		} catch (NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	/**
	 * 获取触发器
	 * 
	 * @param triggerName
	 *            触发器名字
	 * @param groupName
	 *            触发器组名字
	 * @return 对应Trigger
	 */
/*//	private Trigger getTrigger(String triggerName, String groupName) {
//		Trigger trigger = null;
//		if (StringUtils.isBlank(groupName)) {
//			logger.warn("Schedule Job Group is empty!");
//			return null;
//		}
//		if (StringUtils.isBlank(triggerName)) {
//			logger.warn("Schedule trigger Name is empty!");
//			return null;
//		}
//		try {
//			trigger = schedulerFactory.getTrigger(TriggerKey.triggerKey(
//					triggerName, groupName));
//		} catch (SchedulerException e) {
//			logger.warn("Fail to get the trigger (triggerName: " + triggerName
//					+ ", groupName : " + groupName + ")");
//			logger.error(ExceptionUtil.getExceptionMessage(e));
//			return null;
//		}
//		if (trigger == null) {
//			logger.warn("Can not found the trigger of triggerName: "
//					+ triggerName + ", groupName : " + groupName);
//		}
//		return trigger;
//	}
*/
	/**
	 * 更新spring-mvc-timeTask.xml 配置文件
	 * 
	 * @param trigger
	 * @param cronExpression
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void updateSpringMvcTaskXML(
			CronTriggerImpl trigger, String cronExpression) {
		Document document = null;
		File file = null;
		SAXReader saxReader = new SAXReader();
		try {
			URI url = DynamicTask.class.getClassLoader()
					.getResource("spring-mvc-timeTask.xml").toURI();
			file = new File(url.getPath());
			document = saxReader.read(new FileInputStream(file));
		} catch (Exception e) {
			logger.error("读取系统中用到的SQL 语句XML出错");
			throw new RuntimeException(
					"---------读取spring-mvc-timeTask.xml文件出错:" + e.getMessage());
		}
		Element root = document.getRootElement();
		List<Element> beans = root.elements();
		for (Element bean : beans) {
			if (bean.attribute("id") != null
					&& bean.attribute("id").getValue()
							.equals(trigger.getName())) {// 20150919
				beans = bean.elements();
				for (Element temp : beans) {
					if (temp.attribute("name") != null
							&& temp.attribute("name").getValue()
									.equals("cronExpression")) {
						temp.attribute("value").setValue(cronExpression);
						break;
					}
				}
				break;
			}
		}
		XMLWriter fileWriter = null;
		try {
			OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
			xmlFormat.setEncoding("utf-8");
			fileWriter = new XMLWriter(new FileOutputStream(file), xmlFormat);
			fileWriter.write(document);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
		}

	}
	@SuppressWarnings("unchecked")
	public synchronized static void creatSpringMvcTaskXML(
			Scheduler schedulerFactory, JobDetailImpl jd, CronTriggerImpl cronTrigger) {
		Document document = null;
		File file = null;
		SAXReader saxReader = new SAXReader();
		try {
			URI url = DynamicTask.class.getClassLoader()
					.getResource("spring-mvc-timeTask.xml").toURI();
			file = new File(url.getPath());
			document = saxReader.read(new FileInputStream(file));
		} catch (Exception e) {
			logger.error("读取系统中用到的SQL 语句XML出错");
			throw new RuntimeException(
					"---------读取spring-mvc-timeTask.xml文件出错:" + e.getMessage());
		}
		Element root = document.getRootElement();
		List<Element> beans = root.elements();
		for (Element bean : beans) {
		 String schedulerName;
		try {
			schedulerName = schedulerFactory.getSchedulerName();
		     
			
		 if (bean.attribute("id") != null
					&& bean.attribute("id").getValue()
					.equals(schedulerName)) {
			
			  System.out.println("");
				 bean.element("property").element("list").addElement("ref").addAttribute("bean", cronTrigger.getName());
			 
			 
			 
		 }
//			if (bean.attribute("id") != null
//					&& bean.attribute("id").getValue()
//					.equals(cronTrigger.getName())) {// 20150919
//				break;
//			}
		 
		 
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		XMLWriter fileWriter = null;
		try {
			OutputFormat xmlFormat = OutputFormat.createPrettyPrint();
			xmlFormat.setEncoding("utf-8");
			fileWriter = new XMLWriter(new FileOutputStream(file), xmlFormat);
			fileWriter.write(document);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
		}
		
	}

}
