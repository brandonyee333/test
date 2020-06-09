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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class JobRun {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JobRun)) {
			return false;
		}

		JobRun jobRun = (JobRun)obj;

		if (Objects.equals(_completedDate, jobRun._completedDate) &&
			Objects.equals(_id, jobRun._id) &&
			Objects.equals(_jobRunStatus, jobRun._jobRunStatus)) {

			return true;
		}

		return false;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCompletedDate() {
		if (_completedDate == null) {
			return null;
		}

		return new Date(_completedDate.getTime());
	}

	@JsonIgnore
	public String getCompletedDateISO() {
		if (_completedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_completedDate);
	}

	public String getId() {
		return _id;
	}

	@JsonProperty("status")
	public JobRunStatus getJobRunStatus() {
		return _jobRunStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_completedDate, _id, _jobRunStatus);
	}

	public void setCompletedDate(Date completedDate) {
		if (completedDate != null) {
			_completedDate = new Date(completedDate.getTime());
		}
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJobRunStatus(JobRunStatus jobRunStatus) {
		_jobRunStatus = jobRunStatus;
	}

	private Date _completedDate;
	private String _id;
	private JobRunStatus _jobRunStatus;

}