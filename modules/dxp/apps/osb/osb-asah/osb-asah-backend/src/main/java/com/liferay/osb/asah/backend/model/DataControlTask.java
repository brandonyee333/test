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

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Objects;

/**
 * @author Matthew Kong
 */
public final class DataControlTask {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataControlTask)) {
			return false;
		}

		DataControlTask dataControlTask = (DataControlTask)obj;

		if (Objects.equals(_batchId, dataControlTask._batchId) &&
			Objects.equals(_completeDate, dataControlTask._completeDate) &&
			Objects.equals(_createDate, dataControlTask._createDate) &&
			Objects.equals(_emailAddress, dataControlTask._emailAddress) &&
			Objects.equals(_id, dataControlTask._id) &&
			Objects.equals(_startDate, dataControlTask._startDate) &&
			Objects.equals(_status, dataControlTask._status) &&
			Objects.equals(_type, dataControlTask._type)) {

			return true;
		}

		return false;
	}

	public String getBatchId() {
		return _batchId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCompleteDate() {
		if (_completeDate == null) {
			return null;
		}

		return new Date(_completeDate.getTime());
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getStartDate() {
		if (_startDate == null) {
			return null;
		}

		return new Date(_startDate.getTime());
	}

	public String getStatus() {
		return _status;
	}

	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_batchId, _completeDate, _createDate, _emailAddress, _id, _type,
			_status);
	}

	public void setBatchId(String batchId) {
		_batchId = batchId;
	}

	public void setCompleteDate(Date completeDate) {
		if (completeDate != null) {
			_completeDate = new Date(completeDate.getTime());
		}
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setStartDate(Date startDate) {
		if (startDate != null) {
			_startDate = new Date(startDate.getTime());
		}
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _batchId;
	private Date _completeDate;
	private Date _createDate;
	private String _emailAddress;
	private String _id;
	private Date _startDate;
	private String _status;
	private String _type;

}