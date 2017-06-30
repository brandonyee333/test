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
 * This class is a wrapper for {@link OSBRegionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OSBRegionService
 * @generated
 */
public class OSBRegionServiceWrapper implements OSBRegionService,
	ServiceWrapper<OSBRegionService> {
	public OSBRegionServiceWrapper(OSBRegionService osbRegionService) {
		_osbRegionService = osbRegionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _osbRegionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_osbRegionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _osbRegionService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.portal.model.Region addRegion(long countryId,
		java.lang.String regionCode, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionService.addRegion(countryId, regionCode, name, active);
	}

	public com.liferay.portal.model.Region deleteRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionService.deleteRegion(regionId);
	}

	public com.liferay.portal.model.Region updateRegion(long regionId,
		long countryId, java.lang.String regionCode, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionService.updateRegion(regionId, countryId, regionCode,
			name, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OSBRegionService getWrappedOSBRegionService() {
		return _osbRegionService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOSBRegionService(OSBRegionService osbRegionService) {
		_osbRegionService = osbRegionService;
	}

	public OSBRegionService getWrappedService() {
		return _osbRegionService;
	}

	public void setWrappedService(OSBRegionService osbRegionService) {
		_osbRegionService = osbRegionService;
	}

	private OSBRegionService _osbRegionService;
}