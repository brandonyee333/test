/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service.impl;

import com.liferay.osb.customer.license.exception.LicenseKeySetNameException;
import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.base.LicenseKeySetLocalServiceBaseImpl;
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

		validate(name);

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

	public LicenseKeySet addLicenseKeySet(
			long userId, String koroneikiAccountKey, String name)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(name);

		long licenseKeySetId = counterLocalService.increment();

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.create(
			licenseKeySetId);

		licenseKeySet.setUserId(user.getUserId());
		licenseKeySet.setUserName(user.getFullName());
		licenseKeySet.setCreateDate(now);
		licenseKeySet.setModifiedDate(now);
		licenseKeySet.setKoroneikiAccountKey(koroneikiAccountKey);
		licenseKeySet.setName(name);

		return licenseKeySetPersistence.update(licenseKeySet);
	}

	@Override
	public LicenseKeySet deleteLicenseKeySet(LicenseKeySet licenseKeySet)
		throws PortalException {

		licenseKeySet = licenseKeySetPersistence.remove(
			licenseKeySet.getLicenseKeySetId());

		// License keys

		licenseKeyPersistence.removeByLicenseKeySetId(
			licenseKeySet.getLicenseKeySetId());

		return licenseKeySet;
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

		validate(name);

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			licenseKeySetId);

		licenseKeySet.setModifiedDate(new Date());
		licenseKeySet.setName(name);

		return licenseKeySetPersistence.update(licenseKeySet);
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new LicenseKeySetNameException();
		}
	}

}