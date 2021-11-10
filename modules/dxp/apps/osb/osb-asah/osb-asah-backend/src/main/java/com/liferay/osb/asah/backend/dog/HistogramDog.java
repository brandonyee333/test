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
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.petra.string.StringPool;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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

	public HistogramMetricBag getHistogramMetricBag(
		boolean includePrevious, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, includePrevious, metricType,
				searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return new HistogramMetricBag();
		}

		String aggregationKey = "ranges";

		if (includePrevious) {
			aggregationKey = "period_ranges";
		}

		return _createHistogramMetricBag(
			searchQueryContext.getAssetType(), includePrevious,
			searchQueryContext.getInterval(), metricType,
			aggregations.get(aggregationKey),
			searchQueryContext.getTimeRange());
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
				searchQueryContext, _timeZoneDog.getTimeZoneId());
		}

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			Collections.singleton(dateHistogramAggregationBuilder),
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(),
			dogConfiguration.getQueryBuilder(searchQueryContext),
			searchQueryContext, _timeZoneDog.getTimeZoneId());
	}

	private HistogramMetricBag _createHistogramMetricBag(
		AssetType assetType, boolean includePrevious, Interval interval,
		MetricType metricType, Range range, TimeRange timeRange) {

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

				Metric metric = _getMetricFromPreviousTimestamp(
					interval, metrics, histogramBucket.getKeyAsString());

				if (metric == null) {
					continue;
				}

				metric.setPreviousValue(
					mapperFunction.apply(histogramBucket.getAggregations()));
			}

			index = 1;
		}

		Range.Bucket currentRangeBucket = rangeBuckets.get(index);

		Aggregations currentBucketAggregations =
			currentRangeBucket.getAggregations();

		Histogram currentHistogram = currentBucketAggregations.get(
			"metric_over_time");

		for (Histogram.Bucket histogramBucket : currentHistogram.getBuckets()) {
			ZonedDateTime zonedDateTime = ZonedDateTime.parse(
				histogramBucket.getKeyAsString());

			LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

			Metric metric = metrics.get(localDateTime.toString());

			if (metric == null) {
				continue;
			}

			metric.setValue(
				mapperFunction.apply(histogramBucket.getAggregations()));
		}

		return histogramMetricBag;
	}

	private DateHistogramAggregationBuilder _getDateHistogramAggregationBuilder(
		Interval interval, TimeRange timeRange) {

		DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
			AggregationBuilders.dateHistogram("metric_over_time");

		dateHistogramAggregationBuilder.field("eventDate");
		dateHistogramAggregationBuilder.format("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		dateHistogramAggregationBuilder.timeZone(_timeZoneDog.getZoneId());

		if (timeRange.equals(TimeRange.LAST_24_HOURS) ||
			timeRange.equals(TimeRange.YESTERDAY)) {

			dateHistogramAggregationBuilder.calendarInterval(
				DateHistogramInterval.HOUR);

			return dateHistogramAggregationBuilder;
		}

		if (Interval.MONTH.equals(interval)) {
			dateHistogramAggregationBuilder.calendarInterval(
				DateHistogramInterval.MONTH);
		}
		else if (Interval.WEEK.equals(interval)) {
			dateHistogramAggregationBuilder.calendarInterval(
				DateHistogramInterval.WEEK);
			dateHistogramAggregationBuilder.offset("-1d");
		}
		else {
			dateHistogramAggregationBuilder.calendarInterval(
				DateHistogramInterval.DAY);
		}

		return dateHistogramAggregationBuilder;
	}

	private Metric _getMetricFromPreviousTimestamp(
		Interval interval, Map<String, Metric> metrics, String timestamp) {

		String previousValueKey = _getPreviousValueKey(interval, timestamp);

		for (Metric metric : metrics.values()) {
			if (previousValueKey.equals(metric.getPreviousValueKey())) {
				return metric;
			}
		}

		return null;
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

	private String _getPreviousValueKey(Interval interval, String timestamp) {
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(timestamp);

		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

		if (Interval.WEEK.equals(interval)) {
			LocalDate startLocalDate = localDateTime.toLocalDate();

			LocalDate endLocalDate = startLocalDate.plusDays(6);

			return startLocalDate + StringPool.SLASH + endLocalDate;
		}

		if (Interval.MONTH.equals(interval)) {
			LocalDate startLocalDate = localDateTime.toLocalDate();

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

	@Autowired
	private TimeZoneDog _timeZoneDog;

}