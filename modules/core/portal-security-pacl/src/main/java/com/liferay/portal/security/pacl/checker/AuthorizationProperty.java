/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class AuthorizationProperty {

	public String getKey() {
		return _key;
	}

	public String getValue() {
		return _value;
	}

	public String[] getValues() {
		return StringUtil.split(getValue());
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{key=");
		sb.append(_key);
		sb.append(", value=");
		sb.append(_value);
		sb.append("}");

		return sb.toString();
	}

	private String _key;
	private String _value;

}