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

import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.BaseTransformationJSONArrayFunction;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;

import org.json.JSONArray;

/**
 * @author Shinn Lok
 */
public class ActivitiesHistogramTransformationJSONArrayFunction
	extends BaseTransformationJSONArrayFunction {

	public ActivitiesHistogramTransformationJSONArrayFunction(
		boolean includeToday) {

		super(includeToday);
	}

	@Override
	protected JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker,
		ExtendedBounds extendedBounds, QueryBuilder queryBuilder,
		String supportedFieldName) {

		JSONArray jsonArray = new JSONArray();

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				DateHistogramAggregationBuilder aggregationBuilder =
					AggregationBuilders.dateHistogram(computeFunctionString);

				aggregationBuilder.dateHistogramInterval(
					getDateHistogramInterval(computeFunctionString)
				).extendedBounds(
					extendedBounds
				).field(
					supportedFieldName
				).minDocCount(
					0
				);

				searchSourceBuilder.aggregation(aggregationBuilder);

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return jsonArray;
		}

		InternalDateHistogram internalDateHistogram = aggregations.get(
			computeFunctionString);

		for (InternalDateHistogram.Bucket bucket :
				internalDateHistogram.getBuckets()) {

			jsonArray.put(
				JSONUtil.put(
					"intervalInitDate", bucket.getKeyAsString()
				).put(
					"totalElements", bucket.getDocCount()
				));
		}

		return jsonArray;
	}

}