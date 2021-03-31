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

import com.liferay.osb.asah.common.dog.JobDog;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "jobs", typeName = "QueryType")
public class JobBagDataFetcher implements DataFetcher<ResultBag<Job>> {

	@Override
	public ResultBag<Job> get(DataFetchingEnvironment dataFetchingEnvironment) {
		return _jobDog.getJobResultBag(
			dataFetchingEnvironment.getArgument("keywords"),
			dataFetchingEnvironment.getArgument("size"),
			Sort.of(dataFetchingEnvironment.getArgument("sort")),
			dataFetchingEnvironment.getArgument("start"));
	}

	@Autowired
	private JobDog _jobDog;

}