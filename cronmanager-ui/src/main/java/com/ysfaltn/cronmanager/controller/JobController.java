package com.ysfaltn.cronmanager.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ParamEntity;
import com.ysfaltn.cronmanager.entity.ResultEntity;
import com.ysfaltn.cronmanager.model.JobDTO;
import com.ysfaltn.cronmanager.model.ResultDTO;
import com.ysfaltn.cronmanager.repository.JobRepository;
import com.ysfaltn.cronmanager.repository.ParamRepository;
import com.ysfaltn.cronmanager.repository.ResultRepository;
import com.ysfaltn.cronmanager.util.Config;

@Controller
@RequestMapping(value = "/job")
public class JobController
{
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private ParamRepository paramRepository;

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

	@RequestMapping(value = "/create-job", method = RequestMethod.POST)
	public String createJob(@RequestParam("job-name") String jobName,
			@RequestParam("period") String period,
			@RequestParam("executable-file") MultipartFile executableFile,
			@RequestParam("external-files") List<MultipartFile> externalFiles,
			@RequestParam("args") String args) throws IllegalStateException, IOException
	{
		JobEntity jobEntity = new JobEntity();
		List<ParamEntity> paramEntities = new ArrayList<ParamEntity>();

		String fullFilePath = uploadFile(executableFile, jobName);
		String extention = fullFilePath.split(Pattern.quote("."))[fullFilePath.split(Pattern.quote(".")).length - 1];
		jobEntity.setExecutableFilePath(fullFilePath);
		jobEntity.setFileType(extention.toUpperCase());
		jobEntity.setGroupName(jobName);
		jobEntity.setJobName(jobName);
		jobEntity.setSchedule(period);

		jobEntity = jobRepository.save(jobEntity);
		System.err.println(jobEntity.getId());
		String[] argsSplitted = args.split(" ");
		for (String string : argsSplitted)
		{
			ParamEntity paramEntity = new ParamEntity();
			paramEntity.setJob(jobEntity);
			paramEntity.setParam(string);
			paramEntities.add(paramEntity);
		}

		for (MultipartFile file : externalFiles)
		{
			uploadFile(file, jobName);
		}

		paramRepository.save(paramEntities);
		return "redirect:/";
	}

	public static String uploadFile(MultipartFile file, String jobName) throws IllegalStateException, IOException
	{
		String fileName = file.getOriginalFilename();
		String fileNameWithDirectoryPath = null;
		if (!"".equalsIgnoreCase(fileName))
		{
			String fullPath = Config.filePath + "/" + jobName + "/";
			File workSpaceDirectory = new File(Config.filePath + jobName);
			if (!workSpaceDirectory.exists())
			{
				workSpaceDirectory.mkdir();
			}

			fileNameWithDirectoryPath = fullPath + fileName;

			file.transferTo(new File(fileNameWithDirectoryPath));
		}

		return fileNameWithDirectoryPath;
	}
}
