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
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class TechnologyDog {

	@Autowired
	public TechnologyDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public List<Metric> getBrowserMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildBrowserSearchSourceBuilder(
				dogConfiguration, metricType, searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Range.Bucket rangeBucket = _getRangeAggregationBucket(aggregations);

		if (rangeBucket == null) {
			return Collections.emptyList();
		}

		return _getMetrics(
			rangeBucket.getAggregations(), dogConfiguration, metricType);
	}

	public List<Metric> getDeviceMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildDeviceSearchSourceBuilder(
				dogConfiguration, metricType, searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Range.Bucket rangeBucket = _getRangeAggregationBucket(aggregations);

		if (rangeBucket == null) {
			return Collections.emptyList();
		}

		return _getMetrics(
			rangeBucket.getAggregations(), dogConfiguration, metricType);
	}

	private SearchSourceBuilder _buildBrowserSearchSourceBuilder(
		DogConfiguration dogConfiguration, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		AggregationBuilder aggregationBuilder = _createTermsAggregationBuilder(
			"browserName", dogConfiguration.getMetricResolver(metricType));

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			aggregationBuilder,
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(), metricType, searchQueryContext,
			_timeZoneDog.getTimeZoneId());
	}

	private SearchSourceBuilder _buildDeviceSearchSourceBuilder(
		DogConfiguration dogConfiguration, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		AggregationBuilder deviceTypeAggregationBuilder =
			_createTermsAggregationBuilder(
				"deviceType", dogConfiguration.getMetricResolver(metricType),
				_createTermsAggregationBuilder(
					"platformName",
					dogConfiguration.getMetricResolver(metricType)));

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			deviceTypeAggregationBuilder,
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(), metricType, searchQueryContext,
			_timeZoneDog.getTimeZoneId());
	}

	private AggregationBuilder _createTermsAggregationBuilder(
		String fieldName, MetricResolver metricResolver,
		AggregationBuilder... aggregationBuilders) {

		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("terms");

		termsAggregationBuilder.field(fieldName);
		termsAggregationBuilder.size(1024);

		for (AggregationBuilder aggregationBuilder : aggregationBuilders) {
			termsAggregationBuilder.subAggregation(aggregationBuilder);
		}

		DogUtil.addTermsAggregationBuilderOrder(
			metricResolver, termsAggregationBuilder);
		DogUtil.addTermsAggregationBuilderSubaggregations(
			metricResolver, termsAggregationBuilder);

		return termsAggregationBuilder;
	}

	private Metric _getMetric(
		DogConfiguration dogConfiguration, MetricType metricType,
		Terms.Bucket termsBucket) {

		Metric metric = new Metric(metricType);

		Aggregations bucketAggregations = termsBucket.getAggregations();

		metric.setMetrics(
			_getMetrics(bucketAggregations, dogConfiguration, metricType));

		MetricResolver metricResolver = dogConfiguration.getMetricResolver(
			metricType);

		Function<Aggregations, Double> mapperFunction =
			metricResolver.getMapperFunction();

		metric.setValue(mapperFunction.apply(bucketAggregations));

		metric.setValueKey(termsBucket.getKeyAsString());

		return metric;
	}

	private List<Metric> _getMetrics(
		Aggregations bucketAggregations, DogConfiguration dogConfiguration,
		MetricType metricType) {

		Terms terms = bucketAggregations.get("terms");

		if (terms == null) {
			return Collections.emptyList();
		}

		return ListUtil.map(
			terms.getBuckets(),
			termsBucket -> _getMetric(
				dogConfiguration, metricType, termsBucket));
	}

	private Range.Bucket _getRangeAggregationBucket(Aggregations aggregations) {
		Range range = aggregations.get("ranges");

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return null;
		}

		return rangeBuckets.get(0);
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}