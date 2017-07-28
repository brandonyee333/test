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

import com.liferay.osb.exception.LicenseEntryNameException;
import com.liferay.osb.exception.LicenseEntryPortalVersionException;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.service.base.LicenseEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseEntryLocalServiceImpl
	extends LicenseEntryLocalServiceBaseImpl {

	public LicenseEntry addLicenseEntry(
			long userId, long productEntryId, String name, String type,
			int portalVersionMin, int portalVersionMax)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name, portalVersionMin, portalVersionMax);

		long licenseEntryId = counterLocalService.increment();

		LicenseEntry licenseEntry = licenseEntryPersistence.create(
			licenseEntryId);

		licenseEntry.setUserId(user.getUserId());
		licenseEntry.setUserName(user.getFullName());
		licenseEntry.setCreateDate(now);
		licenseEntry.setModifiedDate(now);
		licenseEntry.setProductEntryId(productEntryId);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setPortalVersionMin(portalVersionMin);
		licenseEntry.setPortalVersionMax(portalVersionMax);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		licenseEntryPersistence.update(licenseEntry, serviceContext);

		return licenseEntry;
	}

	@Override
	public LicenseEntry deleteLicenseEntry(long licenseEntryId)
		throws PortalException {

		return licenseEntryPersistence.remove(licenseEntryId);
	}

	public List<LicenseEntry> getLicenseEntries(long productEntryId) {
		return licenseEntryPersistence.findByProductEntryId(productEntryId);
	}

	public List<LicenseEntry> getLicenseEntries(
		long productEntryId, int portalVersion) {

		List<LicenseEntry> licenseEntries =
			licenseEntryPersistence.findByPEI_PV(productEntryId, portalVersion);

		licenseEntries = ListUtil.copy(licenseEntries);

		Iterator<LicenseEntry> itr = licenseEntries.iterator();

		while (itr.hasNext()) {
			LicenseEntry licenseEntry = itr.next();

			if ((licenseEntry.getPortalVersionMax() != 0) &&
				(portalVersion > licenseEntry.getPortalVersionMax())) {

				itr.remove();
			}
		}

		return licenseEntries;
	}

	public LicenseEntry getLicenseEntry(long productEntryId, String type)
		throws PortalException {

		return licenseEntryPersistence.findByPEI_T(productEntryId, type);
	}

	public LicenseEntry updateLicenseEntry(
			long licenseEntryId, long productEntryId, String name, String type,
			int portalVersionMin, int portalVersionMax)
		throws PortalException {

		validate(name, portalVersionMin, portalVersionMax);

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		licenseEntry.setModifiedDate(new Date());
		licenseEntry.setProductEntryId(productEntryId);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setPortalVersionMin(portalVersionMin);
		licenseEntry.setPortalVersionMax(portalVersionMax);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		licenseEntryPersistence.update(licenseEntry, serviceContext);

		return licenseEntry;
	}

	protected void validate(
			String name, int portalVersionMin, int portalVersionMax)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new LicenseEntryNameException();
		}

		if ((portalVersionMax != 0) && (portalVersionMin >= portalVersionMax)) {
			throw new LicenseEntryPortalVersionException();
		}
	}

}