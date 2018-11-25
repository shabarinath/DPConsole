package com.dpconsole.parsers;

import java.util.List;
import java.util.Map;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:48:14 pm 2018
 */
public interface Parser<T> extends ParsingHeaders {

	List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, T content) throws Exception;

}