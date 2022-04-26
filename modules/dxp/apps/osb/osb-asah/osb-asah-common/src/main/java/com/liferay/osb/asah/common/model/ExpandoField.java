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

/**
 * @author Marcos Martins
 */
public class ExpandoField {

	public ExpandoField(
		BQExpandoColumn bqExpandoColumn, BQExpandoValue bqExpandoValue) {

		_columnId = bqExpandoColumn.getColumnId();
		_dataType = bqExpandoColumn.getDataType();
		_name = bqExpandoColumn.getName();
		_value = bqExpandoValue.getValue();
	}

	public Long getColumnId() {
		return _columnId;
	}

	public String getDataType() {
		return _dataType;
	}

	public String getName() {
		return _name;
	}

	public String getValue() {
		return _value;
	}

	private final Long _columnId;
	private final String _dataType;
	private final String _name;
	private final String _value;

}