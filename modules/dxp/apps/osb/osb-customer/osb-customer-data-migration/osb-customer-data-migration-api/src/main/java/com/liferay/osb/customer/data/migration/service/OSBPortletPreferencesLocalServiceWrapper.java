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

package com.liferay.osb.customer.data.migration.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OSBPortletPreferencesLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OSBPortletPreferencesLocalService
 * @generated
 */
@ProviderType
public class OSBPortletPreferencesLocalServiceWrapper
	implements OSBPortletPreferencesLocalService,
		ServiceWrapper<OSBPortletPreferencesLocalService> {
	public OSBPortletPreferencesLocalServiceWrapper(
		OSBPortletPreferencesLocalService osbPortletPreferencesLocalService) {
		_osbPortletPreferencesLocalService = osbPortletPreferencesLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.PortletPreferences addPortletPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		java.lang.String portletId, java.lang.String preferences) {
		return _osbPortletPreferencesLocalService.addPortletPreferences(companyId,
			ownerId, ownerType, plid, portletId, preferences);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _osbPortletPreferencesLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public OSBPortletPreferencesLocalService getWrappedService() {
		return _osbPortletPreferencesLocalService;
	}

	@Override
	public void setWrappedService(
		OSBPortletPreferencesLocalService osbPortletPreferencesLocalService) {
		_osbPortletPreferencesLocalService = osbPortletPreferencesLocalService;
	}

	private OSBPortletPreferencesLocalService _osbPortletPreferencesLocalService;
}