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
package com.dpconsole.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.Hibernate;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 8:50:20 PM
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class Persistent implements Serializable {

	private long id;
	private long version;
	private boolean active = true;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Version
	@Column(name="version", nullable=false)
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Column(name="active", nullable=false)
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Persistent)) {
			return false;
		}

		Class<?> src = Hibernate.getClass(this);
		Class<?> dst = Hibernate.getClass(obj);
		if(!src.getClass().getName().equals(dst.getClass().getName())) {
			return false;
		}
		return (!isPersisted()) ? false : (id == ((Persistent) obj).getId());
	}

	@Override
	public int hashCode() {
		final int seed = 32;
		int result = 1;
		result = seed * result + String.valueOf(id).hashCode();
		return result;
	}

	@Transient
	public final boolean isPersisted() {
		return (id <= 0) ? false : true;
	}
}