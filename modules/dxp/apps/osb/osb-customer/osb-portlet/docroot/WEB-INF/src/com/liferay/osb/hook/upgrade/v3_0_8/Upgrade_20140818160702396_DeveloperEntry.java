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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.WorkflowConstants;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140818160702396_DeveloperEntry
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140818160702396L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeDeveloperEntry();
	}

	protected void upgradeDeveloperEntry() throws Exception {
		if (tableHasColumn("OSB_DeveloperEntry", "domainName")) {
			return;
		}

		runSQL("alter table OSB_DeveloperEntry add column domainName STRING");
		runSQL(
			"alter table OSB_DeveloperEntry add column domainStatus INTEGER");

		runSQL(
			"create index IX_4256345C on OSB_DeveloperEntry " +
				"(domainName, domainStatus)", false);

		runSQL(
			"update OSB_DeveloperEntry set domainStatus = " +
				WorkflowConstants.STATUS_EXPIRED, false);
	}

}
*/

}