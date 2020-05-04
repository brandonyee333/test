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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "dataControlTasks", typeName = "MutationType")
public class DataControlTaskMutationDataFetcher
	implements DataFetcher<Boolean> {

	@Override
	public Boolean get(DataFetchingEnvironment dataFetchingEnvironment) {
		return _dataControlTaskDog.addDataControlTasks(
			dataFetchingEnvironment.getArgument("emailAddresses"),
			_getPath(dataFetchingEnvironment.getArgument("fileName")),
			dataFetchingEnvironment.getArgument("ownerId"),
			dataFetchingEnvironment.getArgument("types"));
	}

	private Path _getPath(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return null;
		}

		return Paths.get(_tempPathName, fileName);
	}

	@Autowired
	private DataControlTaskDog _dataControlTaskDog;

	@Value("${osb.asah.backend.temp.path:/temp}")
	private String _tempPathName;

}