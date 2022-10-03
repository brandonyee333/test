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

import com.liferay.osb.asah.backend.dog.EventHistogramDog;
import com.liferay.osb.asah.backend.dog.HistogramDog;
import com.liferay.osb.asah.backend.dog.IndividualHistogramDog;
import com.liferay.osb.asah.backend.dog.MetricTypeDog;
import com.liferay.osb.asah.backend.dog.SiteHistogramDog;
import com.liferay.osb.asah.backend.dog.VisitorHistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.EventMetricType;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.IndividualMetricType;
import com.liferay.osb.asah.common.model.MetricType;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
@GraphQLTypeWiring(fieldName = "histogram", typeName = "Metric")
public class HistogramDataFetcher extends BaseDataFetcher<HistogramMetricBag> {

	@Override
	public HistogramMetricBag get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ExecutionTypeInfo fieldExecutionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		ExecutionTypeInfo parentExecutionTypeInfo =
			fieldExecutionTypeInfo.getParentTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			parentExecutionTypeInfo.getFieldDefinition();

		MetricType metricType = EventMetricType.of(
			graphQLFieldDefinition.getName());

		if (metricType == null) {
			metricType = _metricTypeDog.getMetricType(
				searchQueryContext.getAssetType(),
				graphQLFieldDefinition.getName());
		}

		if ((metricType == IndividualMetricType.ANONYMOUS_INDIVIDUALS) ||
			(metricType == IndividualMetricType.KNOWN_INDIVIDUALS) ||
			(metricType == IndividualMetricType.TOTAL_INDIVIDUALS)) {

			return _individualHistogramDog.getHistogramMetricBag(
				metricType, searchQueryContext);
		}

		if (metricType == EventMetricType.TOTAL_EVENTS) {
			return _eventHistogramDog.getEventsCountHistogram(
				searchQueryContext);
		}

		if (metricType == EventMetricType.TOTAL_SESSIONS) {
			return _eventHistogramDog.getSessionsCountHistogram(
				searchQueryContext);
		}

		if (searchQueryContext.getAssetType() == AssetType.SITE) {
			return _siteHistogramDog.getHistogramMetricBag(
				searchQueryContext, (SiteMetricType)metricType);
		}

		return _histogramDog.getHistogramMetricBag(
			metricType, searchQueryContext);
	}

	@Autowired
	private EventHistogramDog _eventHistogramDog;

	@Autowired
	private HistogramDog _histogramDog;

	@Autowired
	private IndividualHistogramDog _individualHistogramDog;

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private SiteHistogramDog _siteHistogramDog;

	@Autowired
	private VisitorHistogramDog _visitorHistogramDog;

}