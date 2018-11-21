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
package com.dpconsole.model.delivery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 9:43:02 PM
 */
@Entity
@Table(name="delivery_partners")
@SuppressWarnings("serial")
public class DeliveryPartner extends Persistent {

	private String name;

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}