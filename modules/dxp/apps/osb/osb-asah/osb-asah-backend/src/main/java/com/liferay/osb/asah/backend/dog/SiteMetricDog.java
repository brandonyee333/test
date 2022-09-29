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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 * @author Leilany Ulisses
 * @author Regisson Cesar
 * @author Thiago Buarque
 */
@Component
public class SiteMetricDog {

	public List<Metric> getBrowserMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, Integer> sessionsCountGroupedByBrowserName =
			_bqSessionRepository.getSessionsCountGroupedByBrowserName(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Set<Map.Entry<String, Integer>> set =
			sessionsCountGroupedByBrowserName.entrySet();

		Stream<Map.Entry<String, Integer>> stream = set.stream();

		return stream.map(
			entry -> {
				Metric metric = new Metric(metricType);

				metric.setValue(Double.valueOf(entry.getValue()));
				metric.setValueKey(entry.getKey());

				return metric;
			}
		).collect(
			Collectors.toList()
		);
	}

	public List<Metric> getDeviceMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, Metric> metrics = new LinkedHashMap<>();

		List<Map<String, Object>> sessionsCountGroupedByDeviceName =
			_bqSessionRepository.getSessionsCountGroupedByDeviceName(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Stream<Map<String, Object>> stream =
			sessionsCountGroupedByDeviceName.stream();

		stream.forEach(
			recordMap -> {
				Metric deviceTypeMetric = new Metric(metricType);

				String deviceType = (String)recordMap.get("deviceType");

				deviceTypeMetric.setValueKey(deviceType);

				metrics.putIfAbsent(deviceType, deviceTypeMetric);

				deviceTypeMetric = metrics.get(deviceType);

				Metric platformTypeMetric = new Metric(metricType);

				platformTypeMetric.setValue(
					Double.valueOf(String.valueOf(recordMap.get("count"))));
				platformTypeMetric.setValueKey(
					(String)recordMap.get("platformName"));

				deviceTypeMetric.addMetric(platformTypeMetric);
				deviceTypeMetric.setValue(
					deviceTypeMetric.getValue() +
						platformTypeMetric.getValue());
			});

		return new ArrayList<>(metrics.values());
	}

	public List<Metric> getGeolocationMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, Integer> sessions =
			_bqSessionRepository.getSessionsGroupedByGeolocation(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Set<Map.Entry<String, Integer>> entrySet = sessions.entrySet();

		Stream<Map.Entry<String, Integer>> stream = entrySet.stream();

		return stream.map(
			entry -> {
				Metric metric = new Metric(metricType);

				metric.setValue(Double.valueOf(entry.getValue()));
				metric.setValueKey(entry.getKey());

				return metric;
			}
		).collect(
			Collectors.toList()
		);
	}

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