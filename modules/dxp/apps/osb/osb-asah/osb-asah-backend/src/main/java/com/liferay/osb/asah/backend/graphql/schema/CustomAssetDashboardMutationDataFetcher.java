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

import com.liferay.osb.asah.backend.dog.CustomAssetDashboardDog;
import com.liferay.osb.asah.backend.dto.DashboardDTO;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "dashboard", typeName = "MutationType")
public class DashboardMutationDataFetcher implements DataFetcher<DashboardDTO> {

	@Override
	public DashboardDTO get(DataFetchingEnvironment dataFetchingEnvironment) {
		String dashboardId = dataFetchingEnvironment.getArgument("dashboardId");
		String definition = dataFetchingEnvironment.getArgument("definition");
		String modifiedByUserId = dataFetchingEnvironment.getArgument(
			"modifiedByUserId");
		String modifiedByUserName = dataFetchingEnvironment.getArgument(
			"modifiedByUserName");

		CustomAssetDashboard customAssetDashboard =
			_customAssetDashboardDog.updateCustomAssetDashboard(
				dashboardId, definition, modifiedByUserId, modifiedByUserName);

		if (customAssetDashboard == null) {
			return null;
		}

		return new DashboardDTO(customAssetDashboard);
	}

	@Autowired
	private CustomAssetDashboardDog _customAssetDashboardDog;

}