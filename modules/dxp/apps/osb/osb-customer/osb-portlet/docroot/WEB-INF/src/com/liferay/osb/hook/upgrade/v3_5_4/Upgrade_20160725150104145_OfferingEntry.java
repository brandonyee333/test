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

package com.liferay.osb.hook.upgrade.v3_5_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20160725150104145_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160725150104145L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(8);

		sb.append("select OSB_OfferingEntry.offeringEntryId from ");
		sb.append("OSB_OfferingEntry inner join OSB_OfferingDefinition on ");
		sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId inner join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryid = ");
		sb.append("OSB_OfferingDefinition.productEntryid where ");
		sb.append("OSB_ProductEntry.name like '%portal%' and ");
		sb.append("OSB_OfferingEntry.version = 0");

		try {
			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				/*long offeringEntryId = rs.getLong("offeringEntryId");

				runSQL(
					"update OSB_OfferingEntry set version = '" +
						ProductEntryConstants.PORTAL_VERSION_ANY + "' where " +
							"offeringEntryId = " + offeringEntryId);*/
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}