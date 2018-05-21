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