/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_12_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayCase extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_TestrayCase modify steps TEXT");

		if (!hasColumn("OSB_TestrayCase", "stepsType")) {
			runSQL("alter table OSB_TestrayCase drop column inputStyleType");
			runSQL(
				"alter table OSB_TestrayCase add stepsType VARCHAR(75) null");
			runSQL("update OSB_TestrayCase set stepsType = 'plain-text'");
		}

		if (!hasColumn("OSB_TestrayCase", "descriptionType")) {
			runSQL(
				"alter table OSB_TestrayCase add descriptionType VARCHAR(75) " +
					"null");
			runSQL("update OSB_TestrayCase set descriptionType = 'plain-text'");
		}
	}

}