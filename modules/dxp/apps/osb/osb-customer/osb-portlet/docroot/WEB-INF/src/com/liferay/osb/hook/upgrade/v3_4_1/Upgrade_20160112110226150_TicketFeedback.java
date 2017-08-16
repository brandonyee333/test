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

package com.liferay.osb.hook.upgrade.v3_4_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160112110226150_TicketFeedback
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160112110226150L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_TicketFeedback", "satisfied")) {
			return;
		}

		runSQL("alter table OSB_TicketFeedback add satisfied INTEGER");

		runSQL("update OSB_TicketFeedback set satisfied = 3");
	}

}