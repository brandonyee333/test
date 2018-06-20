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

package com.liferay.osb.hook.upgrade.v3_7_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20170713110007653_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170713110007653L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"create index IX_D879F1BE on OSB_OfferingEntry (accountEntryId, " +
				"productEntryId, type_)");
		runSQL(
			"create index IX_1BCFB30 on OSB_OfferingEntry (accountEntryId, " +
				"type_)");
	}

}