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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

*/

/**
 * @author Jenny Chen
 */
public class Upgrade_20160927140533746_LicenseKey extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20160927140533746L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateLicenseKeys();
		updateLicenseKeySets();
	}

	protected boolean hasActiveLicenseKey(long licenseKeySetId)
		throws Exception {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySetId);

		for (LicenseKey licenseKey : licenseKeys) {
			if (licenseKey.getActive()) {
				return true;
			}
		}

		return false;
	}

	protected void updateLicenseKeys() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"select accountEntryId, name from OSB_AccountEntry where " +
					"(accountEntryId != ?) and (type_ = ?)";

			ps = con.prepareStatement(sql);

			ps.setLong(1, OSBConstants.ACCOUNT_ENTRY_TRIAL_ID);
			ps.setInt(2, AccountEntryConstants.TYPE_TRIAL);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");
				String name = rs.getString("name");

				updateLicenseKeys(accountEntryId, name);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateLicenseKeys(long accountEntryId, String name)
		throws Exception {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(
				accountEntryId);

		if (!licenseKeys.isEmpty()) {
			long firstLicenseKeySetId = licenseKeys.get(0).getLicenseKeySetId();

			List<Long> licenseKeySetIds = new ArrayList<>();

			for (LicenseKey licenseKey : licenseKeys) {
				boolean updateLicenseKey = false;

				if (!Validator.equals(licenseKey.getAccountEntryName(), name)) {
					licenseKey.setAccountEntryName(name);

					updateLicenseKey = true;
				}

				long licenseKeySetId = licenseKey.getLicenseKeySetId();

				if (licenseKeySetId != firstLicenseKeySetId) {
					licenseKey.setLicenseKeySetId(firstLicenseKeySetId);

					if (hasActiveLicenseKey(firstLicenseKeySetId)) {
						licenseKey.setActive(false);
					}

					if (!licenseKeySetIds.contains(licenseKeySetId)) {
						licenseKeySetIds.add(licenseKeySetId);
					}

					updateLicenseKey = true;
				}

				String description = licenseKey.getDescription();

				if (StringUtil.contains(description, "-Day License")) {
					description = StringUtil.replace(
						description, "License", "Trial License");

					licenseKey.setDescription(description);

					updateLicenseKey = true;
				}

				if (updateLicenseKey) {
					LicenseKeyLocalServiceUtil.updateLicenseKey(licenseKey);
				}
			}

			for (long licenseKeySetId : licenseKeySetIds) {
				LicenseKeySetLocalServiceUtil.deleteLicenseKeySet(
					licenseKeySetId);
			}
		}
	}

	protected void updateLicenseKeySet(LicenseKeySet licenseKeySet)
		throws Exception {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
				licenseKeySet.getLicenseKeySetId());

		for (LicenseKey licenseKey : licenseKeys) {
			long accountEntryId = licenseKey.getAccountEntryId();

			if (accountEntryId != OSBConstants.ACCOUNT_ENTRY_TRIAL_ID) {
				licenseKeySet.setAccountEntryId(accountEntryId);

				LicenseKeySetLocalServiceUtil.updateLicenseKeySet(
					licenseKeySet);

				break;
			}
		}
	}

	protected void updateLicenseKeySets() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"select licenseKeySetId from OSB_LicenseKeySet where " +
					"(accountEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setLong(1, OSBConstants.ACCOUNT_ENTRY_TRIAL_ID);

			rs = ps.executeQuery();

			while (rs.next()) {
				long licenseKeySetId = rs.getLong("licenseKeySetId");

				LicenseKeySet licenseKeySet =
					LicenseKeySetLocalServiceUtil.getLicenseKeySet(
						licenseKeySetId);

				updateLicenseKeySet(licenseKeySet);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}

*/

}