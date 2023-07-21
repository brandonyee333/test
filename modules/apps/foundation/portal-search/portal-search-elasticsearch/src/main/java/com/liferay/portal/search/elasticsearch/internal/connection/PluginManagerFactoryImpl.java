/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import java.net.URL;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.env.Environment;

/**
 * @author André de Oliveira
 */
public class PluginManagerFactoryImpl implements PluginManagerFactory {

	public PluginManagerFactoryImpl(Settings settings) {
		_settings = settings;
	}

	@Override
	public PluginManager createPluginManager() {
		return doCreatePluginManager(null);
	}

	@Override
	public PluginManager createPluginManager(PluginZip pluginZip) {
		return doCreatePluginManager(pluginZip.getURL());
	}

	protected PluginManager doCreatePluginManager(URL url) {
		return new PluginManagerImpl(
			new Environment(_settings), url,
			org.elasticsearch.plugins.PluginManager.OutputMode.SILENT,
			TimeValue.timeValueMinutes(1));
	}

	private final Settings _settings;

}