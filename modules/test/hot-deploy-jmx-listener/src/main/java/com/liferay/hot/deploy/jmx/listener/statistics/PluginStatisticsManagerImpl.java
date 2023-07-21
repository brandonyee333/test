/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.hot.deploy.jmx.listener.statistics;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = PluginStatisticsManager.class)
public class PluginStatisticsManagerImpl implements PluginStatisticsManager {

	@Override
	public List<String> getRegisteredLegacyPlugins() {
		return Collections.unmodifiableList(_servletContextNames);
	}

	@Override
	public boolean registerLegacyPlugin(String servletContextName) {
		return _servletContextNames.addIfAbsent(servletContextName);
	}

	@Override
	public boolean unregisterLegacyPlugin(String servletContextName) {
		return _servletContextNames.remove(servletContextName);
	}

	private final CopyOnWriteArrayList<String> _servletContextNames =
		new CopyOnWriteArrayList<>();

}