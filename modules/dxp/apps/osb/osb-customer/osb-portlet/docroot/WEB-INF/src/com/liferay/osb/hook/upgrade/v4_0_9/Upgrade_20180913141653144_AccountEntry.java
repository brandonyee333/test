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

package com.liferay.osb.hook.upgrade.v4_0_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20180913141653144_AccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_AccountEntry", "dossieraAccountKey")) {
			return;
		}

		runSQL(
			"alter table OSB_AccountEntry add column dossieraAccountKey " +
				"VARCHAR(75)");

		runSQL(
			"create index IX_4304C568 on OSB_AccountEntry " +
				"(dossieraAccountKey)");
		runSQL(
			"create index IX_20B23F6D on OSB_AccountEntry " +
				"(dossieraAccountKey, type_)");
	}

}