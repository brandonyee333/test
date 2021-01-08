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

import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.IndividualHistogramDog;
import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.VisitorHistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.PageMetricType;

import graphql.execution.ExecutionTypeInfo;

import graphql.language.Field;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import graphql.schema.GraphQLFieldDefinition;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "histogram", typeName = "Metric")
public class HistogramDataFetcher
	extends BaseDataFetcher<List<HistogramMetric>> {

	@Override
	public List<HistogramMetric> get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		DataFetchingFieldSelectionSet dataFetchingFieldSelectionSet =
			dataFetchingEnvironment.getSelectionSet();

		Map<String, List<Field>> fields = dataFetchingFieldSelectionSet.get();

		boolean includePrevious = false;

		if (fields.containsKey("previousValue") ||
			fields.containsKey("trend")) {

			includePrevious = true;
		}

		ExecutionTypeInfo fieldExecutionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		ExecutionTypeInfo parentExecutionTypeInfo =
			fieldExecutionTypeInfo.getParentTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			parentExecutionTypeInfo.getFieldDefinition();

		MetricType metricType = _metricTypeDog.getMetricType(
			searchQueryContext.getAssetType(),
			graphQLFieldDefinition.getName());

		if ((metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) ||
			(metricType == IndividualMetricType.KNOWN_INDIVIDUALS) ||
			(metricType == IndividualMetricType.TOTAL_INDIVIDUALS)) {

			return _individualHistogramDog.getHistogramMetrics(
				metricType, searchQueryContext);
		}
		else if (metricType == PageMetricType.VISITORS) {
			return _visitorHistogramDog.getHistogramMetrics(
				includePrevious, metricType, searchQueryContext);
		}

		return _histogramDog.getHistogramMetrics(
			includePrevious, metricType, searchQueryContext);
	}

	@Autowired
	private HistogramDog _histogramDog;

	@Autowired
	private IndividualHistogramDog _individualHistogramDog;

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private VisitorHistogramDog _visitorHistogramDog;

}