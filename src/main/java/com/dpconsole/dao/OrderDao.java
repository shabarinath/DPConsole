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
package com.dpconsole.dao;

import java.util.Date;

import com.dpconsole.model.PartialPage;
import com.dpconsole.model.order.Order;

/**
 * @author nanda.malve
 * created on 23-Nov-2018 4:27:13 PM
 */
public interface OrderDao extends Dao {

	PartialPage<Order> getOrdersByCriteria(long kitchenId, String deliveryPartner, Date startCreatedTime,
			Date endCreatedTime, String sortName, boolean isDecendingOrder, int pageNo, int pageSize) throws Exception;

}