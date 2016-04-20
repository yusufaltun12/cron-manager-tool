package com.qualist.cronmanager;

import java.util.ArrayList;
import java.util.List;

import org.quartz.impl.StdSchedulerFactory;

import com.qualist.cronmanager.entity.JobEntity;
import com.qualist.cronmanager.entity.ParamEntity;

/**
 * Hello world!
 */
public class App
{
	public static void main(String[] args) throws Exception
	{
		Context context = Context.getInstance();
		Iterable<JobEntity> jobEntities = context.getJobRepository().findAll();
		List<Scheduler> schedulers = new ArrayList<Scheduler>();

		for (JobEntity jobEntity : jobEntities)
		{
			List<ParamEntity> paramEntities = context.getParamRepository().findByJob(jobEntity);
			schedulers.add(SchedulerBuilder.build(jobEntity, paramEntities));
		}

		org.quartz.Scheduler schedulerQuartz = new StdSchedulerFactory().getScheduler();
		schedulerQuartz.start();

		for (Scheduler sch : schedulers)
		{
			schedulerQuartz.scheduleJob(sch.getDetail(), sch.getTrigger());
		}
	}
}
