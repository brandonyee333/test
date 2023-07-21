/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_11_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayCase extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_TestrayCase set originationKey = testrayCaseId where " +
				"originationKey = 0");
	}

}