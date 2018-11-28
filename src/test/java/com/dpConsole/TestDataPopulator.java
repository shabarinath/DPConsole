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
package com.dpConsole;

import java.util.UUID;

/**
 * @author nanda.malve
 * created on 28-Nov-2018 12:44:17 AM
 */
public class TestDataPopulator {

	public static final String CATEGORIES_STMT  = "INSERT INTO catalogue_categories(id, name, precedence) VALUES(%d, '%s', %d);";
	public static final String SUB_CATEGORIES_STMT  = "INSERT INTO catalogue_sub_categories(id, name, category_id, precedence) VALUES(%d, '%s', %d, %d);";
	public static final String ITEMS_STMT = "INSERT INTO catalogue_items(id, name, description, sub_category_id, food_type, precedence) VALUES(%d, '%s', '%s', %d, '%s', %d);";
	public static final String ITEM_ALIASES_STMT = "INSERT INTO catalogue_item_aliases(item_id, alias, list_index) VALUES(%d, '%s', %d);";
	public static final String KITCHENS_STMT = "INSERT INTO kitchens(id, name, mailbox_username, mailbox_password) VALUES(%d, '%s', '%s');";
	public static final String KITCHEN_DPs_STMT = "INSERT INTO kitchen_delivery_partners(id, kitchen_id, delivery_partner, email_ids, commission_percentage) VALUES(%d, %d, '%s', '%s', %f);";
	public static final String KITCHENS_DISCOUNTS_STMT = "INSERT INTO kitchen_discounts(id, kitchen_id, delivery_partner, start_time, end_time, discount) VALUES(%d, %d, '%s', '%s' '%s', %f);";
	public static final String KITCHENS_ITEMS_STMT = "INSERT INTO kitchen_items(id, kitchen_id, item_id, manufacturing_price, market_price) VALUES(%d, %d, %d, %f, %f);";
	public static final String ORDERS_STMT = "INSERT INTO orders(id, kitchen_id, delivery_partner, dp_order_id, ordered_time, status, total_cost, notes, payment_type) VALUES(%d, %d, '%s', '%s', '%s', '%s', %f, '%s', '%s');";
	public static final String ORDER_ITEMS_STMT = "INSERT INTO order_items(id, order_id, kitchen_item_id, quantity, manufacturing_price, market_price, dp_received_price) VALUES(%d, %d, %d, %d, %f, %f, %f);";

	public static void main(String[] args) {
		int startCId = 1, endCId = 10;
		int startSCId = 1;
		int startIId = 1;
		for(int i = startCId; i < endCId; i++) {
			String cName = "Category_" + UUID.randomUUID().toString().substring(0,  5);
			String cStmt = String.format(CATEGORIES_STMT, i, cName, i);
			System.out.println(cStmt);
			for(int j = startSCId; j < ((endCId - startCId) / 2); j++) {
				String scName = "SubCategory_" + UUID.randomUUID().toString().substring(0,  5);
				String scStmt = String.format(SUB_CATEGORIES_STMT, j, scName, i, j);
				System.out.println(scStmt);
				for(int k = startIId; k < (((endCId - startCId) *2) / 2); k++) {
					String iName = "Item_" + UUID.randomUUID().toString().substring(0,  5);
					String iStmt = String.format(ITEMS_STMT, k, iName, "DESC_" + UUID.randomUUID().toString(), j, "VEG", k);
					System.out.println(iStmt);
				}
			}
		}
	}

}
