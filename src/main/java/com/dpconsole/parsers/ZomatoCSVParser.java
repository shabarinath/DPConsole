package com.dpconsole.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;

/**
 * @author SHABARINATH
 * 25-Dec-2018 5:35:04 PM 2018 
 */

public class ZomatoCSVParser extends CSVParser<Map<String, String>> {
	
	private static final Logger logger = LoggerFactory.getLogger(ZomatoCSVParser.class);

	@Override
	public char getDelimiter() {
		return ',';
	}

	@Override
	public int getSkipLinesCount() {
		return 0;
	}

	@Override
	public Map<String, String> parseRecords(Kitchen kitchen, Map<String, KitchenItem> kitchenItems,
			List<CSVRecord> csvRecords) {
		Map<String, String> orderIdVSFinalAmountMap = new HashMap<>();
		String orderId = "";
		for(CSVRecord record : csvRecords) {
			try {
				orderId = record.get(Zomato.ORDER_ID);
				String dpPaidAmount = record.get(Zomato.DP_CREDIT_AMOUNT);
				String debitAmount = record.get(Zomato.DP_DEBIT_AMOUNT);
				orderIdVSFinalAmountMap.put(orderId, dpPaidAmount.concat("#").concat(debitAmount));
			}catch(Exception e) {
				logger.error("Exception occured in parseRecords for orderId : "+orderId+" reason: ", e);
			}
		}
		return orderIdVSFinalAmountMap;
	}

}

