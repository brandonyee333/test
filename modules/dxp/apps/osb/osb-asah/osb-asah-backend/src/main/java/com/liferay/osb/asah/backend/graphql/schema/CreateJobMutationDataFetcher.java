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

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			dataFetchingEnvironment.getArgument("active"),
			_createJobParameters(dataFetchingEnvironment),
			JobTrainingFrequency.valueOf(
				dataFetchingEnvironment.getArgument("trainingFrequency")),
			JobTrainingPeriod.valueOf(
				dataFetchingEnvironment.getArgument("trainingPeriod")),
			JobType.valueOf(dataFetchingEnvironment.getArgument("type")),
			dataFetchingEnvironment.getArgument("name"));
	}

	private List<JobParameter> _createJobParameters(
		DataFetchingEnvironment dataFetchingEnvironment) {

		List<Map<String, String>> parameters =
			dataFetchingEnvironment.getArgument("parameters");

		Stream<Map<String, String>> stream = parameters.stream();

		return stream.map(
			map -> new JobParameter(map.get("name"), map.get("value"))
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private JobDog _jobDog;

}