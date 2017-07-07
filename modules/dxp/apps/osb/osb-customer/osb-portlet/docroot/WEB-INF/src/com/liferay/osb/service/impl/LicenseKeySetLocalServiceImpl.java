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

package com.liferay.osb.service.impl;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.exception.LicenseKeySetNameException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.base.LicenseKeySetLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class LicenseKeySetLocalServiceImpl
	extends LicenseKeySetLocalServiceBaseImpl {

	public LicenseKeySet addLicenseKeySet(
			long userId, long accountEntryId, String name)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
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
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		licenseKeySetPersistence.update(licenseKeySet, serviceContext);

		return licenseKeySet;
	}

	public List<LicenseKeySet> getAccountEntryLicenseKeySets(
			long accountEntryId, int start, int end)
		throws SystemException {

		return licenseKeySetPersistence.findByAccountEntryId(
			accountEntryId, start, end);
	}

	public int getAccountEntryLicenseKeySetsCount(long accountEntryId)
		throws SystemException {

		return licenseKeySetPersistence.countByAccountEntryId(accountEntryId);
	}

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException, SystemException {

		validateName(name);

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			licenseKeySetId);

		licenseKeySet.setModifiedDate(new Date());
		licenseKeySet.setName(name);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		licenseKeySetPersistence.update(licenseKeySet, serviceContext);

		return licenseKeySet;
	}

	protected void validate(long accountEntryId, String name)
		throws PortalException, SystemException {

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