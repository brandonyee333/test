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

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;

import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Matthew Kong
 */
@Table
public class BreakdownItem implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BreakdownItem)) {
			return false;
		}

		BreakdownItem breakdownItem = (BreakdownItem)obj;

		if (Objects.equals(_breakdownItems, breakdownItem._breakdownItems) &&
			Objects.equals(
				_eventAttributeDefinitionIdValuePairs,
				breakdownItem._eventAttributeDefinitionIdValuePairs) &&
			Objects.equals(_leafNode, breakdownItem._leafNode) &&
			Objects.equals(_name, breakdownItem._name) &&
			Objects.equals(_previousValue, breakdownItem._previousValue) &&
			Objects.equals(_value, breakdownItem._value)) {

			return true;
		}

		return false;
	}

	public List<BreakdownItem> getBreakdownItems() {
		return _breakdownItems;
	}

	public List<Pair<String, Object>>
		getEventAttributeDefinitionIdValuePairs() {

		return _eventAttributeDefinitionIdValuePairs;
	}

	public String getName() {
		return _name;
	}

	public long getPreviousValue() {
		return _previousValue;
	}

	public Number getValue() {
		return _value;
	}

	public boolean isLeafNode() {
		return _leafNode;
	}

	public void setBreakdownItems(List<BreakdownItem> breakdownItems) {
		_breakdownItems = breakdownItems;
	}

	public void setEventAttributeDefinitionIdValuePairs(
		List<Pair<String, Object>> eventAttributeDefinitionIdValuePairs) {

		_eventAttributeDefinitionIdValuePairs =
			eventAttributeDefinitionIdValuePairs;
	}

	public void setLeafNode(boolean leafNode) {
		_leafNode = leafNode;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPreviousValue(long previousValue) {
		_previousValue = previousValue;
	}

	public void setValue(Number value) {
		_value = value;
	}

	private List<BreakdownItem> _breakdownItems;
	private List<Pair<String, Object>> _eventAttributeDefinitionIdValuePairs;
	private boolean _leafNode;
	private String _name;
	private long _previousValue;
	private Number _value;

}