package com.dpconsole.dao.impl;

import java.util.List;

import com.dpconsole.dao.UserDao;
import com.dpconsole.model.user.User;

public class UserDaoImpl extends DaoImpl implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsername(String username) {
		List<User> users = getHibernateTemplate().find("FROM User user WHERE user.username = ?", new Object[]{username});
		return !users.isEmpty() ? users.get(0) : null;
	}

	@Override
	public User getUser(long id) throws Exception {
		return getHibernateTemplate().get(User.class, id);
	}

}