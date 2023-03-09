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

import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.repository.CustomBQUserRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.BQSQLUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 */
public class BQUserRepositoryImpl
	extends BaseRepository implements CustomBQUserRepository {

	public BQUserRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQUser")
			));
	}

	@Override
	public long countByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				"BQUser"
			).where(
				ConditionUtil.toConditions(
					dataSourceIds, keywords,
					new String[] {"firstName", "lastName"})
			));
	}

	@Override
	public void deleteById(String id) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQUser")
			).where(
				DSL.field(
					"id"
				).eq(
					id
				)
			));
	}

	@Override
	public List<BQUser> findAll() {
		return _queryExecutor.queryForList(
			BQUser::new,
			_dslContext.select(
				DSL.asterisk()
			).from(
				"BQUser"
			));
	}

	@Override
	public List<BQUser> findByFields(
		Map<String, Object> fields, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQUser::new,
			selectSelectStep.from(
				"BQUser"
			).where(
				ConditionUtil.toConditions(fields)
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public BQUser insert(BQUser bqUser) {
		_queryExecutor.queryExecute(BQSQLUtil.createInsertStatement(bqUser));

		return bqUser;
	}

	public List<BQUser> searchByDataSourceIdsAndKeywords(
		List<Long> dataSourceIds, String keywords, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQUser::new,
			selectSelectStep.from(
				"BQUser"
			).where(
				ConditionUtil.toConditions(
					dataSourceIds, keywords,
					new String[] {"firstName", "lastName"})
			).orderBy(
				getSortFields(
					_getSortFieldNameConversionMap(), pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return new HashMap<String, String>() {
			{
				put("name", "CONCAT(firstName, ' ', lastName)");
			}
		};
	}

	private final DSLContext _dslContext;

	@Autowired
	private QueryExecutor _queryExecutor;

}