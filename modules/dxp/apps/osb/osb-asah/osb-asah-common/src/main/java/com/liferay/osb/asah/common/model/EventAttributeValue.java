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
public class EventAttributeValue {

	public EventAttributeValue() {
	}

	public EventAttributeValue(String attributeValue, Date lastSeenDate) {
		_attributeValue = attributeValue;

		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventAttributeValue)) {
			return false;
		}

		EventAttributeValue recentValue = (EventAttributeValue)obj;

		if (Objects.equals(_attributeValue, recentValue._attributeValue) &&
			Objects.equals(_lastSeenDate, recentValue._lastSeenDate)) {

			return true;
		}

		return false;
	}

	public String getAttributeValue() {
		return _attributeValue;
	}

	public Date getLastSeenDate() {
		if (_lastSeenDate == null) {
			return null;
		}

		return new Date(_lastSeenDate.getTime());
	}

	@Override
	public int hashCode() {
		return Objects.hash(_attributeValue, _lastSeenDate);
	}

	public void setAttributeValue(String attributeValue) {
		_attributeValue = attributeValue;
	}

	public void setLastSeenDate(Date lastSeenDate) {
		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}
	}

	private String _attributeValue;
	private Date _lastSeenDate;

}