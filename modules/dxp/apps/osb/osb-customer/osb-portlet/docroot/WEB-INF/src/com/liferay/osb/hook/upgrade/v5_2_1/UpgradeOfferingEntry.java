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

package com.liferay.osb.hook.upgrade.v5_2_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeOfferingEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(6);

			sb.append("select offeringEntryId from OSB_OfferingEntry inner ");
			sb.append("join OSB_ProductEntry on ");
			sb.append("OSB_OfferingEntry.productEntryId = ");
			sb.append("OSB_ProductEntry.productEntryId where ");
			sb.append("OSB_ProductEntry.name like '%DXP Cloud%' and ");
			sb.append("OSB_OfferingEntry.sizing = 0");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long offeringEntryId = rs.getLong("offeringEntryId");

				OfferingEntry offeringEntry =
					OfferingEntryLocalServiceUtil.getOfferingEntry(
						offeringEntryId);

				offeringEntry.setSizing(OfferingEntryConstants.SIZING_1);

				OfferingEntryLocalServiceUtil.updateOfferingEntry(
					offeringEntry);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}