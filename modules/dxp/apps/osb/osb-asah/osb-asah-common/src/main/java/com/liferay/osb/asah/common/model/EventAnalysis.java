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

/**
 * @author Matthew Kong
 */
public class EventAnalysis implements Serializable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAnalysis)) {
			return false;
		}

		EventAnalysis eventAnalysis = (EventAnalysis)obj;

		if (Objects.equals(_breakdownItems, eventAnalysis._breakdownItems) &&
			Objects.equals(_count, eventAnalysis._count) &&
			Objects.equals(_page, eventAnalysis._page) &&
			Objects.equals(_value, eventAnalysis._value)) {

			return true;
		}

		return false;
	}

	public List<BreakdownItem> getBreakdownItems() {
		return _breakdownItems;
	}

	public long getCount() {
		return _count;
	}

	public int getPage() {
		return _page;
	}

	public Number getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_count, _page, _value);
	}

	public void setBreakdownItems(List<BreakdownItem> breakdownItems) {
		_breakdownItems = breakdownItems;
	}

	public void setCount(long count) {
		_count = count;
	}

	public void setPage(int page) {
		_page = page;
	}

	public void setValue(Number value) {
		_value = value;
	}

	private List<BreakdownItem> _breakdownItems;
	private long _count;
	private int _page;
	private Number _value;

}