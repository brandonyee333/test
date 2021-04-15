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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Matthew Kong
 */
@Table
public class Suppression implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Suppression)) {
			return false;
		}

		Suppression suppression = (Suppression)obj;

		if (Objects.equals(_createDate, suppression._createDate) &&
			Objects.equals(
				_dataControlTaskBatchId, suppression._dataControlTaskBatchId) &&
			Objects.equals(
				_dataControlTaskCreateDate,
				suppression._dataControlTaskCreateDate) &&
			Objects.equals(
				_dataControlTaskStatus, suppression._dataControlTaskStatus) &&
			Objects.equals(_emailAddress, suppression._emailAddress) &&
			Objects.equals(_id, suppression._id)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
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

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataControlTaskBatchId() {
		return _dataControlTaskBatchId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getDataControlTaskCreateDate() {
		if (_dataControlTaskCreateDate == null) {
			return null;
		}

		return new Date(_dataControlTaskCreateDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDataControlTaskStatus() {
		return _dataControlTaskStatus;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddress() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_createDate, _emailAddress, _id);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataControlTaskBatchId(Long dataControlTaskBatchId) {
		_dataControlTaskBatchId = dataControlTaskBatchId;
	}

	public void setDataControlTaskCreateDate(Date dataControlTaskCreateDate) {
		if (dataControlTaskCreateDate != null) {
			_dataControlTaskCreateDate = new Date(
				dataControlTaskCreateDate.getTime());
		}
	}

	public void setDataControlTaskStatus(String dataControlTaskStatus) {
		_dataControlTaskStatus = dataControlTaskStatus;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	@Transient
	private Date _createDate;

	@Transient
	private Long _dataControlTaskBatchId;

	@Transient
	private Date _dataControlTaskCreateDate;

	@Transient
	private String _dataControlTaskStatus;

	@Transient
	private String _emailAddress;

	@Transient
	private String _emailAddressHashed;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

}