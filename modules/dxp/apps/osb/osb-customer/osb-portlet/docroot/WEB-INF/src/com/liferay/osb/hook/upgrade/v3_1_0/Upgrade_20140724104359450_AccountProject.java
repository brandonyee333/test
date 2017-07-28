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
import com.liferay.osb.model.impl.AccountProjectImpl;

*/

/**
 * @author Alan Zhang
 */
public class Upgrade_20140724104359450_AccountProject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140724104359450L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable(AccountProjectImpl.TABLE_NAME)) {
			return;
		}

		runSQL(AccountProjectImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_E16E8A07 on OSB_AccountProject (accountEntryId)");
	}

}

*/

}