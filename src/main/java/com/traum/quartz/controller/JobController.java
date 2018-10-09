package com.traum.quartz.controller;

import com.traum.quartz.config.SchedulerConfig;
import com.traum.quartz.entity.JobAndTriggerDto;
import com.traum.quartz.entity.ResultBody;
import com.traum.quartz.service.IJobAndTriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 *
 * @ClassName: JobController
 * @Description:
 * @version V1.0
*/ 
@Controller
@RequestMapping(value = "/job")
public class JobController{
	@Autowired
	private IJobAndTriggerService jobAndTriggerService;

	private static Logger log = LoggerFactory.getLogger(JobController.class);

	@RequestMapping(value = "/datagrid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryjob(JobAndTriggerDto jobAndTriggerDto, HttpServletRequest request, HttpServletResponse response) {
		log.debug("queryjob");
		return jobAndTriggerService.getPageJob(jobAndTriggerDto);
	}
	
	/**
	 * @Title: addJob
	 * @Description: 添加Job
	 * @param jobClassName
	 *            类名
	 * @param jobGroupName
	 *            组名
	 * @param cronExpression
	 *            表达式，如：0/5 * * * * ? (每隔5秒)
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody
	Object addJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName, 
			@RequestParam(value = "cronExpression") String cronExpression,
			HttpServletResponse response){
		try {
			jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
			return ResultBody.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBody.fail();
		}
	}

	/**
	 * @Title: pauseJob
	 * @Description: 暂停Job
	 * @param jobClassName
	 *            类名
	 * @param jobGroupName
	 *            组名
	 */
	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	public @ResponseBody Object pauseJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			jobAndTriggerService.pauseJob(jobClassName, jobGroupName);
			return ResultBody.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBody.fail();
		}
	}

	/**
	 * @Title: resumeJob
	 * @Description: 恢复Job
	 * @param jobClassName
	 *            类名
	 * @param jobGroupName
	 *            组名
	 */
	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public @ResponseBody Object resumeJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			jobAndTriggerService.resumejob(jobClassName, jobGroupName);
			return ResultBody.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBody.fail();
		}
	}

	/**
	 * @Title: rescheduleJob
	 * @Description: 重新设置Job
	 * @param jobClassName
	 *            类名
	 * @param jobGroupName
	 *            组名
	 * @param cronExpression
	 *            表达式
	 */
	@RequestMapping(value = "/reschedule", method = RequestMethod.POST)
	public @ResponseBody Object rescheduleJob(
			@RequestParam(value = "jobClassName") String jobClassName, 
			@RequestParam(value = "jobGroupName") String jobGroupName, 
			@RequestParam(value = "cronExpression") String cronExpression,
			HttpServletResponse response) {
		try {
			jobAndTriggerService.updateJob(jobClassName, jobGroupName, cronExpression);
			return ResultBody.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBody.fail();
		}
	}

	/**
	 * @Title: deleteJob
	 * @Description: 删除Job
	 * @param jobClassName
	 *            类名
	 * @param jobGroupName
	 *            组名
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public @ResponseBody Object deleteJob(@RequestParam(value = "jobClassName") String jobClassName, @RequestParam(value = "jobGroupName") String jobGroupName,
			HttpServletResponse response) {
		try {
			jobAndTriggerService.deleteJob(jobClassName, jobGroupName);
			return ResultBody.success();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultBody.fail();
		}
	}


	/**
	 * 查询标注了@JobAnnotation的Job类
	 * 此类已在工程启动时就静态加载完毕
	 * @return jobAnnotationSet （静态类）
	 */
	@RequestMapping(value = "/findClass", method = RequestMethod.POST)
	public @ResponseBody Object findClass(){
		return SchedulerConfig.jobAnnotationSet;
	}

}
