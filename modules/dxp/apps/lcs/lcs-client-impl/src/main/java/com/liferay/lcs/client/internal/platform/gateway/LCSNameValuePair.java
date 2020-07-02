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

package com.liferay.lcs.client.internal.platform.gateway;

import org.apache.http.NameValuePair;

/**
 * @author Igor Beslic
 */
public class LCSNameValuePair implements NameValuePair {

	public LCSNameValuePair(String name, String value) {
		_name = name;
		_value = value;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getValue() {
		return _value;
	}

	private final String _name;
	private final String _value;

}