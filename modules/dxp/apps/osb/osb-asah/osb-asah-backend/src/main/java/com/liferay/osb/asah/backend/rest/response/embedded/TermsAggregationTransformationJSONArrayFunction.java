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

package com.liferay.osb.asah.backend.rest.response.embedded;

import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.InternalCardinality;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

/**
 * @author Leslie Wong
 */
public class TermsAggregationTransformationJSONArrayFunction
	implements TransformationJSONArrayFunction {

	public TermsAggregationTransformationJSONArrayFunction(
		String apply,
		Function<Terms.Bucket, Object> responseFormatterFunction) {

		Matcher matcher = _groupByPattern.matcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					StringEscapeUtils.unescapeJava(_groupByPattern.toString()));
		}

		_contains = matcher.group("containsField");

		String groupByField = matcher.group("groupByField");

		_fieldName = groupByField.replace('/', '.');

		_responseFormatterFunction = responseFormatterFunction;
	}

	public TermsAggregationTransformationJSONArrayFunction(
		String contains, String fieldName,
		Function<Terms.Bucket, Object> responseFormatterFunction) {

		_contains = contains;
		_fieldName = fieldName.replace('/', '.');
		_responseFormatterFunction = responseFormatterFunction;
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
				TermsAggregationBuilder aggregationBuilder =
					AggregationBuilders.terms(
						_fieldName.replace('.', '/')
					).field(
						_fieldName
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						PipelineAggregatorBuilders.bucketSort(
							"groupBySort",
							_getGroupByFieldSortBuilders(sortOrderPairs)
						).from(
							Math.max(0, page * size)
						).size(
							size
						)
					);

				searchSourceBuilder.aggregation(aggregationBuilder);

				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"totalElements"
					).field(
						_fieldName
					));

				BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

				if (queryBuilder != null) {
					boolQueryBuilder.filter(queryBuilder);
				}

				QueryBuilder includeQueryBuilder = _getIncludeQueryBuilder(
					_fieldName, _contains);

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
			return _getTransformationJSONArray(Collections.emptyList(), null);
		}

		Terms terms = aggregations.get(_fieldName.replace('.', '/'));

		InternalCardinality internalCardinality = aggregations.get(
			"totalElements");

		_totalElements = internalCardinality.getValue();

		return _getTransformationJSONArray(
			terms.getBuckets(), _fieldName.replace('.', '/'));
	}

	@Override
	public long getTotalElements() {
		return _totalElements;
	}

	private List<FieldSortBuilder> _getGroupByFieldSortBuilders(
		List<Pair<String, SortOrder>> sortOrderPairs) {

		if ((sortOrderPairs == null) || sortOrderPairs.isEmpty()) {
			return new ArrayList<FieldSortBuilder>() {
				{
					add(SortBuilderUtil.fieldSort("_count", SortOrder.DESC));
					add(SortBuilderUtil.fieldSort("_key"));
				}
			};
		}

		List<FieldSortBuilder> groupByFieldSortBuilders = new ArrayList<>();

		for (Pair<String, SortOrder> sortOrderPair : sortOrderPairs) {
			groupByFieldSortBuilders.add(
				0,
				SortBuilderUtil.fieldSort(
					sortOrderPair.getKey(), sortOrderPair.getValue()));
		}

		return groupByFieldSortBuilders;
	}

	private QueryBuilder _getIncludeQueryBuilder(
		String fieldName, String contains) {

		if (contains == null) {
			return null;
		}

		return QueryBuilders.regexpQuery(
			fieldName,
			FilterStringToQueryBuilderConverter.buildIgnoreCaseRegExp(
				StringUtil.unquote(contains)));
	}

	private JSONArray _getTransformationJSONArray(
		List<? extends Terms.Bucket> buckets, String fieldName) {

		JSONArray transformationsJSONArray = new JSONArray();

		for (Terms.Bucket bucket : buckets) {
			if (_responseFormatterFunction != null) {
				transformationsJSONArray.put(
					_responseFormatterFunction.apply(bucket));
			}
			else {
				transformationsJSONArray.put(
					JSONUtil.put(
						"terms",
						JSONUtil.put(fieldName, bucket.getKeyAsString())
					).put(
						"totalElements", bucket.getDocCount()
					));
			}
		}

		return transformationsJSONArray;
	}

	private static final Pattern _groupByPattern = Pattern.compile(
		"groupby\\(\\((?<groupByField>[^)]+)\\)\\)" +
			"(/contains\\(\\((?<containsField>[^)]+)\\)\\))?");

	private final String _contains;
	private final String _fieldName;
	private final Function<Terms.Bucket, Object> _responseFormatterFunction;
	private long _totalElements;

}