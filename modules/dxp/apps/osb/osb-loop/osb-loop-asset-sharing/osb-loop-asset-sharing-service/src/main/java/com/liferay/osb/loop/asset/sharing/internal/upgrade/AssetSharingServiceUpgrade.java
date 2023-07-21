/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.sharing.internal.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Calvin Keum
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class AssetSharingServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.loop.asset.sharing.service", "1.0.0", "1.1.0",
			new com.liferay.osb.loop.asset.sharing.internal.upgrade.v1_1_0.
				UpgradeAssetSharingPortletId(),
			new com.liferay.osb.loop.asset.sharing.internal.upgrade.v1_1_0.
				UpgradeClassName());
	}

}