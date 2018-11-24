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
package com.dpconsole.dao;

import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:32:10 PM
 */
public interface Dao {

	void saveOrUpdate(Persistent perObj) throws Exception;

	void delete(Persistent perObj) throws Exception;

	Object get(Class<? extends Persistent> clazz, long id) throws Exception;

}