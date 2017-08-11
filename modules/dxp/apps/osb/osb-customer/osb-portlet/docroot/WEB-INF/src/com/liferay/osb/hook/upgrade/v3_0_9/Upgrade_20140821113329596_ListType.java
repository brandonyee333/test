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

package com.liferay.osb.hook.upgrade.v3_0_9;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Sharon Li
 */
public class Upgrade_20140821113329596_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140821113329596L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			26025, "account-administration",
			"com.liferay.osb.model.TicketEntry.component");
		insertListType(
			26026, "license", "com.liferay.osb.model.TicketEntry.component");
		insertListType(
			26027, "patch-management",
			"com.liferay.osb.model.TicketEntry.component");
		insertListType(
			26028, "workflows-forms",
			"com.liferay.osb.model.TicketEntry.component");
	}

	 */

}