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
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class SiteMetricDog {

	public SiteMetric getSiteMetric(SearchQueryContext searchQueryContext) {
		SiteMetric siteMetric = new SiteMetric();

		boolean includePrevious = searchQueryContext.isIncludePrevious();

		List<SiteVisitorBehaviorMetric> sessionSiteVisitorBehaviorMetrics =
			_bqSessionRepository.getSiteVisitorBehaviorMetrics(
				Long.parseLong(searchQueryContext.getChannelId()),
				includePrevious, searchQueryContext.getTimeRange(),
				_timeZoneDog.getZoneId());

		Stream<SiteVisitorBehaviorMetric> stream =
			sessionSiteVisitorBehaviorMetrics.stream();

		Map<String, SiteVisitorBehaviorMetric>
			sessionSiteVisitorBehaviorMetricsMap = stream.collect(
				Collectors.toMap(
					sessionSiteVisitorBehaviorMetric -> {
						if (sessionSiteVisitorBehaviorMetric.isPrevious()) {
							return "previous";
						}

						return "current";
					},
					Function.identity()));

		_setMetricValues(
			sessionSiteVisitorBehaviorMetricsMap.get("current"),
			sessionSiteVisitorBehaviorMetricsMap.get("previous"), siteMetric);

		return siteMetric;
	}

	private void _setMetricPreviousValue(Metric metric, Double value) {
		metric.setPreviousValue(value);
	}

	private void _setMetricValue(Metric metric, Double value) {
		metric.setValue(value);
	}

	private void _setMetricValues(
		SiteVisitorBehaviorMetric currentSiteVisitorBehaviorMetric,
		SiteVisitorBehaviorMetric previousSiteVisitorBehaviorMetric,
		SiteMetric siteMetric) {

		for (Map.Entry
				<Function<SiteMetric, Metric>,
				 Function<SiteVisitorBehaviorMetric, Object>> entry :
					_sessionMetricsFunctionMap.entrySet()) {

			Function<SiteMetric, Metric> metricFunction = entry.getKey();
			Function<SiteVisitorBehaviorMetric, Object> metricValueFunction =
				entry.getValue();

			if (currentSiteVisitorBehaviorMetric != null) {
				_setMetricValue(
					metricFunction.apply(siteMetric),
					Double.parseDouble(
						String.valueOf(
							metricValueFunction.apply(
								currentSiteVisitorBehaviorMetric))));
			}

			if (previousSiteVisitorBehaviorMetric != null) {
				_setMetricPreviousValue(
					metricFunction.apply(siteMetric),
					Double.parseDouble(
						String.valueOf(
							metricValueFunction.apply(
								previousSiteVisitorBehaviorMetric))));
			}
		}
	}

	private static final Map
		<Function<SiteMetric, Metric>,
		 Function<SiteVisitorBehaviorMetric, Object>>
			_sessionMetricsFunctionMap =
				new HashMap
					<Function<SiteMetric, Metric>,
					 Function<SiteVisitorBehaviorMetric, Object>>() {

					{
						put(
							SiteMetric::getAnonymousVisitorsMetric,
							SiteVisitorBehaviorMetric::getAnonymousVisitors);
						put(
							SiteMetric::getBounceRateMetric,
							SiteVisitorBehaviorMetric::getBounceRate);
						put(
							SiteMetric::getKnownVisitorsMetric,
							SiteVisitorBehaviorMetric::getKnownVisitors);
						put(
							SiteMetric::getSessionDurationMetric,
							SiteVisitorBehaviorMetric::
								getAverageSessionDuration);
						put(
							SiteMetric::getSessionsMetric,
							SiteVisitorBehaviorMetric::getSessions);
						put(
							SiteMetric::getSessionsPerVisitorMetric,
							SiteVisitorBehaviorMetric::getSessionsPerVisitor);
						put(
							SiteMetric::getVisitorsMetric,
							SiteVisitorBehaviorMetric::getVisitors);
					}
				};

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}