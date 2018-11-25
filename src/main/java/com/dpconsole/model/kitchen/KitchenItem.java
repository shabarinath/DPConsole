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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;
import com.dpconsole.model.catalogue.Item;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 10:59:47 PM
 */
@Entity
@Table(name="kitchen_items")
@SuppressWarnings("serial")
public class KitchenItem extends Persistent {

	private Item item;
	private Kitchen kitchen;
	private double price;
	private boolean active = true;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kitchen_id")
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	@Column(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

}