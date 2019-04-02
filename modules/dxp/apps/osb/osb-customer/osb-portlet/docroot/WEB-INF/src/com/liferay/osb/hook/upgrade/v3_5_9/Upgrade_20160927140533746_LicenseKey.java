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

package com.liferay.osb.hook.upgrade.v3_5_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenny Chen
 */
public class Upgrade_20160927140533746_LicenseKey extends BaseUpgradeProcess {

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
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId, name from OSB_AccountEntry where " +
					"(accountEntryId != ?) and (type_ = ?)";

			ps = connection.prepareStatement(sql);

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
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateLicenseKeys(long accountEntryId, String name)
		throws Exception {

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(
				accountEntryId);

		if (!licenseKeys.isEmpty()) {
			LicenseKey firstLicenseKey = licenseKeys.get(0);

			long firstLicenseKeySetId = firstLicenseKey.getLicenseKeySetId();

			List<Long> licenseKeySetIds = new ArrayList<>();

			for (LicenseKey licenseKey : licenseKeys) {
				boolean updateLicenseKey = false;

				if (!name.equals(licenseKey.getAccountEntryName())) {
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
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select licenseKeySetId from OSB_LicenseKeySet where " +
					"(accountEntryId = ?)";

			ps = connection.prepareStatement(sql);

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
			DataAccess.cleanUp(ps, rs);
		}
	}

}