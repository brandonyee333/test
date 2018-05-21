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

package com.liferay.osb.hook.upgrade.v3_1_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.SecurityPatchModelImpl;

/**
 * @author Alan Zhang
 */
public class Upgrade_20141120165454243_SecurityPatch
	extends BaseUpgradeProcess {

	@Override
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

}