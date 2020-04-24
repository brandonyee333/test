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