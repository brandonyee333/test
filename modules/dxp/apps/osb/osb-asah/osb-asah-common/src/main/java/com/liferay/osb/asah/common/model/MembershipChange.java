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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @author Marcellus Tavares
 */
@Table
public class MembershipChange implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MembershipChange)) {
			return false;
		}

		MembershipChange membershipChange = (MembershipChange)obj;

		if (Objects.equals(_modifiedDate, membershipChange._modifiedDate) &&
			Objects.equals(_createDate, membershipChange._createDate) &&
			Objects.equals(_id, membershipChange._id) &&
			Objects.equals(
				_individualDeleted, membershipChange._individualDeleted) &&
			Objects.equals(
				_individualEmail, membershipChange._individualEmail) &&
			Objects.equals(_individualId, membershipChange._individualId) &&
			Objects.equals(_individualName, membershipChange._individualName) &&
			Objects.equals(
				_individualSegmentId, membershipChange._individualSegmentId) &&
			Objects.equals(
				_individualsCount, membershipChange._individualsCount) &&
			Objects.equals(
				_knownIndividualsCount,
				membershipChange._knownIndividualsCount) &&
			Objects.equals(_operation, membershipChange._operation)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateChanged")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateFirst")
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
	public Boolean getIndividualDeleted() {
		return _individualDeleted;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getIndividualEmail() {
		return _individualEmail;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getIndividualId() {
		return _individualId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getIndividualName() {
		return _individualName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getIndividualSegmentId() {
		return _individualSegmentId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOperation() {
		return _operation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_modifiedDate, _createDate, _id, _individualDeleted, _individualEmail,
			_individualId, _individualName, _individualSegmentId,
			_individualsCount, _knownIndividualsCount, _operation);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIndividualDeleted(Boolean individualDeleted) {
		_individualDeleted = individualDeleted;
	}

	public void setIndividualEmail(String individualEmail) {
		_individualEmail = individualEmail;
	}

	public void setIndividualId(Long individualId) {
		_individualId = individualId;
	}

	public void setIndividualName(String individualName) {
		_individualName = individualName;
	}

	public void setIndividualsCount(Long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setIndividualSegmentId(Long individualSegmentId) {
		_individualSegmentId = individualSegmentId;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

	@Transient
	private Date _modifiedDate;

	@Transient
	private Date _createDate;

	@Transient
	private Long _id;

	@Transient
	private Boolean _individualDeleted;

	@Transient
	private String _individualEmail;

	@Transient
	private Long _individualId;

	@Transient
	private String _individualName;

	@Transient
	private Long _individualsCount;

	@Transient
	private Long _individualSegmentId;

	@Transient
	private Boolean _isNew;

	@Transient
	private Long _knownIndividualsCount;

	@Transient
	private String _operation;

}