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

package com.liferay.osb.hook.upgrade.v3_3_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20150605115752232_AccountCustomer
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasIndex("OSB_AccountCustomer", "IX_A47A5E94")) {
			runSQL(
				"create index IX_A47A5E94 on OSB_AccountCustomer (userId, " +
					"role)");
		}
	}

}