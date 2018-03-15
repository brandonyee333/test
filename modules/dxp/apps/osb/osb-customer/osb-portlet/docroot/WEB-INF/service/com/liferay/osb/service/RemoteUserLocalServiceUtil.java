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
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for RemoteUser. This utility wraps
 * {@link com.liferay.osb.service.impl.RemoteUserLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteUserLocalService
 * @see com.liferay.osb.service.base.RemoteUserLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.RemoteUserLocalServiceImpl
 * @generated
 */
@ProviderType
public class RemoteUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.RemoteUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addOrganizationUsers(long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addOrganizationUsers(organizationId, userIds);
	}

	public static void addRoleUsers(long roleId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addRoleUsers(roleId, userIds);
	}

	public static void deleteRoleUser(long roleId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteRoleUser(roleId, userId);
	}

	public static com.liferay.portal.kernel.model.User fetchUserByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchUserByEmailAddress(emailAddress);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void unsetOrganizationUsers(long organizationId,
		long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().unsetOrganizationUsers(organizationId, userIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static RemoteUserLocalService getService() {
		if (_service == null) {
			_service = (RemoteUserLocalService)PortletBeanLocatorUtil.locate(ServletContextUtil.getServletContextName(),
					RemoteUserLocalService.class.getName());

			ReferenceRegistry.registerReference(RemoteUserLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RemoteUserLocalService _service;
}