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

import com.liferay.osb.asah.backend.dto.JobRunDTO;
import com.liferay.osb.asah.common.dog.JobRunDog;
import com.liferay.osb.asah.common.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@GraphQLTypeWiring(fieldName = "jobRuns", typeName = "QueryType")
public class JobRunBagDataFetcher implements DataFetcher<ResultBag<JobRunDTO>> {

	@Override
	public ResultBag<JobRunDTO> get(
		DataFetchingEnvironment dataFetchingEnvironment) {

		int size = dataFetchingEnvironment.getArgument("size");
		int start = dataFetchingEnvironment.getArgument("start");

		Page<JobRun> jobRunPage = _jobRunDog.getJobRunPage(
			Long.valueOf(dataFetchingEnvironment.getArgument("jobId")),
			start / size, size,
			Sort.of(dataFetchingEnvironment.getArgument("sort")));

		return new ResultBag<>(
			ListUtil.map(jobRunPage.getContent(), JobRunDTO::new),
			jobRunPage.getTotalElements());
	}

	@Autowired
	private JobRunDog _jobRunDog;

}