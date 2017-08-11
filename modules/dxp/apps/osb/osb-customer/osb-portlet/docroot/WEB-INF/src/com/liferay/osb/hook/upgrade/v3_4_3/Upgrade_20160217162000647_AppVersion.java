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

package com.liferay.osb.hook.upgrade.v3_4_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.PortletPropsValues;

*/

/**
 * @author Yury Butrymovich
 */
public class Upgrade_20160217162000647_AppVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160217162000647L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "hidden_")) {
			return;
		}

		runSQL(
			"alter table OSB_AppVersion add column hidden_ BOOLEAN default" +
				" false");

		runSQL(
			"update OSB_AppVersion set hidden_ = true where appEntryId = " +
				PortletPropsValues.MARKETPLACE_APP_ENTRY_ID);
	}

	 */

}