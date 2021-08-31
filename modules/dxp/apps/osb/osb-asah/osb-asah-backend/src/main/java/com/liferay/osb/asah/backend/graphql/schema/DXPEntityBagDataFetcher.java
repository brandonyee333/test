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

import com.liferay.osb.asah.backend.dto.DXPEntityDTO;
import com.liferay.osb.asah.backend.dto.DXPOrganizationDTO;
import com.liferay.osb.asah.backend.dto.DXPUserDTO;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.DXPOrganization;
import com.liferay.osb.asah.common.model.DXPUser;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	implements DataFetcher<ResultBag<? extends DXPEntityDTO>> {

	@Override
	public ResultBag<? extends DXPEntityDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Page<? extends DXPEntity> dxpEntitiesPage =
			_dxpEntityDog.getDXPEntitiesPage(
				NumberUtils.createLong(
					dataFetchingEnvironment.getArgument("channelId")),
				dataFetchingEnvironment.getArgument("keywords"),
				dataFetchingEnvironment.getArgument("size"),
				Sort.of(dataFetchingEnvironment.getArgument("sort")),
				dataFetchingEnvironment.getArgument("start"),
				DXPEntity.Type.ofCollectionName(
					_getCollectionName(dataFetchingEnvironment)));

		return new ResultBag<>(
			ListUtil.map(
				dxpEntitiesPage.toList(),
				dxpEntity -> {
					if (dxpEntity instanceof DXPOrganization) {
						return new DXPOrganizationDTO(
							(DXPOrganization)dxpEntity);
					}

					if (dxpEntity instanceof DXPUser) {
						return new DXPUserDTO((DXPUser)dxpEntity);
					}

					return new DXPEntityDTO(dxpEntity);
				}),
			dxpEntitiesPage.getTotalElements());
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