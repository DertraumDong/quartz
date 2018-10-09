package com.traum.quartz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.traum.quartz.dao.JobAndTriggerMapper;
import com.traum.quartz.entity.JobAndTriggerDto;
import com.traum.quartz.job.BaseJob;
import com.traum.quartz.service.IJobAndTriggerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**  
* @ClassName: JobAndTriggerImpl  
* @Description:
* @version V1.0  
*/ 
@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JobAndTriggerImpl.class);

	@Autowired
	private Scheduler scheduler;
	
	
	@Autowired
	private JobAndTriggerMapper jobAndTriggerMapper;

	@Override
	public Map<String, Object> getPageJob(JobAndTriggerDto jobAndTriggerDto) {
		PageHelper.startPage(jobAndTriggerDto.getPageNumKey(),jobAndTriggerDto.getPageSizeKey());
		List<JobAndTriggerDto> records = jobAndTriggerMapper.getJobAndTriggerDetails(jobAndTriggerDto);
		Map<String,Object> map = new HashMap<>();
		PageInfo<JobAndTriggerDto> jobAndTriggerDtoPageInfo = new PageInfo<>(records);
		map.put("PageInfo",jobAndTriggerDtoPageInfo);
		return map;
	}

	@Override
	public JobAndTriggerDto getPageJobmod() {
		return jobAndTriggerMapper.getJobAndTriggerDto();
	}

	@Override
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		// 启动调度器
				scheduler.start();

				// 构建job信息
				JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass())
						.withIdentity(jobClassName, jobGroupName).build();

				// 表达式调度构建器(即任务执行的时间)
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

				// 按新的cronExpression表达式构建一个新的trigger
				CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
						.withSchedule(scheduleBuilder).build();

				try {
					scheduler.scheduleJob(jobDetail, trigger);
					LOGGER.info("创建定时任务成功");

				} catch (SchedulerException e) {
					LOGGER.error("创建定时任务失败" + e);
					throw new Exception("创建定时任务失败");
				}
		
	}

	@Override
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		} catch (SchedulerException e) {
			LOGGER.error("更新定时任务失败" + e);
			throw new Exception("更新定时任务失败");
		}
	}

	@Override
	public void deleteJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void pauseJob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
	}

	@Override
	public void resumejob(String jobClassName, String jobGroupName) throws Exception {
		scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
	}
	
	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}
	
}