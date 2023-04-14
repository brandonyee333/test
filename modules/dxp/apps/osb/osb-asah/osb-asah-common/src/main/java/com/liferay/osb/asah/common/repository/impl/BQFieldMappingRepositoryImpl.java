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
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.repository.CustomBQFieldMappingRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;
import org.jooq.tools.StringUtils;

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
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQFieldMapping")
			));
	}

	@Override
	public long countByFilterString(String filterString) {
		FilterExpression filterExpression = new FilterExpression(filterString);

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectCount.from(
				"BQFieldMapping"
			).where(
				filterExpression.getCondition()
			));
	}

	public long countIndividualBQFieldMappings(String displayName) {
		Condition condition = DSL.field(
			"ownerType"
		).eq(
			"individual"
		);

		if (!StringUtils.isBlank(displayName)) {
			condition = condition.and(
				DSL.condition(
					String.format(
						"lower(displayName) like '%s'",
						"%" + displayName.toLowerCase() + "%")));
		}

		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				"BQFieldMapping"
			).where(
				condition
			));
	}

	@Override
	public Optional<BQFieldMapping> findByDisplayNameAndFieldType(
		String displayName, String fieldType) {

		return _queryExecutor.queryForObject(
			BQFieldMapping::new,
			_dslContext.select(
				DSL.field("context"), DSL.field("dataSourceIds"),
				DSL.field("displayName"), DSL.field("displayType"),
				DSL.field("fieldName"), DSL.field("fieldType"),
				DSL.field("modifiedDate", Date.class), DSL.field("ownerType"),
				DSL.field("repeatable")
			).from(
				DSL.table("BQFieldMapping")
			).where(
				DSL.and(
					DSL.field(
						"displayName"
					).eq(
						displayName
					),
					DSL.field(
						"fieldType"
					).eq(
						fieldType
					))
			));
	}

	@Override
	public Optional<BQFieldMapping> findByFieldName(String fieldName) {
		return _queryExecutor.queryForObject(
			BQFieldMapping::new,
			_dslContext.select(
				DSL.field("context"), DSL.field("dataSourceIds"),
				DSL.field("displayName"), DSL.field("displayType"),
				DSL.field("fieldName"), DSL.field("fieldType"),
				DSL.field("modifiedDate", Date.class), DSL.field("ownerType"),
				DSL.field("repeatable")
			).from(
				DSL.table("BQFieldMapping")
			).where(
				DSL.field(
					"fieldName"
				).eq(
					fieldName
				)
			));
	}

	@Override
	public List<BQFieldMapping> findByFieldNameIn(
		Collection<String> fieldNames) {

		return _queryExecutor.queryForList(
			BQFieldMapping::new,
			_dslContext.select(
				DSL.field("context"), DSL.field("dataSourceIds"),
				DSL.field("displayName"), DSL.field("displayType"),
				DSL.field("fieldName"), DSL.field("fieldType"),
				DSL.field("modifiedDate", Date.class), DSL.field("ownerType"),
				DSL.field("repeatable")
			).from(
				"BQFieldMapping"
			).where(
				DSL.field(
					"fieldName"
				).in(
					fieldNames
				)
			));
	}

	@Override
	public List<BQFieldMapping> searchByFilterString(
		String filterString, Pageable pageable) {

		FilterExpression filterExpression = new FilterExpression(filterString);

		return _queryExecutor.queryForList(
			BQFieldMapping::new,
			_dslContext.select(
				DSL.field("context"), DSL.field("dataSourceIds"),
				DSL.field("displayName"), DSL.field("displayType"),
				DSL.field("fieldName"), DSL.field("fieldType"),
				DSL.field("modifiedDate", Date.class), DSL.field("ownerType"),
				DSL.field("repeatable")
			).from(
				"BQFieldMapping"
			).where(
				filterExpression.getCondition()
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<BQFieldMapping> searchIndividualBQFieldMappings(
		String displayName, Pageable pageable) {

		Condition condition = DSL.field(
			"ownerType"
		).eq(
			"individual"
		);

		if (!StringUtils.isBlank(displayName)) {
			condition = condition.and(
				DSL.condition(
					String.format(
						"lower(displayName) like '%s'",
						"%" + displayName.toLowerCase() + "%")));
		}

		return _queryExecutor.queryForList(
			BQFieldMapping::new,
			_dslContext.select(
				DSL.field("context"), DSL.field("dataSourceIds"),
				DSL.field("displayName"), DSL.field("displayType"),
				DSL.field("fieldName"), DSL.field("fieldType"),
				DSL.field("modifiedDate", Date.class), DSL.field("ownerType"),
				DSL.field("repeatable")
			).from(
				"BQFieldMapping"
			).where(
				condition
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