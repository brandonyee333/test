/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.service.impl;

import com.liferay.osb.email.blacklist.exception.BlacklistEntryEmailAddressException;
import com.liferay.osb.email.blacklist.model.BlacklistEntry;
import com.liferay.osb.email.blacklist.service.base.BlacklistEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Jamie Sammons
 */
public class BlacklistEntryLocalServiceImpl
	extends BlacklistEntryLocalServiceBaseImpl {

	@Override
	public BlacklistEntry addBlacklistEntry(String emailAddress)
		throws PortalException {

		validate(emailAddress);

		BlacklistEntry blacklistEntry =
			blacklistEntryPersistence.fetchByEmailAddress(emailAddress);

		if (blacklistEntry != null) {
			return blacklistEntry;
		}

		long blacklistEntryId = counterLocalService.increment();

		blacklistEntry = blacklistEntryPersistence.create(blacklistEntryId);

		blacklistEntry.setCreateDate(new Date());
		blacklistEntry.setEmailAddress(emailAddress);

		blacklistEntry = blacklistEntryPersistence.update(blacklistEntry);

		List<Company> companies = companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = userLocalService.fetchUserByEmailAddress(
				company.getCompanyId(), emailAddress);

			if (user != null) {
				userLocalService.updateEmailAddressVerified(
					user.getUserId(), false);
			}
		}

		return blacklistEntry;
	}

	@Override
	public BlacklistEntry fetchBlacklistEntry(String emailAddress) {
		return blacklistEntryPersistence.fetchByEmailAddress(emailAddress);
	}

	protected void validate(String emailAddress) throws PortalException {
		if (!Validator.isEmailAddress(emailAddress)) {
			throw new BlacklistEntryEmailAddressException();
		}
	}

}