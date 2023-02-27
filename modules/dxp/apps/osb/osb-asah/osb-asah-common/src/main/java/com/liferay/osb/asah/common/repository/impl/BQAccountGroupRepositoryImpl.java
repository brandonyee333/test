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

import com.liferay.osb.asah.common.entity.BQAccountGroup;
import com.liferay.osb.asah.common.repository.CustomBQAccountGroupRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.Date;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 */
public class BQAccountGroupRepositoryImpl
	implements CustomBQAccountGroupRepository {

	public BQAccountGroupRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQAccountGroup")
			));
	}

	@Override
	public void deleteById(String id) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQAccountGroup")
			).where(
				DSL.field(
					"id"
				).eq(
					id
				)
			));
	}

	@Override
	public BQAccountGroup insert(BQAccountGroup bqAccountGroup) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQAccountGroup")
			).columns(
				DSL.field("accountGroupId"),
				DSL.field("createDate", Date.class),
				DSL.field("dataSourceId", Long.class),
				DSL.field("defaultAccountGroup", Boolean.class),
				DSL.field("description"), DSL.field("id"),
				DSL.field("modifiedDate", Date.class), DSL.field("name"),
				DSL.field("type")
			).values(
				bqAccountGroup.getAccountGroupId(),
				bqAccountGroup.getCreateDate(),
				bqAccountGroup.getDataSourceId(),
				bqAccountGroup.getDefaultAccountGroup(),
				bqAccountGroup.getDescription(), bqAccountGroup.getId(),
				bqAccountGroup.getModifiedDate(), bqAccountGroup.getName(),
				bqAccountGroup.getType()
			));

		return bqAccountGroup;
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}