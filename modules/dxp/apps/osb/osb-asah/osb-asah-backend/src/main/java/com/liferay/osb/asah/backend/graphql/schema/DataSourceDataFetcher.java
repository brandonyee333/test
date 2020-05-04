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

import com.liferay.osb.asah.backend.dog.DataSourceDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.DataSource;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;

import org.elasticsearch.search.sort.FieldSortBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 * @author André Miranda
 */
@Component
@GraphQLTypeWiring(fieldName = "dataSources", typeName = "QueryType")
public class DataSourceDataFetcher implements DataFetcher<List<DataSource>> {

	@Override
	public List<DataSource> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		FieldSortBuilder fieldSortBuilder = null;

		Map<String, String> sort = dataFetchingEnvironment.getArgument("sort");

		if (sort != null) {
			fieldSortBuilder = SortBuilderUtil.fieldSort(sort);
		}

		return _dataSourceDog.getDataSources(
			dataFetchingEnvironment.getArgument("credentialsType"),
			fieldSortBuilder, dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("type"));
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

}