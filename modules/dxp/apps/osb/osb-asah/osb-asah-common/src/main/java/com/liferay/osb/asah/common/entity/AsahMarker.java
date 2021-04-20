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

import com.liferay.osb.asah.common.json.JSONUtil;

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
public class AsahMarker implements Persistable<String> {

	public AsahMarker() {
	}

	public AsahMarker(String id) {
		_id = id;

		_isNew = Boolean.TRUE;
	}

	public AsahMarker(String id, JSONObject contextJSONObject) {
		_id = id;
		_contextJSONObject = contextJSONObject;

		_isNew = Boolean.TRUE;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AsahMarker)) {
			return false;
		}

		AsahMarker asahMarker = (AsahMarker)obj;

		if (Objects.equals(
				JSONUtil.toMap(_contextJSONObject),
				JSONUtil.toMap(asahMarker._contextJSONObject)) &&
			Objects.equals(_id, asahMarker._id)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("context")
	@JsonProperty("context")
	public JSONObject getContextJSONObject() {
		return _contextJSONObject;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_contextJSONObject, _id);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setContextJSONObject(JSONObject contextJSONObject) {
		_contextJSONObject = contextJSONObject;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	@Transient
	private JSONObject _contextJSONObject = new JSONObject();

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

}