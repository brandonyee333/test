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

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160809121913146_AccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160809121913146L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		removeLiferayTrialAccountCustomers();
		updateIndexes();
	}

	protected void removeLiferayTrialAccountCustomers() throws Exception {
		runSQL(
			"delete from OSB_AccountCustomer where accountEntryId = " +
				OSBConstants.ACCOUNT_ENTRY_TRIAL_ID);
	}

	protected void updateIndexes() throws Exception {
		if (!hasIndex("OSB_AccountEntry", "IX_31DB3E6F")) {
			runSQL(
				"create index IX_31DB3E6F on OSB_AccountEntry (userId, type_)");
		}

		if (!hasIndex("OSB_AccountEntry", "IX_77FBC39")) {
			runSQL(
				"create index IX_77FBC39 on OSB_AccountEntry (" +
					"redirectAccountEntryId, type_)");
		}
	}

}
*/

}