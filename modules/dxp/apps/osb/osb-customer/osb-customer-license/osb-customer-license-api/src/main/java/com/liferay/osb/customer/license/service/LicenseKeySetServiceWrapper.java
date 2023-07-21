/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseKeySetService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetService
 * @generated
 */
public class LicenseKeySetServiceWrapper
	implements LicenseKeySetService, ServiceWrapper<LicenseKeySetService> {

	public LicenseKeySetServiceWrapper(
		LicenseKeySetService licenseKeySetService) {

		_licenseKeySetService = licenseKeySetService;
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetService.deleteLicenseKeySet(licenseKeySetId);
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			getLicenseKeySet(long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetService.getLicenseKeySet(licenseKeySetId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _licenseKeySetService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.customer.license.model.LicenseKeySet
			updateLicenseKeySet(long licenseKeySetId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _licenseKeySetService.updateLicenseKeySet(licenseKeySetId, name);
	}

	@Override
	public LicenseKeySetService getWrappedService() {
		return _licenseKeySetService;
	}

	@Override
	public void setWrappedService(LicenseKeySetService licenseKeySetService) {
		_licenseKeySetService = licenseKeySetService;
	}

	private LicenseKeySetService _licenseKeySetService;

}