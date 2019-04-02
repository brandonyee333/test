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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20150225100704898_AccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_AccountEntry", "tier")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry change priority tier INTEGER");

		runSQL("update OSB_AccountEntry set tier = 2 where code_ like '%OEM%'");
		runSQL("update OSB_AccountEntry set tier = 2 where tier = 1");
		runSQL("update OSB_AccountEntry set tier = 1 where tier = 0");
	}

}