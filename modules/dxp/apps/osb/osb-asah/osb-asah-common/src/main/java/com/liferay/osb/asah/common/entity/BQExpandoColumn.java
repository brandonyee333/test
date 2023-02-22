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

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.BQDXPEntity;

import java.util.Date;

import org.json.JSONObject;

/**
 * @author Marcos Martins
 */
public class BQExpandoColumn implements BQDXPEntity {

	public BQExpandoColumn() {
	}

	public String getClassName() {
		return _className;
	}

	public String getColumnId() {
		return _columnId;
	}

	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getDataType() {
		return _dataType;
	}

	public String getDisplayType() {
		return _displayType;
	}

	public String getDXPEntityType() {
		return DXPEntity.Type.EXPANDO_COLUMN.name();
	}

	@JsonProperty("fields")
	public JSONObject getFieldsJSONObject() {
		return JSONUtil.put(
			"columnId", _columnId
		).put(
			"dataType", _dataType
		).put(
			"name", _name
		);
	}

	@Override
	public String getId() {
		return _id;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getName() {
		return _name;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setColumnId(String columnId) {
		_columnId = columnId;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setDataType(String dataType) {
		_dataType = dataType;
	}

	public void setDisplayType(String displayType) {
		_displayType = displayType;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	private String _className;
	private String _columnId;
	private Long _dataSourceId;
	private String _dataSourceName;
	private String _dataType;
	private String _displayType;
	private String _id;
	private Date _modifiedDate;
	private String _name;

}