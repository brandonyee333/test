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
 * This class is a wrapper for {@link OSBRegionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OSBRegionLocalService
 * @generated
 */
public class OSBRegionLocalServiceWrapper implements OSBRegionLocalService,
	ServiceWrapper<OSBRegionLocalService> {
	public OSBRegionLocalServiceWrapper(
		OSBRegionLocalService osbRegionLocalService) {
		_osbRegionLocalService = osbRegionLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _osbRegionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_osbRegionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _osbRegionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.model.Region addRegion(long countryId,
		java.lang.String regionCode, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.addRegion(countryId, regionCode, name,
			active);
	}

	public com.liferay.portal.model.Region deleteRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.deleteRegion(regionId);
	}

	public com.liferay.portal.model.Region fetchRegion(long countryId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.fetchRegion(countryId, name);
	}

	public com.liferay.portal.model.Region getRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegion(regionId);
	}

	public java.util.List<com.liferay.portal.model.Region> getRegions(
		long countryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegions(countryId, start, end);
	}

	public int getRegionsCount(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegionsCount(countryId);
	}

	public com.liferay.portal.model.Region updateRegion(long regionId,
		long countryId, java.lang.String regionCode, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.updateRegion(regionId, countryId,
			regionCode, name, active);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OSBRegionLocalService getWrappedOSBRegionLocalService() {
		return _osbRegionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOSBRegionLocalService(
		OSBRegionLocalService osbRegionLocalService) {
		_osbRegionLocalService = osbRegionLocalService;
	}

	public OSBRegionLocalService getWrappedService() {
		return _osbRegionLocalService;
	}

	public void setWrappedService(OSBRegionLocalService osbRegionLocalService) {
		_osbRegionLocalService = osbRegionLocalService;
	}

	private OSBRegionLocalService _osbRegionLocalService;
}