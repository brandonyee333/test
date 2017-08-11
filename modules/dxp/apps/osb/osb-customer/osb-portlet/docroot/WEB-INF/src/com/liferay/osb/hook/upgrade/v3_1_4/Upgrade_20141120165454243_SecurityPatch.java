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

package com.liferay.osb.hook.upgrade.v3_1_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.SecurityPatchModelImpl;

*/

/**
 * @author Alan Zhang
 */
public class Upgrade_20141120165454243_SecurityPatch extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20141120165454243L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("OSB_SecurityPatch")) {
			return;
		}

		runSQL(SecurityPatchModelImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_18BFB3A4 on OSB_SecurityPatch (accountEntryId, " +
				"portletId)");
		runSQL("create index IX_36A4B1D8 on OSB_SecurityPatch (portletId)");
	}

	 */

}