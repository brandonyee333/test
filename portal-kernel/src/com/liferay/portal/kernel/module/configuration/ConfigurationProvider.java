/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.module.configuration;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.settings.SettingsLocator;

/**
 * @author Jorge Ferrer
 */
@ProviderType
public interface ConfigurationProvider {

	public <T> T getCompanyConfiguration(Class<T> clazz, long companyId)
		throws ConfigurationException;

	public <T> T getConfiguration(
			Class<T> clazz, SettingsLocator settingsLocator)
		throws ConfigurationException;

	public <T> T getGroupConfiguration(Class<T> clazz, long groupId)
		throws ConfigurationException;

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getPortletInstanceConfiguration(Class, Layout, String)}
	 */
	@Deprecated
	public <T> T getPortletInstanceConfiguration(
			Class<T> clazz, Layout layout, PortletInstance portletInstance)
		throws ConfigurationException;

	public <T> T getPortletInstanceConfiguration(
			Class<T> clazz, Layout layout, String portletId)
		throws ConfigurationException;

	public <T> T getSystemConfiguration(Class<T> clazz)
		throws ConfigurationException;

}