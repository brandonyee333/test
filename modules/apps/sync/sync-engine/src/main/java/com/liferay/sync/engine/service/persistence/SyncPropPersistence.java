/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.liferay.sync.engine.model.SyncProp;

import java.sql.SQLException;

/**
 * @author Shinn Lok
 */
public class SyncPropPersistence extends BasePersistenceImpl<SyncProp, String> {

	public SyncPropPersistence() throws SQLException {
		super(SyncProp.class);
	}

}