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

import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.repository.CustomBQFieldMappingRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collection;
import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Robson Pastor
 */
public class BQFieldMappingRepositoryImpl
	extends BaseRepository implements CustomBQFieldMappingRepository {

	public BQFieldMappingRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countBQFieldMappings(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectCount.from(
				"BQFieldMapping"
			).where(
				filterHelper.getCondition()
			));
	}

	@Override
	public List<BQFieldMapping> findByFieldNameIn(
		Collection<String> fieldNames) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Condition condition = DSL.noCondition();

		if (!fieldNames.isEmpty()) {
			condition = condition.and(
				DSL.field(
					"fieldName"
				).in(
					fieldNames
				));
		}

		return _queryExecutor.queryForList(
			BQFieldMapping::new,
			selectSelectStep.from(
				"BQFieldMapping"
			).where(
				condition
			));
	}

	@Override
	public List<BQFieldMapping> searchBQFieldMappings(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQFieldMapping::new,
			selectSelectStep.from(
				"BQFieldMapping"
			).where(
				filterHelper.getCondition()
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private final DSLContext _dslContext;

	@Autowired
	private QueryExecutor _queryExecutor;

}