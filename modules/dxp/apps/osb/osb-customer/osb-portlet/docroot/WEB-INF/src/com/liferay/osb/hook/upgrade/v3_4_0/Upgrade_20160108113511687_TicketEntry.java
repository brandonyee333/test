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

package com.liferay.osb.hook.upgrade.v3_4_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20160108113511687_TicketEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160108113511687L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb1 = new StringBundler(4);

		sb1.append("select ticketEntryId, accountEntryId from ");
		sb1.append("OSB_TicketEntry where ((select count(*) from ");
		sb1.append("OSB_OfferingEntry where offeringEntryId = ");
		sb1.append("OSB_TicketEntry.offeringEntryId) < 1)");

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(sb1.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");
				long accountEntryId = rs.getLong("accountEntryId");

				List<OfferingEntry> offeringEntries =
					OfferingEntryLocalServiceUtil.
						getAccountEntryOfferingEntries(accountEntryId);

				if (offeringEntries.isEmpty()) {
					continue;
				}

				/*OfferingEntry offeringEntry = offeringEntries.get(0);

				StringBundler sb2 = new StringBundler(8);

				sb2.append(
					"update OSB_TicketEntry set offeringDefinitionId = ");
				sb2.append(offeringEntry.getOfferingDefinitionId());
				sb2.append(", offeringEntryId = ");
				sb2.append(offeringEntry.getOfferingEntryId());
				sb2.append(", orderEntryId = ");
				sb2.append(offeringEntry.getOrderEntryId());
				sb2.append(" where ticketEntryId = ");
				sb2.append(ticketEntryId);

				runSQL(sb2.toString());*/
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}