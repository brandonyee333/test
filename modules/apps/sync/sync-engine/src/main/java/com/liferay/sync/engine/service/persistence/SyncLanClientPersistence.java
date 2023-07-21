/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncLanClient;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class SyncLanClientPersistence
	extends BasePersistenceImpl<SyncLanClient, String> {

	public SyncLanClientPersistence() throws SQLException {
		super(SyncLanClient.class);
	}

	public List<SyncLanClient> findByModifiedTime(long modifiedTime)
		throws SQLException {

		QueryBuilder<SyncLanClient, String> queryBuilder = queryBuilder();

		Where<SyncLanClient, String> where = queryBuilder.where();

		where.lt("modifiedTime", modifiedTime);

		return queryBuilder.query();
	}

}