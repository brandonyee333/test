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

import com.liferay.osb.asah.common.entity.BQSession;
import com.liferay.osb.asah.common.model.AcquisitionType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.math.BigDecimal;

import java.time.ZoneId;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

/**
 * @author Marcos Martins
 */
public interface CustomBQSessionRepository {

	public long countSessionFieldValues(
		String fieldName, FilterHelper filterHelper, String value);

	public List<BQSession> findAllById(Collection<String> sessionIds);

	public Map<String, BigDecimal> getAcquisitionsMetrics(
		AcquisitionType acquisitionType, Long channelId, Pageable pageable,
		TimeRange timeRange, ZoneId zoneId);

	public long getAcquisitionsMetricsCount(
		AcquisitionType acquisitionType, Long channelId, TimeRange timeRange,
		ZoneId zoneId);

	public List<Map<String, Object>> getCohortHeatMapTuples(
		Long channelId, Interval interval, TimeRange timeRange, ZoneId zoneId);

	public Map<String, BigDecimal> getSessionsCountGroupedByBrowserName(
		Long channelId, TimeRange timeRange, ZoneId zoneId);

	public List<Map<String, Object>> getSessionsCountGroupedByDeviceName(
		Long channelId, TimeRange timeRange, ZoneId zoneId);

	public Map<String, BigDecimal> getSessionsCountGroupedByGeolocation(
		Long channelId, TimeRange timeRange, ZoneId zoneId);

	public List<SiteVisitorBehaviorMetric> getSiteVisitorBehaviorMetrics(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId);

	public List<SiteVisitorBehaviorMetric>
		getSiteVisitorBehaviorMetricsGroupedBySessionStart(
			Long channelId, boolean includePrevious, Interval interval,
			TimeRange timeRange, ZoneId zoneId);

	public List<Map<String, BigDecimal>> getVisitorsCountGroupedByDayAndTime(
		Long channelId, TimeRange timeRange, ZoneId zoneId);

	public List<String> searchSessionFieldValues(
		String fieldName, FilterHelper filterHelper, Pageable pageable,
		String value);

}