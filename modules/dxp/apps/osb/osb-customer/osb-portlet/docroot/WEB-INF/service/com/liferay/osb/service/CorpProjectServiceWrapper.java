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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CorpProjectService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpProjectService
 * @generated
 */
public class CorpProjectServiceWrapper implements CorpProjectService,
	ServiceWrapper<CorpProjectService> {
	public CorpProjectServiceWrapper(CorpProjectService corpProjectService) {
		_corpProjectService = corpProjectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpProjectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpProjectService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpProjectService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.addCorpProject(name);
	}

	public com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String userUuid, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.addCorpProject(userUuid, name);
	}

	public com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.addCorpProject(dossieraProjectKey,
			salesforceProjectKey, name);
	}

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.addCorpProjectUsers(corpProjectId, userIds);
	}

	public void addCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.addCorpProjectUsers(corpProjectId, userUuids);
	}

	public void addUserCorpProjectRoles(long corpProjectId, long[] userIds,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.addUserCorpProjectRoles(corpProjectId, userIds,
			roleId);
	}

	public void addUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.addUserCorpProjectRoles(corpProjectId, userUuids,
			roleName);
	}

	public com.liferay.osb.model.CorpProject deleteCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.deleteCorpProject(corpProjectId);
	}

	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.deleteUserCorpProjectRoles(corpProjectId, userIds,
			roleId);
	}

	public void deleteUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.deleteUserCorpProjectRoles(corpProjectId,
			userUuids, roleId);
	}

	public com.liferay.osb.model.CorpProject getCorpProject(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.getCorpProject(corpProjectId);
	}

	public java.util.List<com.liferay.osb.model.CorpProject> getUserCorpProjects(
		java.lang.String userUuid, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.getUserCorpProjects(userUuid, roleName);
	}

	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.hasUserCorpProject(userId, corpProjectId);
	}

	public boolean hasUserCorpProject(java.lang.String userUuid,
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.hasUserCorpProject(userUuid, corpProjectId);
	}

	public boolean hasUserCorpProjectRole(java.lang.String userUuid,
		long corpProjectId, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.hasUserCorpProjectRole(userUuid,
			corpProjectId, roleName);
	}

	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.unsetCorpProjectUsers(corpProjectId, userIds);
	}

	public void unsetCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpProjectService.unsetCorpProjectUsers(corpProjectId, userUuids);
	}

	public com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.updateCorpProject(corpProjectId, name);
	}

	public com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpProjectService.updateCorpProject(corpProjectId,
			dossieraProjectKey, salesforceProjectKey, name, accountEntryId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpProjectService getWrappedCorpProjectService() {
		return _corpProjectService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpProjectService(
		CorpProjectService corpProjectService) {
		_corpProjectService = corpProjectService;
	}

	public CorpProjectService getWrappedService() {
		return _corpProjectService;
	}

	public void setWrappedService(CorpProjectService corpProjectService) {
		_corpProjectService = corpProjectService;
	}

	private CorpProjectService _corpProjectService;
}