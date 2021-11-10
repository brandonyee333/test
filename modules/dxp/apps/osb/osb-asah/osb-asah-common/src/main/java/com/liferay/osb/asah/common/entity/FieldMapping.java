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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.json.JSONObject;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class FieldMapping implements Persistable<Long> {

	public FieldMapping() {
	}

	public FieldMapping(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public void addDataSourceFieldMapping(
		DataSourceFieldMapping dataSourceFieldMapping) {

		_dataSourceFieldMappings.add(dataSourceFieldMapping);
	}

	public void addDataSourceFieldMapping(Long dataSourceId, String fieldName) {
		_dataSourceFieldMappings.add(
			new DataSourceFieldMapping(dataSourceId, getId(), fieldName));
	}

	public void addDataSourceFieldName(String dataSourceId, String fieldName) {
		_dataSourceFieldNames.put(dataSourceId, fieldName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FieldMapping)) {
			return false;
		}

		FieldMapping fieldMapping = (FieldMapping)obj;

		if (Objects.equals(_author, fieldMapping._author) &&
			Objects.equals(_context, fieldMapping._context) &&
			Objects.equals(_createDate, fieldMapping._createDate) &&
			Objects.equals(
				_dataSourceFieldMappings,
				fieldMapping._dataSourceFieldMappings) &&
			Objects.equals(
				_dataSourceFieldNames, fieldMapping._dataSourceFieldNames) &&
			Objects.equals(_displayName, fieldMapping._displayName) &&
			Objects.equals(_displayType, fieldMapping._displayType) &&
			Objects.equals(_fieldName, fieldMapping._fieldName) &&
			Objects.equals(_fieldType, fieldMapping._fieldType) &&
			Objects.equals(_id, fieldMapping._id) &&
			Objects.equals(_modifiedDate, fieldMapping._modifiedDate) &&
			Objects.equals(_ownerType, fieldMapping._ownerType) &&
			Objects.equals(_strategy, fieldMapping._strategy)) {

			return true;
		}

		return false;
	}

	@JsonProperty("author")
	public Author getAuthor() {
		return _author;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	public String getAuthorId() {
		if (_author == null) {
			return null;
		}

		return _author.getId();
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	public String getAuthorName() {
		if (_author == null) {
			return null;
		}

		return _author.getName();
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getContext() {
		return _context;
	}

	@AccessType(AccessType.Type.PROPERTY)
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

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "fieldmappingid")
	public Set<DataSourceFieldMapping> getDataSourceFieldMappings() {
		return _dataSourceFieldMappings;
	}

	@JsonProperty("dataSourceFieldNames")
	public Map<String, String> getDataSourceFieldNames() {
		return _dataSourceFieldNames;
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
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
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

	@AccessType(AccessType.Type.PROPERTY)
	public String getOwnerType() {
		return _ownerType;
	}

	@JsonProperty("strategy")
	public Strategy getStrategy() {
		return _strategy;
	}

	@JsonIgnore
	public JSONObject getStrategyConfigurationJSONObject() {
		if (_strategy == null) {
			return null;
		}

		return _strategy.getConfigurationJSONObject();
	}

	@JsonIgnore
	public String getStrategyKey() {
		if (_strategy == null) {
			return null;
		}

		return _strategy.getKey();
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_author, _context, _createDate, _dataSourceFieldMappings,
			_dataSourceFieldNames, _displayName, _displayType, _fieldName,
			_fieldType, _id, _modifiedDate, _ownerType, _strategy);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAuthor(Author author) {
		_author = author;
	}

	public void setAuthorId(String authorId) {
		if (authorId == null) {
			return;
		}

		if (_author == null) {
			_author = new Author();
		}

		_author.setId(authorId);
	}

	public void setAuthorName(String authorName) {
		if (authorName == null) {
			return;
		}

		if (_author == null) {
			_author = new Author();
		}

		_author.setName(authorName);
	}

	public void setContext(String context) {
		_context = context;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceFieldMappings(
		Set<DataSourceFieldMapping> dataSourceFieldMappings) {

		_dataSourceFieldMappings = dataSourceFieldMappings;
	}

	public void setDataSourceFieldNames(
		Map<String, String> dataSourceFieldNames) {

		Set<DataSourceFieldMapping> dataSourceFieldMappings = new HashSet<>();

		for (Map.Entry<String, String> entry :
				dataSourceFieldNames.entrySet()) {

			dataSourceFieldMappings.add(
				new DataSourceFieldMapping(
					Long.valueOf(entry.getKey()), getId(), entry.getValue()));
		}

		setDataSourceFieldMappings(dataSourceFieldMappings);

		_dataSourceFieldNames = dataSourceFieldNames;
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

	public void setId(Long id) {
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

	public void setOwnerType(String ownerType) {
		_ownerType = ownerType;
	}

	public void setStrategy(Strategy strategy) {
		_strategy = strategy;
	}

	public void setStrategyConfiguration(
		JSONObject strategyConfigurationJSONObject) {

		if (strategyConfigurationJSONObject == null) {
			return;
		}

		if (_strategy == null) {
			_strategy = Strategy.DEFAULT;
		}

		_strategy.setConfigurationJSONObject(strategyConfigurationJSONObject);
	}

	public void setStrategyKey(String strategyKey) {
		if (strategyKey == null) {
			return;
		}

		if (_strategy == null) {
			_strategy = Strategy.DEFAULT;
		}

		_strategy.setKey(strategyKey);
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Author {

		public Author() {
		}

		public Author(String id, String name) {
			_id = id;
			_name = name;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Author)) {
				return false;
			}

			Author author = (Author)obj;

			if (Objects.equals(_id, author._id) &&
				Objects.equals(_name, author._name)) {

				return true;
			}

			return false;
		}

		public String getId() {
			return _id;
		}

		public String getName() {
			return _name;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_id, _name);
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
	public static class Strategy {

		public static final Strategy DEFAULT = new Strategy(
			Key.MOST_RECENT.toString(), new HashMap<>());

		public Strategy() {
		}

		public Strategy(String key, Map<String, String> configuration) {
			_key = key;

			_configurationJSONObject = _toJSONObject(configuration);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Strategy)) {
				return false;
			}

			Strategy strategy = (Strategy)obj;

			if (Objects.equals(_key, strategy._key)) {
				return true;
			}

			return false;
		}

		@AccessType(AccessType.Type.PROPERTY)
		@Column("strategyconfiguration")
		@JsonProperty("configuration")
		public JSONObject getConfigurationJSONObject() {
			return _configurationJSONObject;
		}

		@AccessType(AccessType.Type.PROPERTY)
		@Column("strategykey")
		@JsonProperty("key")
		public String getKey() {
			return _key;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_key);
		}

		public void setConfigurationJSONObject(
			JSONObject configurationJSONObject) {

			_configurationJSONObject = configurationJSONObject;
		}

		public void setKey(String key) {
			_key = key;
		}

		public enum Key {

			MOST_RECENT, PRIORITY_DATASOURCE

		}

		private JSONObject _toJSONObject(Map<String, String> configuration) {
			JSONObject jsonObject = new JSONObject();

			for (Map.Entry<String, String> entry : configuration.entrySet()) {
				jsonObject.put(entry.getKey(), entry.getValue());
			}

			return jsonObject;
		}

		private JSONObject _configurationJSONObject;
		private String _key;

	}

	@Transient
	private Author _author;

	@Transient
	private String _context;

	@Transient
	private Date _createDate = new Date();

	@Transient
	private Set<DataSourceFieldMapping> _dataSourceFieldMappings =
		new HashSet<>();

	@Transient
	private Map<String, String> _dataSourceFieldNames = new HashMap<>();

	@Transient
	private String _displayName;

	@Transient
	private String _displayType;

	@Transient
	private String _fieldName;

	@Transient
	private String _fieldType;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate = new Date();

	@Transient
	private String _ownerType;

	@Transient
	private Strategy _strategy;

}