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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.AccountInformationImpl;

*/

/**
 * @author Alan Zhang
 */
public class Upgrade_20140724103011044_AccountInformation
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
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

	 */

}