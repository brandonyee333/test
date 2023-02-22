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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author Marcos Martins
 */
public class BQExpandoValue {

	public BQExpandoValue() {
	}

	public BQExpandoValue(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public Long getClassPK() {
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

	public String getId() {
		return _id;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getValue() {
		return _value;
	}

	public void setClassPK(Long classPK) {
		_classPK = classPK;
	}

	public void setClassType(String classType) {
		_classType = classType;
	}

	public void setColumnId(String columnId) {
		_columnId = columnId;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setValue(String value) {
		_value = value;
	}

	private Long _classPK;
	private String _classType;
	private String _columnId;
	private Long _dataSourceId;
	private String _id;
	private Date _modifiedDate;
	private String _value;

}