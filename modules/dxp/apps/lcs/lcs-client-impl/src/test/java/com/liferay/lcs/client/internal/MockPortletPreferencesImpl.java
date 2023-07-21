/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal;

import java.io.IOException;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Igor Beslic
 */
public class MockPortletPreferencesImpl implements PortletPreferences {

	@Override
	public Map<String, String[]> getMap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Enumeration<String> getNames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getValue(String s, String s1) {
		if (_preferences.containsKey(s)) {
			return _preferences.get(s);
		}

		return s1;
	}

	@Override
	public String[] getValues(String s, String[] strings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isReadOnly(String s) {
		return false;
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		_preferences.remove(key);
	}

	@Override
	public void setValue(String s, String s1) throws ReadOnlyException {
		_preferences.put(s, s1);
	}

	@Override
	public void setValues(String s, String[] strings) throws ReadOnlyException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void store() throws IOException, ValidatorException {
	}

	private final Map<String, String> _preferences = new HashMap<>();

}