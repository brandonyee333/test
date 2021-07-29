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

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.io.Serializable;

import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Matthew Kong
 */
@Table
public class EventAnalysisBreakdown implements Serializable {

	public EventAnalysisBreakdown(Map<String, Object> source) {
		BeanUtils.copyProperties(new CaseInsensitiveMap(source), this);
	}

	public EventAnalysisBreakdown(
		String attributeId, AttributeType attributeType,
		EventAttributeDefinition.DataType dataType, String sortType) {

		_attributeId = attributeId;
		_attributeType = attributeType;
		_dataType = dataType;
		_sortType = sortType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAnalysisBreakdown)) {
			return false;
		}

		EventAnalysisBreakdown eventAnalysisBreakdown =
			(EventAnalysisBreakdown)obj;

		if (Objects.equals(_attributeId, eventAnalysisBreakdown._attributeId) &&
			Objects.equals(
				_attributeType, eventAnalysisBreakdown._attributeType)) {

			return true;
		}

		return false;
	}

	public String getAttributeId() {
		return _attributeId;
	}

	public AttributeType getAttributeType() {
		return _attributeType;
	}

	public EventAttributeDefinition.DataType getDataType() {
		return _dataType;
	}

	public String getSortType() {
		return _sortType;
	}

	public void setAttributeId(String attributeId) {
		_attributeId = attributeId;
	}

	public void setAttributeType(AttributeType attributeType) {
		_attributeType = attributeType;
	}

	public void setDataType(EventAttributeDefinition.DataType dataType) {
		_dataType = dataType;
	}

	public void setSortType(String sortType) {
		_sortType = sortType;
	}

	private String _attributeId;
	private AttributeType _attributeType;
	private EventAttributeDefinition.DataType _dataType;
	private String _sortType;

}