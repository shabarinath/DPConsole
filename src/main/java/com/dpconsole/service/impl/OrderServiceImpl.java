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

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dpconsole.dao.OrderDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.order.Order;
import com.dpconsole.service.OrderService;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 3:36:13 PM
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Throwable.class)
	public void saveOrUpdateOrder(Order order) throws Exception {
		orderDao.saveOrUpdate(order);
	}

	@Override
	public PartialPage<Order> getOrdersByCriteria(long kitchenId, long deliveryPartnerId, Date startCreatedTime,
			Date endCreatedTime, String sortName, boolean isDecendingOrder, int pageNo, int pageSize)
					throws Exception {
		return orderDao.getOrdersByCriteria(kitchenId, deliveryPartnerId, startCreatedTime, endCreatedTime, sortName, isDecendingOrder, pageNo, pageSize);
	}

}
