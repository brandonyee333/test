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

package com.liferay.osb.asah.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

import org.json.JSONObject;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author André Miranda
 */
@Table
public class AsahTask implements Persistable<Long> {

	public AsahTask() {
	}

	public AsahTask(Long id) {
		_id = id;
	}

	public AsahTask(
		String className, JSONObject contextJSONObject, String projectId) {

		_className = className;
		_contextJSONObject = contextJSONObject;
		_projectId = projectId;
	}

	public AsahTask(
		String className, JSONObject contextJSONObject, String cronExpression,
		String projectId) {

		_className = className;
		_contextJSONObject = contextJSONObject;
		_cronExpression = cronExpression;
		_projectId = projectId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AsahTask)) {
			return false;
		}

		AsahTask dataSource = (AsahTask)obj;

		if (Objects.equals(_className, dataSource._className) &&
			Objects.equals(_contextJSONObject, dataSource._contextJSONObject) &&
			Objects.equals(_cronExpression, dataSource._cronExpression) &&
			Objects.equals(_id, dataSource._id) &&
			Objects.equals(_projectId, dataSource._projectId)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getClassName() {
		return _className;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("context")
	@JsonProperty("context")
	public JSONObject getContextJSONObject() {
		return _contextJSONObject;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getCronExpression() {
		return _cronExpression;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getProjectId() {
		return _projectId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_className, _contextJSONObject, _cronExpression, _id, _projectId);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setContextJSONObject(JSONObject contextJSONObject) {
		_contextJSONObject = contextJSONObject;
	}

	public void setCronExpression(String cronExpression) {
		_cronExpression = cronExpression;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	@Transient
	private String _className;

	@Transient
	private JSONObject _contextJSONObject;

	@Transient
	private String _cronExpression;

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _projectId;

}