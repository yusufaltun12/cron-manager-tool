package com.ysfaltn.cronmanager.model;

import java.util.List;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ResultEntity;

public class JobDTO
{
	private int id;
	private String jobName;
	private String lastDate;
	private String executableFilePath;
	private String period;
	private List<ResultEntity> results;

	public static JobDTO from(JobEntity entity, List<ResultEntity> results)
	{
		JobDTO jobDTO = new JobDTO();
		jobDTO.id = entity.getId();
		jobDTO.jobName = entity.getJobName();
		jobDTO.executableFilePath = entity.getExecutableFilePath();
		jobDTO.results = results;
		jobDTO.period = entity.getSchedule();
		return jobDTO;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getJobName()
	{
		return jobName;
	}

	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getLastDate()
	{
		return lastDate;
	}

	public void setLastDate(String lastDate)
	{
		this.lastDate = lastDate;
	}

	public String getExecutableFilePath()
	{
		return executableFilePath;
	}

	public void setExecutableFilePath(String executableFilePath)
	{
		this.executableFilePath = executableFilePath;
	}

	public List<ResultEntity> getResults()
	{
		return results;
	}

	public void setResults(List<ResultEntity> results)
	{
		this.results = results;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setPeriod(String period)
	{
		this.period = period;
	}
}
