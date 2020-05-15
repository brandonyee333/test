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

package com.liferay.osb.asah.backend.model;

import java.time.LocalDateTime;

import java.util.Objects;

/**
 * @author André Miranda
 */
public class Dashboard {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Dashboard)) {
			return false;
		}

		Dashboard dashboard = (Dashboard)obj;

		if (Objects.equals(_assetId, dashboard._assetId) &&
			Objects.equals(_assetTitle, dashboard._assetTitle) &&
			Objects.equals(_category, dashboard._category) &&
			Objects.equals(_createDate, dashboard._createDate) &&
			Objects.equals(_definition, dashboard._definition) &&
			Objects.equals(_id, dashboard._id) &&
			Objects.equals(_modifiedDate, dashboard._modifiedDate) &&
			Objects.equals(_modifiedByUserId, dashboard._modifiedByUserId) &&
			Objects.equals(
				_modifiedByUserName, dashboard._modifiedByUserName)) {

			return true;
		}

		return false;
	}

	public String getAssetId() {
		return _assetId;
	}

	public String getAssetTitle() {
		return _assetTitle;
	}

	public String getCategory() {
		return _category;
	}

	public LocalDateTime getCreateDate() {
		return _createDate;
	}

	public String getDefinition() {
		return _definition;
	}

	public String getId() {
		return _id;
	}

	public String getModifiedByUserId() {
		return _modifiedByUserId;
	}

	public String getModifiedByUserName() {
		return _modifiedByUserName;
	}

	public LocalDateTime getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_assetId, _assetTitle, _category, _createDate, _definition, _id,
			_modifiedDate, _modifiedByUserId, _modifiedByUserName);
	}

	public void setAssetId(String assetId) {
		_assetId = assetId;
	}

	public void setAssetTitle(String assetTitle) {
		_assetTitle = assetTitle;
	}

	public void setCategory(String category) {
		_category = category;
	}

	public void setCreateDate(LocalDateTime createDate) {
		_createDate = createDate;
	}

	public void setDefinition(String definition) {
		_definition = definition;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedByUserId(String modifiedByUserId) {
		_modifiedByUserId = modifiedByUserId;
	}

	public void setModifiedByUserName(String modifiedByUserName) {
		_modifiedByUserName = modifiedByUserName;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	private String _assetId;
	private String _assetTitle;
	private String _category;
	private LocalDateTime _createDate;
	private String _definition;
	private String _id;
	private String _modifiedByUserId;
	private String _modifiedByUserName;
	private LocalDateTime _modifiedDate;

}