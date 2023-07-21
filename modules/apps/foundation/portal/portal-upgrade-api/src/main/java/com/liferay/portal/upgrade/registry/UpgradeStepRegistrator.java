/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.registry;

import com.liferay.portal.kernel.upgrade.UpgradeStep;

/**
 * @author Carlos Sierra Andrés
 */
public interface UpgradeStepRegistrator {

	public void register(Registry registry);

	public interface Registry {

		public void register(
			String bundleSymbolicName, String fromSchemaVersionString,
			String toSchemaVersionString, UpgradeStep... upgradeSteps);

	}

}