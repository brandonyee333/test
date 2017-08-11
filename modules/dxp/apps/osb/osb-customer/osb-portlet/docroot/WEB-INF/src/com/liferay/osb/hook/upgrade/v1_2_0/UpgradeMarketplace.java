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

package com.liferay.osb.hook.upgrade.v1_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Ryan Park
 */
public class UpgradeMarketplace extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateAppEntry();
		updateAppVersion();
	}

	protected void updateAppEntry() throws Exception {
		if (tableHasColumn("OSB_AppEntry", "statusByUserId")) {
			return;
		}

		runSQL("alter table OSB_AppEntry add column statusByUserId LONG");
		runSQL("alter table OSB_AppEntry add column statusByUserName STRING");
		runSQL("alter table OSB_AppEntry add column statusDate DATE");

		runSQL("update OSB_AppEntry set statusByUserId = userId");
		runSQL("update OSB_AppEntry set statusByUserName = userName");
		runSQL("update OSB_AppEntry set statusDate = createDate");
	}

	protected void updateAppVersion() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "statusByUserId")) {
			return;
		}

		runSQL("alter table OSB_AppVersion add column statusByUserId LONG");
		runSQL("alter table OSB_AppVersion add column statusByUserName STRING");
		runSQL("alter table OSB_AppVersion add column statusDate DATE");

		runSQL("update OSB_AppVersion set statusByUserId = userId");
		runSQL("update OSB_AppVersion set statusByUserName = userName");
		runSQL("update OSB_AppVersion set statusDate = createDate");
	}

	 */

}