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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author Rachael Koestartyo
 */
public class Field {

	public Field() {
	}

	public Field(Field field) {
		_context = field.getContext();
		_dataSourceId = field.getDataSourceId();
		_dataSourceName = field.getDataSourceName();
		_fieldType = field.getFieldType();
		_id = field.getId();
		_modifiedDate = field.getModifiedDate();
		_name = field.getName();
		_ownerId = field.getOwnerId();
		_ownerType = field.getOwnerType();
		_sourceName = field.getSourceName();
		_value = field.getValue();
	}

	public Field(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Field)) {
			return false;
		}

		Field field = (Field)obj;

		if (Objects.equals(_context, field._context) &&
			Objects.equals(_dataSourceId, field._dataSourceId) &&
			Objects.equals(_dataSourceName, field._dataSourceName) &&
			Objects.equals(_fieldType, field._fieldType) &&
			Objects.equals(_id, field._id) &&
			Objects.equals(_modifiedDate, field._modifiedDate) &&
			Objects.equals(_name, field._name) &&
			Objects.equals(_ownerId, field._ownerId) &&
			Objects.equals(_ownerType, field._ownerType) &&
			Objects.equals(_sourceName, field._sourceName) &&
			Objects.equals(_value, field._value)) {

			return true;
		}

		return false;
	}

	public String getContext() {
		return _context;
	}

	public Long getDataSourceId() {
		return _dataSourceId;
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getFieldType() {
		return _fieldType;
	}

	public Long getId() {
		return _id;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getName() {
		return _name;
	}

	public Long getOwnerId() {
		return _ownerId;
	}

	public String getOwnerType() {
		return _ownerType;
	}

	public String getSourceName() {
		return _sourceName;
	}

	public Object getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_context, _dataSourceId, _fieldType, _id, _modifiedDate, _name,
			_ownerId, _ownerType, _sourceName, _value);
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOwnerId(Long ownerId) {
		_ownerId = ownerId;
	}

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setSourceName(String sourceName) {
		_sourceName = sourceName;
	}

	public void setValue(Object value) {
		_value = value;
	}

	private String _context;
	private Long _dataSourceId;
	private String _dataSourceName;
	private String _fieldType;
	private Long _id;
	private Date _modifiedDate = new Date();
	private String _name;
	private Long _ownerId;
	private String _ownerType;
	private String _sourceName;
	private Object _value;

}