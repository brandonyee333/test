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

package com.liferay.lcs.util;

import java.io.IOException;

import java.util.Enumeration;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

/**
 * @author Ivica Cardic
 */
public class ImmutablePortletPreferences implements PortletPreferences {

	public ImmutablePortletPreferences(PortletPreferences portletPreferences) {
		_portletPreferences = portletPreferences;
	}

	@Override
	public Map<String, String[]> getMap() {
		return _portletPreferences.getMap();
	}

	@Override
	public Enumeration<String> getNames() {
		return _portletPreferences.getNames();
	}

	@Override
	public String getValue(String key, String def) {
		return _portletPreferences.getValue(key, def);
	}

	@Override
	public String[] getValues(String key, String[] def) {
		return _portletPreferences.getValues(key, def);
	}

	@Override
	public boolean isReadOnly(String key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void reset(String key) throws ReadOnlyException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValue(String key, String value) throws ReadOnlyException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValues(String key, String[] values)
		throws ReadOnlyException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void store() throws IOException, ValidatorException {
		throw new UnsupportedOperationException();
	}

	private final PortletPreferences _portletPreferences;

}