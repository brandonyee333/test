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
public class BQAccountGroup implements Persistable<String> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQAccountGroup)) {
			return false;
		}

		BQAccountGroup bqAccountGroup = (BQAccountGroup)obj;

		if (Objects.equals(_accountGroupId, bqAccountGroup._accountGroupId) &&
			Objects.equals(_createDate, bqAccountGroup._createDate) &&
			Objects.equals(
				_defaultAccountGroup, bqAccountGroup._defaultAccountGroup) &&
			Objects.equals(_description, bqAccountGroup._description) &&
			Objects.equals(_id, bqAccountGroup._id) &&
			Objects.equals(_modifiedDate, bqAccountGroup._modifiedDate) &&
			Objects.equals(_name, bqAccountGroup._name) &&
			Objects.equals(_type, bqAccountGroup._type)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getAccountGroupId() {
		return _accountGroupId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Boolean getDefaultAccountGroup() {
		return _defaultAccountGroup;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDescription() {
		return _description;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_accountGroupId, _createDate, _defaultAccountGroup, _description,
			_id, _modifiedDate, _name, _type);
	}

	public Boolean isDefaultAccountGroup() {
		return _defaultAccountGroup;
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAccountGroupId(Long accountGroupId) {
		_accountGroupId = accountGroupId;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDefaultAccountGroup(Boolean defaultAccountGroup) {
		_defaultAccountGroup = defaultAccountGroup;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private Long _accountGroupId;

	@Transient
	private Date _createDate;

	@Transient
	private Boolean _defaultAccountGroup;

	@Transient
	private String _description;

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private String _type;

}