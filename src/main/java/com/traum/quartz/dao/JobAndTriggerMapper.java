package com.traum.quartz.dao;

import com.traum.quartz.entity.JobAndTriggerDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**  
* @ClassName: JobAndTriggerMapper  
* @Description: TODO()  
* @author lixin(1309244704@qq.com)  
* @date 2018年3月15日 上午10:02:48  
* @version V1.0  
*/
@Component
public interface JobAndTriggerMapper {
	
	List<JobAndTriggerDto> getJobAndTriggerDetails(JobAndTriggerDto jobAndTriggerDto);
	
	JobAndTriggerDto getJobAndTriggerDto();
}
