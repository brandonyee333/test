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

package com.liferay.osb.admin.messaging;

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
				accountEntry.getCorpProjectUuid());
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