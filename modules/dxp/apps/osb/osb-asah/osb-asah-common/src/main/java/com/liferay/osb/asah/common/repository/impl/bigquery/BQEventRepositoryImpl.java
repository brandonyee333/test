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

package com.liferay.osb.asah.common.repository.impl.bigquery;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;

import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.Tuple3;
import com.liferay.osb.asah.common.repository.CustomBQEventRepository;
import com.liferay.osb.asah.common.repository.helper.BigQueryHelper;
import com.liferay.osb.asah.common.repository.impl.BaseBQEventRepository;
import com.liferay.osb.asah.common.repository.util.BigQueryUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Matthew Kong
 */
@ConditionalOnGoogleApplicationCredentials
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

		TableResult tableResult = _bigQueryHelper.query(
			getCountBQEventsSelect(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return (int)BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public long countByEventDefinitionId(long eventDefinitionId) {
		TableResult tableResult = _bigQueryHelper.query(
			getCountByEventDefinitionIdSelect(eventDefinitionId));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public Integer countEventSessions(
		Long channelId, String keywords, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		TableResult tableResult = _bigQueryHelper.query(
			getCountEventSessionsSelect(
				channelId, keywords, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return (int)BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public long countTotalBQEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		TableResult tableResult = _bigQueryHelper.query(
			getCountTotalBQEventsSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public long countUniqueIndividuals(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		TableResult tableResult = _bigQueryHelper.query(
			getCountUniqueIndividualsSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public Optional<BQEvent> findLastSeenBQEvent(
		@Nullable Long eventDefinitionId) {

		TableResult tableResult = _bigQueryHelper.query(
			getFindLastSeenBQEventSelect(eventDefinitionId));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return Optional.of(_getBQEvent(fieldValueList));
		}

		return Optional.empty();
	}

	@Override
	public BigDecimal getAverageBQEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate, String timeZoneId) {

		TableResult tableResult = _bigQueryHelper.query(
			getGetAverageBQEventCountPerIndividualSelect(
				channelId, eventAnalysisFilters, eventDefinitionId,
				rangeEndDate, rangeStartDate, timeZoneId));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return BigQueryUtil.getNumber(fieldValueList.get(0));
		}

		return BigDecimal.ZERO;
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

		TableResult tableResult = _bigQueryHelper.query(tuple3.getT2());

		Field<Number> selectField = tuple3.getT1();
		Field valueField = tuple3.getT3();

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			eventAttributeValues.put(
				BigQueryUtil.getObject(
					fieldValueList.get(valueField.getName())),
				BigQueryUtil.getNumber(
					fieldValueList.get(selectField.getName())));
		}

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

		TableResult tableResult = _bigQueryHelper.query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			return BigQueryUtil.getLong(fieldValueList.get(0));
		}

		return 0;
	}

	@Override
	public Map<String, Integer> getBQEventsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return _toMap(
			_bigQueryHelper.query(
				getGetBQEventsCountGroupByEventDateSelect(
					channelId, interval, keywords, rangeEndLocalDateTime,
					rangeStartLocalDateTime, timeZoneId, userIds)));
	}

	@Override
	public Map<String, Integer> getEventSessionsCountGroupByEventDate(
		Long channelId, Interval interval, String keywords,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		return _toMap(
			_bigQueryHelper.query(
				getGetEventSessionsCountGroupByEventDateSelect(
					channelId, interval, keywords, rangeEndLocalDateTime,
					rangeStartLocalDateTime, timeZoneId, userIds)));
	}

	@Override
	public List<BQEvent> searchBQEvents(
		Long channelId, @Nullable String keywords, Pageable pageable,
		LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId,
		Set<String> userIds) {

		List<BQEvent> bqEvents = new ArrayList<>();

		TableResult tableResult = _bigQueryHelper.query(
			getSearchBQEventsSelect(
				channelId, keywords, pageable, rangeEndLocalDateTime,
				rangeStartLocalDateTime, timeZoneId, userIds));

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			bqEvents.add(_getBQEvent(fieldValueList));
		}

		return bqEvents;
	}

	private BQEvent _getBQEvent(FieldValueList fieldValueList) {
		return new BQEvent(
			BigQueryUtil.getString(fieldValueList.get("applicationId")),
			BigQueryUtil.getString(fieldValueList.get("browserName")),
			BigQueryUtil.getString(fieldValueList.get("canonicalUrl")),
			BigQueryUtil.getLong(fieldValueList.get("channelId")),
			BigQueryUtil.getString(fieldValueList.get("city")),
			BigQueryUtil.getString(fieldValueList.get("contentLanguageId")),
			BigQueryUtil.getString(fieldValueList.get("context")),
			BigQueryUtil.getString(fieldValueList.get("country")),
			BigQueryUtil.getDate(fieldValueList.get("createDate")),
			BigQueryUtil.getLong(fieldValueList.get("dataSourceId")),
			BigQueryUtil.getString(fieldValueList.get("description")),
			BigQueryUtil.getString(fieldValueList.get("deviceType")),
			BigQueryUtil.getDate(fieldValueList.get("eventDate")),
			BigQueryUtil.getString(fieldValueList.get("eventId")),
			BigQueryUtil.getString(fieldValueList.get("eventProperties")),
			BigQueryUtil.getString(fieldValueList.get("experienceId")),
			BigQueryUtil.getString(fieldValueList.get("id")),
			BigQueryUtil.getString(fieldValueList.get("keywords")),
			BigQueryUtil.getString(fieldValueList.get("languageId")),
			BigQueryUtil.getString(fieldValueList.get("platformName")),
			BigQueryUtil.getString(fieldValueList.get("projectTimeZoneId")),
			BigQueryUtil.getString(fieldValueList.get("referrer")),
			BigQueryUtil.getString(fieldValueList.get("region")),
			BigQueryUtil.getString(fieldValueList.get("sessionId")),
			BigQueryUtil.getString(fieldValueList.get("timezoneOffset")),
			BigQueryUtil.getString(fieldValueList.get("title")),
			BigQueryUtil.getString(fieldValueList.get("url")),
			BigQueryUtil.getString(fieldValueList.get("userId")),
			BigQueryUtil.getString(fieldValueList.get("variantId")));
	}

	private Map<String, Integer> _toMap(TableResult tableResult) {
		Map<String, Integer> map = new HashMap<>();

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			map.put(
				BigQueryUtil.getString(fieldValueList.get(0)),
				(int)BigQueryUtil.getLong(fieldValueList.get(1)));
		}

		return map;
	}

	@Autowired
	private BigQueryHelper _bigQueryHelper;

}