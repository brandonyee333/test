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
public class DLFileVersionTable {

	public static final String TABLE_NAME = "DLFileVersion";

	public static final Object[][] TABLE_COLUMNS = {
		{"fileVersionId", Types.BIGINT},
		{"groupId", Types.BIGINT},
		{"companyId", Types.BIGINT},
		{"userId", Types.BIGINT},
		{"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP},
		{"modifiedDate", Types.TIMESTAMP},
		{"repositoryId", Types.BIGINT},
		{"folderId", Types.BIGINT},
		{"fileEntryId", Types.BIGINT},
		{"extension", Types.VARCHAR},
		{"mimeType", Types.VARCHAR},
		{"title", Types.VARCHAR},
		{"description", Types.VARCHAR},
		{"changeLog", Types.VARCHAR},
		{"extraSettings", Types.CLOB},
		{"fileEntryTypeId", Types.BIGINT},
		{"version", Types.VARCHAR},
		{"size_", Types.BIGINT},
		{"status", Types.INTEGER},
		{"statusByUserId", Types.BIGINT},
		{"statusByUserName", Types.VARCHAR},
		{"statusDate", Types.TIMESTAMP}
	};

	public static final String TABLE_SQL_CREATE = "create table DLFileVersion (fileVersionId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,repositoryId LONG,folderId LONG,fileEntryId LONG,extension VARCHAR(75) null,mimeType VARCHAR(75) null,title VARCHAR(255) null,description STRING null,changeLog VARCHAR(75) null,extraSettings TEXT null,fileEntryTypeId LONG,version VARCHAR(75) null,size_ LONG,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table DLFileVersion";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_C68DC967 on DLFileVersion (fileEntryId)",
		"create index IX_D47BB14D on DLFileVersion (fileEntryId, status)",
		"create unique index IX_E2815081 on DLFileVersion (fileEntryId, version)",
		"create index IX_DFD809D3 on DLFileVersion (groupId, folderId, status)"
	};

}