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

package com.liferay.osb.hook.upgrade.v3_0_4;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Sharon Li
 */
public class Upgrade_20140626163336549_Address extends BaseUpgradeProcess {

	public Address addAddress(
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

		return AddressLocalServiceUtil.updateAddress(address);
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

}