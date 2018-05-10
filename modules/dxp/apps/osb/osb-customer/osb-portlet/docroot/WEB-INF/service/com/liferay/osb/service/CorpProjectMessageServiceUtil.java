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
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for CorpProjectMessage. This utility wraps
 * {@link com.liferay.osb.service.impl.CorpProjectMessageServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessageService
 * @see com.liferay.osb.service.base.CorpProjectMessageServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpProjectMessageServiceImpl
 * @generated
 */
@ProviderType
public class CorpProjectMessageServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpProjectMessageServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		java.lang.String userUuid, long corpProjectId, int type,
		int severityLevel, java.lang.String title, java.lang.String content,
		boolean displayCP, boolean displayLCS,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCorpProjectMessage(userUuid, corpProjectId, type,
			severityLevel, title, content, displayCP, displayLCS, serviceContext);
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

	public static void clearService() {
		_service = null;
	}

	public static CorpProjectMessageService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpProjectMessageService.class.getName());

			if (invokableService instanceof CorpProjectMessageService) {
				_service = (CorpProjectMessageService)invokableService;
			}
			else {
				_service = new CorpProjectMessageServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(CorpProjectMessageServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static CorpProjectMessageService _service;
}