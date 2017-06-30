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

package com.liferay.osb.hook.upgrade.v3_5_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class Upgrade_20160920173855681_TicketEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160920173855681L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_TicketEntry", "productEntryId")) {
			runSQL(
				"alter table OSB_TicketEntry add column productEntryId LONG");
		}

		if (!tableHasColumn("OSB_TicketEntry", "supportResponseId")) {
			runSQL(
				"alter table OSB_TicketEntry add column supportResponseId " +
					"LONG");
		}

		if (tableHasColumn("OSB_TicketEntry", "offeringDefinitionId")) {
			StringBundler sb = new StringBundler(8);

			sb.append("update OSB_TicketEntry inner join ");
			sb.append("OSB_OfferingDefinition on ");
			sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
			sb.append("OSB_TicketEntry.offeringDefinitionId set ");
			sb.append("OSB_TicketEntry.supportResponseId = ");
			sb.append("OSB_OfferingDefinition.supportResponseId, ");
			sb.append("OSB_TicketEntry.productEntryId = ");
			sb.append("OSB_OfferingDefinition.productEntryId");

			runSQL(sb.toString());

			runSQL(
				"alter table OSB_TicketEntry drop column offeringDefinitionId");
		}
	}

}