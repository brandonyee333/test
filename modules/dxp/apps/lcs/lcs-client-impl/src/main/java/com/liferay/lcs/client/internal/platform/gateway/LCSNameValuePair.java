/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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