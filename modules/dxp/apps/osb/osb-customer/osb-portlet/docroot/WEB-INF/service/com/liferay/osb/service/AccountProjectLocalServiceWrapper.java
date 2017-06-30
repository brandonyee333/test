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
 * This class is a wrapper for {@link AccountProjectLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AccountProjectLocalService
 * @generated
 */
public class AccountProjectLocalServiceWrapper
	implements AccountProjectLocalService,
		ServiceWrapper<AccountProjectLocalService> {
	public AccountProjectLocalServiceWrapper(
		AccountProjectLocalService accountProjectLocalService) {
		_accountProjectLocalService = accountProjectLocalService;
	}

	/**
	* Adds the account project to the database. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject addAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.addAccountProject(accountProject);
	}

	/**
	* Creates a new account project with the primary key. Does not add the account project to the database.
	*
	* @param accountProjectId the primary key for the new account project
	* @return the new account project
	*/
	public com.liferay.osb.model.AccountProject createAccountProject(
		long accountProjectId) {
		return _accountProjectLocalService.createAccountProject(accountProjectId);
	}

	/**
	* Deletes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project that was removed
	* @throws PortalException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject deleteAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.deleteAccountProject(accountProjectId);
	}

	/**
	* Deletes the account project from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject deleteAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.deleteAccountProject(accountProject);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountProjectLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery, start, end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AccountProject fetchAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.fetchAccountProject(accountProjectId);
	}

	/**
	* Returns the account project with the primary key.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project
	* @throws PortalException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject getAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.getAccountProject(accountProjectId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.getAccountProjects(start, end);
	}

	/**
	* Returns the number of account projects.
	*
	* @return the number of account projects
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountProjectsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.getAccountProjectsCount();
	}

	/**
	* Updates the account project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject updateAccountProject(
		com.liferay.osb.model.AccountProject accountProject)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.updateAccountProject(accountProject);
	}

	/**
	* Updates the account project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @param merge whether to merge the account project with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the account project that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject updateAccountProject(
		com.liferay.osb.model.AccountProject accountProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.updateAccountProject(accountProject,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _accountProjectLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_accountProjectLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountProjectLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.getAccountProjects(accountEntryId);
	}

	public com.liferay.osb.model.AccountProject updateAccountProject(
		long userId, long accountProjectId, long accountEntryId,
		java.lang.String name,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _accountProjectLocalService.updateAccountProject(userId,
			accountProjectId, accountEntryId, name, data);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AccountProjectLocalService getWrappedAccountProjectLocalService() {
		return _accountProjectLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAccountProjectLocalService(
		AccountProjectLocalService accountProjectLocalService) {
		_accountProjectLocalService = accountProjectLocalService;
	}

	public AccountProjectLocalService getWrappedService() {
		return _accountProjectLocalService;
	}

	public void setWrappedService(
		AccountProjectLocalService accountProjectLocalService) {
		_accountProjectLocalService = accountProjectLocalService;
	}

	private AccountProjectLocalService _accountProjectLocalService;
}