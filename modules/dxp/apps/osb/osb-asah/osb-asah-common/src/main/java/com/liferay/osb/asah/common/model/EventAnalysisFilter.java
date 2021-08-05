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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public class EventAnalysisFilter {

	public EventAnalysisFilter(Map<String, Object> source) {
		BeanUtils.copyProperties(new CaseInsensitiveMap(source), this);
	}

	public EventAnalysisFilter(
		String attributeId, AttributeType attributeType,
		EventAttributeDefinition.DataType dataType,
		Function<Field, Field> fieldFunction, String operator,
		List<String> values) {

		_attributeId = attributeId;
		_attributeType = attributeType;
		_dataType = dataType;
		_fieldFunction = fieldFunction;
		_operator = operator;
		_values = values;
	}

	public EventAnalysisFilter(
		String attributeId, AttributeType attributeType,
		EventAttributeDefinition.DataType dataType, String operator,
		List<String> values) {

		_attributeId = attributeId;
		_attributeType = attributeType;
		_dataType = dataType;
		_operator = operator;
		_values = values;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof EventAnalysisFilter)) {
			return false;
		}

		EventAnalysisFilter eventAnalysisFilter = (EventAnalysisFilter)obj;

		if (Objects.equals(_attributeId, eventAnalysisFilter._attributeId) &&
			Objects.equals(
				_attributeType, eventAnalysisFilter._attributeType) &&
			Objects.equals(_dataType, eventAnalysisFilter._dataType) &&
			Objects.equals(
				_fieldFunction, eventAnalysisFilter._fieldFunction) &&
			Objects.equals(_operator, eventAnalysisFilter._operator) &&
			Objects.equals(_values, eventAnalysisFilter._values)) {

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

	public Function<Field, Field> getFieldFunction() {
		return _fieldFunction;
	}

	public String getOperator() {
		return _operator;
	}

	public List<String> getValues() {
		return _values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_attributeId, _attributeType, _dataType, _fieldFunction, _operator,
			_values);
	}

	public void setAttributeId(String attributeId) {
		_attributeId = attributeId;
	}

	public void setAttributeType(AttributeType attributeFilterType) {
		_attributeType = attributeFilterType;
	}

	public void setDataType(EventAttributeDefinition.DataType dataType) {
		_dataType = dataType;
	}

	public void setFieldFunction(Function<Field, Field> fieldFunction) {
		_fieldFunction = fieldFunction;
	}

	public void setOperator(String operator) {
		_operator = operator;
	}

	public void setValues(List<String> values) {
		_values = values;
	}

	private String _attributeId;
	private AttributeType _attributeType;
	private EventAttributeDefinition.DataType _dataType;
	private Function<Field, Field> _fieldFunction;
	private String _operator;
	private List<String> _values;

}