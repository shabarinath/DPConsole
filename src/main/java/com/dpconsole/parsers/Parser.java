package com.dpconsole.parsers;

import javax.mail.Message;

import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:48:14 pm 2018 
 */

public interface Parser {
	Order parse(String content) throws Exception;
	
	Order parse(Message messages) throws Exception;
}

