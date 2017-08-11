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

package com.liferay.osb.hook.upgrade.v3_0_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;

import java.util.Date;
import java.util.List;

*/

/**
 * @author Sharon Li
 */
public class Upgrade_20140626163336549_Address extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*public Address addAddress(
			long userId, String userName, String className, long classPK,
			long countryId) {

		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		long addressId = CounterLocalServiceUtil.increment();

		Address address = AddressLocalServiceUtil.createAddress(addressId);

		address.setCompanyId(OSBConstants.COMPANY_ID);
		address.setUserId(userId);
		address.setUserName(userName);
		address.setCreateDate(now);
		address.setModifiedDate(now);
		address.setClassNameId(classNameId);
		address.setClassPK(classPK);
		address.setRegionId(0);
		address.setCountryId(countryId);
		address.setTypeId(0);
		address.setMailing(false);
		address.setPrimary(true);

		return AddressLocalServiceUtil.updateAddress(address, false);
	}

	@Override
	public long getTimestamp() {
		return 20140626163336549L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeAddress();
	}

	protected void upgradeAddress() throws Exception {
		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			if (accountEntry.getCountryId() > 0) {
				addAddress(
					accountEntry.getUserId(), accountEntry.getUserName(),
					AccountEntry.class.getName(),
					accountEntry.getAccountEntryId(),
					accountEntry.getCountryId());
			}
		}
	}

	 */

}