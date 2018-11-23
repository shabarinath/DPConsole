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
package com.dpconsole.dao;

import java.util.List;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.kitchen.KitchenDiscount;
import com.dpconsole.model.kitchen.KitchenItem;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:27:03 PM
 */
public interface KitchenDao extends Dao {

	List<Category> getKitchenCategories(long kitchenId) throws Exception;

	PartialPage<KitchenItem> getKitchenItemsByCategory(long kitchenId, long categoryId) throws Exception;

	PartialPage<KitchenDiscount> getKitchenDiscounts(long kitchenId) throws Exception;

}