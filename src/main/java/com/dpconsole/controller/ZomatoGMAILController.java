package com.dpconsole.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenDeliveryPartner;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;
import com.dpconsole.parsers.ZomatoParser;
import com.dpconsole.service.KitchenService;
import com.dpconsole.service.OrderService;
import com.dpconsole.utils.MailService;
import com.dpconsole.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


/**
 * @author SHABARINATH
 * 25-Nov-2018 8:04:22 PM 2018 
 */

@Controller
@SessionAttributes("gmailController")
public class ZomatoGMAILController {
	
	private static final Logger logger = LoggerFactory.getLogger(ZomatoGMAILController.class);
	
	private static final String INPUT_DATE_FORMAT="yyyy-MM-dd HH:mm";
	
	@Autowired
	KitchenService kitchenService;
	
	@Autowired
	OrderService orderService;
	
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
	@ResponseBody
	public String dashboard(
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) throws Exception{
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ResponseObj obj = new ResponseObj();
			if (validateFormDataData(startTime, endTime, obj)) {
				String json =  ow.writeValueAsString(obj);
				return json;
			}
			ZomatoParser zomatoParser = new ZomatoParser();
			Long kitchenId = 1L; //TODO: Fetch this from UI
			MailService mailService = new MailService();
			Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
			Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);
			Kitchen kitchen = kitchenService.getKitchenById(kitchenId);
			String userName = kitchen.getMailBoxUserName();
			String password = kitchen.getMailBoxPassword();
			KitchenDeliveryPartner kdp = kitchen.getSupportedDeliveryPartner(DeliveryPartner.ZOMATO);
			String dpEmailIds = (kdp!=null)?kdp.getEmailIds():null;
			if(!StringUtils.isNoneEmpty(userName, password, dpEmailIds)) {
				String json = ow.writeValueAsString(new ResponseObj("Configuration Error: UserName, Password or EmailIds are not configured!!", true));
				return json;
			}
			Message[] messages = mailService.getMessagesWithCriteria(userName, password, Arrays.asList(dpEmailIds.split(",")), "", startDate, endDate);
			Map<String, KitchenItem> kitchenItems = kitchenService.getAllKitchenItems(kitchenId);
			List<Order> orders = zomatoParser.parse(kitchen, kitchenItems, Arrays.asList(messages));
			for(Order order: orders) {
				try {
					orderService.saveOrUpdateOrder(order);
				} catch(DataIntegrityViolationException  e) {
					logger.error("Duplicate processing of orders!!!");
					return ow.writeValueAsString(new ResponseObj("Duplicate processing of orders!!!", true));
				}
			}
			return ow.writeValueAsString(new ResponseObj("Processed "+messages.length+" Zomato orders!!", false));
		} catch(Exception e) {
			logger.error("Unable to load Gmail Auto Page!! .", e);
			throw e;
		}
	}

	private boolean validateFormDataData(String startTime, String endTime, ResponseObj responseObj) throws JsonProcessingException, ParseException, java.text.ParseException {
		if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)) {
			responseObj.setStatus("Input cannot be empty!!");
			responseObj.setError(true);
			return true;
		}
		Date startDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", startTime);
		Date endDate = Utils.convertStringToDate("yyyy-MM-dd HH:mm", endTime);
		if(startDate.after(endDate)) {
			responseObj.setStatus("Error: EndDate should be greater than StartDate ");
			responseObj.setError(true);
			return true;
		}
		return false;
	}
	
	class ResponseObj implements Serializable {
		private static final long serialVersionUID = -7539197598047102123L;
		String status;
		boolean isError;
		public ResponseObj() {
		
		}
		public ResponseObj(String response, boolean isError) {
			super();
			this.status = response;
			this.isError = isError;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public boolean isError() {
			return isError;
		}
		public void setError(boolean isError) {
			this.isError = isError;
		}
	}
}
