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
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class SegmentMetricDog {

	@Autowired
	public SegmentMetricDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public ResultBag<Metric> getSegmentMetricResultBag(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, metricType, Optional.empty(),
				searchQueryContext, _createSegmentsTermsAggregationBuilder(),
				_createCardinalityAggregationBuilder("total", "segmentNames")));

		if (DogUtil.isEmpty(aggregations)) {
			return new ResultBag<>(Collections.emptyList(), 0);
		}

		List<Metric> metrics = _getMetrics(
			metricType, aggregations.get("segments"));

		if (metrics.size() <= 15) {
			return new ResultBag<>(metrics, metrics.size());
		}

		Metric othersSegmentMetric = _getOthersSegmentMetric(
			metricType, searchQueryContext, metrics);

		long total = DogUtil.getCardinalityAsLong(aggregations.get("total"));

		return new ResultBag<>(
			new ArrayList<Metric>() {
				{
					addAll(metrics.subList(0, 14));
					add(othersSegmentMetric);
				}
			},
			total);
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		DogConfiguration dogConfiguration, MetricType metricType,
		Optional<QueryBuilder> queryBuilderOptional,
		SearchQueryContext searchQueryContext,
		AggregationBuilder... aggregationBuilders) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		for (AggregationBuilder aggregationBuilder : aggregationBuilders) {
			searchSourceBuilder.aggregation(aggregationBuilder);
		}

		BoolQueryBuilder boolQueryBuilder =
			_searchQueryHelper.createFilterBoolQueryBuilder(
				DogUtil.getAssetIdOptional(
					searchQueryContext.getAssetId(), dogConfiguration),
				metricType, searchQueryContext);

		queryBuilderOptional.ifPresent(boolQueryBuilder::filter);

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private AggregationBuilder _createCardinalityAggregationBuilder(
		String name, String fieldName) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality(name);

		cardinalityAggregationBuilder.field(fieldName);
		cardinalityAggregationBuilder.precisionThreshold(1000);

		return cardinalityAggregationBuilder;
	}

	private Metric _createMetric(
		Terms.Bucket termsBucket, MetricType metricType) {

		Aggregations aggregations = termsBucket.getAggregations();

		return Metric.of(
			metricType, DogUtil.getSingleValue(aggregations.get("users")),
			termsBucket.getKeyAsString());
	}

	private AggregationBuilder _createSegmentsTermsAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("segments");

		termsAggregationBuilder.field("segmentNames");
		termsAggregationBuilder.size(16);
		termsAggregationBuilder.subAggregation(
			_createCardinalityAggregationBuilder("users", "individualId"));
		termsAggregationBuilder.subAggregation(
			PipelineAggregatorBuilders.bucketSort(
				"usersSort",
				Arrays.asList(
					SortBuilderUtil.fieldSort("users", SortOrder.DESC))));

		return termsAggregationBuilder;
	}

	private List<Metric> _getMetrics(MetricType metricType, Terms terms) {
		return ListUtil.map(
			terms.getBuckets(), bucket -> _createMetric(bucket, metricType));
	}

	private Metric _getOthersSegmentMetric(
		MetricType metricType, SearchQueryContext searchQueryContext,
		List<Metric> topSegmentMetrics) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("segmentNames")
		).filter(
			QueryBuilders.scriptQuery(
				new Script(
					Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
					_segmentScriptSource,
					new HashMap() {
						{
							put(
								"segmentNames",
								_getSegmentNames(14, topSegmentMetrics));
						}
					}))
		);

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollectionName(),
			_buildSearchSourceBuilder(
				dogConfiguration, metricType, Optional.of(boolQueryBuilder),
				searchQueryContext,
				_createCardinalityAggregationBuilder("users", "individualId")));

		Metric metric = Metric.of(metricType, 0.0, "others");

		if (DogUtil.isEmpty(aggregations)) {
			return metric;
		}

		metric.setValue(DogUtil.getSingleValue(aggregations.get("users")));

		return metric;
	}

	private List<String> _getSegmentNames(int limit, List<Metric> metrics) {
		Stream<Metric> stream = metrics.stream();

		return stream.limit(
			limit
		).map(
			Metric::getValueKey
		).collect(
			Collectors.toList()
		);
	}

	@PostConstruct
	private void _init() {
		_segmentScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "segment_script.painless");
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	private String _segmentScriptSource;

}