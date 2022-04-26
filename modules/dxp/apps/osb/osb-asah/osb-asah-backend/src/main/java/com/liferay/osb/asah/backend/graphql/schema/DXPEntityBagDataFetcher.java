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
import com.liferay.osb.asah.common.dog.BQGroupDog;
import com.liferay.osb.asah.common.dog.BQRoleDog;
import com.liferay.osb.asah.common.dog.BQTeamDog;
import com.liferay.osb.asah.common.dog.BQUserDog;
import com.liferay.osb.asah.common.dog.BQUserGroupDog;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.BQDXPEntity;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.execution.ExecutionTypeInfo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.GraphQLFieldDefinition;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "groups", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "roles", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "teams", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "userGroups", typeName = "QueryType")
@GraphQLTypeWiring(fieldName = "users", typeName = "QueryType")
public class DXPEntityBagDataFetcher
	implements DataFetcher<ResultBag<? extends DXPEntityDTO>> {

	@Override
	public ResultBag<? extends DXPEntityDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Page<? extends BQDXPEntity> bqDXPEntityPage = _getDXPEntityPage(
			NumberUtils.createLong(
				dataFetchingEnvironment.getArgument("channelId")),
			_getGraphQLFieldDefinitionName(dataFetchingEnvironment),
			dataFetchingEnvironment.getArgument("keywords"),
			dataFetchingEnvironment.getArgument("size"),
			Sort.of(dataFetchingEnvironment.getArgument("sort")),
			dataFetchingEnvironment.getArgument("start"));

		return new ResultBag<>(
			ListUtil.map(
				bqDXPEntityPage.getContent(),
				bqDXPEntity -> {
					if (bqDXPEntity instanceof BQOrganization) {
						return new DXPOrganizationDTO(
							(BQOrganization)bqDXPEntity);
					}

					if (bqDXPEntity instanceof BQUser) {
						return new DXPUserDTO((BQUser)bqDXPEntity);
					}

					return new DXPEntityDTO(bqDXPEntity);
				}),
			bqDXPEntityPage.getTotalElements());
	}

	private Page<? extends BQDXPEntity> _getDXPEntityPage(
		Long channelId, String graphQLFieldDefinitionName, String keywords,
		int size, Sort sort, int start) {

		if (StringUtils.equals(graphQLFieldDefinitionName, "groups")) {
			return _bqGroupDog.getBQGroupPage(
				channelId, keywords, size, sort, start);
		}
		else if (StringUtils.equals(graphQLFieldDefinitionName, "roles")) {
			return _bqRoleDog.getBQRolePage(
				channelId, keywords, size, sort, start);
		}
		else if (StringUtils.equals(graphQLFieldDefinitionName, "teams")) {
			return _bqTeamDog.getBQTeamPage(
				channelId, keywords, size, sort, start);
		}
		else if (StringUtils.equals(graphQLFieldDefinitionName, "userGroups")) {
			return _bqUserGroupDog.getBQUserGroupPage(
				channelId, keywords, size, sort, start);
		}
		else if (StringUtils.equals(graphQLFieldDefinitionName, "users")) {
			return _bqUserDog.getBQUserPage(
				channelId, keywords, size, sort, start);
		}

		return null;
	}

	private String _getGraphQLFieldDefinitionName(
		DataFetchingEnvironment dataFetchingEnvironment) {

		ExecutionTypeInfo executionTypeInfo =
			dataFetchingEnvironment.getFieldTypeInfo();

		GraphQLFieldDefinition graphQLFieldDefinition =
			executionTypeInfo.getFieldDefinition();

		return graphQLFieldDefinition.getName();
	}

	@Autowired
	private BQGroupDog _bqGroupDog;

	@Autowired
	private BQRoleDog _bqRoleDog;

	@Autowired
	private BQTeamDog _bqTeamDog;

	@Autowired
	private BQUserDog _bqUserDog;

	@Autowired
	private BQUserGroupDog _bqUserGroupDog;

}