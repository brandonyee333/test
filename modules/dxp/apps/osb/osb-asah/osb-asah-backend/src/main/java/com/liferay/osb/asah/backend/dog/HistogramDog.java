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
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Interval;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.petra.string.StringPool;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class HistogramDog {

	@Autowired
	public HistogramDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public List<HistogramMetric> getHistogramMetrics(
		boolean includePrevious, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				dogConfiguration, includePrevious, metricType,
				searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		String aggregationKey = "ranges";

		if (includePrevious) {
			aggregationKey = "period_ranges";
		}

		return _createHistogramMetrics(
			searchQueryContext.getAssetType(), includePrevious,
			searchQueryContext.getInterval(), aggregations.get(aggregationKey),
			metricType, searchQueryContext.getTimeRange());
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		DogConfiguration dogConfiguration, boolean includePrevious,
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
			_getDateHistogramAggregationBuilder(
				searchQueryContext.getInterval(),
				searchQueryContext.getTimeRange());

		MetricResolver metricResolver = dogConfiguration.getMetricResolver(
			metricType);

		Set<AggregationBuilder> aggregationBuilders =
			metricResolver.getAggregationBuilders();

		aggregationBuilders.forEach(
			dateHistogramAggregationBuilder::subAggregation);

		Set<PipelineAggregationBuilder> pipelineAggregationBuilders =
			metricResolver.getPipelineAggregationBuilders();

		pipelineAggregationBuilders.forEach(
			dateHistogramAggregationBuilder::subAggregation);

		if (includePrevious) {
			return _searchQueryHelper.createPeriodRangeSearchSourceBuilder(
				Collections.singleton(dateHistogramAggregationBuilder),
				DogUtil.getAssetIdOptional(
					searchQueryContext.getAssetId(), dogConfiguration),
				Collections.emptySet(),
				dogConfiguration.getQueryBuilder(searchQueryContext),
				searchQueryContext);
		}

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			Collections.singleton(dateHistogramAggregationBuilder),
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(),
			dogConfiguration.getQueryBuilder(searchQueryContext),
			searchQueryContext);
	}

	private List<HistogramMetric> _createHistogramMetrics(
		AssetType assetType, boolean includePrevious, Interval interval,
		Range range, MetricType metricType, TimeRange timeRange) {

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if ((includePrevious && (rangeBuckets.size() < 2)) ||
			rangeBuckets.isEmpty()) {

			return Collections.emptyList();
		}

		Map<String, Metric> metrics = _metricHelper.createMetrics(
			Clock.systemUTC(), interval, timeRange, metricType);

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(assetType);

		MetricResolver metricResolver = dogConfiguration.getMetricResolver(
			metricType);

		Function<Aggregations, Double> mapperFunction =
			metricResolver.getMapperFunction();

		int index = 0;

		if (includePrevious) {
			Range.Bucket previousRangeBucket = rangeBuckets.get(0);

			Aggregations previousBucketAggregations =
				previousRangeBucket.getAggregations();

			Histogram previousHistogram = previousBucketAggregations.get(
				"metric_over_time");

			for (Histogram.Bucket histogramBucket :
					previousHistogram.getBuckets()) {

				Map.Entry<String, Metric> entry =
					_getEntryFromPreviousTimestamp(
						interval, metrics, histogramBucket.getKeyAsString());

				if (entry == null) {
					continue;
				}

				Metric metric = entry.getValue();

				metric.setPreviousValue(
					mapperFunction.apply(histogramBucket.getAggregations()));

				metrics.put(entry.getKey(), metric);
			}

			index = 1;
		}

		Range.Bucket currentRangeBucket = rangeBuckets.get(index);

		Aggregations currentBucketAggregations =
			currentRangeBucket.getAggregations();

		Histogram currentHistogram = currentBucketAggregations.get(
			"metric_over_time");

		for (Histogram.Bucket histogramBucket : currentHistogram.getBuckets()) {
			Instant instant = Instant.parse(histogramBucket.getKeyAsString());

			LocalDateTime localDateTime = LocalDateTime.ofInstant(
				instant, ZoneOffset.UTC);

			String timeKey = localDateTime.toString();

			Metric metric = metrics.get(timeKey);

			if (metric == null) {
				continue;
			}

			metric.setValue(
				mapperFunction.apply(histogramBucket.getAggregations()));

			metrics.put(timeKey, metric);
		}

		Set<Map.Entry<String, Metric>> entries = metrics.entrySet();

		Stream<Map.Entry<String, Metric>> stream = entries.stream();

		return stream.map(
			entry -> new HistogramMetric(entry.getKey(), entry.getValue())
		).collect(
			Collectors.toList()
		);
	}

	private DateHistogramAggregationBuilder _getDateHistogramAggregationBuilder(
		Interval interval, TimeRange timeRange) {

		DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
			AggregationBuilders.dateHistogram("metric_over_time");

		dateHistogramAggregationBuilder.field("eventDate");

		if (timeRange.equals(TimeRange.LAST_24_HOURS) ||
			timeRange.equals(TimeRange.YESTERDAY)) {

			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.HOUR);

			return dateHistogramAggregationBuilder;
		}

		if (Interval.MONTH.equals(interval)) {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.MONTH);
		}
		else if (Interval.WEEK.equals(interval)) {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.WEEK);
			dateHistogramAggregationBuilder.offset("-1d");
		}
		else {
			dateHistogramAggregationBuilder.dateHistogramInterval(
				DateHistogramInterval.DAY);
		}

		return dateHistogramAggregationBuilder;
	}

	private Map.Entry<String, Metric> _getEntryFromPreviousTimestamp(
		Interval interval, Map<String, Metric> metrics, String timestamp) {

		String previousValueKey = _getPreviousValueKey(interval, timestamp);

		for (Map.Entry<String, Metric> entry : metrics.entrySet()) {
			Metric metric = entry.getValue();

			if (previousValueKey.equals(metric.getPreviousValueKey())) {
				return entry;
			}
		}

		return null;
	}

	private String _getPreviousValueKey(Interval interval, String timestamp) {
		Instant instant = Instant.parse(timestamp);

		LocalDateTime localDateTime = LocalDateTime.ofInstant(
			instant, ZoneOffset.UTC);

		LocalDate startLocalDate = localDateTime.toLocalDate();

		if (Interval.WEEK.equals(interval)) {
			LocalDate endLocalDate = startLocalDate.plusDays(6);

			return startLocalDate + StringPool.SLASH + endLocalDate;
		}
		else if (Interval.MONTH.equals(interval)) {
			LocalDate endLocalDate = startLocalDate.withDayOfMonth(
				startLocalDate.lengthOfMonth());

			return startLocalDate + StringPool.SLASH + endLocalDate;
		}

		return localDateTime.toString();
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private MetricHelper _metricHelper;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}