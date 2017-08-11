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

package com.liferay.osb.hook.upgrade.v2_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Lin Cui
 */
public class UpgradeTicketComment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateTicketComment();
	}

	protected void updateTicketComment() throws Exception {
		if (tableHasColumn("OSB_TicketComment", "format")) {
			return;
		}

		runSQL("alter table OSB_TicketComment add column format VARCHAR(75)");

		runSQL("update OSB_TicketComment set format = 'plain'");
	}

	 */

}