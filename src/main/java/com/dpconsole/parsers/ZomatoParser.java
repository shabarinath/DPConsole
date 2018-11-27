package com.dpconsole.parsers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	private static final Logger logger = LoggerFactory.getLogger(ZomatoParser.class);

	@Override
	public List<Order> parse(Kitchen kitchen, Map<String, KitchenItem> kitchenItems, List<Message> messages) throws Exception {
		List<Order> orders = new ArrayList<>();
		for(Message message : messages) {
			if(message == null) {
				continue;
			}
			String content = ZomatoParser.getOrderContent(message);
			if(StringUtils.isEmpty(content)) {
				continue;
			}
			String orderId=null;
			String dateStr=null;
			String totalAmount = null;
			Order order = new Order();
			orderId = getAttributeValues(content,EmailAttribute.ORDER_ID,EmailAttribute.Date);
			dateStr = getOrderDate(content); //2018-11-21
			Date orderedTime = Utils.convertStringToDate("yyyy-MM-dd", dateStr);
			setOrderItems(content, kitchenItems, order);
			totalAmount = getTotalAmount(content, order).replaceAll("₹", "");
			order.setDeliveryPartner(DeliveryPartner.ZOMATO);
			order.setOrderedTime(orderedTime);
			//order.setStatus(status);//TODO: Parse this
			order.setDeliveryPartnerOrderId(orderId);
			order.setKitchen(kitchen);
			order.setParsedTime(Utils.getSystemTimeInGMT());
			order.setTotalCost(Double.parseDouble(totalAmount));
			order.setDeliveryPartnerOrderId(orderId);
			order.setKitchen(kitchen);
			orders.add(order);
			System.out.println("orderId: " +orderId.trim()+" date: "+dateStr.trim()+" totalAmount: "+totalAmount);
		}
		return orders;

	}

	private String getTotalAmount(String content, Order order) {
		String totalAmount = "";
		if(StringUtils.isNotEmpty(content)) {
			if(content.contains(EmailAttribute.PREPAID.getName())) {
				order.setPaymentType(EmailAttribute.PREPAID.getName());
				totalAmount = getAttributeValues(content, EmailAttribute.PREPAID, EmailAttribute.ZOMATO_FOOTER);
			}
			else if(content.contains(EmailAttribute.COD.getName())) {
				order.setPaymentType(EmailAttribute.COD.getName());
				totalAmount = getAttributeValues(content, EmailAttribute.COD, EmailAttribute.ZOMATO_FOOTER);
			}
		}
		return totalAmount.trim();
	}

	private void setOrderItems(String content, Map<String, KitchenItem> kitchenItems, Order order) {
		String items = "";
		if(StringUtils.isNotEmpty(content)) {
			int beginIndex = content.indexOf(EmailAttribute.Date.getName())+EmailAttribute.Date.getName().length()+10;
			if(content.contains(EmailAttribute.RESTUARANT_PROMO.getName())) {
				int endIndex = content.indexOf(EmailAttribute.RESTUARANT_PROMO.getName());
				items = content.substring(beginIndex, endIndex);
			} else if(content.contains(EmailAttribute.PAID_BY.getName())) {
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
				continue;
			}
			String itemName = StringUtils.trim(str.split(ESCAPE+PIPE_DELIM)[0]);
			KitchenItem kitchenItem = kitchenItems.get(itemName);
			if(StringUtils.isEmpty(itemName) || null == kitchenItem) {
				order.setManualReview(true);
				order.setManualReviewComments("Item Name: "+itemName+" kitchenObj: "+kitchenItem+" are be empty!!");
				continue;
			}
			String quantity = StringUtils.trim((str.split(ESCAPE+PIPE_DELIM)[1]).split("x")[0].replace("(", ""));
			String dpReceivedPrice = StringUtils.trim(str.split(ESCAPE+PIPE_DELIM)[2]);
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(Integer.parseInt(quantity));
			orderItem.setManufacturingPrice(kitchenItem.getManufacturingPrice());
			orderItem.setMarketPrice(kitchenItem.getMarketPrice());
			orderItem.setDpReceivedPrice(Double.valueOf(dpReceivedPrice));
			orderItem.setKitchenItem(kitchenItem);
			orderItems.add(orderItem);
			order.setOrderItems(orderItems);
			System.out.println(orderItem.toString());
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
	 * Returns order date
	 *
	 * @param content
	 * @return
	 */
	private String getOrderDate(String content) {
		String date ="";
		if(null != content && content.length() >0) {
			int startIndex = content.indexOf(EmailAttribute.Date.getName());
			int dateAttriLen = EmailAttribute.Date.getName().length();
			date = StringUtils.substring(content, startIndex+dateAttriLen, startIndex+dateAttriLen+10);
		}
		return date;
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
		return attributeValue.trim();
	}

	private static String getOrderContent(Message message) throws Exception {
		String content ="";
		Object o = message.getContent();
		if (o instanceof String) {
			content = ((String) o).replaceAll(HTML_COMMENTS_ESCAPCE_REGEX, "").replaceAll(HTML_CLEAN_REGEX, "");
			int index = content.indexOf(EmailAttribute.CUSTOMER_NAME.getName());
			content = content.substring(index);
		}
		return content;
	}
}