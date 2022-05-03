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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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
 * @author Marcellus Tavares
 */
@Table
public class BQMembershipChange implements Persistable<Long> {

	public BQMembershipChange() {
	}

	public BQMembershipChange(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQMembershipChange)) {
			return false;
		}

		BQMembershipChange bqMembershipChange = (BQMembershipChange)obj;

		if (Objects.equals(_id, bqMembershipChange._id) &&
			Objects.equals(
				_individualDeleted, bqMembershipChange._individualDeleted) &&
			Objects.equals(
				_individualEmail, bqMembershipChange._individualEmail) &&
			Objects.equals(_individualId, bqMembershipChange._individualId) &&
			Objects.equals(
				_individualName, bqMembershipChange._individualName) &&
			Objects.equals(
				_individualSegmentId,
				bqMembershipChange._individualSegmentId) &&
			Objects.equals(
				_individualsCount, bqMembershipChange._individualsCount) &&
			Objects.equals(_joinedDate, bqMembershipChange._joinedDate) &&
			Objects.equals(
				_knownIndividualsCount,
				bqMembershipChange._knownIndividualsCount) &&
			Objects.equals(_modifiedDate, bqMembershipChange._modifiedDate) &&
			Objects.equals(_operation, bqMembershipChange._operation)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
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
	public Long getIndividualSegmentId() {
		return _individualSegmentId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("dateFirst")
	public Date getJoinedDate() {
		if (_joinedDate == null) {
			return null;
		}

		return new Date(_joinedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("dateChanged")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOperation() {
		return _operation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _individualDeleted, _individualEmail, _individualId,
			_individualName, _individualSegmentId, _individualsCount,
			_joinedDate, _knownIndividualsCount, _modifiedDate, _operation);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
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

	public void setJoinedDate(Date joinedDate) {
		if (joinedDate != null) {
			_joinedDate = new Date(joinedDate.getTime());
		}
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setOperation(String operation) {
		_operation = operation;
	}

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
	private Date _joinedDate;

	@Transient
	private Long _knownIndividualsCount;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _operation;

}