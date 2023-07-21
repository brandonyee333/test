/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service.impl;

import com.liferay.osb.customer.admin.exception.LicenseEntryNameException;
import com.liferay.osb.customer.admin.exception.LicenseEntryVersionException;
import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.osb.customer.admin.service.base.LicenseEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
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
			int versionMin, int versionMax)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(name, versionMin, versionMax);

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
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	public List<LicenseEntry> getLicenseEntries(long productEntryId) {
		return licenseEntryPersistence.findByProductEntryId(productEntryId);
	}

	public List<LicenseEntry> getLicenseEntries(
		long productEntryId, int version) {

		List<LicenseEntry> licenseEntries = licenseEntryPersistence.findByPEI_V(
			productEntryId, version);

		licenseEntries = ListUtil.copy(licenseEntries);

		Iterator<LicenseEntry> itr = licenseEntries.iterator();

		while (itr.hasNext()) {
			LicenseEntry licenseEntry = itr.next();

			if ((licenseEntry.getVersionMax() != 0) &&
				(version > licenseEntry.getVersionMax())) {

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
			int versionMin, int versionMax)
		throws PortalException {

		validate(name, versionMin, versionMax);

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		licenseEntry.setModifiedDate(new Date());
		licenseEntry.setProductEntryId(productEntryId);
		licenseEntry.setName(name);
		licenseEntry.setType(type);
		licenseEntry.setVersionMin(versionMin);
		licenseEntry.setVersionMax(versionMax);

		return licenseEntryPersistence.update(licenseEntry);
	}

	protected void validate(String name, int versionMin, int versionMax)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new LicenseEntryNameException();
		}

		if ((versionMax != 0) && (versionMin >= versionMax)) {
			throw new LicenseEntryVersionException();
		}
	}

}