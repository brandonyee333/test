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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("field-mappings")
public class FieldMappingDTO {

	public FieldMappingDTO() {
	}

	public FieldMappingDTO(BQFieldMapping bqFieldMapping) {
		_context = bqFieldMapping.getContext();
		_displayName = bqFieldMapping.getDisplayName();
		_displayType = bqFieldMapping.getDisplayType();
		_fieldName = bqFieldMapping.getFieldName();
		_fieldType = bqFieldMapping.getFieldType();
		_modifiedDate = bqFieldMapping.getModifiedDate();
		_ownerType = bqFieldMapping.getOwnerType();
		_repeatable = bqFieldMapping.getRepeatable();
	}

	public FieldMappingDTO(Collection<FieldMappingDTO> fieldMappingDTOs) {
		_fieldMappingDTOs = new LinkedHashSet<>(fieldMappingDTOs);
	}

	public FieldMappingDTO(List<BQFieldMapping> bqFieldMappings) {
		_fieldMappingDTOs = SetUtil.map(bqFieldMappings, FieldMappingDTO::new);
	}

	@JsonProperty("context")
	public String getContext() {
		return _context;
	}

	@JsonProperty("dataSourceFieldNames")
	public Map<String, String> getDataSourceFieldNames() {
		return _dataSourceFieldNames;
	}

	@JsonProperty("dataSources")
	public List<Map<String, String>> getDataSources() {
		return _dataSources;
	}

	@JsonProperty("displayName")
	public String getDisplayName() {
		return _displayName;
	}

	@JsonProperty("displayType")
	public String getDisplayType() {
		return _displayType;
	}

	@JsonProperty("field-mappings")
	public Set<FieldMappingDTO> getFieldMappingDTOs() {
		return _fieldMappingDTOs;
	}

	@JsonProperty("fieldName")
	public String getFieldName() {
		return _fieldName;
	}

	@JsonProperty("fieldType")
	public String getFieldType() {
		return _fieldType;
	}

	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonProperty("ownerType")
	public String getOwnerType() {
		return _ownerType;
	}

	@JsonProperty("repeatable")
	public Boolean getRepeatable() {
		return _repeatable;
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setDataSourceFieldNames(
		Map<String, String> dataSourceFieldNames) {

		_dataSourceFieldNames = dataSourceFieldNames;
	}

	public void setDataSources(List<Map<String, String>> dataSources) {
		_dataSources = dataSources;
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
		else {
			_modifiedDate = null;
		}
	}

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setRepeatable(Boolean repeatable) {
		_repeatable = repeatable;
	}

	private String _context;
	private Set<DataSourceFieldMapping> _dataSourceFieldMappings;
	private Map<String, String> _dataSourceFieldNames;
	private List<Map<String, String>> _dataSources;
	private String _displayName;
	private String _displayType;
	private Set<FieldMappingDTO> _fieldMappingDTOs;
	private String _fieldName;
	private String _fieldType;
	private Date _modifiedDate;
	private String _ownerType;
	private Boolean _repeatable;

}