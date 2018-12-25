package com.dpconsole.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.dpconsole.model.Persistent;
import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;

/**
 * @author SHABARINATH
 * 21-Nov-2018 10:07:56 pm 2018
 */
@Entity
@Table(name="orders")
@SuppressWarnings("serial")
public class Order extends Persistent {

	private List<OrderItem> orderItems;
	private Kitchen kitchen;
	private DeliveryPartner deliveryPartner;
	private String deliveryPartnerOrderId;
	private Date parsedTime;
	private Date orderedTime;
	private String status;
	private String paymentType;
	private double totalCost;
	private double postCommissionAmount; // This is calculated price
	private double dpReceivedPrice; // This is amount parsed from weekly CSV file
	private String notes;
	private boolean manualReview;
	private String manualReviewComments = "";
	private double restaurantPromo;
	private double piggybankCoins;
	private double zomatoPromo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "order_id", nullable = false)
	@OrderColumn(name = "list_index")
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "kitchen_id", nullable = false)
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

	@Column(name = "dp_order_id")
	public String getDeliveryPartnerOrderId() {
		return deliveryPartnerOrderId;
	}
	public void setDeliveryPartnerOrderId(String deliveryPartnerOrderId) {
		this.deliveryPartnerOrderId = deliveryPartnerOrderId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "parsed_time", nullable = false)
	public Date getParsedTime() {
		return parsedTime;
	}
	public void setParsedTime(Date parsedTime) {
		this.parsedTime = parsedTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ordered_time")
	public Date getOrderedTime() {
		return orderedTime;
	}
	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}

	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "notes")
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Column(name = "payment_type")
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "total_cost")
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "post_commission_amount")
	public double getPostCommissionAmount() {
		return postCommissionAmount;
	}
	public void setPostCommissionAmount(double postCommissionAmount) {
		this.postCommissionAmount = postCommissionAmount;
	}

	@Column(name = "dp_received_price")
	public double getDpReceivedPrice() {
		return dpReceivedPrice;
	}
	public void setDpReceivedPrice(double dpReceivedPrice) {
		this.dpReceivedPrice = dpReceivedPrice;
	}

	@Column(name = "manual_review")
	public boolean isManualReview() {
		return manualReview;
	}
	public void setManualReview(boolean manualReview) {
		this.manualReview = manualReview;
	}

	@Column(name = "manual_review_comments")
	public String getManualReviewComments() {
		return manualReviewComments;
	}
	public void setManualReviewComments(String manualReviewComments) {
		this.manualReviewComments = manualReviewComments;
	}

	@Transient
	public void addOrderItem(OrderItem orderItem) {
		if(this.orderItems == null) {
			orderItems = new ArrayList<>();
		}

		if(!orderItems.contains(orderItem)) {
			orderItems.add(orderItem);
		}
	}

	@Transient
	public void addReviewComment(String comment) {
		this.manualReviewComments += (comment + "; ");
	}
	@Column(name = "restaurant_promo")
	public double getRestaurantPromo() {
		return restaurantPromo;
	}
	@Column(name = "piggy_bank_coins")
	public double getPiggybankCoins() {
		return piggybankCoins;
	}
	public void setRestaurantPromo(double restaurantPromo) {
		this.restaurantPromo = restaurantPromo;
	}
	public void setPiggybankCoins(double piggybankCoins) {
		this.piggybankCoins = piggybankCoins;
	}
	@Column(name = "zomato_promo")
	public double getZomatoPromo() {
		return zomatoPromo;
	}
	public void setZomatoPromo(double zomatoPromo) {
		this.zomatoPromo = zomatoPromo;
	}
	@Override
	public String toString() {
		return "Order [orderItems=" + orderItems + ", kitchen=" + kitchen
				+ ", deliveryPartner=" + deliveryPartner
				+ ", deliveryPartnerOrderId=" + deliveryPartnerOrderId
				+ ", parsedTime=" + parsedTime + ", orderedTime=" + orderedTime
				+ ", status=" + status + ", paymentType=" + paymentType
				+ ", totalCost=" + totalCost + ", notes=" + notes
				+ ", manualReview=" + manualReview + ", manualReviewComments="
				+ manualReviewComments + "]";
	}
}