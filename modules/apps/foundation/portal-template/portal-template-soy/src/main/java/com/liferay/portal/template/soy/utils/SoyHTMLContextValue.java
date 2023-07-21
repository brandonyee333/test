/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.utils;

/**
 * @author Bruno Basto
 */
public class SoyHTMLContextValue implements CharSequence {

	public SoyHTMLContextValue(String value) {
		_value = value;
	}

	@Override
	public char charAt(int index) {
		return _value.charAt(index);
	}

	@Override
	public int length() {
		return _value.length();
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return _value.subSequence(start, end);
	}

	@Override
	public String toString() {
		return _value;
	}

	private final String _value;

}