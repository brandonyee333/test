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

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155638740_SupportTeam extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140424155638740L;
	}

	protected void addSupportLaborId() throws Exception {
		if (tableHasColumn("OSB_SupportTeam", "supportLaborId")) {
			return;
		}

		runSQL("alter table OSB_SupportTeam add column supportLaborId LONG");

		runSQL("create index IX_92D16E1 on OSB_SupportTeam (supportLaborId)");
	}

	protected void addType() throws Exception {
		if (tableHasColumn("OSB_SupportTeam", "type_")) {
			return;
		}

		runSQL("alter table OSB_SupportTeam add column type_ INTEGER");

		runSQL("update OSB_SupportTeam set type_ = 0");
	}

	@Override
	protected void doUpgrade() throws Exception {
		addSupportLaborId();
		addType();
	}

}

*/

}