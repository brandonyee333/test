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