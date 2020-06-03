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
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;

import graphql.schema.DataFetchingEnvironment;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "assetPages", typeName = "QueryType")
public class PageDataFetcher extends BaseDataFetcher<List<AssetMetric>> {

	@Override
	public List<AssetMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		AssetMetric assetMetric = _metricDog.getAssetMetric(searchQueryContext);

		List<String> urls = assetMetric.getURLs();

		if (urls.isEmpty()) {
			return Collections.emptyList();
		}

		searchQueryContext.setAssetType(AssetType.PAGE);

		Map<String, Object> context = dataFetchingEnvironment.getContext();

		return _metricDog.getAssetMetrics(
			new HashSet<>(urls), null, searchQueryContext,
			(Set<String>)context.get("selectedMetrics"), 1000, 0);
	}

	@Autowired
	private MetricDog _metricDog;

}