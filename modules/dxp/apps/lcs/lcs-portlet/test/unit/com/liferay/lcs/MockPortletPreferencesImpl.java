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

package com.liferay.lcs;

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