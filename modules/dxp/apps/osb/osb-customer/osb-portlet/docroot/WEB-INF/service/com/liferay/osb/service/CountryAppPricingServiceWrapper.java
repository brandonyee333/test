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
 * This class is a wrapper for {@link CountryAppPricingService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CountryAppPricingService
 * @generated
 */
public class CountryAppPricingServiceWrapper implements CountryAppPricingService,
	ServiceWrapper<CountryAppPricingService> {
	public CountryAppPricingServiceWrapper(
		CountryAppPricingService countryAppPricingService) {
		_countryAppPricingService = countryAppPricingService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _countryAppPricingService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_countryAppPricingService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _countryAppPricingService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void deleteCountryAppPricings(long appVersionId, long[] countryIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_countryAppPricingService.deleteCountryAppPricings(appVersionId,
			countryIds);
	}

	public java.util.List<com.liferay.osb.model.CountryAppPricing> updateCountryAppPricings(
		long appVersionId, long appPricingId, long[] countryIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _countryAppPricingService.updateCountryAppPricings(appVersionId,
			appPricingId, countryIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CountryAppPricingService getWrappedCountryAppPricingService() {
		return _countryAppPricingService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCountryAppPricingService(
		CountryAppPricingService countryAppPricingService) {
		_countryAppPricingService = countryAppPricingService;
	}

	public CountryAppPricingService getWrappedService() {
		return _countryAppPricingService;
	}

	public void setWrappedService(
		CountryAppPricingService countryAppPricingService) {
		_countryAppPricingService = countryAppPricingService;
	}

	private CountryAppPricingService _countryAppPricingService;
}