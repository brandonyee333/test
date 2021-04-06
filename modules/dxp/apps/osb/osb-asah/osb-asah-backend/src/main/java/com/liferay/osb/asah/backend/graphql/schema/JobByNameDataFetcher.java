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

import com.liferay.osb.asah.backend.dto.JobDTO;
import com.liferay.osb.asah.common.dog.JobDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "jobByName", typeName = "QueryType")
public class JobByNameDataFetcher implements DataFetcher<JobDTO> {

	@Override
	public JobDTO get(DataFetchingEnvironment dataFetchingEnvironment) {
		return new JobDTO(
			_jobDog.fetchJob(dataFetchingEnvironment.getArgument("name")));
	}

	@Autowired
	private JobDog _jobDog;

}