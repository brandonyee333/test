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

import com.liferay.osb.asah.backend.dog.JobDog;
import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "deleteJobs", typeName = "MutationType")
public class DeleteJobMutationDataFetcher
	extends BaseExperimentDataFetcher implements DataFetcher<List<Boolean>> {

	@Override
	public List<Boolean> get(DataFetchingEnvironment dataFetchingEnvironment) {
		return _jobDog.deleteJobs(
			dataFetchingEnvironment.getArgument("jobIds"));
	}

	@Autowired
	private JobDog _jobDog;

}