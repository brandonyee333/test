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
public class ZendeskUser {

	public ZendeskUser() {
	}

	public String getEmail() {
		return _email;
	}

	public String getName() {
		return _name;
	}

	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	private String _email;
	private String _name;
	private long _zendeskUserId;

}