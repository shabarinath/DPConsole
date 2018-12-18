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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 22-Nov-2018 12:18:55 AM
 */
@Entity
@Table(name="kitchen_delivery_partners")
@SuppressWarnings("serial")
public class KitchenDeliveryPartner extends Persistent {

	private DeliveryPartner deliveryPartner;
	private String emailIds;
	private double commissionPercentage;
	private boolean active = true;
	private double discountPercentage;
	private double supportChargesPercentage;
	private double convenienceFeePercentage;
	private double maxDiscountAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name="delivery_partner", nullable = false)
	public DeliveryPartner getDeliveryPartner() {
		return deliveryPartner;
	}
	public void setDeliveryPartner(DeliveryPartner deliveryPartner) {
		this.deliveryPartner = deliveryPartner;
	}

	@Column(name="commission_percentage")
	public double getCommissionPercentage() {
		return commissionPercentage;
	}
	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name="email_ids")
	public String getEmailIds() {
		return emailIds;
	}
	public void setEmailIds(String emailIds) {
		this.emailIds = emailIds;
	}
	@Column(name="discount_percentage")
	public double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	@Column(name="max_discount_amount")
	public double getMaxDiscountAmount() {
		return maxDiscountAmount;
	}
	public void setMaxDiscountAmount(double maxDiscountAmount) {
		this.maxDiscountAmount = maxDiscountAmount;
	}
	@Column(name="support_charges_percentage")
	public double getSupportChargesPercentage() {
		return supportChargesPercentage;
	}
	public void setSupportChargesPercentage(double supportChargesPercentage) {
		this.supportChargesPercentage = supportChargesPercentage;
	}
	@Column(name="convenience_fee_percentage")
	public double getConvenienceFeePercentage() {
		return convenienceFeePercentage;
	}
	public void setConvenienceFeePercentage(double convenienceFeePercentage) {
		this.convenienceFeePercentage = convenienceFeePercentage;
	}
	@Override
	public String toString() {
		return "KitchenDeliveryPartner [deliveryPartner=" + deliveryPartner
				+ ", emailIds=" + emailIds + ", commissionPercentage="
				+ commissionPercentage + ", active=" + active + "]";
	}
}