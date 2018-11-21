package com.dpconsole.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;
import com.dpconsole.model.catalogue.Item;

/**
 * @author SHABARINATH
 * 21-Nov-2018 11:13:01 pm 2018
 */

@Entity
@Table(name="order_item")
@SuppressWarnings("serial")
public class OrderItem extends Persistent {

	private Item item;
	private int quantity;
	private double unitPrice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false)
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unit_price")
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
}