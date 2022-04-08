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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.filter.FilterOperator;
import com.liferay.osb.asah.common.model.filter.FilterOperators;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SelectHavingConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
public class BQEventRepositoryImpl extends BaseRepository {

	public BQEventRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public Integer countBQEvents(
		Long channelId, @Nullable String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return _getEventsCount(
			channelId, DSL.count(), keywords, rangeEndLocalDateTime,
			rangeStartLocalDateTime, timeZoneId, userIds);
	}

	public long countByEventDefinitionId(long eventDefinitionId) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_getEventSelectJoinStep(eventDefinitionId, selectSelectStep);

		return selectJoinStep.where(
			_getConditions(null, eventDefinitionId, null, null)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public Integer countEventSessions(
		Long channelId, String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return _getEventsCount(
			channelId, DSL.countDistinct(DSL.field("sessionId")), keywords,
			rangeEndLocalDateTime, rangeStartLocalDateTime, timeZoneId,
			userIds);
	}

	public long countTotalBQEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_getEventSelectJoinStep(eventDefinitionId, selectSelectStep);

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId)
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
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(_getUniqueIndividualsField());

		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_getEventSelectJoinStep(eventDefinitionId, selectSelectStep);

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public Optional<BQEvent> findLastSeenBQEvent(
		@Nullable Long eventDefinitionId) {

		Table<Record> eventTable = DSL.table("BQEvent");

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			eventTable.asterisk());

		SelectJoinStep<Record> selectJoinStep = selectSelectStep.from(
			"BQEvent");

		Condition condition = DSL.noCondition();

		if (eventDefinitionId != null) {
			selectJoinStep = _joinEventDefinitionTable(selectJoinStep);

			Field<Object> eventDefinitionIdField = DSL.field(
				"EventDefinition.id");

			condition = eventDefinitionIdField.eq(eventDefinitionId);
		}

		return selectJoinStep.where(
			condition
		).orderBy(
			DSL.field(
				"eventDate"
			).desc()
		).limit(
			1
		).fetchOptional(
			record -> new BQEvent(record.intoMap())
		);
	}

	public BigDecimal getAverageBQEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		Field totalEventCount = DSL.count();

		totalEventCount = totalEventCount.cast(SQLDataType.DECIMAL);

		SelectSelectStep<Record1<Number>> selectSelectStep = _dslContext.select(
			totalEventCount.div(DSL.nullif(_getUniqueIndividualsField(), 0)));

		SelectJoinStep<Record1<Number>> selectJoinStep =
			_getEventSelectJoinStep(eventDefinitionId, selectSelectStep);

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId)
		).fetchOptional(
			0, BigDecimal.class
		).orElse(
			BigDecimal.ZERO
		);
	}

	public Map<Object, Number> getBQEventPropertyValues(
		AnalysisType analysisType, @Nullable BreakdownItem breakdownItem,
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, Pageable pageable,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate,
		String timeZoneId) {

		EventAttributeDefinition eventAttributeDefinition =
			_getEventAttributeDefinition(
				eventAnalysisBreakdown.getAttributeId());

		if (eventAttributeDefinition == null) {
			return Collections.emptyMap();
		}

		Map<Object, Number> eventAttributeValues = new LinkedHashMap<>();

		Field<Number> selectField = _getSelectField(
			analysisType, eventAttributeDefinition);
		Field valueField = _getValueField(
			true, eventAnalysisBreakdown, eventAttributeDefinition, timeZoneId);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = _buildSelectJoinStep(
			breakdownItem, eventAnalysisBreakdown, eventAttributeDefinition,
			selectSelectStep.select(
				selectField, valueField
			).from(
				"BQEvent"
			),
			timeZoneId);

		selectJoinStep = _joinEventDefinitionTable(selectJoinStep);

		selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId)
		).groupBy(
			DSL.field(valueField.getName())
		).orderBy(
			_getOrderField(selectField, eventAnalysisBreakdown.getSortType()),
			_getOrderField(valueField, "ASC")
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).forEach(
			record -> eventAttributeValues.put(
				record.get(valueField),
				(Number)record.get(selectField.getName()))
		);

		return eventAttributeValues;
	}

	public long getBQEventPropertyValuesCount(
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		EventAttributeDefinition eventAttributeDefinition =
			_getEventAttributeDefinition(
				eventAnalysisBreakdown.getAttributeId());

		if (eventAttributeDefinition == null) {
			return 0;
		}

		AttributeType attributeType = eventAnalysisBreakdown.getAttributeType();

		Field valueField = _getValueField(
			false, eventAnalysisBreakdown, eventAttributeDefinition,
			timeZoneId);

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(
				DSL.countDistinct(
					valueField
				).plus(
					DSL.count(DSL.when(valueField.isNull(), 1))
				));

		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_joinEventDefinitionTable(selectSelectStep.from("BQEvent"));

		selectJoinStep = _joinEventAttributeTable(
			attributeType, eventAttributeDefinition, selectJoinStep);

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public Map<String, Integer> getBQEventsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Field<OffsetDateTime> eventDateField = DSL.field(
			String.format("BQEvent.eventDate AT TIME ZONE '%s'", timeZoneId),
			OffsetDateTime.class);

		if (interval == Interval.DAY) {
			eventDateField = _dateTrunc(DatePart.DAY, eventDateField);
		}
		else if (interval == Interval.HOUR) {
			eventDateField = _dateTrunc(DatePart.HOUR, eventDateField);
		}
		else if (interval == Interval.MONTH) {
			eventDateField = _dateTrunc(DatePart.MONTH, eventDateField);
		}
		else {
			eventDateField = _dateTrunc(DatePart.WEEK, eventDateField);
		}

		eventDateField = eventDateField.as("eventDate");

		SelectJoinStep<Record2<OffsetDateTime, Integer>> selectJoinStep =
			_dslContext.select(
				eventDateField, DSL.count()
			).from(
				"BQEvent"
			);

		selectJoinStep = _joinEventDefinitionTable(selectJoinStep);

		return _toMap(
			selectJoinStep.where(
				_createCondition(
					channelId, keywords, rangeEndLocalDateTime,
					rangeStartLocalDateTime, timeZoneId, userIds)
			).groupBy(
				eventDateField
			).fetch(
				record -> record
			));
	}

	public Map<String, Integer> getEventSessionsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Field<OffsetDateTime> eventDateField = DSL.field(
			String.format("eventDate AT TIME ZONE '%s'", timeZoneId),
			OffsetDateTime.class);

		if (interval != Interval.HOUR) {
			eventDateField = _dateTrunc(DatePart.DAY, eventDateField);
		}
		else {
			eventDateField = _dateTrunc(DatePart.HOUR, eventDateField);
		}

		eventDateField = eventDateField.as("eventDate");

		Field<OffsetDateTime> event1EventDateField = DSL.field(
			"event1.\"eventDate\"", OffsetDateTime.class);

		SelectSelectStep<Record2<OffsetDateTime, Object>> selectSelectStep =
			_dslContext.selectDistinct(eventDateField, DSL.field("sessionId"));

		return _toMap(
			_dslContext.select(
				event1EventDateField, DSL.count()
			).from(
				DSL.table(
					selectSelectStep.from(
						"BQEvent"
					).innerJoin(
						DSL.table("EventDefinition")
					).on(
						DSL.field(
							"BQEvent.eventId"
						).eq(
							DSL.field("EventDefinition.name")
						)
					).where(
						_createCondition(
							channelId, keywords, rangeEndLocalDateTime,
							rangeStartLocalDateTime, timeZoneId, userIds)
					)
				).as(
					"event1"
				)
			).groupBy(
				event1EventDateField
			).fetch(
				record -> record
			));
	}

	public List<BQEvent> searchBQEvents(
		Long channelId, @Nullable String keywords, Pageable pageable,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Table<Record> eventTable = DSL.table("BQEvent");

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			eventTable.asterisk());

		return selectSelectStep.from(
			eventTable
		).innerJoin(
			DSL.table("EventDefinition")
		).on(
			DSL.field(
				"BQEvent.eventId"
			).eq(
				DSL.field("EventDefinition.name")
			)
		).where(
			_createCondition(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds)
		).orderBy(
			getSortFields(pageable.getSort(), eventTable)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new BQEvent(record.intoMap())
		);
	}

	private SelectJoinStep _buildSelectJoinStep(
		BreakdownItem breakdownItem,
		EventAnalysisBreakdown eventAnalysisBreakdown,
		EventAttributeDefinition eventAttributeDefinition,
		SelectJoinStep selectJoinStep, String timeZoneId) {

		AttributeType attributeType = eventAnalysisBreakdown.getAttributeType();

		selectJoinStep = _joinEventAttributeTable(
			attributeType, eventAttributeDefinition, selectJoinStep);

		if (breakdownItem == null) {
			return selectJoinStep;
		}

		List<EventAnalysisFilter> eventAnalysisFilters =
			breakdownItem.getEventAnalysisFilters();

		Map<Long, EventAttributeDefinition> eventAttributeDefinitionMap =
			_getEventAttributeDefinitionMap(eventAnalysisFilters);

		for (int i = 0; i < eventAnalysisFilters.size(); i++) {
			EventAnalysisFilter eventAnalysisFilter = eventAnalysisFilters.get(
				i);

			eventAttributeDefinition = eventAttributeDefinitionMap.get(
				Long.valueOf(eventAnalysisFilter.getAttributeId()));

			attributeType = eventAnalysisFilter.getAttributeType();

			String tableName = attributeType.getTableName();

			if (Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

				tableName = "BQEvent";
			}

			String alias = StringUtils.lowerCase(tableName) + i;

			FilterOperator filterOperator = FilterOperators.of(
				eventAnalysisFilter.getDataType(),
				eventAnalysisFilter.getOperator(),
				eventAnalysisFilter.getValues());

			Condition condition = DSL.noCondition();
			Field attributeField = null;

			if (Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

				attributeField = _getGlobalAttributeField(
					alias, eventAttributeDefinition.getName());
			}
			else {
				attributeField = DSL.field(
					attributeType.getQualifiedAttributeValueFieldName(alias));

				Field eventAttributeDefinitionIdField = DSL.field(
					alias + ".name");

				condition = eventAttributeDefinitionIdField.eq(
					eventAttributeDefinition.getName());
			}

			selectJoinStep = selectJoinStep.join(
				DSL.table(
					tableName
				).as(
					alias
				)
			).on(
				DSL.field(
					DSL.field(_getJoinFieldTableName(attributeType))
				).eq(
					DSL.field(DSL.field(alias + ".id"))
				)
			).and(
				DSL.and(
					condition,
					filterOperator.getCondition(
						_getField(
							eventAnalysisFilter, DSL.field(attributeField),
							timeZoneId)))
			);
		}

		return selectJoinStep;
	}

	private Condition _createCondition(
		Long channelId, String keyword, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Condition condition = DSL.and(
			DSL.field(
				"BQEvent.channelId"
			).eq(
				channelId
			),
			DSL.field(
				"BQEvent.eventDate"
			).between(
				DateUtil.toUTCLocalDateTime(
					rangeStartLocalDateTime, ZoneId.of(timeZoneId)),
				DateUtil.toUTCLocalDateTime(
					rangeEndLocalDateTime, ZoneId.of(timeZoneId))
			),
			DSL.field(
				"EventDefinition.hidden", Boolean.class
			).isFalse());

		if ((userIds != null) && !userIds.isEmpty()) {
			condition = DSL.and(
				condition,
				DSL.field(
					"BQEvent.userId"
				).in(
					userIds
				));
		}

		if (!StringUtils.isEmpty(keyword)) {
			Condition keywordCondition = DSL.or(
				DSL.field(
					"EventDefinition.name"
				).containsIgnoreCase(
					keyword
				),
				DSL.exists(
					DSL.select(
						DSL.field("id")
					).from(
						DSL.table(
							"BQEventProperty"
						).where(
							DSL.and(
								DSL.field(
									"BQEventProperty.id"
								).eq(
									DSL.field("BQEvent.id")
								)),
							DSL.field(
								"BQEventProperty.value"
							).containsIgnoreCase(
								keyword
							)
						)
					)));

			for (String globalAttribute : _globalAttributes.values()) {
				keywordCondition = keywordCondition.or(
					DSL.field(
						"BQEvent." + globalAttribute
					).containsIgnoreCase(
						keyword
					));
			}

			condition = condition.and(keywordCondition);
		}

		return condition;
	}

	private <OffsetDateTime> Field<OffsetDateTime> _dateTrunc(
		DatePart datePart, Field<OffsetDateTime> field) {

		if (datePart == DatePart.WEEK) {
			return DSL.field(
				"date_trunc({0}, {1} + INTERVAL '1 DAY') - INTERVAL '1 DAY'",
				field.getDataType(), datePart.toSQL(), field);
		}

		return DSL.field(
			"date_trunc({0}, {1})", field.getDataType(), datePart.toSQL(),
			field);
	}

	private List<Condition> _getConditions(
		Long channelId, List<EventAnalysisFilter> eventAnalysisFilters,
		Long eventDefinitionId, Date rangeEndDate, Date rangeStartDate,
		String timeZoneId) {

		List<Condition> conditions = _getConditions(
			channelId, eventDefinitionId, rangeEndDate, rangeStartDate);

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, rangeEndDate, rangeStartDate,
					timeZoneId));
		}

		return conditions;
	}

	private List<Condition> _getConditions(
		Long channelId, Long eventDefinitionId, Date rangeEndDate,
		Date rangeStartDate) {

		List<Condition> conditions = new ArrayList<>();

		if (channelId != null) {
			Field<Object> field = DSL.field("BQEvent.channelId");

			conditions.add(field.eq(channelId));
		}

		if (eventDefinitionId != null) {
			Field<Object> field = DSL.field("EventDefinition.id");

			conditions.add(field.eq(eventDefinitionId));
		}

		if ((rangeEndDate != null) && (rangeStartDate != null)) {
			Field<Object> field = DSL.field("BQEvent.eventDate");

			conditions.add(field.between(rangeStartDate, rangeEndDate));
		}

		return conditions;
	}

	private Field<OffsetDateTime> _getDateValueField(
		Field field, String timeZoneId) {

		StringBuilder sb = new StringBuilder();

		sb.append(DSL.function("try_cast_timestamp", Object.class, field));
		sb.append(" AT TIME ZONE 'UTC'");

		if (!timeZoneId.equals("UTC")) {
			sb.append(" AT TIME ZONE '");
			sb.append(timeZoneId);
			sb.append("'");
		}

		return DSL.field(sb.toString(), OffsetDateTime.class);
	}

	private Condition _getEventAnalysisFilterCondition(
		AttributeType attributeType,
		List<EventAnalysisFilter> eventAnalysisFilters, Date rangeEndDate,
		Date rangeStartDate, String timeZoneId) {

		Condition conditions = DSL.noCondition();

		Map<Long, EventAttributeDefinition> eventAttributeDefinitionMap =
			_getEventAttributeDefinitionMap(eventAnalysisFilters);

		Stream<EventAnalysisFilter> stream = eventAnalysisFilters.stream();

		Map<String, List<EventAnalysisFilter>> eventAnalysisFiltersByField =
			stream.collect(
				Collectors.groupingBy(EventAnalysisFilter::getAttributeId));

		for (Map.Entry<String, List<EventAnalysisFilter>> entry :
				eventAnalysisFiltersByField.entrySet()) {

			EventAttributeDefinition eventAttributeDefinition =
				eventAttributeDefinitionMap.get(Long.valueOf(entry.getKey()));

			Condition condition = _getValueCondition(
				attributeType, eventAttributeDefinition, rangeEndDate,
				rangeStartDate);

			for (EventAnalysisFilter eventAnalysisFilter : entry.getValue()) {
				FilterOperator filterOperator = FilterOperators.of(
					eventAnalysisFilter.getDataType(),
					eventAnalysisFilter.getOperator(),
					eventAnalysisFilter.getValues());

				Field field = DSL.field("BQEventProperty.value");

				if (Objects.equals(
						eventAttributeDefinition.getType(),
						EventAttributeDefinition.Type.GLOBAL)) {

					field = _getGlobalAttributeField(
						eventAttributeDefinition.getName());
				}

				condition = condition.and(
					filterOperator.getCondition(
						_getField(eventAnalysisFilter, field, timeZoneId)));
			}

			conditions = conditions.or(condition);
		}

		Field<Object> field = DSL.field(_getJoinFieldTableName(attributeType));

		SelectJoinStep<Record1<Object>> selectJoinStep = _dslContext.select(
			field
		).from(
			"BQEvent"
		);

		if (_hasNonglobalAttributes(eventAttributeDefinitionMap)) {
			selectJoinStep = selectJoinStep.join(
				attributeType.getTableName()
			).on(
				field.eq(DSL.field(attributeType.getJoinFieldName()))
			);
		}

		SelectHavingConditionStep<Record1<Object>> selectHavingConditionStep =
			selectJoinStep.where(
				conditions
			).groupBy(
				field
			).having(
				DSL.count(
					field
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
		Date rangeStartDate, String timeZoneId) {

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
					rangeStartDate, timeZoneId));
		}

		return conditions;
	}

	private EventAttributeDefinition _getEventAttributeDefinition(
		String attributeId) {

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findById(
				Long.valueOf(attributeId));

		return eventAttributeDefinitionOptional.orElse(null);
	}

	private Map<Long, EventAttributeDefinition> _getEventAttributeDefinitionMap(
		List<EventAnalysisFilter> eventAnalysisFilters) {

		Stream<EventAnalysisFilter> eventAnalysisFiltersStream =
			eventAnalysisFilters.stream();

		List<EventAttributeDefinition> eventAttributeDefinitions =
			IterableUtils.toList(
				_eventAttributeDefinitionRepository.findAllById(
					eventAnalysisFiltersStream.map(
						EventAnalysisFilter::getAttributeId
					).map(
						Long::valueOf
					).collect(
						Collectors.toList()
					)));

		Stream<EventAttributeDefinition> eventAttributeDefinitionsStream =
			eventAttributeDefinitions.stream();

		return eventAttributeDefinitionsStream.collect(
			Collectors.toMap(
				EventAttributeDefinition::getId, Function.identity()));
	}

	private Integer _getEventsCount(
		Long channelId, AggregateFunction<Integer> countAggregateFunction,
		String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Table<Record> eventTable = DSL.table("BQEvent");

		SelectSelectStep<Record1<Integer>> selectCount = _dslContext.select(
			countAggregateFunction);

		return selectCount.from(
			eventTable
		).innerJoin(
			DSL.table("EventDefinition")
		).on(
			DSL.field(
				"BQEvent.eventId"
			).eq(
				DSL.field("EventDefinition.name")
			)
		).where(
			_createCondition(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds)
		).fetchOne(
			countAggregateFunction
		);
	}

	private <T> SelectJoinStep<Record1<T>> _getEventSelectJoinStep(
		Long eventDefinitionId, SelectSelectStep<Record1<T>> selectSelectStep) {

		SelectJoinStep<Record1<T>> selectJoinStep = selectSelectStep.from(
			"BQEvent");

		if (eventDefinitionId == null) {
			return selectJoinStep;
		}

		return _joinEventDefinitionTable(selectJoinStep);
	}

	private Field _getField(
		EventAnalysisFilter eventAnalysisFilter, Field field,
		String timeZoneId) {

		EventAttributeDefinition.DataType dataType =
			eventAnalysisFilter.getDataType();

		if (dataType.equals(EventAttributeDefinition.DataType.BOOLEAN)) {
			return DSL.function("try_cast_boolean", Object.class, field);
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			return _dateTrunc(
				DatePart.DAY, _getDateValueField(field, timeZoneId));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			return DSL.round(
				DSL.abs(
					DSL.function("try_cast_bigint", BigInteger.class, field)),
				-3);
		}

		if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			return DSL.round(
				DSL.function("try_cast_float", BigDecimal.class, field));
		}

		return field;
	}

	private Field _getGlobalAttributeField(String name) {
		return _getGlobalAttributeField("BQEvent", name);
	}

	private Field _getGlobalAttributeField(String alias, String name) {
		return DSL.field(
			alias.concat(
				"."
			).concat(
				_globalAttributes.get(name)
			));
	}

	private String _getJoinFieldTableName(AttributeType attributeType) {
		if (attributeType.equals(AttributeType.EVENT)) {
			return "BQEvent.id";
		}

		return "BQEvent.individualId";
	}

	private OrderField<?> _getOrderField(Field field, String sortType) {
		if ((sortType == null) || sortType.equalsIgnoreCase("DESC")) {
			return field.desc();
		}

		return field.asc();
	}

	private Field _getSelectField(
		AnalysisType analysisType,
		EventAttributeDefinition eventAttributeDefinition) {

		Field field = DSL.field("BQEventProperty.value");

		if (Objects.equals(
				eventAttributeDefinition.getType(),
				EventAttributeDefinition.Type.GLOBAL)) {

			field = _getGlobalAttributeField(
				eventAttributeDefinition.getName());
		}

		if (analysisType.equals(AnalysisType.AVERAGE)) {
			return DSL.count(
				field
			).cast(
				SQLDataType.DECIMAL
			).div(
				_getUniqueIndividualsField()
			).as(
				"average"
			);
		}

		if (analysisType.equals(AnalysisType.TOTAL)) {
			return DSL.count(
				field
			).as(
				"count"
			);
		}

		return _getUniqueIndividualsField();
	}

	private Field<Integer> _getUniqueIndividualsField() {
		return DSL.countDistinct(DSL.field("BQEvent.userId"));
	}

	private Condition _getValueCondition(
		AttributeType attributeFilterType,
		EventAttributeDefinition eventAttributeDefinition, Date rangeEndDate,
		Date rangeStartDate) {

		Condition condition = DSL.noCondition();

		if (attributeFilterType.equals(AttributeType.EVENT)) {
			if (!Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

				Field eventAttributeDefinitionIdField = DSL.field(
					"BQEventProperty.name");

				condition = condition.and(
					eventAttributeDefinitionIdField.eq(
						eventAttributeDefinition.getName()));
			}

			if ((rangeEndDate != null) && (rangeStartDate != null)) {
				Field<Object> eventDateField = DSL.field(
					"BQEventProperty.eventDate");

				condition = condition.and(
					eventDateField.between(rangeStartDate, rangeEndDate));
			}
		}

		return condition;
	}

	private Field _getValueField(
		boolean alias, EventAnalysisBreakdown eventAnalysisBreakdown,
		EventAttributeDefinition eventAttributeDefinition, String timeZoneId) {

		Field field = null;

		EventAttributeDefinition.DataType dataType =
			eventAnalysisBreakdown.getDataType();

		Field attributeField = null;

		if (Objects.equals(
				eventAttributeDefinition.getType(),
				EventAttributeDefinition.Type.GLOBAL)) {

			attributeField = _getGlobalAttributeField(
				eventAttributeDefinition.getName());
		}
		else {
			AttributeType attributeType =
				eventAnalysisBreakdown.getAttributeType();

			attributeField = DSL.field(
				attributeType.getQualifiedAttributeValueFieldName(null));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.BOOLEAN)) {
			field = DSL.function(
				"try_cast_boolean", Boolean.class, attributeField);
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			DateGrouping dateGrouping =
				eventAnalysisBreakdown.getDateGrouping();

			Field<OffsetDateTime> offsetDateTimeField = _getDateValueField(
				attributeField, timeZoneId);

			if (dateGrouping.equals(DateGrouping.DAY)) {
				field = DSL.concat(
					DSL.extract(offsetDateTimeField, DatePart.YEAR),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.MONTH),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.DAY));
			}
			else if (dateGrouping.equals(DateGrouping.MONTH)) {
				field = DSL.concat(
					DSL.extract(offsetDateTimeField, DatePart.YEAR),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.MONTH));
			}
			else if (dateGrouping.equals(DateGrouping.YEAR)) {
				field = DSL.extract(offsetDateTimeField, DatePart.YEAR);
			}
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			field = DSL.floor(
				DSL.round(
					DSL.abs(
						DSL.function(
							"try_cast_bigint", BigInteger.class,
							attributeField)),
					-3
				).div(
					eventAnalysisBreakdown.getBinSize()
				)
			).multiply(
				eventAnalysisBreakdown.getBinSize()
			);
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			field = DSL.floor(
				DSL.round(
					DSL.function(
						"try_cast_float", BigDecimal.class, attributeField)
				).div(
					eventAnalysisBreakdown.getBinSize()
				)
			).multiply(
				eventAnalysisBreakdown.getBinSize()
			);
		}
		else {
			field = DSL.lower(attributeField.cast(String.class));
		}

		if (alias) {
			return field.as("temp");
		}

		return field;
	}

	private boolean _hasNonglobalAttributes(
		Map<Long, EventAttributeDefinition> eventAttributeDefinitionMap) {

		for (EventAttributeDefinition eventAttributeDefinition :
				eventAttributeDefinitionMap.values()) {

			if (!Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

				return true;
			}
		}

		return false;
	}

	private SelectJoinStep _joinEventAttributeTable(
		AttributeType attributeType,
		EventAttributeDefinition eventAttributeDefinition,
		SelectJoinStep selectJoinStep) {

		if (Objects.equals(
				eventAttributeDefinition.getType(),
				EventAttributeDefinition.Type.GLOBAL)) {

			return selectJoinStep;
		}

		return selectJoinStep.join(
			attributeType.getTableName()
		).on(
			DSL.field(
				_getJoinFieldTableName(attributeType)
			).eq(
				DSL.field(attributeType.getJoinFieldName())
			)
		).and(
			DSL.field(
				attributeType.getQualifiedAttributeIdFieldName(null)
			).eq(
				eventAttributeDefinition.getName()
			)
		);
	}

	private SelectJoinStep _joinEventDefinitionTable(
		SelectJoinStep selectJoinStep) {

		return selectJoinStep.join(
			"EventDefinition"
		).on(
			DSL.field(
				"BQEvent.eventId"
			).eq(
				DSL.field("EventDefinition.name")
			)
		);
	}

	private Map<String, Integer> _toMap(
		List<Record2<OffsetDateTime, Integer>> record2s) {

		Stream<Record2<OffsetDateTime, Integer>> stream = record2s.stream();

		return stream.collect(
			Collectors.toMap(
				record2 -> {
					OffsetDateTime offsetDateTime = record2.value1();

					LocalDateTime localDateTime =
						offsetDateTime.toLocalDateTime();

					return localDateTime.toString();
				},
				Record2::value2));
	}

	private static final Map<String, String> _globalAttributes =
		new HashMap<String, String>() {
			{
				put("canonicalUrl", "canonicalUrl");
				put("pageDescription", "description");
				put("pageKeywords", "keywords");
				put("pageTitle", "title");
				put("referrer", "referrer");
				put("url", "url");
			}
		};

	private final DSLContext _dslContext;

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

}