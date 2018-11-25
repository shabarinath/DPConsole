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
package com.dpconsole.service;

import com.dpconsole.model.user.User;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 5:32:39 PM
 */
public interface UserService {

	User getUser(long id) throws Exception;

	void saveOrUpdateUser(User user) throws Exception;

}