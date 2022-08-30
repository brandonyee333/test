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

package com.liferay.osb.asah.backend.dog.site;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQPageRepository;
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.time.ZoneId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class SiteMetricDog {

	public SiteMetric getSiteMetric(SearchQueryContext searchQueryContext) {
		SiteMetric siteMetric = new SiteMetric();

		Long channelId = Long.parseLong(searchQueryContext.getChannelId());
		boolean includePrevious = searchQueryContext.isIncludePrevious();
		TimeRange timeRange = searchQueryContext.getTimeRange();
		ZoneId zoneId = _timeZoneDog.getZoneId();

		List<SiteVisitorBehaviorMetric> pageSiteVisitorBehaviorMetrics =
			_bqPageRepository.getSiteVisitorBehaviorMetrics(
				channelId, includePrevious, timeRange, zoneId);

		if (!pageSiteVisitorBehaviorMetrics.isEmpty()) {
			_setMetricValues(
				includePrevious, _pageMetricsFunctionMap,
				pageSiteVisitorBehaviorMetrics, siteMetric);
		}

		List<SiteVisitorBehaviorMetric> sessionSiteVisitorBehaviorMetrics =
			_bqSessionRepository.getSiteVisitorBehaviorMetrics(
				channelId, includePrevious, timeRange, zoneId);

		if (!sessionSiteVisitorBehaviorMetrics.isEmpty()) {
			_setMetricValues(
				includePrevious, _sessionMetricsFunctionMap,
				sessionSiteVisitorBehaviorMetrics, siteMetric);
		}

		return siteMetric;
	}

	private void _setMetricPreviousValue(Metric metric, Double value) {
		metric.setPreviousValue(value);
	}

	private void _setMetricValue(Metric metric, Double value) {
		metric.setValue(value);
	}

	private void _setMetricValues(
		boolean includePrevious,
		Map
			<Function<SiteMetric, Metric>,
			 Function<SiteVisitorBehaviorMetric, Object>> metricsFunctionMap,
		List<SiteVisitorBehaviorMetric> siteVisitorBehaviorMetrics,
		SiteMetric siteMetric) {

		for (Map.Entry
				<Function<SiteMetric, Metric>,
				 Function<SiteVisitorBehaviorMetric, Object>> entry :
					metricsFunctionMap.entrySet()) {

			Function<SiteMetric, Metric> metricFunction = entry.getKey();
			Function<SiteVisitorBehaviorMetric, Object> metricValueFunction =
				entry.getValue();

			_setMetricValue(
				metricFunction.apply(siteMetric),
				Double.parseDouble(
					String.valueOf(
						metricValueFunction.apply(
							siteVisitorBehaviorMetrics.get(0)))));

			if (includePrevious && (siteVisitorBehaviorMetrics.size() > 1)) {
				_setMetricPreviousValue(
					metricFunction.apply(siteMetric),
					Double.parseDouble(
						String.valueOf(
							metricValueFunction.apply(
								siteVisitorBehaviorMetrics.get(1)))));
			}
		}
	}

	private static final Map
		<Function<SiteMetric, Metric>,
		 Function<SiteVisitorBehaviorMetric, Object>> _pageMetricsFunctionMap =
			new HashMap
				<Function<SiteMetric, Metric>,
				 Function<SiteVisitorBehaviorMetric, Object>>() {

				{
					put(
						SiteMetric::getAnonymousVisitorsMetric,
						SiteVisitorBehaviorMetric::getAnonymousVisitors);
					put(
						SiteMetric::getKnownVisitorsMetric,
						SiteVisitorBehaviorMetric::getKnownVisitors);
					put(
						SiteMetric::getVisitorsMetric,
						SiteVisitorBehaviorMetric::getVisitors);
				}
			};

	private static final Map
		<Function<SiteMetric, Metric>,
		 Function<SiteVisitorBehaviorMetric, Object>>
			_sessionMetricsFunctionMap =
				new HashMap
					<Function<SiteMetric, Metric>,
					 Function<SiteVisitorBehaviorMetric, Object>>() {

					{
						put(
							SiteMetric::getBounceRateMetric,
							SiteVisitorBehaviorMetric::getBounceRate);
						put(
							SiteMetric::getSessionDurationMetric,
							SiteVisitorBehaviorMetric::getSessionDuration);
						put(
							SiteMetric::getSessionsMetric,
							SiteVisitorBehaviorMetric::getSessions);
					}
				};

	@Autowired
	private BQPageRepository _bqPageRepository;

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}