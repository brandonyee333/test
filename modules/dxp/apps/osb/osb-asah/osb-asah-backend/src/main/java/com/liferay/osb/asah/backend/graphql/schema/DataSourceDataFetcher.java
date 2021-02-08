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

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.DataSource;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "dataSource", typeName = "QueryType")
public class DataSourceDataFetcher implements DataFetcher<DataSourceDTO> {

	@Override
	public DataSourceDTO get(DataFetchingEnvironment dataFetchingEnvironment) {
		String dataSourceId = dataFetchingEnvironment.getArgument(
			"dataSourceId");

		DataSource dataSource = _dataSourceDog.fetchDataSource(
			Long.valueOf(dataSourceId));

		if (dataSource != null) {
			return new DataSourceDTO(dataSource);
		}

		return null;
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

}