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
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("field-mappings")
public class FieldMappingDTO {

	public FieldMappingDTO() {
	}

	public FieldMappingDTO(Collection<FieldMappingDTO> fieldMappingDTOs) {
		_fieldMappingDTOs = new LinkedHashSet<>(fieldMappingDTOs);
	}

	public FieldMappingDTO(FieldMapping fieldMapping) {
		AuthorDTO authorDTO = new AuthorDTO(fieldMapping);

		if (!authorDTO.isEmpty()) {
			_authorDTO = authorDTO;
		}

		_context = fieldMapping.getContext();
		_createDate = fieldMapping.getCreateDate();

		_dataSourceFieldMappings = fieldMapping.getDataSourceFieldMappings();
		_dataSourceFieldNames = fieldMapping.getDataSourceFieldNames();

		if (CollectionUtils.isNotEmpty(_dataSourceFieldMappings) &&
			MapUtils.isEmpty(_dataSourceFieldNames)) {

			Iterator<DataSourceFieldMapping> iterator =
				_dataSourceFieldMappings.iterator();

			while (iterator.hasNext()) {
				DataSourceFieldMapping dataSourceFieldMapping = iterator.next();

				_dataSourceFieldNames.put(
					String.valueOf(dataSourceFieldMapping.getDataSourceId()),
					dataSourceFieldMapping.getFieldName());
			}
		}

		_displayName = fieldMapping.getDisplayName();
		_displayType = fieldMapping.getDisplayType();
		_fieldName = fieldMapping.getFieldName();
		_fieldType = fieldMapping.getFieldType();
		_id = StringUtil.get(fieldMapping.getId(), null);
		_modifiedDate = fieldMapping.getModifiedDate();
		_ownerType = fieldMapping.getOwnerType();

		StrategyDTO strategyDTO = new StrategyDTO(fieldMapping);

		if (!strategyDTO.isEmpty()) {
			_strategyDTO = strategyDTO;
		}
	}

	public FieldMappingDTO(List<FieldMapping> fieldMappings) {
		_fieldMappingDTOs = SetUtil.map(fieldMappings, FieldMappingDTO::new);
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
	public JSONArray getDataSourcesJSONArray() {
		return _dataSourcesJSONArray;
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

	@JsonProperty("strategy")
	public StrategyDTO getStrategyDTO() {
		return _strategyDTO;
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

	public void setDataSourcesJSONArray(JSONArray dataSourcesJSONArray) {
		_dataSourcesJSONArray = dataSourcesJSONArray;
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

	public void setStrategyDTO(StrategyDTO strategyDTO) {
		_strategyDTO = strategyDTO;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AuthorDTO {

		public AuthorDTO() {
		}

		public AuthorDTO(FieldMapping fieldMapping) {
			_id = StringUtil.get(fieldMapping.getAuthorId(), null);
			_name = fieldMapping.getAuthorName();
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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class StrategyDTO {

		public StrategyDTO() {
		}

		public StrategyDTO(FieldMapping fieldMapping) {
			_key = StringUtil.get(fieldMapping.getStrategyKey(), null);
			_configurationJSONObject =
				fieldMapping.getStrategyConfigurationJSONObject();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof StrategyDTO)) {
				return false;
			}

			StrategyDTO strategyDTO = (StrategyDTO)obj;

			if (Objects.equals(
					_configurationJSONObject,
					strategyDTO._configurationJSONObject) &&
				Objects.equals(_key, strategyDTO._key)) {

				return true;
			}

			return false;
		}

		@JsonProperty("configuration")
		public JSONObject getConfigurationJSONObject() {
			return _configurationJSONObject;
		}

		@JsonProperty("key")
		public String getKey() {
			return _key;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_configurationJSONObject, _key);
		}

		@JsonIgnore
		public boolean isEmpty() {
			return equals(new StrategyDTO());
		}

		public void setConfigurationJSONObject(
			JSONObject configurationJSONObject) {

			_configurationJSONObject = configurationJSONObject;
		}

		public void setKey(String key) {
			_key = key;
		}

		private JSONObject _configurationJSONObject;
		private String _key;

	}

	private AuthorDTO _authorDTO;
	private String _context;
	private Date _createDate;
	private Set<DataSourceFieldMapping> _dataSourceFieldMappings;
	private Map<String, String> _dataSourceFieldNames;
	private JSONArray _dataSourcesJSONArray;
	private String _displayName;
	private String _displayType;
	private Set<FieldMappingDTO> _fieldMappingDTOs;
	private String _fieldName;
	private String _fieldType;
	private String _id;
	private Date _modifiedDate;
	private String _ownerType;
	private StrategyDTO _strategyDTO;

}