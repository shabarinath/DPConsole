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
package com.dpconsole.model.catalogue;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 9:04:14 PM
 */
public enum Type {

	VEG("Veg"),
	NON_VEG("Non Veg"),
	EGG("Egg");

	private String name;

	Type(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}