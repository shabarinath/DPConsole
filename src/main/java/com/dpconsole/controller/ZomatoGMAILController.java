package com.dpconsole.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * @author SHABARINATH
 * 25-Nov-2018 8:04:22 PM 2018 
 */

@Controller
@SessionAttributes("gmailController")
public class ZomatoGMAILController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZomatoGMAILController.class);
	
	@RequestMapping(value = "/gmailAuto", method = RequestMethod.GET)
	public String dashboard(Model model) throws Exception{
		try {
			return "parser/gmailAuto";
		} catch(Exception e) {
			logger.error("Unable to load Gmail Auto Page!! .", e);
			throw e;
		}
	}

}

