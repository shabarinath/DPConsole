package com.dpconsole.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("homePage")
public class HomePageController {

	private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

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