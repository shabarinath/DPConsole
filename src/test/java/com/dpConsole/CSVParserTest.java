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
package com.dpConsole;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.parsers.FoodPandaParser;

/**
 * @author nanda.malve
 * created on 25-Nov-2018 11:39:58 AM
 */
public class CSVParserTest {

	public static void main(String ars[]) throws Exception {
		FoodPandaParser fpp = new FoodPandaParser();
		List<Order> orders = fpp.parse(new Kitchen(), new HashMap<>(), "FOOD-PANDA.csv");
		for(Order o : orders) {
			for(OrderItem oi : o.getOrderItems()) {
				System.out.println(ToStringBuilder.reflectionToString(oi));
			}
			System.out.println(ToStringBuilder.reflectionToString(o));
		}
		System.out.println(orders.size());
	}

}