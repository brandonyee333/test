/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service.impl;

import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.osb.customer.license.service.base.LicenseKeySetServiceBaseImpl;
import com.liferay.osb.customer.license.service.permission.LicenseKeySetPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeySetServiceImpl extends LicenseKeySetServiceBaseImpl {

	public LicenseKeySet deleteLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.DELETE);

		return licenseKeySetLocalService.deleteLicenseKeySet(licenseKeySet);
	}

	public LicenseKeySet getLicenseKeySet(long licenseKeySetId)
		throws PortalException {

		LicenseKeySet licenseKeySet =
			licenseKeySetLocalService.getLicenseKeySet(licenseKeySetId);

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySet, ActionKeys.VIEW);

		return licenseKeySet;
	}

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException {

		LicenseKeySetPermission.check(
			getPermissionChecker(), licenseKeySetId, ActionKeys.UPDATE);

		return licenseKeySetLocalService.updateLicenseKeySet(
			licenseKeySetId, name);
	}

}