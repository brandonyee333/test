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

import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.VisitorCohortHeatMapDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.CohortHeatMapMetric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.SiteMetricType;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
@GraphQLTypeWiring(fieldName = "cohortHeatMap", typeName = "Metric")
public class CohortHeatMapDataFetcher
	extends BaseDataFetcher<List<CohortHeatMapMetric>> {

	@Override
	public List<CohortHeatMapMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ExecutionTypeInfo fieldExecutionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		ExecutionTypeInfo parentExecutionTypeInfo =
			fieldExecutionTypeInfo.getParentTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			parentExecutionTypeInfo.getFieldDefinition();

		MetricType metricType = _metricTypeDog.getMetricType(
			searchQueryContext.getAssetType(),
			graphQLFieldDefinition.getName());

		if ((metricType == SiteMetricType.ANONYMOUS_VISITORS) ||
			(metricType == SiteMetricType.KNOWN_VISITORS) ||
			(metricType == SiteMetricType.VISITORS)) {

			return _visitorCohortHeatMapDog.getCohortHeatMapMetrics(
				metricType, searchQueryContext);
		}

		return Collections.emptyList();
	}

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private VisitorCohortHeatMapDog _visitorCohortHeatMapDog;

}