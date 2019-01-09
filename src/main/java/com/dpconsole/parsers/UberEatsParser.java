package com.dpconsole.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 07-Jan-2019 10:40:54 PM 2019 
 */

public class UberEatsParser extends CSVParser<List<Order>> {
	
	private static final Logger logger = LoggerFactory.getLogger(UberEatsParser.class);

	@Override
	public char getDelimiter() {
		// TODO Auto-generated method stub
		return ',';
	}

	@Override
	public int getSkipLinesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> parseRecords(Kitchen kitchen, Map<String, KitchenItem> kitchenItems,
			List<CSVRecord> csvRecords) {
		ArrayList<Order> orders = new ArrayList<Order>();
		return null;
	}

}

