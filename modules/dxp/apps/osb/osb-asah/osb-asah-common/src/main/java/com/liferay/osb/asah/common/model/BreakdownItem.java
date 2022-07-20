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

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class BreakdownItem {

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
			Objects.equals(_internalName, breakdownItem._internalName) &&
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

	@JsonIgnore
	public String getInternalName() {
		return _internalName;
	}

	public String getName() {
		return _name;
	}

	public Number getPreviousValue() {
		return _previousValue;
	}

	public Number getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_breakdownItems, _internalName, _leafNode, _name, _previousValue,
			_value);
	}

	public boolean isLeafNode() {
		return _leafNode;
	}

	public void setBreakdownItems(List<BreakdownItem> breakdownItems) {
		_breakdownItems = breakdownItems;
	}

	public void setInternalName(String internalName) {
		_internalName = internalName;
	}

	public void setLeafNode(boolean leafNode) {
		_leafNode = leafNode;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPreviousValue(Number previousValue) {
		_previousValue = previousValue;
	}

	public void setValue(Number value) {
		_value = value;
	}

	private List<BreakdownItem> _breakdownItems;
	private String _internalName;
	private boolean _leafNode;
	private String _name;
	private Number _previousValue;
	private Number _value;

}