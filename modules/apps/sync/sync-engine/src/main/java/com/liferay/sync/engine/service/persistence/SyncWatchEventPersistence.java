/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncWatchEvent;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Michael Young
 */
public class SyncWatchEventPersistence
	extends BasePersistenceImpl<SyncWatchEvent, Long> {

	public SyncWatchEventPersistence() throws SQLException {
		super(SyncWatchEvent.class);
	}

	public void deleteBySyncAccountId(long syncAccountId) throws SQLException {
		DeleteBuilder<SyncWatchEvent, Long> deleteBuilder = deleteBuilder();

		Where<SyncWatchEvent, Long> where = deleteBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		deleteBuilder.delete();
	}

	public SyncWatchEvent fetchBySyncAccountId_First(long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.queryForFirst();
	}

	public SyncWatchEvent fetchByE_F_NotT_First(
			String eventType, String filePathName, long timestamp)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("eventType", eventType);
		where.eq("filePathName", new SelectArg(filePathName));
		where.ne("timestamp", timestamp);

		where.and(3);

		return where.queryForFirst();
	}

	public SyncWatchEvent fetchByE_F_T_First(
			String eventType, String filePathName, long timestamp)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("eventType", eventType);
		where.eq("filePathName", new SelectArg(filePathName));
		where.between("timestamp", timestamp - 1000, timestamp + 1000);

		where.and(3);

		return where.queryForFirst();
	}

	public List<SyncWatchEvent> findBySyncAccountId(long syncAccountId)
		throws SQLException {

		QueryBuilder<SyncWatchEvent, Long> queryBuilder = queryBuilder();

		queryBuilder.orderBy("fileType", false);

		Where<SyncWatchEvent, Long> where = queryBuilder.where();

		where.eq("syncAccountId", syncAccountId);

		return where.query();
	}

}