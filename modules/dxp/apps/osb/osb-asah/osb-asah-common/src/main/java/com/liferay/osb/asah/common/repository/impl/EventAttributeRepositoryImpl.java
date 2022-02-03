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

import java.util.List;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;

/**
 * @author Alejo Ceballos
 */
public class EventAttributeRepositoryImpl extends BaseRepository {

	public EventAttributeRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countDistinctAttributeValuesByKeywords(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectSelectStep<Record1<Integer>> selectCount =
			_dslContext.selectCount();

		return selectCount.from(
			_getEventAttributeSelectStep(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<String> findDistinctAttributeValuesByKeywords(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords, Pageable pageable) {

		SelectConditionStep<Record1<String>> eventAttributeSelectStep =
			_getEventAttributeSelectStep(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords);

		return eventAttributeSelectStep.limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			Record1::value1
		);
	}

	private Condition _getCondition(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		return DSL.field(
			"Event.channelId"
		).eq(
			channelId
		).and(
			DSL.field(
				"Event.eventDefinitionId"
			).eq(
				eventDefinitionId
			)
		).and(
			DSL.field(
				"EventAttribute.eventAttributeDefinitionId"
			).eq(
				eventAttributeDefinitionId
			)
		).and(
			DSL.field(
				"EventAttribute.value"
			).likeIgnoreCase(
				"%" + keywords + "%"
			)
		);
	}

	private SelectConditionStep<Record1<String>> _getEventAttributeSelectStep(
		Long channelId, Long eventAttributeDefinitionId, Long eventDefinitionId,
		String keywords) {

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(
				DSL.lower(DSL.field("value", String.class)));

		return selectSelectStep.from(
			"EventAttribute"
		).innerJoin(
			"Event"
		).on(
			DSL.field(
				"Event.id"
			).eq(
				DSL.field("EventAttribute.eventId")
			)
		).where(
			_getCondition(
				channelId, eventAttributeDefinitionId, eventDefinitionId,
				keywords)
		);
	}

	private final DSLContext _dslContext;

}