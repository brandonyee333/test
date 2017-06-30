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

package com.liferay.osb.hook.upgrade.v3_2_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Date;

/**
 * @author Joan Kim
 */
public class Upgrade_20150618140756525_AssetReceiptLicense
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150618140756525L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select assetReceiptLicenseId from ");
			sb.append("OSB_AssetReceiptLicense where usageType = ");
			sb.append(AssetLicenseConstants.USAGE_TYPE_TRIAL);
			sb.append(" and datediff(endDate,startDate) > 30");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			Date startDate = new Date();
			Date endDate = new Date(
				startDate.getTime() + AssetLicenseConstants.LIFETIME_TRIAL);

			while (rs.next()) {
				long assetReceiptLicenseId = rs.getLong(
					"assetReceiptLicenseId");

				AssetReceiptLicense assetReceiptLicense =
					AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
						assetReceiptLicenseId);

				AssetReceiptLicenseLocalServiceUtil.addAssetReceiptLicense(
					assetReceiptLicense.getUserId(),
					assetReceiptLicense.getAssetReceiptId(),
					assetReceiptLicense.getAssetLicenseId(),
					assetReceiptLicense.getAssetEntryId(),
					assetReceiptLicense.getOwnerClassNameId(),
					assetReceiptLicense.getOwnerClassPK(),
					assetReceiptLicense.getProductClassNameId(),
					assetReceiptLicense.getProductClassPK(),
					assetReceiptLicense.getProductId(), startDate, endDate);

				MarketplaceEmailUtil.sendAssetReceiptLicenseTrialUpdateEmail(
					assetReceiptLicense);

				AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense(
					assetReceiptLicenseId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}