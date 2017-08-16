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

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155654192_SupportWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140424155654192L;
	}

	protected void addClockedIn() throws Exception {
		if (hasColumn("OSB_SupportWorker", "clockedIn")) {
			return;
		}

		runSQL("alter table OSB_SupportWorker add column clockedIn BOOLEAN");
	}

	protected void addSupportLaborId() throws Exception {
		if (hasColumn("OSB_SupportWorker", "supportLaborId")) {
			return;
		}

		runSQL("alter table OSB_SupportWorker add column supportLaborId LONG");

		runSQL("create index IX_C3CFE42 on OSB_SupportWorker (supportLaborId)");
	}

	@Override
	protected void doUpgrade() throws Exception {
		addClockedIn();
		addSupportLaborId();
	}

}