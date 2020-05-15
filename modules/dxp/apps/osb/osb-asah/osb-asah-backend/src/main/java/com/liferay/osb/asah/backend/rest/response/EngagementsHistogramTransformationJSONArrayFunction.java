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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.BaseTransformationJSONArrayFunction;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public class EngagementsHistogramTransformationJSONArrayFunction
	extends BaseTransformationJSONArrayFunction {

	public EngagementsHistogramTransformationJSONArrayFunction(
		boolean includeToday, String individualSegmentId) {

		super(includeToday);

		_individualSegmentId = individualSegmentId;
	}

	@Override
	protected JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker,
		ExtendedBounds extendedBounds, QueryBuilder queryBuilder,
		String supportedFieldName) {

		if (StringUtils.isEmpty(_individualSegmentId)) {
			return _getEngagementsTransformationJSONArray(
				collectionName, computeFunctionString, elasticsearchInvoker,
				extendedBounds, queryBuilder, supportedFieldName);
		}

		return _getIndividualSegmentEngagementsTransformationJSONArray(
			collectionName, computeFunctionString, elasticsearchInvoker,
			extendedBounds, queryBuilder, supportedFieldName);
	}

	private JSONArray _getEngagementsTransformationJSONArray(
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
				).subAggregation(
					AggregationBuilders.avg(
						"scoreAvg"
					).field(
						"score"
					)
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
					"scoreAvg",
					getAggregationValue(bucket.getAggregations(), "scoreAvg")
				).put(
					"totalElements", bucket.getDocCount()
				));
		}

		return jsonArray;
	}

	private JSONArray _getIndividualSegmentEngagementsTransformationJSONArray(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker,
		ExtendedBounds extendedBounds, QueryBuilder queryBuilder,
		String supportedFieldName) {

		JSONArray jsonArray = new JSONArray();

		Map<String, Double> scores = new HashMap<>();

		JSONArray engagementsJSONArray = elasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).lte(
					endDayDateString
				)
			).filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).gte(
					startDayDateString
				)
			).filter(
				QueryBuilders.termQuery("ownerId", _individualSegmentId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual-segment")
			));

		for (int i = 0; i < engagementsJSONArray.length(); i++) {
			JSONObject engagementJSONObject =
				engagementsJSONArray.getJSONObject(i);

			scores.put(
				engagementJSONObject.getString("dateRecorded"),
				engagementJSONObject.getDouble("score"));
		}

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

			String intervalInitDate = bucket.getKeyAsString();

			jsonArray.put(
				JSONUtil.put(
					"intervalInitDate", intervalInitDate
				).put(
					"scoreAvg", scores.getOrDefault(intervalInitDate, 0.0)
				).put(
					"totalElements", bucket.getDocCount()
				));
		}

		return jsonArray;
	}

	private final String _individualSegmentId;

}