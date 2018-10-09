package com.traum.quartz.config;

import com.traum.quartz.QuartzApplication;
import com.traum.quartz.util.AnnotationUtil;
import com.traum.quartz.util.ClassUtil;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;
import java.util.Set;

@Configuration
public class SchedulerConfig implements SchedulerFactoryBeanCustomizer {

    public static Set<?> jobAnnotationSet;

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setStartupDelay(2);
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setOverwriteExistingJobs(true);
    }

    static {
        // 获取特定包下所有的类(包括接口和类)
        List<Class<?>> clsList = ClassUtil.getAllClassByPackageName(QuartzApplication.class.getPackage());
        //输出所有使用了特定注解的类的注解值
        jobAnnotationSet = AnnotationUtil.validAnnotation(clsList);
    }
}
