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

package com.liferay.osb.asah.backend.rest.response;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.Min;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Leslie Wong
 */
public class NumbersDistributionTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	@Override
	public JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker, int page, int size,
		List<Pair<String, SortOrder>> sortOrderPairs, String supportedFieldName,
		QueryBuilder queryBuilder) {

		JSONArray jsonArray = new JSONArray();

		if ((size == 0) ||
			!elasticsearchInvoker.exists(collectionName, queryBuilder)) {

			return jsonArray;
		}

		_totalElements = size;

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.max(
						"max"
					).field(
						supportedFieldName
					));

				searchSourceBuilder.aggregation(
					AggregationBuilders.min(
						"min"
					).field(
						supportedFieldName
					));

				if (queryBuilder != null) {
					searchSourceBuilder.query(queryBuilder);
				}
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Max max = aggregations.get("max");
		Min min = aggregations.get("min");

		double maxValue = max.getValue();
		double minValue = min.getValue();

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				supportedFieldName
			).gte(
				minValue
			).lte(
				maxValue
			));

		if (queryBuilder != null) {
			boolQueryBuilder.filter(queryBuilder);
		}

		if ((maxValue == minValue) || (size == 1)) {
			_totalElements = 1;

			return JSONUtil.put(
				JSONUtil.put(
					"count",
					elasticsearchInvoker.count(collectionName, boolQueryBuilder)
				).put(
					"values", JSONUtil.putAll(minValue, maxValue)
				));
		}

		searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				RangeAggregationBuilder aggregationBuilder =
					AggregationBuilders.range(
						"ranges"
					).field(
						supportedFieldName
					);

				double diff = maxValue - minValue;

				double binSize = diff / size;

				if ((binSize != Math.ceil(binSize)) && (diff > 1)) {
					binSize = Math.ceil(binSize);
				}

				for (int i = 0; i < size; i++) {
					double from = minValue + (i * binSize);
					double to = minValue + ((i + 1) * binSize);

					if ((to == maxValue) && (i == (size - 1))) {
						aggregationBuilder.addUnboundedFrom(from);
					}
					else {
						aggregationBuilder.addRange(from, to);
					}
				}

				searchSourceBuilder.aggregation(aggregationBuilder);

				searchSourceBuilder.query(boolQueryBuilder);
			});

		aggregations = searchResponse.getAggregations();

		Range range = aggregations.get("ranges");

		for (Range.Bucket bucket : range.getBuckets()) {
			Double to = (Double)bucket.getTo();

			if (to == Double.POSITIVE_INFINITY) {
				to = maxValue;
			}

			jsonArray.put(
				JSONUtil.put(
					"count", bucket.getDocCount()
				).put(
					"values", JSONUtil.putAll(bucket.getFrom(), to)
				));
		}

		return jsonArray;
	}

	@Override
	public long getTotalElements() {
		return _totalElements;
	}

	private long _totalElements;

}