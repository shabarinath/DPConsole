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
package com.dpconsole.parsers;

import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.order.Order;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 6:28:53 PM
 */
public class SwiggyParser extends CSVParser {

	@Override
	public int getSkipLinesCount() {
		// TODO
		return 0;
	}

	@Override
	public List<Order> parseRecords(Kitchen kitchen, List<CSVRecord> csvRecords) {
		// TODO
		return null;
	}

}