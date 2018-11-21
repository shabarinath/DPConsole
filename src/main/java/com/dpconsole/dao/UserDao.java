package com.dpconsole.dao;

import com.dpconsole.model.user.User;

public interface UserDao {

	User findByUsername(String username);

}