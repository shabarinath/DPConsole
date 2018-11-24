package com.dpconsole.parsers;

import java.util.List;

import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:48:14 pm 2018
 */
public interface Parser {

	List<Order> parse(Object content) throws Exception;

}