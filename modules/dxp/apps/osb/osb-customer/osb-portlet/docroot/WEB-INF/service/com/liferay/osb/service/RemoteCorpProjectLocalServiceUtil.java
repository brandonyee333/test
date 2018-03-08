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
 * Provides the local service utility for RemoteCorpProject. This utility wraps
 * {@link com.liferay.osb.service.impl.RemoteCorpProjectLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpProjectLocalService
 * @see com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.RemoteCorpProjectLocalServiceImpl
 * @generated
 */
@ProviderType
public class RemoteCorpProjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.RemoteCorpProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.CorpProject addCorpProject(
		long creatorUserId, long ownerUserId,
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCorpProject(creatorUserId, ownerUserId,
			dossieraProjectKey, salesforceProjectKey, name);
	}

	public static void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addCorpProjectUsers(corpProjectId, userIds);
	}

	public static void addUserCorpProjectRoles(long corpProjectId,
		long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().addUserCorpProjectRoles(corpProjectId, userIds, roleId);
	}

	public static void deleteCorpProject(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCorpProject(corpProjectId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateCorpProject(corpProjectId, name);
	}

	public static void clearService() {
		_service = null;
	}

	public static RemoteCorpProjectLocalService getService() {
		if (_service == null) {
			_service = (RemoteCorpProjectLocalService)PortletBeanLocatorUtil.locate(ServletContextUtil.getServletContextName(),
					RemoteCorpProjectLocalService.class.getName());

			ReferenceRegistry.registerReference(RemoteCorpProjectLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RemoteCorpProjectLocalService _service;
}