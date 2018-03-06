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

package com.liferay.osb.support.messaging;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;
import java.util.List;

/**
 * @author Lin Cui
 */
public class CheckStatusAccountEntriesMessageListener
	extends BaseMessageListener {

	protected void checkTrialAccountEntry(AccountEntry accountEntry)
		throws PortalException {

		LicenseKey licenseKey = LicenseKeyLocalServiceUtil.getFirstLicenseKey(
			accountEntry.getAccountEntryId(),
			new LicenseKeyExpirationDateComparator());

		Date expirationDate = licenseKey.getExpirationDate();

		Date now = new Date();
		Date deleteDate = new Date(expirationDate.getTime() + (90 * Time.DAY));

		if (now.after(deleteDate)) {
			AccountCustomerLocalServiceUtil.deleteAccountEntryAccountCustomers(
				accountEntry.getAccountEntryId());

			AccountWorkerLocalServiceUtil.deleteAccountEntryAccountWorkers(
				accountEntry.getAccountEntryId());

			AccountEntryLocalServiceUtil.deleteAccountEntry(
				accountEntry.getAccountEntryId());

			RemoteCorpProjectLocalServiceUtil.deleteCorpProject(
				accountEntry.getCorpProjectId());
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			if (accountEntry.getAccountEntryId() ==
					OSBConstants.ACCOUNT_ENTRY_TRIAL_ID) {

				continue;
			}

			AccountEntryLocalServiceUtil.updateStatus(
				accountEntry.getAccountEntryId());

			if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
				checkTrialAccountEntry(accountEntry);
			}
		}
	}

}