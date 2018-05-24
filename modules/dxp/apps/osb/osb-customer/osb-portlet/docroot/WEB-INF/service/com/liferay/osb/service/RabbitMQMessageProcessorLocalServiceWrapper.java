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
 * Provides a wrapper for {@link RabbitMQMessageProcessorLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RabbitMQMessageProcessorLocalService
 * @generated
 */
@ProviderType
public class RabbitMQMessageProcessorLocalServiceWrapper
	implements RabbitMQMessageProcessorLocalService,
		ServiceWrapper<RabbitMQMessageProcessorLocalService> {
	public RabbitMQMessageProcessorLocalServiceWrapper(
		RabbitMQMessageProcessorLocalService rabbitMQMessageProcessorLocalService) {
		_rabbitMQMessageProcessorLocalService = rabbitMQMessageProcessorLocalService;
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _rabbitMQMessageProcessorLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _rabbitMQMessageProcessorLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void processCreateMessage(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		_rabbitMQMessageProcessorLocalService.processCreateMessage(jsonObject);
	}

	@Override
	public void processUpdateMessage(
		com.liferay.portal.kernel.json.JSONObject jsonObject) {
		_rabbitMQMessageProcessorLocalService.processUpdateMessage(jsonObject);
	}

	@Override
	public RabbitMQMessageProcessorLocalService getWrappedService() {
		return _rabbitMQMessageProcessorLocalService;
	}

	@Override
	public void setWrappedService(
		RabbitMQMessageProcessorLocalService rabbitMQMessageProcessorLocalService) {
		_rabbitMQMessageProcessorLocalService = rabbitMQMessageProcessorLocalService;
	}

	private RabbitMQMessageProcessorLocalService _rabbitMQMessageProcessorLocalService;
}