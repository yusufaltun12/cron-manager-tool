package com.ysfaltn.cronmanager.model;

import com.ysfaltn.cronmanager.entity.ResultEntity;

public class ResultDTO
{
	private int id;
	private String description;
	private String date;
	private ResultStatus resultStatus;

	public static ResultDTO from(ResultEntity entity)
	{
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.id = entity.getId();
		resultDTO.description = entity.getDescription();
		resultDTO.date = entity.getCreatedAt().toString();
		resultDTO.resultStatus = ResultStatus.valueOf(entity.getStatus());

		return resultDTO;
	}

	public ResultStatus getResultStatus()
	{
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus)
	{
		this.resultStatus = resultStatus;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}
}
