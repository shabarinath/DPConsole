package com.dpconsole.parsers;

import javax.mail.Message;

import org.apache.commons.lang.StringUtils;

import com.dpconsole.model.order.Order;

/**
 * @author SHABARINATH
 * 23-Nov-2018 5:52:46 pm 2018 
 */

public class ZomatoParser implements Parser {
	
	@Override
	public Order parse(Message message) throws Exception {
		if(null == message) {
			
		}
		String content = ZomatoParser.getOrderContent(message);
		String orderId=null;
		String date=null;
		String contactNumber=null;
		String name=null;
		String amount = null;
		orderId =getAttributeValues(content,EmailAttribute.ORDER_ID,EmailAttribute.Date);
		//contactNumber =getAttributeValues(content,EmailAttribute.CONTACT_NO,EmailAttribute.CUSTOMER_ADDRESS);
		//name =getAttributeValues(content,EmailAttribute.CONTACT_NO,EmailAttribute.CUSTOMER_ADDRESS);
		date = getOrderDate(content); //2018-11-21
		String items = getOrderItems(content);
		System.out.println("orderId: " +orderId.trim()+" date: "+date.trim()+" items: "+items);
		/*date = (date == null)? date =getAttributeValues(content,"Date: "):date;
		amount = (amount == null)? amount =getAttributeValues(content,"Prepaid"):amount;*/
		Order order = new Order();
		/*DeliveryPartner dp = new DeliveryPartner();
		order.setDeliveryPartner(dp); //TODO: set appropriate delivery partner after loading from DB
		order.setCommissionPercentage(1.2f);//TODO: check this
		order.setCreatedTime(createdTime);
		order.setDeliveryPartnerOrderId(orderId);
		order.setDiscountAmount(discountAmount);
		order.setKitchen(kitchen);
		order.setOrderItems(orderItems);
		order.setReceivedAmount(Float.parseFloat(amount));
		order.setTaxPercentage(taxPercentage);
		order.setTotalAmount(totalAmount);*/
		return order;
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
		//System.out.println(items);
		return items.replaceAll("\\r\\n|\\r|\\n", "")/*.replaceAll("\\s{2,}", "")*/;
	}

	private String getOrderDate(String content) {
		String date ="";
		if(null != content && content.length() >0) {
			int startIndex = content.indexOf(EmailAttribute.Date.getName());
			int dateAttriLen = EmailAttribute.Date.getName().length();
			date = StringUtils.substring(content, startIndex+dateAttriLen, startIndex+dateAttriLen+10);
		}
		return date;
	}

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
           content = ((String) o).replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "");
           int index = content.indexOf(EmailAttribute.CUSTOMER_NAME.getName());
           content = content.substring(index);/*.replaceAll("\\s{2,}", "")*/
        } 
		return content;
	}

	@Override
	public Order parse(String content) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

