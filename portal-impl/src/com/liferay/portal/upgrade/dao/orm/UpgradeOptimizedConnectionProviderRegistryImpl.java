/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.dao.orm;

import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.upgrade.dao.orm.UpgradeOptimizedConnectionProvider;
import com.liferay.portal.kernel.upgrade.dao.orm.UpgradeOptimizedConnectionProviderRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author Cristina González
 */
public class UpgradeOptimizedConnectionProviderRegistryImpl
	implements UpgradeOptimizedConnectionProviderRegistry {

	public UpgradeOptimizedConnectionProviderRegistryImpl() {
		ServiceLoader<UpgradeOptimizedConnectionProvider> serviceLoader =
			ServiceLoader.load(
				UpgradeOptimizedConnectionProvider.class,
				UpgradeOptimizedConnectionProviderRegistryImpl.class.
					getClassLoader());

		for (UpgradeOptimizedConnectionProvider
				upgradeOptimizedConnectionProvider : serviceLoader) {

			_upgradeOptimizedConnectionProviders.put(
				upgradeOptimizedConnectionProvider.getDBType(),
				upgradeOptimizedConnectionProvider);
		}
	}

	@Override
	public UpgradeOptimizedConnectionProvider
		getUpgradeOptimizedConnectionProvider(DBType dbType) {

		return _upgradeOptimizedConnectionProviders.get(dbType);
	}

	private final Map<DBType, UpgradeOptimizedConnectionProvider>
		_upgradeOptimizedConnectionProviders = new HashMap<>();

}