/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.internal.upgrade.v1_0_0.util;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsEntryTable {

	public static final Object[][] TABLE_COLUMNS = {
		{"meetupsEntryId", Integer.valueOf(Types.BIGINT)},
		{"companyId", Integer.valueOf(Types.BIGINT)},
		{"userId", Integer.valueOf(Types.BIGINT)},
		{"userName", Integer.valueOf(Types.VARCHAR)},
		{"createDate", Integer.valueOf(Types.TIMESTAMP)},
		{"modifiedDate", Integer.valueOf(Types.TIMESTAMP)},
		{"title", Integer.valueOf(Types.VARCHAR)},
		{"description", Integer.valueOf(Types.VARCHAR)},
		{"startDate", Integer.valueOf(Types.TIMESTAMP)},
		{"endDate", Integer.valueOf(Types.TIMESTAMP)},
		{"totalAttendees", Integer.valueOf(Types.INTEGER)},
		{"maxAttendees", Integer.valueOf(Types.INTEGER)},
		{"price", Integer.valueOf(Types.DOUBLE)},
		{"thumbnailId", Integer.valueOf(Types.BIGINT)}
	};

	public static final String TABLE_NAME = "SN_MeetupsEntry";

	public static final String TABLE_SQL_CREATE =
		"create table SN_MeetupsEntry (meetupsEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,title VARCHAR(75) null,description STRING null,startDate DATE null,endDate DATE null,totalAttendees INTEGER,maxAttendees INTEGER,price DOUBLE,thumbnailId LONG)";

	public static final String TABLE_SQL_DROP = "drop table SN_MeetupsEntry";

}