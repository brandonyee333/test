/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class WallEntryTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"wallEntryId", Integer.valueOf(Types.BIGINT)},
		{"groupId", Integer.valueOf(Types.BIGINT)},
		{"companyId", Integer.valueOf(Types.BIGINT)},
		{"userId", Integer.valueOf(Types.BIGINT)},
		{"userName", Integer.valueOf(Types.VARCHAR)},
		{"createDate", Integer.valueOf(Types.TIMESTAMP)},
		{"modifiedDate", Integer.valueOf(Types.TIMESTAMP)},
		{"comments", Integer.valueOf(Types.VARCHAR)}
	};

	public static final String TABLE_NAME = "SN_WallEntry";

	public static final String TABLE_SQL_CREATE =
		"create table SN_WallEntry (wallEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,comments STRING null)";

	public static final String TABLE_SQL_DROP = "drop table SN_WallEntry";

}