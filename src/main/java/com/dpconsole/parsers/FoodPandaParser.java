/*
 * This computer program is the confidential information and proprietary trade
 * secret of DP Console Project. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * DP Console Project, and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of DP Console Project.
 *
 * Copyright 2018 DP Console Project. All Rights Reserved.
 */
package com.dpconsole.parsers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
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
	private static final String ORDER_DATE_PATTERN = "dd/MM/yy hh:mm a";

	@Override
	public char getDelimiter() {
		return ';';
	}

	@Override
	public int getSkipLinesCount() {
		return SKIP_LINES;
	}

	@Override
	public List<Order> parseRecords(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, List<CSVRecord> csvRecords) {
		Date parsedTime = Utils.getSystemTimeInGMT();
		List<Order> orders = new ArrayList<>();
		for(CSVRecord record : csvRecords) {
			try {
				String orderId = record.get(FoodPanda.ORDER_CODE);
				Order order = new Order();
				order.setKitchen(kitchen);
				order.setDeliveryPartner(DeliveryPartner.FOOD_PANDA);
				order.setDeliveryPartnerOrderId(orderId);
				order.setParsedTime(parsedTime);
				try {
					order.setStatus(record.get(FoodPanda.STATUS));
					order.setPaymentType(record.get(FoodPanda.PAYMENT_TYPE));
					order.setNotes(record.get(FoodPanda.CANCELLATION_REASON));
					order.setTotalCost(Double.valueOf(record.get(FoodPanda.FOOD_COST)));
					try {
						String strDate = record.get(FoodPanda.EXPECTED_DELIVERY_DATE);
						if(!Utils.isEmpty(strDate)) {
							strDate += (" " + record.get(FoodPanda.EXPECTED_DELIVERY_TIME));
							Date orderedTime = Utils.convertStringToDate(ORDER_DATE_PATTERN, strDate);
							orderedTime = Utils.convertDateToGMT(orderedTime, TimeZone.getDefault());
							order.setOrderedTime(orderedTime);
						}
					} catch(ParseException pe) {
						String comment = "Reason: " + pe.getMessage();
						addReviewComment(logger, order, comment, pe);
					}
					processOrderItems(kitchenItems, order, record.get(FoodPanda.ITEMS_COUNT), record.get(FoodPanda.ORDER_ITEMS));
				} catch(Exception e) {
					String comment = "Record: " + record.toString() + ". Reason: " + e.getMessage();
					addReviewComment(logger, order, comment, e);
				}
				orders.add(order);
			} catch(Exception e) {
				logger.error("Failed to process order: " + record.toString(), e);
			}
		}
		return orders;
	}

	private void processOrderItems(Map<String, KitchenItem> kItems, Order order, String itemsCountStr, String itemsStr) {
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
			KitchenItem kItem = kItems.get(item[1]);
			if(kItem != null) {
				orderItem.setItem(kItem.getItem());
				orderItem.setUnitPrice(kItem.getPrice());
			} else {
				String comment = "Kitchen Item not found for " + item[1];
				addReviewComment(logger, order, comment, null);
			}
			order.addOrderItem(orderItem);
		}
		int itemsCount = Integer.valueOf(itemsCountStr);
		if(parsedCount != itemsCount) {
			String comment = "Expected " + itemsCount + "items. Found " + parsedCount;
			addReviewComment(logger, order, comment, null);
		}
	}

}