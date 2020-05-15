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

import com.liferay.osb.asah.backend.dog.DataControlTaskDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.backend.model.DataControlTask;
import com.liferay.osb.asah.backend.model.ResultBag;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "dataControlTasks", typeName = "QueryType")
public class DataControlTaskBagDataFetcher
	implements DataFetcher<ResultBag<DataControlTask>> {

	@Override
	public ResultBag<DataControlTask> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		return _dataControlTaskDog.getDataControlTaskResultBag(
			dataFetchingEnvironment.getArgument("batchId"),
			dataFetchingEnvironment.getArgument("keywords"),
			dataFetchingEnvironment.getArgument("rangeKey"),
			dataFetchingEnvironment.getArgument("size"),
			dataFetchingEnvironment.getArgument("sort"),
			dataFetchingEnvironment.getArgument("start"),
			dataFetchingEnvironment.getArgument("statuses"),
			dataFetchingEnvironment.getArgument("types"));
	}

	@Autowired
	private DataControlTaskDog _dataControlTaskDog;

}