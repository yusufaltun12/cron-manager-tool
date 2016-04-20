package com.qualist.cronmanager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualist.cronmanager.entity.JobEntity;
import com.qualist.cronmanager.entity.ResultEntity;
import com.qualist.cronmanager.model.JobDTO;
import com.qualist.cronmanager.model.ResultDTO;
import com.qualist.cronmanager.repository.JobRepository;
import com.qualist.cronmanager.repository.ResultRepository;

@Controller
@RequestMapping(value = "/job")
public class JobController 
{
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ResultRepository resultRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<JobDTO> getJobs(HttpServletRequest req, HttpServletResponse resp)
	{
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
		List<JobDTO> jobs = new ArrayList<JobDTO>();
		Iterable<JobEntity> jobEntities = jobRepository.findAll();
		
		for (JobEntity jobEntity : jobEntities) 
		{
			List<ResultEntity> results = new ArrayList<ResultEntity>();
			Iterable<ResultEntity> resultEntites = resultRepository.findByJob(jobEntity);
			for (ResultEntity resultEntity : resultEntites) 
			{
				results.add(resultEntity);
			}
			jobs.add(JobDTO.from(jobEntity, results));
		}
		
		return jobs;
	}
	
	@RequestMapping(value = "/result/{job_id:\\d+}", method = RequestMethod.GET)
	@ResponseBody
	public List<ResultDTO> getResults(HttpServletRequest req, HttpServletResponse resp, @PathVariable("job_id") Integer jobId)
	{
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		resp.setHeader("Access-Control-Max-Age", "3600");
		resp.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));
		JobEntity job = new JobEntity();
		job.setId(jobId);
		
		Iterable<ResultEntity> resulteEntites = resultRepository.findByJob(job);
		List<ResultDTO> results = new ArrayList<ResultDTO>();
		for (ResultEntity resultEntity : resulteEntites) 
		{
			results.add(ResultDTO.from(resultEntity));
		}
		
		return results;
	}
}
