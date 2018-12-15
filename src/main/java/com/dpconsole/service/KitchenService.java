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
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.catalogue.Category;
import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenDiscount;
import com.dpconsole.model.kitchen.KitchenItem;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 3:35:09 PM
 */
@Service
public interface KitchenService {

	List<Category> getKitchenCategories(long kitchenId) throws Exception;

	PartialPage<KitchenItem> getKitchenItemsByCategory(long kitchenId, long categoryId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception;

	PartialPage<KitchenDiscount> getKitchenDiscounts(long kitchenId, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception;

	void saveOrUpdateKitchenItem(KitchenItem kitchenItem) throws Exception;

	void saveOrUpdateKitchenDiscount(KitchenDiscount kitchenDiscount) throws Exception;

	Map<String, KitchenItem> getAllKitchenItems(long kitchenId, DeliveryPartner dp) throws Exception;

	Kitchen getKitchenById(long kitchenId) throws Exception;

	List<Kitchen> getAllKitchens() throws Exception;

}