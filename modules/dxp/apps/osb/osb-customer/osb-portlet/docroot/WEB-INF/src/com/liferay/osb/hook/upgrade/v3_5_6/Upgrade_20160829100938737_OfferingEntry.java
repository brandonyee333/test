/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * @author Amos Fong
 */
public class Upgrade_20160829100938737_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160829100938737L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(7);

			sb.append("select orderEntryId, offeringEntryId, expirationDate, ");
			sb.append("max(OSB_LicenseKey.createDate) as createDate from ");
			sb.append("OSB_LicenseKey inner join OSB_AccountEntry on ");
			sb.append("OSB_AccountEntry.accountEntryId = ");
			sb.append("OSB_LicenseKey.accountEntryId where ");
			sb.append("OSB_AccountEntry.type_ = 4 group by ");
			sb.append("OSB_LicenseKey.accountEntryId");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long orderEntryId = rs.getLong("orderEntryId");
				long offeringEntryId = rs.getLong("offeringEntryId");
				Timestamp expirationDate = rs.getTimestamp("expirationDate");
				Timestamp createDate = rs.getTimestamp("createDate");

				OrderEntry orderEntry =
					OrderEntryLocalServiceUtil.getOrderEntry(orderEntryId);

				orderEntry.setStartDate(createDate);

				OrderEntryLocalServiceUtil.updateOrderEntry(orderEntry);

				OfferingEntry offeringEntry =
					OfferingEntryLocalServiceUtil.getOfferingEntry(
						offeringEntryId);

				offeringEntry.setSupportEndDate(expirationDate);

				OfferingEntryLocalServiceUtil.updateOfferingEntry(
					offeringEntry);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}