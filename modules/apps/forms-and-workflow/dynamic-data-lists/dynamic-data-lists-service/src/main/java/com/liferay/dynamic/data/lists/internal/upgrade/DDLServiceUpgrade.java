/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.internal.upgrade;

import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeKernelPackage;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeLastPublishDate;
import com.liferay.dynamic.data.lists.internal.upgrade.v1_0_0.UpgradeSchema;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class DDLServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.dynamic.data.lists.service", "0.0.1", "0.0.2",
			new UpgradeSchema());

		registry.register(
			"com.liferay.dynamic.data.lists.service", "0.0.2", "0.0.3",
			new UpgradeKernelPackage());

		registry.register(
			"com.liferay.dynamic.data.lists.service", "0.0.3", "1.0.0",
			new UpgradeLastPublishDate());
	}

	@Reference(unbind = "-")
	protected void setDDMStructureLocalService(
		DDMStructureLocalService ddmStructureLocalService) {
	}

}