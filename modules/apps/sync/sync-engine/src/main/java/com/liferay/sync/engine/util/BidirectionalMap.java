/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class BidirectionalMap<K, V> extends HashMap<K, V> {

	@Override
	public void clear() {
		_invertedMap.clear();

		super.clear();
	}

	public K getKey(Object value) {
		return _invertedMap.get(value);
	}

	@Override
	public V put(K key, V value) {
		_invertedMap.put(value, key);

		return super.put(key, value);
	}

	public K removeValue(Object value) {
		K key = _invertedMap.remove(value);

		if (key != null) {
			super.remove(key);
		}

		return key;
	}

	private final Map<V, K> _invertedMap = new HashMap<>();

}