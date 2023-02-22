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

import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.repository.CustomBQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 */
public class BQExpandoValueRepositoryImpl
	implements CustomBQExpandoValueRepository {

	public BQExpandoValueRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQExpandoValue")
			));
	}

	@Override
	public List<BQExpandoValue> findByClassPKAndClassTypeAndDataSourceId(
		Long classPK, String classType, Long dataSourceId) {

		return _queryExecutor.queryForList(
			BQExpandoValue::new,
			_dslContext.select(
			).from(
				DSL.table("BQExpandoValue")
			).where(
				DSL.and(
					DSL.field(
						"classPK"
					).eq(
						classPK
					),
					DSL.field(
						"classType"
					).eq(
						classType
					),
					DSL.field(
						"dataSourceId"
					).eq(
						dataSourceId
					))
			));
	}

	@Override
	public BQExpandoValue insert(BQExpandoValue bqExpandoValue) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQExpandoValue")
			).columns(
				DSL.field("classPK"), DSL.field("classType"),
				DSL.field("columnId"), DSL.field("dataSourceId"),
				DSL.field("id"), DSL.field("modifiedDate"), DSL.field("value")
			).values(
				bqExpandoValue.getClassPK(), bqExpandoValue.getClassType(),
				bqExpandoValue.getColumnId(), bqExpandoValue.getDataSourceId(),
				bqExpandoValue.getId(), bqExpandoValue.getModifiedDate(),
				bqExpandoValue.getValue()
			));

		return bqExpandoValue;
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}