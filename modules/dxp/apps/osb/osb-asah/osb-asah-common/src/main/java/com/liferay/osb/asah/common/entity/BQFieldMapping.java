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

/**
 * @author Marcellus Tavares
 */
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

	public String getContext() {
		return _context;
	}

	public Set<Long> getDataSourceIds() {
		return _dataSourceIds;
	}

	public String getDisplayName() {
		return _displayName;
	}

	public String getDisplayType() {
		return _displayType;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public String getFieldType() {
		return _fieldType;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getOwnerType() {
		return _ownerType;
	}

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

	private String _context;
	private Set<Long> _dataSourceIds;
	private String _displayName;
	private String _displayType;
	private String _fieldName;
	private String _fieldType;
	private Date _modifiedDate = new Date();
	private String _ownerType;
	private Boolean _repeatable;

}