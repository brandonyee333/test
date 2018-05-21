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

package com.liferay.osb.hook.upgrade.v2_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Alan Zhang
 */
public class UpgradeAccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEntry();
	}

	protected void updateAccountEntry() throws Exception {
		if (hasColumn("OSB_AccountEntry", "priority")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry add column priority INTEGER");

		runSQL("update OSB_AccountEntry set priority = 0");
	}

}