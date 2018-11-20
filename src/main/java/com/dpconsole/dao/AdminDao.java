package com.dpconsole.dao;

import com.dpconsole.domain.User;

public interface AdminDao {
	
	public Object get(@SuppressWarnings("rawtypes") Class clazz, long id) throws Exception;
	
	public void saveOrUpdate(Object entity) throws Exception;

	public void delete(Object entity) throws Exception;
	
	public User getUser(long id) throws Exception;

	public void saveOrUpdateUser(User user) throws Exception;
	
}
