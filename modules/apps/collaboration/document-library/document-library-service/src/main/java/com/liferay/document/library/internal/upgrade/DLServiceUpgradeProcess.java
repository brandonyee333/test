/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.upgrade;

import com.liferay.document.library.internal.upgrade.v1_0_0.UpgradeDocumentLibrary;
import com.liferay.document.library.internal.upgrade.v1_0_2.UpgradeDLFileShortcut;
import com.liferay.document.library.internal.upgrade.v1_1_0.UpgradeSchema;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Miguel Pastor
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class DLServiceUpgradeProcess implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.document.library.service", "0.0.0", "1.0.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.document.library.service", "0.0.1", "1.0.0",
			new UpgradeDocumentLibrary(_store));

		registry.register(
			"com.liferay.document.library.service", "1.0.0", "1.0.1",
			new UpgradeDLFileShortcut());

		registry.register(
			"com.liferay.document.library.service", "1.0.1", "1.1.0",
			new UpgradeSchema());
	}

	@Reference(target = "(dl.store.upgrade=true)")
	private Store _store;

}