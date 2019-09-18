package com.bbg.scheduler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;



@Configuration
public class QuartzConfigration {
	
	/** 
     * attention: 
     * Details：配置定时任务 
     */  
    @Bean(name = "jobDetail")  
    public MethodInvokingJobDetailFactoryBean update_LP_REAL_BFXS_HZ_FactoryBean(BhbiScheduler task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();  
        jobDetail.setConcurrent(false);  
        jobDetail.setName("update_LP_REAL_BFXS_HZ");
        jobDetail.setGroup("bhbi");
        jobDetail.setTargetObject(task);  
        jobDetail.setTargetMethod("update_LP_REAL_BFXS_HZ");  
        return jobDetail;  
    }

    @Bean(name = "jobDetail_sendMessage")
    public MethodInvokingJobDetailFactoryBean ckeck_message_FactoryBean(BhbiScheduler task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false);
        jobDetail.setName("ckeck_message");
        jobDetail.setGroup("ckeck_message");
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("ckeck_message");
        return jobDetail;
    }



    /** 
     * attention: 
     * Details：配置定时任务的触发器，也就是什么时候触发执行定时任务 
     */  
    @Bean(name = "jobTrigger")  
    public CronTriggerFactoryBean cronJobTrigger(JobDetail  jobDetail) {  
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();  
        tigger.setJobDetail(jobDetail);  
        tigger.setCronExpression("0 */1 * * * ?");// 初始时的cron表达式  
        tigger.setName("update_LP_REAL_BFXS_HZ_tigger");// trigger的name  
        return tigger;  
  
    }

    @Bean(name = "jobTrigger_sendMessage")
    public CronTriggerFactoryBean cronJobTrigger_sendMessage(JobDetail  jobDetail_sendMessage) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail_sendMessage);
        tigger.setCronExpression("0 0/1 10,11,12,13,14,15,16,17,18,19,20,21,22,23 * * ?");// 初始时的cron表达式
        tigger.setName("sendMessage_tigger");// trigger的name
        return tigger;

    }

    /** 
     * attention: 
     * Details：定义quartz调度工厂 
     */  
    @Bean(name = "scheduler")  
    public SchedulerFactoryBean schedulerFactory(Trigger jobTrigger,Trigger jobTrigger_sendMessage) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();  
        bean.setOverwriteExistingJobs(true);  
        bean.setStartupDelay(20);  
        bean.setTriggers(jobTrigger,jobTrigger_sendMessage);
        //bean.setTriggers(jobTrigger);
        //bean.setTriggers(jobTrigger_sendMessage);
        return bean;  
    }  
 
}