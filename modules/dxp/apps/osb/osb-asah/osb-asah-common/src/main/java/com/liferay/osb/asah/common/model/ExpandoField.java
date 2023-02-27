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

import com.liferay.osb.asah.common.entity.BQExpandoColumn;
import com.liferay.osb.asah.common.entity.BQExpandoValue;

import java.util.Date;

/**
 * @author Marcos Martins
 */
public class ExpandoField {

	public ExpandoField(
		BQExpandoColumn bqExpandoColumn, BQExpandoValue bqExpandoValue) {

		_classPK = bqExpandoValue.getClassPK();
		_classType = bqExpandoValue.getClassType();
		_columnId = bqExpandoColumn.getColumnId();
		_dataSourceId = bqExpandoColumn.getDataSourceId();
		_dataSourceName = bqExpandoColumn.getDataSourceName();
		_dataType = bqExpandoColumn.getDataType();
		_id = bqExpandoColumn.getId();
		_modifiedDate = bqExpandoColumn.getModifiedDate();
		_name = bqExpandoColumn.getName();
		_value = bqExpandoValue.getValue();
	}

	public String getClassPK() {
		return _classPK;
	}

	public String getClassType() {
		return _classType;
	}

	public String getColumnId() {
		return _columnId;
	}

	public Long getDataSourceId() {
		return _dataSourceId;
	}

	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getDataType() {
		return _dataType;
	}

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

	public String getValue() {
		return _value;
	}

	private final String _classPK;
	private final String _classType;
	private final String _columnId;
	private final Long _dataSourceId;
	private final String _dataSourceName;
	private final String _dataType;
	private final String _id;
	private final Date _modifiedDate;
	private final String _name;
	private final String _value;

}