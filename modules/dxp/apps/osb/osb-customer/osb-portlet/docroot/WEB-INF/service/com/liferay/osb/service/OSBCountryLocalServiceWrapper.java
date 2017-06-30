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
 * This class is a wrapper for {@link OSBCountryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OSBCountryLocalService
 * @generated
 */
public class OSBCountryLocalServiceWrapper implements OSBCountryLocalService,
	ServiceWrapper<OSBCountryLocalService> {
	public OSBCountryLocalServiceWrapper(
		OSBCountryLocalService osbCountryLocalService) {
		_osbCountryLocalService = osbCountryLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _osbCountryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_osbCountryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _osbCountryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.model.Country addCountry(java.lang.String name,
		java.lang.String a2, java.lang.String a3, java.lang.String number,
		java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.addCountry(name, a2, a3, number, idd,
			active);
	}

	public com.liferay.portal.model.Country deleteCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.deleteCountry(countryId);
	}

	public java.util.List<com.liferay.portal.model.Country> getCountries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.getCountries();
	}

	public java.util.List<com.liferay.portal.model.Country> getCountries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.getCountries(start, end);
	}

	public int getCountriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.getCountriesCount();
	}

	public com.liferay.portal.model.Country getCountry(long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.getCountry(countryId);
	}

	public com.liferay.portal.model.Country updateCountry(long countryId,
		java.lang.String name, java.lang.String a2, java.lang.String a3,
		java.lang.String number, java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbCountryLocalService.updateCountry(countryId, name, a2, a3,
			number, idd, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OSBCountryLocalService getWrappedOSBCountryLocalService() {
		return _osbCountryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOSBCountryLocalService(
		OSBCountryLocalService osbCountryLocalService) {
		_osbCountryLocalService = osbCountryLocalService;
	}

	public OSBCountryLocalService getWrappedService() {
		return _osbCountryLocalService;
	}

	public void setWrappedService(OSBCountryLocalService osbCountryLocalService) {
		_osbCountryLocalService = osbCountryLocalService;
	}

	private OSBCountryLocalService _osbCountryLocalService;
}