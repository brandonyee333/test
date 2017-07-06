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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for OSBRegion. This utility wraps
 * {@link com.liferay.osb.service.impl.OSBRegionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OSBRegionLocalService
 * @see com.liferay.osb.service.base.OSBRegionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OSBRegionLocalServiceImpl
 * @generated
 */
@ProviderType
public class OSBRegionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OSBRegionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.Region addRegion(
		long countryId, java.lang.String regionCode, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addRegion(countryId, regionCode, name, active);
	}

	public static com.liferay.portal.kernel.model.Region deleteRegion(
		long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteRegion(regionId);
	}

	public static com.liferay.portal.kernel.model.Region fetchRegion(
		long countryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchRegion(countryId, name);
	}

	public static com.liferay.portal.kernel.model.Region getRegion(
		long regionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getRegion(regionId);
	}

	public static com.liferay.portal.kernel.model.Region updateRegion(
		long regionId, long countryId, java.lang.String regionCode,
		java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateRegion(regionId, countryId, regionCode, name, active);
	}

	public static int getRegionsCount(long countryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRegionsCount(countryId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		long countryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getRegions(countryId, start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static OSBRegionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OSBRegionLocalService.class.getName());

			if (invokableLocalService instanceof OSBRegionLocalService) {
				_service = (OSBRegionLocalService)invokableLocalService;
			}
			else {
				_service = new OSBRegionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OSBRegionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OSBRegionLocalService _service;
}