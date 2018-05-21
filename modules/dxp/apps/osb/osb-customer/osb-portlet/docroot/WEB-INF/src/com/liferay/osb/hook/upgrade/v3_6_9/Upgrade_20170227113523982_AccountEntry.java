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

package com.liferay.osb.hook.upgrade.v3_6_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20170227113523982_AccountEntry extends BaseUpgradeProcess {

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