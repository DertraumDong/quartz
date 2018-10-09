package com.traum.quartz.util;

import com.traum.quartz.annotation.JobAnnotation;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AnnotationUtil {

    /**
     * 匹配查找配置了相应注解的类
     * @param clsList 根据系统启动项的类指引的包下所有类
     * @return
     */
    public static Set<String> validAnnotation(List<Class<?>> clsList){
        Set<String> set = new HashSet<String>();
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                JobAnnotation testAnnotation = cls.getAnnotation(JobAnnotation.class);
                if(testAnnotation != null){
                    set.add(cls.getName());
                }
            }
        }
        return set;
    }

}
