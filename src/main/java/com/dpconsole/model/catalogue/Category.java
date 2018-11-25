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
package com.dpconsole.model.catalogue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 8:49:43 PM
 */
@Entity
@Table(name="catalogue_categories")
@SuppressWarnings("serial")
public class Category extends Persistent {

	private String name;
	private int precedence;
	private boolean active = true;

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="precedence")
	public int getPrecedence() {
		return precedence;
	}
	public void setPrecedence(int precedence) {
		this.precedence = precedence;
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
		return "Category [name=" + name + ", precedence=" + precedence
				+ ", active=" + active + ", getName()=" + getName()
				+ ", getPrecedence()=" + getPrecedence() + ", isActive()="
				+ isActive() + ", getId()=" + getId() + ", getVersion()="
				+ getVersion() + ", hashCode()=" + hashCode()
				+ ", isPersisted()=" + isPersisted() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
}