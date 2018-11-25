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

import java.util.ArrayList;
import java.util.List;

import com.dpconsole.dao.CatalogueDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.Item;
import com.dpconsole.model.catalogue.SubCategory;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:29:54 PM
 */
public class CatalogueDaoImpl extends DaoImpl implements CatalogueDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() throws Exception {
		List<Category> categories = getHibernateTemplate().find("FROM Category c WHERE c.active = ? ORDER BY c.precedence", new Object[]{true});
		return !categories.isEmpty() ? categories : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubCategory> getSubCategories(long categoryId) throws Exception {
		List<SubCategory> subCategories = getHibernateTemplate().find("FROM SubCategory sc WHERE sc.category.id = ? AND sc.active = ? ORDER BY sc.precedence", new Object[]{categoryId, true});
		return !subCategories.isEmpty() ? subCategories : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PartialPage<Item> getItemsByCategory(long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		List<Object> queryParams = new ArrayList<>();
		StringBuffer queryString = new StringBuffer("FROM Item i WHERE i.subCategory.active = ? AND i.active = ?");
		queryParams.add(true);
		queryParams.add(true);
		if (categoryId > 0) {
			queryString.append(" AND i.subCategory.category.id = ? ");
			queryParams.add(categoryId);
		}

		StringBuffer countQuery = new StringBuffer("SELECT COUNT(*) ").append(queryString);
		if(Utils.isEmpty(sortName)) {
			sortName = "i.subCategory.category.precedence, i.subCategory.precedence, i.precedence";
		}

		queryString.append(" ORDER BY " + sortName + (isDecendingOrder ? " desc" : " asc"));

		return getHibernatePage(queryString.toString(), countQuery.toString(), queryParams, pageNo, pageSize);
	}

}