package com.ysfaltn.cronmanager;

import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ParamEntity;
import com.ysfaltn.cronmanager.entity.ResultEntity;
import com.ysfaltn.cronmanager.repository.ResultRepository;

public class ExecutableBATJob implements Job
{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		JobEntity jobModel = (JobEntity) context.getJobDetail().getJobDataMap().get("jobentity");
		@SuppressWarnings("unchecked")
		List<ParamEntity> params = (List<ParamEntity>) context.getJobDetail().getJobDataMap().get("jobparams");
		ResultRepository resultRepository = Context.getInstance().getResultRepository();

		List<String> commandsAndParams = new ArrayList<>();
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
