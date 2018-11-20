package com.dpconsole.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class DefaultDaoImpl implements DefaultDao {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object get(Class clazz, long id) throws Exception {
		return hibernateTemplate.get(clazz, id);
	}
}
