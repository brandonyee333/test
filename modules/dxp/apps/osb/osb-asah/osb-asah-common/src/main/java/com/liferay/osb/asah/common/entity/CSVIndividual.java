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

import java.util.Map;
import java.util.Objects;

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
public class CSVIndividual implements Persistable<Long> {

	public CSVIndividual() {
	}

	public CSVIndividual(Long dataSourceId) {
		this(dataSourceId, null);
	}

	public CSVIndividual(Long dataSourceId, JSONObject fieldsJSONObject) {
		_dataSourceId = dataSourceId;
		_fieldsJSONObject = fieldsJSONObject;
	}

	public CSVIndividual(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CSVIndividual)) {
			return false;
		}

		CSVIndividual csvIndividual = (CSVIndividual)obj;

		if (Objects.equals(_dataSourceId, csvIndividual._dataSourceId) &&
			Objects.equals(
				_dataSourceIndividualPK,
				csvIndividual._dataSourceIndividualPK) &&
			Objects.equals(
				JSONUtil.toMap(_fieldsJSONObject),
				JSONUtil.toMap(csvIndividual._fieldsJSONObject)) &&
			Objects.equals(_id, csvIndividual._id)) {

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
	public String getDataSourceIndividualPK() {
		return _dataSourceIndividualPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("fields")
	@JsonProperty("fields")
	public JSONObject getFieldsJSONObject() {
		return _fieldsJSONObject;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_dataSourceId, _dataSourceIndividualPK, _fieldsJSONObject, _id);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDataSourceIndividualPK(String dataSourceIndividualPK) {
		_dataSourceIndividualPK = dataSourceIndividualPK;
	}

	public void setFieldsJSONObject(JSONObject fieldsJSONObject) {
		_fieldsJSONObject = fieldsJSONObject;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _dataSourceIndividualPK;

	@Transient
	private JSONObject _fieldsJSONObject;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

}