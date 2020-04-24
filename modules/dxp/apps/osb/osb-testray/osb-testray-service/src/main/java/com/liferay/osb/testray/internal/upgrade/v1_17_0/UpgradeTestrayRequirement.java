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