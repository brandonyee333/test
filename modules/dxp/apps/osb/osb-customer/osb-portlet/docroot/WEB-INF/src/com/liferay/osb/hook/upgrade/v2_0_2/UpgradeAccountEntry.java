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

package com.liferay.osb.hook.upgrade.v2_0_2;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class UpgradeAccountEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEntry();
	}

	protected void updateAccountEntry() throws Exception {
		if (hasColumn("OSB_AccountEntry", "countryId")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry add column countryId LONG");

		runSQL("update OSB_AccountEntry set countryId = 0");

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			List<Address> addresses = AddressLocalServiceUtil.getAddresses(
				OSBConstants.COMPANY_ID, AccountEntry.class.getName(),
				accountEntry.getAccountEntryId());

			if (addresses.isEmpty()) {
				continue;
			}

			for (Address address : addresses) {
				if (address.isPrimary()) {
					accountEntry.setCountryId(address.getCountryId());
				}

				if (accountEntry.getCountryId() <= 0) {
					accountEntry.setCountryId(address.getCountryId());
				}
			}

			AccountEntryLocalServiceUtil.updateAccountEntry(accountEntry);

			AddressLocalServiceUtil.deleteAddresses(
				OSBConstants.COMPANY_ID, AccountEntry.class.getName(),
				accountEntry.getAccountEntryId());
		}
	}

}