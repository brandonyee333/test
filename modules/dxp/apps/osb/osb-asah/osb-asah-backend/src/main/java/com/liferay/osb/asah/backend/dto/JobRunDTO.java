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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;

import java.util.Date;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
@GraphQLType("JobRun")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobRunDTO {

	public JobRunDTO(JobRun jobRun) {
		_jobRun = jobRun;
	}

	@GraphQLProperty("completedDate")
	public String getCompletedDateISO() {
		Date completedDate = _jobRun.getCompletedDate();

		if (completedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(completedDate);
	}

	public Map<String, Object> getContext() {
		return JSONUtil.toMap(_jobRun.getContextJSONObject());
	}

	public String getId() {
		return String.valueOf(_jobRun.getId());
	}

	@GraphQLProperty("status")
	public JobRunStatus getJobRunStatus() {
		return _jobRun.getJobRunStatus();
	}

	private final JobRun _jobRun;

}