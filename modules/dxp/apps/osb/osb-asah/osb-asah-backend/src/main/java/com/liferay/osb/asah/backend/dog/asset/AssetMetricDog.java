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

package com.liferay.osb.asah.backend.dog.asset;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.TimeRange;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import java.time.LocalDate;

import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class AssetMetricDog {

	public long getMetricValue(
		String assetId, Optional<String> channelIdOptional,
		String collectionName, Optional<String> dataSourceIdOptional,
		Optional<LocalDate> endLocalDateOptional, MetricType metricType,
		Optional<LocalDate> startLocalDateOptional) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			_createRangeQueryBuilder(
				endLocalDateOptional, startLocalDateOptional));

		boolQueryBuilder.filter(QueryBuilders.termQuery("assetId", assetId));

		if (channelIdOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("channelId", channelIdOptional.get()));
		}

		if (dataSourceIdOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", dataSourceIdOptional.get()));
		}

		return _getMetricValue(collectionName, metricType, boolQueryBuilder);
	}

	private QueryBuilder _createRangeQueryBuilder(
		Optional<LocalDate> endLocalDateOptional,
		Optional<LocalDate> startLocalDateOptional) {

		if (!endLocalDateOptional.isPresent() ||
			!startLocalDateOptional.isPresent()) {

			return QueryBuilders.boolQuery();
		}

		return _searchQueryHelper.createRangeQueryBuilder(
			"eventDate",
			TimeRange.of(
				startLocalDateOptional.get(), endLocalDateOptional.get()),
			_timeZoneDog.getTimeZoneId());
	}

	private AggregationBuilder _createSumAggregationBuilder(
		MetricType metricType) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			metricType.getAggregationName());

		sumAggregationBuilder.field(metricType.getFieldName());

		return sumAggregationBuilder;
	}

	private long _getMetricValue(
		String collectionName, MetricType metricType,
		QueryBuilder queryBuilder) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			_createSumAggregationBuilder(metricType));
		searchSourceBuilder.query(queryBuilder);
		searchSourceBuilder.size(0);

		Aggregations aggregations = _dataDog.queryAggregations(
			collectionName, searchSourceBuilder);

		if (DogUtil.isEmpty(aggregations)) {
			return 0;
		}

		Sum sum = aggregations.get(metricType.getAggregationName());

		return (long)sum.getValue();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}