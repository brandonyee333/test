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

package com.liferay.osb.hook.upgrade.v3_0_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Sharon Li
 */
public class Upgrade_20140506163210674_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140506163210674L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeListType();
	}

	protected void upgradeListType() {
		try {
			insertListType(
				34029, "primary-browser",
				"com.liferay.osb.model.AuditEntry.field");

			insertListType(
				37000, "chrome-28+",
				"com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37001, "firefox-20+",
				"com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37002, "ie-6", "com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37003, "ie-7", "com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37004, "ie-8", "com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37005, "ie-9", "com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37006, "ie-10", "com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37007, "safari-5",
				"com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37008, "safari-6",
				"com.liferay.osb.model.TicketEntry.envBrowser");
			insertListType(
				37999, "other", "com.liferay.osb.model.TicketEntry.envBrowser");
		}
		catch (Exception e) {
		}
	}

}