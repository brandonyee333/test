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

import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.service.base.LicenseKeySetServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.service.permission.OSBLicenseKeySetPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeySetServiceImpl extends LicenseKeySetServiceBaseImpl {

	public LicenseKeySet addLicenseKeySet(
			long userId, long accountEntryId, String name)
		throws PortalException {

		OSBAccountEntryPermission.check(
			getPermissionChecker(), accountEntryId,
			OSBActionKeys.ADD_LICENSE_KEY_SET);

		return licenseKeySetLocalService.addLicenseKeySet(
			userId, accountEntryId, name);
	}

	public LicenseKeySet deleteLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		OSBLicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.DELETE);

		return licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySet);
	}

	public LicenseKeySet getLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		OSBLicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.VIEW);

		return licenseKeySet;
	}

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException {

		OSBLicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySetId, ActionKeys.UPDATE);

		return licenseKeySetLocalService.updateLicenseKeySet(
			licenseKeySetId, name);
	}

}