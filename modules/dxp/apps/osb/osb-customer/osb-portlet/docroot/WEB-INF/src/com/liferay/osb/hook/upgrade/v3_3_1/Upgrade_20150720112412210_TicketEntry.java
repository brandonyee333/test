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

package com.liferay.osb.hook.upgrade.v3_3_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.TicketEntryImpl;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150720112412210_TicketEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150720112412210L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn(TicketEntryImpl.TABLE_NAME, "subcomponentCustom")) {
			return;
		}

		runSQL(
			"alter table " + TicketEntryImpl.TABLE_NAME + " add " +
				"subcomponentCustom VARCHAR(150)");
	}

}

*/

}