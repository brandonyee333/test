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

import java.util.Date;
import java.util.Objects;

/**
 * @author Leslie Wong
 */
public class BQEventPropertyValue {

	public BQEventPropertyValue() {
	}

	public BQEventPropertyValue(Date lastSeenDate, String value) {
		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}

		_value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQEventPropertyValue)) {
			return false;
		}

		BQEventPropertyValue bqEventPropertyValue = (BQEventPropertyValue)obj;

		if (Objects.equals(_lastSeenDate, bqEventPropertyValue._lastSeenDate) &&
			Objects.equals(_value, bqEventPropertyValue._value)) {

			return true;
		}

		return false;
	}

	public Date getLastSeenDate() {
		if (_lastSeenDate == null) {
			return null;
		}

		return new Date(_lastSeenDate.getTime());
	}

	public String getValue() {
		return _value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_lastSeenDate, _value);
	}

	public void setLastSeenDate(Date lastSeenDate) {
		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}
	}

	public void setValue(String value) {
		_value = value;
	}

	private Date _lastSeenDate;
	private String _value;

}