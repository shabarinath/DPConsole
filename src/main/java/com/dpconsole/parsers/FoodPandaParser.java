/*
 * This computer program is the confidential information and proprietary trade
 * secret of OpsRamp, Inc. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * OpsRamp, Inc., and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of OpsRamp, Inc.
 *
 * Copyright 2018 OpsRamp, Inc. All Rights Reserved.
 */
package com.dpconsole.parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 6:24:45 PM
 */
public class FoodPandaParser extends CSVParser {

	private static final Logger logger = LoggerFactory.getLogger(FoodPandaParser.class);

	private static final short SKIP_LINES = 1;
	private static final String ORDER_DATE_PATTERN = "dd/mm/yy hh:mm a";

	@Override
	public int getSkipLinesCount() {
		return SKIP_LINES;
	}

	@Override
	public List<Order> parseRecords(Kitchen kitchen, List<CSVRecord> csvRecords) {
		Date createdTime = Utils.getSystemTimeInGMT();
		List<Order> orders = new ArrayList<>();
		for(CSVRecord record : csvRecords) {
			try {
				String orderId = record.get(FoodPanda.ORDER_CODE);
				Order order = new Order();
				order.setKitchen(kitchen);
				order.setDeliveryPartner(DeliveryPartner.FOOD_PANDA);
				order.setDeliveryPartnerOrderId(orderId);
				order.setCreatedTime(createdTime);
				try {
					order.setStatus(record.get(FoodPanda.STATUS));
					order.setPaymentType(record.get(FoodPanda.PAYMENT_TYPE));
					order.setNotes(record.get(FoodPanda.CANCELLATION_REASON));
					order.setTotalCost(Double.valueOf(record.get(FoodPanda.FOOD_COST)));
					String strDate = record.get(FoodPanda.EXPECTED_DELIVERY_DATE) + " " + record.get(FoodPanda.EXPECTED_DELIVERY_TIME);
					Date orderedTime = Utils.convertStringToDate(ORDER_DATE_PATTERN, strDate);
					orderedTime = Utils.convertDateToGMT(orderedTime, TimeZone.getDefault());
					order.setOrderedTime(orderedTime);
					processOrderItems(order, record.get(FoodPanda.ITEMS_COUNT), record.get(FoodPanda.ORDER_ITEMS));
				} catch(Exception e) {
					order.setManualReview(true);
					String errMsg = "Record: " + record.toString() + ". Reason: " + e.getMessage();
					order.setManualReviewComments(errMsg);
					logger.error("Failed to parse order date for orderId: " + orderId + " " + errMsg, e);
				}
				orders.add(order);
			} catch(Exception e) {
				logger.error("Failed to process order: " + record.toString(), e);
			}
		}
		return orders;
	}

	private void processOrderItems(Order order, String itemsCountStr, String itemsStr) {
		String[] itemsArray = itemsStr.split(";");
		int parsedCount = 0;
		for(String itemStr : itemsArray) {
			itemStr = itemStr.trim();
			if(Utils.isEmpty(itemStr)) {
				continue;
			}
			itemStr = itemStr.trim();
			String[] item = itemStr.split(" ", 2);
			OrderItem orderItem = new OrderItem();
			int quantity = Integer.valueOf(item[0]);
			orderItem.setQuantity(quantity);
			parsedCount += quantity;
			//TODO: Set item object and unit price
			orderItem.setItem(null);
			orderItem.setUnitPrice(0);
			order.addOrderItem(orderItem);
		}
		int itemsCount = Integer.valueOf(itemsCountStr);
		if(parsedCount != itemsCount) {
			order.setManualReview(true);
			String comment = "Expected " + itemsCount + "items. Found " + parsedCount;
			logger.error("Failed to parse order date for orderId: " + order.getDeliveryPartnerOrderId() + " " + comment);
			order.setManualReviewComments(comment);
		}
	}

	public static void main(String ars[]) throws Exception {
		FoodPandaParser fpp = new FoodPandaParser();
		List<Order> orders = fpp.parse(null, "FOOD-PANDA.csv");
		for(Order o : orders) {
			for(OrderItem oi : o.getOrderItems()) {
				System.out.println(ToStringBuilder.reflectionToString(oi));
			}
			System.out.println(ToStringBuilder.reflectionToString(o));
		}
		System.out.println(orders.size());
	}
}