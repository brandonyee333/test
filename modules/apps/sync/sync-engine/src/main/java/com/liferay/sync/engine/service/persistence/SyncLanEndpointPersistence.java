/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.service.persistence;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import com.liferay.sync.engine.model.SyncLanEndpoint;

import java.sql.SQLException;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class SyncLanEndpointPersistence
	extends BasePersistenceImpl<SyncLanEndpoint, Long> {

	public SyncLanEndpointPersistence() throws SQLException {
		super(SyncLanEndpoint.class);
	}

	public List<SyncLanEndpoint> findBySyncLanClientUuid(
			String syncLanClientUuid)
		throws SQLException {

		return queryForEq("syncLanClientUuid", syncLanClientUuid);
	}

	public List<String> findByL_R(String lanServerUuid, long repositoryId)
		throws SQLException {

		QueryBuilder<SyncLanEndpoint, Long> queryBuilder = queryBuilder();

		queryBuilder.selectColumns("syncLanClientUuid");

		Where<SyncLanEndpoint, Long> where = queryBuilder.where();

		where.eq("lanServerUuid", lanServerUuid);
		where.eq("repositoryId", repositoryId);

		where.and(2);

		GenericRawResults<String> genericRawResults = queryRaw(
			queryBuilder.prepareStatementString(),
			new RawRowMapper<String>() {

				@Override
				public String mapRow(
					String[] columnNames, String[] resultColumns) {

					return String.valueOf(resultColumns[0]);
				}

			});

		return genericRawResults.getResults();
	}

	public void deleteBySyncLanClientUuid(String syncLanClientUuid)
		throws SQLException {

		DeleteBuilder<SyncLanEndpoint, Long> deleteBuilder = deleteBuilder();

		Where<SyncLanEndpoint, Long> where = deleteBuilder.where();

		where.eq("syncLanClientUuid", syncLanClientUuid);

		deleteBuilder.delete();
	}

}