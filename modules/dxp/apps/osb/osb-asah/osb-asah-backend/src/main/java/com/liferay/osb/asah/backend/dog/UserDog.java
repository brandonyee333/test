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
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component("com.liferay.osb.asah.backend.dog.UserDog")
public class UserDog {

	@Autowired
	public UserDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public Long getAnonymousUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				metricType, QueryBuilders.termQuery("knownIndividual", false),
				searchQueryContext,
				_createCardinalityAggregationBuilder("userId")));

		return _getUsersCountAggregationValue(aggregations);
	}

	public Long getKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				metricType, _getKnownIndividualBoolQueryBuilder(),
				searchQueryContext,
				_createIndividualIdsTermsAggregationBuilder()));

		return _countIndividuals(aggregations);
	}

	public Long getNonsegmentedKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		BoolQueryBuilder boolQueryBuilder =
			_getKnownIndividualBoolQueryBuilder();

		boolQueryBuilder.mustNot(QueryBuilders.existsQuery("segmentNames"));

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				metricType, boolQueryBuilder, searchQueryContext,
				_createIndividualIdsTermsAggregationBuilder()));

		return _countIndividuals(aggregations);
	}

	public Long getSegmentedKnownUsersCount(
		MetricType metricType, SearchQueryContext searchQueryContext) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		BoolQueryBuilder boolQueryBuilder =
			_getKnownIndividualBoolQueryBuilder();

		boolQueryBuilder.filter(QueryBuilders.existsQuery("segmentNames"));

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(
				metricType, boolQueryBuilder, searchQueryContext,
				_createIndividualIdsTermsAggregationBuilder()));

		return _countIndividuals(aggregations);
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		MetricType metricType, QueryBuilder queryBuilder,
		SearchQueryContext searchQueryContext,
		AggregationBuilder... aggregationBuilders) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		Stream.of(
			aggregationBuilders
		).forEach(
			searchSourceBuilder::aggregation
		);

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		BoolQueryBuilder boolQueryBuilder =
			_searchQueryHelper.createFilterBoolQueryBuilder(
				DogUtil.getAssetIdOptional(
					searchQueryContext.getAssetId(), dogConfiguration),
				metricType, searchQueryContext);

		boolQueryBuilder.filter(queryBuilder);

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private long _countIndividuals(Aggregations aggregations) {
		List<String> individualIds = _getIndividualIds(
			aggregations.get("individuals"));

		if (individualIds.isEmpty()) {
			return 0;
		}

		return _faroInfoElasticsearchInvoker.count(
			"individuals",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("id", individualIds)
			).filter(
				QueryBuilders.existsQuery("demographics.email")
			));
	}

	private AggregationBuilder _createCardinalityAggregationBuilder(
		String fieldName) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality("users_count");

		cardinalityAggregationBuilder.field(fieldName);
		cardinalityAggregationBuilder.precisionThreshold(1000);

		return cardinalityAggregationBuilder;
	}

	private AggregationBuilder _createIndividualIdsTermsAggregationBuilder() {
		TermsAggregationBuilder individualIdsTermsAggregationBuilder =
			AggregationBuilders.terms("individuals");

		individualIdsTermsAggregationBuilder.field("individualId");
		individualIdsTermsAggregationBuilder.size(Integer.MAX_VALUE);

		return individualIdsTermsAggregationBuilder;
	}

	private List<String> _getIndividualIds(Terms terms) {
		List<? extends Terms.Bucket> buckets = terms.getBuckets();

		Stream<? extends Terms.Bucket> stream = buckets.stream();

		return stream.map(
			MultiBucketsAggregation.Bucket::getKeyAsString
		).collect(
			Collectors.toList()
		);
	}

	private BoolQueryBuilder _getKnownIndividualBoolQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("knownIndividual", true));
	}

	private long _getUsersCountAggregationValue(Aggregations aggregations) {
		if (DogUtil.isEmpty(aggregations)) {
			return 0L;
		}

		return DogUtil.getCardinalityAsLong(aggregations.get("users_count"));
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}