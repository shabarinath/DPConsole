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
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.dpconsole.dao.OrderDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.order.Order;
import com.dpconsole.utils.Utils;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:28:16 PM
 */
public class OrderDaoImpl extends DaoImpl implements OrderDao {

	@SuppressWarnings("unchecked")
	@Override
	public PartialPage<Order> getOrdersByCriteria(long kitchenId, String deliveryPartner, Date startCreatedTime,
			Date endCreatedTime, String sortName, boolean isDecendingOrder, int pageNo, int pageSize)
					throws Exception {
		List<Object> queryParams = new ArrayList<>();
		StringBuffer queryString = new StringBuffer("FROM Order o");
		if (kitchenId > 0) {
			queryString.append(" WHERE o.kitchen.id = ? ");
			queryParams.add(kitchenId);
		}
		if (StringUtils.isNotEmpty(deliveryPartner)) {
			if(CollectionUtils.isNotEmpty(queryParams)) {
				queryString.append(" AND ");
			} else {
				queryString.append(" WHERE ");
			}
			queryString.append(" o.deliveryPartner= ? ");
			queryParams.add(DeliveryPartner.valueOf(deliveryPartner));
		}
		if(startCreatedTime != null) {
			if(CollectionUtils.isNotEmpty(queryParams)) {
				queryString.append(" AND ");
			} else {
				queryString.append(" WHERE ");
			}
			queryString.append(" o.orderedTime >= ?");
			queryParams.add(startCreatedTime);
		}
		if(endCreatedTime != null) {
			queryString.append(" AND o.orderedTime <= ?");
			queryParams.add(endCreatedTime);
		}

		StringBuffer countQuery = new StringBuffer("SELECT COUNT(*) ").append(queryString);
		if(Utils.isEmpty(sortName)) {
			sortName = "o.kitchen.id, o.orderedTime";
		}

		queryString.append(" ORDER BY " + sortName + (isDecendingOrder ? " desc" : " asc"));

		return getHibernatePage(queryString.toString(), countQuery.toString(), queryParams, pageNo, pageSize);
	}

}