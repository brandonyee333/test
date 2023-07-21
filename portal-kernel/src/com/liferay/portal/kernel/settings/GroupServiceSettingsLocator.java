/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.settings;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

/**
 * @author Iván Zaera
 * @author Jorge Ferrer
 */
public class GroupServiceSettingsLocator implements SettingsLocator {

	public GroupServiceSettingsLocator(long groupId, String settingsId) {
		_groupId = groupId;
		_settingsId = settingsId;

		_configurationPid = settingsId;
	}

	public GroupServiceSettingsLocator(
		long groupId, String settingsId, String configurationPid) {

		_groupId = groupId;
		_settingsId = settingsId;
		_configurationPid = configurationPid;
	}

	@Override
	public Settings getSettings() throws SettingsException {
		long companyId = getCompanyId(_groupId);

		Settings configurationBeanSettings =
			_settingsLocatorHelper.getConfigurationBeanSettings(
				_configurationPid);

		Settings portalPreferencesSettings =
			_settingsLocatorHelper.getPortalPreferencesSettings(
				companyId, configurationBeanSettings);

		Settings companyPortletPreferencesSettings =
			_settingsLocatorHelper.getCompanyPortletPreferencesSettings(
				companyId, _settingsId, portalPreferencesSettings);

		return _settingsLocatorHelper.getGroupPortletPreferencesSettings(
			_groupId, _settingsId, companyPortletPreferencesSettings);
	}

	@Override
	public String getSettingsId() {
		return _settingsId;
	}

	protected long getCompanyId(long groupId) throws SettingsException {
		try {
			Group group = GroupLocalServiceUtil.getGroup(groupId);

			return group.getCompanyId();
		}
		catch (PortalException pe) {
			throw new SettingsException(pe);
		}
	}

	private final String _configurationPid;
	private final long _groupId;
	private final String _settingsId;
	private final SettingsLocatorHelper _settingsLocatorHelper =
		SettingsLocatorHelperUtil.getSettingsLocatorHelper();

}