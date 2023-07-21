/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.dao.orm.custom.sql;

import com.liferay.petra.concurrent.ConcurrentReferenceKeyHashMap;
import com.liferay.petra.memory.FinalizeManager;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;

/**
 * @author     Peter Fellwock
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class CustomSQLPool {

	public CustomSQLPool() {
		_maps = new ConcurrentReferenceKeyHashMap<>(
			FinalizeManager.WEAK_REFERENCE_FACTORY);
	}

	public void clear() {
		_maps = null;

		_maps = new ConcurrentReferenceKeyHashMap<>(
			FinalizeManager.WEAK_REFERENCE_FACTORY);
	}

	public String get(BundleContext bundleContext, String id) {
		Map<String, String> map = _maps.get(bundleContext);

		if (map != null) {
			return map.get(id);
		}

		return null;
	}

	public String get(Map<String, String> map, String id) {
		return map.get(id);
	}

	public String get(String id) {
		for (Map.Entry<BundleContext, Map<String, String>> entry :
				_maps.entrySet()) {

			if (entry.getKey() == null) {
				continue;
			}

			Map<String, String> map = entry.getValue();

			if (map == null) {
				continue;
			}

			String content = map.get(id);

			if (content != null) {
				return content;
			}
		}

		return null;
	}

	public boolean isBundleContextLoaded(BundleContext bundleContext) {
		Map<String, String> map = _maps.get(bundleContext);

		if (map != null) {
			return true;
		}

		return false;
	}

	public void put(BundleContext bundleContext, String id, String content) {
		Map<String, String> map = _maps.get(bundleContext);

		if (map == null) {
			map = new HashMap<>();

			_maps.put(bundleContext, map);
		}

		map.put(id, content);
	}

	private static Map<BundleContext, Map<String, String>> _maps =
		new ConcurrentReferenceKeyHashMap<>(
			FinalizeManager.WEAK_REFERENCE_FACTORY);

}