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
package com.dpconsole.model.kitchen;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 9:35:24 PM
 */
public enum DeliveryPartner {

	SWIGGY("Swiggy"),
	ZOMATO("Zomato"),
	FOOD_PANDA("FoodPanda"),
	UBER_EATS("Uber Eats");

	private String name;

	DeliveryPartner(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
