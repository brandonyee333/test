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
 * This class is a wrapper for {@link LicenseEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       LicenseEntryService
 * @generated
 */
public class LicenseEntryServiceWrapper implements LicenseEntryService,
	ServiceWrapper<LicenseEntryService> {
	public LicenseEntryServiceWrapper(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _licenseEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_licenseEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public LicenseEntryService getWrappedLicenseEntryService() {
		return _licenseEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedLicenseEntryService(
		LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	public LicenseEntryService getWrappedService() {
		return _licenseEntryService;
	}

	public void setWrappedService(LicenseEntryService licenseEntryService) {
		_licenseEntryService = licenseEntryService;
	}

	private LicenseEntryService _licenseEntryService;
}