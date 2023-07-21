/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.web.internal.upgrade;

import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.upgrade.release.BaseUpgradeWebModuleRelease;
import com.liferay.social.privatemessaging.web.internal.upgrade.v1_0_0.UpgradePortletId;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class PrivateMessagingWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		BaseUpgradeWebModuleRelease baseUpgradeWebModuleRelease =
			new BaseUpgradeWebModuleRelease() {

				@Override
				protected String getBundleSymbolicName() {
					return "com.liferay.social.privatemessaging.web";
				}

				@Override
				protected String[] getPortletIds() {
					return new String[] {"1_WAR_privatemessagingportlet"};
				}

			};

		try {
			baseUpgradeWebModuleRelease.upgrade();
		}
		catch (UpgradeException ue) {
			throw new RuntimeException(ue);
		}

		registry.register(
			"com.liferay.social.privatemessaging.web", "0.0.0", "1.0.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.social.privatemessaging.web", "0.0.1", "1.0.1",
			new UpgradePortletId());

		// See LPS-65946

		registry.register(
			"com.liferay.social.privatemessaging.web", "1.0.0", "1.0.1",
			new UpgradePortletId());
	}

}