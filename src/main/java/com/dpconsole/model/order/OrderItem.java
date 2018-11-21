package com.dpconsole.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Table;

import com.dpconsole.model.catalogue.Item;

/**
 * @author SHABARINATH
 * 21-Nov-2018 11:13:01 pm 2018 
 */
@Entity
@Table(name="order_item")
public class OrderItem extends Persistence {

	@Column(name = "order_id")
	private String orderId;
	
	@Column(name = "quantity")
	private int quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;
	
	@Column(name = "unit_price")
	private int unitPrice;

	public String getOrderId() {
		return orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
}

