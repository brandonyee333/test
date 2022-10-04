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
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetchingEnvironment;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "page", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "blog", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "custom", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "document", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "form", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "journal", typeName = "QueryType")
public class AssetMetricDataFetcher extends BaseDataFetcher<AssetMetric> {

	@Override
	public AssetMetric get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		Map<String, Object> context = dataFetchingEnvironment.getContext();

		Set<String> selectedMetrics = (Set<String>)context.get(
			"selectedMetrics");

		selectedMetrics.remove("accessMetric");

		AssetType assetType = searchQueryContext.getAssetType();

		if (assetType.equals(AssetType.PAGE) &&
			StringUtils.isBlank(searchQueryContext.getAssetId())) {

			searchQueryContext.setAssetId(searchQueryContext.getCanonicalUrl());
		}

		return _metricDog.getAssetMetric(searchQueryContext, selectedMetrics);
	}

	@Autowired
	private MetricDog _metricDog;

}