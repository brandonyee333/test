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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Marcos Martins
 */
public class BreakdownRow {

	public BreakdownRow() {
	}

	public BreakdownRow(Map<String, Object> source) {
		for (Map.Entry<String, Object> entry : source.entrySet()) {
			_breakdownColumns.add(
				new BreakdownColumn(entry.getKey(), entry.getValue()));
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BreakdownRow)) {
			return false;
		}

		BreakdownRow breakdownRow = (BreakdownRow)obj;

		if (Objects.equals(_breakdownColumns, breakdownRow._breakdownColumns)) {
			return true;
		}

		return false;
	}

	public BreakdownColumn getBreakdownColumn(int columnIndex) {
		if (columnIndex < getBreakdownColumnsCount()) {
			return _breakdownColumns.get(columnIndex);
		}

		return null;
	}

	public List<BreakdownColumn> getBreakdownColumns() {
		return _breakdownColumns;
	}

	public int getBreakdownColumnsCount() {
		return _breakdownColumns.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(_breakdownColumns);
	}

	public static class BreakdownColumn {

		public BreakdownColumn() {
		}

		public BreakdownColumn(String name, Object value) {
			_name = name;
			_value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof BreakdownColumn)) {
				return false;
			}

			BreakdownColumn breakdownColumn = (BreakdownColumn)obj;

			if (Objects.equals(_name, breakdownColumn._name) &&
				Objects.equals(_value, breakdownColumn._value)) {

				return true;
			}

			return false;
		}

		public String getName() {
			return _name;
		}

		public Object getValue() {
			return _value;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_name, _value);
		}

		private String _name;
		private Object _value;

	}

	private final List<BreakdownColumn> _breakdownColumns = new ArrayList<>();

}