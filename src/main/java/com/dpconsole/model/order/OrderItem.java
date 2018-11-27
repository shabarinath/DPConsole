package com.dpconsole.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;
import com.dpconsole.model.kitchen.KitchenItem;

/**
 * @author SHABARINATH
 * 21-Nov-2018 11:13:01 pm 2018
 */
@Entity
@Table(name="order_items")
@SuppressWarnings("serial")
public class OrderItem extends Persistent {

	private KitchenItem kitchenItem;
	private int quantity;
	private double manufacturingPrice;
	private double marketPrice;
	private double dpReceivedPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchen_item_id", nullable = false)
	public KitchenItem getKitchenItem() {
		return kitchenItem;
	}
	public void setKitchenItem(KitchenItem kitchenItem) {
		this.kitchenItem = kitchenItem;
	}

	@Column(name = "quantity")
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "manufacturing_price")
	public double getManufacturingPrice() {
		return manufacturingPrice;
	}
	public void setManufacturingPrice(double manufacturingPrice) {
		this.manufacturingPrice = manufacturingPrice;
	}

	@Column(name = "market_price")
	public double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	@Column(name = "dp_received_price")
	public double getDpReceivedPrice() {
		return dpReceivedPrice;
	}
	public void setDpReceivedPrice(double dpReceivedPrice) {
		this.dpReceivedPrice = dpReceivedPrice;
	}

	@Override
	public String toString() {
		return "OrderItem [kitchenItem=" + kitchenItem + ", quantity=" + quantity
				+ ", manufacturingPrice=" + manufacturingPrice
				+ ", marketPrice=" + marketPrice + ", dpReceivedPrice="
				+ dpReceivedPrice + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + ", hashCode()="
				+ hashCode() + ", isPersisted()=" + isPersisted()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
}