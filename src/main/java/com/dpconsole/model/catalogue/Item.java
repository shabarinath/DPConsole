/*
 * This computer program is the confidential information and proprietary trade
 * secret of OpsRamp, Inc. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * OpsRamp, Inc., and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of OpsRamp, Inc.
 *
 * Copyright 2018 OpsRamp, Inc. All Rights Reserved.
 */
package com.dpconsole.model.catalogue;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 8:49:23 PM
 */
@Entity
@Table(name="catalogue_items")
@SuppressWarnings("serial")
public class Item extends Persistent {

	private String name;
	private String description;
	private SubCategory subCategory;
	private Type type;
	private int precedence;
	private List<String> aliases;
	private boolean active = true;

	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sub_category_id")
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="food_type", nullable=false)
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	@Column(name="precedence")
	public int getPrecedence() {
		return precedence;
	}
	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}

	@ElementCollection(targetClass = java.lang.String.class, fetch=FetchType.EAGER)
	@CollectionTable(name="item_aliases", joinColumns = {
			@JoinColumn(name="item_id", nullable=false) })
	@Column(name="alias")
	@Cascade( {CascadeType.ALL, CascadeType.DELETE_ORPHAN} )
	@OrderColumn(name = "list_index")
	public List<String> getAliases() {
		return aliases;
	}
	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}