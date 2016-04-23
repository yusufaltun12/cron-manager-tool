package com.ysfaltn.cronmanager;

import java.util.HashMap;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ParamEntity;
import com.ysfaltn.cronmanager.model.FileType;

public class SchedulerBuilder
{
	private static HashMap<FileType, Class<? extends Job>> JOBS;
	static
	{
		JOBS = new HashMap<FileType, Class<? extends Job>>();
		JOBS.put(FileType.JAR, ExecutableJarJob.class);
		JOBS.put(FileType.EXE, ExecutableExeJob.class);
		JOBS.put(FileType.SH, ExecutableSHJob.class);
		JOBS.put(FileType.BAT, ExecutableBATJob.class);
	}

	public static Scheduler build(JobEntity jobEntity, List<ParamEntity> paramEntities)
	{
		Scheduler schedulerModel = new Scheduler();
		HashMap<String, Object> jobData = new HashMap<>();
		jobData.put("jobentity", jobEntity);
		jobData.put("jobparams", paramEntities);
		jobData.put("events", paramEntities);

		JobDataMap jobDataMap = new JobDataMap(jobData);
		JobKey jobKey = new JobKey(jobEntity.getJobName(), jobEntity.getGroupName());

		JobDetail jobA = JobBuilder.newJob(JOBS.get(FileType.valueOf(jobEntity.getFileType())))
				.usingJobData(jobDataMap)
				.withIdentity(jobKey).build();

		Trigger trigger1 = TriggerBuilder
				.newTrigger()
				.withIdentity(jobEntity.getJobName() + jobEntity.getGroupName(), jobEntity.getGroupName())
				.withSchedule(
						CronScheduleBuilder.cronSchedule(jobEntity.getSchedule()))
				.build();

		schedulerModel.setDetail(jobA);
		schedulerModel.setTrigger(trigger1);

		return schedulerModel;
	}
}
