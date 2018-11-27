package com.dpconsole.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.parsers.Parser;
import com.dpconsole.parsers.ZomatoParser;
import com.dpconsole.service.KitchenService;
import com.dpconsole.utils.MailService;
import com.dpconsole.utils.Utils;


/**
 * @author SHABARINATH
 * 25-Nov-2018 8:04:22 PM 2018 
 */

@Controller
@SessionAttributes("gmailController")
public class ZomatoGMAILController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZomatoGMAILController.class);
	
	@Autowired
	KitchenService kitchenService;
	
	@RequestMapping(value = "/zomatoGmailAuto", method = RequestMethod.GET)
	public String loadPage(Model model) throws Exception{
		try {
			return "parser/gmailAuto";
		} catch(Exception e) {
			logger.error("Unable to load Gmail Auto Page!! .", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/processZomatoOrders", method = RequestMethod.GET)
	public String dashboard(Model model,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) throws Exception{
		try {
			if (!validateData(startTime, endTime)) {
				System.out.println("Failed");
				model.addAttribute("error", "check form !!");
				return "parser/gmailAuto";
			}
			Parser<List<Message>> zomatoParser = new ZomatoParser();
			Long kitchenId = 1L;
			MailService mailService = new MailService();
			String[] dpEmails = new String[2];
			dpEmails[0]="volamshabarinath@gmail.com";
			dpEmails[1]="noreply@zomato.com";
			Date startDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", startTime);
			Date endDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", endTime);
			Message[] messages = mailService.getMessagesWithCriteria("Kitchensofchina@gmail.com", "koc654321", dpEmails, "", startDate, endDate);
			Map<String, KitchenItem> kitchenItems = kitchenService.getAllKitchenItems(kitchenId);
			Kitchen kitchen = kitchenService.getKitchenById(kitchenId);
			zomatoParser.parse(kitchen, kitchenItems, Arrays.asList(messages));
			System.out.println("startTime: "+startTime);
			System.out.println("endTime: "+endTime);
			return "parser/gmailAuto";
		} catch(Exception e) {
			logger.error("Unable to load Gmail Auto Page!! .", e);
			throw e;
		}
	}

	private boolean validateData(String startTime, String endTime) throws ParseException {
		System.out.println("Inside validation");
		if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			return false;
		}
		Date startDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", startTime);
		Date endDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", endTime);
		if(startDate.after(endDate)) {
			return false;
		}
		return true;
	}

}

