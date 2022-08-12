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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQFieldMapping;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
		AuthorDTO authorDTO = new AuthorDTO();

		if (!authorDTO.isEmpty()) {
			_authorDTO = authorDTO;
		}

		_context = bqFieldMapping.getContext();

		_displayName = bqFieldMapping.getDisplayName();
		_displayType = bqFieldMapping.getDisplayType();
		_fieldName = bqFieldMapping.getFieldName();
		_fieldType = bqFieldMapping.getFieldType();
		_id = StringUtil.get(bqFieldMapping.getFieldName(), null);
		_modifiedDate = bqFieldMapping.getModifiedDate();
		_ownerType = bqFieldMapping.getOwnerType();
	}

	public FieldMappingDTO(Collection<FieldMappingDTO> fieldMappingDTOs) {
		_fieldMappingDTOs = new LinkedHashSet<>(fieldMappingDTOs);
	}

	public FieldMappingDTO(List<BQFieldMapping> bqFieldMappings) {
		_fieldMappingDTOs = SetUtil.map(bqFieldMappings, FieldMappingDTO::new);
	}

	@JsonProperty("author")
	public AuthorDTO getAuthorDTO() {
		return _authorDTO;
	}

	@JsonProperty("context")
	public String getContext() {
		return _context;
	}

	@JsonAlias("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
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

	@JsonProperty("id")
	public String getId() {
		return _id;
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

	public void setAuthorDTO(AuthorDTO authorDTO) {
		_authorDTO = authorDTO;
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
		else {
			_createDate = null;
		}
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

	public void setId(String id) {
		_id = id;
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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AuthorDTO {

		public AuthorDTO() {
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof AuthorDTO)) {
				return false;
			}

			AuthorDTO authorDTO = (AuthorDTO)obj;

			if (Objects.equals(_id, authorDTO._id) &&
				Objects.equals(_name, authorDTO._name)) {

				return true;
			}

			return false;
		}

		@JsonProperty("id")
		public String getId() {
			return _id;
		}

		@JsonProperty("name")
		public String getName() {
			return _name;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_id, _name);
		}

		@JsonIgnore
		public boolean isEmpty() {
			return equals(new AuthorDTO());
		}

		public void setId(String id) {
			_id = id;
		}

		public void setName(String name) {
			_name = name;
		}

		private String _id;
		private String _name;

	}

	private AuthorDTO _authorDTO;
	private String _context;
	private Date _createDate;
	private Set<DataSourceFieldMapping> _dataSourceFieldMappings;
	private Map<String, String> _dataSourceFieldNames;
	private List<Map<String, String>> _dataSources;
	private String _displayName;
	private String _displayType;
	private Set<FieldMappingDTO> _fieldMappingDTOs;
	private String _fieldName;
	private String _fieldType;
	private String _id;
	private Date _modifiedDate;
	private String _ownerType;

}