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
 * Provides a wrapper for {@link RemoteCorpProjectMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpProjectMessageLocalService
 * @generated
 */
@ProviderType
public class RemoteCorpProjectMessageLocalServiceWrapper
	implements RemoteCorpProjectMessageLocalService,
		ServiceWrapper<RemoteCorpProjectMessageLocalService> {
	public RemoteCorpProjectMessageLocalServiceWrapper(
		RemoteCorpProjectMessageLocalService remoteCorpProjectMessageLocalService) {
		_remoteCorpProjectMessageLocalService = remoteCorpProjectMessageLocalService;
	}

	@Override
	public com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		long userId, long corpProjectId, int type, int severityLevel,
		java.lang.String title, java.lang.String content, boolean displayCP,
		boolean displayLCS, boolean displayLESA)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteCorpProjectMessageLocalService.addCorpProjectMessage(userId,
			corpProjectId, type, severityLevel, title, content, displayCP,
			displayLCS, displayLESA);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _remoteCorpProjectMessageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _remoteCorpProjectMessageLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void deleteCorpProjectMessage(long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteCorpProjectMessageLocalService.deleteCorpProjectMessage(corpProjectMessageId);
	}

	@Override
	public RemoteCorpProjectMessageLocalService getWrappedService() {
		return _remoteCorpProjectMessageLocalService;
	}

	@Override
	public void setWrappedService(
		RemoteCorpProjectMessageLocalService remoteCorpProjectMessageLocalService) {
		_remoteCorpProjectMessageLocalService = remoteCorpProjectMessageLocalService;
	}

	private RemoteCorpProjectMessageLocalService _remoteCorpProjectMessageLocalService;
}