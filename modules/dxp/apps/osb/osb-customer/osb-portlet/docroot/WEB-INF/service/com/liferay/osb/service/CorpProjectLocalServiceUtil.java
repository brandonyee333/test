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
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the corp project local service. This utility wraps {@link com.liferay.osb.service.impl.CorpProjectLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectLocalService
 * @see com.liferay.osb.service.base.CorpProjectLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpProjectLocalServiceImpl
 * @generated
 */
public class CorpProjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the corp project to the database. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject addCorpProject(
		com.liferay.osb.model.CorpProject corpProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCorpProject(corpProject);
	}

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	public static com.liferay.osb.model.CorpProject createCorpProject(
		long corpProjectId) {
		return getService().createCorpProject(corpProjectId);
	}

	/**
	* Deletes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws PortalException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject deleteCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpProject(corpProjectId);
	}

	/**
	* Deletes the corp project from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject deleteCorpProject(
		com.liferay.osb.model.CorpProject corpProject)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpProject(corpProject);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.CorpProject fetchCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCorpProject(corpProjectId);
	}

	/**
	* Returns the corp project with the primary key.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws PortalException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject getCorpProject(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProject(corpProjectId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> getCorpProjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjects(start, end);
	}

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpProjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectsCount();
	}

	/**
	* Updates the corp project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @return the corp project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject updateCorpProject(
		com.liferay.osb.model.CorpProject corpProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProject(corpProject);
	}

	/**
	* Updates the corp project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProject the corp project
	* @param merge whether to merge the corp project with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject updateCorpProject(
		com.liferay.osb.model.CorpProject corpProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProject(corpProject, merge);
	}

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
		long creatorUserId, long ownerUserId,
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpProject(creatorUserId, ownerUserId,
			dossieraProjectKey, salesforceProjectKey, name);
	}

	public static void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpProjectUsers(corpProjectId, userIds);
	}

	public static void addUserCorpProjectRoles(long userId, long corpProjectId,
		long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addUserCorpProjectRoles(userId, corpProjectId, userIds, roleId);
	}

	public static void deleteUserCorpProjectRoles(long userId,
		long corpProjectId, long[] userIds, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteUserCorpProjectRoles(userId, corpProjectId, userIds, roleId);
	}

	public static com.liferay.osb.model.CorpProject fetchCorpProject(
		java.lang.String dossieraProjectKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCorpProject(dossieraProjectKey);
	}

	public static java.util.List<com.liferay.osb.model.CorpProject> getCorpProjects(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjects(name, start, end, obc);
	}

	public static int getCorpProjectsCount(java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectsCount(name);
	}

	public static com.liferay.osb.model.CorpProject getOrganizationCorpProject(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationCorpProject(organizationId);
	}

	public static java.util.List<com.liferay.osb.model.CorpProject> getUserCorpProjects(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserCorpProjects(userId);
	}

	public static java.util.List<com.liferay.osb.model.CorpProject> getUserCorpProjects(
		long userId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserCorpProjects(userId, roleId);
	}

	public static java.util.List<com.liferay.osb.model.CorpProject> getUserCorpProjects(
		long userId, java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserCorpProjects(userId, roleName);
	}

	public static boolean hasUserCorpProject(long userId, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserCorpProject(userId, corpProjectId);
	}

	public static boolean hasUserCorpProjectRole(long userId,
		long corpProjectId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasUserCorpProjectRole(userId, corpProjectId, roleId);
	}

	public static void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().unsetCorpProjectUsers(corpProjectId, userIds);
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

	public static void validateMembership(long userId, long corpProjectId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().validateMembership(userId, corpProjectId, roleId);
	}

	public static void validateSalesforceProjectKey(long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().validateSalesforceProjectKey(corpProjectId);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpProjectLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpProjectLocalService.class.getName());

			if (invokableLocalService instanceof CorpProjectLocalService) {
				_service = (CorpProjectLocalService)invokableLocalService;
			}
			else {
				_service = new CorpProjectLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CorpProjectLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpProjectLocalService service) {
	}

	private static CorpProjectLocalService _service;
}