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
