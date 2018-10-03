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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Amos Fong
 * @author Kyle Bischof
 */
public class ZendeskUser {

	public ZendeskUser() {
	}

	public String getEmail() {
		return _email;
	}

	public String getExternalId() {
		return _externalId;
	}

	public String getLocale() {
		return _locale;
	}

	public String getName() {
		return _name;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	public void setLocale(String locale) {
		_locale = locale;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTags(Set<String> tags) {
		_tags = tags;
	}

	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	private String _email;
	private String _externalId;
	private String _locale;
	private String _name;
	private Set<String> _tags = new HashSet<>();
	private long _zendeskUserId;

}