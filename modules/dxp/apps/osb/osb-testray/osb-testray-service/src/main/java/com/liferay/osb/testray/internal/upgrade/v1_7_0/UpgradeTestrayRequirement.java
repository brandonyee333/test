/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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