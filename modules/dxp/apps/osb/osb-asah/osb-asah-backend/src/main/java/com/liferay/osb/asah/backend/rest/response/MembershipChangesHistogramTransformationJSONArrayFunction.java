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
import com.liferay.osb.asah.common.rest.response.BaseTransformationJSONArrayFunction;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregator;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class MembershipChangesHistogramTransformationJSONArrayFunction
	extends BaseTransformationJSONArrayFunction {

	public MembershipChangesHistogramTransformationJSONArrayFunction(
		boolean includeToday) {

		super(includeToday);
	}

	@Override
	public JSONArray apply(
			String collectionName, String computeFunctionString,
			ElasticsearchInvoker elasticsearchInvoker, int page, int size,
			List<Pair<String, SortOrder>> sortOrderPair,
			String supportedFieldName, QueryBuilder queryBuilder)
		throws Exception {

		JSONArray jsonArray = new JSONArray();

		computeEndDayDateString();
		computeStartDayDateString(computeFunctionString, size);

		ExtendedBounds extendedBounds = new ExtendedBounds(
			startDayDateString, endDayDateString);

		QueryBuilder finalQueryBuilder = BoolQueryBuilderUtil.filter(
			queryBuilder
		).filter(
			QueryBuilders.rangeQuery(
				supportedFieldName
			).lte(
				endDayDateString
			)
		).filter(
			QueryBuilders.rangeQuery(
				supportedFieldName
			).gte(
				startDayDateString
			)
		);

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
					AggregationBuilders.filters(
						"operation",
						new FiltersAggregator.KeyedFilter(
							"ADDED",
							QueryBuilders.termQuery("operation", "ADDED")),
						new FiltersAggregator.KeyedFilter(
							"REMOVED",
							QueryBuilders.termQuery("operation", "REMOVED"))
					).subAggregation(
						AggregationBuilders.filter(
							"knownIndividuals",
							QueryBuilders.existsQuery("individualEmail"))
					)
				);

				searchSourceBuilder.aggregation(aggregationBuilder);

				searchSourceBuilder.query(finalQueryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		InternalDateHistogram internalDateHistogram = aggregations.get(
			computeFunctionString);

		long individualsCount = 0;
		long knownIndividualsCount = 0;

		JSONArray membershipChangesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				collectionName,
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							queryBuilder
						).filter(
							QueryBuilders.rangeQuery(
								"dateChanged"
							).lt(
								startDayDateString
							)
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort("id", SortOrder.DESC);
				}));

		if (membershipChangesJSONArray.length() > 0) {
			JSONObject membershipChangeJSONObject =
				membershipChangesJSONArray.getJSONObject(0);

			individualsCount = membershipChangeJSONObject.getLong(
				"individualsCount");

			knownIndividualsCount = membershipChangeJSONObject.optLong(
				"knownIndividualsCount", individualsCount);
		}

		for (InternalDateHistogram.Bucket bucket :
				internalDateHistogram.getBuckets()) {

			Aggregations termsAggregations = bucket.getAggregations();

			Filters filters = termsAggregations.get("operation");

			long addedIndividualsCount = 0;
			long addedKnownIndividualsCount = 0;
			long removedIndividualsCount = 0;
			long removedKnownIndividualsCount = 0;

			for (Filters.Bucket filtersBucket : filters.getBuckets()) {
				Aggregations knownIndividualsAggregations =
					filtersBucket.getAggregations();

				Filter knownIndividualsFilter =
					knownIndividualsAggregations.get("knownIndividuals");

				String operation = filtersBucket.getKeyAsString();

				if (operation.equals("ADDED")) {
					addedIndividualsCount = filtersBucket.getDocCount();

					addedKnownIndividualsCount =
						knownIndividualsFilter.getDocCount();
				}
				else if (operation.equals("REMOVED")) {
					removedIndividualsCount = filtersBucket.getDocCount();

					removedKnownIndividualsCount =
						knownIndividualsFilter.getDocCount();
				}
				else if (_log.isWarnEnabled()) {
					_log.warn(
						"Invalid operation in membership changes: " +
							operation);
				}
			}

			individualsCount += addedIndividualsCount;
			individualsCount -= removedIndividualsCount;

			knownIndividualsCount += addedKnownIndividualsCount;
			knownIndividualsCount -= removedKnownIndividualsCount;

			jsonArray.put(
				JSONUtil.put(
					"addedIndividualsCount", addedIndividualsCount
				).put(
					"anonymousIndividualsCount",
					individualsCount - knownIndividualsCount
				).put(
					"individualsCount", individualsCount
				).put(
					"intervalInitDate", bucket.getKeyAsString()
				).put(
					"knownIndividualsCount", knownIndividualsCount
				).put(
					"removedIndividualsCount", removedIndividualsCount
				));
		}

		return jsonArray;
	}

	private static final Log _log = LogFactory.getLog(
		MembershipChangesHistogramTransformationJSONArrayFunction.class);

}