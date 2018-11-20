package com.dpconsole.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.dao.DefaultDao;
import com.dpconsole.service.AdminService;
import com.dpconsole.service.Configuration;

@Controller
@SessionAttributes("homePage")
public class HomePageController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);
	
	public static final String Header_Logo_Dir_Path = Configuration.Header_Logo_Dir_Path;
	
	public static final String Principal_Photo_Dir_Path = Configuration.Principal_Photo_Dir_Path;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DefaultDao defaultDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage(Model model) throws Exception{
		try {
			return "home/home";
		} catch(Exception e) {
			logger.error("Unable to load Home page.", e);
			throw e;
		}
	}
}
