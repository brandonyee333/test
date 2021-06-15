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
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.CustomAssetMetricRepository;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;

import java.math.BigDecimal;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class CustomAssetMetricDog {

	public HistogramMetricBag getHistogramMetricBag(
		CustomAssetMetricType customAssetMetricType,
		SearchQueryContext searchQueryContext) {

		List<Tuple2<LocalDateTime, BigDecimal>> histogramMetrics =
			_customAssetMetricRepository.getHistogramMetrics(
				Long.valueOf(searchQueryContext.getChannelId()),
				customAssetMetricType, searchQueryContext.getAssetId(),
				searchQueryContext.getInterval(),
				_getTimeRange(searchQueryContext));

		if (histogramMetrics.isEmpty()) {
			return new HistogramMetricBag();
		}

		HistogramMetricBag histogramMetricBag =
			_metricHelper.createHistogramMetricBag(
				Clock.system(_timeZoneDog.getZoneId()),
				searchQueryContext.isIncludePrevious(),
				searchQueryContext.getInterval(), customAssetMetricType,
				searchQueryContext.getTimeRange());

		Map<String, Metric> metrics = _getHistogramMetricBuckets(
			histogramMetricBag);

		for (Tuple2<LocalDateTime, BigDecimal> histogramMetric :
				histogramMetrics) {

			Metric metric = metrics.get(histogramMetric.getT1());

			BigDecimal histogramMetricValueBigDecimal = histogramMetric.getT2();

			if (metric != null) {
				metric.setValue(histogramMetricValueBigDecimal.doubleValue());

				continue;
			}

			LocalDateTime previousBucketLocalDateTime = histogramMetric.getT1();

			String bucketKey = null;

			if (searchQueryContext.getTimeRange() == TimeRange.LAST_24_HOURS) {
				bucketKey = String.valueOf(
					previousBucketLocalDateTime.plusHours(24));
			}
			else {
				TimeRange timeRange = searchQueryContext.getTimeRange();

				bucketKey = String.valueOf(
					previousBucketLocalDateTime.plusDays(
						timeRange.getDeltaDays()));
			}

			metric = metrics.get(bucketKey);

			if (metric != null) {
				metric.setPreviousValue(
					histogramMetricValueBigDecimal.doubleValue());
			}
		}

		return histogramMetricBag;
	}

	private Map<String, Metric> _getHistogramMetricBuckets(
		HistogramMetricBag histogramMetricBag) {

		List<HistogramMetric> histogramMetrics =
			histogramMetricBag.getMetrics();

		Stream<HistogramMetric> histogramMetricStream =
			histogramMetrics.stream();

		return histogramMetricStream.collect(
			Collectors.toMap(HistogramMetric::getKey, Function.identity()));
	}

	private TimeRange _getTimeRange(SearchQueryContext searchQueryContext) {
		TimeRange timeRange = searchQueryContext.getTimeRange();

		if (!searchQueryContext.isIncludePrevious()) {
			return timeRange;
		}

		if (timeRange == TimeRange.LAST_24_HOURS) {
			LocalDateTime endLocalDateTime = timeRange.getEndLocalDateTime();

			LocalDateTime startLocalDateTime = timeRange.getEndLocalDateTime();

			startLocalDateTime = startLocalDateTime.withMinute(0);
			startLocalDateTime = startLocalDateTime.withSecond(0);
			startLocalDateTime = startLocalDateTime.withNano(0);

			startLocalDateTime = startLocalDateTime.minusHours(47);

			return TimeRange.of(endLocalDateTime, startLocalDateTime);
		}

		if (TimeRange.YESTERDAY.equals(timeRange) ||
			TimeRange.LAST_7_DAYS.equals(timeRange) ||
			TimeRange.LAST_28_DAYS.equals(timeRange) ||
			TimeRange.LAST_30_DAYS.equals(timeRange) ||
			TimeRange.LAST_90_DAYS.equals(timeRange) ||
			TimeRange.LAST_180_DAYS.equals(timeRange) ||
			TimeRange.LAST_YEAR.equals(timeRange)) {

			LocalDateTime endLocalDateTime = timeRange.getEndLocalDateTime();

			LocalDateTime startLocalDateTime = endLocalDateTime.with(
				LocalTime.MIN);

			startLocalDateTime = startLocalDateTime.minusDays(
				(timeRange.getDeltaDays() * 2) - 1);

			return TimeRange.of(endLocalDateTime, startLocalDateTime);
		}

		LocalDate endLocalDate = timeRange.getEndLocalDate();
		LocalDate startLocalDate = timeRange.getStartLocalDate();

		endLocalDate = endLocalDate.plusDays(1);

		startLocalDate = startLocalDate.minusDays(timeRange.getDeltaDays() * 2);

		return TimeRange.of(endLocalDate, startLocalDate);
	}

	@Autowired
	private CustomAssetMetricRepository _customAssetMetricRepository;

	@Autowired
	private MetricHelper _metricHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}