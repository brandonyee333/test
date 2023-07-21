/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncAccount;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Shinn Lok
 */
public class SyncAccountPersistence
	extends BasePersistenceImpl<SyncAccount, Long> {

	public SyncAccountPersistence() throws SQLException {
		super(SyncAccount.class);
	}

	public List<SyncAccount> findByLanServerUuid(String lanServerUuid)
		throws SQLException {

		QueryBuilder<SyncAccount, Long> queryBuilder = queryBuilder();

		Where<SyncAccount, Long> where = queryBuilder.where();

		where.eq("lanServerUuid", new SelectArg(lanServerUuid));

		return where.query();
	}

	public List<Long> findByActive(boolean active) throws SQLException {
		QueryBuilder<SyncAccount, Long> queryBuilder = queryBuilder();

		queryBuilder.selectColumns("syncAccountId");

		Where<SyncAccount, Long> where = queryBuilder.where();

		where.eq("active", active);

		GenericRawResults<Long> genericRawResults = queryRaw(
			queryBuilder.prepareStatementString(),
			new RawRowMapper<Long>() {

				@Override
				public Long mapRow(
					String[] columnNames, String[] resultColumns) {

					return Long.valueOf(resultColumns[0]);
				}

			});

		return genericRawResults.getResults();
	}

	public SyncAccount fetchByUuid(String uuid) throws SQLException {
		QueryBuilder<SyncAccount, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncAccount, Long> where = queryBuilder.where();

		where.eq("uuid", uuid);

		where.and(1);

		return where.queryForFirst();
	}

	public SyncAccount fetchByFilePathName(String filePathName)
		throws SQLException {

		QueryBuilder<SyncAccount, Long> queryBuilder = queryBuilder();

		queryBuilder.limit(1L);

		Where<SyncAccount, Long> where = queryBuilder.where();

		where.eq("filePathName", new SelectArg(filePathName));

		where.and(1);

		return where.queryForFirst();
	}

}