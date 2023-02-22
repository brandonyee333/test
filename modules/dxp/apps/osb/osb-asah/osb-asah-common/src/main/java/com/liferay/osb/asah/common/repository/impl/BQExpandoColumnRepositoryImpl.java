/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.repository.CustomBQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.Optional;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 */
public class BQExpandoColumnRepositoryImpl
	implements CustomBQExpandoColumnRepository {

	public BQExpandoColumnRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQExpandoColumn")
			));
	}

	@Override
	public Optional<BQExpandoColumn> findByColumnIdAndDataSourceId(
		String expandoColumnId, Long dataSourceId) {

		return _queryExecutor.queryForObject(
			BQExpandoColumn::new,
			_dslContext.select(
			).from(
				DSL.table("BQExpandoColumn")
			).where(
				DSL.and(
					DSL.field(
						"columnId"
					).eq(
						expandoColumnId
					),
					DSL.field(
						"dataSourceId"
					).eq(
						dataSourceId
					))
			));
	}

	@Override
	public Optional<BQExpandoColumn> findById(String id) {
		return _queryExecutor.queryForObject(
			BQExpandoColumn::new,
			_dslContext.select(
			).from(
				DSL.table("BQExpandoColumn")
			).where(
				DSL.field(
					"id"
				).eq(
					id
				)
			));
	}

	@Override
	public BQExpandoColumn insert(BQExpandoColumn bqExpandoColumn) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQExpandoColumn")
			).columns(
				DSL.field("className"), DSL.field("columnId"),
				DSL.field("dataSourceId"), DSL.field("dataSourceName"),
				DSL.field("dataType"), DSL.field("displayType"),
				DSL.field("id"), DSL.field("modifiedDate"), DSL.field("name")
			).values(
				bqExpandoColumn.getClassName(), bqExpandoColumn.getColumnId(),
				bqExpandoColumn.getDataSourceId(),
				bqExpandoColumn.getDataSourceName(),
				bqExpandoColumn.getDataType(), bqExpandoColumn.getDisplayType(),
				bqExpandoColumn.getId(), bqExpandoColumn.getModifiedDate(),
				bqExpandoColumn.getName()
			));

		return bqExpandoColumn;
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}