/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_7_0;

import com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayRequirement extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("OSB_TestrayRequirement")) {
			return;
		}

		runSQL(TestrayRequirementModelImpl.TABLE_SQL_CREATE);
		runSQL(
			"create unique index IX_7F95BF16 on OSB_TestrayRequirement " +
				"(testrayProjectId, key_[$COLUMN_LENGTH:75$])");
		runSQL(
			TestrayRequirementModelImpl.
				MAPPING_TABLE_OSB_TESTRAYREQUIREMENTS_TESTRAYCASES_SQL_CREATE);
		runSQL(
			"create index IX_B3ED945F on " +
				"OSB_TestrayRequirements_TestrayCases (companyId)");
		runSQL(
			"create index IX_E933BD8A on " +
				"OSB_TestrayRequirements_TestrayCases (testrayCaseId)");
		runSQL(
			"create index IX_32059323 on " +
				"OSB_TestrayRequirements_TestrayCases (testrayRequirementId)");
	}

}