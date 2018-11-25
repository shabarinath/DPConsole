/*
 * This computer program is the confidential information and proprietary trade
 * secret of DP Console Project.. Possessions and use of this program must
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
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 6:28:53 PM
 */
public class SwiggyParser extends CSVParser {

	private static final Logger logger = LoggerFactory.getLogger(SwiggyParser.class);

	private static final short SKIP_LINES = 5;
	private static final String ORDER_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@Override
	public char getDelimiter() {
		return ',';
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
				String orderId = record.get(Swiggy.ORDER_CODE);
				Order order = new Order();
				order.setKitchen(kitchen);
				order.setDeliveryPartner(DeliveryPartner.SWIGGY);
				order.setDeliveryPartnerOrderId(orderId);
				order.setParsedTime(parsedTime);
				try {
					order.setStatus(record.get(Swiggy.STATUS));
					order.setPaymentType(null);
					String cancellationReason = record.get(Swiggy.CANCELLATION_REASON);
					if(!Utils.isEmpty(cancellationReason)) {
						String notes = cancellationReason + "_" + record.get(Swiggy.CANCELLATION_ENTITY);
						order.setNotes(notes);
					}
					order.setTotalCost(Double.valueOf(record.get(Swiggy.FOOD_COST)));
					try {
						String strDate = record.get(Swiggy.ORDERED_TIME);
						if(!Utils.isEmpty(strDate)) {
							Date orderedTime = Utils.convertStringToDate(ORDER_DATE_PATTERN, strDate);
							orderedTime = Utils.convertDateToGMT(orderedTime, TimeZone.getDefault());
							order.setOrderedTime(orderedTime);
						}
					} catch(ParseException pe) {
						String comment = "Reason: " + pe.getMessage();
						addReviewComment(logger, order, comment, pe);
					}
					processOrderItems(kitchenItems, order, record.get(Swiggy.ITEMS_COUNT), record.get(Swiggy.ORDER_ITEMS));
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
		//TODO:
	}

}