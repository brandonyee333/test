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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Amos Fong
 */
public class Upgrade_20160413174405139_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160413174405139L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_OfferingEntry", "version")) {
			runSQL("alter table OSB_OfferingEntry add column version INTEGER");
			runSQL(
				"alter table OSB_OfferingEntry add column platform " +
					"VARCHAR(75)");
			runSQL(
				"alter table OSB_OfferingEntry add column platformVersion " +
					"VARCHAR(75)");
		}

		updateClosedOfferings();

		updateOpenOfferings();
	}

	protected int getPortalVersion(long closedTime) {
		if (closedTime > 1385942400000L) {
			return 22004;
		}
		else if (closedTime > 1329868800000L) {
			return 22003;
		}
		else if (closedTime > 1284076800000L) {
			return 22002;
		}
		else {
			return 22001;
		}
	}

	protected void updateClosedOfferings() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(11);

		sb.append("select OSB_OfferingEntry.offeringEntryId, ");
		sb.append("OSB_OfferingEntry.modifiedDate, ");
		sb.append("OSB_ProductEntry.versionsListType from OSB_OfferingEntry ");
		sb.append("inner join OSB_OfferingDefinition on ");
		sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId inner join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_OfferingDefinition.productEntryId where (status = ");
		sb.append(OfferingEntryConstants.STATUS_CLOSED);
		sb.append(") and (OSB_ProductEntry.versionsListType = ");
		sb.append("'portalMinorVersions')");

		try {
			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long offeringEntryId = rs.getLong("offeringEntryId");
				Timestamp modifiedDate = rs.getTimestamp("modifiedDate");

				int portalVerison = getPortalVersion(modifiedDate.getTime());

				runSQL(
					"update OSB_OfferingEntry set version = '" + portalVerison +
						"' where offeringEntryId = " + offeringEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateOpenOfferings() throws Exception {
		StringBundler sb = new StringBundler(11);

		sb.append("update OSB_OfferingEntry inner join ");
		sb.append("OSB_OfferingDefinition on ");
		sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId inner join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_OfferingDefinition.productEntryId set ");
		sb.append("OSB_OfferingEntry.version = 22004 where ");
		sb.append("(OSB_ProductEntry.versionsListType = ");
		sb.append("'portalMinorVersions') and (OSB_OfferingEntry.status != ");
		sb.append(OfferingEntryConstants.STATUS_CLOSED);
		sb.append(")");

		runSQL(sb.toString());
	}

}