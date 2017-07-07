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

package com.liferay.osb.hook.upgrade.v3_6_9;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20170227113523982_AccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20170227113523982L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_AccountEntry add column lastAuditDate DATE");

		runSQL("drop index IX_77FBC39 on OSB_AccountEntry");

		runSQL(
			"create index IX_5F5D3902 on OSB_AccountEntry " +
				"(redirectAccountEntryId, status)");
		runSQL(
			"create index IX_E57EB21F on OSB_AccountEntry " +
				"(redirectAccountEntryId, type_, status)");
	}

}
*/

}