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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.KitchenDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.kitchen.Kitchen;
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
	public PartialPage<KitchenItem> getKitchenItemsByCategory(long kitchenId, long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		return kitchenDao.getKitchenItemsByCategory(kitchenId, categoryId, sortName, isDecendingOrder, pageNo, pageSize);
	}

	@Override
	public PartialPage<KitchenDiscount> getKitchenDiscounts(long kitchenId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception {
		return kitchenDao.getKitchenDiscounts(kitchenId, sortName, isDecendingOrder, pageNo, pageSize);
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

	@Override
	public Map<String, KitchenItem> getAllKitchenItems(long kitchenId) throws Exception {
		List<KitchenItem> kItems = kitchenDao.getAllKitchenItems(kitchenId);
		Map<String, KitchenItem> kItemsMap = new HashMap<>();
		for(KitchenItem kItem : ListUtils.emptyIfNull(kItems)) {
			kItemsMap.put(kItem.getItem().getName(), kItem);
			for(String alias : kItem.getItem().getAliases()) {
				kItemsMap.put(alias, kItem);
			}
			//To avoid lazy exceptions
			//kItem.getItem().getSubCategory().getName();
			//kItem.getItem().getSubCategory().getCategory().getName();
		}
		/*ListUtils.emptyIfNull(kItems).forEach(kItem->{
			kItemsMap.put(kItem.getItem().getName(), kItem);
			for(String alias : kItem.getItem().getAliases()) {
				kItemsMap.put(alias, kItem);
			}
		});*/
		return kItemsMap;
	}

	@Override
	public Kitchen getKitchenById(long kitchenId) throws Exception {
		return (Kitchen) kitchenDao.get(Kitchen.class, kitchenId);
	}

	public KitchenDao getKitchenDao() {
		return kitchenDao;
	}

	public void setKitchenDao(KitchenDao kitchenDao) {
		this.kitchenDao = kitchenDao;
	}
}