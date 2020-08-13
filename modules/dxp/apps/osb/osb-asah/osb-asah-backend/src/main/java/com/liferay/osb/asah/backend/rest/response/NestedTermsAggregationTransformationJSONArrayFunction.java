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

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.Nested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.InternalCardinality;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Rachael Koestartyo
 */
public class NestedTermsAggregationTransformationJSONArrayFunction
	extends TermsAggregationTransformationJSONArrayFunction {

	public NestedTermsAggregationTransformationJSONArrayFunction(
		String contains, String fieldName, String path,
		Function<Terms.Bucket, Object> responseFormatterFunction) {

		super(contains, fieldName, responseFormatterFunction);

		_path = path;
	}

	@Override
	public JSONArray apply(
		String collectionName, String computeFunctionString,
		ElasticsearchInvoker elasticsearchInvoker, int page, int size,
		List<Pair<String, SortOrder>> sortOrderPairs, String supportedFieldName,
		QueryBuilder queryBuilder) {

		SearchResponse searchResponse = elasticsearchInvoker.search(
			collectionName,
			searchSourceBuilder -> {
				NestedAggregationBuilder nestedAggregationBuilder =
					AggregationBuilders.nested(
						_path, _path
					).subAggregation(
						AggregationBuilders.terms(
							fieldName.replace('.', '/')
						).field(
							fieldName
						).size(
							Integer.MAX_VALUE
						).subAggregation(
							PipelineAggregatorBuilders.bucketSort(
								"groupBySort",
								getGroupByFieldSortBuilders(sortOrderPairs)
							).from(
								Math.max(0, page * size)
							).size(
								size
							)
						)
					).subAggregation(
						AggregationBuilders.cardinality(
							"totalElements"
						).field(
							fieldName
						)
					);

				searchSourceBuilder.aggregation(nestedAggregationBuilder);

				BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

				if (queryBuilder != null) {
					boolQueryBuilder.filter(queryBuilder);
				}

				QueryBuilder includeQueryBuilder = getIncludeQueryBuilder(
					contains, fieldName);

				if (includeQueryBuilder != null) {
					boolQueryBuilder.filter(includeQueryBuilder);
				}

				if (boolQueryBuilder.hasClauses()) {
					searchSourceBuilder.query(boolQueryBuilder);
				}

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (DogUtil.isEmpty(aggregations)) {
			return getTransformationJSONArray(Collections.emptyList(), null);
		}

		Nested nested = aggregations.get(_path);

		Aggregations nestedAggregations = nested.getAggregations();

		Terms terms = nestedAggregations.get(fieldName.replace('.', '/'));

		InternalCardinality internalCardinality = nestedAggregations.get(
			"totalElements");

		totalElements = internalCardinality.getValue();

		return getTransformationJSONArray(
			terms.getBuckets(), fieldName.replace('.', '/'));
	}

	@Override
	protected QueryBuilder getIncludeQueryBuilder(
		String contains, String fieldName) {

		if (contains == null) {
			return null;
		}

		return QueryBuilders.nestedQuery(
			_path, super.getIncludeQueryBuilder(contains, fieldName),
			ScoreMode.None);
	}

	private final String _path;

}