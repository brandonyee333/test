/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.internal.upgrade;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Calvin Keum
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class LoopServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.loop.service", "1.0.0", "1.1.0",
			new com.liferay.osb.loop.internal.upgrade.v1_1_0.UpgradeClassName(),
			new com.liferay.osb.loop.internal.upgrade.v1_1_0.
				UpgradeLoopPortletId(),
			new com.liferay.osb.loop.internal.upgrade.v1_1_0.
				UpgradeLoopUserNotificationEvent(),
			new com.liferay.osb.loop.internal.upgrade.v1_1_0.
				UpgradeResourceAction(),
			new com.liferay.osb.loop.internal.upgrade.v1_1_0.
				UpgradeResourcePermission(
					_counterLocalService, _resourceActionLocalService,
					_resourcePermissionLocalService, _roleLocalService));
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}