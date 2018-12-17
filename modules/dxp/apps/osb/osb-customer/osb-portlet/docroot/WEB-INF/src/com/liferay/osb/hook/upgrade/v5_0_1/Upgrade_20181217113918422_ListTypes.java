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

package com.liferay.osb.hook.upgrade.v5_0_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20181217113918422_ListTypes extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20181217113918422L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			27066, "websphere-8.5.5-fix-pack-11+",
			"com.liferay.osb.model.AccountEnvironment.envAS");

		runSQL(
			"update OSB_AccountEnvironment set envAS = 27066 where envAS = " +
				"27047 and (envLFR = 41000 or envLFR = 41100)");
	}

}