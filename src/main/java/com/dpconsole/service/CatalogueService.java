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
package com.dpconsole.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.catalogue.Item;
import com.dpconsole.model.catalogue.SubCategory;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 3:34:06 PM
 */
@Service
public interface CatalogueService {

	List<Category> getAllCategories() throws Exception;

	List<SubCategory> getSubCategories(long categoryId) throws Exception;

	PartialPage<Item> getItemsByCategory(long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception;

	void saveOrUpdateItem(Item item) throws Exception;

	List<SubCategory> getAllSubCategories() throws Exception;

}