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

package com.liferay.osb.hook.upgrade.v2_2_4;

import com.liferay.osb.model.impl.AccountEnvironmentModelImpl;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 */
public class UpgradeAccountEnvironment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEnvironment();
	}

	protected void updateAccountEnvironment() throws Exception {
		if (hasTable("OSB_AccountEnvironment")) {
			return;
		}

		runSQL(AccountEnvironmentModelImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_EE986C81 on OSB_AccountEnvironment " +
				"(accountEntryId)");
		runSQL(
			"create index IX_CC4721B3 on OSB_AccountEnvironment " +
				"(accountEntryId, productEntryId)");
	}

}