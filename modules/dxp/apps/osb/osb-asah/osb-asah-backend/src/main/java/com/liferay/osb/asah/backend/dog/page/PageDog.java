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

package com.liferay.osb.asah.backend.dog.page;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.PageMetricType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.petra.string.StringPool;

import java.util.Optional;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class PageDog {

	public long getIndirectAccessMetricValue(
		SearchQueryContext searchQueryContext) {

		return _getMetricValue(
			PageMetricType.INDIRECT_ACCESS,
			_createQueryBuilder(searchQueryContext));
	}

	public long getMetricValue(
		Optional<String> canonicalUrlOptional,
		Optional<String> fromDateStringOptional, PageMetricType pageMetricType,
		Optional<String> toDateStringOptional, Optional<String> urlOptional) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			_createRangeQueryBuilder(
				fromDateStringOptional, toDateStringOptional));

		if (urlOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("url", urlOptional.get()));
		}
		else if (canonicalUrlOptional.isPresent()) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"canonicalUrl", canonicalUrlOptional.get()));
		}

		return _getMetricValue(pageMetricType, boolQueryBuilder);
	}

	public long getViewsMetricValue(
		Optional<String> fromDateStringOptional,
		Optional<String> toDateStringOptional, Optional<String> urlOptional) {

		return getMetricValue(
			Optional.empty(), fromDateStringOptional, PageMetricType.VIEWS,
			toDateStringOptional, urlOptional);
	}

	private QueryBuilder _createQueryBuilder(
		SearchQueryContext searchQueryContext) {

		BoolQueryBuilder boolQueryBuilder =
			_searchQueryHelper.createFilterBoolQueryBuilder(
				Optional.empty(), searchQueryContext);

		boolQueryBuilder.mustNot(
			QueryBuilders.termQuery("referrer", StringPool.BLANK));

		return boolQueryBuilder;
	}

	private QueryBuilder _createRangeQueryBuilder(
		Optional<String> fromDateStringOptional,
		Optional<String> toDateStringOptional) {

		if (!fromDateStringOptional.isPresent() &&
			!toDateStringOptional.isPresent()) {

			return QueryBuilders.boolQuery();
		}

		RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(
			"eventDate");

		fromDateStringOptional.map(rangeQueryBuilder::gte);
		toDateStringOptional.map(rangeQueryBuilder::lte);

		return rangeQueryBuilder;
	}

	private AggregationBuilder _createSumAggregationBuilder(
		MetricType metricType) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			metricType.getAggregationName());

		sumAggregationBuilder.field(metricType.getFieldName());

		return sumAggregationBuilder;
	}

	private long _getMetricValue(
		PageMetricType pageMetricType, QueryBuilder queryBuilder) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.aggregation(
			_createSumAggregationBuilder(pageMetricType));
		searchSourceBuilder.query(queryBuilder);
		searchSourceBuilder.size(0);

		Aggregations aggregations = _dataDog.queryAggregations(
			"pages", searchSourceBuilder);

		if (DogUtil.isEmpty(aggregations)) {
			return 0;
		}

		Sum sum = aggregations.get(pageMetricType.getAggregationName());

		return (long)sum.getValue();
	}

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}