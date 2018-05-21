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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.LicenseKeySetNameException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.base.LicenseKeySetLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class LicenseKeySetLocalServiceImpl
	extends LicenseKeySetLocalServiceBaseImpl {

	public LicenseKeySet addLicenseKeySet(
			long userId, long accountEntryId, String name)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(accountEntryId, name);

		long licenseKeySetId = counterLocalService.increment();

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.create(
			licenseKeySetId);

		licenseKeySet.setUserId(user.getUserId());
		licenseKeySet.setUserName(user.getFullName());
		licenseKeySet.setCreateDate(now);
		licenseKeySet.setModifiedDate(now);
		licenseKeySet.setAccountEntryId(accountEntryId);
		licenseKeySet.setName(name);

		return licenseKeySetPersistence.update(licenseKeySet);
	}

	public List<LicenseKeySet> getAccountEntryLicenseKeySets(
		long accountEntryId, int start, int end) {

		return licenseKeySetPersistence.findByAccountEntryId(
			accountEntryId, start, end);
	}

	public int getAccountEntryLicenseKeySetsCount(long accountEntryId) {
		return licenseKeySetPersistence.countByAccountEntryId(accountEntryId);
	}

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException {

		validateName(name);

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			licenseKeySetId);

		licenseKeySet.setModifiedDate(new Date());
		licenseKeySet.setName(name);

		return licenseKeySetPersistence.update(licenseKeySet);
	}

	protected void validate(long accountEntryId, String name)
		throws PortalException {

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}

		validateName(name);
	}

	protected void validateName(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new LicenseKeySetNameException();
		}
	}

}