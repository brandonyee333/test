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

package com.liferay.osb.hook.upgrade.v3_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20150128090715904_TicketComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150128090715904L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasIndex("OSB_TicketComment", "IX_565F15CA")) {
			return;
		}

		runSQL(
			"create index IX_565F15CA on OSB_TicketComment (" +
				"userId, ticketEntryId, visibility, status, type_)");
	}

}

*/

}