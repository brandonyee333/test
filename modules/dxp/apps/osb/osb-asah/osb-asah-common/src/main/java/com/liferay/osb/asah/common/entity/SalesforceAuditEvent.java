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

import java.util.Date;
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
public class SalesforceAuditEvent implements Persistable<Long> {

	public SalesforceAuditEvent() {
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SalesforceAuditEvent)) {
			return false;
		}

		SalesforceAuditEvent salesforceAuditEvent = (SalesforceAuditEvent)obj;

		if (Objects.equals(
				JSONUtil.toMap(_additionalInfoJSONObject),
				JSONUtil.toMap(
					salesforceAuditEvent._additionalInfoJSONObject)) &&
			Objects.equals(_dataSourceId, salesforceAuditEvent._dataSourceId) &&
			Objects.equals(
				_entityTypeName, salesforceAuditEvent._entityTypeName) &&
			Objects.equals(_id, salesforceAuditEvent._id) &&
			Objects.equals(_recordId, salesforceAuditEvent._recordId) &&
			Objects.equals(_type, salesforceAuditEvent._type) &&
			Objects.equals(_userId, salesforceAuditEvent._userId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("additionalinfo")
	@JsonProperty("additionalInfo")
	public JSONObject getAdditionalInfoJSONObject() {
		return _additionalInfoJSONObject;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("dateCreated")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getAuditEventDate() {
		if (_auditEventDate == null) {
			return null;
		}

		return new Date(_auditEventDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("osbAsahDataSourceId")
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("typeName")
	public String getEntityTypeName() {
		return _entityTypeName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getRecordId() {
		return _recordId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("eventType")
	public Type getType() {
		return _type;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserId() {
		return _userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			JSONUtil.toMap(_additionalInfoJSONObject), _auditEventDate,
			_dataSourceId, _entityTypeName, _id, _recordId, _type, _userId);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAdditionalInfoJSONObject(
		JSONObject additionalInfoJSONObject) {

		_additionalInfoJSONObject = additionalInfoJSONObject;
	}

	public void setAuditEventDate(Date auditEventDate) {
		if (auditEventDate != null) {
			_auditEventDate = new Date(auditEventDate.getTime());
		}
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setEntityTypeName(String entityTypeName) {
		_entityTypeName = entityTypeName;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setRecordId(String recordId) {
		_recordId = recordId;
	}

	public void setType(Type type) {
		_type = type;
	}

	public void setUserId(String userId) {
		_userId = userId;
	}

	public enum Type {

		ADD, DELETE, UPDATE

	}

	@Transient
	private JSONObject _additionalInfoJSONObject;

	@Transient
	private Date _auditEventDate;

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _entityTypeName;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _recordId;

	@Transient
	private Type _type;

	@Transient
	private String _userId;

}