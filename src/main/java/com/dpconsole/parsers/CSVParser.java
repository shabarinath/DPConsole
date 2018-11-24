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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.dpconsole.model.order.Order;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 24-Nov-2018 10:43:11 PM
 */
public abstract class CSVParser implements Parser<String> {

	private static CSVFormat format = CSVFormat.DEFAULT.withDelimiter(';').withHeader().withIgnoreHeaderCase();

	public abstract int getSkipLinesCount();

	public abstract List<Order> parseRecords(List<CSVRecord> csvRecords);

	@Override
	public List<Order> parse(String filePath) throws Exception {
		if(Utils.isEmpty(filePath)) {
			throw new Exception("Orders file path cannot be empty");
		}
		List<CSVRecord> csvRecords = getCSVRecords(filePath);
		return parseRecords(csvRecords);
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