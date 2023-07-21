/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.model;

/**
 * @author Amos Fong
 */
public class ZendeskUserIdentity {

	public ZendeskUserIdentity() {
	}

	public String getType() {
		return _type;
	}

	public String getValue() {
		return _value;
	}

	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public long getZendeskUserIdentityId() {
		return _zendeskUserIdentityId;
	}

	public boolean isPrimary() {
		return _primary;
	}

	public void setPrimary(boolean primary) {
		_primary = primary;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setValue(String value) {
		_value = value;
	}

	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	public void setZendeskUserIdentityId(long zendeskUserIdentityId) {
		_zendeskUserIdentityId = zendeskUserIdentityId;
	}

	private boolean _primary;
	private String _type;
	private String _value;
	private long _zendeskUserId;
	private long _zendeskUserIdentityId;

}