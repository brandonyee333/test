/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author     Julio Camarero
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PublicRenderParameters extends HashMap<String, String[]> {

	public PublicRenderParameters(
		Map<String, String[]> map1, Map<String, String[]> map2) {

		super(map1);

		_map = map2;
	}

	@Override
	public void clear() {
		super.clear();

		_map.clear();
	}

	@Override
	public String[] put(String key, String[] value) {
		_map.put(key, value);

		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String[]> map) {
		super.putAll(map);

		_map.putAll(map);
	}

	@Override
	public String[] remove(Object key) {
		_map.remove(key);

		return super.remove(key);
	}

	private final Map<String, String[]> _map;

}