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

package com.liferay.osb.asah.common.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Inácio Nery
 */
@Table
public class BQMembership implements Persistable<Long> {

	public BQMembership() {
	}

	public BQMembership(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQMembership)) {
			return false;
		}

		BQMembership bqMembership = (BQMembership)obj;

		if (Objects.equals(_createDate, bqMembership._createDate) &&
			Objects.equals(_id, bqMembership._id) &&
			Objects.equals(_individualId, bqMembership._individualId) &&
			Objects.equals(
				_individualSegmentId, bqMembership._individualSegmentId) &&
			Objects.equals(_modifiedDate, bqMembership._modifiedDate) &&
			Objects.equals(_removedDate, bqMembership._removedDate) &&
			Objects.equals(_status, bqMembership._status)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getIndividualId() {
		return _individualId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getIndividualSegmentId() {
		return _individualSegmentId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("removedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateRemoved")
	public Date getRemovedDate() {
		if (_removedDate == null) {
			return null;
		}

		return new Date(_removedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getStatus() {
		return _status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_createDate, _id, _individualId, _individualSegmentId,
			_modifiedDate, _removedDate, _status);
	}

	@JsonIgnore
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

	public void setId(Long id) {
		_id = id;
	}

	public void setIndividualId(Long individualId) {
		_individualId = individualId;
	}

	public void setIndividualSegmentId(Long individualSegmentId) {
		_individualSegmentId = individualSegmentId;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setRemovedDate(Date removedDate) {
		if (removedDate != null) {
			_removedDate = new Date(removedDate.getTime());
		}
	}

	public void setStatus(String status) {
		_status = status;
	}

	@Transient
	private Date _createDate;

	@Transient
	private Long _id;

	@Transient
	private Long _individualId;

	@Transient
	private Long _individualSegmentId;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate;

	@Transient
	private Date _removedDate;

	@Transient
	private String _status;

}