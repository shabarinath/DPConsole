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

import java.util.Date;

import com.dpconsole.dao.OrderDao;
import com.dpconsole.model.PartialPage;
import com.dpconsole.model.order.Order;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:28:16 PM
 */
public class OrderDaoImpl extends DaoImpl implements OrderDao {

	@Override
	public PartialPage<Order> getOrdersByCriteria(long kitchenId, long deliveryParnerId, Date startDate, Date endDate)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}