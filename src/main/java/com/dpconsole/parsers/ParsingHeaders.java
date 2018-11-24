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

	}

}