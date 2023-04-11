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
import com.liferay.osb.asah.backend.dog.UserDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dto.AudienceReportDTO;
import com.liferay.osb.asah.backend.model.AudienceReport;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
@GraphQLTypeWiring(fieldName = "audienceReport", typeName = "Metric")
public class AudienceReportDataFetcher
	extends BaseDataFetcher<AudienceReportDTO> {

	@Override
	public AudienceReportDTO get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ExecutionTypeInfo fieldExecutionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		ExecutionTypeInfo parentExecutionTypeInfo =
			fieldExecutionTypeInfo.getParentTypeInfo();

		GraphQLFieldDefinition parentGraphQLFieldDefinition =
			parentExecutionTypeInfo.getFieldDefinition();

		AudienceReport audienceReport = _userDog.getAudienceReport(
			_metricTypeDog.getMetricType(
				searchQueryContext.getAssetType(),
				parentGraphQLFieldDefinition.getName()),
			searchQueryContext);

		return new AudienceReportDTO(audienceReport);
	}

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private UserDog _userDog;

}