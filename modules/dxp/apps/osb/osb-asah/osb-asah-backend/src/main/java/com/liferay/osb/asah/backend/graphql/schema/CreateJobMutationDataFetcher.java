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
import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobParameter;
import com.liferay.osb.asah.backend.model.JobTrainingFrequency;
import com.liferay.osb.asah.backend.model.JobTrainingPeriod;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "createJob", typeName = "MutationType")
public class CreateJobMutationDataFetcher implements DataFetcher<Job> {

	@Override
	public Job get(DataFetchingEnvironment dataFetchingEnvironment) {
		return _jobDog.addJob(
			ListUtil.map(
				dataFetchingEnvironment.getArgument("parameters"),
				JobParameter::of),
			JobTrainingFrequency.valueOf(
				dataFetchingEnvironment.getArgument("trainingFrequency")),
			JobTrainingPeriod.valueOf(
				dataFetchingEnvironment.getArgument("trainingPeriod")),
			JobType.valueOf(dataFetchingEnvironment.getArgument("type")),
			dataFetchingEnvironment.getArgument("name"));
	}

	@Autowired
	private JobDog _jobDog;

}