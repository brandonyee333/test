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

package com.liferay.osb.hook.upgrade.v3_1_6;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20141218102834000_AssetReceiptLicense
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20141218102834000L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select * from OSB_AssetReceiptLicense where " +
					"productClassNameId = 0");

			rs = ps.executeQuery();

			while (rs.next()) {
				long assetReceiptId = rs.getLong("assetReceiptId");

				AssetReceipt assetReceipt =
					AssetReceiptLocalServiceUtil.fetchAssetReceipt(
						assetReceiptId);

				if (assetReceipt == null) {
					continue;
				}

				StringBundler sb = new StringBundler(5);

				sb.append("update OSB_AssetReceiptLicense set ");
				sb.append("productClassNameId = ");
				sb.append(assetReceipt.getProductClassNameId());
				sb.append(" where assetReceiptLicenseId = ");
				sb.append(rs.getLong("assetReceiptLicenseId"));

				runSQL(sb.toString(), false);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}
*/

}