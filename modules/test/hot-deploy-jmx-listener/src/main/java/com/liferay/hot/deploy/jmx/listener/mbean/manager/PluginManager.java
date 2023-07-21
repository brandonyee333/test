/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.hot.deploy.jmx.listener.mbean.manager;

import com.liferay.hot.deploy.jmx.listener.statistics.PluginStatisticsManager;

import java.util.List;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(
	immediate = true,
	property = {
		"jmx.objectname=com.liferay.portal.monitoring:classification=plugin_statistics,name=PluginsManager",
		"jmx.objectname.cache.key=PluginsManager"
	},
	service = DynamicMBean.class
)
public class PluginManager extends StandardMBean implements PluginMBeanManager {

	public PluginManager() throws NotCompliantMBeanException {
		super(PluginMBeanManager.class);
	}

	@Override
	public List<String> listLegacyPlugins() {
		return _pluginStatisticsManager.getRegisteredLegacyPlugins();
	}

	@Reference(unbind = "-")
	protected void setPluginStatistics(
		PluginStatisticsManager pluginStatisticsManager) {

		_pluginStatisticsManager = pluginStatisticsManager;
	}

	private PluginStatisticsManager _pluginStatisticsManager;

}