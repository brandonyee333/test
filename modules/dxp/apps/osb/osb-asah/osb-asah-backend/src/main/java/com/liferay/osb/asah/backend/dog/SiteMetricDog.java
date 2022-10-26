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

import com.liferay.osb.asah.backend.dog.helper.MetricHelper;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.CohortHeatMapMetric;
import com.liferay.osb.asah.backend.model.CohortMetric;
import com.liferay.osb.asah.backend.model.Composition;
import com.liferay.osb.asah.backend.model.CompositionResultBag;
import com.liferay.osb.asah.backend.model.HeatMapMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.AcquisitionType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.math.BigDecimal;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 * @author Leilany Ulisses
 * @author Regisson Cesar
 * @author Thiago Buarque
 */
@Component
public class SiteMetricDog {

	public CompositionResultBag getAcquisitionsMetrics(
		AcquisitionType acquisitionType, String channelId, int size, int start,
		TimeRange timeRange) {

		long acquisitionsMetricsCount =
			_bqSessionRepository.getAcquisitionsMetricsCount(
				acquisitionType, Long.parseLong(channelId), timeRange,
				_timeZoneDog.getZoneId());

		if (acquisitionsMetricsCount == 0) {
			return new CompositionResultBag(Collections.emptyList(), 0, 0);
		}

		Map<String, BigDecimal> acquisitionsMetrics =
			_bqSessionRepository.getAcquisitionsMetrics(
				acquisitionType, Long.parseLong(channelId),
				PageRequest.of(start, size), timeRange,
				_timeZoneDog.getZoneId());

		if (acquisitionsMetrics.isEmpty()) {
			return new CompositionResultBag(Collections.emptyList(), 0, 0);
		}

		List<Composition> compositions = new ArrayList<>();

		for (Map.Entry<String, BigDecimal> entrySet :
				acquisitionsMetrics.entrySet()) {

			BigDecimal count = entrySet.getValue();

			compositions.add(
				new Composition(count.longValue(), entrySet.getKey()));
		}

		return new CompositionResultBag(
			compositions, compositions.size(), acquisitionsMetricsCount);
	}

	public List<Metric> getBrowserMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		Map<String, BigDecimal> sessionsCountGroupedByBrowserName =
			_bqSessionRepository.getSessionsCountGroupedByBrowserName(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Set<Map.Entry<String, BigDecimal>> set =
			sessionsCountGroupedByBrowserName.entrySet();

		Stream<Map.Entry<String, BigDecimal>> stream = set.stream();

		return stream.map(
			entry -> {
				Metric metric = new Metric(metricType);

				BigDecimal value = entry.getValue();

				metric.setValue(value.doubleValue());

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

		Map<String, BigDecimal> sessionsCountGroupedByGeolocation =
			_bqSessionRepository.getSessionsCountGroupedByGeolocation(
				Long.valueOf(searchQueryContext.getChannelId()),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		Set<Map.Entry<String, BigDecimal>> set =
			sessionsCountGroupedByGeolocation.entrySet();

		Stream<Map.Entry<String, BigDecimal>> stream = set.stream();

		return stream.map(
			entry -> {
				Metric metric = new Metric(metricType);

				BigDecimal value = entry.getValue();

				metric.setValue(value.doubleValue());

				metric.setValueKey(entry.getKey());

				return metric;
			}
		).collect(
			Collectors.toList()
		);
	}

	public List<HeatMapMetric> getHeatMapMetrics(
		Long channelId, TimeRange timeRange) {

		List<Map<String, BigDecimal>> visitorsCountGroupedByDayAndTime =
			_bqSessionRepository.getVisitorsCountGroupedByDayAndTime(
				channelId, timeRange, _timeZoneDog.getZoneId());

		Map<Pair<Integer, Integer>, HeatMapMetric> heatMapMetrics =
			new TreeMap<>();

		for (int day = 0; day < 7; day++) {
			for (int hour = 0; hour < 24; hour++) {
				Metric metric = new Metric(SiteMetricType.VISITORS);

				metric.setValue(0D);

				heatMapMetrics.put(
					Pair.of(day, hour),
					new HeatMapMetric(
						String.valueOf(day), metric, String.valueOf(hour)));
			}
		}

		if (visitorsCountGroupedByDayAndTime.isEmpty()) {
			return new ArrayList<>(heatMapMetrics.values());
		}

		for (Map<String, BigDecimal> visitorCount :
				visitorsCountGroupedByDayAndTime) {

			BigDecimal dayOfWeek = visitorCount.get("dayOfWeek");
			BigDecimal hourOfDay = visitorCount.get("hourOfDay");
			BigDecimal visitors = visitorCount.get("visitors");

			Metric metric = new Metric(SiteMetricType.VISITORS);

			metric.setValue(Double.valueOf(String.valueOf(visitors)));

			heatMapMetrics.put(
				Pair.of(dayOfWeek.intValue() - 1, hourOfDay.intValue()),
				new HeatMapMetric(
					String.valueOf(dayOfWeek.intValue() - 1), metric,
					String.valueOf(hourOfDay.intValue())));
		}

		return new ArrayList<>(heatMapMetrics.values());
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

	public CohortMetric getVisitorCohortHeatMapMetrics(
		SearchQueryContext searchQueryContext) {

		Interval interval = searchQueryContext.getInterval();

		List<String> cohortIntervals =
			_metricHelper.getVisitorCohortMetricsIntervals(
				Clock.system(_timeZoneDog.getZoneId()), interval);

		LocalDate startDate = LocalDate.parse(cohortIntervals.get(0));

		List<Map<String, Object>> visitorCohortMetrics =
			_bqSessionRepository.getVisitorCohortMetrics(
				Long.valueOf(searchQueryContext.getChannelId()), interval,
				TimeRange.of(LocalDateTime.now(), startDate.atStartOfDay()),
				_timeZoneDog.getZoneId());

		Map<String, Map<String, Object>> visitorCohortMetricsMap =
			new HashMap<>();

		for (Map<String, Object> visitorCohortMetric : visitorCohortMetrics) {
			visitorCohortMetricsMap.put(
				(String)visitorCohortMetric.get("cohortDate"),
				visitorCohortMetric);
		}

		CohortMetric cohortMetric = new CohortMetric();

		List<CohortHeatMapMetric> anonymousVisitorsMetrics =
			_getVisitorsCohortMetrics(
				cohortIntervals, SiteMetricType.ANONYMOUS_VISITORS,
				visitorCohortMetricsMap);

		cohortMetric.setAnonymousVisitorsMetric(anonymousVisitorsMetrics);

		List<CohortHeatMapMetric> knownVisitorsMetrics =
			_getVisitorsCohortMetrics(
				cohortIntervals, SiteMetricType.KNOWN_VISITORS,
				visitorCohortMetricsMap);

		cohortMetric.setKnownVisitorsMetric(knownVisitorsMetrics);

		cohortMetric.setVisitorsMetric(
			_getVisitorsCohortMetrics(
				anonymousVisitorsMetrics, knownVisitorsMetrics));

		return cohortMetric;
	}

	private List<CohortHeatMapMetric> _getVisitorsCohortMetrics(
		List<CohortHeatMapMetric> anonymousVisitorsMetrics,
		List<CohortHeatMapMetric> knownVisitorsMetrics) {

		List<CohortHeatMapMetric> visitorsMetrics = new ArrayList<>();

		Map<String, Double> firstIntervalValues = new Hashtable<>();

		for (int i = 0; i < anonymousVisitorsMetrics.size(); i++) {
			Metric metric = new Metric(SiteMetricType.VISITORS);

			CohortHeatMapMetric anonymousMetric = anonymousVisitorsMetrics.get(
				i);

			metric.setValueKey(anonymousMetric.getValueKey());

			CohortHeatMapMetric knownMetric = knownVisitorsMetrics.get(i);

			double metricValue =
				anonymousMetric.getValue() + knownMetric.getValue();

			metric.setValue(metricValue);

			String colDimension = anonymousMetric.getColDimension();

			if (colDimension.equals("0")) {
				firstIntervalValues.put(
					anonymousMetric.getRowDimension(), metricValue);
			}

			Double firstIntervalValue = firstIntervalValues.get(
				anonymousMetric.getRowDimension());

			double retention = 0;

			if (firstIntervalValue != 0) {
				retention = (metricValue / firstIntervalValue) * 100;
			}

			visitorsMetrics.add(
				new CohortHeatMapMetric(
					colDimension, metric, retention,
					anonymousMetric.getRowDimension(),
					anonymousMetric.getRowKey()));
		}

		return visitorsMetrics;
	}

	@NotNull
	private List<CohortHeatMapMetric> _getVisitorsCohortMetrics(
		List<String> cohortsIntervals, SiteMetricType siteMetricType,
		Map<String, Map<String, Object>> visitorCohortMetricsMap) {

		String columnVisitorType = "Known";

		if (siteMetricType.equals(SiteMetricType.ANONYMOUS_VISITORS)) {
			columnVisitorType = "Unknown";
		}

		List<CohortHeatMapMetric> visitorsMetrics = new ArrayList<>();

		double totalVisitors = 0D;

		for (int i = 0; i < cohortsIntervals.size(); i++) {
			double columnTotalVisitors = 0D;
			int firstIntervalIndex = 0;

			for (int j = 0; j <= (cohortsIntervals.size() - 1 - i); j++) {
				if (j == 0) {
					firstIntervalIndex = visitorsMetrics.size();
				}

				if ((j == (cohortsIntervals.size() - 1)) && (i == 0)) {
					break;
				}

				String cohortDate = cohortsIntervals.get(j);

				Map<String, Object> cohort = visitorCohortMetricsMap.get(
					cohortDate);

				Metric metric = new Metric(siteMetricType);

				double retention = 0D;
				double visitors = 0D;

				if (cohort != null) {
					BigDecimal intervalVisitors = (BigDecimal)cohort.get(
						String.format(
							"interval%dTotal%s", i, columnVisitorType));

					visitors = intervalVisitors.doubleValue();

					metric.setValue(visitors);

					metric.setValueKey(cohortDate);

					BigDecimal intervalRetention = (BigDecimal)cohort.get(
						String.format(
							"interval%dRetention%s", i, columnVisitorType));

					retention = intervalRetention.doubleValue() * 100;
				}

				visitorsMetrics.add(
					new CohortHeatMapMetric(
						String.valueOf(i), metric, retention,
						String.valueOf(j + 1), cohortDate));

				if (i == 0) {
					totalVisitors += visitors;
				}

				columnTotalVisitors += visitors;
			}

			Metric metric = new Metric(siteMetricType);

			metric.setValue(columnTotalVisitors);

			double retention = 0;

			if (totalVisitors != 0) {
				retention = (columnTotalVisitors / totalVisitors) * 100;
			}

			visitorsMetrics.add(
				firstIntervalIndex,
				new CohortHeatMapMetric(
					String.valueOf(i), metric, retention, "0", null));
		}

		return visitorsMetrics;
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
	private MetricHelper _metricHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}