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

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Leslie Wong
 */
@Table
public class BlockedEventDefinition {

	public BlockedEventDefinition() {
	}

	public BlockedEventDefinition(Date lastSeenDate, String lastSeenURL) {
		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}

		_lastSeenURL = lastSeenURL;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlockedEventDefinition)) {
			return false;
		}

		BlockedEventDefinition blockedEventDefinition =
			(BlockedEventDefinition)obj;

		if (Objects.equals(
				_lastSeenDate, blockedEventDefinition._lastSeenDate) &&
			Objects.equals(_lastSeenURL, blockedEventDefinition._lastSeenURL)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getLastSeenDate() {
		if (_lastSeenDate == null) {
			return null;
		}

		return new Date(_lastSeenDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getLastSeenURL() {
		return _lastSeenURL;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_lastSeenDate, _lastSeenURL);
	}

	public void setLastSeenDate(Date lastSeenDate) {
		if (lastSeenDate != null) {
			_lastSeenDate = new Date(lastSeenDate.getTime());
		}
	}

	public void setLastSeenURL(String lastSeenURL) {
		_lastSeenURL = lastSeenURL;
	}

	@Transient
	private Date _lastSeenDate;

	@Transient
	private String _lastSeenURL;

}