package com.dpconsole.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
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
		for(CSVRecord record : csvRecords) {
			try {
				String orderId = record.get(Zomato.ORDER_ID);
				String dpPaidAmount = record.get(Zomato.DP_PAID_AMOUNT);
				String settlementType = record.get(Zomato.SETTLEMENT_TYPE);
				if(StringUtils.isNotEmpty(settlementType) && settlementType.equalsIgnoreCase("Order")) {
					orderIdVSFinalAmountMap.put(orderId, dpPaidAmount);
				}
			}catch(Exception e) {
				logger.error("Exception occured in parseRecords reason: ", e);
			}
		}
		return orderIdVSFinalAmountMap;
	}

}

