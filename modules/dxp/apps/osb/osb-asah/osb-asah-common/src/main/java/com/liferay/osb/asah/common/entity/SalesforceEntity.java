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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class SalesforceEntity implements Persistable<String> {

	public SalesforceEntity() {
	}

	public SalesforceEntity(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public SalesforceEntity(
		String id, Long dataSourceId, JSONObject fieldsJSONObject,
		SalesforceEntity.Type type) {

		_id = id;
		_dataSourceId = dataSourceId;
		_fieldsJSONObject = fieldsJSONObject;
		_type = type;

		_isNew = Boolean.TRUE;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesforceEntity)) {
			return false;
		}

		SalesforceEntity salesforceEntity = (SalesforceEntity)obj;

		if (Objects.equals(_id, salesforceEntity._id) &&
			Objects.equals(
				JSONUtil.toMap(_fieldsJSONObject),
				JSONUtil.toMap(salesforceEntity._fieldsJSONObject)) &&
			Objects.equals(_type, salesforceEntity._type)) {

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
	@Column("fields")
	@JsonProperty("fields")
	public JSONObject getFieldsJSONObject() {
		return _fieldsJSONObject;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Type getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, JSONUtil.toMap(_fieldsJSONObject), _type);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		return _isNew;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setFieldsJSONObject(JSONObject fieldsJSONObject) {
		_fieldsJSONObject = fieldsJSONObject;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setType(Type type) {
		_type = type;
	}

	public enum Type {

		ACCOUNT("Account"), CONTACT("Contact"), INDIVIDUAL("individuals"),
		LEAD("Lead");

		public static Type of(String value) {
			return Optional.ofNullable(
				_types.get(value)
			).orElseThrow(
				IllegalArgumentException::new
			);
		}

		public String getValue() {
			return _value;
		}

		private Type(String value) {
			_value = value;
		}

		private static final Map<String, Type> _types = new HashMap<>();

		static {
			for (Type type : values()) {
				_types.put(type.getValue(), type);
			}
		}

		private final String _value;

	}

	@Transient
	private Long _dataSourceId;

	@Transient
	private JSONObject _fieldsJSONObject;

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew = Boolean.FALSE;

	@Transient
	private Type _type;

}