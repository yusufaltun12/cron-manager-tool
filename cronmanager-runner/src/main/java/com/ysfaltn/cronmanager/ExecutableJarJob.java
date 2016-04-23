package com.ysfaltn.cronmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ParamEntity;
import com.ysfaltn.cronmanager.entity.ResultEntity;
import com.ysfaltn.cronmanager.repository.ResultRepository;

/**
 * Created by Yusuf on 8.4.2016.
 */
public class ExecutableJarJob extends Observable implements Job
{
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
	{
		JobEntity jobModel = (JobEntity) jobExecutionContext.getJobDetail().getJobDataMap().get("jobentity");
		@SuppressWarnings("unchecked")
		List<ParamEntity> params = (List<ParamEntity>) jobExecutionContext.getJobDetail().getJobDataMap().get("jobparams");
		ResultRepository resultRepository = Context.getInstance().getResultRepository();

		List<String> commandsAndParams = new ArrayList<>();
		commandsAndParams.add("java");
		commandsAndParams.add("-jar");
		commandsAndParams.add(jobModel.getExecutableFilePath());
		for (ParamEntity paramEntity : params)
		{
			commandsAndParams.add(paramEntity.getParam());
		}

		ResultEntity result = Processor.process(commandsAndParams);
		result.setJob(jobModel);
		resultRepository.save(result);
	}
}
