/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_18_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brent Krone-Schmidt
 */
public class UpgradeTestrayBuild extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_TestrayBuild", "descriptionType")) {
			runSQL(
				"alter table OSB_TestrayBuild add descriptionType " +
					"VARCHAR(75) null");
			runSQL(
				"update OSB_TestrayBuild set descriptionType = 'plain-text'");
		}
	}

}