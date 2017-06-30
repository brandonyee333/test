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

package com.liferay.osb.hook.upgrade.v3_0_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.CorpGroupImpl;

/**
 * @author Rachael Koestartyo
 */
public class Upgrade_20140902190618422_CorpGroup extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140902190618422L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeCorpGroup();
	}

	protected void upgradeCorpGroup() throws Exception {
		if (hasTable(CorpGroupImpl.TABLE_NAME)) {
			return;
		}

		runSQL(CorpGroupImpl.TABLE_SQL_CREATE);
		runSQL(CorpGroupImpl.MAPPING_TABLE_OSB_CORPENTRY_CORPGROUP_SQL_CREATE);

		runSQL(
			"create index IX_2F99E8B0 on OSB_CorpEntry_CorpGroup (" +
				"corpEntryId)");
		runSQL(
			"create index IX_F3C8083D on OSB_CorpEntry_CorpGroup (" +
				"corpGroupId)");
	}

}