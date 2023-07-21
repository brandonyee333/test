/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.internal.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Timothy Bell
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class TokenAuthServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.loop.token.auth.service", "1.0.0", "1.1.0",
			new com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0.
				UpgradeClassName(),
			new com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0.
				UpgradeResourceAction(),
			new com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0.
				UpgradeResourcePermission(),
			new com.liferay.osb.loop.token.auth.internal.upgrade.v1_1_0.
				UpgradeTokenAuthPortletId());
	}

}