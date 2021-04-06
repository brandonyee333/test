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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.model.JobParameter;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
@GraphQLType("Job")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobDTO {

	public JobDTO(Job job) {
		_job = job;
	}

	public String getId() {
		return String.valueOf(_job.getId());
	}

	@GraphQLProperty("parameters")
	public List<JobParameter> getJobParameters() {
		return new ArrayList<>(_job.getJobParameters());
	}

	@GraphQLProperty("runDataPeriod")
	public JobRunDataPeriod getJobRunDataPeriod() {
		return _job.getJobRunDataPeriod();
	}

	@GraphQLProperty("runFrequency")
	public JobRunFrequency getJobRunFrequency() {
		return _job.getJobRunFrequency();
	}

	@GraphQLProperty("type")
	public JobType getJobType() {
		return _job.getJobType();
	}

	public String getName() {
		return _job.getName();
	}

	private final Job _job;

}