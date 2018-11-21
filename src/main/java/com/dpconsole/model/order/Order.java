package com.dpconsole.model.order;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dpconsole.model.delivery.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;

/**
 * @author SHABARINATH
 * 21-Nov-2018 10:07:56 pm 2018
 */
@Entity
@Table(name="orders")
public class Order extends Persistence {

	@Column(name = "dp_order_id")
	private String deliveryPartnerOrderId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kitchen_id", nullable = false)
	private Kitchen kitchen;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "delivery_partner_id", nullable = false)
	private DeliveryPartner deliveryPartner;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time")
	private Date createdTime;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "order_id", nullable = false)
	@OrderColumn(name = "list_index")
	private List<OrderItem> orderItems;

	public Kitchen getKitchen() {
		return kitchen;
	}

	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}

	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}

	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	public String getDeliveryPartnerOrderId() {
		return deliveryPartnerOrderId;
	}

	public void setDeliveryPartnerOrderId(String deliveryPartnerOrderId) {
		this.deliveryPartnerOrderId = deliveryPartnerOrderId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}

