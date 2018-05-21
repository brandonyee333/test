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

package com.liferay.osb.hook.upgrade.v3_1_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Alan Zhang
 */
public class Upgrade_20140731010631183_AccountAttachment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140731010631183L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_AccountAttachment", "accountProjectId")) {
			return;
		}

		runSQL(
			"alter table OSB_AccountAttachment add column accountProjectId " +
				"LONG");

		runSQL("update OSB_AccountAttachment set accountProjectId = 0");

		runSQL(
			"create index IX_F1A6D304 on OSB_AccountAttachment (" +
				"accountEntryId, accountProjectId)");
		runSQL(
			"create index IX_1B961F96 on OSB_AccountAttachment (" +
				"accountEntryId, accountProjectId, fileName, type_)");
		runSQL(
			"create index IX_D44EEA51 on OSB_AccountAttachment (" +
				"accountEntryId, accountProjectId, type_)");
	}

}