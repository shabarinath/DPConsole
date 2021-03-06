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
package com.dpconsole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.CatalogueDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.Item;
import com.dpconsole.model.catalogue.SubCategory;
import com.dpconsole.service.CatalogueService;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 3:37:44 PM
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class CatalogueServiceImpl implements CatalogueService {

	@Autowired
	private CatalogueDao catalogueDao;

	@Override
	public List<Category> getAllCategories() throws Exception {
		return catalogueDao.getAllCategories();
	}

	@Override
	public List<SubCategory> getSubCategories(long categoryId) throws Exception {
		return catalogueDao.getSubCategories(categoryId);
	}

	@Override
	public PartialPage<Item> getItemsByCategory(long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		return catalogueDao.getItemsByCategory(categoryId, sortName, isDecendingOrder, pageNo, pageSize);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateItem(Item item) throws Exception {
		catalogueDao.saveOrUpdate(item);
	}

	@Override
	public List<SubCategory> getAllSubCategories() throws Exception {
		return catalogueDao.getAllSubCategories();
	}

	@Override
	public SubCategory getSubCategoryById(long id) throws Exception {
		return (SubCategory)catalogueDao.get(SubCategory.class, id);
	}

}