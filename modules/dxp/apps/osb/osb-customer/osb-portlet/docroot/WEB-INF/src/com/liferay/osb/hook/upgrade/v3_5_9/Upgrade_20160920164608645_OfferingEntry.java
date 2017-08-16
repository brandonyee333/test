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
public class Upgrade_20160920164608645_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160920164608645L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasIndex("OSB_OfferingEntry", "IX_7D4D60AC")) {
			runSQL("drop index IX_7D4D60AC on OSB_OfferingEntry");
		}

		if (hasIndex("OSB_OfferingEntry", "IX_2749229A")) {
			runSQL("drop index IX_2749229A on OSB_OfferingEntry");
		}

		if (!hasColumn("OSB_OfferingEntry", "productEntryId")) {
			runSQL(
				"alter table OSB_OfferingEntry add column productEntryId LONG");
		}

		if (!hasColumn("OSB_OfferingEntry", "supportResponseId")) {
			runSQL(
				"alter table OSB_OfferingEntry add column supportResponseId " +
					"LONG");
		}

		if (!hasColumn("OSB_OfferingEntry", "productDescription")) {
			runSQL(
				"alter table OSB_OfferingEntry add column productDescription " +
					"VARCHAR(75)");
		}

		if (!hasColumn("OSB_OfferingEntry", "licenses")) {
			runSQL("alter table OSB_OfferingEntry add column licenses BOOLEAN");
		}

		if (!hasColumn("OSB_OfferingEntry", "maxConcurrentUsers")) {
			runSQL(
				"alter table OSB_OfferingEntry add column maxConcurrentUsers " +
					"BOOLEAN");
		}

		if (!hasColumn("OSB_OfferingEntry", "maxUsers")) {
			runSQL("alter table OSB_OfferingEntry add column maxUsers BOOLEAN");
		}

		if (!hasColumn("OSB_OfferingEntry", "supportTickets")) {
			runSQL(
				"alter table OSB_OfferingEntry add column supportTickets " +
					"BOOLEAN");
		}

		if (hasColumn("OSB_OfferingEntry", "instanceSize")) {
			runSQL(
				"alter table OSB_OfferingEntry change column instanceSize " +
					"sizing INTEGER");
		}

		if (hasColumn("OSB_OfferingEntry", "offeringDefinitionId")) {
			StringBundler sb = new StringBundler(18);

			sb.append("update OSB_OfferingEntry inner join ");
			sb.append("OSB_OfferingDefinition on ");
			sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
			sb.append("OSB_OfferingEntry.offeringDefinitionId set ");
			sb.append("OSB_OfferingEntry.productEntryId = ");
			sb.append("OSB_OfferingDefinition.productEntryId, ");
			sb.append("OSB_OfferingEntry.supportResponseId = ");
			sb.append("OSB_OfferingDefinition.supportResponseId, ");
			sb.append("OSB_OfferingEntry.productDescription = ");
			sb.append("OSB_OfferingDefinition.productDescription, ");
			sb.append("OSB_OfferingEntry.licenses = ");
			sb.append("OSB_OfferingDefinition.licenses, ");
			sb.append("OSB_OfferingEntry.maxConcurrentUsers = ");
			sb.append("OSB_OfferingDefinition.maxConcurrentUsers, ");
			sb.append("OSB_OfferingEntry.maxUsers = ");
			sb.append("OSB_OfferingDefinition.maxUsers, ");
			sb.append("OSB_OfferingEntry.supportTickets = ");
			sb.append("OSB_OfferingDefinition.supportTickets");

			runSQL(sb.toString());

			sb = new StringBundler(6);

			sb.append("update OSB_OfferingEntry inner join ");
			sb.append("OSB_OfferingDefinition on ");
			sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
			sb.append("OSB_OfferingEntry.offeringDefinitionId set ");
			sb.append("OSB_OfferingEntry.quantity = 10000 where ");
			sb.append("OSB_OfferingDefinition.unlimitedLicenses = true");

			runSQL(sb.toString());

			runSQL(
				"alter table OSB_OfferingEntry drop column " +
					"offeringDefinitionId");
		}

		if (!hasIndex("OSB_OfferingEntry", "IX_6455D563")) {
			runSQL(
				"create index IX_6455D563 on OSB_OfferingEntry (userId, " +
					"accountEntryId, orderEntryId, type_)");
		}
	}

}