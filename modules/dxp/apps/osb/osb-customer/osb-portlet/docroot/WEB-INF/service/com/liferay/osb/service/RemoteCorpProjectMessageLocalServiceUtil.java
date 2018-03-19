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
 * Provides the local service utility for RemoteCorpProjectMessage. This utility wraps
 * {@link com.liferay.osb.service.impl.RemoteCorpProjectMessageLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpProjectMessageLocalService
 * @see com.liferay.osb.service.base.RemoteCorpProjectMessageLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.RemoteCorpProjectMessageLocalServiceImpl
 * @generated
 */
@ProviderType
public class RemoteCorpProjectMessageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.RemoteCorpProjectMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		long userId, long corpProjectId, int type, int severityLevel,
		java.lang.String title, java.lang.String content, boolean displayCP,
		boolean displayLCS, boolean displayLESA)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCorpProjectMessage(userId, corpProjectId, type,
			severityLevel, title, content, displayCP, displayLCS, displayLESA);
	}

	public static void deleteCorpProjectMessage(long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void clearService() {
		_service = null;
	}

	public static RemoteCorpProjectMessageLocalService getService() {
		if (_service == null) {
			_service = (RemoteCorpProjectMessageLocalService)PortletBeanLocatorUtil.locate(ServletContextUtil.getServletContextName(),
					RemoteCorpProjectMessageLocalService.class.getName());

			ReferenceRegistry.registerReference(RemoteCorpProjectMessageLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RemoteCorpProjectMessageLocalService _service;
}