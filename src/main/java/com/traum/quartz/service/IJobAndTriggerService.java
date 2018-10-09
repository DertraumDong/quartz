package com.traum.quartz.service;

import com.traum.quartz.entity.JobAndTriggerDto;

import java.util.Map;


/**
* @version V1.0  
*/ 
public interface IJobAndTriggerService {
	/**  
	* @Title: getPageJob  
	* @Description: 查询定时任务，分页)
	* @param @param jobAndTriggerDto
	* @param @return    参数  
	* @return Map<String,Object>    返回类型  
	* @throws  
	*/ 
	public Map<String, Object> getPageJob(JobAndTriggerDto jobAndTriggerDto);
	
	/**  
	* @Title: getPageJobmod  
	* @Description: 查询定时任务
	* @param @return    参数  
	* @return JobAndTriggerDto    返回类型  
	* @throws  
	*/ 
	public JobAndTriggerDto getPageJobmod();
	
	/**  
	* @Title: addJob  
	* @Description: 添加任务
	* @param @param jobClassName 任务路径名称
	* @param @param jobGroupName 任务分组
	* @param @param cronExpression cron时间规则
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	*/ 
	public void addJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
	
	/**  
	* @Title: updateJob  
	* @Description: 更新定时任务
	* @param @param jobClassName 任务路径名称
	* @param @param jobGroupName 任务分组
	* @param @param cronExpression cron时间规则
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	*/ 
	public void updateJob(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
	
	/**  
	* @Title: deleteJob  
	* @Description: 删除定时任务
	* @param @param jobClassName 任务路径名称
	* @param @param jobGroupName 任务分组
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	*/ 
	public void deleteJob(String jobClassName, String jobGroupName) throws Exception;
	
	/**  
	* @Title: pauseJob  
	* @Description: 暂停定时任务
	* @param @param jobClassName 任务路径名称
	* @param @param jobGroupName 任务分组
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	*/ 
	public void pauseJob(String jobClassName, String jobGroupName) throws Exception;
	
	/**  
	* @Title: resumejob  
	* @Description: 恢复任务
	* @param @param jobClassName 任务路径名称
	* @param @param jobGroupName 任务分组
	* @param @throws Exception    参数  
	* @return void    返回类型  
	* @throws  
	*/ 
	public void resumejob(String jobClassName, String jobGroupName) throws Exception;
}
