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

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.MetricHelper;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.metrics.ScriptedMetric;
import org.elasticsearch.search.aggregations.metrics.ScriptedMetricAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class VisitorHistogramDog {

	@Autowired
	public VisitorHistogramDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public HistogramMetricBag getHistogramMetricBag(
		boolean includePrevious, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, includePrevious, searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return new HistogramMetricBag();
		}

		String aggregationKey = "ranges";

		if (includePrevious) {
			aggregationKey = "period_ranges";
		}

		return _createHistogramMetricBag(
			includePrevious, searchQueryContext.getInterval(), metricType,
			aggregations.get(aggregationKey),
			searchQueryContext.getTimeRange());
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		DogConfiguration dogConfiguration, boolean includePrevious,
		SearchQueryContext searchQueryContext) {

		ScriptedMetricAggregationBuilder scriptedMetricAggregationBuilder =
			AggregationBuilders.scriptedMetric("visitors");

		scriptedMetricAggregationBuilder.initScript(
			_visitorHistogramInitScript);
		scriptedMetricAggregationBuilder.mapScript(_visitorHistogramMapScript);
		scriptedMetricAggregationBuilder.combineScript(
			_visitorHistogramCombineScript);
		scriptedMetricAggregationBuilder.reduceScript(
			_visitorHistogramReduceScript);

		if (includePrevious) {
			return _searchQueryHelper.createPeriodRangeSearchSourceBuilder(
				Collections.singleton(scriptedMetricAggregationBuilder),
				DogUtil.getAssetIdOptional(
					searchQueryContext.getAssetId(), dogConfiguration),
				Collections.emptySet(),
				dogConfiguration.getQueryBuilder(searchQueryContext),
				searchQueryContext, _timeZoneDog.getTimeZoneId());
		}

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			Collections.singleton(scriptedMetricAggregationBuilder),
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(),
			dogConfiguration.getQueryBuilder(searchQueryContext),
			searchQueryContext, _timeZoneDog.getTimeZoneId());
	}

	private HistogramMetricBag _createHistogramMetricBag(
		boolean includePrevious, Interval interval, MetricType metricType,
		Range range, TimeRange timeRange) {

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if ((includePrevious && (rangeBuckets.size() < 2)) ||
			rangeBuckets.isEmpty()) {

			return new HistogramMetricBag();
		}

		HistogramMetricBag histogramMetricBag =
			_metricHelper.createHistogramMetricBag(
				Clock.system(_timeZoneDog.getZoneId()), includePrevious,
				interval, metricType, timeRange);

		Map<String, Metric> metrics = _getMetrics(histogramMetricBag);

		List<LocalDateTime> histogramBuckets = _getHistogramBuckets(
			metrics.keySet());

		int index = 0;

		if (includePrevious) {
			Range.Bucket previousRangeBucket = rangeBuckets.get(0);

			Aggregations previousBucketAggregations =
				previousRangeBucket.getAggregations();

			ScriptedMetric previousHistogram = previousBucketAggregations.get(
				"visitors");

			Map<String, Integer> previousValues =
				(Map<String, Integer>)previousHistogram.aggregation();

			for (Map.Entry<String, Integer> entry : previousValues.entrySet()) {
				String timeKey = _getTimeKey(
					false, histogramBuckets, timeRange, entry.getKey());

				Metric metric = metrics.get(timeKey);

				if (metric == null) {
					continue;
				}

				metric.addPreviousValue((double)entry.getValue());

				metrics.put(timeKey, metric);
			}

			index = 1;
		}

		Range.Bucket currentRangeBucket = rangeBuckets.get(index);

		Aggregations currentBucketAggregations =
			currentRangeBucket.getAggregations();

		ScriptedMetric currentHistogram = currentBucketAggregations.get(
			"visitors");

		Map<String, Integer> currentValues =
			(Map<String, Integer>)currentHistogram.aggregation();

		for (Map.Entry<String, Integer> entry : currentValues.entrySet()) {
			String timeKey = _getTimeKey(
				true, histogramBuckets, timeRange, entry.getKey());

			Metric metric = metrics.get(timeKey);

			if (metric == null) {
				continue;
			}

			metric.addValue((double)entry.getValue());

			metrics.put(timeKey, metric);
		}

		return histogramMetricBag;
	}

	private LocalDateTime _getCurrentLocalDateTime(
		List<LocalDateTime> histogramBuckets, LocalDateTime localDateTime,
		TimeRange timeRange) {

		if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			for (LocalDateTime histogramBucket : histogramBuckets) {
				if (histogramBucket.compareTo(localDateTime) <= 0) {
					return histogramBucket;
				}
			}
		}

		return localDateTime;
	}

	private List<LocalDateTime> _getHistogramBuckets(Set<String> metricsKeys) {
		Stream<String> metricsKeysStream = metricsKeys.stream();

		return metricsKeysStream.map(
			LocalDateTime::parse
		).sorted(
			Collections.reverseOrder()
		).collect(
			Collectors.toList()
		);
	}

	private Map<String, Metric> _getMetrics(
		HistogramMetricBag histogramMetricBag) {

		List<HistogramMetric> histogramMetrics =
			histogramMetricBag.getMetrics();

		Stream<HistogramMetric> histogramMetricsStream =
			histogramMetrics.stream();

		return histogramMetricsStream.collect(
			Collectors.toMap(HistogramMetric::getKey, Function.identity()));
	}

	private LocalDateTime _getPreviousLocalDateTime(
		LocalDateTime localDateTime, TimeRange timeRange) {

		if (TimeRange.LAST_24_HOURS.equals(timeRange) ||
			TimeRange.YESTERDAY.equals(timeRange)) {

			return localDateTime.plusDays(1);
		}

		if (TimeRange.LAST_7_DAYS.equals(timeRange)) {
			return localDateTime.plusDays(7);
		}

		if (TimeRange.LAST_28_DAYS.equals(timeRange)) {
			return localDateTime.plusDays(28);
		}

		if (TimeRange.LAST_30_DAYS.equals(timeRange)) {
			return localDateTime.plusDays(30);
		}

		if (TimeRange.LAST_90_DAYS.equals(timeRange)) {
			if (localDateTime.getDayOfWeek() != DayOfWeek.SUNDAY) {
				localDateTime = localDateTime.minusWeeks(1);
			}

			localDateTime = localDateTime.with(DayOfWeek.SUNDAY);

			return localDateTime.plusWeeks(13);
		}

		if (TimeRange.LAST_180_DAYS.equals(timeRange)) {
			return localDateTime.plusWeeks(180);
		}

		if (TimeRange.LAST_YEAR.equals(timeRange)) {
			return localDateTime.plusYears(1);
		}

		return localDateTime.plusDays(timeRange.getDeltaDays());
	}

	private String _getTimeKey(
		boolean current, List<LocalDateTime> histogramBuckets,
		TimeRange timeRange, String timestamp) {

		Instant instant = Instant.ofEpochMilli(Long.parseLong(timestamp));

		LocalDateTime localDateTime = LocalDateTime.ofInstant(
			instant, _timeZoneDog.getZoneId());

		localDateTime = localDateTime.withMinute(0);
		localDateTime = localDateTime.withNano(0);
		localDateTime = localDateTime.withSecond(0);

		if (!TimeRange.LAST_24_HOURS.equals(timeRange) &&
			!TimeRange.YESTERDAY.equals(timeRange)) {

			localDateTime = localDateTime.withHour(0);
		}

		if (current) {
			localDateTime = _getCurrentLocalDateTime(
				histogramBuckets, localDateTime, timeRange);
		}
		else {
			localDateTime = _getPreviousLocalDateTime(localDateTime, timeRange);
		}

		return localDateTime.toString();
	}

	@PostConstruct
	private void _init() {
		_visitorHistogramCombineScript = ScriptUtil.createScript(
			getClass(), "visitor_histogram_combine_script.painless");
		_visitorHistogramInitScript = ScriptUtil.createScript(
			getClass(), "visitor_histogram_init_script.painless");
		_visitorHistogramMapScript = ScriptUtil.createScript(
			getClass(), "visitor_histogram_map_script.painless");
		_visitorHistogramReduceScript = ScriptUtil.createScript(
			getClass(), "visitor_histogram_reduce_script.painless");
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private MetricHelper _metricHelper;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

	private Script _visitorHistogramCombineScript;
	private Script _visitorHistogramInitScript;
	private Script _visitorHistogramMapScript;
	private Script _visitorHistogramReduceScript;

}