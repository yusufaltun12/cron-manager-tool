package com.qualist.cronmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MainController 
{
	@RequestMapping(method = RequestMethod.GET)
	public String goMainPage()
	{
		return "index";
	}
	
	@RequestMapping(value = "job-detail", method = RequestMethod.GET)
	public String goJobDetail()
	{
		return "job-detail";
	}
}
