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

/**
 * @author nanda.malve
 * created on 24-Nov-2018 8:11:50 PM
 */
public interface ParsingHeaders {

	interface FoodPanda {
		String ORDER_CODE = "ORDER CODE";
		String FOOD_COST = "FOOD COST";
		String PAYMENT_TYPE = "PAYMENT TYPE";
		String ITEMS_COUNT = "ITEMS";
		String ORDER_ITEMS = "ORDER ITEMS";
		String EXPECTED_DELIVERY_DATE = "EXPECTED DELIVERY DATE";
		String EXPECTED_DELIVERY_TIME = "EXPECTED DELIVERY TIME";
		String STATUS = "STATUS";
		String CANCELLATION_REASON = "CANCELLATION REASON";
	}

	interface Swiggy {
		String ORDER_CODE = "Order ID";
		String FOOD_COST = "Total-bill-amount <bill>";
		String ITEMS_COUNT = "Item-count";
		String ORDER_ITEMS = "Item1-name_reward_type_quantity_price+Variants+Addons";
		String ORDERED_TIME = "Order-relay-time(ordered time)";
		String STATUS = "Order-status";
		String CANCELLATION_REASON = "Cancelled reason";
		String CANCELLATION_ENTITY = "Cancellation-responsible-entity";
	}
	
	interface Zomato {
		String ORDER_ID = "Order Id";
		String DP_PAID_AMOUNT = "Credit ( INR ) "; 
		String SETTLEMENT_TYPE = "Settlement Type";
	}

}