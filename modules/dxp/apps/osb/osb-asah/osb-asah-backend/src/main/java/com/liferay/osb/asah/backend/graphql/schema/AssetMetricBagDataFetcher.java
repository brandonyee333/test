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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.dog.MetricDog;
import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import java.util.Map;
import java.util.Set;

import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "blogs", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "documents", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "forms", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "journals", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "pages", typeName = "QueryType")
public class AssetMetricBagDataFetcher extends BaseDataFetcher<ResultBag> {

	@Override
	public ResultBag get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ResultBag<AssetMetric> resultBag = new ResultBag<>();

		int assetMetricsCount = _metricDog.getAssetMetricsCount(
			searchQueryContext);

		if (assetMetricsCount == 0) {
			return resultBag;
		}

		Map<String, Object> context = dataFetchingEnvironment.getContext();
		Map<String, String> sort = dataFetchingEnvironment.getArgument("sort");
		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		resultBag.setResults(
			_metricDog.getAssetMetrics(
				assetMetricsCount,
				_createFieldSortBuilder(
					searchQueryContext.getAssetType(), sort),
				searchQueryContext, (Set<String>)context.get("selectedMetrics"),
				size, start));

		resultBag.setTotal(assetMetricsCount);

		return resultBag;
	}

	@Override
	protected AssetType getAssetType(
		DataFetchingEnvironment dataFetchingEnvironment) {

		ExecutionTypeInfo executionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			executionTypeInfo.getFieldDefinition();

		String name = graphQLFieldDefinition.getName();

		return AssetType.of(name.substring(0, name.length() - 1));
	}

	private FieldSortBuilder _createFieldSortBuilder(
		AssetType assetType, Map<String, String> sort) {

		MetricType metricType = _metricTypeDog.getMetricType(
			assetType, sort.get("column"));

		return SortBuilderUtil.fieldSort(
			metricType.getAggregationName(),
			SortOrder.valueOf(sort.get("type")));
	}

	@Autowired
	private MetricDog _metricDog;

	@Autowired
	private MetricTypeDog _metricTypeDog;

}