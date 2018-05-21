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

package com.liferay.osb.hook.upgrade.v3_0_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Sharon Li
 */
public class Upgrade_20140513113816789_AccountEnvironment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140513113816789L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAccountEnvironment();
	}

	protected void upgradeAccountEnvironment() throws Exception {
		if (!hasColumn("OSB_AccountEnvironment", "envOSCustom")) {
			runSQL(
				"alter table OSB_AccountEnvironment add column envOSCustom " +
					"VARCHAR(150)");
		}
	}

}