package com.qualist.cronmanager.model;

import com.qualist.cronmanager.entity.ResultEntity;

public class ResultDTO 
{
	private int id;
	private String description;
	private String date;
	public static ResultDTO from(ResultEntity entity)
	{
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.id = entity.getId();
		resultDTO.description = entity.getDescription();
		resultDTO.date = entity.getCreatedAt().toString();
		
		return resultDTO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
