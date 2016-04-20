package com.ysfaltn.cronmanager;

import org.quartz.JobDetail;
import org.quartz.Trigger;

public class Scheduler 
{
	private Scheduler scheduler;
	private JobDetail detail;
	private Trigger trigger;

	public Scheduler getScheduler() 
	{
		return scheduler;
	}
	
	public void setScheduler(Scheduler scheduler) 
	{
		this.scheduler = scheduler;
	}
	
	public JobDetail getDetail() 
	{
		return detail;
	}
	
	public void setDetail(JobDetail detail) 
	{
		this.detail = detail;
	}
	
	public Trigger getTrigger() 
	{
		return trigger;
	}
	
	public void setTrigger(Trigger trigger)
	{
		this.trigger = trigger;
	}
	
	
}
