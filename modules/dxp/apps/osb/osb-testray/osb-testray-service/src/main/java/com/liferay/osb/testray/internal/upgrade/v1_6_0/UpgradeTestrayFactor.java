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

package com.liferay.osb.testray.internal.upgrade.v1_6_0;

import com.liferay.osb.testray.internal.upgrade.v1_5_0.BaseUpgradeIndex;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayFactor extends BaseUpgradeIndex {

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasIndex("OSB_TestrayFactor", "IX_B5A8AD86")) {
			return;
		}

		runSQL("drop index IX_5E2C79A1 on OSB_TestrayFactor");
		runSQL(
			"create unique index IX_B5A8AD86 on OSB_TestrayFactor " +
				"(classNameId, classPK, testrayFactorOptionId)");
	}

}