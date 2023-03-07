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

/**
 * @author Marcellus Tavares
 */
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
			Objects.equals(
				_identitiesCount, bqMembershipChange._identitiesCount) &&
			Objects.equals(
				_individualsCount, bqMembershipChange._individualsCount) &&
			Objects.equals(_segmentId, bqMembershipChange._segmentId)) {

			return true;
		}

		return false;
	}

	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public Long getIdentitiesCount() {
		return _identitiesCount;
	}

	public Long getIndividualsCount() {
		return _individualsCount;
	}

	public Long getSegmentId() {
		return _segmentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_identitiesCount, _individualsCount, _createDate, _segmentId);
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setIdentitiesCount(Long identitiesCount) {
		_identitiesCount = identitiesCount;
	}

	public void setIndividualsCount(Long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setSegmentId(Long segmentId) {
		_segmentId = segmentId;
	}

	private Date _createDate;
	private Long _identitiesCount;
	private Long _individualsCount;
	private Long _segmentId;

}