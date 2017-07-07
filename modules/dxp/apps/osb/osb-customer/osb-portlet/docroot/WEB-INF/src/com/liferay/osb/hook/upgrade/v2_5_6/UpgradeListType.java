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

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Alan Zhang
 * @author Sharon Li
 */
public class UpgradeListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateListType();
	}

	protected void updateListType() throws Exception {
		try {
			insertListType(
				33017, "resolved-in-production",
				"com.liferay.osb.model.TicketEntry.status");
		}
		catch (Exception e) {
		}

		runSQL(
			"delete from ListType where listTypeId = " +
				UpgradeTicketStatus.STATUS_CUSTOMER_TESTING);
		runSQL(
			"delete from ListType where listTypeId = " +
				UpgradeTicketStatus.STATUS_PATCH_IN_QA);
		runSQL(
			"delete from ListType where listTypeId = " +
				UpgradeTicketStatus.STATUS_PATCH_NOT_WORKING);
		runSQL(
			"delete from ListType where listTypeId = " +
				UpgradeTicketStatus.STATUS_REVIEWING_PATCH);
	}

}

*/

}