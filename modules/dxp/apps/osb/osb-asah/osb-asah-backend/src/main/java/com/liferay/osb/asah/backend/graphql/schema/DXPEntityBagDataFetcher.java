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

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "groups", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "organizations", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "roles", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "teams", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "userGroups", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "users", typeName = "QueryType")
public class DXPEntityBagDataFetcher
	implements DataFetcher<ResultBag<? extends DXPEntity>> {

	@Override
	public ResultBag<? extends DXPEntity> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return _dxpEntityDog.getDXPEntityResultBag(
			dataFetchingEnvironment.getArgument("channelId"),
			_getCollectionName(dataFetchingEnvironment),
			dataFetchingEnvironment.getArgument("keywords"),
			dataFetchingEnvironment.getArgument("size"),
			Sort.of(dataFetchingEnvironment.getArgument("sort")),
			dataFetchingEnvironment.getArgument("start"));
	}

	private String _getCollectionName(
		DataFetchingEnvironment dataFetchingEnvironment) {

		ExecutionTypeInfo executionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			executionTypeInfo.getFieldDefinition();

		String name = graphQLFieldDefinition.getName();

		if (name.equals("userGroups")) {
			return "user-groups";
		}

		return name;
	}

	@Autowired
	private DXPEntityDog _dxpEntityDog;

}