/*
 * This computer program is the confidential information and proprietary trade
 * secret of OpsRamp, Inc. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * OpsRamp, Inc., and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of OpsRamp, Inc.
 *
 * Copyright 2018 OpsRamp, Inc. All Rights Reserved.
 */
package com.dpconsole.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dpconsole.dao.Dao;
import com.dpconsole.model.HibernatePage;
import com.dpconsole.model.Persistent;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:32:52 PM
 */
public class DaoImpl implements Dao {

	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	private final Session getSession() throws DataAccessResourceFailureException, IllegalStateException {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}

	@Override
	public void saveOrUpdate(Persistent perObj) throws Exception {
		hibernateTemplate.saveOrUpdate(perObj.getClass().getName(), perObj);
	}

	@Override
	public void delete(Persistent perObj) throws Exception {
		hibernateTemplate.delete(perObj);
	}

	@Override
	public Object get(Class<? extends Persistent> clazz, long id) throws Exception {
		return hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String selectQuery, String countQuery, List parameters, int pageNo, int pageSize) throws Exception {
		return getHibernatePage(selectQuery, countQuery, parameters.toArray(), pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String selectQuery, String countQuery, int pageNo, int pageSize) throws Exception {
		return getHibernatePage(selectQuery, countQuery, (Object[]) null, pageNo, pageSize);
	}

	@SuppressWarnings("rawtypes")
	public HibernatePage getHibernatePage(String selectQueryString, String countQueryString, Object[] parameters, int pageNo, int pageSize) throws Exception {
		try {
			Query selectQuery = getSession().createQuery(selectQueryString);
			selectQuery.setCacheable(true);
			Query countQuery = getSession().createQuery(countQueryString);
			countQuery.setCacheable(true);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					selectQuery.setParameter(i, parameters[i]);
					countQuery.setParameter(i, parameters[i]);
				}
			}
			return new HibernatePage(selectQuery, countQuery, pageNo, pageSize);
		} catch (HibernateException e) {
			throw new Exception("Fail to execute query - " + e.getMessage(), e);
		}
	}

}