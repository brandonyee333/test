/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_1_0.util;

import java.sql.Types;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class GroupTable {

	public static final String TABLE_NAME = "Group_";

	public static final Object[][] TABLE_COLUMNS = {
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"creatorUserId", Types.BIGINT},
		{"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT},
		{"parentGroupId", Types.BIGINT},
		{"liveGroupId", Types.BIGINT},
		{"name", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"type_", Types.INTEGER},
		{"typeSettings", Types.VARCHAR},
		{"friendlyURL", Types.VARCHAR},
		{"site", Types.BOOLEAN},
		{"active_", Types.BOOLEAN}
	};

	public static final String TABLE_SQL_CREATE = "create table Group_ (groupId LONG not null primary key,companyId LONG,creatorUserId LONG,classNameId LONG,classPK LONG,parentGroupId LONG,liveGroupId LONG,name VARCHAR(150) null,description STRING null,type_ INTEGER,typeSettings STRING null,friendlyURL VARCHAR(100) null,site BOOLEAN,active_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table Group_";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_ABA5CEC2 on Group_ (companyId)",
		"create unique index IX_D0D5E397 on Group_ (companyId, classNameId, classPK)",
		"create unique index IX_5DE0BE11 on Group_ (companyId, classNameId, liveGroupId, name)",
		"create unique index IX_5BDDB872 on Group_ (companyId, friendlyURL)",
		"create unique index IX_BBCA55B on Group_ (companyId, liveGroupId, name)",
		"create unique index IX_5AA68501 on Group_ (companyId, name)",
		"create index IX_16218A38 on Group_ (liveGroupId)",
		"create index IX_7B590A7A on Group_ (type_, active_)"
	};

}