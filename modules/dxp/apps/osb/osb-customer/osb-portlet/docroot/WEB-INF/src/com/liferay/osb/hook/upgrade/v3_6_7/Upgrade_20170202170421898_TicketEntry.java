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

package com.liferay.osb.hook.upgrade.v3_6_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketInformation;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketInformationLocalServiceUtil;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170202170421898_TicketEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20170202170421898L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		TicketInformation ticketInformation =
			TicketInformationLocalServiceUtil.fetchTicketInformation(85116325);

		if (ticketInformation == null) {
			return;
		}

		ticketInformation.setData(
			"Liferay Portal 6.2.10 EE GA1 (Newton / Build 6210 / November 1, " +
				"2013)");

		TicketInformationLocalServiceUtil.updateTicketInformation(
			ticketInformation);

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			63991);

		ticketEntry.setEnvName(
			"Liferay Portal 6.2.10 EE GA1 (Newton / Build 6210 / November 1, " +
				"2013)");

		TicketEntryLocalServiceUtil.updateTicketEntry(ticketEntry);

		runSQL("alter table OSB_TicketEntry modify envName VARCHAR(75)");
	}

}
*/

}