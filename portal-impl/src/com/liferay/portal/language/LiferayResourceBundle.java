/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.language;

import com.liferay.portal.kernel.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class LiferayResourceBundle extends ResourceBundle {

	public LiferayResourceBundle(InputStream inputStream, String charsetName)
		throws IOException {

		this(null, inputStream, charsetName);
	}

	public LiferayResourceBundle(
			ResourceBundle parentResourceBundle, InputStream inputStream,
			String charsetName)
		throws IOException {

		setParent(parentResourceBundle);

		_map = new HashMap<>();

		Properties properties = PropertiesUtil.load(inputStream, charsetName);

		LanguageResources.fixValues(_map, properties);
	}

	public LiferayResourceBundle(String string) throws IOException {
		_map = new HashMap<>();

		Properties properties = PropertiesUtil.load(string);

		LanguageResources.fixValues(_map, properties);
	}

	@Override
	public boolean containsKey(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		if (_map.containsKey(key)) {
			return true;
		}

		if (parent != null) {
			return parent.containsKey(key);
		}

		return false;
	}

	@Override
	public Enumeration<String> getKeys() {
		Set<String> keys = _map.keySet();

		if (parent == null) {
			return Collections.enumeration(keys);
		}

		return new ResourceBundleEnumeration(keys, parent.getKeys());
	}

	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		return _map.get(key);
	}

	@Override
	protected Set<String> handleKeySet() {
		return _map.keySet();
	}

	private final Map<String, String> _map;

}