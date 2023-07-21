/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

/**
 * @author Iván Zaera
 * @author Jorge Ferrer
 */
public class CompanyServiceSettingsLocator implements SettingsLocator {

	public CompanyServiceSettingsLocator(long companyId, String settingsId) {
		_companyId = companyId;
		_settingsId = settingsId;

		_configurationPid = settingsId;
	}

	public CompanyServiceSettingsLocator(
		long companyId, String settingsId, String configurationPid) {

		_companyId = companyId;
		_settingsId = settingsId;
		_configurationPid = configurationPid;
	}

	@Override
	public Settings getSettings() {
		Settings configurationBeanSettings =
			_settingsLocatorHelper.getConfigurationBeanSettings(
				_configurationPid);

		Settings portalPreferencesSettings =
			_settingsLocatorHelper.getPortalPreferencesSettings(
				_companyId, configurationBeanSettings);

		return _settingsLocatorHelper.getCompanyPortletPreferencesSettings(
			_companyId, _settingsId, portalPreferencesSettings);
	}

	@Override
	public String getSettingsId() {
		return _settingsId;
	}

	private final long _companyId;
	private final String _configurationPid;
	private final String _settingsId;
	private final SettingsLocatorHelper _settingsLocatorHelper =
		SettingsLocatorHelperUtil.getSettingsLocatorHelper();

}