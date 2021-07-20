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
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.filter.FilterOperator;
import com.liferay.osb.asah.common.model.filter.FilterOperators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectHavingConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
public class EventRepositoryImpl extends BaseRepository {

	public EventRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countTotalEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			"Event");

		List<Condition> conditions = _getConditions(
			channelId, eventDefinitionId, rangeEndDate, rangeStartDate);

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, rangeEndDate, rangeStartDate));
		}

		return selectJoinStep.where(
			conditions
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public long countUniqueIndividuals(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(_getUniqueIndividualsField());

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			"Event");

		List<Condition> conditions = _getConditions(
			channelId, eventDefinitionId, rangeEndDate, rangeStartDate);

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, rangeEndDate, rangeStartDate));
		}

		return selectJoinStep.where(
			conditions
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
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate) {

		Field<Integer> totalEventCount = DSL.count();

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(
				totalEventCount.div(
					DSL.nullif(_getUniqueIndividualsField(), 0)));

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			"Event");

		List<Condition> conditions = _getConditions(
			channelId, eventDefinitionId, rangeEndDate, rangeStartDate);

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, rangeEndDate, rangeStartDate));
		}

		return selectJoinStep.where(
			conditions
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
			Field<Object> field = DSL.field("Event.channelId");

			conditions.add(field.eq(channelId));
		}

		if (eventDefinitionId != null) {
			Field<Object> field = DSL.field("Event.eventDefinitionId");

			conditions.add(field.eq(eventDefinitionId));
		}

		if ((rangeEndDate != null) && (rangeStartDate != null)) {
			Field<Object> field = DSL.field("Event.eventDate");

			conditions.add(field.between(rangeStartDate, rangeEndDate));
		}

		return conditions;
	}

	private Condition _getEventAnalysisFilterCondition(
		AttributeType attributeType,
		List<EventAnalysisFilter> eventAnalysisFilters, Date rangeEndDate,
		Date rangeStartDate) {

		Condition conditions = DSL.noCondition();

		Stream<EventAnalysisFilter> stream = eventAnalysisFilters.stream();

		Map<String, List<EventAnalysisFilter>> eventAnalysisFiltersByField =
			stream.collect(
				Collectors.groupingBy(EventAnalysisFilter::getAttributeId));

		for (Map.Entry<String, List<EventAnalysisFilter>> entry :
				eventAnalysisFiltersByField.entrySet()) {

			String attributeId = entry.getKey();

			Condition condition = _getValueCondition(
				attributeType, attributeId, rangeEndDate, rangeStartDate);

			for (EventAnalysisFilter eventAnalysisFilter : entry.getValue()) {
				FilterOperator filterOperator = FilterOperators.of(
					eventAnalysisFilter.getDataType(),
					eventAnalysisFilter.getOperator(),
					eventAnalysisFilter.getValues());

				condition = condition.and(
					filterOperator.getCondition(
						_getField(eventAnalysisFilter)));
			}

			conditions = conditions.or(condition);
		}

		Field<Object> field = DSL.field(attributeType.getJoinFieldName());

		SelectHavingConditionStep<Record1<Object>> selectHavingConditionStep =
			_dslContext.select(
				field
			).from(
				attributeType.getTableName()
			).where(
				conditions
			).groupBy(
				field
			).having(
				DSL.count(
				).ge(
					eventAnalysisFiltersByField.size()
				)
			);

		Field<Object> joinFieldTableField = DSL.field(
			_getJoinFieldTableName(attributeType));

		return joinFieldTableField.in(selectHavingConditionStep);
	}

	private List<Condition> _getEventAnalysisFilterConditions(
		List<EventAnalysisFilter> eventAnalysisFilters, Date rangeEndDate,
		Date rangeStartDate) {

		List<Condition> conditions = new ArrayList<>();

		Stream<EventAnalysisFilter> stream = eventAnalysisFilters.stream();

		Map<AttributeType, List<EventAnalysisFilter>>
			eventAnalysisFiltersByAttributeType = stream.collect(
				Collectors.groupingBy(EventAnalysisFilter::getAttributeType));

		for (Map.Entry<AttributeType, List<EventAnalysisFilter>> entry :
				eventAnalysisFiltersByAttributeType.entrySet()) {

			conditions.add(
				_getEventAnalysisFilterCondition(
					entry.getKey(), entry.getValue(), rangeEndDate,
					rangeStartDate));
		}

		return conditions;
	}

	private Field _getField(EventAnalysisFilter eventAnalysisFilter) {
		EventAttributeDefinition.DataType dataType =
			eventAnalysisFilter.getDataType();

		if (dataType.equals(EventAttributeDefinition.DataType.BOOLEAN)) {
			return DSL.field("try_cast_boolean(value)");
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			return DSL.field("try_cast_timestamp(value)");
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			return DSL.field("try_cast_bigint(value)");
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			return DSL.field("try_cast_float(value)");
		}

		return DSL.field("value");
	}

	private String _getJoinFieldTableName(AttributeType attributeType) {
		if (attributeType.equals(AttributeType.EVENT)) {
			return "id";
		}

		return "individualId";
	}

	private Field<Integer> _getUniqueIndividualsField() {
		return DSL.countDistinct(DSL.field("individualId"));
	}

	private Condition _getValueCondition(
		AttributeType attributeFilterType, String attributeId,
		Date rangeEndDate, Date rangeStartDate) {

		Condition condition = DSL.noCondition();

		if (attributeFilterType.equals(AttributeType.EVENT)) {
			Field<Long> eventAttributeDefinitionIdField = DSL.field(
				"eventAttributeDefinitionId", Long.class);

			condition = condition.and(
				eventAttributeDefinitionIdField.eq(Long.valueOf(attributeId)));

			if ((rangeEndDate != null) && (rangeStartDate != null)) {
				Field<Object> eventDateField = DSL.field(
					"EventAttribute.eventDate");

				condition = condition.and(
					eventDateField.between(rangeStartDate, rangeEndDate));
			}
		}

		return condition;
	}

	private final DSLContext _dslContext;

}