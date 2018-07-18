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
 * Provides a wrapper for {@link RemoteCorpEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpEntryLocalService
 * @generated
 */
@ProviderType
public class RemoteCorpEntryLocalServiceWrapper
	implements RemoteCorpEntryLocalService,
		ServiceWrapper<RemoteCorpEntryLocalService> {
	public RemoteCorpEntryLocalServiceWrapper(
		RemoteCorpEntryLocalService remoteCorpEntryLocalService) {
		_remoteCorpEntryLocalService = remoteCorpEntryLocalService;
	}

	@Override
	public boolean hasCorpEntry(java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteCorpEntryLocalService.hasCorpEntry(dossieraAccountKey);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _remoteCorpEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _remoteCorpEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public RemoteCorpEntryLocalService getWrappedService() {
		return _remoteCorpEntryLocalService;
	}

	@Override
	public void setWrappedService(
		RemoteCorpEntryLocalService remoteCorpEntryLocalService) {
		_remoteCorpEntryLocalService = remoteCorpEntryLocalService;
	}

	private RemoteCorpEntryLocalService _remoteCorpEntryLocalService;
}