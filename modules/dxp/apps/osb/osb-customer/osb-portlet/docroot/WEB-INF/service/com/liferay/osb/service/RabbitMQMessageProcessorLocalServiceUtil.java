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
 * Provides the local service utility for RabbitMQMessageProcessor. This utility wraps
 * {@link com.liferay.osb.service.impl.RabbitMQMessageProcessorLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see RabbitMQMessageProcessorLocalService
 * @see com.liferay.osb.service.base.RabbitMQMessageProcessorLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.RabbitMQMessageProcessorLocalServiceImpl
 * @generated
 */
@ProviderType
public class RabbitMQMessageProcessorLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.RabbitMQMessageProcessorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
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

	public static void processCreateMessage(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().processCreateMessage(jsonObject);
	}

	public static void processUpdateMessage(
		com.liferay.portal.kernel.json.JSONObject jsonObject) {
		getService().processUpdateMessage(jsonObject);
	}

	public static void clearService() {
		_service = null;
	}

	public static RabbitMQMessageProcessorLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					RabbitMQMessageProcessorLocalService.class.getName());

			if (invokableLocalService instanceof RabbitMQMessageProcessorLocalService) {
				_service = (RabbitMQMessageProcessorLocalService)invokableLocalService;
			}
			else {
				_service = new RabbitMQMessageProcessorLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(RabbitMQMessageProcessorLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static RabbitMQMessageProcessorLocalService _service;
}