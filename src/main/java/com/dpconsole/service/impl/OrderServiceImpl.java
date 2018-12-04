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
	public PartialPage<Order> getOrdersByCriteria(long kitchenId, String deliveryPartner, Date startCreatedTime,
			Date endCreatedTime, String sortName, boolean isDecendingOrder, int pageNo, int pageSize)
					throws Exception {
		return orderDao.getOrdersByCriteria(kitchenId, deliveryPartner, startCreatedTime, endCreatedTime, sortName, isDecendingOrder, pageNo, pageSize);
	}

}
