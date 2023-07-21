/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_17_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brent Krone-Schmidt
 */
public class UpgradeTestrayRequirement extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_TestrayRequirement", "goals")) {
			runSQL("alter table OSB_TestrayRequirement add goals STRING null");
		}

		if (!hasColumn("OSB_TestrayRequirement", "goalsType")) {
			runSQL(
				"alter table OSB_TestrayRequirement add goalsType " +
					"VARCHAR(75) null");
			runSQL(
				"update OSB_TestrayRequirement set goalsType = 'plain-text'");
		}

		if (!hasColumn("OSB_TestrayRequirement", "variations")) {
			runSQL(
				"alter table OSB_TestrayRequirement add variations STRING " +
					"null");
		}

		if (!hasColumn("OSB_TestrayRequirement", "variationsType")) {
			runSQL(
				"alter table OSB_TestrayRequirement add variationsType " +
					"VARCHAR(75) null");
			runSQL(
				"update OSB_TestrayRequirement set variationsType = " +
					"'plain-text'");
		}
	}

}