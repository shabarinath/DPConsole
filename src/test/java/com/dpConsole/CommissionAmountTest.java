package com.dpConsole;

import com.dpconsole.model.kitchen.KitchenDeliveryPartner;
import com.dpconsole.model.order.Order;
import com.dpconsole.utils.CommissionUtil;

/**
 * @author SHABARINATH
 * 19-Dec-2018 1:08:31 AM 2018 
 */

public class CommissionAmountTest {

	public static void main(String argv[]) throws Exception {
		CommissionUtil util = new CommissionUtil();
		KitchenDeliveryPartner kdp = new KitchenDeliveryPartner();
		kdp.setCommissionPercentage(15);
		kdp.setConvenienceFeePercentage(2.275);
/*		kdp.setDiscountPercentage(0);
		kdp.setMaxDiscountAmount(80);*/
		kdp.setSupportChargesPercentage(1);
		Order order = new Order();
		/*order.setZomatoPromo(32);*/
		order.setRestaurantPromo(100);
		order.setTotalCost(420.0);
		util.getPostCommissionAmount(kdp, order);
	}

}

