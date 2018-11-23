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
package com.dpconsole.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.KitchenDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.kitchen.KitchenDiscount;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.service.KitchenService;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 3:35:41 PM
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class KitchenServiceImpl implements KitchenService {

	@Autowired
	private KitchenDao kitchenDao;

	@Override
	public List<Category> getKitchenCategories(long kitchenId) throws Exception {
		return kitchenDao.getKitchenCategories(kitchenId);
	}

	@Override
	public PartialPage<KitchenItem> getKitchenItemsByCategory(long kitchenId, long categoryId) throws Exception {
		return kitchenDao.getKitchenItemsByCategory(kitchenId, categoryId);
	}

	@Override
	public PartialPage<KitchenDiscount> getKitchenDiscounts(long kitchenId) throws Exception {
		return kitchenDao.getKitchenDiscounts(kitchenId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateKitchenItem(KitchenItem kitchenItem) throws Exception {
		kitchenDao.saveOrUpdate(kitchenItem);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateKitchenDiscount(KitchenDiscount kitchenDiscount) throws Exception {
		kitchenDao.saveOrUpdate(kitchenDiscount);
	}

}