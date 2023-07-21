/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.model.impl;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.customer.admin.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.LicenseKeySetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Time;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyImpl extends LicenseKeyBaseImpl {

	public LicenseKeyImpl() {
	}

	public boolean canRenew() throws PortalException {
		if (!isActive()) {
			return false;
		}

		if (getLicenseVersion() < 3) {
			return false;
		}

		Date startDate = getStartDate();
		Date expirationDate = getExpirationDate();

		if (((expirationDate.getTime() - startDate.getTime()) / Time.DAY) >
				365) {

			return false;
		}

		return true;
	}

	public AccountEntry getAccountEntry() throws PortalException {
		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	public LicenseEntry getLicenseEntry() throws PortalException {
		return LicenseEntryLocalServiceUtil.getLicenseEntry(
			getLicenseEntryId());
	}

	public LicenseKeySet getLicenseKeySet() throws PortalException {
		return LicenseKeySetLocalServiceUtil.getLicenseKeySet(
			getLicenseKeySetId());
	}

	public boolean isExpired() {
		Date now = new Date();

		if (now.after(getExpirationDate())) {
			return true;
		}

		return false;
	}

}