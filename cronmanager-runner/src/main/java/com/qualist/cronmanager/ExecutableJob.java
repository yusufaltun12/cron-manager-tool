package com.qualist.cronmanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qualist.cronmanager.entity.JobEntity;
import com.qualist.cronmanager.entity.ParamEntity;
import com.qualist.cronmanager.entity.ResultEntity;
import com.qualist.cronmanager.repository.ResultRepository;

/**
 * Created by Yusuf on 8.4.2016.
 */
public class ExecutableJob implements Job
{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
    	JobEntity jobModel = (JobEntity)jobExecutionContext.getJobDetail().getJobDataMap().get("jobentity");
    	List<ParamEntity> params = (List<ParamEntity>)jobExecutionContext.getJobDetail().getJobDataMap().get("jobparams");
    	ResultRepository resultRepository = Context.getInstance().getResultRepository();
    	List<String> commandsAndParams = new ArrayList<>();
    	commandsAndParams.add("java");
    	commandsAndParams.add("-jar");
    	commandsAndParams.add(jobModel.getExecutableFilePath());
    	for (ParamEntity paramEntity : params) 
    	{
    		commandsAndParams.add(paramEntity.getParam());
		}
    	
    	ProcessBuilder pb = new ProcessBuilder();
    	pb.command(commandsAndParams);
    	ResultEntity result = new ResultEntity();
    	result.setJob(jobModel);
    	
    	pb.redirectErrorStream(true);
    	try
    	{
    		Process p = pb.start();
        	InputStream is = p.getInputStream();
        	BufferedReader br = new BufferedReader(new InputStreamReader(is));
        	StringBuilder output = new StringBuilder();
        	for (String line = br.readLine(); line != null; line = br.readLine()) 
        	{
        		output.append(line).append("\n");
        	}
        	result.setDescription(output.toString());
        	p.waitFor();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	result.setCreatedAt(LocalDateTime.now());
    	resultRepository.save(result);
    }
}
