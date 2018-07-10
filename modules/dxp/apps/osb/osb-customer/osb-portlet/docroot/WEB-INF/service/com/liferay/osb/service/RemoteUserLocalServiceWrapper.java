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
 * Provides a wrapper for {@link RemoteUserLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteUserLocalService
 * @generated
 */
@ProviderType
public class RemoteUserLocalServiceWrapper implements RemoteUserLocalService,
	ServiceWrapper<RemoteUserLocalService> {
	public RemoteUserLocalServiceWrapper(
		RemoteUserLocalService remoteUserLocalService) {
		_remoteUserLocalService = remoteUserLocalService;
	}

	@Override
	public com.liferay.portal.kernel.model.User fetchUserByEmailAddress(
		java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteUserLocalService.fetchUserByEmailAddress(emailAddress);
	}

	@Override
	public com.liferay.portal.kernel.model.User getUserByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteUserLocalService.getUserByUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.User translate(
		com.liferay.portal.kernel.json.JSONObject jsonObject)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _remoteUserLocalService.translate(jsonObject);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _remoteUserLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _remoteUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.addOrganizationUsers(organizationId, userIds);
	}

	@Override
	public void addRoleUsers(long roleId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.addRoleUsers(roleId, userIds);
	}

	@Override
	public void deleteRoleUser(long roleId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.deleteRoleUser(roleId, userId);
	}

	@Override
	public void synchronize(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.synchronize(userId);
	}

	@Override
	public void unsetOrganizationUsers(long organizationId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.unsetOrganizationUsers(organizationId, userIds);
	}

	@Override
	public void updateUserExpandoValue(long userId,
		java.lang.String expandoColumnName, java.lang.Object data)
		throws com.liferay.portal.kernel.exception.PortalException {
		_remoteUserLocalService.updateUserExpandoValue(userId,
			expandoColumnName, data);
	}

	@Override
	public RemoteUserLocalService getWrappedService() {
		return _remoteUserLocalService;
	}

	@Override
	public void setWrappedService(RemoteUserLocalService remoteUserLocalService) {
		_remoteUserLocalService = remoteUserLocalService;
	}

	private RemoteUserLocalService _remoteUserLocalService;
}