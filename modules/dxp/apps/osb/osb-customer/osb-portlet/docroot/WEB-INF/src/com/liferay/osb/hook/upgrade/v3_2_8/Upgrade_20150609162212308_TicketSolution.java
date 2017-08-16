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

package com.liferay.osb.hook.upgrade.v3_2_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.TicketSolutionImpl;

/**
 * @author Amos Fong
 */
public class Upgrade_20150609162212308_TicketSolution
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150609162212308L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn(TicketSolutionImpl.TABLE_NAME, "reviewForKB")) {
			runSQL(
				"alter table " + TicketSolutionImpl.TABLE_NAME + " add " +
					"reviewForKB BOOLEAN");
		}

		if (!hasColumn(TicketSolutionImpl.TABLE_NAME, "issueType")) {
			runSQL(
				"alter table " + TicketSolutionImpl.TABLE_NAME + " add " +
					"issueType INTEGER");
		}
	}

}