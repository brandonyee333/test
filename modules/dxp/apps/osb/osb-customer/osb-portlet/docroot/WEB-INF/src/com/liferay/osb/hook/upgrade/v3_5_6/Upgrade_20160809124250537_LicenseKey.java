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

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160809124250537_LicenseKey extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160809124250537L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateIndexes();
		updateLicenseKeys();

		if (_log.isInfoEnabled()) {
			for (Map.Entry<String, Date> entry :
					_existingTrialUsers.entrySet()) {

				_log.info(entry.getKey() + ", " + entry.getValue());
			}
		}
	}

	protected void updateIndexes() throws Exception {
		if (!hasIndex("OSB_LicenseKey", "IX_6BE4BD7E")) {
			runSQL(
				"create index IX_6BE4BD7E on OSB_LicenseKey (accountEntryId, " +
					"offeringDefinitionId, productVersion)");
		}
	}

	protected void updateLicenseKeys() throws Exception {
		/*long trialAccountEntryId = 8264303;

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(
				trialAccountEntryId, OSBConstants.OFFERING_DEFINITION_TRIAL_ID,
				ProductEntryConstants.PORTAL_VERSION_7_0_10);

		Date now = new Date();

		for (LicenseKey licenseKey : licenseKeys) {
			User user = UserLocalServiceUtil.getUser(licenseKey.getUserId());

			if (!OSBUtil.isTrialEULA(user.getCompanyId(), user.getUserId())) {
				continue;
			}

			if (now.after(licenseKey.getExpirationDate())) {
				continue;
			}

			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.fetchUserTrialAccountEntry(
					licenseKey.getUserId());

			if (accountEntry == null) {
				AccountEntryLocalServiceUtil.addTrialAccountEntry(
					licenseKey.getUserId(), licenseKey.getLicenseKeyId());
			}
			else {
				List<OrderEntry> orderEntries =
					OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(
						accountEntry.getAccountEntryId());

				OrderEntry orderEntry = orderEntries.get(0);

				List<OfferingEntry> offeringEntries =
					OfferingEntryLocalServiceUtil.getOrderEntryOfferingEntries(
						orderEntry.getOrderEntryId(),
						OSBConstants.OFFERING_DEFINITION_TRIAL_ID);

				OfferingEntry offeringEntry = offeringEntries.get(0);

				LicenseKeyLocalServiceUtil.updateLicenseKey(
					licenseKey.getLicenseKeyId(),
					accountEntry.getAccountEntryId(),
					offeringEntry.getOfferingEntryId(),
					orderEntry.getOrderEntryId());
			}

			Date latestLicenseKeyCreateDate = _existingTrialUsers.get(
				user.getEmailAddress());

			if ((latestLicenseKeyCreateDate == null) ||
				latestLicenseKeyCreateDate.before(licenseKey.getCreateDate())) {

				_existingTrialUsers.put(
					user.getEmailAddress(), licenseKey.getCreateDate());
			}
		}*/
	}

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20160809124250537_LicenseKey.class);

	private final Map<String, Date> _existingTrialUsers = new HashMap<>();

}