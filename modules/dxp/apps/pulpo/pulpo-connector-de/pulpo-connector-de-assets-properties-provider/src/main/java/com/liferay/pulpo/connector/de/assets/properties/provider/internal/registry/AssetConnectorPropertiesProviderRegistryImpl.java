/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.assets.properties.provider.internal.registry;

import com.liferay.pulpo.connector.de.assets.properties.provider.AssetConnectorPropertiesProvider;
import com.liferay.pulpo.connector.de.assets.properties.provider.registry.AssetConnectorPropertiesProviderRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(service = AssetConnectorPropertiesProviderRegistry.class)
public class AssetConnectorPropertiesProviderRegistryImpl
	implements AssetConnectorPropertiesProviderRegistry {

	@Override
	public AssetConnectorPropertiesProvider getPropertiesProvider(
		String className) {

		return _propertiesProviders.get(className);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		unbind = "unregisterPropertiesProvider"
	)
	public void registerPropertiesProvider(
		AssetConnectorPropertiesProvider propertiesProvider) {

		_propertiesProviders.put(
			propertiesProvider.getClassName(), propertiesProvider);
	}

	public void unregisterPropertiesProvider(
		AssetConnectorPropertiesProvider propertiesProvider) {

		_propertiesProviders.remove(propertiesProvider);
	}

	private final Map<String, AssetConnectorPropertiesProvider>
		_propertiesProviders = new ConcurrentHashMap<>();

}