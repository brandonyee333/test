/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeWebModuleRelease;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class SocialNetworkingWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		BaseUpgradeWebModuleRelease baseUpgradeWebModuleRelease =
			new BaseUpgradeWebModuleRelease() {

				@Override
				protected String getBundleSymbolicName() {
					return "com.liferay.social.networking.web";
				}

				@Override
				protected String[] getPortletIds() {
					return new String[] {
						"1_WAR_socialnetworkingportlet",
						"2_WAR_socialnetworkingportlet",
						"3_WAR_socialnetworkingportlet",
						"4_WAR_socialnetworkingportlet",
						"5_WAR_socialnetworkingportlet",
						"6_WAR_socialnetworkingportlet",
						"7_WAR_socialnetworkingportlet",
						"8_WAR_socialnetworkingportlet"
					};
				}

			};

		try {
			baseUpgradeWebModuleRelease.upgrade();
		}
		catch (UpgradeException ue) {
			throw new RuntimeException(ue);
		}

		registry.register(
			"com.liferay.social.networking.web", "0.0.1", "1.0.4",
			new DummyUpgradeStep());
	}

}