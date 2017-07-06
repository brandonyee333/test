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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OSBRegionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OSBRegionLocalService
 * @generated
 */
@ProviderType
public class OSBRegionLocalServiceWrapper implements OSBRegionLocalService,
	ServiceWrapper<OSBRegionLocalService> {
	public OSBRegionLocalServiceWrapper(
		OSBRegionLocalService osbRegionLocalService) {
		_osbRegionLocalService = osbRegionLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.Region addRegion(long countryId,
		java.lang.String regionCode, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.addRegion(countryId, regionCode, name,
			active);
	}

	@Override
	public com.liferay.portal.kernel.model.Region deleteRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.deleteRegion(regionId);
	}

	@Override
	public com.liferay.portal.kernel.model.Region fetchRegion(long countryId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.fetchRegion(countryId, name);
	}

	@Override
	public com.liferay.portal.kernel.model.Region getRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegion(regionId);
	}

	@Override
	public com.liferay.portal.kernel.model.Region updateRegion(long regionId,
		long countryId, java.lang.String regionCode, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.updateRegion(regionId, countryId,
			regionCode, name, active);
	}

	@Override
	public int getRegionsCount(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegionsCount(countryId);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _osbRegionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _osbRegionLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		long countryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _osbRegionLocalService.getRegions(countryId, start, end);
	}

	@Override
	public OSBRegionLocalService getWrappedService() {
		return _osbRegionLocalService;
	}

	@Override
	public void setWrappedService(OSBRegionLocalService osbRegionLocalService) {
		_osbRegionLocalService = osbRegionLocalService;
	}

	private OSBRegionLocalService _osbRegionLocalService;
}