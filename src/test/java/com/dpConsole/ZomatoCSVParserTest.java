package com.dpConsole;

import com.dpconsole.parsers.ZomatoCSVParser;

/**
 * @author SHABARINATH
 * 25-Dec-2018 10:25:51 PM 2018 
 */

public class ZomatoCSVParserTest {

	public static void main(String[] args) {
		ZomatoCSVParser zCSVParser = new ZomatoCSVParser();
		try {
			zCSVParser.parse(null, null, "F:\\restruants website\\Calculation Details\\SOA_DATA_18452902_2018-12-12 00_00_00_2018-12-16 00_00_00.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

