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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.ListUtils;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 9:45:54 PM
 */
@Entity
@Table(name="kitchens")
@SuppressWarnings("serial")
public class Kitchen extends Persistent {

	private String name;
	private String mailBoxUserName;
	private String mailBoxPassword;
	private List<KitchenDeliveryPartner> supportedDeliveryPartners;
	private boolean active = true;

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="kitchen_id", nullable = false)
	public List<KitchenDeliveryPartner> getSupportedDeliveryPartners() {
		return supportedDeliveryPartners;
	}
	public void setSupportedDeliveryPartners(List<KitchenDeliveryPartner> supportedDeliveryPartners) {
		this.supportedDeliveryPartners = supportedDeliveryPartners;
	}

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name="mailbox_username")
	public String getMailBoxUserName() {
		return mailBoxUserName;
	}
	public void setMailBoxUserName(String mailBoxUserName) {
		this.mailBoxUserName = mailBoxUserName;
	}

	@Column(name="mailbox_password")
	public String getMailBoxPassword() {
		return mailBoxPassword;
	}
	public void setMailBoxPassword(String mailBoxPassword) {
		this.mailBoxPassword = mailBoxPassword;
	}

	@Transient
	public KitchenDeliveryPartner getSupportedDeliveryPartner(DeliveryPartner deliveryPartner) {
		for(KitchenDeliveryPartner kdp : ListUtils.emptyIfNull(this.supportedDeliveryPartners)) {
			if(deliveryPartner == kdp.getDeliveryPartner()) {
				return kdp;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Kitchen [name=" + name + ", mailBoxUserName=" + mailBoxUserName
				+ ", mailBoxPassword=" + mailBoxPassword
				+ ", supportedDeliveryPartners=" + supportedDeliveryPartners
				+ ", active=" + active + ", getName()=" + getName()
				+ ", getSupportedDeliveryPartners()="
				+ getSupportedDeliveryPartners() + ", isActive()=" + isActive()
				+ ", getMailBoxUserName()=" + getMailBoxUserName()
				+ ", getMailBoxPassword()=" + getMailBoxPassword()
				+ ", getId()=" + getId() + ", getVersion()=" + getVersion()
				+ ", hashCode()=" + hashCode() + ", isPersisted()="
				+ isPersisted() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}