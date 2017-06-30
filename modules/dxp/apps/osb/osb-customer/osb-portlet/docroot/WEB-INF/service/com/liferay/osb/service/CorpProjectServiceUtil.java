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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the corp project remote service. This utility wraps {@link com.liferay.osb.service.impl.CorpProjectServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectService
 * @see com.liferay.osb.service.base.CorpProjectServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpProjectServiceImpl
 * @generated
 */
public class CorpProjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addCorpProject(name);
	}

	public static com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String userUuid, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addCorpProject(userUuid, name);
	}

	public static com.liferay.osb.model.CorpProject addCorpProject(
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpProject(dossieraProjectKey, salesforceProjectKey,
			name);
	}

	public static void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpProjectUsers(corpProjectId, userIds);
	}

	public static void addCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpProjectUsers(corpProjectId, userUuids);
	}

	public static void addUserCorpProjectRoles(long corpProjectId,
		long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserCorpProjectRoles(corpProjectId, userIds, roleId);
	}

	public static void addUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addUserCorpProjectRoles(corpProjectId, userUuids, roleName);
	}

	public static com.liferay.osb.model.CorpProject deleteCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpProject(corpProjectId);
	}

	public static void deleteUserCorpProjectRoles(long corpProjectId,
		long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserCorpProjectRoles(corpProjectId, userIds, roleId);
	}

	public static void deleteUserCorpProjectRoles(long corpProjectId,
		java.lang.String[] userUuids, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteUserCorpProjectRoles(corpProjectId, userUuids, roleId);
	}

	public static com.liferay.osb.model.CorpProject getCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProject(corpProjectId);
	}

	public static java.util.List<com.liferay.osb.model.CorpProject> getUserCorpProjects(
		java.lang.String userUuid, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserCorpProjects(userUuid, roleName);
	}

	public static boolean hasUserCorpProject(long userId, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserCorpProject(userId, corpProjectId);
	}

	public static boolean hasUserCorpProject(java.lang.String userUuid,
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserCorpProject(userUuid, corpProjectId);
	}

	public static boolean hasUserCorpProjectRole(java.lang.String userUuid,
		long corpProjectId, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasUserCorpProjectRole(userUuid, corpProjectId, roleName);
	}

	public static void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsetCorpProjectUsers(corpProjectId, userIds);
	}

	public static void unsetCorpProjectUsers(long corpProjectId,
		java.lang.String[] userUuids)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsetCorpProjectUsers(corpProjectId, userUuids);
	}

	public static com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProject(corpProjectId, name);
	}

	public static com.liferay.osb.model.CorpProject updateCorpProject(
		long corpProjectId, java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCorpProject(corpProjectId, dossieraProjectKey,
			salesforceProjectKey, name, accountEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpProjectService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpProjectService.class.getName());

			if (invokableService instanceof CorpProjectService) {
				_service = (CorpProjectService)invokableService;
			}
			else {
				_service = new CorpProjectServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(CorpProjectServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpProjectService service) {
	}

	private static CorpProjectService _service;
}