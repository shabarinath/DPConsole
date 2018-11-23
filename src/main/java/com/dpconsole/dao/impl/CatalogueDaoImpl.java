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

import com.dpconsole.dao.CatalogueDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.Item;
import com.dpconsole.model.catalogue.SubCategory;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:29:54 PM
 */
public class CatalogueDaoImpl extends DaoImpl implements CatalogueDao {

	@Override
	public List<Category> getAllCategories() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubCategory> getSubCategories(long categoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartialPage<Item> getItemsByCategory(long categoryId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}