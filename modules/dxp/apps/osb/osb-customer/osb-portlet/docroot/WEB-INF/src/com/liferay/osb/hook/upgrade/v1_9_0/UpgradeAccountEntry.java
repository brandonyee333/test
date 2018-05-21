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

package com.liferay.osb.hook.upgrade.v1_9_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeAccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEntry();
	}

	protected void updateAccountEntry() throws Exception {
		if (hasColumn("OSB_AccountEntry", "redirectAccountEntryId")) {
			return;
		}

		runSQL(
			"alter table OSB_AccountEntry add column redirectAccountEntryId " +
				"LONG");

		runSQL(
			"create index IX_C2D8D61C on OSB_AccountEntry " +
				"(redirectAccountEntryId)");

		runSQL("update OSB_AccountEntry set redirectAccountEntryId = 0");
	}

}