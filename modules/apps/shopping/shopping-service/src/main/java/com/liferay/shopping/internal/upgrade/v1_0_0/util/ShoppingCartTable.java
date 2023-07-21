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
public class ShoppingCartTable {

	public static final String TABLE_NAME = "ShoppingCart";

	public static final Object[][] TABLE_COLUMNS = {
		{"cartId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"itemIds", Types.CLOB},
		{"couponCodes", Types.VARCHAR},
		{"altShipping", Types.INTEGER},
		{"insure", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("cartId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("itemIds", Types.CLOB);

TABLE_COLUMNS_MAP.put("couponCodes", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("altShipping", Types.INTEGER);

TABLE_COLUMNS_MAP.put("insure", Types.BOOLEAN);

}
	public static final String TABLE_SQL_CREATE = "create table ShoppingCart (cartId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,itemIds TEXT null,couponCodes VARCHAR(75) null,altShipping INTEGER,insure BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table ShoppingCart";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create unique index IX_FC46FE16 on ShoppingCart (groupId, userId)",
		"create index IX_54101CC8 on ShoppingCart (userId)"
	};

}