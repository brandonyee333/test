/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.module.configuration;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.settings.SettingsLocator;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerList;

/**
 * @author Jorge Ferrer
 */
public class ConfigurationProviderUtil {

	public static <T> T getCompanyConfiguration(Class<T> clazz, long companyId)
		throws ConfigurationException {

		ConfigurationProvider configurationProvider =
			getConfigurationProvider();

		return configurationProvider.getCompanyConfiguration(clazz, companyId);
	}

	public static <T> T getConfiguration(
			Class<T> clazz, SettingsLocator settingsLocator)
		throws ConfigurationException {

		ConfigurationProvider configurationProvider =
			getConfigurationProvider();

		return configurationProvider.getConfiguration(clazz, settingsLocator);
	}

	public static ConfigurationProvider getConfigurationProvider() {
		PortalRuntimePermission.checkGetBeanProperty(
			ConfigurationProviderUtil.class);

		return _configurationProvider.get(0);
	}

	public static <T> T getGroupConfiguration(Class<T> clazz, long groupId)
		throws ConfigurationException {

		ConfigurationProvider configurationProvider =
			getConfigurationProvider();

		return configurationProvider.getGroupConfiguration(clazz, groupId);
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getPortletInstanceConfiguration(Class, Layout, String)}
	 */
	@Deprecated
	public static <T> T getPortletInstanceConfiguration(
			Class<T> clazz, Layout layout, PortletInstance portletInstance)
		throws ConfigurationException {

		return getPortletInstanceConfiguration(
			clazz, layout, portletInstance.getPortletInstanceKey());
	}

	public static <T> T getPortletInstanceConfiguration(
			Class<T> clazz, Layout layout, String portletId)
		throws ConfigurationException {

		ConfigurationProvider configurationProvider =
			getConfigurationProvider();

		return configurationProvider.getPortletInstanceConfiguration(
			clazz, layout, portletId);
	}

	public static <T> T getSystemConfiguration(Class<T> clazz)
		throws ConfigurationException {

		ConfigurationProvider configurationProvider =
			getConfigurationProvider();

		return configurationProvider.getSystemConfiguration(clazz);
	}

	private static final ServiceTrackerList<ConfigurationProvider>
		_configurationProvider = ServiceTrackerCollections.openList(
			ConfigurationProvider.class);

}