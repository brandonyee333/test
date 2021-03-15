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

package com.liferay.osb.asah.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
@GraphQLType
public class Job {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Job)) {
			return false;
		}

		Job job = (Job)obj;

		if (equalsJob(job)) {
			Class<?> clazz = obj.getClass();

			if (!clazz.isInstance(this) && Job.class.isAssignableFrom(clazz)) {
				return obj.equals(this);
			}

			return true;
		}

		return false;
	}

	@JsonProperty("osbAsahTaskId")
	public String getAsahTaskId() {
		return _asahTaskId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCreatedDate() {
		if (_createdDate == null) {
			return null;
		}

		return new Date(_createdDate.getTime());
	}

	public String getId() {
		return _id;
	}

	@GraphQLProperty("parameters")
	@JsonProperty("parameters")
	public List<JobParameter> getJobParameters() {
		return _jobParameters;
	}

	@GraphQLProperty("runDataPeriod")
	@JsonProperty("runDataPeriod")
	public JobRunDataPeriod getJobRunDataPeriod() {
		return _jobRunDataPeriod;
	}

	@GraphQLProperty("runFrequency")
	@JsonProperty("runFrequency")
	public JobRunFrequency getJobRunFrequency() {
		return _jobRunFrequency;
	}

	@GraphQLProperty("type")
	@JsonProperty("type")
	public JobType getJobType() {
		return _jobType;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getLastUpdatedDate() {
		if (_lastUpdatedDate == null) {
			return null;
		}

		return new Date(_lastUpdatedDate.getTime());
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_createdDate, _id, _jobParameters, _jobRunDataPeriod,
			_jobRunFrequency, _jobType, _lastUpdatedDate, _name);
	}

	public void setAsahTaskId(String asahTaskId) {
		_asahTaskId = asahTaskId;
	}

	public void setCreatedDate(Date createdDate) {
		if (createdDate != null) {
			_createdDate = new Date(createdDate.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJobParameters(List<JobParameter> jobParameters) {
		_jobParameters = jobParameters;
	}

	public void setJobRunDataPeriod(JobRunDataPeriod jobRunDataPeriod) {
		_jobRunDataPeriod = jobRunDataPeriod;
	}

	public void setJobRunFrequency(JobRunFrequency jobRunFrequency) {
		_jobRunFrequency = jobRunFrequency;
	}

	public void setJobType(JobType jobType) {
		_jobType = jobType;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		if (lastUpdatedDate != null) {
			_lastUpdatedDate = new Date(lastUpdatedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	protected boolean equalsJob(Job job) {
		if (Objects.equals(_createdDate, job._createdDate) &&
			Objects.equals(_id, job._id) &&
			Objects.equals(_jobParameters, job._jobParameters) &&
			Objects.equals(_jobRunDataPeriod, job._jobRunDataPeriod) &&
			Objects.equals(_jobRunFrequency, job._jobRunFrequency) &&
			Objects.equals(_jobType, job._jobType) &&
			Objects.equals(_lastUpdatedDate, job._lastUpdatedDate) &&
			Objects.equals(_name, job._name)) {

			return true;
		}

		return false;
	}

	private String _asahTaskId;
	private Date _createdDate;
	private String _id;
	private List<JobParameter> _jobParameters = new ArrayList<>();
	private JobRunDataPeriod _jobRunDataPeriod;
	private JobRunFrequency _jobRunFrequency;
	private JobType _jobType;
	private Date _lastUpdatedDate;
	private String _name;

}