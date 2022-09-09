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
import com.liferay.osb.asah.common.entity.EventDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.BreakdownRow;
import com.liferay.osb.asah.common.model.DateGrouping;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.filter.FilterOperator;
import com.liferay.osb.asah.common.model.filter.FilterOperators;
import com.liferay.osb.asah.common.repository.CustomBQEventRepository;
import com.liferay.osb.asah.common.repository.EventAttributeDefinitionRepository;
import com.liferay.osb.asah.common.repository.EventDefinitionRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.util.GetterUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.jooq.SelectFinalStep;
import org.jooq.SelectHavingStep;
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
public class BQEventRepositoryImpl
	extends BaseRepository implements CustomBQEventRepository {

	public BQEventRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public Integer countBQEvents(
		Long channelId, @Nullable String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return (int)_queryExecutor.queryForLong(
			_getEventsCount(
				channelId, DSL.count(), keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds));
	}

	@Override
	public long countByEventDefinitionId(long eventDefinitionId) {
		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_getEventSelectJoinStep(_dslContext.selectCount());

		return _queryExecutor.queryForLong(
			selectJoinStep.where(
				_getConditions(null, eventDefinitionId, null, null)));
	}

	@Override
	public Integer countEventSessions(
		Long channelId, String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return (int)_queryExecutor.queryForLong(
			_getEventsCount(
				channelId, DSL.countDistinct(DSL.field("sessionId")), keywords,
				rangeEndLocalDateTime, rangeStartLocalDateTime, timeZoneId,
				userIds));
	}

	@Override
	public long countTotalBQEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectJoinStep<Record1<Integer>> selectJoinStep;

		String filteredEventsTableName = null;

		if ((eventAnalysisFilters == null) ||
			!_hasOnlyLocalAttributes(eventAnalysisFilters)) {

			selectJoinStep = _getEventSelectJoinStep(_dslContext.selectCount());
		}
		else {
			filteredEventsTableName = "_filteredEvents";

			selectJoinStep = _getFiltersCommonTableSelectJoinStep(
				channelId, eventDefinitionId, filteredEventsTableName,
				rangeEndDate, rangeStartDate);
		}

		return _queryExecutor.queryForLong(
			selectJoinStep.where(
				_getConditions(
					channelId, eventAnalysisFilters, eventDefinitionId,
					filteredEventsTableName, rangeEndDate, rangeStartDate,
					timeZoneId)));
	}

	@Override
	public long countUniqueIndividuals(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(_getUniqueIndividualsField(null));

		SelectJoinStep<Record1<Integer>> selectJoinStep =
			_getEventSelectJoinStep(selectSelectStep);

		return _queryExecutor.queryForLong(
			selectJoinStep.where(
				_getConditions(
					channelId, eventAnalysisFilters, eventDefinitionId,
					rangeEndDate, rangeStartDate, timeZoneId)));
	}

	@Override
	public Optional<BQEvent> findLastSeenBQEvent(
		@Nullable Long eventDefinitionId) {

		Table<Record> eventTable = DSL.table("BQEvent");

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			eventTable.asterisk());

		Condition condition = DSL.noCondition();

		if (eventDefinitionId != null) {
			Optional<EventDefinition> eventDefinitionOptional =
				_eventDefinitionRepository.findById(eventDefinitionId);

			EventDefinition eventDefinition = eventDefinitionOptional.get();

			condition = DSL.field(
				"BQEvent.eventId"
			).eq(
				eventDefinition.getName()
			);
		}

		return _queryExecutor.queryForObject(
			BQEvent::new,
			selectSelectStep.from(
				"BQEvent"
			).where(
				condition
			).orderBy(
				DSL.field(
					"eventDate"
				).desc()
			).limit(
				1
			));
	}

	@Override
	public BigDecimal getAverageBQEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		Field totalEventCount = DSL.count();

		totalEventCount = totalEventCount.cast(SQLDataType.DECIMAL);

		SelectSelectStep<Record1<Number>> selectSelectStep = _dslContext.select(
			totalEventCount.div(
				DSL.nullif(_getUniqueIndividualsField(null), 0)));

		SelectJoinStep<Record1<Number>> selectJoinStep =
			_getEventSelectJoinStep(selectSelectStep);

		return _queryExecutor.queryForBigDecimal(
			selectJoinStep.where(
				_getConditions(
					channelId, eventAnalysisFilters, eventDefinitionId,
					rangeEndDate, rangeStartDate, timeZoneId)));
	}

	@Override
	public List<BreakdownRow> getBQEventPropertyValues(
		AnalysisType analysisType, @Nullable Long channelId,
		boolean compareToPrevious,
		List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters, Long eventDefinitionId,
		Pageable pageable, TimeRange timeRange, String timeZoneId) {

		Stream<EventAnalysisBreakdown> stream =
			eventAnalysisBreakdowns.stream();

		Map<String, EventAttributeDefinition> eventAttributeDefinitions =
			_getEventAttributeDefinitions(
				stream.map(
					eventAnalysisBreakdown -> Long.valueOf(
						eventAnalysisBreakdown.getAttributeId())
				).collect(
					Collectors.toList()
				));

		EventAnalysisBreakdown lastEventAnalysisBreakdown = null;

		List<Field> valueFields = new ArrayList();

		if (!eventAnalysisBreakdowns.isEmpty()) {
			lastEventAnalysisBreakdown = eventAnalysisBreakdowns.get(
				eventAnalysisBreakdowns.size() - 1);

			for (EventAnalysisBreakdown eventAnalysisBreakdown :
					eventAnalysisBreakdowns) {

				valueFields.add(
					_getValueField(
						true, eventAnalysisBreakdown,
						eventAttributeDefinitions.get(
							eventAnalysisBreakdown.getAttributeId()),
						timeZoneId));
			}
		}

		List<Field<Number>> selectFields = new ArrayList<>();

		selectFields.add(
			_getSelectField(
				analysisType,
				_getEventAttributeDefinition(
					lastEventAnalysisBreakdown, eventAttributeDefinitions),
				timeRange));

		if (compareToPrevious) {
			Field previousSelectField = _getSelectField(
				analysisType,
				_getEventAttributeDefinition(
					lastEventAnalysisBreakdown, eventAttributeDefinitions),
				timeRange.getPreviousTimeRange());

			selectFields.add(previousSelectField.as("previous"));

			timeRange = timeRange.getIncludePreviousTimeRange();
		}

		SelectJoinStep<Record> selectJoinStep = _buildSelectJoinStep(
			channelId, eventAnalysisBreakdowns, eventAttributeDefinitions,
			_dslContext.select(
				ListUtils.union(valueFields, selectFields)
			).from(
				"BQEvent"
			),
			timeRange);

		SelectConditionStep<Record> selectConditionStep = selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisBreakdowns, eventAnalysisFilters,
				eventAttributeDefinitions, eventDefinitionId, pageable,
				timeRange, timeZoneId));

		if (!valueFields.isEmpty()) {
			return _queryExecutor.queryForList(
				BreakdownRow::new, selectConditionStep.groupBy(valueFields));
		}

		return _queryExecutor.queryForList(
			BreakdownRow::new, selectConditionStep);
	}

	@Override
	public long getBQEventPropertyValuesCount(
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, TimeRange timeRange,
		String timeZoneId) {

		EventAttributeDefinition eventAttributeDefinition =
			_getEventAttributeDefinition(
				eventAnalysisBreakdown.getAttributeId());

		if (eventAttributeDefinition == null) {
			return 0;
		}

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

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			"BQEvent");

		selectJoinStep = _buildSelectJoinStep(
			channelId, Arrays.asList(eventAnalysisBreakdown),
			Collections.singletonMap(
				eventAnalysisBreakdown.getAttributeId(),
				eventAttributeDefinition),
			selectJoinStep, timeRange);

		return _queryExecutor.queryForLong(
			selectJoinStep.where(
				_getConditions(
					channelId, eventAnalysisFilters, eventDefinitionId,
					timeRange.getEndDate(), timeRange.getStartDate(),
					timeZoneId)));
	}

	@Override
	public Map<String, Integer> getBQEventsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Field<OffsetDateTime> eventDateField =
			_dslHelper.getDateAtTimeZoneField("BQEvent.eventDate", timeZoneId);

		if (interval == Interval.DAY) {
			eventDateField = _dslHelper.dateTrunc(DatePart.DAY, eventDateField);
		}
		else if (interval == Interval.HOUR) {
			eventDateField = _dslHelper.dateTrunc(
				DatePart.HOUR, eventDateField);
		}
		else if (interval == Interval.MONTH) {
			eventDateField = _dslHelper.dateTrunc(
				DatePart.MONTH, eventDateField);
		}
		else {
			eventDateField = _dslHelper.dateTrunc(
				DatePart.WEEK, eventDateField);
		}

		eventDateField = eventDateField.as("eventDateTrunc");

		return _queryExecutor.queryForMap(
			GetterUtil::getDateString,
			_dslContext.select(
				eventDateField, DSL.count()
			).from(
				"BQEvent"
			).where(
				_createCondition(
					channelId, keywords, rangeEndLocalDateTime,
					rangeStartLocalDateTime, timeZoneId, userIds)
			).groupBy(
				eventDateField
			),
			GetterUtil::getInteger);
	}

	@Override
	public Map<String, Integer> getEventSessionsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Field<OffsetDateTime> eventDateField =
			_dslHelper.getDateAtTimeZoneField("BQEvent.eventDate", timeZoneId);

		if (interval != Interval.HOUR) {
			eventDateField = _dslHelper.dateTrunc(DatePart.DAY, eventDateField);
		}
		else {
			eventDateField = _dslHelper.dateTrunc(
				DatePart.HOUR, eventDateField);
		}

		eventDateField = eventDateField.as("eventDate");

		Field<OffsetDateTime> event1EventDateField = DSL.field(
			"event1.eventDate", OffsetDateTime.class);

		SelectSelectStep<Record2<OffsetDateTime, Object>> selectSelectStep =
			_dslContext.selectDistinct(eventDateField, DSL.field("sessionId"));

		return _queryExecutor.queryForMap(
			object -> GetterUtil.getDateString(object),
			_dslContext.select(
				event1EventDateField, DSL.count()
			).from(
				DSL.table(
					selectSelectStep.from(
						"BQEvent"
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
			),
			GetterUtil::getInteger);
	}

	@Override
	public Map<String, Date> getLastSeenDateDateGroupedByColumnName(
		String columnName, int size) {

		Field<String> columnField = DSL.field(columnName, String.class);

		SelectSelectStep<Record2<String, Date>> selectSelectStep =
			_dslContext.select(
				columnField,
				DSL.max(
					DSL.field("eventdate", Date.class)
				).as(
					"lastseendate"
				));

		SelectHavingStep<Record2<String, Date>> selectHavingStep =
			selectSelectStep.from(
				"BQEvent"
			).where(
				DSL.field(
					"eventdate"
				).ge(
					_dslHelper.getDateParam(
						DateUtil.addDays(DateUtil.newDate(), -7))
				)
			).groupBy(
				columnField
			);

		return _queryExecutor.queryForMap(
			key -> (String)key,
			_dslContext.select(
			).from(
				selectHavingStep
			).orderBy(
				DSL.field(
					"lastseendate"
				).desc()
			).limit(
				size
			),
			value -> (Date)value);
	}

	@Override
	public List<BQEvent> searchBQEvents(
		Long channelId, @Nullable String keywords, Pageable pageable,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		Table<Record> eventTable = DSL.table("BQEvent");

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			eventTable.asterisk());

		return _queryExecutor.queryForList(
			BQEvent::new,
			selectSelectStep.from(
				eventTable
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
			));
	}

	private SelectJoinStep _buildSelectJoinStep(
		Long channelId, List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		Map<String, EventAttributeDefinition> eventAttributeDefinitions,
		SelectJoinStep selectJoinStep, TimeRange timeRange) {

		for (EventAnalysisBreakdown eventAnalysisBreakdown :
				eventAnalysisBreakdowns) {

			EventAttributeDefinition eventAttributeDefinition =
				eventAttributeDefinitions.get(
					eventAnalysisBreakdown.getAttributeId());

			if (Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

				continue;
			}

			AttributeType attributeType =
				eventAnalysisBreakdown.getAttributeType();

			Table<Record> table = DSL.table(
				String.format(
					"%s as %s", attributeType.getTableName(),
					_getEventAttributeDefinitionName(
						eventAttributeDefinition)));

			Condition condition = DSL.and(
				_getChannelIdFilter(
					channelId,
					String.format(
						"%s.channelId",
						_getEventAttributeDefinitionName(
							eventAttributeDefinition)))
			).and(
				DSL.field(
					_getJoinFieldTableName(attributeType)
				).eq(
					DSL.field(
						String.format(
							"%s.%s",
							_getEventAttributeDefinitionName(
								eventAttributeDefinition),
							attributeType.getJoinFieldName()))
				)
			).and(
				DSL.field(
					String.format(
						"%s.%s",
						_getEventAttributeDefinitionName(
							eventAttributeDefinition),
						attributeType.getAttributeIdFieldName())
				).eq(
					eventAttributeDefinition.getName()
				)
			);

			if (Objects.equals(attributeType, AttributeType.EVENT)) {
				condition = condition.and(
					_getEventDateRangeFilter(
						String.format(
							"%s.eventDate",
							_getEventAttributeDefinitionName(
								eventAttributeDefinition)),
						timeRange.getEndDate(), timeRange.getStartDate()));
			}

			selectJoinStep = selectJoinStep.join(
				table
			).on(
				condition
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
				"BQEvent.applicationId"
			).in(
				_eventDefinitionRepository.getEventDefinitionApplicationIds(
					false)
			),
			DSL.field(
				"BQEvent.channelId"
			).eq(
				channelId
			),
			DSL.field(
				"BQEvent.eventDate"
			).between(
				_dslHelper.getDateParam(rangeStartLocalDateTime, timeZoneId),
				_dslHelper.getDateParam(rangeEndLocalDateTime, timeZoneId)
			),
			DSL.field(
				"BQEvent.eventId"
			).in(
				_eventDefinitionRepository.getEventDefinitionNames(false)
			));

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
				DSL.lower(
					DSL.field("BQEvent.eventId", String.class)
				).like(
					"%" + StringUtils.lowerCase(keyword) + "%"
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
							DSL.lower(
								DSL.field("BQEventProperty.value", String.class)
							).like(
								"%" + StringUtils.lowerCase(keyword) + "%"
							)
						)
					)));

			for (String globalAttribute : _globalAttributes.values()) {
				keywordCondition = keywordCondition.or(
					DSL.lower(
						DSL.field("BQEvent." + globalAttribute, String.class)
					).like(
						"%" + StringUtils.lowerCase(keyword) + "%"
					));
			}

			condition = condition.and(keywordCondition);
		}

		return condition;
	}

	private Condition _getChannelIdFilter(Long channelId, String fieldName) {
		if (channelId == null) {
			return DSL.noCondition();
		}

		Field<Object> field = DSL.field(fieldName);

		return field.eq(channelId);
	}

	private List<Condition> _getConditions(
		Long channelId, List<EventAnalysisBreakdown> eventAnalysisBreakdowns,
		List<EventAnalysisFilter> eventAnalysisFilters,
		Map<String, EventAttributeDefinition> eventAttributeDefinitions,
		Long eventDefinitionId, Pageable pageable, TimeRange timeRange,
		String timeZoneId) {

		List<Condition> conditions = _getConditions(
			channelId, eventAnalysisFilters, eventDefinitionId,
			timeRange.getEndDate(), timeRange.getStartDate(), timeZoneId);

		if (eventAnalysisBreakdowns.isEmpty()) {
			return conditions;
		}

		EventAnalysisBreakdown firstEventAnalysisBreakdown =
			eventAnalysisBreakdowns.get(0);

		Field firstValueField = _getValueField(
			false, firstEventAnalysisBreakdown,
			_getEventAttributeDefinition(
				firstEventAnalysisBreakdown, eventAttributeDefinitions),
			timeZoneId);

		List<EventAnalysisFilter> filteredEventAnalysisFilters = null;

		if (eventAnalysisFilters != null) {
			Stream<EventAnalysisFilter> stream = eventAnalysisFilters.stream();

			filteredEventAnalysisFilters = stream.filter(
				eventAnalysisFilter -> eventAttributeDefinitions.containsKey(
					firstEventAnalysisBreakdown.getAttributeId())
			).collect(
				Collectors.toList()
			);
		}

		conditions.add(
			firstValueField.in(
				_buildSelectJoinStep(
					channelId, Arrays.asList(firstEventAnalysisBreakdown),
					eventAttributeDefinitions,
					DSL.selectDistinct(
						firstValueField
					).from(
						"BQEvent"
					),
					timeRange
				).where(
					_getConditions(
						channelId, filteredEventAnalysisFilters,
						eventDefinitionId, timeRange.getEndDate(),
						timeRange.getStartDate(), timeZoneId)
				).limit(
					pageable.getPageSize()
				).offset(
					pageable.getOffset()
				)));

		return conditions;
	}

	private List<Condition> _getConditions(
		Long channelId, List<EventAnalysisFilter> eventAnalysisFilters,
		Long eventDefinitionId, Date rangeEndDate, Date rangeStartDate,
		String timeZoneId) {

		return _getConditions(
			channelId, eventAnalysisFilters, eventDefinitionId, null,
			rangeEndDate, rangeStartDate, timeZoneId);
	}

	private List<Condition> _getConditions(
		Long channelId, List<EventAnalysisFilter> eventAnalysisFilters,
		Long eventDefinitionId, String filteredEventsTableName,
		Date rangeEndDate, Date rangeStartDate, String timeZoneId) {

		List<Condition> conditions = new ArrayList<>();

		if (filteredEventsTableName == null) {
			conditions = _getConditions(
				channelId, eventDefinitionId, rangeEndDate, rangeStartDate);
		}

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, filteredEventsTableName, rangeEndDate,
					rangeStartDate, timeZoneId));
		}

		return conditions;
	}

	private List<Condition> _getConditions(
		Long channelId, Long eventDefinitionId, Date rangeEndDate,
		Date rangeStartDate) {

		List<Condition> conditions = new ArrayList<>();

		EventDefinition eventDefinition = null;

		if (eventDefinitionId != null) {
			Optional<EventDefinition> eventDefinitionOptional =
				_eventDefinitionRepository.findById(eventDefinitionId);

			eventDefinition = eventDefinitionOptional.orElse(null);
		}

		if (eventDefinition != null) {
			Field<Object> field = DSL.field("BQEvent.applicationId");

			conditions.add(
				field.in(
					SetUtil.of(
						"CustomEvent", eventDefinition.getApplicationId())));
		}

		if (channelId != null) {
			Field<Object> field = DSL.field("BQEvent.channelId");

			conditions.add(field.eq(channelId));
		}

		if (eventDefinition != null) {
			Field<Object> field = DSL.field("BQEvent.eventId");

			conditions.add(field.eq(eventDefinition.getName()));
		}

		conditions.add(
			_getEventDateRangeFilter(
				"BQEvent.eventDate", rangeEndDate, rangeStartDate));

		return conditions;
	}

	private Condition _getEventAnalysisFilterCondition(
		AttributeType attributeType, EventAnalysisFilter eventAnalysisFilter,
		String filteredEventsTableName, Date rangeEndDate, Date rangeStartDate,
		String timeZoneId) {

		Condition conditions = DSL.noCondition();

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_getEventAttributeDefinition(eventAnalysisFilter);

		if (!eventAttributeDefinitionOptional.isPresent()) {
			return conditions;
		}

		EventAttributeDefinition eventAttributeDefinition =
			eventAttributeDefinitionOptional.get();

		Condition condition = _getValueCondition(
			attributeType, eventAttributeDefinition, filteredEventsTableName,
			rangeEndDate, rangeStartDate);

		FilterOperator filterOperator = FilterOperators.of(
			eventAnalysisFilter.getDataType(), _dslHelper,
			eventAnalysisFilter.getOperator(), eventAnalysisFilter.getValues());

		String fieldTableName = "BQEventProperty";

		if (filteredEventsTableName != null) {
			fieldTableName = filteredEventsTableName;
		}

		Field field = DSL.field(fieldTableName + ".value");

		if (_isGlobalAttribute(eventAttributeDefinition)) {
			if (filteredEventsTableName == null) {
				field = _getGlobalAttributeField(
					eventAttributeDefinition.getName());
			}
			else {
				String attributeDefinitionName = _globalAttributes.get(
					eventAttributeDefinition.getName());

				field = DSL.field(
					filteredEventsTableName + "." + attributeDefinitionName);
			}
		}

		conditions = conditions.and(
			condition.and(
				filterOperator.getCondition(
					_getField(eventAnalysisFilter, field, timeZoneId))));

		Field<Object> joinFieldTableField = DSL.field(
			_getJoinFieldTableName(attributeType));

		if (filteredEventsTableName == null) {
			return joinFieldTableField.in(
				_getFilterCondition(
					attributeType, conditions, eventAttributeDefinition,
					rangeEndDate, rangeStartDate));
		}

		return joinFieldTableField.in(
			_getFilterConditionForCommonTable(
				conditions, filteredEventsTableName));
	}

	private List<Condition> _getEventAnalysisFilterConditions(
		List<EventAnalysisFilter> eventAnalysisFilters,
		String filteredEventsTableName, Date rangeEndDate, Date rangeStartDate,
		String timeZoneId) {

		List<Condition> conditions = new ArrayList<>();

		for (EventAnalysisFilter filter : eventAnalysisFilters) {
			conditions.add(
				_getEventAnalysisFilterCondition(
					filter.getAttributeType(), filter, filteredEventsTableName,
					rangeEndDate, rangeStartDate, timeZoneId));
		}

		return conditions;
	}

	private EventAttributeDefinition _getEventAttributeDefinition(
		EventAnalysisBreakdown eventAnalysisBreakdown,
		Map<String, EventAttributeDefinition> eventAttributeDefinitions) {

		if (eventAnalysisBreakdown == null) {
			return null;
		}

		return eventAttributeDefinitions.get(
			eventAnalysisBreakdown.getAttributeId());
	}

	private Optional<EventAttributeDefinition> _getEventAttributeDefinition(
		EventAnalysisFilter eventAnalysisFilter) {

		return _eventAttributeDefinitionRepository.findById(
			Long.valueOf(eventAnalysisFilter.getAttributeId()));
	}

	private EventAttributeDefinition _getEventAttributeDefinition(
		String attributeId) {

		Optional<EventAttributeDefinition> eventAttributeDefinitionOptional =
			_eventAttributeDefinitionRepository.findById(
				Long.valueOf(attributeId));

		return eventAttributeDefinitionOptional.orElse(null);
	}

	private String _getEventAttributeDefinitionName(
		EventAttributeDefinition eventAttributeDefinition) {

		return "_" + eventAttributeDefinition.getName();
	}

	private Map<String, EventAttributeDefinition> _getEventAttributeDefinitions(
		List<Long> attributeIds) {

		List<EventAttributeDefinition> attributeDefinitions =
			IterableUtils.toList(
				_eventAttributeDefinitionRepository.findAllById(attributeIds));

		Stream<EventAttributeDefinition> stream = attributeDefinitions.stream();

		return stream.collect(
			Collectors.toMap(
				eventAttributeDefinition -> String.valueOf(
					eventAttributeDefinition.getId()),
				Function.identity()));
	}

	private List<EventAttributeDefinition> _getEventAttributesDefinitions(
		List<EventAnalysisFilter> eventAnalysisFilters) {

		Stream<EventAnalysisFilter> eventAnalysisFiltersStream =
			eventAnalysisFilters.stream();

		return IterableUtils.toList(
			_eventAttributeDefinitionRepository.findAllById(
				eventAnalysisFiltersStream.map(
					EventAnalysisFilter::getAttributeId
				).map(
					Long::valueOf
				).collect(
					Collectors.toList()
				)));
	}

	private Condition _getEventDateRangeFilter(
		String fieldName, Date rangeEndDate, Date rangeStartDate) {

		if ((rangeEndDate != null) && (rangeStartDate != null)) {
			Field<Object> field = DSL.field(fieldName);

			return field.between(
				_dslHelper.getDateParam(rangeStartDate),
				_dslHelper.getDateParam(rangeEndDate));
		}

		return DSL.noCondition();
	}

	private SelectFinalStep<Record1<Integer>> _getEventsCount(
		Long channelId, AggregateFunction<Integer> countAggregateFunction,
		String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectSelectStep<Record1<Integer>> selectCount = _dslContext.select(
			countAggregateFunction);

		return selectCount.from(
			"BQEvent"
		).where(
			_createCondition(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds)
		);
	}

	private <T> SelectJoinStep<Record1<T>> _getEventSelectJoinStep(
		SelectSelectStep<Record1<T>> selectSelectStep) {

		return selectSelectStep.from("BQEvent");
	}

	private Field _getField(
		EventAnalysisFilter eventAnalysisFilter, Field field,
		String timeZoneId) {

		EventAttributeDefinition.DataType dataType =
			eventAnalysisFilter.getDataType();

		if (dataType.equals(EventAttributeDefinition.DataType.BOOLEAN)) {
			return _dslHelper.getCastBooleanField(field);
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			return _dslHelper.dateTrunc(
				DatePart.DAY, _dslHelper.getDateValueField(field, timeZoneId));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			return _dslHelper.getCastDurationField(field);
		}

		if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			return _dslHelper.getCastNumberField(field);
		}

		return field;
	}

	private SelectHavingStep<Record1<Object>> _getFilterCondition(
		AttributeType attributeType, Condition conditions,
		EventAttributeDefinition eventAttributeDefinition, Date rangeEndDate,
		Date rangeStartDate) {

		if ((rangeEndDate != null) && (rangeStartDate != null)) {
			Field<Object> eventDateField = DSL.field("BQEvent.eventDate");

			conditions = conditions.and(
				eventDateField.between(
					_dslHelper.getDateParam(rangeStartDate),
					_dslHelper.getDateParam(rangeEndDate)));
		}

		Field<Object> field = DSL.field(_getJoinFieldTableName(attributeType));

		SelectJoinStep<Record1<Object>> selectJoinStep = _dslContext.select(
			field
		).from(
			"BQEvent"
		);

		if (!_isGlobalAttribute(eventAttributeDefinition)) {
			selectJoinStep = selectJoinStep.join(
				attributeType.getTableName()
			).on(
				field.eq(
					DSL.field(attributeType.getQualifiedJoinFieldName(null)))
			);
		}

		return selectJoinStep.where(
			conditions
		).groupBy(
			field
		);
	}

	private SelectConditionStep<Record1<Object>>
		_getFilterConditionForCommonTable(
			Condition conditions, String filteredEventsTableName) {

		Field<Object> field = DSL.field("id");

		SelectJoinStep<Record1<Object>> selectJoinStep = _dslContext.select(
			field
		).from(
			filteredEventsTableName
		);

		return selectJoinStep.where(conditions);
	}

	private SelectJoinStep<Record1<Integer>>
		_getFiltersCommonTableSelectJoinStep(
			Long channelId, Long eventDefinitionId,
			String filteredEventsTableName, Date rangeEndDate,
			Date rangeStartDate) {

		SelectSelectStep<Record3<Object, Object, Object>> selectConditionStep =
			_dslContext.select(
				DSL.field("BQEvent.*"), DSL.field("BQEventProperty.name"),
				DSL.field("BQEventProperty.value"));

		SelectConditionStep<Record3<Object, Object, Object>>
			filtersCommonTable = selectConditionStep.from(
				"BQEvent"
			).join(
				"BQEventProperty"
			).on(
				"BQEvent.id = BQEventProperty.id"
			).where(
				_getConditions(
					channelId, eventDefinitionId, rangeEndDate, rangeStartDate)
			);

		return _getEventSelectJoinStep(
			_dslContext.with(
				filteredEventsTableName
			).as(
				filtersCommonTable
			).select(
				DSL.count(DSL.field("BQEvent.eventId"))
			));
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

	private Field _getSelectField(
		AnalysisType analysisType,
		EventAttributeDefinition eventAttributeDefinition,
		TimeRange timeRange) {

		Field field = null;

		if (eventAttributeDefinition == null) {
			field = DSL.when(
				_getEventDateRangeFilter(
					"BQEvent.eventDate", timeRange.getEndDate(),
					timeRange.getStartDate()),
				DSL.field("BQEvent.id"));
		}
		else if (Objects.equals(
					eventAttributeDefinition.getType(),
					EventAttributeDefinition.Type.GLOBAL)) {

			field = DSL.when(
				_getEventDateRangeFilter(
					"BQEvent.eventDate", timeRange.getEndDate(),
					timeRange.getStartDate()),
				_getGlobalAttributeField(eventAttributeDefinition.getName()));
		}
		else {
			field = DSL.when(
				_getEventDateRangeFilter(
					_getEventAttributeDefinitionName(eventAttributeDefinition) +
						".eventDate",
					timeRange.getEndDate(), timeRange.getStartDate()),
				DSL.field(
					_getEventAttributeDefinitionName(eventAttributeDefinition) +
						".value"));
		}

		if (analysisType.equals(AnalysisType.AVERAGE)) {
			Field<Integer> uniqueIndividualsField = _getUniqueIndividualsField(
				timeRange);

			field = DSL.when(
				uniqueIndividualsField.notEqual(0),
				DSL.count(
					field
				).cast(
					SQLDataType.DECIMAL
				).div(
					uniqueIndividualsField
				)
			).otherwise(
				BigDecimal.ZERO
			);

			return field.as("average");
		}

		if (analysisType.equals(AnalysisType.TOTAL)) {
			return DSL.count(
				field
			).as(
				"count"
			);
		}

		return _getUniqueIndividualsField(timeRange);
	}

	private Field<Integer> _getUniqueIndividualsField(TimeRange timeRange) {
		if (timeRange == null) {
			return DSL.countDistinct(DSL.field("BQEvent.userId"));
		}

		return DSL.countDistinct(
			DSL.when(
				_getEventDateRangeFilter(
					"BQEvent.eventDate", timeRange.getEndDate(),
					timeRange.getStartDate()),
				DSL.field("BQEvent.userId")));
	}

	private Condition _getValueCondition(
		AttributeType attributeFilterType,
		EventAttributeDefinition eventAttributeDefinition,
		String filteredEventsTableName, Date rangeEndDate,
		Date rangeStartDate) {

		Condition condition = DSL.noCondition();

		if (attributeFilterType.equals(AttributeType.EVENT) &&
			!Objects.equals(
				eventAttributeDefinition.getType(),
				EventAttributeDefinition.Type.GLOBAL)) {

			if (filteredEventsTableName == null) {
				filteredEventsTableName = "BQEventProperty";
			}

			Field eventAttributeDefinitionIdField = DSL.field(
				filteredEventsTableName + ".name");

			condition = condition.and(
				eventAttributeDefinitionIdField.eq(
					eventAttributeDefinition.getName()));

			if ((rangeEndDate != null) && (rangeStartDate != null)) {
				Field<Object> eventDateField = DSL.field(
					filteredEventsTableName + ".eventDate");

				condition = condition.and(
					eventDateField.between(
						_dslHelper.getDateParam(rangeStartDate),
						_dslHelper.getDateParam(rangeEndDate)));
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
				attributeType.getQualifiedAttributeValueFieldName(
					_getEventAttributeDefinitionName(
						eventAttributeDefinition)));
		}

		if (dataType.equals(EventAttributeDefinition.DataType.BOOLEAN)) {
			field = _dslHelper.getCastBooleanField(attributeField);
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DATE)) {
			DateGrouping dateGrouping =
				eventAnalysisBreakdown.getDateGrouping();

			Field<OffsetDateTime> offsetDateTimeField =
				_dslHelper.getDateValueField(attributeField, timeZoneId);

			if (dateGrouping.equals(DateGrouping.DAY)) {
				field = _dslHelper.concat(
					DSL.extract(offsetDateTimeField, DatePart.YEAR),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.MONTH),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.DAY));
			}
			else if (dateGrouping.equals(DateGrouping.MONTH)) {
				field = _dslHelper.concat(
					DSL.extract(offsetDateTimeField, DatePart.YEAR),
					DSL.val("-"),
					DSL.extract(offsetDateTimeField, DatePart.MONTH));
			}
			else if (dateGrouping.equals(DateGrouping.YEAR)) {
				field = DSL.extract(offsetDateTimeField, DatePart.YEAR);
			}
			else {
				field = offsetDateTimeField;
			}
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.DURATION)) {
			field = DSL.floor(
				DSL.round(
					DSL.abs(_dslHelper.getCastDurationField(attributeField)), -3
				).div(
					eventAnalysisBreakdown.getBinSize()
				)
			).multiply(
				eventAnalysisBreakdown.getBinSize()
			);
		}
		else if (dataType.equals(EventAttributeDefinition.DataType.NUMBER)) {
			Field castField = _dslHelper.getCastNumberField(attributeField);

			field = DSL.floor(
				castField.div(eventAnalysisBreakdown.getBinSize())
			).multiply(
				eventAnalysisBreakdown.getBinSize()
			);
		}
		else {
			field = DSL.lower(_dslHelper.getCastStringField(attributeField));
		}

		field = DSL.when(
			field.isNotNull(), _dslHelper.getCastStringField(field)
		).else_(
			"undefined"
		);

		if (alias) {
			return field.as(
				_getEventAttributeDefinitionName(eventAttributeDefinition));
		}

		return field;
	}

	private boolean _hasOnlyLocalAttributes(
		List<EventAnalysisFilter> eventAnalysisFilters) {

		for (EventAttributeDefinition filter :
				_getEventAttributesDefinitions(eventAnalysisFilters)) {

			if (!_isGlobalAttribute(filter)) {
				return true;
			}
		}

		return false;
	}

	private boolean _isGlobalAttribute(
		EventAttributeDefinition eventAttributeDefinition) {

		return Objects.equals(
			eventAttributeDefinition.getType(),
			EventAttributeDefinition.Type.GLOBAL);
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
	private DSLHelper _dslHelper;

	@Autowired
	private EventAttributeDefinitionRepository
		_eventAttributeDefinitionRepository;

	@Autowired
	private EventDefinitionRepository _eventDefinitionRepository;

	@Autowired
	private QueryExecutor _queryExecutor;

}