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

import java.util.Date;
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
public class Membership implements Persistable<Long> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Membership)) {
			return false;
		}

		Membership membership = (Membership)obj;

		if (Objects.equals(_createDate, membership._createDate) &&
			Objects.equals(_id, membership._id) &&
			Objects.equals(_individualId, membership._individualId) &&
			Objects.equals(
				_individualSegmentId, membership._individualSegmentId) &&
			Objects.equals(_modifiedDate, membership._modifiedDate) &&
			Objects.equals(_removedDate, membership._removedDate) &&
			Objects.equals(_status, membership._status)) {

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
	public Long getIndividualId() {
		return _individualId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getIndividualSegmentId() {
		return _individualSegmentId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
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