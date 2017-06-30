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
 * This class is a wrapper for {@link AppPricingService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPricingService
 * @generated
 */
public class AppPricingServiceWrapper implements AppPricingService,
	ServiceWrapper<AppPricingService> {
	public AppPricingServiceWrapper(AppPricingService appPricingService) {
		_appPricingService = appPricingService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPricingService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPricingService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPricingService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.AppPricing addAppPricing(long appVersionId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingService.addAppPricing(appVersionId, name);
	}

	public com.liferay.osb.model.AppPricing deleteAppPricing(long appPricingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingService.deleteAppPricing(appPricingId);
	}

	public com.liferay.osb.model.AppPricing updateAppPricing(
		long appPricingId, java.lang.String name, long currencyEntryId,
		double standardSupportPrice, double developerSupportPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingService.updateAppPricing(appPricingId, name,
			currencyEntryId, standardSupportPrice, developerSupportPrice);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPricingService getWrappedAppPricingService() {
		return _appPricingService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPricingService(AppPricingService appPricingService) {
		_appPricingService = appPricingService;
	}

	public AppPricingService getWrappedService() {
		return _appPricingService;
	}

	public void setWrappedService(AppPricingService appPricingService) {
		_appPricingService = appPricingService;
	}

	private AppPricingService _appPricingService;
}