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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 10:26:28 PM
 */
@Entity
@Table(name="kitchen_discounts")
@SuppressWarnings("serial")
public class KitchenDiscount extends Persistent {

	private Kitchen kitchen;
	private DeliveryPartner deliveryPartner;
	private Date startTime;
	private Date endTime;
	private int discount;
	private boolean active = true;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kitchen_id")
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="delivery_partner", nullable = false)
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

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "KitchenDiscount [kitchen=" + kitchen + ", deliveryPartner="
				+ deliveryPartner + ", startTime=" + startTime + ", endTime="
				+ endTime + ", discount=" + discount + ", active=" + active
				+ ", getKitchen()=" + getKitchen() + ", getDeliveryPartner()="
				+ getDeliveryPartner() + ", getStartTime()=" + getStartTime()
				+ ", getEndTime()=" + getEndTime() + ", getDiscount()="
				+ getDiscount() + ", isActive()=" + isActive() + ", getId()="
				+ getId() + ", getVersion()=" + getVersion() + ", hashCode()="
				+ hashCode() + ", isPersisted()=" + isPersisted()
				+ ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";
	}
}