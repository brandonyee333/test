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

package com.liferay.osb.asah.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataExportTask {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataExportTask)) {
			return false;
		}

		DataExportTask dataExportTask = (DataExportTask)obj;

		if (Objects.equals(_completedDate, dataExportTask._completedDate) &&
			Objects.equals(_createdDate, dataExportTask._createdDate) &&
			Objects.equals(
				_dataExportTaskStatus, dataExportTask._dataExportTaskStatus) &&
			Objects.equals(
				_dataExportTaskType, dataExportTask._dataExportTaskType) &&
			Objects.equals(_id, dataExportTask._id) &&
			Objects.equals(_startedDate, dataExportTask._startedDate)) {

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

	@JsonProperty("status")
	public DataExportTaskStatus getDataExportTaskStatus() {
		return _dataExportTaskStatus;
	}

	@JsonProperty("type")
	public DataExportTaskType getDataExportTaskType() {
		return _dataExportTaskType;
	}

	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getStartedDate() {
		if (_startedDate == null) {
			return null;
		}

		return new Date(_startedDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_completedDate, _createdDate, _dataExportTaskStatus, _id,
			_startedDate);
	}

	public void setCompletedDate(Date completedDate) {
		if (completedDate != null) {
			_completedDate = new Date(completedDate.getTime());
		}
	}

	public void setCreatedDate(Date createdDate) {
		if (createdDate != null) {
			_createdDate = new Date(createdDate.getTime());
		}
	}

	public void setDataExportTaskStatus(
		DataExportTaskStatus dataExportTaskStatus) {

		_dataExportTaskStatus = dataExportTaskStatus;
	}

	public void setDataExportTaskType(DataExportTaskType dataExportTaskType) {
		_dataExportTaskType = dataExportTaskType;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setStartedDate(Date startedDate) {
		if (startedDate != null) {
			_startedDate = new Date(startedDate.getTime());
		}
	}

	private Date _completedDate;
	private Date _createdDate;
	private DataExportTaskStatus _dataExportTaskStatus;
	private DataExportTaskType _dataExportTaskType;
	private String _id;
	private Date _startedDate;

}