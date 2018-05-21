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
import com.liferay.osb.model.impl.AccountInformationImpl;

/**
 * @author Alan Zhang
 */
public class Upgrade_20140724103011044_AccountInformation
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140724103011044L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable(AccountInformationImpl.TABLE_NAME)) {
			return;
		}

		runSQL(AccountInformationImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_3B5CFE75 on OSB_AccountInformation (" +
				"accountEntryId, accountProjectId)");
		runSQL(
			"create unique index IX_9644DED0 on OSB_AccountInformation (" +
				"accountEntryId, accountProjectId, fieldId)");
	}

}