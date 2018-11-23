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
package com.dpconsole.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:15:23 PM
 */
public class HibernatePage<E> implements PartialPage<E> {


	private static Logger logger = LoggerFactory.getLogger(HibernatePage.class);

	private List<E> results;

	private int pageSize;

	private int page;

	private int totalResults = -1;

	public HibernatePage() {
		results = new ArrayList<E>();
	}

	public HibernatePage(List<E> results , int page,int pageSize,int totalResults){
		this.results=results;
		this.page=page;
		this.pageSize=pageSize;
		this.totalResults=totalResults;

	}

	public HibernatePage(Criteria selectCriteria, Criteria countCriteria, int page, int pageSize) throws Exception {
		if (page <= 0) {
			throw new Exception("Invalid page number: " + page + ".");
		}

		if (pageSize <= 0) {
			throw new Exception("Invalid page size: " + page + ".");
		}

		this.page = page;
		this.pageSize = pageSize;
		try {
			this.totalResults = ((Long) countCriteria.uniqueResult()).intValue();
			/*
			 * We set the max results to one more than the specfied pageSize to
			 * determine if any more results exist (i.e. if there is a next page
			 * to display). The result set is trimmed down to just the pageSize
			 * before being displayed later (in getList()).
			 */
			results = selectCriteria.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize + 1).list();
		} catch (HibernateException e) {
			throw new Exception("Failed to execute query.", e);
		}

	}

	/**
	 * Construct a new Page.
	 *
	 * @param query
	 *            the Hibernate Query
	 * @param page
	 *            the page number
	 * @param pageSize
	 *            the number of results to display on the page
	 * @throws DaoException
	 */
	public HibernatePage(Query selectQuery, Query countQuery, int page, int pageSize) throws Exception {
		if (page <= 0) {
			throw new Exception("Invalid page number: " + page + ".");
		}

		if (pageSize <= 0) {
			throw new Exception("Invalid page size: " + page + ".");
		}

		this.page = page;
		this.pageSize = pageSize;
		try {
			Long count = (Long) countQuery.uniqueResult();
			this.totalResults =  count != null ? count.intValue() : 0;

			/*
			 * We set the max results to one more than the specfied pageSize to
			 * determine if any more results exist (i.e. if there is a next page
			 * to display). The result set is trimmed down to just the pageSize
			 * before being displayed later (in getList()).
			 */
			results = selectQuery.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize + 1).list();
		} catch (HibernateException e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Failed to execute query.", e);
		}

	}

	public boolean isFirstPage() {
		return page == 1;
	}

	public boolean isLastPage() {
		return page >= getLastPageNumber();
	}

	@Override
	public boolean hasNextPage() {
		return results != null && results.size() > pageSize;
	}

	@Override
	public boolean isNextPage() {
		return results != null && results.size() > pageSize;
	}

	public boolean hasPreviousPage() {
		return page > 1;
	}

	@Override
	public int getLastPageNumber() {
		double totalResults = new Integer(getTotalResults()).doubleValue();
		int mod = (int) (totalResults % pageSize);
		return mod == 0 ? new Double(Math.floor(totalResults / pageSize)).intValue() : new Double(Math.floor(totalResults / pageSize) + 1).intValue();
	}

	@Override
	public List<E> getList() {
		/*
		 * Since we retrieved one more than the specified pageSize when the
		 * class was constructed, we now trim it down to the pageSize if a next
		 * page exists.
		 */
		return hasNextPage() ? results.subList(0, pageSize) : results;
	}

	@Override
	public int getTotalResults() {
		return totalResults;
	}

	@Override
	public int getFirstResultNumber() {
		return (page - 1) * pageSize + 1;
	}

	@Override
	public int getLastResultNumber() {
		int fullPage = getFirstResultNumber() + pageSize - 1;
		return getTotalResults() < fullPage ? getTotalResults() : fullPage;
	}

	@Override
	public int getNextPageNumber() {
		return page + 1;
	}

	@Override
	public int getPreviousPageNumber() {
		return page - 1;
	}

	@Override
	public int getTotalPages() {
		return (pageSize > 0)?((totalResults + (pageSize - 1)) / pageSize):0;
	}

	@Override
	public int getPageNo() {
		return page;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

}