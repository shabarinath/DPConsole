package com.dpconsole.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.SubCategory;
import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.service.KitchenService;
import com.dpconsole.service.OrderService;
import com.dpconsole.utils.Utils;

/**
 * @author SHABARINATH
 * 28-Dec-2018 10:21:48 AM 2018 
 */

@Controller
@SessionAttributes("dashboardController")
public class DashboardController {
	
	private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private OrderService orderService;
	
	private static final String INPUT_DATE_FORMAT="yyyy-MM-dd HH:mm";

	@RequestMapping(value = "/filtered/{type}", method = RequestMethod.GET)
	public String loadPage(Model model, @PathVariable("type") String type) throws Exception{
		try {
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("deliveryPartners", DeliveryPartner.values());
			String headerValue = type.equalsIgnoreCase("orderLayout") ? "Order Details" : 
				type.equalsIgnoreCase("zomatoGmailAuto") ? 
				"Zomato Email Parser" : type.equalsIgnoreCase("dashboard") ? 
				"Dashboard":type.equalsIgnoreCase("stats") ? "Order Stats":"";
			model.addAttribute("headerValue", headerValue);
			return "orders/layout";
		} catch(Exception e) {
			logger.error("Failed to load orders layout", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(Model model,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("deliveryPartner") String deliveryPartner,
			HttpServletRequest request) throws Exception{
			HashMap<String, HashMap<String, HashMap<String, Double>>> statsMap = new HashMap<String, HashMap<String, HashMap<String, Double>>>();
		try {
			Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
			Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);
			PartialPage<Order> orderDetails = orderService.getOrdersByCriteria(kitchenId, deliveryPartner, startDate, endDate, "", true, 1, 1000000000);
			List<Order> orders = orderDetails.getList();
			//Kitchen , DP <params, value>
			HashMap<String, Double> paramVsValMap = new HashMap<String, Double>();
			HashMap<String, HashMap<String, Double>> dpVsAttribsMap = null;
			for(Order order : orders) {
				double noOfItems = 0.0;
				double noOfOrders = 0.0;
				String kitchenName = order.getKitchen().getName();
				String deliveryPartnerName = order.getDeliveryPartner().getName();
				double totalAmount =  order.getPostCommissionAmount();
				int itemsCount = order.getOrderItems().size();
				if(statsMap.get(kitchenName) == null) {
					dpVsAttribsMap = new HashMap<String, HashMap<String, Double>>();
					noOfOrders = 1;
					noOfItems = (double)itemsCount;
					paramVsValMap = new HashMap<String, Double>();
					dpVsAttribsMap.put(deliveryPartnerName, paramVsValMap);
				} else {
					dpVsAttribsMap = statsMap.get(kitchenName);
					 paramVsValMap = dpVsAttribsMap.get(deliveryPartnerName);
					 if(paramVsValMap == null) {
						 paramVsValMap = new HashMap<String, Double>();
					 }
					 totalAmount = paramVsValMap.get("totalAmount")==null ? totalAmount :  paramVsValMap.get("totalAmount")+totalAmount;
					 noOfItems = paramVsValMap.get("noOfItems")==null ? itemsCount : + paramVsValMap.get("noOfItems")+itemsCount;
					 noOfOrders = paramVsValMap.get("noOfOrders")==null ? 1 : paramVsValMap.get("noOfOrders")+1 ;
				}
				paramVsValMap.put("totalAmount", totalAmount);
				paramVsValMap.put("noOfItems", noOfItems);
				paramVsValMap.put("noOfOrders", noOfOrders);
				dpVsAttribsMap.put(deliveryPartnerName, paramVsValMap);
				statsMap.put(kitchenName, dpVsAttribsMap);
			}
		}catch(Exception e) {
			logger.error("Exception in processing dashbaord", e);
		}
		model.addAttribute("stats", statsMap);
		return "dashboard/dashboard";
	}
	
	@RequestMapping(value = "/stats")
	public String stats(Model model,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("deliveryPartner") String deliveryPartner,
			HttpServletRequest request) throws Exception{
			HashMap<String, HashMap<String, HashMap<String, Integer>>> statsMap = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();
			HashMap<String, Integer> subCategoryCount = new HashMap<String, Integer>();
			try {
				Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
				Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);
				PartialPage<Order> orderDetails = orderService.getOrdersByCriteria(kitchenId, deliveryPartner, startDate, endDate, "", true, 1, 1000000000);
				List<Order> orders = orderDetails.getList();
				//<categories, <sub_categories, HashMap<ItemName, quantity>>>
				for(Order order : orders) {
					int subCategoryTotalCount = 0;
					List<OrderItem> orderItems = order.getOrderItems();
					for(OrderItem oi:orderItems) {
						SubCategory subCategory = oi.getKitchenItem().getItem().getSubCategory();
						String categoryName = subCategory.getCategory().getName();
						String subCategoryName = subCategory.getName();
						String itemName = oi.getKitchenItem().getItem().getName();
						if(statsMap.get(categoryName) == null) {
							HashMap<String, HashMap<String, Integer>> subCategoryMap = new HashMap<>();
							HashMap<String, Integer> itemsMap = new HashMap<>();
							itemsMap.put(oi.getKitchenItem().getItem().getName(), oi.getQuantity());
							subCategoryMap.put(subCategoryName,  Utils.sortByValues(itemsMap));
							statsMap.put(categoryName, subCategoryMap);
							subCategoryCount.put(subCategoryName+"_TOTAL", oi.getQuantity());
						}else {
							HashMap<String, HashMap<String, Integer>> subCategoryMap = statsMap.get(categoryName);
							HashMap<String, Integer> itemsMap = null;
							if(subCategoryMap == null) {
								subCategoryMap = new HashMap<>();
							} 
							if(subCategoryMap.get(subCategoryName) == null) {
								itemsMap = new HashMap<>();
								itemsMap.put(itemName, 0);
							} else {
								itemsMap = subCategoryMap.get(subCategoryName);
								if(itemsMap == null) {
									itemsMap = new HashMap<>();
									itemsMap.put(itemName, 0);
								}
							}
							int quantity = itemsMap.get(itemName)==null?  oi.getQuantity() : itemsMap.get(itemName) +oi.getQuantity();
							itemsMap.put(itemName, quantity);
							subCategoryMap.put(subCategoryName, Utils.sortByValues(itemsMap));
							int total = (subCategoryCount.get(subCategoryName+"_TOTAL")==null) ? 0: subCategoryCount.get(subCategoryName+"_TOTAL");
							subCategoryTotalCount = total+ +oi.getQuantity();
							subCategoryCount.put(subCategoryName+"_TOTAL", subCategoryTotalCount);
							statsMap.put(categoryName, subCategoryMap);
						}
					}
				}
			}catch(Exception e) {
				logger.error("Exception occured while processing stats reason: ", e);
			}
			model.addAttribute("statsMap", statsMap);
			model.addAttribute("subCategoryCountMap", subCategoryCount);
			return "dashboard/stats";
	}
}

