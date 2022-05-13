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

		if (Objects.equals(_createDate, bqMembershipChange._createDate) &&
			Objects.equals(_id, bqMembershipChange._id) &&
			Objects.equals(
				_individualSegmentId,
				bqMembershipChange._individualSegmentId) &&
			Objects.equals(
				_individualsCount, bqMembershipChange._individualsCount) &&
			Objects.equals(
				_knownIndividualsCount,
				bqMembershipChange._knownIndividualsCount)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
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
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _individualSegmentId, _individualsCount,
			_knownIndividualsCount, _createDate);
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

	@Transient
	private Date _createDate;

	@Transient
	private Long _id;

	@Transient
	private Long _individualsCount;

	@Transient
	private Long _individualSegmentId;

	@Transient
	private Boolean _isNew;

	@Transient
	private Long _knownIndividualsCount;

}