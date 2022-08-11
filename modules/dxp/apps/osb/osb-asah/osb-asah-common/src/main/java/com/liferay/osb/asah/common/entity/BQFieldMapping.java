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
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class BQFieldMapping {

	public BQFieldMapping() {
	}

	public BQFieldMapping(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQFieldMapping)) {
			return false;
		}

		BQFieldMapping bqFieldMapping = (BQFieldMapping)obj;

		if (Objects.equals(_context, bqFieldMapping._context) &&
			Objects.equals(_dataSourceIds, bqFieldMapping._dataSourceIds) &&
			Objects.equals(_displayName, bqFieldMapping._displayName) &&
			Objects.equals(_displayType, bqFieldMapping._displayType) &&
			Objects.equals(_fieldName, bqFieldMapping._fieldName) &&
			Objects.equals(_fieldType, bqFieldMapping._fieldType) &&
			Objects.equals(_modifiedDate, bqFieldMapping._modifiedDate) &&
			Objects.equals(_ownerType, bqFieldMapping._ownerType) &&
			Objects.equals(_repeatable, bqFieldMapping._repeatable)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getContext() {
		return _context;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getDataSourceIds() {
		return _dataSourceIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDisplayName() {
		return _displayName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDisplayType() {
		return _displayType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFieldName() {
		return _fieldName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFieldType() {
		return _fieldType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getOwnerType() {
		return _ownerType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("repeatable_")
	public Boolean getRepeatable() {
		return _repeatable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_context, _dataSourceIds, _displayName, _displayType, _fieldName,
			_fieldType, _modifiedDate, _ownerType, _repeatable);
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setDataSourceIds(Set<Long> dataSourceIds) {
		_dataSourceIds = dataSourceIds;
	}

	public void setDisplayName(String displayName) {
		_displayName = displayName;
	}

	public void setDisplayType(String displayType) {
		_displayType = displayType;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setFieldType(String fieldType) {
		_fieldType = fieldType;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setRepeatable(Boolean repeatable) {
		_repeatable = repeatable;
	}

	@Transient
	private String _context;

	@Transient
	private Set<Long> _dataSourceIds;

	@Transient
	private String _displayName;

	@Transient
	private String _displayType;

	@Transient
	private String _fieldName;

	@Transient
	private String _fieldType;

	@Transient
	private Date _modifiedDate = new Date();

	@Transient
	private String _ownerType;

	@Transient
	private Boolean _repeatable;

}