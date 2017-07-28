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
 * Provides a wrapper for {@link AccountProjectLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.AccountProject addAccountProject(
		com.liferay.osb.model.AccountProject accountProject) {
		return _accountProjectLocalService.addAccountProject(accountProject);
	}

	/**
	* Creates a new account project with the primary key. Does not add the account project to the database.
	*
	* @param accountProjectId the primary key for the new account project
	* @return the new account project
	*/
	@Override
	public com.liferay.osb.model.AccountProject createAccountProject(
		long accountProjectId) {
		return _accountProjectLocalService.createAccountProject(accountProjectId);
	}

	/**
	* Deletes the account project from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountProject deleteAccountProject(
		com.liferay.osb.model.AccountProject accountProject) {
		return _accountProjectLocalService.deleteAccountProject(accountProject);
	}

	/**
	* Deletes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project that was removed
	* @throws PortalException if a account project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountProject deleteAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectLocalService.deleteAccountProject(accountProjectId);
	}

	@Override
	public com.liferay.osb.model.AccountProject fetchAccountProject(
		long accountProjectId) {
		return _accountProjectLocalService.fetchAccountProject(accountProjectId);
	}

	/**
	* Returns the account project with the primary key.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project
	* @throws PortalException if a account project with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountProject getAccountProject(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectLocalService.getAccountProject(accountProjectId);
	}

	/**
	* Updates the account project in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountProject the account project
	* @return the account project that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountProject updateAccountProject(
		com.liferay.osb.model.AccountProject accountProject) {
		return _accountProjectLocalService.updateAccountProject(accountProject);
	}

	@Override
	public com.liferay.osb.model.AccountProject updateAccountProject(
		long userId, long accountProjectId, long accountEntryId,
		java.lang.String name,
		java.util.Map<java.lang.Integer, java.lang.String> data)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectLocalService.updateAccountProject(userId,
			accountProjectId, accountEntryId, name, data);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountProjectLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountProjectLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountProjectLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountProjectLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account projects.
	*
	* @return the number of account projects
	*/
	@Override
	public int getAccountProjectsCount() {
		return _accountProjectLocalService.getAccountProjectsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountProjectLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountProjectLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _accountProjectLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of account projects
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		int start, int end) {
		return _accountProjectLocalService.getAccountProjects(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountProject> getAccountProjects(
		long accountEntryId) {
		return _accountProjectLocalService.getAccountProjects(accountEntryId);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _accountProjectLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _accountProjectLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AccountProjectLocalService getWrappedService() {
		return _accountProjectLocalService;
	}

	@Override
	public void setWrappedService(
		AccountProjectLocalService accountProjectLocalService) {
		_accountProjectLocalService = accountProjectLocalService;
	}

	private AccountProjectLocalService _accountProjectLocalService;
}