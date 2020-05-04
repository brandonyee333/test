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

package com.liferay.osb.asah.backend.model.ml;

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
			Objects.equals(_cronExpression, job._cronExpression) &&
			Objects.equals(_id, job._id) &&
			Objects.equals(_jobParameters, job._jobParameters) &&
			Objects.equals(_name, job._name)) {

			return true;
		}

		return false;
	}

	public String getCronExpression() {
		return _cronExpression;
	}

	public String getId() {
		return _id;
	}

	@JsonProperty("parameters")
	public List<JobParameter> getJobParameters() {
		return _jobParameters;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_active, _cronExpression, _id, _jobParameters, _name);
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setCronExpression(String cronExpression) {
		_cronExpression = cronExpression;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJobParameters(List<JobParameter> jobParameters) {
		_jobParameters = jobParameters;
	}

	public void setName(String name) {
		_name = name;
	}

	private boolean _active;
	private String _cronExpression;
	private String _id;
	private List<JobParameter> _jobParameters = new ArrayList<>();
	private String _name;

}