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
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.AttributeType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.filter.FilterOperator;
import com.liferay.osb.asah.common.model.filter.FilterOperators;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.Record1;
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

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate)
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

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate)
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

	public BigDecimal getAverageEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate) {

		Field totalEventCount = DSL.count();

		totalEventCount = totalEventCount.cast(SQLDataType.DECIMAL);

		SelectSelectStep<Record1<Number>> selectSelectStep = _dslContext.select(
			totalEventCount.div(DSL.nullif(_getUniqueIndividualsField(), 0)));

		SelectJoinStep<Record1<Number>> selectJoinStep = selectSelectStep.from(
			"Event");

		return selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate)
		).fetchOptional(
			0, BigDecimal.class
		).orElse(
			BigDecimal.ZERO
		);
	}

	public Map<Object, Number> getEventAttributeValues(
		AnalysisType analysisType, @Nullable BreakdownItem breakdownItem,
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, Pageable pageable,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate) {

		Map<Object, Number> eventAttributeValues = new LinkedHashMap<>();

		Field<Number> selectField = _getSelectField(analysisType);

		AttributeType attributeType = eventAnalysisBreakdown.getAttributeType();

		Field valueField = DSL.field(
			attributeType.getQualifiedAttributeValueFieldName(null));

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectJoinStep<Record> selectJoinStep = _buildSelectJoinStep(
			breakdownItem, eventAnalysisBreakdown,
			selectSelectStep.select(
				selectField, valueField
			).from(
				"Event"
			));

		selectJoinStep.where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate)
		).groupBy(
			valueField
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

	public long getEventAttributeValuesCount(
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate) {

		AttributeType attributeType = eventAnalysisBreakdown.getAttributeType();

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.select(
				DSL.countDistinct(
					DSL.field(
						attributeType.getQualifiedAttributeValueFieldName(
							null))));

		SelectJoinStep<Record1<Integer>> selectJoinStep = selectSelectStep.from(
			"Event");

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
				Long.valueOf(eventAnalysisBreakdown.getAttributeId())
			)
		).where(
			_getConditions(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Event> searchEvents(
		Long channelId, Long individualId, String keywords, Pageable pageable,
		TimeRange timeRange) {

		Table<Record> eventTable = DSL.table("Event");

		SelectSelectStep<Record> selectSelectStep = _dslContext.selectDistinct(
			eventTable.asterisk());

		Condition condition = DSL.and(
			DSL.field(
				"Event.channelId"
			).eq(
				channelId
			),
			DSL.field(
				"Event.eventDate"
			).between(
				DateUtil.toUTCLocalDateTime(
					timeRange.getStartLocalDateTime(),
					_timeZoneDog.getZoneId()),
				DateUtil.toUTCLocalDateTime(
					timeRange.getEndLocalDateTime(), _timeZoneDog.getZoneId())
			),
			DSL.field(
				"Event.individualId"
			).eq(
				individualId
			));

		if (!StringUtils.isEmpty(keywords)) {
			condition = condition.and(
				DSL.or(
					DSL.field(
						"EventAttribute.value"
					).containsIgnoreCase(
						keywords
					),
					DSL.field(
						"EventDefinition.name"
					).containsIgnoreCase(
						keywords
					)));
		}

		return selectSelectStep.from(
			eventTable
		).innerJoin(
			DSL.table("EventAttribute")
		).on(
			DSL.field(
				"Event.id"
			).eq(
				DSL.field("EventAttribute.eventId")
			)
		).innerJoin(
			DSL.table("EventDefinition")
		).on(
			DSL.field(
				"Event.eventDefinitionId"
			).eq(
				DSL.field("EventDefinition.id")
			)
		).where(
			condition
		).orderBy(
			getSortFields(pageable.getSort(), eventTable)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Event(record.intoMap())
		);
	}

	private SelectJoinStep _buildSelectJoinStep(
		BreakdownItem breakdownItem,
		EventAnalysisBreakdown eventAnalysisBreakdown,
		SelectJoinStep selectJoinStep) {

		AttributeType attributeType = eventAnalysisBreakdown.getAttributeType();

		selectJoinStep = selectJoinStep.join(
			attributeType.getTableName()
		).on(
			DSL.field(
				_getJoinFieldTableName(attributeType)
			).eq(
				DSL.field(attributeType.getQualifiedJoinFieldName(null))
			).and(
				DSL.field(
					attributeType.getQualifiedAttributeIdFieldName(null)
				).eq(
					Long.valueOf(eventAnalysisBreakdown.getAttributeId())
				)
			)
		);

		if (breakdownItem == null) {
			return selectJoinStep;
		}

		List<EventAnalysisFilter> eventAnalysisFilters =
			breakdownItem.getEventAnalysisFilters();

		for (int i = 0; i < eventAnalysisFilters.size(); i++) {
			EventAnalysisFilter eventAnalysisFilter = eventAnalysisFilters.get(
				i);

			attributeType = eventAnalysisFilter.getAttributeType();

			String alias =
				StringUtils.lowerCase(attributeType.getTableName()) + i;

			List<String> values = eventAnalysisFilter.getValues();

			selectJoinStep = selectJoinStep.join(
				DSL.table(
					attributeType.getTableName()
				).as(
					alias
				)
			).on(
				DSL.field(
					_getJoinFieldTableName(attributeType)
				).eq(
					DSL.field(attributeType.getQualifiedJoinFieldName(alias))
				)
			).and(
				DSL.and(
					DSL.field(
						attributeType.getQualifiedAttributeIdFieldName(alias)
					).eq(
						Long.valueOf(eventAnalysisFilter.getAttributeId())
					),
					DSL.field(
						attributeType.getQualifiedAttributeValueFieldName(alias)
					).eq(
						values.get(0)
					))
			);
		}

		return selectJoinStep;
	}

	private List<Condition> _getConditions(
		Long channelId, List<EventAnalysisFilter> eventAnalysisFilters,
		Long eventDefinitionId, Date rangeEndDate, Date rangeStartDate) {

		List<Condition> conditions = _getConditions(
			channelId, eventDefinitionId, rangeEndDate, rangeStartDate);

		if (eventAnalysisFilters != null) {
			conditions.addAll(
				_getEventAnalysisFilterConditions(
					eventAnalysisFilters, rangeEndDate, rangeStartDate));
		}

		return conditions;
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
			return "Event.id";
		}

		return "Event.individualId";
	}

	private OrderField<?> _getOrderField(Field field, String sortType) {
		if ((sortType == null) || sortType.equalsIgnoreCase("DESC")) {
			return field.desc();
		}

		return field.asc();
	}

	private Field _getSelectField(AnalysisType analysisType) {
		if (analysisType.equals(AnalysisType.AVERAGE)) {
			return DSL.count(
				DSL.field("EventAttribute.value")
			).cast(
				SQLDataType.DECIMAL
			).div(
				_getUniqueIndividualsField()
			).as(
				"average"
			);
		}
		else if (analysisType.equals(AnalysisType.TOTAL)) {
			return DSL.count(
				DSL.field("EventAttribute.value")
			).as(
				"count"
			);
		}

		return _getUniqueIndividualsField();
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

	@Autowired
	private TimeZoneDog _timeZoneDog;

}