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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class BQMembershipChange {

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
				_identitiesCount, bqMembershipChange._identitiesCount) &&
			Objects.equals(
				_knownIdentitiesCount,
				bqMembershipChange._knownIdentitiesCount) &&
			Objects.equals(_segmentId, bqMembershipChange._segmentId)) {

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
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getIdentitiesCount() {
		return _identitiesCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getKnownIdentitiesCount() {
		return _knownIdentitiesCount;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getSegmentId() {
		return _segmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _identitiesCount, _knownIdentitiesCount, _createDate,
			_segmentId);
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIdentitiesCount(Long identitiesCount) {
		_identitiesCount = identitiesCount;
	}

	public void setKnownIdentitiesCount(Long knownIdentitiesCount) {
		_knownIdentitiesCount = knownIdentitiesCount;
	}

	public void setSegmentId(Long segmentId) {
		_segmentId = segmentId;
	}

	@Transient
	private Date _createDate;

	@Transient
	private Long _id;

	@Transient
	private Long _identitiesCount;

	@Transient
	private Long _knownIdentitiesCount;

	@Transient
	private Long _segmentId;

}