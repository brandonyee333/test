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

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
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

		if (Objects.equals(_active, job._active) &&
			Objects.equals(_id, job._id) &&
			Objects.equals(_jobParameters, job._jobParameters) &&
			Objects.equals(_jobTrainingFrequency, job._jobTrainingFrequency) &&
			Objects.equals(_jobTrainingPeriod, job._jobTrainingPeriod) &&
			Objects.equals(_jobType, job._jobType) &&
			Objects.equals(_name, job._name)) {

			return true;
		}

		return false;
	}

	public String getId() {
		return _id;
	}

	@JsonProperty("parameters")
	public List<JobParameter> getJobParameters() {
		return _jobParameters;
	}

	@JsonProperty("trainingFrequency")
	public JobTrainingFrequency getJobTrainingFrequency() {
		return _jobTrainingFrequency;
	}

	@JsonProperty("trainingPeriod")
	public JobTrainingPeriod getJobTrainingPeriod() {
		return _jobTrainingPeriod;
	}

	@JsonProperty("type")
	public JobType getJobType() {
		return _jobType;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_active, _id, _jobParameters, _jobTrainingFrequency,
			_jobTrainingPeriod, _jobType, _name);
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJobParameters(List<JobParameter> jobParameters) {
		_jobParameters = jobParameters;
	}

	public void setJobTrainingFrequency(
		JobTrainingFrequency jobTrainingFrequency) {

		_jobTrainingFrequency = jobTrainingFrequency;
	}

	public void setJobTrainingPeriod(JobTrainingPeriod jobTrainingPeriod) {
		_jobTrainingPeriod = jobTrainingPeriod;
	}

	public void setJobType(JobType jobType) {
		_jobType = jobType;
	}

	public void setName(String name) {
		_name = name;
	}

	private boolean _active;
	private String _id;
	private List<JobParameter> _jobParameters = new ArrayList<>();
	private JobTrainingFrequency _jobTrainingFrequency;
	private JobTrainingPeriod _jobTrainingPeriod;
	private JobType _jobType;
	private String _name;

}