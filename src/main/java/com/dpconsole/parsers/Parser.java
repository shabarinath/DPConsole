package com.dpconsole.parsers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.utils.Utils;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:48:14 pm 2018
 */
public interface Parser<T> extends ParsingHeaders {

	List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, T content) throws Exception;

	default void addReviewComment(Logger logger, Order order, String comment, Throwable t) {
		order.setManualReview(true);
		order.addReviewComment(comment);
		String errMsg = "Failed to parse order date for orderId: " + order.getDeliveryPartnerOrderId() + " " + comment;
		if(logger == null) {
			return;
		}
		if(t != null) {
			logger.error(errMsg, t);
		} else {
			logger.error(errMsg);
		}
	}

	default boolean validateTotalCost(List<Order> orders) {
		boolean match = true;
		for(Order order : orders) {
			if(Utils.isEmpty(order.getStatus())
					|| !order.getStatus().toLowerCase().contains("deliver")) {
				continue;
			}
			double totalCost = order.getTotalCost();
			double calculatedCost = 0;
			for(OrderItem oItem : order.getOrderItems()) {
				calculatedCost += (oItem.getQuantity() * oItem.getMarketPrice());
			}
			if(totalCost != calculatedCost) {
				match = false;
				String comment = "Expected " + totalCost + "cost. Found " + calculatedCost;
				this.addReviewComment(null, order, comment, null);
			}
		}
		return match;
	}
}