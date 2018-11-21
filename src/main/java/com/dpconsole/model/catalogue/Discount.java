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

import com.dpconsole.model.Kitchen;
import com.dpconsole.model.Persistent;
import com.dpconsole.model.delivery.DeliveryPartner;

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
	private Date startDate;
	private Date endDate;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_time", nullable = false)
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="discount")
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}

}