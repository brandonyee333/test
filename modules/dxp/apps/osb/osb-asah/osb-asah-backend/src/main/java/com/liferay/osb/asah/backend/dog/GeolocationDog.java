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

import com.liferay.osb.asah.backend.constants.DataConstants;
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
public class GeolocationDog {

	@Autowired
	public GeolocationDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public List<Metric> getGeolocationMetrics(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, metricType, searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Range range = aggregations.get("ranges");

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return Collections.emptyList();
		}

		Range.Bucket rangeBucket = rangeBuckets.get(0);

		return _getMetrics(
			rangeBucket.getAggregations(), dogConfiguration, metricType);
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		DogConfiguration dogConfiguration, MetricType metricType,
		SearchQueryContext searchQueryContext) {

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			_createCountryAggregationBuilder(
				dogConfiguration.getMetricResolver(metricType)),
			DogUtil.getAssetIdOptional(
				searchQueryContext.getAssetId(), dogConfiguration),
			Collections.emptySet(), metricType, searchQueryContext,
			_timeZoneDog.getTimeZoneId());
	}

	private AggregationBuilder _createCountryAggregationBuilder(
		MetricResolver metricResolver) {

		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("terms");

		termsAggregationBuilder.field("country");
		termsAggregationBuilder.missing(DataConstants.UNKNOWN);
		termsAggregationBuilder.size(1024);

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

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}