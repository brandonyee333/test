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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link LicenseKeySetService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseKeySetService
 * @generated
 */
public class LicenseKeySetServiceWrapper implements LicenseKeySetService,
	ServiceWrapper<LicenseKeySetService> {
	public LicenseKeySetServiceWrapper(
		LicenseKeySetService licenseKeySetService) {
		_licenseKeySetService = licenseKeySetService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _licenseKeySetService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_licenseKeySetService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseKeySetService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.LicenseKeySet addLicenseKeySet(long userId,
		long accountEntryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetService.addLicenseKeySet(userId, accountEntryId,
			name);
	}

	public com.liferay.osb.model.LicenseKeySet deleteLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetService.deleteLicenseKeySet(licenseKeySetId);
	}

	public java.lang.String exportToXML(long licenseKeySetId)
		throws java.lang.Exception {
		return _licenseKeySetService.exportToXML(licenseKeySetId);
	}

	public com.liferay.osb.model.LicenseKeySet getLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetService.getLicenseKeySet(licenseKeySetId);
	}

	public com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		long licenseKeySetId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeySetService.updateLicenseKeySet(licenseKeySetId, name);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LicenseKeySetService getWrappedLicenseKeySetService() {
		return _licenseKeySetService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLicenseKeySetService(
		LicenseKeySetService licenseKeySetService) {
		_licenseKeySetService = licenseKeySetService;
	}

	public LicenseKeySetService getWrappedService() {
		return _licenseKeySetService;
	}

	public void setWrappedService(LicenseKeySetService licenseKeySetService) {
		_licenseKeySetService = licenseKeySetService;
	}

	private LicenseKeySetService _licenseKeySetService;
}