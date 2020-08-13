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

import java.util.List;
import java.util.stream.Stream;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
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
				_createCardinalityAggregationBuilder("individualId")));

		return _getUsersCountAggregationValue(aggregations);
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
				_createCardinalityAggregationBuilder("individualId")));

		return _getUsersCountAggregationValue(aggregations);
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
				_createCardinalityAggregationBuilder("individualId")));

		return _getUsersCountAggregationValue(aggregations);
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

	private AggregationBuilder _createCardinalityAggregationBuilder(
		String fieldName) {

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality("users_count");

		cardinalityAggregationBuilder.field(fieldName);
		cardinalityAggregationBuilder.precisionThreshold(1000);

		return cardinalityAggregationBuilder;
	}

	private BoolQueryBuilder _getKnownIndividualBoolQueryBuilder() {
		return BoolQueryBuilderUtil.should(
			QueryBuilders.termQuery("knownIndividual", true)
		).should(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("individualId")
			).mustNot(
				QueryBuilders.existsQuery("knownIndividual")
			)
		);
	}

	private long _getUsersCountAggregationValue(Aggregations aggregations) {
		if (DogUtil.isEmpty(aggregations)) {
			return 0L;
		}

		return DogUtil.getCardinalityAsLong(aggregations.get("users_count"));
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}