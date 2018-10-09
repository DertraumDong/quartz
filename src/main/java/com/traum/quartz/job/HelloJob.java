package com.traum.quartz.job;

import com.traum.quartz.annotation.JobAnnotation;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

@JobAnnotation
public class HelloJob implements BaseJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("Hello Job 运行成功！" + new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date()));

    }
}
