/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.internal.upgrade;

import com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_1.UpgradeJIRAComponent;
import com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_2.UpgradeArtifactVersion;
import com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_2.UpgradePortletPreferences;
import com.liferay.osb.customer.release.tool.service.JIRAComponentLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class OSBCustomerReleaseToolUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.customer.release.tool.service", "1.0.0", "1.0.1",
			new UpgradeJIRAComponent(_jiraComponentLocalService));

		registry.register(
			"com.liferay.osb.customer.release.tool.service", "1.0.1", "1.0.2",
			new UpgradeArtifactVersion(),
			new UpgradePortletPreferences(_portletPreferencesLocalService));
	}

	@Reference
	private JIRAComponentLocalService _jiraComponentLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

}