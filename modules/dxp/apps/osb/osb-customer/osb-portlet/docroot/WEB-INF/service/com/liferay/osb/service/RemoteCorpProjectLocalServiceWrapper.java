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
 * Provides a wrapper for {@link RemoteCorpProjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpProjectLocalService
 * @generated
 */
@ProviderType
public class RemoteCorpProjectLocalServiceWrapper
	implements RemoteCorpProjectLocalService,
		ServiceWrapper<RemoteCorpProjectLocalService> {
	public RemoteCorpProjectLocalServiceWrapper(
		RemoteCorpProjectLocalService remoteCorpProjectLocalService) {
		_remoteCorpProjectLocalService = remoteCorpProjectLocalService;
	}

	@Override
	public com.liferay.osb.model.CorpProject addCorpProject(
		long creatorUserId, long ownerUserId,
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteCorpProjectLocalService.addCorpProject(creatorUserId,
			ownerUserId, dossieraProjectKey, salesforceProjectKey, name);
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteCorpProjectLocalService.addCorpProjectUsers(corpProjectId,
			userIds);
	}

	@Override
	public void addUserCorpProjectRoles(long corpProjectId, long[] userIds,
		long roleId) throws com.liferay.portal.kernel.exception.PortalException {
		_remoteCorpProjectLocalService.addUserCorpProjectRoles(corpProjectId,
			userIds, roleId);
	}

	@Override
	public void deleteCorpProject(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteCorpProjectLocalService.deleteCorpProject(corpProjectId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _remoteCorpProjectLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteCorpProjectLocalService.updateCorpProject(corpProjectId,
			name);
	}

	@Override
	public RemoteCorpProjectLocalService getWrappedService() {
		return _remoteCorpProjectLocalService;
	}

	@Override
	public void setWrappedService(
		RemoteCorpProjectLocalService remoteCorpProjectLocalService) {
		_remoteCorpProjectLocalService = remoteCorpProjectLocalService;
	}

	private RemoteCorpProjectLocalService _remoteCorpProjectLocalService;
}