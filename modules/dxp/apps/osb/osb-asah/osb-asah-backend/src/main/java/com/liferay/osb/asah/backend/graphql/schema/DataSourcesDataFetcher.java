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

import com.liferay.osb.asah.backend.dto.DataSourceDTO;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "dataSources", typeName = "QueryType")
public class DataSourcesDataFetcher
	implements DataFetcher<List<DataSourceDTO>> {

	@Override
	public List<DataSourceDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		Sort sort = null;

		Map<String, String> sortMap = dataFetchingEnvironment.getArgument(
			"sort");

		if (sortMap != null) {
			if (Objects.equals("dateCreated", sortMap.get("column"))) {
				sortMap.put("column", "createDate");
			}

			sort = Sort.by(
				new Sort.Order(
					Sort.Direction.fromString(sortMap.get("type")),
					sortMap.get("column")));
		}

		return ListUtil.map(
			_dataSourceDog.getDataSources(
				dataFetchingEnvironment.getArgument("credentialsType"),
				dataFetchingEnvironment.getArgument("type"),
				dataFetchingEnvironment.getArgument("size"), sort),
			DataSourceDTO::new);
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

}