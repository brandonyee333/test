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
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.MetricType;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "anonymousUsersCount", typeName = "Metric")
@GraphQLTypeWiring(fieldName = "knownUsersCount", typeName = "Metric")
@GraphQLTypeWiring(
	fieldName = "nonsegmentedKnownUsersCount", typeName = "Metric"
)
@GraphQLTypeWiring(
	fieldName = "segmentedAnonymousUsersCount", typeName = "Metric"
)
@GraphQLTypeWiring(fieldName = "segmentedKnownUsersCount", typeName = "Metric")
public class UsersCountDataFetcher extends BaseDataFetcher<Long> {

	@Override
	public Long get(
		DataFetchingEnvironment dataFetchingEnvironment,
		SearchQueryContext searchQueryContext) {

		ExecutionTypeInfo fieldExecutionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		ExecutionTypeInfo parentExecutionTypeInfo =
			fieldExecutionTypeInfo.getParentTypeInfo();

		GraphQLFieldDefinition parentGraphQLFieldDefinition =
			parentExecutionTypeInfo.getFieldDefinition();

		MetricType metricType = _metricTypeDog.getMetricType(
			searchQueryContext.getAssetType(),
			parentGraphQLFieldDefinition.getName());

		GraphQLFieldDefinition graphQLFieldDefinition =
			fieldExecutionTypeInfo.getFieldDefinition();

		if (Objects.equals(
				graphQLFieldDefinition.getName(), "anonymousUsersCount")) {

			return _userDog.getAnonymousUsersCount(
				metricType, searchQueryContext);
		}

		if (Objects.equals(
				graphQLFieldDefinition.getName(), "knownUsersCount")) {

			return _userDog.getKnownUsersCount(metricType, searchQueryContext);
		}

		if (Objects.equals(
				graphQLFieldDefinition.getName(),
				"nonsegmentedKnownUsersCount")) {

			return _userDog.getNonsegmentedIndividualsCount(
				metricType, searchQueryContext);
		}

		if (Objects.equals(
				graphQLFieldDefinition.getName(),
				"segmentedAnonymousUsersCount")) {

			return 0L;
		}

		return _userDog.getSegmentedIndividualsCount(
			metricType, searchQueryContext);
	}

	@Autowired
	private MetricTypeDog _metricTypeDog;

	@Autowired
	private UserDog _userDog;

}