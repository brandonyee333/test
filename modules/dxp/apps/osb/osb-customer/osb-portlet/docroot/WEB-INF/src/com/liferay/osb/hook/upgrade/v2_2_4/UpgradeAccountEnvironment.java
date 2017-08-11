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

package com.liferay.osb.hook.upgrade.v2_2_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.impl.AccountEnvironmentModelImpl;

*/

/**
 * @author Lin Cui
 */
public class UpgradeAccountEnvironment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
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

	 */

}