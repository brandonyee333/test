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
 * Provides a wrapper for {@link AccountEnvironmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentLocalService
 * @generated
 */
@ProviderType
public class AccountEnvironmentLocalServiceWrapper
	implements AccountEnvironmentLocalService,
		ServiceWrapper<AccountEnvironmentLocalService> {
	public AccountEnvironmentLocalServiceWrapper(
		AccountEnvironmentLocalService accountEnvironmentLocalService) {
		_accountEnvironmentLocalService = accountEnvironmentLocalService;
	}

	/**
	* Adds the account environment to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was added
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment) {
		return _accountEnvironmentLocalService.addAccountEnvironment(accountEnvironment);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		long userId, long accountEntryId, long productEntryId,
		java.lang.String name, int envOS, java.lang.String envOSCustom,
		int envDB, int envJVM, int envAS, int envLFR, int envCommerce,
		int envBrowser, int envCS, java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.addAccountEnvironment(userId,
			accountEntryId, productEntryId, name, envOS, envOSCustom, envDB,
			envJVM, envAS, envLFR, envCommerce, envBrowser, envCS, envSearch,
			files, types);
	}

	/**
	* Creates a new account environment with the primary key. Does not add the account environment to the database.
	*
	* @param accountEnvironmentId the primary key for the new account environment
	* @return the new account environment
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment createAccountEnvironment(
		long accountEnvironmentId) {
		return _accountEnvironmentLocalService.createAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Deletes the account environment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was removed
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment) {
		return _accountEnvironmentLocalService.deleteAccountEnvironment(accountEnvironment);
	}

	/**
	* Deletes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment that was removed
	* @throws PortalException if a account environment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.deleteAccountEnvironment(accountEnvironmentId);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment fetchAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name) {
		return _accountEnvironmentLocalService.fetchAccountEnvironment(accountEntryId,
			productEntryId, name);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment fetchAccountEnvironment(
		long accountEnvironmentId) {
		return _accountEnvironmentLocalService.fetchAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Returns the account environment with the primary key.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment
	* @throws PortalException if a account environment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.getAccountEnvironment(accountEnvironmentId);
	}

	/**
	* Updates the account environment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironment the account environment
	* @return the account environment that was updated
	*/
	@Override
	public com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		com.liferay.osb.model.AccountEnvironment accountEnvironment) {
		return _accountEnvironmentLocalService.updateAccountEnvironment(accountEnvironment);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		long userId, long accountEnvironmentId, long productEntryId,
		java.lang.String name, int envOS, java.lang.String envOSCustom,
		int envDB, int envJVM, int envAS, int envLFR, int envCommerce,
		int envBrowser, int envCS, java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.updateAccountEnvironment(userId,
			accountEnvironmentId, productEntryId, name, envOS, envOSCustom,
			envDB, envJVM, envAS, envLFR, envCommerce, envBrowser, envCS,
			envSearch, files, types);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _accountEnvironmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _accountEnvironmentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _accountEnvironmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of account environments.
	*
	* @return the number of account environments
	*/
	@Override
	public int getAccountEnvironmentsCount() {
		return _accountEnvironmentLocalService.getAccountEnvironmentsCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _accountEnvironmentLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _accountEnvironmentLocalService.getOSGiServiceIdentifier();
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
		return _accountEnvironmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountEnvironmentLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _accountEnvironmentLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of account environments
	*/
	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		int start, int end) {
		return _accountEnvironmentLocalService.getAccountEnvironments(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.getAccountEnvironments(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId, long productEntryId) {
		return _accountEnvironmentLocalService.getAccountEnvironments(accountEntryId,
			productEntryId);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _accountEnvironmentLocalService.getAccountEnvironmentsMap(accountEntryId);
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
		return _accountEnvironmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _accountEnvironmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public AccountEnvironmentLocalService getWrappedService() {
		return _accountEnvironmentLocalService;
	}

	@Override
	public void setWrappedService(
		AccountEnvironmentLocalService accountEnvironmentLocalService) {
		_accountEnvironmentLocalService = accountEnvironmentLocalService;
	}

	private AccountEnvironmentLocalService _accountEnvironmentLocalService;
}