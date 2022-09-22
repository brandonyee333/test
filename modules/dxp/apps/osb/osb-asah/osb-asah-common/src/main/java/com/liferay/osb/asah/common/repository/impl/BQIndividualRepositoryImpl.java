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

import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.repository.CustomBQIndividualRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public class BQIndividualRepositoryImpl
	extends BaseRepository implements CustomBQIndividualRepository {

	public BQIndividualRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public List<Distribution> getIndividualDistributions(
		@Nullable Long channelId, String fieldName,
		@Nullable Long individualSegmentId, Pageable pageable) {

		Field<String> nestedField = _dslHelper.getNestedField(
			"name", fieldName, "value", "fields");

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			conditions.add(_getChannelIdCondition(channelId));
		}

		if (individualSegmentId != null) {
			conditions.add(
				_getIndividualSegmentIdCondition(individualSegmentId));
		}

		conditions.add(nestedField.isNotNull());

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		return _queryExecutor.queryForList(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.get("nestedField"))),
			modifiedDateSelectSelectStep.select(
				nestedField.as("nestedField"),
				DSL.count(
				).as(
					"count"
				)
			).from(
				"BQIndividual"
			).where(
				conditions
			).groupBy(
				nestedField.as("nestedField")
			).orderBy(
				getSortFields(
					_getSortFieldNameConversionMap(), pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private Condition _getChannelIdCondition(Long channelId) {
		return DSL.exists(
			DSL.selectOne(
			).from(
				"BQIdentityActivity"
			).where(
				DSL.and(
					DSL.field(
						"BQIdentityActivity.channelId"
					).eq(
						channelId
					),
					DSL.field(
						"BQIdentityActivity.individualId"
					).eq(
						DSL.field("BQIndividual.id")
					))
			));
	}

	private Condition _getIndividualSegmentIdCondition(
		Long individualSegmentId) {

		return DSL.exists(
			DSL.selectOne(
			).from(
				"BQMembership"
			).where(
				DSL.and(
					DSL.field(
						"BQMembership.individualSegmentId"
					).eq(
						individualSegmentId
					),
					DSL.field(
						"BQMembership.individualId"
					).eq(
						DSL.field("BQIndividual.id")
					))
			));
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}