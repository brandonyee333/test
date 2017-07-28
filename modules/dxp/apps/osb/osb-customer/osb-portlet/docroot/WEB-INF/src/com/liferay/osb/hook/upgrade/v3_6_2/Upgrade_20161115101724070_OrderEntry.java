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

package com.liferay.osb.hook.upgrade.v3_6_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20161115101724070_OrderEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161115101724070L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_OrderEntry", "status")) {
			runSQL("alter table OSB_OrderEntry add column status INTEGER");
			runSQL(
				"alter table OSB_OrderEntry add column statusByUserId BIGINT");
			runSQL(
				"alter table OSB_OrderEntry add column statusByUserName " +
					"VARCHAR(75)");
			runSQL("alter table OSB_OrderEntry add column statusDate DATETIME");
			runSQL(
				"alter table OSB_OrderEntry add column statusMessage LONGTEXT");

			runSQL("update OSB_OrderEntry set status = 0");
		}
	}

}

*/

}