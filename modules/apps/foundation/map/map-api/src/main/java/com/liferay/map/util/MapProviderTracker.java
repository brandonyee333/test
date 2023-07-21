/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.map.util;

import com.liferay.map.MapProvider;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = MapProviderTracker.class)
public class MapProviderTracker {

	public MapProvider getMapProvider(String mapProviderKey) {
		return _mapProviders.get(mapProviderKey);
	}

	public Collection<MapProvider> getMapProviders() {
		return _mapProviders.values();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected synchronized void registerMapProvider(MapProvider mapProvider) {
		_mapProviders.put(mapProvider.getKey(), mapProvider);
	}

	protected synchronized void unregisterMapProvider(MapProvider mapProvider) {
		_mapProviders.remove(mapProvider.getKey());
	}

	private final Map<String, MapProvider> _mapProviders =
		new ConcurrentHashMap<>();

}