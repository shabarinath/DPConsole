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
package com.dpConsole;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.parsers.SwiggyParser;

/**
 * @author nanda.malve
 * created on 25-Nov-2018 11:39:58 AM
 */
public class CSVParserTest {

	public static void main(String ars[]) throws Exception {
		SwiggyParser fpp = new SwiggyParser();
		List<Order> orders = fpp.parse(new Kitchen(), new HashMap<>(), "FOOD-FOR-U-SWIGGY.csv");
		//FoodPandaParser fpp = new FoodPandaParser();
		//List<Order> orders = fpp.parse(new Kitchen(), new HashMap<>(), "FOOD-PANDA.csv");
		for(Order o : orders) {
			System.out.println(ToStringBuilder.reflectionToString(o));
			if(o.getOrderItems() == null) {
				continue;
			}
			for(OrderItem oi : o.getOrderItems()) {
				System.out.println(ToStringBuilder.reflectionToString(oi));
			}
		}
		System.out.println(orders.size());
	}

}