package com.dpconsole.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dpconsole.model.user.User;

public class AdminDaoImpl implements AdminDao {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object get(Class clazz, long id) throws Exception {
		return hibernateTemplate.get(clazz, id);
	}

	@Override
	public void saveOrUpdate(Object entity) throws Exception {
		hibernateTemplate.saveOrUpdate(entity);
	}

	@Override
	public void delete(Object entity) throws Exception {
		hibernateTemplate.delete(entity);
	}

	@Override
	public User getUser(long id) throws Exception {
		return hibernateTemplate.get(User.class, id);
	}

	@Override
	public void saveOrUpdateUser(User user) throws Exception {
		hibernateTemplate.saveOrUpdate(user);
	}
}