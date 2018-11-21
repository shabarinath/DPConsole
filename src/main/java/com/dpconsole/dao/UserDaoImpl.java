package com.dpconsole.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dpconsole.model.user.User;

public class UserDaoImpl implements UserDao {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public User findByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> users = hibernateTemplate.find("from User user where user.username=?",new Object[]{username});
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

}