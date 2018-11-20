package com.dpconsole.dao;

import com.dpconsole.domain.User;

public interface UserDao {
		
	public User findByUsername(String username);
	
}
