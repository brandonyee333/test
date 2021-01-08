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
import com.liferay.osb.asah.backend.model.Metric;

import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "assets", typeName = "QueryType")
public class AssetMetricsDataFetcher
	extends BaseDataFetcher<List<AssetMetric>> {

	@Override
	public List<AssetMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		List<AssetMetric> assetMetrics = new ArrayList<>();

		Map<String, Object> context = dataFetchingEnvironment.getContext();

		for (AssetType assetType : AssetType.values()) {
			if (Objects.equals(assetType, AssetType.PAGE)) {
				continue;
			}

			searchQueryContext.setAssetType(assetType);

			assetMetrics.addAll(
				_metricDog.getAssetMetrics(
					null, searchQueryContext,
					(Set<String>)context.get("selectedMetrics"), 10000, 0));
		}

		assetMetrics.sort(
			Comparator.comparing(
				this::_getDefaultAssetMetricValue,
				Comparator.nullsLast(Comparator.reverseOrder())));

		return assetMetrics;
	}

	@Override
	protected AssetType getAssetType(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return null;
	}

	private Double _getDefaultAssetMetricValue(AssetMetric assetMetric) {
		Metric defaultMetric = assetMetric.getDefaultMetric();

		return Optional.ofNullable(
			defaultMetric.getValue()
		).orElse(
			0D
		);
	}

	@Autowired
	private MetricDog _metricDog;

}