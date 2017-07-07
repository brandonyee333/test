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

package com.liferay.osb.hook.upgrade.v3_7_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntryConstants;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170622142816707_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20170622142816707L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update ListType set name = 'tcServer-3.1' where listTypeId = " +
				TicketEntryConstants.ENV_AS_TCSERVER_3_1);

		insertListType(
			27060, "tcServer-3.2", "com.liferay.osb.model.TicketEntry.envAS");
	}

}
*/

}