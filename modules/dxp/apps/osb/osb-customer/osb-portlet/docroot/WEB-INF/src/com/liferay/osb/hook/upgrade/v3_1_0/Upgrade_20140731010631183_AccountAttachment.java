/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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