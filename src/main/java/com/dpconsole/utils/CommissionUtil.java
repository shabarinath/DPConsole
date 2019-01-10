package com.dpconsole.utils;

import java.text.DecimalFormat;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.KitchenDeliveryPartner;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;

/**
 * @author SHABARINATH
 * 18-Dec-2018 11:57:10 PM 2018 
 */

public class CommissionUtil {

	private static final Logger logger = LoggerFactory.getLogger(CommissionUtil.class);
	
	public double getPostCommissionAmount(KitchenDeliveryPartner kdp, Order order) throws Exception {
		
		/*																	 (Zomato Commission)
		 * Item Name   ItemPrice  ZomatoPays CustomerPrice Discount20%	5%GST Net ZomatoFee18% ZomatoGST18%	SupportCharge(TDS1%) ConvenienceFee2.275%	ZomatoCommission	Zomato Pays	Preparation Cost	Net Margin
		 * Egg Biryani 195	      125.56	 163.8	       39	         7.8  156 28.08	       5.05	        1.56	             3.55					38.24				125.56		
		 * 
		 */
		DecimalFormat df = new DecimalFormat("#.00");
		
		double itemsPriceWithoutDeducations = getItemsPriceWithoutDeducations(order);
		double customerPrice = getCustomerPrice(order);
		double GSTAmount = getGSTAmount((itemsPriceWithoutDeducations), getDiscountAmount(order));
		double netAmount = customerPrice - GSTAmount;
		double zomatoCommissionFeeAmount = getZomatoCommissoinFeeAmount(netAmount, kdp);
		double zomatoGST = Double.parseDouble(df.format((zomatoCommissionFeeAmount*18)/100));  //NOTE: Here GST 18% is fixed.
		double tcs = (netAmount*kdp.getSupportChargesPercentage())/100;
		double convenienceFee = Double.parseDouble(df.format((netAmount*kdp.getConvenienceFeePercentage())/100));
		double supportCharges = order.getPiggybankCoins() >0 ?  (netAmount * 2.95)/100 : 0; 
		double zomatoTotalComission = Double.parseDouble(df.format(zomatoCommissionFeeAmount + zomatoGST + supportCharges + convenienceFee+ tcs));
		double zomatoPays = customerPrice - zomatoTotalComission;
		logger.error("OrderId:"+order.getDeliveryPartnerOrderId()+"itemsPriceWithoutDeducations"+itemsPriceWithoutDeducations+
				"customerPrice:"+customerPrice+"tcs:"+tcs+"zomatoPays: "+zomatoPays +" "+order.getDeliveryPartnerOrderId()+
				"GSTAmount:"+GSTAmount+"netAmount: "+netAmount+"zomatoCommissionFeeAmount: "+zomatoCommissionFeeAmount+
				"zomatoGST: "+zomatoGST+"convenienceFee: "+convenienceFee+
				"supportCharges: "+supportCharges+
				"zomatoTotalComission: "+zomatoTotalComission+
				"zomatoPays: "+zomatoPays);
		return zomatoPays;
	}

	private double getZomatoCommissoinFeeAmount(double netAmount, KitchenDeliveryPartner kdp) {
		double commissionPercentage = kdp.getCommissionPercentage();
		double comissionAmount = (netAmount * commissionPercentage)/100;
		return comissionAmount;
	}

	private static double getCustomerPrice(Order order) throws Exception {
		double totalAmount = getItemsPriceWithoutDeducations(order);
		double discountAmount = getDiscountAmount(order);
		double gstAmount = getGSTAmount(totalAmount, discountAmount);
		double customerPaidAmount = (totalAmount - discountAmount)+gstAmount;
		return customerPaidAmount;
	}
	
	private static double getItemsPriceWithoutDeducations(Order order) {
		double totalAmount = 0;
		for(OrderItem item : ListUtils.emptyIfNull(order.getOrderItems())) {
			int quantity = item.getQuantity();
			double unitPrice = item.getKitchenItem().getMarketPrice();
			totalAmount = totalAmount + (unitPrice * quantity);
		}
		return totalAmount;
	}

	private static double getDiscountAmount(Order order) {
		double discountAmount = order.getRestaurantPromo()+order.getZomatoPromo()+order.getPiggybankCoins();
		return discountAmount;
	}

	private static double getGSTAmount(double totalAmount, double discountAmount) {
		//NOTE : Here GST 5% is fixed for all DP's
		return ((totalAmount - discountAmount)*5)/100;
	}
	
}

