package com.dpconsole.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.dpconsole.parsers.ZomatoCSVParser;
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
	
	private static final String NEW_ONLINE_DELIVERY_EMAIL_SUBJECT="New online order received at";

	@Autowired
	KitchenService kitchenService;

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/zomatoGmailAuto", method = RequestMethod.GET)
	public String loadPage(Model model) throws Exception{
		try {
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("deliveryPartners", DeliveryPartner.values());
			model.addAttribute("headerValue", "Zomato Email Parser");
			return "orders/layout";
		} catch(Exception e) {
			logger.error("Unable to load Gmail Auto Page!! .", e);
			throw e;
		}
	}

	@RequestMapping(value = "/processZomatoOrders", method = RequestMethod.GET)
	@ResponseBody
	public String processOrders(Model model,
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime) throws Exception{
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			ResponseObj obj = new ResponseObj();
			if (validateFormDataData(startTime, endTime, obj, kitchenId)) {
				String json =  ow.writeValueAsString(obj);
				return json;
			}
			ZomatoParser zomatoParser = new ZomatoParser();
			MailService mailService = new MailService();
			Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
			Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);
			Kitchen kitchen = kitchenService.getKitchenById(kitchenId);
			String userName = kitchen.getMailBoxUserName();
			String password = kitchen.getMailBoxPassword();
			String mailBoxFolder = kitchen.getMailBoxFolder();
			KitchenDeliveryPartner kdp = kitchen.getSupportedDeliveryPartner(DeliveryPartner.ZOMATO);
			String dpEmailIds = (kdp!=null)?kdp.getEmailIds():null;
			if(!StringUtils.isNoneEmpty(userName, password, dpEmailIds)) {
				String json = ow.writeValueAsString(new ResponseObj("Configuration Error: UserName, Password or EmailIds are not configured!!", true));
				return json;
			}
			long start = System.currentTimeMillis();
			Message[] messages = mailService.getMessagesWithCriteria(userName, password, Arrays.asList(dpEmailIds.split(",")), NEW_ONLINE_DELIVERY_EMAIL_SUBJECT, startDate, endDate, mailBoxFolder);
			long end = System.currentTimeMillis();
			long turnAroundTime = end - start;
			logger.info("Turn Around Time for fetching emails: "+messages.length+" is "+turnAroundTime+" ms");
			Map<String, KitchenItem> kitchenItems = kitchenService.getAllKitchenItems(kitchenId, DeliveryPartner.ZOMATO);
			List<Order> orders = zomatoParser.parse(kitchen, kitchenItems, Arrays.asList(messages));
			for(Order order: orders) {
				try {
					orderService.saveOrUpdateOrder(order);
				} catch(DataIntegrityViolationException  e) {
					logger.error("Duplicate processing of orders!!! "+order.getDeliveryPartnerOrderId());
					/*return ow.writeValueAsString(new ResponseObj("Duplicate processing of orders!!!", true));*/
				}
			}
			return ow.writeValueAsString(new ResponseObj("Processed "+messages.length+" Zomato orders!!", false));
		} catch(Exception e) {
			logger.error("Exception while parsing!!! .", e);
			return ow.writeValueAsString(new ResponseObj("Exception while processing!!!", true));
		}
	}

	private boolean validateFormDataData(String startTime, String endTime, ResponseObj responseObj, long kitchenId) throws JsonProcessingException, ParseException, java.text.ParseException {
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
		if(kitchenId<=0) {
			responseObj.setStatus("Select Kitchen!!");
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

