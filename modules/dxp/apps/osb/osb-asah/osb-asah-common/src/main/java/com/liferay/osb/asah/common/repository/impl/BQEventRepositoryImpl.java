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

import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.Tuple3;
import com.liferay.osb.asah.common.repository.CustomBQEventRepository;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.SelectFinalStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Leslie Wong
 */
@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
public class BQEventRepositoryImpl
	extends BaseBQEventRepository implements CustomBQEventRepository {

	public BQEventRepositoryImpl(DSLContext dslContext) {
		super(dslContext);
	}

	@Override
	public Integer countBQEvents(
		Long channelId, @Nullable String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountBQEventsSelect(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds);

		return selectFinalStep.fetchOne(DSL.count());
	}

	@Override
	public long countByEventDefinitionId(long eventDefinitionId) {
		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountByEventDefinitionIdSelect(eventDefinitionId);

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public Integer countEventSessions(
		Long channelId, String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountEventSessionsSelect(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds);

		return selectFinalStep.fetchOne(
			DSL.countDistinct(DSL.field("sessionId")));
	}

	@Override
	public long countTotalBQEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountTotalBQEventsSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId);

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countUniqueIndividuals(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getCountUniqueIndividualsSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId);

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public Optional<BQEvent> findLastSeenBQEvent(
		@Nullable Long eventDefinitionId) {

		SelectFinalStep<Record> selectFinalStep = getFindLastSeenBQEventSelect(
			eventDefinitionId);

		return selectFinalStep.fetchOptional(
			record -> new BQEvent(record.intoMap()));
	}

	@Override
	public BigDecimal getAverageBQEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectFinalStep<Record1<Number>> selectFinalStep =
			getGetAverageBQEventCountPerIndividualSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId);

		return selectFinalStep.fetchOptional(
			0, BigDecimal.class
		).orElse(
			BigDecimal.ZERO
		);
	}

	@Override
	public Map<Object, Number> getBQEventPropertyValues(
		AnalysisType analysisType, @Nullable BreakdownItem breakdownItem,
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, Pageable pageable,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate,
		String timeZoneId) {

		Tuple3<Field<Number>, SelectFinalStep<Record>, Field> tuple3 =
			getGetBQEventPropertyValuesSelect(
				analysisType, breakdownItem, channelId, eventAnalysisBreakdown,
				eventAnalysisFilters, eventDefinitionId, pageable, rangeEndDate,
				rangeStartDate, timeZoneId);

		if (tuple3 == null) {
			return Collections.emptyMap();
		}

		Map<Object, Number> eventAttributeValues = new LinkedHashMap<>();

		Field<Number> selectField = tuple3.getT1();
		SelectFinalStep<Record> selectFinalStep = tuple3.getT2();

		selectFinalStep.fetch(
		).forEach(
			record -> eventAttributeValues.put(
				record.get(tuple3.getT3()),
				(Number)record.get(selectField.getName()))
		);

		return eventAttributeValues;
	}

	@Override
	public long getBQEventPropertyValuesCount(
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		SelectFinalStep<Record1<Integer>> selectFinalStep =
			getGetBQEventPropertyValuesCountSelect(
				channelId, eventAnalysisBreakdown, eventAnalysisFilters,
				eventDefinitionId, rangeEndDate, rangeStartDate, timeZoneId);

		if (selectFinalStep == null) {
			return 0;
		}

		return selectFinalStep.fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public Map<String, Integer> getBQEventsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectFinalStep<Record2<OffsetDateTime, Integer>> selectFinalStep =
			getGetBQEventsCountGroupByEventDateSelect(
				channelId, interval, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds);

		return _toMap(selectFinalStep.fetch(record -> record));
	}

	@Override
	public Map<String, Integer> getEventSessionsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectFinalStep<Record2<OffsetDateTime, Integer>> selectFinalStep =
			getGetEventSessionsCountGroupByEventDateSelect(
				channelId, interval, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds);

		return _toMap(selectFinalStep.fetch(record -> record));
	}

	@Override
	public List<BQEvent> searchBQEvents(
		Long channelId, @Nullable String keywords, Pageable pageable,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		SelectFinalStep<Record> selectFinalStep = getSearchBQEventsSelect(
			channelId, keywords, pageable, rangeEndLocalDateTime,
			rangeStartLocalDateTime, timeZoneId, userIds);

		return selectFinalStep.fetch(record -> new BQEvent(record.intoMap()));
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

}