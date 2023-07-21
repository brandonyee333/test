/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncUser;

import java.sql.SQLException;

/**
 * @author Dennis Ju
 */
public class SyncUserPersistence extends BasePersistenceImpl<SyncUser, Long> {

	public SyncUserPersistence() throws SQLException {
		super(SyncUser.class);
	}

	public SyncUser fetchBySyncAccountId(long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncUser, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncUser, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.queryForFirst();
	}

}