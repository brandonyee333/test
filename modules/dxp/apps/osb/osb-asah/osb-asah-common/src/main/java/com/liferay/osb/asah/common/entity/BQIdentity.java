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

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQIdentity implements Persistable<String> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQIdentity)) {
			return false;
		}

		BQIdentity bqIdentity = (BQIdentity)obj;

		if (Objects.equals(_userId, bqIdentity._userId)) {
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
	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	@Override
	public String getId() {
		return _userId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_userId);
	}

	@Override
	public boolean isNew() {
		if ((_userId == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	@Transient
	private Date _createDate;

	@Transient
	private String _emailAddressHashed;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _userId;

}