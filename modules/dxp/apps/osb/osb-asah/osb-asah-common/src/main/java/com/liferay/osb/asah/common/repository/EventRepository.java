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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.Event;
import com.liferay.osb.asah.common.model.AnalysisType;
import com.liferay.osb.asah.common.model.BreakdownItem;
import com.liferay.osb.asah.common.model.EventAnalysisBreakdown;
import com.liferay.osb.asah.common.model.EventAnalysisFilter;
import com.liferay.osb.asah.common.model.EventAttributeValue;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Leslie Wong
 */
@Repository
public interface EventRepository extends OSBAsahRepository<Event, Long> {

	@Cacheable
	public Long countByChannelIdAndEventDateBetweenAndEventDefinitionId(
		Long channelId, Date rangeStartDate, Date rangeEndDate,
		long eventDefinitionId);

	@Cacheable
	public long countByEventDefinitionId(long eventDefinitionId);

	public Integer countEvents(
		Long channelId, Long individualId, String keywords,
		TimeRange timeRange);

	public Integer countEventSessions(
		Long channelId, Long individualId, String keywords,
		TimeRange timeRange);

	@Cacheable
	public long countTotalEvents(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate);

	@Cacheable
	public long countUniqueIndividuals(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate);

	@Cacheable
	public List<EventAttributeValue> findDistinctAttributeValues(
		@Param("eventAttributeDefinitionId") Long eventAttributeDefinitionId,
		@Param("size") int size);

	@Cacheable
	public Optional<Event> findFirstByOrderByIdDesc();

	@Cacheable
	public Optional<Event> findLastSeenEvent(@Nullable Long eventDefinitionId);

	@Cacheable
	public BigDecimal getAverageEventCountPerIndividual(
		@Nullable Long channelId,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate);

	@Cacheable
	public Map<Object, Number> getEventAttributeValues(
		AnalysisType analysisType, @Nullable BreakdownItem breakdownItem,
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, Pageable pageable,
		@Nullable Date rangeEndDate, @Nullable Date rangeStartDate);

	@Cacheable
	public long getEventAttributeValuesCount(
		@Nullable Long channelId, EventAnalysisBreakdown eventAnalysisBreakdown,
		@Nullable List<EventAnalysisFilter> eventAnalysisFilters,
		@Nullable Long eventDefinitionId, @Nullable Date rangeEndDate,
		@Nullable Date rangeStartDate);

	public Map<String, Integer> getEventsCountGroupByEventDate(
		Long channelId, Long individualId, Interval interval, String keywords,
		TimeRange timeRange);

	public Map<String, Integer> getEventSessionsCountGroupByEventDate(
		Long channelId, Long individualId, Interval interval, String keywords,
		TimeRange timeRange);

	public List<Event> searchEvents(
		Long channelId, Long individualId, String keywords, Pageable pageable,
		TimeRange timeRange);

}