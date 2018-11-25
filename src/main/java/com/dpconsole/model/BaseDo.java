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
package com.dpconsole.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author nanda.malve
 * created on 21-Nov-2018 8:57:30 PM
 */
@MappedSuperclass
@SuppressWarnings("serial")
public class BaseDo extends Persistent {

	private String classCode;

	@Column(name="class_code", insertable=false, updatable=false)
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
}