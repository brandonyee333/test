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
import com.liferay.osb.asah.common.model.JobParameter;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "updateJob", typeName = "MutationType")
public class UpdateJobMutationDataFetcher implements DataFetcher<Job> {

	@Override
	public Job get(DataFetchingEnvironment dataFetchingEnvironment) {
		return _jobDog.updateJob(
			dataFetchingEnvironment.getArgument("jobId"),
			ListUtil.map(
				dataFetchingEnvironment.getArgument("parameters"),
				JobParameter::of),
			JobRunDataPeriod.valueOf(
				dataFetchingEnvironment.getArgument("runDataPeriod")),
			JobRunFrequency.valueOf(
				dataFetchingEnvironment.getArgument("runFrequency")),
			dataFetchingEnvironment.getArgument("name"),
			dataFetchingEnvironment.getArgument("runNow"));
	}

	@Autowired
	private JobDog _jobDog;

}