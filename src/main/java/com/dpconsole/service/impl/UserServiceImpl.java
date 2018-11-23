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
package com.dpconsole.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.UserDao;
import com.dpconsole.model.user.User;
import com.dpconsole.service.UserService;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 5:33:00 PM
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User getUser(long id) throws Exception {
		return userDao.getUser(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateUser(User user) throws Exception {
		userDao.saveOrUpdate(user);
	}

}