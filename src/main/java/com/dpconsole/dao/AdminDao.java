package com.dpconsole.dao;

import com.dpconsole.model.user.User;

public interface AdminDao {

	Object get(@SuppressWarnings("rawtypes") Class clazz, long id) throws Exception;

	void saveOrUpdate(Object entity) throws Exception;

	void delete(Object entity) throws Exception;

	User getUser(long id) throws Exception;

	void saveOrUpdateUser(User user) throws Exception;

}