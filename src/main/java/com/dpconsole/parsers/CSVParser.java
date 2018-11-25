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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 10:43:11 PM
 */
public abstract class CSVParser implements Parser<String> {

	public abstract char getDelimiter();

	public abstract int getSkipLinesCount();

	private CSVFormat format = CSVFormat.DEFAULT.withDelimiter(getDelimiter()).withHeader().withIgnoreHeaderCase();

	public abstract List<Order> parseRecords(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, List<CSVRecord> csvRecords);

	@Override
	public List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, String filePath) throws Exception {
		if(Utils.isEmpty(filePath)) {
			throw new Exception("Orders file path cannot be empty");
		}
		List<CSVRecord> csvRecords = getCSVRecords(filePath);
		return parseRecords(kitchen, kitchenItems, csvRecords);
	}

	private List<CSVRecord> getCSVRecords(String filePath) throws Exception {
		Path path = Paths.get(filePath);
		if(!Files.exists(path)) {
			throw new FileNotFoundException("File not found: " + filePath);
		}

		BufferedReader reader = Files.newBufferedReader(path);
		for(short i = 0; i < getSkipLinesCount(); i++) {
			reader.readLine();
		}

		org.apache.commons.csv.CSVParser parser = org.apache.commons.csv.CSVParser.parse(reader, format);

		return parser.getRecords();
	}
}