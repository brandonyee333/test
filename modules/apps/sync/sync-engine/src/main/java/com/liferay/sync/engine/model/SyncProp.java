/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.liferay.sync.engine.service.persistence.BasePersistenceImpl;

/**
 * @author Shinn Lok
 */
@DatabaseTable(daoClass = BasePersistenceImpl.class, tableName = "SyncProp")
public class SyncProp extends BaseModel {

	public static final String KEY_BUILD_NUMBER = "buildNumber";

	public static final String KEY_GLOBAL_MAX_DOWNLOAD_RATE =
		"globalMaxDownloadRate";

	public static final String KEY_GLOBAL_MAX_UPLOAD_RATE =
		"globalMaxUploadRate";

	public static final String KEY_LAN_ENABLED = "lanEnabled";

	public static final String KEY_SYNC_LAN_CLIENT_UUID = "syncLanClientUuid";

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@DatabaseField(id = true, useGetSet = true)
	protected String key;

	@DatabaseField(useGetSet = true, width = 16777216)
	protected String value;

}