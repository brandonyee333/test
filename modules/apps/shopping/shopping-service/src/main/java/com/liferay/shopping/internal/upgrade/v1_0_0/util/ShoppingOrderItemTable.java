/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.internal.upgrade.v1_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class ShoppingOrderItemTable {

	public static final String TABLE_NAME = "ShoppingOrderItem";

	public static final Object[][] TABLE_COLUMNS = {
		{"orderItemId", Types.BIGINT},
		{"orderId", Types.BIGINT},
		{"itemId", Types.CLOB},
		{"sku", Types.VARCHAR},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"properties", Types.VARCHAR},
		{"price", Types.DOUBLE},
		{"quantity", Types.INTEGER},
		{"shippedDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("orderItemId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("orderId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("itemId", Types.CLOB);

TABLE_COLUMNS_MAP.put("sku", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("properties", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("price", Types.DOUBLE);

TABLE_COLUMNS_MAP.put("quantity", Types.INTEGER);

TABLE_COLUMNS_MAP.put("shippedDate", Types.TIMESTAMP);

}
	public static final String TABLE_SQL_CREATE = "create table ShoppingOrderItem (orderItemId LONG not null primary key,orderId LONG,itemId TEXT null,sku VARCHAR(75) null,name VARCHAR(200) null,description STRING null,properties STRING null,price DOUBLE,quantity INTEGER,shippedDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table ShoppingOrderItem";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_B5F82C7A on ShoppingOrderItem (orderId)"
	};

}