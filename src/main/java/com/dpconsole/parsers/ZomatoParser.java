package com.dpconsole.parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.mail.Message;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;
import com.dpconsole.model.order.OrderItem;
import com.dpconsole.utils.Utils;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:52:46 pm 2018
 */
public class ZomatoParser implements Parser<List<Message>> {

	private static final String COMMA_DELIM=",";
	private static final String PIPE_DELIM="|";
	private static final String NEW_LINE_REGEX="[\\\r\\\n]+";
	private static final String ESCAPE="\\";
	private static final String HTML_COMMENTS_ESCAPCE_REGEX = "<!--.*?-->";
	private static final String HTML_CLEAN_REGEX="<[^>]+>";
	private static final String RUPEE_UNICODE="\\u20B9";

	private static final Logger logger = LoggerFactory.getLogger(ZomatoParser.class);

	@Override
	public List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, List<Message> messages) throws Exception {
		List<Order> orders = new ArrayList<>();
		for(Message message : messages) {
			try {
				if(message == null) {
					continue;
				}
				long start = System.currentTimeMillis();
				String content = ZomatoParser.getOrderContent(message);
				if(StringUtils.isEmpty(content)) {
					logger.info("content is empty");
					continue;
				}
				String orderId=null;
				String totalAmount = null; 
				double restaurantPromo;
				double piggybankCoins;
				Order order = new Order();
				orderId = getAttributeValues(content,EmailAttribute.ORDER_ID,EmailAttribute.Date);
				order.setDeliveryPartnerOrderId(orderId);
				Date orderedTime = message.getReceivedDate();
				setOrderItems(content, kitchenItems, order);
				restaurantPromo = getRestaurantPromo(content, order);
				piggybankCoins = getPiggyBankCoins(content, order);
				totalAmount = getTotalAmount(content, order);
				order.setRestaurantPromo(restaurantPromo);
				order.setPiggybankCoins(piggybankCoins);
				order.setDeliveryPartner(DeliveryPartner.ZOMATO);
				order.setOrderedTime(Utils.convertDateToGMT(orderedTime, TimeZone.getTimeZone("GMT")));
				order.setStatus("DELIVERED");
				order.setKitchen(kitchen);
				order.setParsedTime(Utils.getSystemTimeInGMT());
				order.setTotalCost(Double.parseDouble(totalAmount));
				order.setDeliveryPartnerOrderId(orderId);
				order.setKitchen(kitchen);
				orders.add(order);
				long end = System.currentTimeMillis();
				long turnAroundTime = end - start;
				logger.info("Turn Around Time for parsing for orderId: "+orderId+" "+turnAroundTime+" ms");
			}catch(Exception e) {
				logger.error("Exception occured :", e);
			}
		}
		return orders;

	}

	private String getTotalAmount(String content, Order order) {
		String totalAmount = "";
		EmailAttribute attribute = null;
		try {
			if(StringUtils.isNotEmpty(content)) {
				attribute = (content.contains(EmailAttribute.PREPAID.getName()) ? EmailAttribute.PREPAID : 
					(content.contains(EmailAttribute.COD.getName()) ? EmailAttribute.COD:null));
				order.setPaymentType(attribute.getName());
				if(content.contains(EmailAttribute.CASH_TO_BE_COLLECTED_FROM.getName())) {
					totalAmount = getAttributeValues(content, attribute, EmailAttribute.CASH_TO_BE_COLLECTED_FROM);
				} else {
					totalAmount = getAttributeValues(content, attribute, EmailAttribute.ZOMATO_FOOTER);
				}
			}
		}catch(Exception e) {
			setManualReviewDetail(order, "Total Amount Parsing Failed: ");
		}
		return totalAmount.replaceAll(RUPEE_UNICODE, "").replaceAll(",", "").trim();
	}

	private void setOrderItems(String content, Map<String, KitchenItem> kitchenItems, Order order) {
		String items = "";
		if(StringUtils.isNotEmpty(content)) {
			int beginIndex = content.indexOf(EmailAttribute.Date.getName())+EmailAttribute.Date.getName().length()+10;
			if(content.contains(EmailAttribute.TAXES.getName())) {
				int endIndex = content.indexOf(EmailAttribute.TAXES.getName());
				items = content.substring(beginIndex, endIndex);
			} else if(content.contains(EmailAttribute.RESTUARANT_PROMO.getName())) {
				int endIndex = content.indexOf(EmailAttribute.RESTUARANT_PROMO.getName());
				items = content.substring(beginIndex, endIndex);
			} else if(content.contains(EmailAttribute.PIGGY_BANK_DISCOUNT.getName())) {
				int endIndex = content.indexOf(EmailAttribute.PIGGY_BANK_DISCOUNT.getName());
				items = content.substring(beginIndex, endIndex);
			} 
			else if(content.contains(EmailAttribute.PAID_BY.getName())) {
				int endIndex = content.indexOf(EmailAttribute.PAID_BY.getName());
				items = content.substring(beginIndex, endIndex);
			}
		}
		String[] sanitizedItems = sanitizeItemsContent(StringUtils.trim(items)).split(COMMA_DELIM);
		/*
		 * SanitizedItems:
		 * [Chilli Chicken with Noodles Combo, (1 x ₹220), ₹220, Chef Special Chicken with Fried Rice Combo, (1 x ₹220), ₹220]
		 */
		List<OrderItem> orderItems = new ArrayList<>();
		for(String str : sanitizedItems) {
			if(str.length()<3){
				setManualReviewDetail(order, "Reflow OrderId ");
				continue;
			}
			String itemName = StringUtils.trim(str.split(ESCAPE+PIPE_DELIM)[0]);
			KitchenItem kitchenItem = kitchenItems.get(itemName);
			if(StringUtils.isEmpty(itemName) || null == kitchenItem) {
				setManualReviewDetail(order, "Item Name: "+itemName+" missing!!");
				continue;
			}
			String quantity = StringUtils.trim((str.split(ESCAPE+PIPE_DELIM)[1]).split("x")[0].replace("(", ""));
			String dpReceivedTotalPricePerItem = StringUtils.trim(str.split(ESCAPE+PIPE_DELIM)[2]).replaceAll(RUPEE_UNICODE, "");
			Double dpReceivedUnitPrice = (Double.parseDouble(dpReceivedTotalPricePerItem)/Integer.parseInt(quantity));
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(Integer.parseInt(quantity));
			orderItem.setManufacturingPrice(kitchenItem.getManufacturingPrice());
			orderItem.setMarketPrice(kitchenItem.getMarketPrice());
			orderItem.setDpReceivedPrice(dpReceivedUnitPrice);  //TODO: is this unit price
			orderItem.setKitchenItem(kitchenItem);
			orderItems.add(orderItem);
			order.setOrderItems(orderItems);
		}
	}

	/**
	 * Takes items content and processes and
	 * returns content which is suitable for parsing.
	 *
	 * @param itemsSection
	 * @return
	 */
	private String sanitizeItemsContent(String itemsSection) {
		if(itemsSection.contains(EmailAttribute.CUSTOMIZE.getName())) {
			itemsSection = removeCustomizeBlock(itemsSection);
		} else if(itemsSection.contains(EmailAttribute.QUANTITY.getName())) {
			itemsSection = removeQuantityBlock(itemsSection);
		}
		String[] splittedStr = itemsSection.trim().replaceAll(NEW_LINE_REGEX, COMMA_DELIM).split(COMMA_DELIM);
		StringBuffer itemsBuffer = new StringBuffer();
		int count = 1;
		for(String str : splittedStr) {
			if(!StringUtils.isWhitespace(str)) {
				if(StringUtils.isNotEmpty(itemsBuffer.toString())) {
					if(count %3 ==0) {
						itemsBuffer.append(COMMA_DELIM);
					} else {
						itemsBuffer.append(PIPE_DELIM);
					}
					count = count+1;
				}
				itemsBuffer.append(str.trim());
			}
		}
		return itemsBuffer.toString();
	}

	private String removeQuantityBlock(String itemsSection) {
		if(StringUtils.isNotEmpty(itemsSection)) {
			String firstPart = itemsSection.split(EmailAttribute.QUANTITY.getName())[0];
			String secondPart = itemsSection.split(EmailAttribute.QUANTITY.getName())[1];
			if(secondPart.contains("]")) {
				secondPart = secondPart.substring(secondPart.indexOf("]"), secondPart.length()).replaceAll("]", "");
			}
			return firstPart+secondPart;
		}
		return "";
	}

	/**
	 * Returns items order block after
	 * removing customize block.
	 *
	 * @param itemsSection
	 * @return
	 */
	private String removeCustomizeBlock(String itemsSection) {
		if(StringUtils.isNotEmpty(itemsSection)) {
			String firstPart = itemsSection.split(EmailAttribute.CUSTOMIZE.getName())[0];
			String secondPart = itemsSection.split(EmailAttribute.CUSTOMIZE.getName())[1];
			secondPart = secondPart.substring(secondPart.indexOf("("), secondPart.length());
			return firstPart+secondPart;
		}
		return "";
	}

	/**
	 * Returns element value base on
	 * predecessor and successor elements
	 *
	 * @param content
	 * @param matcher
	 * @param succeeding
	 * @return
	 */
	private static String getAttributeValues(String content, EmailAttribute matcher, EmailAttribute succeeding) {
		String attributeValue = "";
		if(null != content && content.length() >0) {
			attributeValue = StringUtils.substringBetween(content, matcher.getName(), succeeding.getName());
		}
		return (attributeValue != null) ?attributeValue.trim():attributeValue;
	}

	private static String getOrderContent(Message message) throws Exception {
		String content ="";
		Object o = message.getContent();
		if (o instanceof String) {
			content = ((String) o).replaceAll(HTML_COMMENTS_ESCAPCE_REGEX, "").replaceAll(HTML_CLEAN_REGEX, "");
			int index = content.indexOf(EmailAttribute.CUSTOMER_NAME.getName());
			content = (index>0) ? content.substring(index):"";
		}
		return content;
	}
	
	private double getPiggyBankCoins(String content, Order order) {
		double piggyBankCoins=0;
		try {
			if(StringUtils.isNotEmpty(content) && content.contains(EmailAttribute.PIGGY_BANK_DISCOUNT.getName())) {
				String piggyBankCoinsStr = getAttributeValues(content, EmailAttribute.PIGGY_BANK_DISCOUNT, EmailAttribute.PAID_BY)
						.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(RUPEE_UNICODE, "").replaceAll(",", "");
				piggyBankCoins = Double.parseDouble(piggyBankCoinsStr);
			}
		}catch(Exception e) {
			setManualReviewDetail(order, "Piggy Bank Coins error!!");
			logger.error("Exception while parsing restaurant promo reason: ", e);
		}
		return piggyBankCoins;
	}

	private double getRestaurantPromo(String content, Order order) {
		double restaurantPromo = 0;
		String promoStr = "";
		if(StringUtils.isNotEmpty(content)) {
			try {
				if(content.contains(EmailAttribute.ZOMATO_PROMO.getName())) {
					promoStr = getAttributeValues(content, EmailAttribute.RESTUARANT_PROMO, EmailAttribute.ZOMATO_PROMO);
				} else if(content.contains(EmailAttribute.PIGGY_BANK_DISCOUNT.getName())) {
					promoStr = getAttributeValues(content, EmailAttribute.RESTUARANT_PROMO, EmailAttribute.PIGGY_BANK_DISCOUNT);
				} else if(content.contains(EmailAttribute.PAID_BY.getName())) {
					promoStr = getAttributeValues(content, EmailAttribute.RESTUARANT_PROMO, EmailAttribute.PAID_BY);
				}
				if(StringUtils.isNotEmpty(promoStr)) {
					promoStr = promoStr.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll(RUPEE_UNICODE, "").replaceAll(",", "");
					restaurantPromo = Double.parseDouble(promoStr);
				}
			}catch(Exception e) {
				setManualReviewDetail(order, "Restaurant promo parsing error!!");
				logger.error("Exception while parsing restaurant promo reason: ", e);
			}
		}
		return restaurantPromo;
	}
	
	private void setManualReviewDetail(Order ord, String comments) {
		ord.setManualReview(true);
		StringBuffer cmts = new StringBuffer(ord.getDeliveryPartnerOrderId()+" : "+ord.getManualReviewComments().toString());
		cmts.append(" | ").append(comments);
		ord.setManualReviewComments(cmts.toString());
	}
}