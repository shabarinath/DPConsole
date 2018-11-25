/*
 * This computer program is the confidential information and proprietary trade
 * secret of DP Console Project. Possessions and use of this program must
 * conform strictly to the license agreement between the user and
 * DP Console Project, and receipt or possession does not convey any rights
 * to divulge, reproduce, or allow others to use this program without specific
 * written authorization of DP Console Project.
 *
 * Copyright 2018 DP Console Project. All Rights Reserved.
 */
package com.dpconsole.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dpconsole.dao.KitchenDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.kitchen.KitchenDiscount;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:28:38 PM
 */
public class KitchenDaoImpl extends DaoImpl implements KitchenDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getKitchenCategories(long kitchenId) throws Exception {
		List<Category> categories = getHibernateTemplate().find("SELECT DISTINCT ki.item.subCategory.category FROM KitchenItem ki WHERE ki.kitchen.id = ? ki.item.subCategory.category = ? ORDER BY ki.item.subCategory.category.precedence", new Object[]{kitchenId, true});
		return !categories.isEmpty() ? categories : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PartialPage<KitchenItem> getKitchenItemsByCategory(long kitchenId, long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		List<Object> queryParams = new ArrayList<>();
		StringBuffer queryString = new StringBuffer("FROM KitchenItem ki WHERE kd.kitchen.active = ? AND ki.item.subCategory.category.active = ? AND ki.item.subCategory.active = ? AND ki.item.active = ?");
		queryParams.add(true);
		queryParams.add(true);
		queryParams.add(true);
		if (kitchenId > 0) {
			queryString.append(" AND kd.kitchen.id = ? ");
			queryParams.add(kitchenId);
		}
		if (categoryId > 0) {
			queryString.append(" AND ki.item.subCategory.category.id = ? ");
			queryParams.add(categoryId);
		}

		StringBuffer countQuery = new StringBuffer("SELECT COUNT(*) ").append(queryString);
		if(Utils.isEmpty(sortName)) {
			sortName = "kd.kitchen.id, ki.item.subCategory.category.precedence, ki.item.subCategory.precedence, ki.item.precedence";
		}

		queryString.append(" ORDER BY " + sortName + (isDecendingOrder ? " desc" : " asc"));

		return getHibernatePage(queryString.toString(), countQuery.toString(), queryParams, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PartialPage<KitchenDiscount> getKitchenDiscounts(long kitchenId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		List<Object> queryParams = new ArrayList<>();
		StringBuffer queryString = new StringBuffer("FROM KitchenDiscount kd WHERE kd.kitchen.active = ?");
		queryParams.add(true);
		if (kitchenId > 0) {
			queryString.append(" AND kd.kitchen.id = ? ");
			queryParams.add(kitchenId);
		}

		StringBuffer countQuery = new StringBuffer("SELECT COUNT(*) ").append(queryString);
		if(Utils.isEmpty(sortName)) {
			sortName = "kd.kitchen.id, kd.endTime";
		}

		queryString.append(" ORDER BY " + sortName + (isDecendingOrder ? " desc" : " asc"));

		return getHibernatePage(queryString.toString(), countQuery.toString(), queryParams, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KitchenItem> getAllKitchenItems(long kitchenId) throws Exception {
		List<KitchenItem> kItems = getHibernateTemplate().find("FROM KitchenItem ki WHERE kd.kitchen.id = ?", new Object[]{kitchenId});
		return !kItems.isEmpty() ? kItems : null;
	}

}