package com.dpconsole.parsers;

import java.util.List;

import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:48:14 pm 2018
 */
public interface Parser<T> {

	List<Order> parse(T content) throws Exception;

}