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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;

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
public class BQCSVUser implements Persistable<Long> {

	public BQCSVUser() {
	}

	public BQCSVUser(Long dataSourceId) {
		this(dataSourceId, null);
	}

	public BQCSVUser(Long dataSourceId, JSONArray fieldsJSONArray) {
		_dataSourceId = dataSourceId;
		_fieldsJSONArray = fieldsJSONArray;
	}

	public BQCSVUser(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQCSVUser)) {
			return false;
		}

		BQCSVUser bqCSVUser = (BQCSVUser)obj;

		if (Objects.equals(_dataSourceId, bqCSVUser._dataSourceId) &&
			Objects.equals(_dataSourceUserPK, bqCSVUser._dataSourceUserPK) &&
			Objects.equals(_emailAddress, bqCSVUser._emailAddress) &&
			Objects.equals(
				JSONUtil.toSafeList(_fieldsJSONArray, JSONUtil::toMap),
				JSONUtil.toSafeList(
					bqCSVUser._fieldsJSONArray, JSONUtil::toMap)) &&
			Objects.equals(_id, bqCSVUser._id)) {

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
	@JsonAlias("dataSourceUserPK")
	@JsonProperty("dataSourceIndividualPK")
	public String getDataSourceUserPK() {
		return _dataSourceUserPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddress() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("fields")
	@JsonProperty("fields")
	public JSONArray getFieldsJSONArray() {
		return _fieldsJSONArray;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_dataSourceId, _dataSourceUserPK, _emailAddress, _fieldsJSONArray,
			_id);
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

	public void setDataSourceUserPK(String dataSourceUserPK) {
		_dataSourceUserPK = dataSourceUserPK;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFieldsJSONArray(JSONArray fieldsJSONArray) {
		_fieldsJSONArray = fieldsJSONArray;
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

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _dataSourceUserPK;

	@Transient
	private String _emailAddress;

	@Transient
	private JSONArray _fieldsJSONArray;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate;

}