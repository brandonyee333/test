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

import com.liferay.osb.asah.common.dog.EventAttributeDefinitionDog;
import com.liferay.osb.asah.common.dog.EventDefinitionDog;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.repository.CustomBQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

/**
 * @author Alejo Ceballos
 */
public class BQEventPropertyRepositoryImpl
	extends BaseRepository implements CustomBQEventPropertyRepository {

	public BQEventPropertyRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public long countValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectCount.from(
				_getEventPropertySelectStep(
					channelId, eventAttributeDefinitionId, eventDefinitionId,
					DSL.lower(DSL.field("BQEventProperty.value", String.class)),
					keywords)));
	}

	@Override
	public List<String> searchValues(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		Field<String> selectField = DSL.lower(
			DSL.field("BQEventProperty.value", String.class)
		).as(
			"temp"
		);

		SelectConditionStep<Record1<String>> eventPropertySelectStep =
			_getEventPropertySelectStep(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				selectField, keywords);

		return _queryExecutor.queryForList(
			eventPropertySelectStep.orderBy(
				selectField.asc()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private Condition _getCondition(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		return DSL.field(
			"BQEvent.channelId"
		).eq(
			channelId
		).and(
			DSL.field(
				"BQEvent.eventId"
			).eq(
				_getEventDefinitionName(eventDefinitionId)
			)
		).and(
			DSL.field(
				"BQEventProperty.name"
			).eq(
				_getEventAttributeDefinitionName(eventAttributeDefinitionId)
			)
		).and(
			DSL.lower(
				DSL.field("BQEventProperty.value", String.class)
			).like(
				"%" + StringUtils.lowerCase(keywords) + "%"
			)
		);
	}

	private String _getEventAttributeDefinitionName(
		Long eventAttributeDefinitionId) {

		EventAttributeDefinition eventAttributeDefinition =
			_eventAttributeDefinitionDog.getEventAttributeDefinition(
				eventAttributeDefinitionId);

		return eventAttributeDefinition.getName();
	}

	private String _getEventDefinitionName(Long eventDefinitionId) {
		EventDefinition eventDefinition =
			_eventDefinitionDog.getEventDefinition(eventDefinitionId);

		return eventDefinition.getName();
	}

	private SelectConditionStep<Record1<String>> _getEventPropertySelectStep(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		Field<String> selectField, String keywords) {

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(selectField);

		return selectSelectStep.from(
			"BQEventProperty"
		).join(
			"BQEvent"
		).on(
			DSL.field(
				"BQEvent.id"
			).eq(
				DSL.field("BQEventProperty.id")
			)
		).where(
			_getCondition(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords)
		);
	}

	private final DSLContext _dslContext;

	@Autowired
	private EventAttributeDefinitionDog _eventAttributeDefinitionDog;

	@Autowired
	private EventDefinitionDog _eventDefinitionDog;

	@Autowired
	private QueryExecutor _queryExecutor;

}