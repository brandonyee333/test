/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.shopping.upgrade.v1_0_0.UpgradeCompanyId;
import com.liferay.shopping.upgrade.v1_0_0.UpgradeKernelPackage;
import com.liferay.shopping.upgrade.v1_0_0.UpgradePortletId;
import com.liferay.shopping.upgrade.v1_0_0.UpgradeShopping;
import com.liferay.shopping.upgrade.v1_0_0.UpgradeShoppingPreferences;
import com.liferay.shopping.upgrade.v1_0_1.UpgradeShoppingEmailAddress;

import org.osgi.service.component.annotations.Component;

/**
 * @author Peter Fellwock
 * @author Manuel de la Peña
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ShoppingServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.shopping.service", "0.0.1", "0.0.2",
			new UpgradeKernelPackage(), new UpgradePortletId());

		registry.register(
			"com.liferay.shopping.service", "0.0.2", "1.0.0",
			new UpgradeCompanyId(), new UpgradeShopping(),
			new UpgradeShoppingPreferences());

		registry.register(
			"com.liferay.shopping.service", "1.0.0", "1.0.1",
			new UpgradeShoppingEmailAddress());
	}

}