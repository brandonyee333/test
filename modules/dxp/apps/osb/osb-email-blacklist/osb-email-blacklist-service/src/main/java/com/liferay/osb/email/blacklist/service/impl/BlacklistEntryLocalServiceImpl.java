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

		blacklistEntryPersistence.update(blacklistEntry);

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