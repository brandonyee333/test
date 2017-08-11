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

package com.liferay.osb.hook.upgrade.v2_5_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.impl.TicketSolutionImpl;

*/

/**
 * @author Amos Fong
 */
public class UpgradeTicketSolution extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateTicketSolution();
	}

	protected void updateTicketSolution() throws Exception {
		if (hasTable("OSB_TicketSolution")) {
			return;
		}

		runSQL(TicketSolutionImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_F1F54CE5 on OSB_TicketSolution (ticketEntryId)");
		runSQL(
			"create index IX_A75ED6CB on OSB_TicketSolution (ticketEntryId, " +
				"status)");
	}

	 */

}