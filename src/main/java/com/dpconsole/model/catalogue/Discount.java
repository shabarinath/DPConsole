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
package com.dpconsole.model.catalogue;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dpconsole.model.Persistent;
import com.dpconsole.model.delivery.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 10:26:28 PM
 */
@Entity
@Table(name="catalogue_discounts")
@SuppressWarnings("serial")
public class Discount extends Persistent {

	private Kitchen kitchen;
	private DeliveryPartner deliveryPartner;
	private Date startTime;
	private Date endTime;
	private int discount;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kitchen_id")
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="delivery_partner_id")
	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}
	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time", nullable = false)
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time", nullable = false)
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name="discount")
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}

}