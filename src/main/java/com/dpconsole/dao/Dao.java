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