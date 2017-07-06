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
 * Provides the local service utility for OSBCountry. This utility wraps
 * {@link com.liferay.osb.service.impl.OSBCountryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OSBCountryLocalService
 * @see com.liferay.osb.service.base.OSBCountryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OSBCountryLocalServiceImpl
 * @generated
 */
@ProviderType
public class OSBCountryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OSBCountryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.Country addCountry(
		java.lang.String name, java.lang.String a2, java.lang.String a3,
		java.lang.String number, java.lang.String idd, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addCountry(name, a2, a3, number, idd, active);
	}

	public static com.liferay.portal.kernel.model.Country deleteCountry(
		long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCountry(countryId);
	}

	public static com.liferay.portal.kernel.model.Country getCountry(
		long countryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountry(countryId);
	}

	public static com.liferay.portal.kernel.model.Country updateCountry(
		long countryId, java.lang.String name, java.lang.String a2,
		java.lang.String a3, java.lang.String number, java.lang.String idd,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCountry(countryId, name, a2, a3, number, idd, active);
	}

	public static int getCountriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountriesCount();
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

	public static java.util.List<com.liferay.portal.kernel.model.Country> getCountries()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountries();
	}

	public static java.util.List<com.liferay.portal.kernel.model.Country> getCountries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCountries(start, end);
	}

	public static void clearService() {
		_service = null;
	}

	public static OSBCountryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OSBCountryLocalService.class.getName());

			if (invokableLocalService instanceof OSBCountryLocalService) {
				_service = (OSBCountryLocalService)invokableLocalService;
			}
			else {
				_service = new OSBCountryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OSBCountryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OSBCountryLocalService _service;
}