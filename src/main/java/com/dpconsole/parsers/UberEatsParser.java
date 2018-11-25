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

import java.util.List;
import java.util.Map;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 6:30:08 PM
 */
public class UberEatsParser<T> implements Parser<T> {

	@Override
	public List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, T content) throws Exception {
		// TODO
		return null;
	}

}