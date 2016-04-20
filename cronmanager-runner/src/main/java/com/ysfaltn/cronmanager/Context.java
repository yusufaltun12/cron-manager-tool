package com.ysfaltn.cronmanager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ysfaltn.cronmanager.repository.JobRepository;
import com.ysfaltn.cronmanager.repository.ParamRepository;
import com.ysfaltn.cronmanager.repository.ResultRepository;

public class Context 
{
	private static final Context context = null;
	private ApplicationContext applicationContext;
	private JobRepository jobRepository;
	private ParamRepository paramRepository;
	private ResultRepository resultRepository;
	
	private Context()
	{
		applicationContext = new ClassPathXmlApplicationContext("file:src/main/resources/spring.xml");
	}
	
	public static Context getInstance()
	{
		if(context == null)
		{
			return new Context();
		}
		return context;
	}
	
	public JobRepository getJobRepository()
	{
		if(jobRepository == null)
		{
			jobRepository = applicationContext.getBean(JobRepository.class);
		}
		return jobRepository;
	}
	
	public ParamRepository getParamRepository()
	{
		if(paramRepository == null)
		{
			paramRepository = applicationContext.getBean(ParamRepository.class);
		}
		return paramRepository;
	}
	
	public ResultRepository getResultRepository()
	{
		if(resultRepository == null)
		{
			resultRepository = applicationContext.getBean(ResultRepository.class);
		}
		return resultRepository;
	}
}
