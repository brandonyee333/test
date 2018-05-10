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
 * Provides a wrapper for {@link CorpProjectMessageService}.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessageService
 * @generated
 */
@ProviderType
public class CorpProjectMessageServiceWrapper
	implements CorpProjectMessageService,
		ServiceWrapper<CorpProjectMessageService> {
	public CorpProjectMessageServiceWrapper(
		CorpProjectMessageService corpProjectMessageService) {
		_corpProjectMessageService = corpProjectMessageService;
	}

	@Override
	public com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		java.lang.String userUuid, long corpProjectId, int type,
		int severityLevel, java.lang.String title, java.lang.String content,
		boolean displayCP, boolean displayLCS,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectMessageService.addCorpProjectMessage(userUuid,
			corpProjectId, type, severityLevel, title, content, displayCP,
			displayLCS, serviceContext);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpProjectMessageService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _corpProjectMessageService.getOSGiServiceIdentifier();
	}

	@Override
	public CorpProjectMessageService getWrappedService() {
		return _corpProjectMessageService;
	}

	@Override
	public void setWrappedService(
		CorpProjectMessageService corpProjectMessageService) {
		_corpProjectMessageService = corpProjectMessageService;
	}

	private CorpProjectMessageService _corpProjectMessageService;
}