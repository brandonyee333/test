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

import com.liferay.osb.asah.common.entity.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class EventRepositoryImpl extends BaseRepository {

	public EventRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countTotalEvents(
		@Nullable Long channelId, @Nullable Long eventDefinitionId,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Event"
		).where(
			_getConditions(
				channelId, eventDefinitionId, rangeEndDate, rangeStartDate)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countUniqueIndividuals(
		@Nullable Long channelId, @Nullable Long eventDefinitionId,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(_getUniqueIndividualsField());

		return selectSelectStep.from(
			"Event"
		).where(
			_getConditions(
				channelId, eventDefinitionId, rangeEndDate, rangeStartDate)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public Optional<Event> findLastSeenEvent(@Nullable Long eventDefinitionId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<?> field = DSL.field("eventDate");

		return selectSelectStep.from(
			"Event"
		).where(
			_getConditions(null, eventDefinitionId, null, null)
		).orderBy(
			field.desc()
		).limit(
			1
		).fetchOptional(
			record -> new Event(record.intoMap())
		);
	}

	public long getAverageEventCountPerIndividual(
		@Nullable Long channelId, @Nullable Long eventDefinitionId,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		Field<Integer> totalEventCount = DSL.count();

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(
				totalEventCount.div(_getUniqueIndividualsField()));

		return selectSelectStep.from(
			"Event"
		).where(
			_getConditions(
				channelId, eventDefinitionId, rangeEndDate, rangeStartDate)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	private List<Condition> _getConditions(
		Long channelId, Long eventDefinitionId, Date rangeEndDate,
		Date rangeStartDate) {

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			Field<Object> field = DSL.field("channelId");

			conditions.add(field.eq(channelId));
		}

		if (eventDefinitionId != null) {
			Field<Object> field = DSL.field("eventDefinitionId");

			conditions.add(field.eq(eventDefinitionId));
		}

		if ((rangeEndDate != null) && (rangeStartDate != null)) {
			Field<Object> field = DSL.field("eventDate");

			conditions.add(field.between(rangeStartDate, rangeEndDate));
		}

		return conditions;
	}

	private Field<Integer> _getUniqueIndividualsField() {
		return DSL.countDistinct(DSL.field("individualId"));
	}

	private final DSLContext _dslContext;

}