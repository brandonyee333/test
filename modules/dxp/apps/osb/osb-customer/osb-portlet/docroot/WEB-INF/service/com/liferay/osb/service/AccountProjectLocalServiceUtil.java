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
 * The utility for the account project local service. This utility wraps {@link com.liferay.osb.service.impl.AccountProjectLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectLocalService
 * @see com.liferay.osb.service.base.AccountProjectLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountProjectLocalServiceImpl
 * @generated
 */
public class AccountProjectLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AccountProjectLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the account project to the database. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject addAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAccountProject(accountProject);
	}

	/**
	* Creates a new account project with the primary key. Does not add the account project to the database.
	*
	* @param accountProjectId the primary key for the new account project
	* @return the new account project
	*/
	public static com.liferay.osb.model.AccountProject createAccountProject(
		long accountProjectId) {
		return getService().createAccountProject(accountProjectId);
	}

	/**
	* Deletes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project that was removed
	* @throws PortalException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject deleteAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountProject(accountProjectId);
	}

	/**
	* Deletes the account project from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject deleteAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAccountProject(accountProject);
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

	public static com.liferay.osb.model.AccountProject fetchAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAccountProject(accountProjectId);
	}

	/**
	* Returns the account project with the primary key.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project
	* @throws PortalException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject getAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountProject(accountProjectId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of account projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountProjects(start, end);
	}

	/**
	* Returns the number of account projects.
	*
	* @return the number of account projects
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountProjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountProjectsCount();
	}

	/**
	* Updates the account project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject updateAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountProject(accountProject);
	}

	/**
	* Updates the account project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @param merge whether to merge the account project with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountProject updateAccountProject(
		com.liferay.osb.model.AccountProject accountProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAccountProject(accountProject, merge);
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

	public static java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountProjects(accountEntryId);
	}

	public static com.liferay.osb.model.AccountProject updateAccountProject(
		long userId, long accountProjectId, long accountEntryId,
		java.lang.String name,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAccountProject(userId, accountProjectId,
			accountEntryId, name, data);
	}

	public static void clearService() {
		_service = null;
	}

	public static AccountProjectLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AccountProjectLocalService.class.getName());

			if (invokableLocalService instanceof AccountProjectLocalService) {
				_service = (AccountProjectLocalService)invokableLocalService;
			}
			else {
				_service = new AccountProjectLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AccountProjectLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AccountProjectLocalService service) {
	}

	private static AccountProjectLocalService _service;
}