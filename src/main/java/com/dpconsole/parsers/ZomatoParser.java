package com.dpconsole.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.Message;

import org.apache.commons.lang.StringUtils;

import com.dpconsole.model.kitchen.DeliveryPartner;
import com.dpconsole.model.kitchen.Kitchen;
import com.dpconsole.model.kitchen.KitchenItem;
import com.dpconsole.model.order.Order;

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
			String date=null;
			String amount = null;
			orderId = getAttributeValues(content,EmailAttribute.ORDER_ID,EmailAttribute.Date);
			date = getOrderDate(content); //2018-11-21
			String items = getOrderItems(content);
			amount = getAttributeValues(content,EmailAttribute.PREPAID,EmailAttribute.ZOMATO_FOOTER); 
			System.out.println("orderId: " +orderId.trim()+" date: "+date.trim());
			/*date = (date == null)? date =getAttributeValues(content,"Date: "):date;
			amount = (amount == null)? amount =getAttributeValues(content,"Prepaid"):amount;*/
			Order order = new Order();
			order.setDeliveryPartner(DeliveryPartner.ZOMATO); //TODO: set appropriate delivery partner after loading from DB
			//order.setCommissionPercentage(1.2f);//TODO: check this
			//order.setCreatedTime(createdTime);
			order.setDeliveryPartnerOrderId(orderId);
			//order.setDiscountAmount(discountAmount);
			order.setKitchen(kitchen);
			//order.setOrderItems(orderItems);
			//order.setReceivedAmount(Float.parseFloat(amount));
			//order.setTaxPercentage(taxPercentage);
			//order.setTotalCost(Double.parseDouble(amount));
		}
		return orders;
		
	}
	
	private String getOrderItems(String content) {
		String items = "";
		if(null != content && content.length() >0) {
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
		for(String str : sanitizedItems) {
			if(str.length()<3){
				continue;
			}
			String name = str.split(ESCAPE+PIPE_DELIM)[0];
			String quantity = str.split(ESCAPE+PIPE_DELIM)[1];
			String amount = str.split(ESCAPE+PIPE_DELIM)[2];
			System.out.println("Name: "+name+" Quantity: "+quantity+" Amount: "+amount);
		}
		return items.trim();
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

