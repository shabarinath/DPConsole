package com.dpconsole.utils;

import java.text.DecimalFormat;

import com.dpconsole.model.kitchen.KitchenDeliveryPartner;
import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 18-Dec-2018 11:57:10 PM 2018 
 */

public class CommissionUtil {

	public double getPostCommissionAmount(KitchenDeliveryPartner kdp, Order order) throws Exception {
		
		/*																	 (Zomato Commission)
		 * Item Name   ItemPrice  ZomatoPays CustomerPrice Discount20%	5%GST Net ZomatoFee18% ZomatoGST18%	SupportCharge(TDS1%) ConvenienceFee2.275%	ZomatoCommission	Zomato Pays	Preparation Cost	Net Margin
		 * Egg Biryani 195	      125.56	 163.8	       39	         7.8  156 28.08	       5.05	        1.56	             3.55					38.24				125.56		
		 * 
		 */
		DecimalFormat df = new DecimalFormat("#.00");
		double customerPrice = getCustomerPrice(kdp, order);
		double GSTAmount = getGSTAmount(order.getTotalCost(), getDiscountAmount(order, kdp));
		double netAmount = customerPrice - GSTAmount;
		double zomatoCommissionFeeAmount = getZomatoCommissoinFeeAmount(netAmount, kdp);
		double zomatoGST = Double.parseDouble(df.format((zomatoCommissionFeeAmount*18)/100));  //NOTE: Here GST 18% is fixed.
		double supportCharges = (netAmount*kdp.getSupportChargesPercentage())/100;
		double convenienceFee = Double.parseDouble(df.format((netAmount*kdp.getConvenienceFeePercentage())/100));
		double zomatoTotalComission = Double.parseDouble(df.format(zomatoCommissionFeeAmount + zomatoGST + supportCharges + convenienceFee));
		double zomatoPays = customerPrice - zomatoTotalComission;
		return zomatoPays;
	}

	private double getZomatoCommissoinFeeAmount(double netAmount, KitchenDeliveryPartner kdp) {
		double commissionPercentage = kdp.getCommissionPercentage();
		double comissionAmount = (netAmount * commissionPercentage)/100;
		return comissionAmount;
	}

	private static double getCustomerPrice(KitchenDeliveryPartner kdp, Order order) throws Exception {
		double itemPrice = order.getTotalCost();
		double discountAmount = getDiscountAmount(order, kdp);
		double gstAmount = getGSTAmount(itemPrice, discountAmount);
		double customerPrice = (itemPrice - discountAmount)+gstAmount;
		return customerPrice;
	}

	private static double getDiscountAmount(Order order, KitchenDeliveryPartner kdp) {
		double itemPrice = order.getTotalCost();
		double discountPercentage = kdp.getDiscountPercentage();
		double maxDiscountAmount = kdp.getMaxDiscountAmount();
		double discountAmount = (itemPrice * discountPercentage)/100;
		discountAmount = (discountAmount <= maxDiscountAmount) ?  discountAmount : maxDiscountAmount;
		return discountAmount;
	}

	private static double getGSTAmount(double itemPrice, double discountAmount) {
		//NOTE : Here GST 5% is fixed for all DP's
		return ((itemPrice - discountAmount)*5)/100;
	}
	
}

