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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for OSBPortletPreferences. This utility wraps
 * {@link com.liferay.osb.customer.data.migration.service.impl.OSBPortletPreferencesServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see OSBPortletPreferencesService
 * @see com.liferay.osb.customer.data.migration.service.base.OSBPortletPreferencesServiceBaseImpl
 * @see com.liferay.osb.customer.data.migration.service.impl.OSBPortletPreferencesServiceImpl
 * @generated
 */
@ProviderType
public class OSBPortletPreferencesServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.customer.data.migration.service.impl.OSBPortletPreferencesServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.model.PortletPreferences addPortletPreferences(
		long companyId, long ownerId, int ownerType, long plid,
		java.lang.String portletId, java.lang.String preferences)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addPortletPreferences(companyId, ownerId, ownerType, plid,
			portletId, preferences);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static OSBPortletPreferencesService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OSBPortletPreferencesService, OSBPortletPreferencesService> _serviceTracker =
		ServiceTrackerFactory.open(OSBPortletPreferencesService.class);
}