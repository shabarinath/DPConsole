package com.dpconsole.parsers;
/**
 * @author SHABARINATH
 * 24-Nov-2018 3:21:31 pm 2018 
 */

public enum EmailAttribute {
	CUSTOMER_NAME("Customer Name:"),
	CONTACT_NO("Contact No.:"),
	CUSTOMER_ADDRESS("Customer Address:"),
	ORDER_ID("Order ID: "),
	Date ("Date: "),
	RESTUARANT_PROMO("Restaurant Promo"),
	PAID_BY("Paid by"),
	PREPAID("Prepaid"),
	COD("COD"),
	CUSTOMIZE("Customize :"),
	ZOMATO_FOOTER("Zomato Media Pvt. Ltd."),
	CASH_TO_BE_COLLECTED_FROM("Cash to be collected from"),
	TAXES("Taxes"),
	PIGGY_BANK_DISCOUNT("Discount using Piggybank Coins"),
	ZOMATO_PROMO("Zomato Promo"),
	QUANTITY("Quantity :");

	private String name;

	EmailAttribute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

