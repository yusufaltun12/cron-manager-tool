package com.qualist.cronmanager;

import java.util.HashMap;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.qualist.cronmanager.entity.JobEntity;
import com.qualist.cronmanager.entity.ParamEntity;

public class SchedulerBuilder 
{
	public static Scheduler build(JobEntity jobEntity, List<ParamEntity> paramEntities)
	{
		Scheduler schedulerModel = new Scheduler();
		HashMap<String, Object> jobData = new HashMap<>();
		jobData.put("jobentity", jobEntity);
		jobData.put("jobparams", paramEntities);
		
		JobDataMap jobDataMap1 = new JobDataMap(jobData);
        JobKey jobKeyA1 = new JobKey(jobEntity.getJobName(), jobEntity.getGroupName());
        JobDetail jobA1 = JobBuilder.newJob(ExecutableJob.class).usingJobData(jobDataMap1)
                .withIdentity(jobKeyA1).build();

        Trigger trigger1 = TriggerBuilder
                .newTrigger()
                .withIdentity(jobEntity.getJobName()+jobEntity.getGroupName(), jobEntity.getGroupName())
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(jobEntity.getSchedule()))
                .build();
        
        schedulerModel.setDetail(jobA1);
        schedulerModel.setTrigger(trigger1);
        
        return schedulerModel;
	}
}
