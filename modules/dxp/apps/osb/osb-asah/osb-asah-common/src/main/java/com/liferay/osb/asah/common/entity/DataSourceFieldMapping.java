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

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class DataSourceFieldMapping {

	public DataSourceFieldMapping() {
	}

	public DataSourceFieldMapping(
		Long dataSourceId, Long fieldMappingId, String fieldName) {

		_dataSourceId = dataSourceId;
		_fieldMappingId = fieldMappingId;
		_fieldName = fieldName;
	}

	public DataSourceFieldMapping(Long dataSourceId, String fieldName) {
		_dataSourceId = dataSourceId;
		_fieldName = fieldName;
	}

	public DataSourceFieldMapping(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataSourceFieldMapping)) {
			return false;
		}

		DataSourceFieldMapping dataSourceFieldMapping =
			(DataSourceFieldMapping)obj;

		if (Objects.equals(
				_dataSourceId, dataSourceFieldMapping._dataSourceId) &&
			Objects.equals(
				_fieldMappingId, dataSourceFieldMapping._fieldMappingId) &&
			Objects.equals(_fieldName, dataSourceFieldMapping._fieldName)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getFieldMappingId() {
		return _fieldMappingId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFieldName() {
		return _fieldName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_dataSourceId, _fieldMappingId, _fieldName);
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setFieldMappingId(Long fieldMappingId) {
		_fieldMappingId = fieldMappingId;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	@Transient
	private Long _dataSourceId;

	@Transient
	private Long _fieldMappingId;

	@Transient
	private String _fieldName;

}