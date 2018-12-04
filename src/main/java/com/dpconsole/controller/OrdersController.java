package com.dpconsole.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;
import com.dpconsole.service.KitchenService;
import com.dpconsole.service.OrderService;
import com.dpconsole.utils.DisplayTagUtils;
import com.dpconsole.utils.Utils;

/**
 * @author SHABARINATH
 * 03-Dec-2018 7:45:58 PM 2018
 */

@Controller
@SessionAttributes("orders")
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	private static final String INPUT_DATE_FORMAT="yyyy-MM-dd HH:mm";

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/ordersLayout", method = RequestMethod.GET)
	public String loadPage(Model model) throws Exception{
		try {
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("deliveryPartners", DeliveryPartner.values());
			return "orders/ordersLayout";
		} catch(Exception e) {
			logger.error("Failed to load orders layout", e);
			throw e;
		}
	}

	@RequestMapping(value = "/orders")
	public String dashboard(Model model,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("deliveryPartner") String deliveryPartner,
			HttpServletRequest request) throws Exception{
		try {
			Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
			Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);

			String tableId="order";
			String sortName = DisplayTagUtils.getSortName(request, tableId, "");
			boolean isDecendingOrder = DisplayTagUtils.isDecendingSortOrder(request, tableId, true);
			int pageNo = DisplayTagUtils.getPage(request, tableId, 1);
			int pageSize = DisplayTagUtils.getPageSize(request, tableId, 20);

			PartialPage<Order> orderDetails = orderService.getOrdersByCriteria(kitchenId, deliveryPartner, startDate, endDate, sortName, isDecendingOrder, pageNo, pageSize);
			model.addAttribute("ordersPage", orderDetails);
			return "orders/ordersPage";
		} catch(Exception e) {
			logger.error("Failed to load orders", e);
			throw e;
		}
	}
}