package com.dpconsole.dao;

import com.dpconsole.model.user.User;

public interface UserDao extends Dao {

	User getUserByUsername(String username);

	User getUser(long id) throws Exception;

}