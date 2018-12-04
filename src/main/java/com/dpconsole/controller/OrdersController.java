package com.dpconsole.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	KitchenService kitchenService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String loadPage(Model model) throws Exception{
		try {
			List<Kitchen> kitchens = kitchenService.getAllKitchens();
			model.addAttribute("kitchens", kitchens);
			model.addAttribute("deliveryPartners", DeliveryPartner.values());
			return "orders/orders";
		} catch(Exception e) {
			logger.error("Unable to load listing Page!! .", e);
			throw e;
		}
	}
	
	@RequestMapping(value = "/getOrderDetails", method = RequestMethod.GET)
	public String dashboard(Model model,
			@RequestParam("startTime") String startTime,
			@RequestParam("endTime") String endTime,
			@RequestParam("kitchenId") long kitchenId,
			@RequestParam("deliveryPartner") String deliveryPartner) throws Exception{
		try {
			Date startDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, startTime);
			Date endDate = Utils.convertStringToDate(INPUT_DATE_FORMAT, endTime);
			PartialPage<Order> orderDetails = orderService.getOrdersByCriteria(kitchenId, deliveryPartner, startDate, endDate,"",true,1,10000000);
			model.addAttribute("orders", orderDetails.getList());
			return "orders/ordersList";
		}catch(Exception e) {
			logger.error("Exception while fetching details reason: ", e);
			throw e;
		}
	}
	
	class ResponseObj implements Serializable {

		private static final long serialVersionUID = 3171819293048380553L;
		
		int draw;
		int recordsTotal;
		int recordsFiltered;
		List<Order> data;
		
		public ResponseObj(int draw, int recordsTotal, int recordsFiltered, List<Order> data) {
			super();
			this.draw = draw;
			this.recordsTotal = recordsTotal;
			this.recordsFiltered = recordsFiltered;
			this.data = data;
		}
		
		public int getDraw() {
			return draw;
		}
		public int getRecordsTotal() {
			return recordsTotal;
		}
		public int getRecordsFiltered() {
			return recordsFiltered;
		}
		public List<Order> getData() {
			return data;
		}
		public void setDraw(int draw) {
			this.draw = draw;
		}
		public void setRecordsTotal(int recordsTotal) {
			this.recordsTotal = recordsTotal;
		}
		public void setRecordsFiltered(int recordsFiltered) {
			this.recordsFiltered = recordsFiltered;
		}
		public void setData(List<Order> data) {
			this.data = data;
		}
		
		@Override
		public String toString() {
			return "ResponseObj [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered="
					+ recordsFiltered + ", data=" + data + "]";
		}
	}
}

